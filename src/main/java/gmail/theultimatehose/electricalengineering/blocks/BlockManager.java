package gmail.theultimatehose.electricalengineering.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import gmail.theultimatehose.electricalengineering.Names;
import gmail.theultimatehose.electricalengineering.Util;
import gmail.theultimatehose.electricalengineering.creativetab.CreativeTabGeneral;
import net.minecraftforge.oredict.OreDictionary;

public class BlockManager {

    public static BlockSolder machineSolder;
    public static BlockMAD machineMAD;
    public static BlockWinder machineWinder;

    public static BlockBurntMachine machineBurnt;

    public static void initBlocks() {
        machineSolder = (BlockSolder) new BlockSolder().setBlockName(Util.MOD_ID + "_"  + Names.MACHINE_SOLDER).setCreativeTab(CreativeTabGeneral.instance);
        machineMAD = (BlockMAD) new BlockMAD().setBlockName(Util.MOD_ID + "_"  + Names.MACHINE_MAD).setCreativeTab(CreativeTabGeneral.instance);
        machineWinder = (BlockWinder) new BlockWinder().setBlockName(Util.MOD_ID + "_"  + Names.MACHINE_WINDER).setCreativeTab(CreativeTabGeneral.instance);

        machineBurnt = (BlockBurntMachine) new BlockBurntMachine().setBlockName(Util.MOD_ID + "_"  + Names.MACHINE_BURNT).setCreativeTab(CreativeTabGeneral.instance);
    }

    public static void registerBlocksToGR() {
        GameRegistry.registerBlock(machineSolder, Names.MACHINE_SOLDER);
        GameRegistry.registerBlock(machineMAD, Names.MACHINE_MAD);
        GameRegistry.registerBlock(machineWinder, Names.MACHINE_WINDER);

        GameRegistry.registerBlock(machineBurnt, Names.MACHINE_BURNT);
    }

    public static void registerBlocksToOreDict() {
        OreDictionary.registerOre("machineSolder", machineSolder);
        OreDictionary.registerOre("machineMAD", machineMAD);
        OreDictionary.registerOre("machineWinder", machineWinder);

        OreDictionary.registerOre("blockBurntMachine", machineBurnt);

    }

}
