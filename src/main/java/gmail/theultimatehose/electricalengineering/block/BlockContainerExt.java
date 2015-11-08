package gmail.theultimatehose.electricalengineering.block;

import gmail.theultimatehose.electricalengineering.tile.TileEntityInventoryBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public abstract class BlockContainerExt extends BlockContainer {

    protected BlockContainerExt(Material material) {
        super(material);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        this.dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, par6);
    }

    public void dropInventory(World world, int x, int y, int z) {
        if (!world.isRemote) {
            TileEntity aTile = world.getTileEntity(x, y, z);
            if (aTile instanceof TileEntityInventoryBase) {
                TileEntityInventoryBase tile = (TileEntityInventoryBase) aTile;
                if (tile.getSizeInventory() > 0) {
                    for (int i = 0; i < tile.getSizeInventory(); i++) {
                        this.dropSlotFromInventory(i, tile, world, x, y, z);
                    }
                }
            }
        }
    }

    public void dropSlotFromInventory(int i, TileEntityInventoryBase tile, World world, int x, int y, int z) {
        Random rand = new Random();
        ItemStack stack = tile.getStackInSlot(i);
        if (stack != null && stack.stackSize > 0) {
            float dX = rand.nextFloat() * 0.8F + 0.1F;
            float dY = rand.nextFloat() * 0.8F + 0.1F;
            float dZ = rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, stack.copy());
            if (stack.hasTagCompound())
                entityItem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
            float factor = 0.05F;
            entityItem.motionX = rand.nextGaussian() * factor;
            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
            entityItem.motionZ = rand.nextGaussian() * factor;
            world.spawnEntityInWorld(entityItem);
        }
        tile.setInventorySlotContents(i, null);
    }

    public void dropStack(ItemStack stack, World world, int x, int y, int z) {
        Random rand = new Random();
        if (stack != null && stack.stackSize > 0) {
            float dX = rand.nextFloat() * 0.8F + 0.1F;
            float dY = rand.nextFloat() * 0.8F + 0.1F;
            float dZ = rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, stack.copy());
            if (stack.hasTagCompound())
                entityItem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
            float factor = 0.05F;
            entityItem.motionX = rand.nextGaussian() * factor;
            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
            entityItem.motionZ = rand.nextGaussian() * factor;
            world.spawnEntityInWorld(entityItem);
        }
    }

}
