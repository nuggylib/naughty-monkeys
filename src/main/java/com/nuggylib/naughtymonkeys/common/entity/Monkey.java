package com.nuggylib.naughtymonkeys.common.entity;

import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysItems;
import com.nuggylib.naughtymonkeys.common.registry.NaughtyMonkeysTags;
import com.nuggylib.naughtymonkeys.common.world.entity.ai.goal.RangedMonkeyPooAttackGoal;
import com.nuggylib.naughtymonkeys.common.world.entity.projectile.AbstractMonkeyPoo;
import com.nuggylib.naughtymonkeys.common.world.entity.projectile.NaughtyMonkeysProjectileUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * {@link net.minecraft.world.entity.monster.Skeleton} {@link net.minecraft.world.level.block.Blocks}
 */
public class Monkey extends Animal implements RangedAttackMob {
    private int eatAnimationTick;
    public Monkey(EntityType<? extends Animal> type, Level world) {
        super(type, world);
    }

    protected void updateNoActionTime() {
        float f = this.getBrightness();
        if (f > 0.5F) {
            this.noActionTime += 2;
        }

    }

    @Override
    public void aiStep() {
        this.updateSwingTime();
        this.updateNoActionTime();
        super.aiStep();
    }

    @Override
    protected void registerGoals() {

        RangedMonkeyPooAttackGoal<Monkey> monkeyPooRangedAttackGoal = new RangedMonkeyPooAttackGoal<>(this, 1.0D, 50, 15.0F);

        // monkey floats
        goalSelector.addGoal(0, new FloatGoal(this));
        // monkey breeds
        goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        // monkey tempted by bananas
        goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(NaughtyMonkeysItems.BANANA.get()), false));
        // babies follow parent monkeys
        goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        // monkey avoid water
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        // monkey looks at players
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        // monkey randomly looks around
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        // monkey throws poop
        goalSelector.addGoal(4, monkeyPooRangedAttackGoal);
        // monkey alerts others when hurt by a target entity
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        // monkey targets players
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monkey.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    public float getHeadEatAngleScale(float p_29883_) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float f = ((float)(this.eatAnimationTick - 4) - p_29883_) / 32.0F;
            return ((float)Math.PI / 5F) + 0.21991149F * Mth.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? ((float)Math.PI / 5F) : this.getXRot() * ((float)Math.PI / 180F);
        }
    }

    protected AbstractMonkeyPoo getMonkeyPoo(ItemStack p_32156_, float p_32157_) {
        return NaughtyMonkeysProjectileUtil.getMobThrownPoop(this, p_32156_, p_32157_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob mate) {
        return null;
    }

    @Override
    public void performRangedAttack(LivingEntity monkey, float p_32142_) {
        ItemStack itemstack = new ItemStack(NaughtyMonkeysItems.MONKEY_POO.get());
        AbstractMonkeyPoo abstractMonkeyPoo = this.getMonkeyPoo(itemstack, p_32142_);
        double d0 = monkey.getX() - this.getX();
        double d1 = monkey.getY(0.3333333333333333D) - abstractMonkeyPoo.getY();
        double d2 = monkey.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        abstractMonkeyPoo.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(abstractMonkeyPoo);
    }

    public static boolean checkSpawnRules(EntityType<? extends Animal> p_27578_, LevelAccessor levelAccessor, MobSpawnType p_27580_, BlockPos blockPos, Random p_27582_) {
        return (levelAccessor.getBlockState(blockPos.below()).is(NaughtyMonkeysTags.MONKEYS_SPAWNABLE_ON) ||
                levelAccessor.getBlockState(blockPos.above()).is(NaughtyMonkeysTags.MONKEYS_SPAWNABLE_ON)) &&
                isBrightEnoughToSpawn(levelAccessor, blockPos);
    }
}
