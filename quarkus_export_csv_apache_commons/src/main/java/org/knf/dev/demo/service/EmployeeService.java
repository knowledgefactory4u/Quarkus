package org.knf.dev.demo.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.knf.dev.demo.entity.Employee;
import org.knf.dev.demo.helper.CSVHelper;
import org.knf.dev.demo.repository.EmployeeRepository;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;

    @Transactional
    public List<Employee> fetchAll() {
        dummyDatas();
        return employeeRepository.listAll();

    }

    public ByteArrayInputStream load() {
        List<Employee> employees = fetchAll();

        ByteArrayInputStream in = CSVHelper.employeesToCSV(employees);
        return in;
    }

    // generate dummy datas
    public void dummyDatas() {
        for (int i = 1; i <=20; i++) {
            employeeRepository.persist(
                new Employee("sibin-" + i, "mygmail@com-" + i, 
                  "USA", 54 + i, "Developer"));
        }
    }
}
