package com.nuggylib.naughtymonkeys.common.world.entity.ai.goal;

import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Functions similarly to the vanilla {@link net.minecraft.world.entity.ai.goal.AvoidEntityGoal}, except that this
 * goal is applied to all mobs, except monkeys, and is only active when there is a nearby stinky player.
 */
public class FleeStinkyPlayersGoal<T extends LivingEntity> extends Goal {

    protected final PathfinderMob mob;
    private final double walkSpeedModifier;
    private final double sprintSpeedModifier;
    @Nullable
    protected T toAvoid;
    protected final float maxDist;
    @Nullable
    protected Path path;
    protected final PathNavigation pathNav;
    protected final Class<T> avoidClass;
    protected final Predicate<LivingEntity> avoidPredicate;
    protected final Predicate<LivingEntity> predicateOnAvoidEntity;
    private final TargetingConditions avoidEntityTargeting;

    public FleeStinkyPlayersGoal(PathfinderMob mob, Class<T> clazz, Predicate<LivingEntity> avoidPredicate, float range, double walkSpeedModifier, double sprintSpeedModifier, Predicate<LivingEntity> predicateOnAvoidEntity) {
        this.mob = mob;
        this.avoidClass = clazz;
        this.avoidPredicate = avoidPredicate;
        this.maxDist = range;
        this.walkSpeedModifier = walkSpeedModifier;
        this.sprintSpeedModifier = sprintSpeedModifier;
        this.predicateOnAvoidEntity = predicateOnAvoidEntity;
        this.pathNav = mob.getNavigation();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.avoidEntityTargeting = TargetingConditions.forCombat().range((double)range).selector(predicateOnAvoidEntity.and(avoidPredicate));
    }

    @Override
    public boolean canUse() {
        this.toAvoid = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.avoidClass, this.mob.getBoundingBox().inflate((double)this.maxDist, 3.0D, (double)this.maxDist), (p_148078_) -> {
            return true;
        }), this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
        if (this.toAvoid == null) {
            return false;
        } else {
            Collection<MobEffectInstance> activeEffects = this.toAvoid.getActiveEffects();
            // Only avoid when the player has the stinky effect active
            if (activeEffects.stream().anyMatch((mobEffectInstance -> mobEffectInstance.getEffect() == NaughtyMonkeysEffects.STINKY.get()))) {
                Vec3 vec3 = DefaultRandomPos.getPosAway(this.mob, 16, 7, this.toAvoid.position());
                if (vec3 == null) {
                    return false;
                } else if (this.toAvoid.distanceToSqr(vec3.x, vec3.y, vec3.z) < this.toAvoid.distanceToSqr(this.mob)) {
                    return false;
                } else {
                    this.path = this.pathNav.createPath(vec3.x, vec3.y, vec3.z, 0);
                    return this.path != null;
                }
            }
            return false;
        }
    }

    public void start() {
        this.pathNav.moveTo(this.path, this.walkSpeedModifier);
    }

    public void stop() {
        this.toAvoid = null;
    }

    public void tick() {
        // If mob is within 49 blocks of target, sprint
        if (this.mob.distanceToSqr(Objects.requireNonNull(this.toAvoid)) < 49.0D) {
            this.mob.getNavigation().setSpeedModifier(this.sprintSpeedModifier);
        } else {
            this.mob.getNavigation().setSpeedModifier(this.walkSpeedModifier);
        }

    }
}
