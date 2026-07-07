package com.vivids.mod.registry;

import com.vivids.mod.VividsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registro de sonidos.
 * ESTADO: ESQUELETO DE AUDIO - los eventos de sonido estan registrados y
 * referenciados en sounds.json, pero los archivos .ogg reales NO estan
 * incluidos (no puedo generar audio). Debes agregar tus propios .ogg en
 * src/main/resources/assets/vivids/sounds/ con estos nombres exactos,
 * o el juego usara un sonido silencioso/fallback sin romper el mod.
 */
public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VividsMod.MOD_ID);

    public static final RegistryObject<SoundEvent> VIVID_IDLE = register("vivid_idle");
    public static final RegistryObject<SoundEvent> VIVID_HURT = register("vivid_hurt");
    public static final RegistryObject<SoundEvent> VIVID_DEATH = register("vivid_death");
    public static final RegistryObject<SoundEvent> VIVID_GREET = register("vivid_greet");
    public static final RegistryObject<SoundEvent> RAY_SHOOT = register("ray_shoot");
    public static final RegistryObject<SoundEvent> RAY_OVERHEAT = register("ray_overheat");

    private static RegistryObject<SoundEvent> register(String name) {
        ResourceLocation id = new ResourceLocation(VividsMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
