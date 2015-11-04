package gmail.theultimatehose.ee.container;

import gmail.theultimatehose.ee.Main;
import gmail.theultimatehose.ee.tile.TileEntityMAD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerMAD extends Container {

    private TileEntityMAD tileMAD;

    public ContainerMAD(InventoryPlayer inventory, TileEntityMAD tile) {
        this.tileMAD = tile;

        //Sorting slots
        this.addSlotToContainer(new Slot(this.tileMAD, TileEntityMAD.SLOT_SCRAB_IN, 10, 6));
        this.addSlotToContainer(new SlotOutput(this.tileMAD, TileEntityMAD.SLOT_CAPACITOR_LV, 36, 6));
        this.addSlotToContainer(new SlotOutput(this.tileMAD, TileEntityMAD.SLOT_TRANSISTOR, 36, 24));
        this.addSlotToContainer(new SlotOutput(this.tileMAD, TileEntityMAD.SLOT_RESISTOR, 36, 42));
        this.addSlotToContainer(new SlotOutput(this.tileMAD, TileEntityMAD.SLOT_CAPACITOR_HV, 54, 6));
        this.addSlotToContainer(new SlotOutput(this.tileMAD, TileEntityMAD.SLOT_TRANSFORMER, 54, 24));

        //Etching slots
        this.addSlotToContainer(new Slot(this.tileMAD, TileEntityMAD.SLOT_CB_IN, 84, 5));
        this.addSlotToContainer(new SlotOutput(this.tileMAD, TileEntityMAD.SLOT_PCB_OUT, 84, 55));
        this.addSlotToContainer(new Slot(this.tileMAD, TileEntityMAD.SLOT_ACID_IN, 132, 6));
        this.addSlotToContainer(new SlotOutput(this.tileMAD, TileEntityMAD.SLOT_BUCKET_OUT, 132, 40));

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
    public boolean canInteractWith(EntityPlayer plr) {
        return tileMAD.isUseableByPlayer(plr);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
        ItemStack stack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack()) {

            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (slotNum < 11) {
                //From Machine to Inventory

                if (!this.mergeItemStack(stack1, 11, this.inventorySlots.size(), true)) {
                    return null;
                }
                slot.onSlotChange(stack1, stack);
            } else {
                //From Inventory to Machine
                Item item = stack.getItem();
                if (item == Main.pcbScrapLV || item == Main.pcbScrapHV) {
                    if (!this.mergeItemStack(stack1, 0, 1, false)) {
                        return null;
                    }
                } else if (item == Main.pcbUnetched) {
                    if (!this.mergeItemStack(stack1, 6, 7, false)) {
                        return null;
                    }
                } else if (item == Main.bucketAcid) {
                    if (!this.mergeItemStack(stack1, 8, 9, false)) {
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
