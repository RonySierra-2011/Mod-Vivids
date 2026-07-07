package com.vivids.mod.registry;

import com.vivids.mod.VividsMod;
import com.vivids.mod.menu.VividBackpackMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** Registro de tipos de menu (GUIs). ESTADO: FUNCIONAL. */
public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, VividsMod.MOD_ID);

    public static final RegistryObject<MenuType<VividBackpackMenu>> VIVID_BACKPACK_MENU =
            MENU_TYPES.register("vivid_backpack",
                    () -> IForgeMenuType.create((windowId, inv, data) ->
                            new VividBackpackMenu(windowId, inv, inv.player.getMainHandItem())));
}
