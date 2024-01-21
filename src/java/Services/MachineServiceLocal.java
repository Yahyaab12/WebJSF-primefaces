package Services;

import Controle.Machine;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MachineServiceLocal {
    List<Machine> getAllMachines();
    List<Machine> getMachinesByEmployeeId(int employeeId);
    void addMachine(Machine machine);
    void deleteMachine(int machineId);
    void updateMachine(Machine machine);
}
