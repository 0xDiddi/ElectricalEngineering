package gmail.theultimatehose.electricalengineering;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import gmail.theultimatehose.electricalengineering.block.*;
import gmail.theultimatehose.electricalengineering.config.CfgHandler;
import gmail.theultimatehose.electricalengineering.entity.EntityBolt;
import gmail.theultimatehose.electricalengineering.event.CraftEvent;
import gmail.theultimatehose.electricalengineering.event.WorldDecorationEvent;
import gmail.theultimatehose.electricalengineering.gui.GuiHandler;
import gmail.theultimatehose.electricalengineering.item.ItemManager;
import gmail.theultimatehose.electricalengineering.network.PacketHandler;
import gmail.theultimatehose.electricalengineering.proxy.CommonProxy;
import gmail.theultimatehose.electricalengineering.recipe.SolderRecipe;
import gmail.theultimatehose.electricalengineering.tile.TileEntityCoilWinder;
import gmail.theultimatehose.electricalengineering.tile.TileEntityMAD;
import gmail.theultimatehose.electricalengineering.tile.TileEntityPcbFrame;
import gmail.theultimatehose.electricalengineering.tile.TileEntitySolder;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

    CfgHandler cfgHandler;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        //Item/Block init & registering
        //config handling
        Util.LOGGER.info("Starting PreInitialization...");

        cfgHandler = new CfgHandler(e.getSuggestedConfigurationFile());

        ItemManager.initItems();

        BlockManager.initBlocks();

        ItemManager.registerItemsToGR();
        ItemManager.registerItemsToOreDict();

        BlockManager.registerBlocksToGR();
        BlockManager.registerBlocksToOreDict();

        Util.LOGGER.info("Finished PreInitialization");
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        //proxy, TileEntity, entity, GUI & packet registering
        Util.LOGGER.info("Starting Initialization...");

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.wireCopper, 4), "CCC", "CSC", "CCC", 'C', "ingotCopper", 'S', Items.stick));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.tubeIron, 1), " I ", "I I", " I ", 'I', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.coil, 1, 2048), "WWW", "WTW", "WWW", 'W', ItemManager.wireCopper, 'T', ItemManager.tubeIron));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.coil, 1), "WWW", "WCW", "WWW", 'W', ItemManager.wireCopper, 'C', new ItemStack(ItemManager.coil, 1, OreDictionary.WILDCARD_VALUE)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.hardPaperRaw), "PPP", "GPD", "PPP", 'P', Items.paper, 'G', "ingotGold", 'D', "dyeGreen"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.pcbUnetched), "GGG", "PPP", 'G', "nuggetGold", 'P', ItemManager.hardPaper));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.machineSolder), "PC ", "SIS", "SRS", 'P', ItemManager.pcbLVBurnt, 'C', new ItemStack(ItemManager.coil, 1, OreDictionary.WILDCARD_VALUE), 'S', "cobblestone", 'I', "blockIron", 'R', "blockRedstone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.bucketAcid), "RGR", "GBG", "RGR", 'R', "dustRedstone", 'G', "dustGlowstone", 'B', Items.water_bucket));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.machineMAD), "PPP", "BGB", "BRB", 'G', "paneGlassColorless", 'B', "blockIron", 'R', "blockRedstone", 'P', Blocks.sticky_piston));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.machineWinder), "CCC", "PTP", "CRC", 'C', "cobblestone", 'P', Blocks.piston, 'T', ItemManager.tubeIron, 'R', "blockRedstone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.bolt), "  I", " I ", "I  ", 'I', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemManager.copperRod), "  C", " C ", "C  ", 'C', "ingotCopper"));

        GameRegistry.addSmelting(ItemManager.hardPaperRaw, new ItemStack(ItemManager.hardPaper), 10);

        CompatUtil.registerCompatCrafting();

        Util.registerEvent(new CraftEvent());
        MinecraftForge.TERRAIN_GEN_BUS.register(new WorldDecorationEvent());
        PacketHandler.init();

        GameRegistry.registerTileEntity(TileEntitySolder.class, Util.MOD_ID_LOWER + ":tileEntitySolder");
        GameRegistry.registerTileEntity(TileEntityMAD.class, Util.MOD_ID_LOWER + ":tileEntityMAD");
        GameRegistry.registerTileEntity(TileEntityCoilWinder.class, Util.MOD_ID_LOWER + ":tileEntityWinder");
        GameRegistry.registerTileEntity(TileEntityPcbFrame.class, Util.MOD_ID_LOWER + ":tileEntityPcbFrame");

        EntityRegistry.registerModEntity(EntityBolt.class, "Bolt", EntityRegistry.findGlobalUniqueEntityId(), instance, 64, 80000, true);

        GuiHandler.init();
        SolderRecipe.init();

        proxy.initRenderStuff();

        Util.LOGGER.info("Finished Initialization");
    }

}
