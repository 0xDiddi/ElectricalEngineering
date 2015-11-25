package gmail.theultimatehose.electricalengineering.network.sync;

public interface IPcbFrameDataStackReciever {

    void onStackRecieved(String channelIn, String channelOut, String compare, boolean[] rsIn, boolean[] rsOut);

}
