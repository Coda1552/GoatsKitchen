package coda.goatskitchen;

import coda.goatskitchen.common.entities.ChefEntity;
import coda.goatskitchen.common.entities.LonghornEntity;
import coda.goatskitchen.common.init.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GoatsKitchen.MOD_ID)
public class GoatsKitchen {
    public static final String MOD_ID = "goatskitchen";

    public GoatsKitchen() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::registerEntityAttributes);


        GKBlocks.REGISTER.register(bus);
        GKTileEntities.REGISTER.register(bus);
        GKItems.REGISTER.register(bus);
        GKEntities.REGISTER.register(bus);
        GKContainers.REGISTER.register(bus);
        GKFeatures.REGISTER.register(bus);
        GKRecipes.REGISTER.register(bus);
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(GKEntities.CHEF.get(), ChefEntity.createAttributes().build());
        event.put(GKEntities.LONGHORN.get(), LonghornEntity.createAttributes().build());
    }

    public final static ItemGroup GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(GKBlocks.BLENDER.get());
        }
    };
}