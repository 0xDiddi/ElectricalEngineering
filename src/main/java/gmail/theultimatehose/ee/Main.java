package gmail.theultimatehose.ee;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gmail.theultimatehose.ee.blocks.BlockBurntMachine;
import gmail.theultimatehose.ee.blocks.BlockMAD;
import gmail.theultimatehose.ee.blocks.BlockSolder;
import gmail.theultimatehose.ee.blocks.BlockWinder;
import gmail.theultimatehose.ee.config.CfgHandler;
import gmail.theultimatehose.ee.creativetab.CreativeTabGeneral;
import gmail.theultimatehose.ee.creativetab.CreativeTabWeapons;
import gmail.theultimatehose.ee.event.CraftEvent;
import gmail.theultimatehose.ee.event.WorldDecorationEvent;
import gmail.theultimatehose.ee.gui.GuiHandler;
import gmail.theultimatehose.ee.item.ItemGaussGun;
import gmail.theultimatehose.ee.network.PacketHandler;
import gmail.theultimatehose.ee.proxy.CommonProxy;
import gmail.theultimatehose.ee.recipe.SolderRecipe;
import gmail.theultimatehose.ee.tile.TileEntityCoilWinder;
import gmail.theultimatehose.ee.tile.TileEntityMAD;
import gmail.theultimatehose.ee.tile.TileEntitySolder;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = Util.MOD_ID, name = "Electrical Engineering", version = Util.VERSION)
public class Main {

    @Instance(Util.MOD_ID)
    public static Main instance;

    @SidedProxy(clientSide = "gmail.theultimatehose.ee.proxy.ClientProxy", serverSide = "gmail.theultimatehose.ee.proxy.CommonProxy")
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

        wireCopper = new Item().setUnlocalizedName(Names.WIRE_COPPER).setTextureName("ee:copperWire").setCreativeTab(CreativeTabGeneral.instance);
        tubeIron = new Item().setUnlocalizedName(Names.TUBE_IRON).setTextureName("ee:tubeIron").setCreativeTab(CreativeTabGeneral.instance);
        coil = new Item().setUnlocalizedName(Names.COIL).setMaxStackSize(1).setMaxDamage(2049).setTextureName("ee:coil").setCreativeTab(CreativeTabGeneral.instance).setNoRepair();
        pcbLVBurnt = new Item().setUnlocalizedName(Names.PCB_LV_BURNT).setMaxStackSize(32).setTextureName("ee:pcbNormalBurnt").setCreativeTab(CreativeTabGeneral.instance);
        pcbHVBurnt = new Item().setUnlocalizedName(Names.PCB_HV_BURNT).setMaxStackSize(32).setTextureName("ee:pcbHVBurnt").setCreativeTab(CreativeTabGeneral.instance);
        pcbScrapLV = new Item().setUnlocalizedName(Names.PCB_SCRAP_LV).setTextureName("ee:pcbScrapLV").setCreativeTab(CreativeTabGeneral.instance);
        pcbScrapHV = new Item().setUnlocalizedName(Names.PCB_SCRAP_HV).setTextureName("ee:pcbScrapHV").setCreativeTab(CreativeTabGeneral.instance);
        hardPaperRaw = new Item().setUnlocalizedName(Names.HARDPAPER_RAW).setTextureName("ee:hardPaper_raw").setCreativeTab(CreativeTabGeneral.instance);
        hardPaper = new Item().setUnlocalizedName(Names.HARDPAPER).setTextureName("ee:hardPaper").setCreativeTab(CreativeTabGeneral.instance);
        bucketAcid = new Item().setUnlocalizedName(Names.BUCKET_ACID).setMaxStackSize(1).setTextureName("ee:bucket_acid").setCreativeTab(CreativeTabGeneral.instance);
        pcbUnetched = new Item().setUnlocalizedName(Names.PCB_UNETCHED).setTextureName("ee:pcbUnetched").setCreativeTab(CreativeTabGeneral.instance);
        pcbEtched = new Item().setUnlocalizedName(Names.PCB_ETCHED).setTextureName("ee:pcbEtched").setCreativeTab(CreativeTabGeneral.instance);
        pcbControl = new Item().setUnlocalizedName(Names.PCB_CONTROL).setTextureName("ee:pcbControl").setCreativeTab(CreativeTabGeneral.instance);
        pcbVoltReg = new Item().setUnlocalizedName(Names.PCB_VOLT_REG).setTextureName("ee:pcbVoltReg").setCreativeTab(CreativeTabGeneral.instance);

        capacitorLV = new Item().setUnlocalizedName(Names.CAPACITOR_LV).setTextureName("ee:capacitor_LV").setCreativeTab(CreativeTabGeneral.instance);
        capacitorHV = new Item().setUnlocalizedName(Names.CAPACITOR_HV).setTextureName("ee:capacitor_HV").setCreativeTab(CreativeTabGeneral.instance);
        transistor = new Item().setUnlocalizedName(Names.TRANSISTOR).setTextureName("ee:transistor").setCreativeTab(CreativeTabGeneral.instance);
        resistor = new Item().setUnlocalizedName(Names.RESISTOR).setTextureName("ee:resistor").setCreativeTab(CreativeTabGeneral.instance);
        transformer = new Item().setUnlocalizedName(Names.TRANSFORMER).setTextureName("ee:transformer").setCreativeTab(CreativeTabGeneral.instance);

        gaussGun = new ItemGaussGun().setUnlocalizedName(Names.GAUSS_GUN).setFull3D().setTextureName("ee:gaussGun").setCreativeTab(CreativeTabWeapons.instance);

        machineSolder = (BlockSolder) new BlockSolder().setBlockName(Names.MACHINE_SOLDER).setCreativeTab(CreativeTabGeneral.instance);
        machineMAD = (BlockMAD) new BlockMAD().setBlockName(Names.MACHINE_MAD).setCreativeTab(CreativeTabGeneral.instance);
        machineWinder = (BlockWinder) new BlockWinder().setBlockName(Names.MACHINE_WINDER).setCreativeTab(CreativeTabGeneral.instance);

        machineBurnt = (BlockBurntMachine) new BlockBurntMachine().setBlockName(Names.MACHINE_BURNT).setCreativeTab(CreativeTabGeneral.instance);

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
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coil, 1, 2048), "WWW", "WTW", "WWW", 'W', wireCopper, 'T', Main.tubeIron));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(coil, 1), "WWW", "WCW", "WWW", 'W', wireCopper, 'C', new ItemStack(coil, 1, OreDictionary.WILDCARD_VALUE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(hardPaperRaw), "PPP", "GPD", "PPP", 'P', Items.paper, 'G', "ingotGold", 'D', "dyeGreen"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pcbUnetched), "GGG", "PPP", 'G', "nuggetGold", 'P', Main.hardPaper));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineSolder), "PC ", "SIS", "SRS", 'P', Main.pcbLVBurnt, 'C', new ItemStack(coil, 1, OreDictionary.WILDCARD_VALUE), 'S', "cobblestone", 'I', "blockIron", 'R', "blockRedstone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bucketAcid), "RGR", "GBG", "RGR", 'R', "dustRedstone", 'G', "dustGlowstone", 'B', Items.water_bucket));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineMAD), "PPP", "BGB", "BRB", 'G', "paneGlassColorless", 'B', "blockIron", 'R', "blockRedstone", 'P', Blocks.sticky_piston));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(machineWinder), "CCC", "PTP", "CRC", 'C', "cobblestone", 'P', Blocks.piston, 'T', Main.tubeIron, 'R', "blockRedstone"));

        GameRegistry.addSmelting(hardPaperRaw, new ItemStack(hardPaper), 10);

        Util.registerEvent(new CraftEvent());
        MinecraftForge.TERRAIN_GEN_BUS.register(new WorldDecorationEvent());
        PacketHandler.init();

        GameRegistry.registerTileEntity(TileEntitySolder.class, Util.MOD_ID_LOWER + ":tileEntitySolder");
        GameRegistry.registerTileEntity(TileEntityMAD.class, Util.MOD_ID_LOWER + ":tileEntityMAD");
        GameRegistry.registerTileEntity(TileEntityCoilWinder.class, Util.MOD_ID_LOWER + ":tileEntityWinder");
        GuiHandler.init();
        SolderRecipe.init();

        proxy.initRenderStuff();

        Util.LOGGER.info("Finished Initialization");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {

    }

}
