package coda.goatskitchen.common.entities;

import coda.goatskitchen.common.init.GKItems;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.LookAtTradingPlayerGoal;
import net.minecraft.world.entity.ai.goal.TradeWithPlayerGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;
import java.util.Random;

public class ChefEntity extends AbstractVillager implements Merchant {
    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> CHEF_TRADES = toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new ItemsForItemsTrade(new ItemStack(GKItems.CHEESE.get(), 3), new ItemStack(GKItems.WINE_BOTTLE.get()), 2, 3, 30),
            new ItemsForItemsTrade(new ItemStack(GKItems.CHEESE.get(), 6), new ItemStack(GKItems.PINEAPPLE.get(), 1), 2, 3, 30),
            new ItemsForItemsTrade(new ItemStack(GKItems.CHEESE.get(), 7), new ItemStack(Items.BREAD, 2), 3, 3, 30),
    }));

    public ChefEntity(EntityType<? extends ChefEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(2, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.6F;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() != GKItems.CHEF_SPAWN_EGG.get() && this.isAlive() && !this.isTrading() && !this.isBaby()) {
            if (hand == InteractionHand.MAIN_HAND) {
                player.awardStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            } else {
                if (!this.level.isClientSide) {
                    this.setTradingPlayer(player);
                    this.openTradingScreen(player, this.getDisplayName(), 0);
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(GKItems.CHEF_SPAWN_EGG.get());
    }

    protected void updateTrades() {
        VillagerTrades.ItemListing[] trade1 = CHEF_TRADES.get(1);
        if (trade1 != null) {
            MerchantOffers offers = this.getOffers();
            this.addOffersFromItemListings(offers, trade1, 5);
            int i = this.random.nextInt(trade1.length);
            VillagerTrades.ItemListing villagertrades$itrade = trade1[i];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.random);
            if (merchantoffer != null) {
                offers.add(merchantoffer);
            }

        }
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (offer.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isTrading() ? SoundEvents.WANDERING_TRADER_TRADE : SoundEvents.WANDERING_TRADER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.WANDERING_TRADER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WANDERING_TRADER_DEATH;
    }

    @Override
    public float getVoicePitch() {
        return 5.0F;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }

    private static class ItemsForItemsTrade implements VillagerTrades.ItemListing {
        private final ItemStack buying1, buying2, selling;
        private final int maxUses, xp;
        private final float priceMultiplier;

        public ItemsForItemsTrade(ItemStack buying1, ItemStack buying2, ItemStack selling, int maxUses, int xp, float priceMultiplier) {
            this.buying1 = buying1;
            this.buying2 = buying2;
            this.selling = selling;
            this.maxUses = maxUses;
            this.xp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        public ItemsForItemsTrade(ItemStack buying1, ItemStack selling, int maxUses, int xp, float priceMultiplier) {
            this(buying1, ItemStack.EMPTY, selling, maxUses, xp, priceMultiplier);
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(buying1, buying2, selling, maxUses, xp, priceMultiplier);
        }
    }
}
