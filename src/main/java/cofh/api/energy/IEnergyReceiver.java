package cofh.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Implement this interface on Tile Entities which should receive storage, generally storing it in one or more internal {@link IEnergyStorage} objects.
 * <p>
 * A reference implementation is provided {@link TileEnergyHandler}.
 *
 * @author King Lemming
 *
 */
public interface IEnergyReceiver extends IEnergyConnection {

	/**
	 * Add storage to an IEnergyReceiver, internal distribution is left entirely to the IEnergyReceiver.
	 *
	 * @param from
	 *            Orientation the storage is received from.
	 * @param maxReceive
	 *            Maximum amount of storage to receive.
	 * @param simulate
	 *            If TRUE, the charge will only be simulated.
	 * @return Amount of storage that was (or would have been, if simulated) received.
	 */
	int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate);

	/**
	 * Returns the amount of storage currently stored.
	 */
	int getEnergyStored(ForgeDirection from);

	/**
	 * Returns the maximum amount of storage that can be stored.
	 */
	int getMaxEnergyStored(ForgeDirection from);

}
