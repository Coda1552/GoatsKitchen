package coda.goatskitchen;

import coda.goatskitchen.init.GKBlocks;
import coda.goatskitchen.init.GKContainers;
import coda.goatskitchen.init.GKItems;
import coda.goatskitchen.init.GKTileEntities;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GoatsKitchen.MOD_ID)
public class GoatsKitchen {
    public static final String MOD_ID = "goatskitchen";

    public GoatsKitchen() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        GKBlocks.REGISTRY.register(bus);
        GKTileEntities.REGISTRY.register(bus);
        GKItems.REGISTRY.register(bus);
        GKContainers.REGISTRY.register(bus);
    }

    public final static ItemGroup GROUP = new ItemGroup(MOD_ID) {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(GKBlocks.BLENDER.get());
        }
    };
}