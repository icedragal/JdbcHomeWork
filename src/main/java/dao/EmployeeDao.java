package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    void add(Employee employee) throws SQLException;
    Employee readById(long id);
    List<Employee> findAll();
    void updateEmployeeById(long id,String name);
    void deleteById(long id);
}
