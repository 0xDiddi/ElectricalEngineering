package gmail.theultimatehose.electricalengineering.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import gmail.theultimatehose.electricalengineering.Util;

public class NEIGaussGunConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        Util.LOGGER.info("Registering NEI Handlers...");

        SolderingRecipeHandler solderingRecipeHandler = new SolderingRecipeHandler();
        API.registerRecipeHandler(solderingRecipeHandler);
        API.registerUsageHandler(solderingRecipeHandler);

        DeSolderRecipeHandler deSolderRecipeHandler = new DeSolderRecipeHandler();
        API.registerRecipeHandler(deSolderRecipeHandler);
        API.registerUsageHandler(deSolderRecipeHandler);

        CoilWinderRecipeHandler coilWinderRecipeHandler = new CoilWinderRecipeHandler();
        API.registerRecipeHandler(coilWinderRecipeHandler);
        API.registerUsageHandler(coilWinderRecipeHandler);

        SortingRecipeHandler sortingRecipeHandler = new SortingRecipeHandler();
        API.registerRecipeHandler(sortingRecipeHandler);
        API.registerUsageHandler(sortingRecipeHandler);

        EtchingRecipeHandler etchingRecipeHandler = new EtchingRecipeHandler();
        API.registerRecipeHandler(etchingRecipeHandler);
        API.registerUsageHandler(etchingRecipeHandler);

        Util.LOGGER.info("Registered NEI Handlers.");
    }

    @Override
    public String getName() {
        return Util.MOD_ID + "-NEI-Plugin";
    }

    @Override
    public String getVersion() {
        return Util.VERSION;
    }
}