package Services;

import Controle.Machine;
import java.time.Year;
import Controle.Employee; // Make sure to import Employee
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class MachineFacade extends AbstractFacade<Machine> {
    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MachineFacade() {
        super(Machine.class);
    }
    
    public List<Machine> findMachinesByEmployeeAndYear(Employee selectedEmployee, Year acquisitionYear) {
    TypedQuery<Machine> query = em.createQuery(
        "SELECT m FROM Machine m WHERE m.employee = :employee AND m.acquisitionYear = :acquisitionYear", Machine.class);
    query.setParameter("employee", selectedEmployee);
    query.setParameter("acquisitionYear", acquisitionYear);
    return query.getResultList();
}


    // Add the following method to find machines by employee
    public List<Machine> findMachinesByEmployee(Employee selectedEmployee) {
        TypedQuery<Machine> query = em.createQuery(
            "SELECT m FROM Machine m WHERE m.employee = :employee", Machine.class);
        query.setParameter("employee", selectedEmployee); 
        return query.getResultList();
}

}
