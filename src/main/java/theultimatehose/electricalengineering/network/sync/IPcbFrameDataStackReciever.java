package theultimatehose.electricalengineering.network.sync;

public interface IPcbFrameDataStackReciever {

    void onStackReceived(String channelIn, String channelOut, String compare, boolean[] rsIn, boolean[] rsOut);

}
