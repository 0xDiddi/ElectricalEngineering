package theultimatehose.electricalengineering.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import theultimatehose.electricalengineering.ElectricalEngineering;
import theultimatehose.electricalengineering.gui.GuiHandler;
import theultimatehose.electricalengineering.tile.TileEntitySolder;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSolder extends BlockContainerExt {

    private IIcon topIcon;
    private IIcon onIcon;
    private IIcon bottomIcon;

    public BlockSolder() {
        super(Material.rock);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeStone);
        this.setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2) {
        return new TileEntitySolder();
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1 && meta != 1) return this.topIcon;
        if (side == 0) return this.bottomIcon;
        return this.onIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconReg) {
        this.topIcon = iconReg.registerIcon("electricalengineering:solderTop");
        this.onIcon = iconReg.registerIcon("electricalengineering:solderSide");
        this.bottomIcon = iconReg.registerIcon("electricalengineering:MachineBottom");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            TileEntitySolder solder = (TileEntitySolder) world.getTileEntity(x, y, z);
            if (solder != null) player.openGui(ElectricalEngineering.instance, GuiHandler.SOLDER_ID, world, x, y, z);
        }
        return true;
    }

}
