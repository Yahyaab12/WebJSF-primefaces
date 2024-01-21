package Services;

import Controle.Machine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceContext;

@Stateless
public class MachineService implements MachineServiceLocal {

    @PersistenceContext(unitName = "WebApplication1PU") // Adjust the unitName based on your persistence unit
    private EntityManager entityManager;

    @Override
    public List<Machine> getAllMachines() {
        // Implement logic to retrieve all machines from the database
        return entityManager.createNamedQuery("Machine.findAll", Machine.class).getResultList();
    }

    @Override
    public List<Machine> getMachinesByEmployeeId(int id) {
        TypedQuery<Machine> query = entityManager.createNamedQuery("Machine.findByEmployeeId", Machine.class)
                .setParameter("id", id);
        return query.getResultList();

    }

    @Override
    public void addMachine(Machine machine) {
        // Implement logic to add a new machine to the database
        entityManager.persist(machine);
    }

    @Override
    public void deleteMachine(int machineId) {
        // Implement logic to delete a machine from the database
        Machine machine = entityManager.find(Machine.class, machineId);
        if (machine != null) {
            entityManager.remove(machine);
        }
    }

    @Override
    public void updateMachine(Machine machine) {
        // Implement logic to update a machine in the database
        entityManager.merge(machine);
    }
}
