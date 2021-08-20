package coda.goatskitchen.common.items;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GKSpawnEggItem extends SpawnEggItem {
    public static final List<GKSpawnEggItem> UNADDED_EGGS = new ArrayList<GKSpawnEggItem>();
    private final Lazy<? extends EntityType<?>> entityTypeSupplier;

    public GKSpawnEggItem(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, final int primaryColor, final int secondaryColor, final Properties properties) {
        super(null, primaryColor, secondaryColor, properties);
        this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
        UNADDED_EGGS.add(this);
    }

    @Override
    public EntityType<?> getType(CompoundTag nbt) {
        return this.entityTypeSupplier.get();
    }
}