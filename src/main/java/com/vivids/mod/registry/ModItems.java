package com.vivids.mod.registry;

import com.vivids.mod.VividsMod;
import com.vivids.mod.item.VividBackpackItem;
import com.vivids.mod.item.VividRayItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registro de todos los items del mod.
 * ESTADO: FUNCIONAL - todos estos items se pueden craftear y usar en el juego.
 */
public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VividsMod.MOD_ID);

    // ---- Comida de domesticacion ----
    public static final RegistryObject<Item> VIVID_FOOD = ITEMS.register("vivid_food",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(4).saturationMod(0.6f).build())));

    public static final RegistryObject<Item> SOAPY_FOAM = ITEMS.register("soapy_foam",
            () -> new Item(new Item.Properties()));

    // ---- Materiales ----
    public static final RegistryObject<Item> VIVID_FRAGMENT = ITEMS.register("vivid_fragment",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VIVID_CLOTH = ITEMS.register("vivid_cloth",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VIVID_BUBBLE = ITEMS.register("vivid_bubble",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VIVID_HEART = ITEMS.register("vivid_heart",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> VIVID_COIN = ITEMS.register("vivid_coin",
            () -> new Item(new Item.Properties()));

    // ---- Mochila ----
    public static final RegistryObject<Item> VIVID_BACKPACK = ITEMS.register("vivid_backpack",
            () -> new VividBackpackItem(new Item.Properties().stacksTo(1)));

    // ---- Armas de energia ----
    public static final RegistryObject<Item> VIVID_RAY = ITEMS.register("vivid_ray",
            () -> new VividRayItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE),
                    VividRayItem.RayType.OVERWORLD));

    public static final RegistryObject<Item> BATHROOM_RAY = ITEMS.register("bathroom_ray",
            () -> new VividRayItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE),
                    VividRayItem.RayType.BATHROOM));

    // ---- Objetos de portal ----
    public static final RegistryObject<Item> BATHROOM_PORTAL_FRAME = ITEMS.register("bathroom_portal_frame",
            () -> new net.minecraft.world.item.BlockItem(ModBlocks.BATHROOM_PORTAL_BLOCK.get(), new Item.Properties()));

    // ---- Spawn eggs (para pruebas/creativo) ----
    public static final RegistryObject<Item> VIVID_SPAWN_EGG = ITEMS.register("vivid_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.VIVID,
                    0x8ecae6, 0xffffff, new Item.Properties()));

    public static final RegistryObject<Item> VIVID_BATH_SPAWN_EGG = ITEMS.register("vivid_bath_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.VIVID_BATH,
                    0x9be564, 0xffffff, new Item.Properties()));

    // ---- Spawn eggs de los 4 jefes (para pruebas en creativo, ya que su
    // aparicion natural es extremadamente rara por diseño) ----
    public static final RegistryObject<Item> REY_VIVID_SPAWN_EGG = ITEMS.register("rey_vivid_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.REY_VIVID,
                    0xffd700, 0x8ecae6, new Item.Properties()));

    public static final RegistryObject<Item> GUARDIAN_VIVID_SPAWN_EGG = ITEMS.register("guardian_vivid_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.GUARDIAN_VIVID,
                    0x4b6f44, 0x8ecae6, new Item.Properties()));

    public static final RegistryObject<Item> DUQUE_JABONOSO_SPAWN_EGG = ITEMS.register("duque_jabonoso_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.DUQUE_JABONOSO,
                    0x4fa8e0, 0xffffff, new Item.Properties()));

    public static final RegistryObject<Item> LIMPIADOR_SUPREMO_SPAWN_EGG = ITEMS.register("limpiador_supremo_spawn_egg",
            () -> new net.minecraftforge.common.ForgeSpawnEggItem(ModEntities.LIMPIADOR_SUPREMO,
                    0xffffff, 0x4fa8e0, new Item.Properties()));
}
