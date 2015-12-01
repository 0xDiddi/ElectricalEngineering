package theultimatehose.electricalengineering.data.worlddata;

public class PcbFrameDataEntry {

    private int worldID;
    private int x, y, z;

    public PcbFrameDataEntry(int worldID, int x, int y, int z) {
        this.worldID = worldID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getWorldID() {
        return worldID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public boolean hasSamePosition(PcbFrameDataEntry otherData) {
        return otherData.worldID == this.worldID && otherData.x == this.x && otherData.y == this.y && otherData.z == this.z;
    }

}
