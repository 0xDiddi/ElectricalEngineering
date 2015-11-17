package gmail.theultimatehose.electricalengineering.block;

import gmail.theultimatehose.electricalengineering.ElectricalEngineering;
import gmail.theultimatehose.electricalengineering.gui.GuiHandler;
import gmail.theultimatehose.electricalengineering.item.ItemManager;
import gmail.theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPcbFrame extends BlockContainerExt {

    protected BlockPcbFrame() {
        super(Material.rock);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeStone);
        this.setTickRandomly(true);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        int rotation = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (rotation == 0) world.setBlockMetadataWithNotify(x, y, z, 0, 0); //north = front
        if (rotation == 1) world.setBlockMetadataWithNotify(x, y, z, 1, 3); //east = front
        if (rotation == 2) world.setBlockMetadataWithNotify(x, y, z, 2, 2); //south = front
        if (rotation == 3) world.setBlockMetadataWithNotify(x, y, z, 3, 3); //west = front
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par6, float par7, float par8) {
        if (!world.isRemote) {
            if (player.getHeldItem() == null) {
                if (player.isSneaking()) {
                    TileEntityPcbFrame tile = (TileEntityPcbFrame) world.getTileEntity(x, y, z);
                    if (tile.isRemoteModuleInstalled()) {
                        player.setCurrentItemOrArmor(0, new ItemStack(ItemManager.rcAntenna));
                        tile.setIsRemoteModuleInstalled(false);
                    } else if (tile.isRedstoneModuleInstalled()) {
                        player.setCurrentItemOrArmor(0, new ItemStack(ItemManager.rsAntenna));
                        tile.setIsRedstoneModuleInstalled(false);
                    } else if (tile.isControlModuleInstalled()) {
                        player.setCurrentItemOrArmor(0, new ItemStack(ItemManager.pcbControl));
                        tile.setIsControlModuleInstalled(false);
                    } else if (tile.isPowerModuleInstalled()) {
                        player.setCurrentItemOrArmor(0, new ItemStack(ItemManager.pcbVoltReg));
                        tile.setIsPowerModuleInstalled(false);
                    }
                } else {
                    TileEntityPcbFrame pcbFrame = (TileEntityPcbFrame) world.getTileEntity(x, y, z);
                    if (pcbFrame != null) player.openGui(ElectricalEngineering.instance, GuiHandler.PCB_FRAME_ID, world, x, y, z);
                }
            } else {
                ItemStack inHand = player.getCurrentEquippedItem();
                TileEntityPcbFrame tile = (TileEntityPcbFrame) world.getTileEntity(x, y, z);
                if (inHand.getItem() == ItemManager.pcbVoltReg && !tile.isPowerModuleInstalled()) {
                    tile.setIsPowerModuleInstalled(true);
                    inHand.stackSize--;
                    if (inHand.stackSize <= 0) inHand = null;
                    player.setCurrentItemOrArmor(0, inHand);
                } else if (inHand.getItem() == ItemManager.pcbControl && !tile.isControlModuleInstalled()) {
                    tile.setIsControlModuleInstalled(true);
                    inHand.stackSize--;
                    if (inHand.stackSize <= 0) inHand = null;
                    player.setCurrentItemOrArmor(0, inHand);
                } else if (inHand.getItem() == ItemManager.rsAntenna && !tile.isRedstoneModuleInstalled()) {
                    tile.setIsRedstoneModuleInstalled(true);
                    inHand.stackSize--;
                    if (inHand.stackSize <= 0) inHand = null;
                    player.setCurrentItemOrArmor(0, inHand);
                } else if (inHand.getItem() == ItemManager.rcAntenna && !tile.isRemoteModuleInstalled()) {
                    tile.setIsRemoteModuleInstalled(true);
                    inHand.stackSize--;
                    if (inHand.stackSize <= 0) inHand = null;
                    player.setCurrentItemOrArmor(0, inHand);
                } else {
                    TileEntityPcbFrame pcbFrame = (TileEntityPcbFrame) world.getTileEntity(x, y, z);
                    if (pcbFrame != null) player.openGui(ElectricalEngineering.instance, GuiHandler.PCB_FRAME_ID, world, x, y, z);
                }
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par1) {
        return new TileEntityPcbFrame();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

}
