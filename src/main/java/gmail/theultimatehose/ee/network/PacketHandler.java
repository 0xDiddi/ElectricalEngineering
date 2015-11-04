/*
 * This file ("PacketHandler.java") is part of the Actually Additions Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://github.com/Ellpeck/ActuallyAdditions/blob/master/README.md
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015 Ellpeck
 */

package gmail.theultimatehose.ee.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import gmail.theultimatehose.ee.network.sync.PacketSyncerToClient;

public class PacketHandler {

    public static SimpleNetworkWrapper theNetwork;

    public static void init() {
        theNetwork = NetworkRegistry.INSTANCE.newSimpleChannel("ee");

        theNetwork.registerMessage(PacketSyncerToClient.Handler.class, PacketSyncerToClient.class, 0, Side.CLIENT);
    }
}
