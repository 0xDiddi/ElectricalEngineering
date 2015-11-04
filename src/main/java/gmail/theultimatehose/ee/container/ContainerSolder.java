package gmail.theultimatehose.ee.container;

import gmail.theultimatehose.ee.Main;
import gmail.theultimatehose.ee.recipe.SolderRecipe;
import gmail.theultimatehose.ee.tile.TileEntitySolder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerSolder extends Container {

    private TileEntitySolder tileSolder;

    public ContainerSolder(InventoryPlayer inventory, TileEntitySolder solder) {
        this.tileSolder = solder;

        //Desolder slots
        this.addSlotToContainer(new Slot(this.tileSolder, TileEntitySolder.SLOT_PCB_IN, 13, 8));
        this.addSlotToContainer(new SlotOutput(this.tileSolder, TileEntitySolder.SLOT_PCB_OUT, 13, 58));

        //Solder Crafting slots
        this.addSlotToContainer(new SlotOutput(this.tileSolder, TileEntitySolder.SLOT_CRAFT_RES, 139, 15));

        int index = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                this.addSlotToContainer(new Slot(this.tileSolder, index, 51 + x * 18, 17 + y * 18) {
                    @Override
                    public void onSlotChanged() {
                        if (this.inventory instanceof TileEntitySolder) {
                            TileEntitySolder solder = (TileEntitySolder) this.inventory;
                            ItemStack[] matrix = new ItemStack[]{solder.slots[0], solder.slots[1], solder.slots[2],
                                    solder.slots[3], solder.slots[4], solder.slots[5],
                                    solder.slots[6], solder.slots[7], solder.slots[8]};
                            SolderRecipe sr = SolderRecipe.checkMatch(matrix);
                            if (sr != null) {
                                tileSolder.startSolder(sr);
                            }
                        }
                        super.onSlotChanged();
                    }
                });
                index++;
            }
        }

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
        return this.tileSolder.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
        ItemStack stack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack()) {

            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if (slotNum < 12) {
                //From Machine to Inventory

                if (!this.mergeItemStack(stack1, 13, this.inventorySlots.size(), true)) {
                    return null;
                }
                slot.onSlotChange(stack1, stack);
            } else {
                //From Inventory to Machine
                Item item = stack.getItem();
                if (item == Main.pcbLVBurnt || item == Main.pcbHVBurnt) {
                    if (!this.mergeItemStack(stack1, 0, 1, false)) {
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
