
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {

        System.out.println("Задание 1");
        final String user = "postgres";
        final String password = "postgres";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM employee WHERE id = (?)")) {
            statement.setInt(1, 8);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = "Имя: " + resultSet.getString("first_name");
                String lastName = "Фамилия: " + resultSet.getString("last_name");
                String gender = "Пол: " + resultSet.getString("gender");
                int age = resultSet.getInt(5);
                long cityId = resultSet.getLong(6);

                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println("Возраст: " + age);
                System.out.println("ID города: " + cityId);
            }
        }
        System.out.println();

        System.out.println("Задание 2");
        System.out.println();

        EmployeeDao employeeDao=new EmployeeDaoImpl();
        employeeDao.findAll().forEach(System.out::println);

        System.out.println();
        employeeDao.add(new Employee(9,"Vova","Petrov","man",25,new City((2),
                "Санкт-Петербург")));

        System.out.println();
        employeeDao.findAll().forEach(System.out::println);

        System.out.println();
        System.out.println(employeeDao.readById(8));

        System.out.println();
        employeeDao.updateEmployeeById(2,"Anna");
        employeeDao.findAll().forEach(System.out::println);

        System.out.println();
        employeeDao.deleteById(0);
        employeeDao.findAll().forEach(System.out::println);
    }
}
