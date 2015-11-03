package gmail.theultimatehose.gg;

import cpw.mods.fml.common.SidedProxy;
import gmail.theultimatehose.gg.blocks.BlockBurntMachine;
import gmail.theultimatehose.gg.blocks.BlockMAD;
import gmail.theultimatehose.gg.blocks.BlockSolder;
import gmail.theultimatehose.gg.blocks.BlockWinder;
import gmail.theultimatehose.gg.config.CfgHandler;
import gmail.theultimatehose.gg.event.CraftEvent;
import gmail.theultimatehose.gg.event.WorldDecorationEvent;
import gmail.theultimatehose.gg.gui.GuiHandler;
import gmail.theultimatehose.gg.item.ItemGaussGun;
import gmail.theultimatehose.gg.proxy.CommonProxy;
import gmail.theultimatehose.gg.recipe.SolderRecipe;
import gmail.theultimatehose.gg.tile.TileEntityCoilWinder;
import gmail.theultimatehose.gg.tile.TileEntityMAD;
import gmail.theultimatehose.gg.tile.TileEntitySolder;
import gmail.theultimatehose.gg.network.PacketHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Util.MOD_ID, name = "Gauss Gun Mod", version = Util.VERSION)
public class Main {
	
	@Instance(Util.MOD_ID)
	public static Main instance;

    @SidedProxy(clientSide = "gmail.theultimatehose.gg.proxy.ClientProxy", serverSide = "gmail.theultimatehose.gg.proxy.CommonProxy")
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

		wireCopper = new Item().setUnlocalizedName(Names.WIRE_COPPER).setTextureName("gg:copperWire").setCreativeTab(CreativeTab.instance);
		tubeIron = new Item().setUnlocalizedName(Names.TUBE_IRON).setTextureName("gg:tubeIron").setCreativeTab(CreativeTab.instance);
		coil = new Item().setUnlocalizedName(Names.COIL).setMaxStackSize(1).setMaxDamage(2049).setTextureName("gg:coil").setCreativeTab(CreativeTab.instance).setNoRepair();
		pcbLVBurnt = new Item().setUnlocalizedName(Names.PCB_LV_BURNT).setMaxStackSize(32).setTextureName("gg:pcbNormalBurnt").setCreativeTab(CreativeTab.instance);
		pcbHVBurnt = new Item().setUnlocalizedName(Names.PCB_HV_BURNT).setMaxStackSize(32).setTextureName("gg:pcbHVBurnt").setCreativeTab(CreativeTab.instance);
		pcbScrapLV = new Item().setUnlocalizedName(Names.PCB_SCRAP_LV).setTextureName("gg:pcbScrapLV").setCreativeTab(CreativeTab.instance);
		pcbScrapHV = new Item().setUnlocalizedName(Names.PCB_SCRAP_HV).setTextureName("gg:pcbScrapHV").setCreativeTab(CreativeTab.instance);
		hardPaperRaw = new Item().setUnlocalizedName(Names.HARDPAPER_RAW).setTextureName("gg:hardPaper_raw").setCreativeTab(CreativeTab.instance);
		hardPaper = new Item().setUnlocalizedName(Names.HARDPAPER).setTextureName("gg:hardPaper").setCreativeTab(CreativeTab.instance);
		bucketAcid = new Item().setUnlocalizedName(Names.BUCKET_ACID).setMaxStackSize(1).setTextureName("gg:bucket_acid").setCreativeTab(CreativeTab.instance);
		pcbUnetched = new Item().setUnlocalizedName(Names.PCB_UNETCHED).setTextureName("gg:pcbUnetched").setCreativeTab(CreativeTab.instance);
        pcbEtched = new Item().setUnlocalizedName(Names.PCB_ETCHED).setTextureName("gg:pcbEtched").setCreativeTab(CreativeTab.instance);
		pcbControl = new Item().setUnlocalizedName(Names.PCB_CONTROL).setTextureName("gg:pcbControl").setCreativeTab(CreativeTab.instance);
        pcbVoltReg = new Item().setUnlocalizedName(Names.PCB_VOLT_REG).setTextureName("gg:pcbVoltReg").setCreativeTab(CreativeTab.instance);

		capacitorLV = new Item().setUnlocalizedName(Names.CAPACITOR_LV).setTextureName("gg:capacitor_LV").setCreativeTab(CreativeTab.instance);
        capacitorHV = new Item().setUnlocalizedName(Names.CAPACITOR_HV).setTextureName("gg:capacitor_HV").setCreativeTab(CreativeTab.instance);
        transistor = new Item().setUnlocalizedName(Names.TRANSISTOR).setTextureName("gg:transistor").setCreativeTab(CreativeTab.instance);
        resistor = new Item().setUnlocalizedName(Names.RESISTOR).setTextureName("gg:resistor").setCreativeTab(CreativeTab.instance);
        transformer = new Item().setUnlocalizedName(Names.TRANSFORMER).setTextureName("gg:transformer").setCreativeTab(CreativeTab.instance);

        gaussGun = new ItemGaussGun().setUnlocalizedName(Names.GAUSS_GUN).setFull3D().setTextureName("gg:gaussGun").setCreativeTab(CreativeTab.instance);

		machineSolder = (BlockSolder) new BlockSolder().setBlockName(Names.MACHINE_SOLDER).setCreativeTab(CreativeTab.instance);
        machineMAD = (BlockMAD) new BlockMAD().setBlockName(Names.MACHINE_MAD).setCreativeTab(CreativeTab.instance);
        machineWinder = (BlockWinder) new BlockWinder().setBlockName(Names.MACHINE_WINDER).setCreativeTab(CreativeTab.instance);

        machineBurnt = (BlockBurntMachine) new BlockBurntMachine().setBlockName(Names.MACHINE_BURNT).setCreativeTab(CreativeTab.instance);

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
