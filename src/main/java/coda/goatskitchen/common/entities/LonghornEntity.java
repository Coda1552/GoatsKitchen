package coda.goatskitchen.common.entities;

import coda.goatskitchen.common.init.GKEntities;
import coda.goatskitchen.common.init.GKItems;
import coda.goatskitchen.common.init.GKSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class LonghornEntity extends Animal {

    public LonghornEntity(EntityType<? extends LonghornEntity> p_i48567_1_, Level p_i48567_2_) {
        super(p_i48567_1_, p_i48567_2_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.MOVEMENT_SPEED, 0.2F).add(Attributes.ATTACK_DAMAGE, 2.0F).add(Attributes.ATTACK_KNOCKBACK, 0.85D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(GKItems.LONGHORN_SPAWN_EGG.get());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return GKEntities.LONGHORN.get().create(p_146743_);
    }

    protected SoundEvent getAmbientSound() {
        return GKSounds.LONGHORN_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource p_28306_) {
        return GKSounds.LONGHORN_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return GKSounds.LONGHORN_DEATH.get();
    }

}
