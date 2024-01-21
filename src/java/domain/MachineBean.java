package domain;

import Services.MachineService;
import Services.EmployeeService;
import Controle.Employee;
import Controle.Machine;
import java.util.stream.Collectors;
import java.util.*;
import Services.EmployeeServiceLocal;
import Services.MachineServiceLocal;
import javax.annotation.PostConstruct;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class MachineBean implements Serializable {
    @Inject
    private MachineServiceLocal machineService;
    @Inject
    private EmployeeServiceLocal employeeService; // Assuming you have an EmployeeService
    
    private List<Machine> allMachines;
    private List<Employee> allEmployees;
    private Machine newMachine;
    private Machine selectedMachine;
    private List<Machine> filteredMachines;
    private Employee selectedEmployee; // Property to store the selected employee for filtering
    private String sortOrder; // Property to store the sorting order (ASC or DESC)
    @PostConstruct
    public void init() {
        refreshMachineList();
    }
 public List<Employee> getAllEmployees() {
        if (allEmployees == null) {
            allEmployees = employeeService.getAllEmployees();
        }
        return allEmployees;
    }
 public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    // Getter and setter for sortOrder
    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<Machine> getAllMachines() {
        if (allMachines == null) {
            refreshMachineList();
        }
        return allMachines;
    }

    public List<Machine> getFilteredMachines() {
        return filteredMachines;
    }

    public void setFilteredMachines(List<Machine> filteredMachines) {
        this.filteredMachines = filteredMachines;
    }

    public void refreshMachineList() {
    // Load all machines
    allMachines = machineService.getAllMachines();
    // Apply filtering based on selected criteria
    filterMachines();
    

}

public void filterMachines() {
    if (selectedEmployee != null) {
        filteredMachines = allMachines.stream()
            .filter(machine -> machine.getEmployee().getId().equals(selectedEmployee.getId()))
            .collect(Collectors.toList());
    } else {
        filteredMachines = allMachines;
    }
}


    public void addMachine() {
        // Implement logic to add a new machine
        machineService.addMachine(newMachine);
        // Reset newMachine to prepare for the next entry
        newMachine = new Machine();
        // Refresh the machine list
        refreshMachineList();
    }

    public void deleteMachine(Machine machine) {
        // Implement logic to delete the selected machine
        machineService.deleteMachine(machine.getId());
        // Refresh the machine list
        refreshMachineList();
    }

    public void prepareForEdit(Machine machine) {
        // Set the selected machine for modification
        selectedMachine = machine;
    }

    public void updateMachine() {
        // Implement logic to update the selected machine
        machineService.updateMachine(selectedMachine);
        // Reset selectedMachine after modification
        selectedMachine = null;
        // Refresh the machine list
        refreshMachineList();
    }


}
