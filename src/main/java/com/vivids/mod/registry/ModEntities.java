package com.vivids.mod.registry;

import com.vivids.mod.VividsMod;
import com.vivids.mod.entity.VividBathEntity;
import com.vivids.mod.entity.VividEntity;
import com.vivids.mod.entity.VividitoEntity;
import com.vivids.mod.entity.boss.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registro de tipos de entidad.
 * ESTADO: FUNCIONAL para VIVID, VIVID_BATH, VIVITO y los 4 jefes.
 */
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VividsMod.MOD_ID);

    public static final RegistryObject<EntityType<VividEntity>> VIVID = ENTITY_TYPES.register("vivid",
            () -> EntityType.Builder.of(VividEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.2f)
                    .clientTrackingRange(10)
                    .build("vivid"));

    public static final RegistryObject<EntityType<VividBathEntity>> VIVID_BATH = ENTITY_TYPES.register("vivid_bath",
            () -> EntityType.Builder.of(VividBathEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.2f)
                    .clientTrackingRange(10)
                    .build("vivid_bath"));

    public static final RegistryObject<EntityType<VividitoEntity>> VIVITO = ENTITY_TYPES.register("vivito",
            () -> EntityType.Builder.of(VividitoEntity::new, MobCategory.CREATURE)
                    .sized(0.35f, 0.7f)
                    .clientTrackingRange(10)
                    .build("vivito"));

    public static final RegistryObject<EntityType<ReyVividEntity>> REY_VIVID = ENTITY_TYPES.register("rey_vivid",
            () -> EntityType.Builder.of(ReyVividEntity::new, MobCategory.CREATURE)
                    .sized(1.2f, 2.4f)
                    .clientTrackingRange(16)
                    .fireImmune()
                    .build("rey_vivid"));

    public static final RegistryObject<EntityType<GuardianVividEntity>> GUARDIAN_VIVID = ENTITY_TYPES.register("guardian_vivid",
            () -> EntityType.Builder.of(GuardianVividEntity::new, MobCategory.CREATURE)
                    .sized(1.2f, 2.4f)
                    .clientTrackingRange(16)
                    .build("guardian_vivid"));

    public static final RegistryObject<EntityType<DuqueJabonosoEntity>> DUQUE_JABONOSO = ENTITY_TYPES.register("duque_jabonoso",
            () -> EntityType.Builder.of(DuqueJabonosoEntity::new, MobCategory.CREATURE)
                    .sized(1.2f, 2.4f)
                    .clientTrackingRange(16)
                    .build("duque_jabonoso"));

    public static final RegistryObject<EntityType<LimpiadorSupremoEntity>> LIMPIADOR_SUPREMO = ENTITY_TYPES.register("limpiador_supremo",
            () -> EntityType.Builder.of(LimpiadorSupremoEntity::new, MobCategory.CREATURE)
                    .sized(1.2f, 2.4f)
                    .clientTrackingRange(16)
                    .fireImmune()
                    .build("limpiador_supremo"));
}
