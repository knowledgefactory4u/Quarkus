package org.knf.dev.demo.repository;

import javax.enterprise.context.ApplicationScoped;
import org.knf.dev.demo.entity.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

}