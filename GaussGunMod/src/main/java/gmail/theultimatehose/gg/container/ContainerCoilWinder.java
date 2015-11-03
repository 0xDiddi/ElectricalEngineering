package gmail.theultimatehose.gg.container;

import gmail.theultimatehose.gg.Main;
import gmail.theultimatehose.gg.tile.TileEntityCoilWinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerCoilWinder extends Container {

    private TileEntityCoilWinder tileCoilWinder;

    public ContainerCoilWinder(InventoryPlayer inventory, TileEntityCoilWinder tile) {
        this.tileCoilWinder = tile;

        this.addSlotToContainer(new Slot(this.tileCoilWinder, TileEntityCoilWinder.SLOT_WIRE_IN, 12, 8));
        this.addSlotToContainer(new Slot(this.tileCoilWinder, TileEntityCoilWinder.SLOT_COIL_IN, 75, 10));
        this.addSlotToContainer(new SlotOutput(this.tileCoilWinder, TileEntityCoilWinder.SLOT_COIL_OUT, 75, 60));

        //Player inventory slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileCoilWinder.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
        ItemStack stack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack()) {

            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (slotNum < 4) {
                //From Machine to Inventory

                if (!this.mergeItemStack(stack1, 4, this.inventorySlots.size(), true)) {
                    return null;
                }
                slot.onSlotChange(stack1, stack);
            } else {
                //From Inventory to Machine
                Item item = stack.getItem();
                if (item == Main.wireCopper) {
                    if (!this.mergeItemStack(stack1, 0, 1, false)) {
                        return null;
                    }
                } else if (item == Main.coil) {
                    if (!this.mergeItemStack(stack1, 1, 2, false)) {
                        return null;
                    }
                } else {
                    return null;
                }
            }

            if (stack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (stack1.stackSize == stack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, stack1);
        }

        return stack;
    }


}
