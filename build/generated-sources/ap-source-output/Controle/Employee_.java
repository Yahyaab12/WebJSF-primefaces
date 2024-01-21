package Controle;

import Controle.Machine;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-12T22:17:33")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile CollectionAttribute<Employee, Machine> machineCollection;
    public static volatile SingularAttribute<Employee, Integer> id;
    public static volatile SingularAttribute<Employee, Double> salaire;
    public static volatile SingularAttribute<Employee, String> nom;
    public static volatile SingularAttribute<Employee, String> prenom;

}