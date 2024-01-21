package Services;
import Controle.Employee;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EmployeeServiceLocal {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);
}
