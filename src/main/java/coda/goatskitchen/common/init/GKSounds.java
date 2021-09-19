package coda.goatskitchen.common.init;

import coda.goatskitchen.GoatsKitchen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKSounds {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<SoundEvent> CUTTING_BOARD_CUT = REGISTER.register("cutting_board_cut", () -> new SoundEvent(new ResourceLocation(GoatsKitchen.MOD_ID, "cutting_board.cutting_board_cut")));
    public static final RegistryObject<SoundEvent> LONGHORN_AMBIENT = REGISTER.register("longhorn_ambient", () -> new SoundEvent(new ResourceLocation(GoatsKitchen.MOD_ID, "longhorn.ambient")));
    public static final RegistryObject<SoundEvent> LONGHORN_HURT = REGISTER.register("longhorn_hurt", () -> new SoundEvent(new ResourceLocation(GoatsKitchen.MOD_ID, "longhorn.hurt")));
    public static final RegistryObject<SoundEvent> LONGHORN_DEATH = REGISTER.register("longhorn_death", () -> new SoundEvent(new ResourceLocation(GoatsKitchen.MOD_ID, "longhorn.death")));
}
