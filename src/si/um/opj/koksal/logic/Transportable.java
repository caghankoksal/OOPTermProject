package si.um.opj.koksal.logic;

import si.um.opj.koksal.logic.transport.Vehicle;

public interface Transportable {
    public void acceptVehicle(Vehicle vehicle) throws Exception;
}
