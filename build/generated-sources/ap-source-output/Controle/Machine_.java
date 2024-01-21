package Controle;

import Controle.Employee;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-12T22:17:33")
@StaticMetamodel(Machine.class)
public class Machine_ { 

    public static volatile SingularAttribute<Machine, String> reference;
    public static volatile SingularAttribute<Machine, Double> prix;
    public static volatile SingularAttribute<Machine, Date> dateAchat;
    public static volatile SingularAttribute<Machine, Integer> id;
    public static volatile SingularAttribute<Machine, Employee> employee;
    public static volatile SingularAttribute<Machine, String> marque;

}