package com.vivids.mod.registry;

import com.vivids.mod.VividsMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** Pestaña de creativo dedicada al mod. ESTADO: FUNCIONAL. */
public class ModCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(ForgeRegistries.CREATIVE_MODE_TABS, VividsMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> VIVIDS_TAB = CREATIVE_MODE_TABS.register("vivids_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.vivids"))
                    .icon(() -> new ItemStack(ModItems.VIVID_HEART.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.VIVID_FOOD.get());
                        output.accept(ModItems.SOAPY_FOAM.get());
                        output.accept(ModItems.VIVID_FRAGMENT.get());
                        output.accept(ModItems.VIVID_CLOTH.get());
                        output.accept(ModItems.VIVID_BUBBLE.get());
                        output.accept(ModItems.VIVID_HEART.get());
                        output.accept(ModItems.VIVID_COIN.get());
                        output.accept(ModItems.VIVID_BACKPACK.get());
                        output.accept(ModItems.VIVID_RAY.get());
                        output.accept(ModItems.BATHROOM_RAY.get());
                        output.accept(ModItems.BATHROOM_PORTAL_FRAME.get());
                        output.accept(ModItems.VIVID_SPAWN_EGG.get());
                        output.accept(ModItems.VIVID_BATH_SPAWN_EGG.get());
                        output.accept(ModItems.REY_VIVID_SPAWN_EGG.get());
                        output.accept(ModItems.GUARDIAN_VIVID_SPAWN_EGG.get());
                        output.accept(ModItems.DUQUE_JABONOSO_SPAWN_EGG.get());
                        output.accept(ModItems.LIMPIADOR_SUPREMO_SPAWN_EGG.get());
                    })
                    .build());
}
