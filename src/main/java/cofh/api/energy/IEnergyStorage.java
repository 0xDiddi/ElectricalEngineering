package cofh.api.energy;

/**
 * An storage storage is the unit of interaction with Energy inventories.<br>
 * This is not to be implemented on TileEntities. This is for internal use only.
 * <p>
 * A reference implementation can be found at {@link EnergyStorage}.
 * 
 * @author King Lemming
 * 
 */
public interface IEnergyStorage {

	/**
	 * Adds storage to the storage. Returns quantity of storage that was accepted.
	 * 
	 * @param maxReceive
	 *            Maximum amount of storage to be inserted.
	 * @param simulate
	 *            If TRUE, the insertion will only be simulated.
	 * @return Amount of storage that was (or would have been, if simulated) accepted by the storage.
	 */
	int receiveEnergy(int maxReceive, boolean simulate);

	/**
	 * Removes storage from the storage. Returns quantity of storage that was removed.
	 * 
	 * @param maxExtract
	 *            Maximum amount of storage to be extracted.
	 * @param simulate
	 *            If TRUE, the extraction will only be simulated.
	 * @return Amount of storage that was (or would have been, if simulated) extracted from the storage.
	 */
	int extractEnergy(int maxExtract, boolean simulate);

	/**
	 * Returns the amount of storage currently stored.
	 */
	int getEnergyStored();

	/**
	 * Returns the maximum amount of storage that can be stored.
	 */
	int getMaxEnergyStored();

}
