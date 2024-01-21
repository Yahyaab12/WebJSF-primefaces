package domain;

import Services.EmployeeService;
import Controle.Employee;
import Services.EmployeeServiceLocal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class EmployeeBean implements Serializable {

    @Inject
    private EmployeeServiceLocal employeeService;

    private List<Employee> allEmployees;
    private Employee newEmployee; // Property to store the new employee information
    private Employee selectedEmployee; // Property to store the selected employee for modification

    public EmployeeBean() {
        newEmployee = new Employee();
    }

    // Getter and setter for newEmployee
    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    // Getter and setter for selectedEmployee
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<Employee> getAllEmployees() {
        if (allEmployees == null) {
            refreshEmployeeList();
        }
        return allEmployees;
    }

    private void refreshEmployeeList() {
        allEmployees = employeeService.getAllEmployees();
    }

    public String viewEmployee(Employee employee) {
        // Implement logic to navigate to the employee details page
        // You can store the selected employee in a session or request scope for use on the next page
        // For simplicity, I'm assuming you have an EmployeeDetails.xhtml page
        // Here, I'm using a flash scope to pass the employee to the next page

        return "employeeDetails?faces-redirect=true&id=" + employee.getId();
    }

    public void addEmployee() {
        // Implement logic to add a new employee
        employeeService.addEmployee(newEmployee);
        // Reset newEmployee to prepare for the next entry
        newEmployee = new Employee();
        // Refresh the employee list
        refreshEmployeeList();
    }

    public void deleteEmployee(Employee employee) {
        // Implement logic to delete the selected employee
        employeeService.deleteEmployee(employee.getId());
        // Refresh the employee list
        refreshEmployeeList();
    }

    public void prepareForEdit(Employee employee) {
        // Set the selected employee for modification
        selectedEmployee = employee;
    }

    public void updateEmployee() {
        // Implement logic to update the selected employee
        employeeService.updateEmployee(selectedEmployee);
        // Reset selectedEmployee after modification
        selectedEmployee = null;
        // Refresh the employee list
        refreshEmployeeList();
    }

    // Add any other methods you need
}
