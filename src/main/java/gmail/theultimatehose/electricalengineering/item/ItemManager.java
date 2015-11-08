package gmail.theultimatehose.electricalengineering.item;

import cpw.mods.fml.common.registry.GameRegistry;
import gmail.theultimatehose.electricalengineering.Names;
import gmail.theultimatehose.electricalengineering.Util;
import gmail.theultimatehose.electricalengineering.creativetab.CreativeTabGeneral;
import gmail.theultimatehose.electricalengineering.creativetab.CreativeTabWeapons;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ItemManager {

    public static Item wireCopper;
    public static Item tubeIron;
    public static Item coil;
    public static Item pcbLVBurnt;
    public static Item pcbHVBurnt;
    public static Item pcbScrapLV;
    public static Item pcbScrapHV;
    public static Item hardPaperRaw;
    public static Item hardPaper;
    public static Item bucketAcid;
    public static Item bolt;

    public static Item pcbUnetched;
    public static Item pcbEtched;
    public static Item pcbControl;
    public static Item pcbVoltReg;

    public static Item capacitorLV;
    public static Item capacitorHV;
    public static Item transistor;
    public static Item resistor;
    public static Item transformer;

    public static Item gaussGun;

    public static void initItems() {
        wireCopper = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.WIRE_COPPER).setTextureName("electricalengineering:copperWire").setCreativeTab(CreativeTabGeneral.instance);
        tubeIron = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.TUBE_IRON).setTextureName("electricalengineering:tubeIron").setCreativeTab(CreativeTabGeneral.instance);
        coil = new Item().setUnlocalizedName(Util.MOD_ID + "_"  + Names.COIL).setMaxStackSize(1).setMaxDamage(2049).setTextureName("electricalengineering:coil").setCreativeTab(CreativeTabGeneral.instance).setNoRepair();
        pcbLVBurnt = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.PCB_LV_BURNT).setMaxStackSize(32).setTextureName("electricalengineering:pcbNormalBurnt").setCreativeTab(CreativeTabGeneral.instance);
        pcbHVBurnt = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.PCB_HV_BURNT).setMaxStackSize(32).setTextureName("electricalengineering:pcbHVBurnt").setCreativeTab(CreativeTabGeneral.instance);
        pcbScrapLV = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.PCB_SCRAP_LV).setTextureName("electricalengineering:pcbScrapLV").setCreativeTab(CreativeTabGeneral.instance);
        pcbScrapHV = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.PCB_SCRAP_HV).setTextureName("electricalengineering:pcbScrapHV").setCreativeTab(CreativeTabGeneral.instance);
        hardPaperRaw = new Item().setUnlocalizedName(Util.MOD_ID + "_"  + Names.HARDPAPER_RAW).setTextureName("electricalengineering:hardPaper_raw").setCreativeTab(CreativeTabGeneral.instance);
        hardPaper = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.HARDPAPER).setTextureName("electricalengineering:hardPaper").setCreativeTab(CreativeTabGeneral.instance);
        bucketAcid = new Item().setUnlocalizedName(Util.MOD_ID + "_"  + Names.BUCKET_ACID).setMaxStackSize(1).setTextureName("electricalengineering:bucket_acid").setCreativeTab(CreativeTabGeneral.instance);
        pcbUnetched = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.PCB_UNETCHED).setTextureName("electricalengineering:pcbUnetched").setCreativeTab(CreativeTabGeneral.instance);
        pcbEtched = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.PCB_ETCHED).setTextureName("electricalengineering:pcbEtched").setCreativeTab(CreativeTabGeneral.instance);
        pcbControl = new Item().setUnlocalizedName(Util.MOD_ID + "_"  + Names.PCB_CONTROL).setTextureName("electricalengineering:pcbControl").setCreativeTab(CreativeTabGeneral.instance);
        pcbVoltReg = new Item().setUnlocalizedName(Util.MOD_ID + "_"  + Names.PCB_VOLT_REG).setTextureName("electricalengineering:pcbVoltReg").setCreativeTab(CreativeTabGeneral.instance);
        bolt = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.BOLT).setTextureName("electricalengineering:bolt").setCreativeTab(CreativeTabWeapons.instance);

        capacitorLV = new Item().setUnlocalizedName(Util.MOD_ID + "_"  + Names.CAPACITOR_LV).setTextureName("electricalengineering:capacitor_LV").setCreativeTab(CreativeTabGeneral.instance);
        capacitorHV = new Item().setUnlocalizedName(Util.MOD_ID + "_"  + Names.CAPACITOR_HV).setTextureName("electricalengineering:capacitor_HV").setCreativeTab(CreativeTabGeneral.instance);
        transistor = new Item().setUnlocalizedName(Util.MOD_ID + "_"  + Names.TRANSISTOR).setTextureName("electricalengineering:transistor").setCreativeTab(CreativeTabGeneral.instance);
        resistor = new Item().setUnlocalizedName(Util.MOD_ID + "_"  + Names.RESISTOR).setTextureName("electricalengineering:resistor").setCreativeTab(CreativeTabGeneral.instance);
        transformer = new Item().setUnlocalizedName(Util.MOD_ID + "_" + Names.TRANSFORMER).setTextureName("electricalengineering:transformer").setCreativeTab(CreativeTabGeneral.instance);

        gaussGun = new ItemGaussGun().setUnlocalizedName(Util.MOD_ID + "_"  + Names.GAUSS_GUN).setFull3D().setTextureName("electricalengineering:gaussGun").setCreativeTab(CreativeTabWeapons.instance);
    }

    public static void registerItemsToGR() {
        GameRegistry.registerItem(wireCopper, Names.WIRE_COPPER);
        GameRegistry.registerItem(tubeIron, Names.TUBE_IRON);
        GameRegistry.registerItem(coil, Names.COIL);
        GameRegistry.registerItem(pcbLVBurnt, Names.PCB_LV_BURNT);
        GameRegistry.registerItem(pcbHVBurnt, Names.PCB_HV_BURNT);
        GameRegistry.registerItem(pcbScrapLV, Names.PCB_SCRAP_LV);
        GameRegistry.registerItem(pcbScrapHV, Names.PCB_SCRAP_HV);
        GameRegistry.registerItem(hardPaperRaw, Names.HARDPAPER_RAW);
        GameRegistry.registerItem(hardPaper, Names.HARDPAPER);
        GameRegistry.registerItem(bucketAcid, Names.BUCKET_ACID);
        GameRegistry.registerItem(pcbUnetched, Names.PCB_UNETCHED);
        GameRegistry.registerItem(pcbEtched, Names.PCB_ETCHED);
        GameRegistry.registerItem(pcbControl, Names.PCB_CONTROL);
        GameRegistry.registerItem(pcbVoltReg, Names.PCB_VOLT_REG);
        GameRegistry.registerItem(bolt, Names.BOLT);

        GameRegistry.registerItem(capacitorLV, Names.CAPACITOR_LV);
        GameRegistry.registerItem(capacitorHV, Names.CAPACITOR_HV);
        GameRegistry.registerItem(transistor, Names.TRANSISTOR);
        GameRegistry.registerItem(resistor, Names.RESISTOR);
        GameRegistry.registerItem(transformer, Names.TRANSFORMER);

        GameRegistry.registerItem(gaussGun, Names.GAUSS_GUN);
    }

    public static void registerItemsToOreDict() {
        OreDictionary.registerOre("wireCopper", wireCopper);
        OreDictionary.registerOre("tubeIron", tubeIron);
        OreDictionary.registerOre("coil", coil);
        OreDictionary.registerOre("pcbLVBurnt", pcbLVBurnt);
        OreDictionary.registerOre("pcbHVBurnt", pcbHVBurnt);
        OreDictionary.registerOre("pcbScrapLV", pcbScrapLV);
        OreDictionary.registerOre("pcbScrapHV", pcbScrapHV);
        OreDictionary.registerOre("hardPaperRaw", hardPaperRaw);
        OreDictionary.registerOre("hardPaper", hardPaper);
        OreDictionary.registerOre("bucketAcid", bucketAcid);
        OreDictionary.registerOre("pcbUnetched", pcbUnetched);
        OreDictionary.registerOre("pcbEtched", pcbEtched);
        OreDictionary.registerOre("pcbControl", pcbControl);
        OreDictionary.registerOre("pcbVoltReg", pcbVoltReg);
        OreDictionary.registerOre("bolt", bolt);

        OreDictionary.registerOre("capacitorLV", capacitorLV);
        OreDictionary.registerOre("capacitorHV", capacitorHV);
        OreDictionary.registerOre("transistor", transistor);
        OreDictionary.registerOre("resistor", resistor);
        OreDictionary.registerOre("transformer", transformer);

        OreDictionary.registerOre("gaussGun", gaussGun);
    }

}
