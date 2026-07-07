package com.vivids.mod.menu;

import com.vivids.mod.registry.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/**
 * Menu real de la Mochila Vivid.
 * ESTADO: FUNCIONAL - 9 slots extra de inventario persistente, guardado
 * directamente en el NBT del ItemStack de la mochila (sobrevive a
 * cerrar el juego, tirar el item al piso, etc).
 */
public class VividBackpackMenu extends AbstractContainerMenu {

    private static final int BACKPACK_SLOTS = 9;
    public final Container backpackInventory;
    private final ItemStack backpackStack;

    public VividBackpackMenu(int windowId, Inventory playerInv, ItemStack backpackStack) {
        super(ModMenus.VIVID_BACKPACK_MENU.get(), windowId);
        this.backpackStack = backpackStack;
        this.backpackInventory = createContainerFromNbt(backpackStack);

        // Slots de la mochila (3x3)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int index = col + row * 3;
                this.addSlot(new Slot(backpackInventory, index, 8 + col * 18, 18 + row * 18));
            }
        }

        // Inventario del jugador
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    private static Container createContainerFromNbt(ItemStack stack) {
        SimpleContainer container = new SimpleContainer(BACKPACK_SLOTS) {
            @Override
            public void setChanged() {
                super.setChanged();
                saveToStack(this, stack);
            }
        };
        if (stack.hasTag() && stack.getTag().contains("BackpackItems")) {
            net.minecraft.nbt.ListTag list = stack.getTag().getList("BackpackItems", 10);
            for (int i = 0; i < list.size(); i++) {
                net.minecraft.nbt.CompoundTag itemTag = list.getCompound(i);
                int slot = itemTag.getByte("Slot") & 255;
                if (slot < BACKPACK_SLOTS) {
                    container.setItem(slot, ItemStack.of(itemTag.getCompound("Item")));
                }
            }
        }
        return container;
    }

    private static void saveToStack(Container container, ItemStack stack) {
        net.minecraft.nbt.ListTag list = new net.minecraft.nbt.ListTag();
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack s = container.getItem(i);
            if (!s.isEmpty()) {
                net.minecraft.nbt.CompoundTag itemTag = new net.minecraft.nbt.CompoundTag();
                itemTag.putByte("Slot", (byte) i);
                itemTag.put("Item", s.save(new net.minecraft.nbt.CompoundTag()));
                list.add(itemTag);
            }
        }
        stack.getOrCreateTag().put("BackpackItems", list);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;
        ItemStack original = slot.getItem();
        ItemStack copy = original.copy();

        if (index < BACKPACK_SLOTS) {
            if (!this.moveItemStackTo(original, BACKPACK_SLOTS, this.slots.size(), true)) return ItemStack.EMPTY;
        } else {
            if (!this.moveItemStackTo(original, 0, BACKPACK_SLOTS, false)) return ItemStack.EMPTY;
        }

        if (original.isEmpty()) slot.set(ItemStack.EMPTY); else slot.setChanged();
        return copy;
    }
}
