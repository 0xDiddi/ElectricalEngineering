/*
 * This file ("PacketHandler.java") is part of the Actually Additions Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://github.com/Ellpeck/ActuallyAdditions/blob/master/README.md
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * � 2015 Ellpeck
 */

package theultimatehose.electricalengineering.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import theultimatehose.electricalengineering.Util;
import theultimatehose.electricalengineering.network.sync.PacketSyncerToClient;
import theultimatehose.electricalengineering.network.sync.PcbFrameDataStackPacket;

public class PacketHandler {

    public static SimpleNetworkWrapper theNetwork;

    public static void init() {
        theNetwork = NetworkRegistry.INSTANCE.newSimpleChannel(Util.MOD_ID_LOWER);

        theNetwork.registerMessage(PacketSyncerToClient.Handler.class, PacketSyncerToClient.class, 0, Side.CLIENT);
        theNetwork.registerMessage(PcbFrameDataStackPacket.Handler.class, PcbFrameDataStackPacket.class, 1, Side.SERVER);
    }
}
