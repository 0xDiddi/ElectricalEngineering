package gmail.theultimatehose.ee.blocks;

import gmail.theultimatehose.ee.Main;
import gmail.theultimatehose.ee.config.values.CfgFloatValues;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class BlockBurntMachine extends Block {

    private IIcon topIcon;
    private IIcon sideIcon;
    private IIcon bottomIcon;

    public BlockBurntMachine() {
        super(Material.iron);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(1.0F);
        this.setStepSound(soundTypeMetal);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>(2);
        Random rand = new Random();
        float chance = CfgFloatValues.OLD_MACHINE_DROP_CHANCE.getValue();
        int chanceLV = Math.round(chance * 5);
        int chanceHV = Math.round(chance * 9);
        int amountLV = Math.max(0, (rand.nextInt(7) - chanceLV) + fortune);
        int amountHV = Math.max(0, (rand.nextInt(11) - chanceHV) + fortune);

        list.add(new ItemStack(Main.pcbLVBurnt, amountLV));
        list.add(new ItemStack(Main.pcbHVBurnt, amountHV));

        return list;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconReg) {
        this.topIcon = iconReg.registerIcon("ee:burntMachineTop");
        this.sideIcon = iconReg.registerIcon("ee:burntMachineSide");
        this.bottomIcon = iconReg.registerIcon("ee:MachineBottom");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) return this.topIcon;
        if (side == 0) return this.bottomIcon;
        return this.sideIcon;
    }
}