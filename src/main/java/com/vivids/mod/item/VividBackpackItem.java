package com.vivids.mod.item;

import com.vivids.mod.menu.VividBackpackMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Mochila Vivid.
 * ESTADO: FUNCIONAL. Al usarla, abre VividBackpackMenu con 9 slots
 * extra de inventario, persistentes en el NBT del propio item (ver
 * VividBackpackMenu.java).
 */
public class VividBackpackItem extends Item {

    public VividBackpackItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide && player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
            serverPlayer.openMenu(new SimpleMenuProvider(
                    (windowId, inv, p) -> new VividBackpackMenu(windowId, inv, stack),
                    Component.translatable("item.vivids.vivid_backpack")
            ));
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
EOF

