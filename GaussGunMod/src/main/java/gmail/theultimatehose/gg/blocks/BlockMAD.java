package gmail.theultimatehose.gg.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gmail.theultimatehose.gg.Main;
import gmail.theultimatehose.gg.gui.GuiHandler;
import gmail.theultimatehose.gg.tile.TileEntityMAD;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMAD extends BlockContainerExt {

    private IIcon topIcon;
    private IIcon frontIcon;
    private IIcon sideIcon;
    private IIcon backIcon;
    private IIcon bottomIcon;

    public BlockMAD() {
        super(Material.iron);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeStone);
        this.setTickRandomly(true);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        int rotation = MathHelper.floor_double((double)(player.rotationYaw*4.0F/360.0F)+0.5D) & 3;
        if(rotation == 0) world.setBlockMetadataWithNotify(x, y, z, 0, 0); //north = front
        if(rotation == 1) world.setBlockMetadataWithNotify(x, y, z, 1, 3); //east = front
        if(rotation == 2) world.setBlockMetadataWithNotify(x, y, z, 2, 2); //south = front
        if(rotation == 3) world.setBlockMetadataWithNotify(x, y, z, 3, 3); //west = front
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if(side == 3) return this.frontIcon;
        if(side == 2) return this.backIcon;
        if(side == 1) return this.topIcon;
        if(side == 0) return this.bottomIcon;
        return this.sideIcon;
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side){
        int meta = world.getBlockMetadata(x, y, z);

        switch (meta) {
            case 0:
                if (side==2) return this.frontIcon;
                if (side==3) return this.backIcon;
                break;
            case 1:
                if (side==5) return this.frontIcon;
                if (side==4) return this.backIcon;
                break;
            case 2:
                if (side==3) return this.frontIcon;
                if (side==2) return this.backIcon;
                break;
            case 3:
                if (side==4) return this.frontIcon;
                if (side==5) return this.backIcon;
                break;
        }

        if(side == 1) return this.topIcon;
        if(side == 0) return this.bottomIcon;
        return this.sideIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconReg) {
        this.topIcon = iconReg.registerIcon("gg:madTop");
        this.sideIcon = Blocks.iron_block.getIcon(0,0);
        this.frontIcon = iconReg.registerIcon("gg:madFront");
        this.backIcon = iconReg.registerIcon("gg:madBack");
        this.bottomIcon = iconReg.registerIcon("gg:MachineBottom");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2) {
        return new TileEntityMAD();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            TileEntityMAD mad = (TileEntityMAD)world.getTileEntity(x,y,z);
            if (mad != null) player.openGui(Main.instance, GuiHandler.MAD_ID, world, x, y, z);
        }
        return true;
    }
}
