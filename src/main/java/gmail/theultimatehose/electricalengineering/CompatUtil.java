package gmail.theultimatehose.electricalengineering;

import cpw.mods.fml.common.registry.GameRegistry;
import gmail.theultimatehose.electricalengineering.block.BlockManager;
import gmail.theultimatehose.electricalengineering.item.ItemManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CompatUtil {

    public static void registerCompatCrafting() {
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.wireCopper,      "X", 'X', "wireCopper"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.tubeIron,        "X", 'X', "tubeIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.coil,            "X", 'X', "coil"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.pcbLVBurnt,      "X", 'X', "pcbLVBurnt"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.pcbHVBurnt,      "X", 'X', "pcbHVBurnt"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.pcbScrapLV,      "X", 'X', "pcbScrapLV"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.pcbScrapHV,      "X", 'X', "pcbScrapHV"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.hardPaperRaw,    "X", 'X', "hardPaperRaw"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.hardPaper,       "X", 'X', "hardPaper"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.bucketAcid,      "X", 'X', "bucketAcid"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.pcbUnetched,     "X", 'X', "pcbUnetched"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.pcbEtched,       "X", 'X', "pcbEtched"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.pcbControl,      "X", 'X', "pcbControl"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.pcbVoltReg,      "X", 'X', "pcbVoltReg"));

        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.capacitorLV,     "X", 'X', "capacitorLV"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.capacitorHV,     "X", 'X', "capacitorHV"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.transistor,      "X", 'X', "transistor"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.resistor,        "X", 'X', "resistor"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.transformer,     "X", 'X', "transformer"));

        GameRegistry.addRecipe(new ShapedOreRecipe(ItemManager.gaussGun,        "X", 'X', "gaussGun"));


        GameRegistry.addRecipe(new ShapedOreRecipe(BlockManager.machineSolder,  "X", 'X', "machineSolder"));
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockManager.machineMAD,     "X", 'X', "machineMAD"));
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockManager.machineWinder,  "X", 'X', "machineWinder"));
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockManager.machineBurnt,   "X", 'X', "blockBurntMachine"));
    }

}
