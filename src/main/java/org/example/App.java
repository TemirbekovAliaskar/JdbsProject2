package org.example;

import org.example.config.JdbcConfig;
import org.example.models.Employee;
import org.example.models.Job;
import service.EmployeeService;
import service.JobService;
import service.impl.EmployeeServiceImpl;
import service.impl.JobServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        JdbcConfig.getConnection();
        JobService jobService = new JobServiceImpl();
//        jobService.createJobTable();
//        jobService.addJob(new Job("Mentor","JS","Backend developer",7));
//        System.out.println(jobService.getJobById(1L));
//        System.out.println(jobService.sortByExperience("asc"));

        System.out.println(jobService.getJobByEmployeeId(1L));

//        jobService.deleteDescriptionColumn();

        EmployeeService employeeService = new EmployeeServiceImpl();
//        employeeService.createEmployee();
//        employeeService.addEmployee(new Employee("Argen","Bakytbekov",19,"a@gmail.com"));
//        employeeService.addEmployee(new Employee("Ar","Baekov",23,"ar@gmail.com",2));
//        employeeService.dropTable();
//        employeeService.createEmployee();
//        employeeService.updateEmployee(1L,new Employee("Ali","Asa",34,"a@gmail.com"));
//        System.out.println(employeeService.getAllEmployees());
//        System.out.println(employeeService.findByEmail("a@gmail.com"));
//        System.out.println(employeeService.getEmployeeById(1L));
//        System.out.println(employeeService.getEmployeeByPosition("Mentor"));
    }
}
