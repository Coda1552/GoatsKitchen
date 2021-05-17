package coda.goatskitchen;

import coda.goatskitchen.entities.ChefEntity;
import coda.goatskitchen.init.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GoatsKitchen.MOD_ID)
public class GoatsKitchen {
    public static final String MOD_ID = "goatskitchen";

    public GoatsKitchen() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::registerEntityAttributes);


        GKBlocks.REGISTRY.register(bus);
        GKTileEntities.REGISTRY.register(bus);
        GKItems.REGISTRY.register(bus);
        GKEntities.REGISTRY.register(bus);
        GKContainers.REGISTRY.register(bus);
        GKRecipes.SERIALIZERS.register(bus);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(GKEntities.CHEF.get(), ChefEntity.createAttributes().create());
    }

    public final static ItemGroup GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(GKBlocks.BLENDER.get());
        }
    };
}