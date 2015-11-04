package gmail.theultimatehose.electricalengineering;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import gmail.theultimatehose.electricalengineering.blocks.BlockBurntMachine;
import gmail.theultimatehose.electricalengineering.blocks.BlockMAD;
import gmail.theultimatehose.electricalengineering.blocks.BlockSolder;
import gmail.theultimatehose.electricalengineering.blocks.BlockWinder;
import gmail.theultimatehose.electricalengineering.config.CfgHandler;
import gmail.theultimatehose.electricalengineering.creativetab.CreativeTabGeneral;
import gmail.theultimatehose.electricalengineering.creativetab.CreativeTabWeapons;
import gmail.theultimatehose.electricalengineering.entity.EntityBolt;
import gmail.theultimatehose.electricalengineering.event.CraftEvent;
import gmail.theultimatehose.electricalengineering.event.WorldDecorationEvent;
import gmail.theultimatehose.electricalengineering.gui.GuiHandler;
import gmail.theultimatehose.electricalengineering.item.ItemGaussGun;
import gmail.theultimatehose.electricalengineering.network.PacketHandler;
import gmail.theultimatehose.electricalengineering.proxy.CommonProxy;
import gmail.theultimatehose.electricalengineering.recipe.SolderRecipe;
import gmail.theultimatehose.electricalengineering.tile.TileEntityCoilWinder;
import gmail.theultimatehose.electricalengineering.tile.TileEntityMAD;
import gmail.theultimatehose.electricalengineering.tile.TileEntitySolder;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = Util.MOD_ID, name = "Electrical Engineering", version = Util.VERSION)
public class ElectricalEngineering {

    @Instance(Util.MOD_ID)
    public static ElectricalEngineering instance;

    @SidedProxy(clientSide = "gmail.theultimatehose.electricalengineering.proxy.ClientProxy", serverSide = "gmail.theultimatehose.electricalengineering.proxy.CommonProxy")
    public static CommonProxy proxy;

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

    public static BlockSolder machineSolder;
    public static BlockMAD machineMAD;
    public static BlockWinder machineWinder;

    public static BlockBurntMachine machineBurnt;

    CfgHandler cfgHandler;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        //Item/Block init & registering
        //config handling
        Util.LOGGER.info("Starting PreInitialization...");

        cfgHandler = new CfgHandler(e.getSuggestedConfigurationFile());

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

        machineSolder = (BlockSolder) new BlockSolder().setBlockName(Util.MOD_ID + "_"  + Names.MACHINE_SOLDER).setCreativeTab(CreativeTabGeneral.instance);
        machineMAD = (BlockMAD) new BlockMAD().setBlockName(Util.MOD_ID + "_"  + Names.MACHINE_MAD).setCreativeTab(CreativeTabGeneral.instance);
        machineWinder = (BlockWinder) new BlockWinder().setBlockName(Util.MOD_ID + "_"  + Names.MACHINE_WINDER).setCreativeTab(CreativeTabGeneral.instance);

        machineBurnt = (BlockBurntMachine) new BlockBurntMachine().setBlockName(Util.MOD_ID + "_"  + Names.MACHINE_BURNT).setCreativeTab(CreativeTabGeneral.instance);

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

        GameRegistry.registerBlock(machineSolder, Names.MACHINE_SOLDER);
        GameRegistry.registerBlock(machineMAD, Names.MACHINE_MAD);
        GameRegistry.registerBlock(machineWinder, Names.MACHINE_WINDER);

        GameRegistry.registerBlock(machineBurnt, Names.MACHINE_BURNT);

        Util.LOGGER.info("Finished PreInitialization");
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        //proxy, TileEntity, entity, GUI & packet registering
        Util.LOGGER.info("Starting Initialization...");

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(wireCopper, 4), "CCC", "CSC", "CCC", 'C', "ingotCopper", 'S', Items.stick));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(tubeIron, 1), " I ", "I I", " I ", 'I', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coil, 1, 2048), "WWW", "WTW", "WWW", 'W', wireCopper, 'T', ElectricalEngineering.tubeIron));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coil, 1), "WWW", "WCW", "WWW", 'W', wireCopper, 'C', new ItemStack(coil, 1, OreDictionary.WILDCARD_VALUE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(hardPaperRaw), "PPP", "GPD", "PPP", 'P', Items.paper, 'G', "ingotGold", 'D', "dyeGreen"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pcbUnetched), "GGG", "PPP", 'G', "nuggetGold", 'P', ElectricalEngineering.hardPaper));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineSolder), "PC ", "SIS", "SRS", 'P', ElectricalEngineering.pcbLVBurnt, 'C', new ItemStack(coil, 1, OreDictionary.WILDCARD_VALUE), 'S', "cobblestone", 'I', "blockIron", 'R', "blockRedstone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bucketAcid), "RGR", "GBG", "RGR", 'R', "dustRedstone", 'G', "dustGlowstone", 'B', Items.water_bucket));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineMAD), "PPP", "BGB", "BRB", 'G', "paneGlassColorless", 'B', "blockIron", 'R', "blockRedstone", 'P', Blocks.sticky_piston));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineWinder), "CCC", "PTP", "CRC", 'C', "cobblestone", 'P', Blocks.piston, 'T', ElectricalEngineering.tubeIron, 'R', "blockRedstone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bolt), "  I", " I ", "I  ", 'I', "ingotIron"));

        GameRegistry.addSmelting(hardPaperRaw, new ItemStack(hardPaper), 10);

        Util.registerEvent(new CraftEvent());
        MinecraftForge.TERRAIN_GEN_BUS.register(new WorldDecorationEvent());
        PacketHandler.init();

        GameRegistry.registerTileEntity(TileEntitySolder.class, Util.MOD_ID_LOWER + ":tileEntitySolder");
        GameRegistry.registerTileEntity(TileEntityMAD.class, Util.MOD_ID_LOWER + ":tileEntityMAD");
        GameRegistry.registerTileEntity(TileEntityCoilWinder.class, Util.MOD_ID_LOWER + ":tileEntityWinder");

        EntityRegistry.registerModEntity(EntityBolt.class, "Bolt", EntityRegistry.findGlobalUniqueEntityId(), this, 64, 1, true);

        GuiHandler.init();
        SolderRecipe.init();

        proxy.initRenderStuff();

        Util.LOGGER.info("Finished Initialization");
    }

}
