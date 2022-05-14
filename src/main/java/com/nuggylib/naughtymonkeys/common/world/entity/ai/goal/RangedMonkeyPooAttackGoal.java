package com.nuggylib.naughtymonkeys.common.world.entity.ai.goal;

import com.nuggylib.naughtymonkeys.common.item.ItemMonkeyPoo;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;

import java.util.EnumSet;

/**
 * {@link net.minecraft.world.entity.ai.goal.RangedBowAttackGoal}
 */
public class RangedMonkeyPooAttackGoal<T extends net.minecraft.world.entity.Mob & RangedAttackMob> extends Goal {

    private final T mob;
    private final double speedModifier;
    private int attackIntervalMin;
    private final float attackRadiusSqr;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public RangedMonkeyPooAttackGoal(T p_25792_, double p_25793_, int p_25794_, float p_25795_) {
        this.mob = p_25792_;
        this.speedModifier = p_25793_;
        this.attackIntervalMin = p_25794_;
        this.attackRadiusSqr = p_25795_ * p_25795_;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void setMinAttackInterval(int p_25798_) {
        this.attackIntervalMin = p_25798_;
    }

    public boolean canUse() {
        return this.mob.getTarget() != null;
    }

    public boolean canContinueToUse() {
        return (this.canUse() || !this.mob.getNavigation().isDone());
    }

    public void start() {
        super.start();
        this.mob.setAggressive(true);
    }

    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.mob.stopUsingItem();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity targetEntity = this.mob.getTarget();
        if (targetEntity != null) {
            double distance = this.mob.distanceToSqr(targetEntity.getX(), targetEntity.getY(), targetEntity.getZ());
            boolean hasLOSOnTarget = this.mob.getSensing().hasLineOfSight(targetEntity);
            boolean continuousLOS = this.seeTime > 0;
            if (hasLOSOnTarget != continuousLOS) {
                this.seeTime = 0;
            }

            if (hasLOSOnTarget) {
                ++this.seeTime;
            } else {
                --this.seeTime;
            }

            if (!(distance > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                this.mob.getNavigation().stop();
                ++this.strafingTime;
            } else {
                this.mob.getNavigation().moveTo(targetEntity, this.speedModifier);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20) {
                if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1) {
                if (distance > (double)(this.attackRadiusSqr * 0.75F)) {
                    this.strafingBackwards = false;
                } else if (distance < (double)(this.attackRadiusSqr * 0.25F)) {
                    this.strafingBackwards = true;
                }

                this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.mob.lookAt(targetEntity, 30.0F, 30.0F);
            } else {
                this.mob.getLookControl().setLookAt(targetEntity, 30.0F, 30.0F);
            }

            if (this.mob.isUsingItem()) {
                if (!hasLOSOnTarget && this.seeTime < -60) {
                    this.mob.stopUsingItem();
                } else if (hasLOSOnTarget) {
                    int i = this.mob.getTicksUsingItem();
                    if (i >= 20) {
                        this.mob.stopUsingItem();
                        this.mob.performRangedAttack(targetEntity, BowItem.getPowerForTime(i));
                        this.attackTime = this.attackIntervalMin;
                    }
                }
            } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
                this.mob.startUsingItem(ProjectileUtil.getWeaponHoldingHand(this.mob, item -> item instanceof ItemMonkeyPoo));
            }

        }
    }

}
