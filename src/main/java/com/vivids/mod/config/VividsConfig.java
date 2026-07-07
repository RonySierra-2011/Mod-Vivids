package com.vivids.mod.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Configuracion del mod (vivids-common.toml se genera automaticamente
 * en la carpeta config/ al ejecutar el juego).
 * ESTADO: FUNCIONAL.
 */
public class VividsConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue VILLAGE_SPAWN_MIN =
            BUILDER.comment("Minimo de Vivids por aldea").defineInRange("villageSpawnMin", 1, 0, 10);

    public static final ForgeConfigSpec.IntValue VILLAGE_SPAWN_MAX =
            BUILDER.comment("Maximo de Vivids por aldea").defineInRange("villageSpawnMax", 2, 0, 10);

    public static final ForgeConfigSpec.IntValue CAMP_MIN_HOUSES =
            BUILDER.comment("Casas minimas por Campamento Vivid").defineInRange("campMinHouses", 3, 1, 20);

    public static final ForgeConfigSpec.IntValue CAMP_MAX_HOUSES =
            BUILDER.comment("Casas maximas por Campamento Vivid").defineInRange("campMaxHouses", 10, 1, 20);

    public static final ForgeConfigSpec.IntValue RAY_MAX_SHOTS =
            BUILDER.comment("Disparos antes de sobrecalentar el Rayo Vivid").defineInRange("rayMaxShots", 6, 1, 50);

    public static final ForgeConfigSpec.IntValue RAY_OVERHEAT_SECONDS =
            BUILDER.comment("Segundos de espera tras sobrecalentar").defineInRange("rayOverheatSeconds", 20, 1, 120);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
