package gmail.theultimatehose.gg.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gmail.theultimatehose.gg.Main;
import gmail.theultimatehose.gg.gui.GuiHandler;
import gmail.theultimatehose.gg.tile.TileEntityCoilWinder;
import gmail.theultimatehose.gg.tile.TileEntitySolder;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockWinder extends BlockContainerExt {

    private IIcon topIcon;
    private IIcon onIcon;
    private IIcon bottomIcon;

    public BlockWinder() {
        super(Material.rock);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeStone);
        this.setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) { return new TileEntityCoilWinder(); }

    @Override
    public IIcon getIcon(int side, int meta){
        if(side == 1 && meta != 1) return this.topIcon;
        if(side == 0) return this.bottomIcon;
        return this.onIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconReg) {
        this.topIcon = iconReg.registerIcon("gg:coilWinderTop");
        this.onIcon = iconReg.registerIcon("gg:coilWinderSide");
        this.bottomIcon = iconReg.registerIcon("gg:MachineBottom");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            TileEntityCoilWinder winder = (TileEntityCoilWinder)world.getTileEntity(x, y, z);
            if (winder != null) player.openGui(Main.instance, GuiHandler.WINDER_ID, world, x, y, z);
        }
        return true;
    }

    @Override
    public void dropInventory(World world, int x, int y, int z) {
        super.dropInventory(world, x, y, z);
        TileEntityCoilWinder tile = (TileEntityCoilWinder)world.getTileEntity(x, y, z);
        int wire = tile.currCoilAmount;
        if (wire > 0) {
            while (wire > 0) {
                if (wire > 64) {
                    ItemStack is = new ItemStack(Main.wireCopper, 64);
                    this.dropStack(is, world, x, y, z);
                    wire -= 64;
                } else {
                    ItemStack is = new ItemStack(Main.wireCopper, wire);
                    this.dropStack(is, world, x, y, z);
                    wire -= wire;
                }
            }
        }
    }
}
