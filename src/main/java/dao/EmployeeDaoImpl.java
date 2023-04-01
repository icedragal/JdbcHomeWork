package dao;

import jdbc.ConnectionManager;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private static String FIND_ALL = "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id";
    private static String INSERT = "INSERT INTO employee (id, first_name, last_name, gender, age, city_id) " +
            "VALUES ((?), (?), (?), (?), (?), (?))";
    private static String FIND_BY_ID = "SELECT * FROM employee INNER JOIN city " +
            "ON employee.city_id=city.city_id AND employee.id=(?)";
    private static String UPDATE = "UPDATE employee SET first_name = (?) WHERE id=(?)";
    private static String DELETE = "DELETE FROM employee WHERE id=(?)";


    @Override
    public void add(Employee employee) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     INSERT)) {
            statement.setLong(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setInt(5, employee.getAge());
            statement.setLong(6, employee.getCityId().getCityId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(long id) {
        Employee employee = new Employee();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee.setId(Long.parseLong(resultSet.getString("id")));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setCityId(new City(resultSet.getLong("city_id"),
                        resultSet.getString("city_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long id = Long.parseLong(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                City cityId = new City(resultSet.getLong("city_id"),
                        resultSet.getString("city_name"));
                employees.add(new Employee(id, firstName, lastName, gender, age, cityId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void updateEmployeeById(long id, String name) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(2, id);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(long id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
