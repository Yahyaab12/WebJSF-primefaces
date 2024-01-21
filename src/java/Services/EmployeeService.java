package Services;
import Controle.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EmployeeService implements EmployeeServiceLocal {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager entityManager; // Injected entity manager, replace with your persistence unit name

    @Override
    public List<Employee> getAllEmployees() {
        Query query = entityManager.createNamedQuery("Employee.findAll");
        return query.getResultList();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void addEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }
}
