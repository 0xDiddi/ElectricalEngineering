package gmail.theultimatehose.electricalengineering.block;

import gmail.theultimatehose.electricalengineering.config.values.CfgFloatValues;
import gmail.theultimatehose.electricalengineering.item.ItemManager;
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

        list.add(new ItemStack(ItemManager.pcbLVBurnt, amountLV));
        list.add(new ItemStack(ItemManager.pcbHVBurnt, amountHV));

        return list;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconReg) {
        this.topIcon = iconReg.registerIcon("electricalengineering:burntMachineTop");
        this.sideIcon = iconReg.registerIcon("electricalengineering:burntMachineSide");
        this.bottomIcon = iconReg.registerIcon("electricalengineering:MachineBottom");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) return this.topIcon;
        if (side == 0) return this.bottomIcon;
        return this.sideIcon;
    }
}