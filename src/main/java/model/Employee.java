package model;

import java.util.Objects;

public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private City cityId;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String gender, int age, City cityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.cityId = cityId;
    }

    public Employee(long id, String firstName, String lastName, String gender, int age, City cityId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.cityId = cityId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName)
                && Objects.equals(gender, employee.gender)
                && Objects.equals(cityId, employee.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, age, cityId);
    }

    @Override
    public String toString() {
        return "id = " + id +", firstName = " + firstName +", lastName = " + lastName +", gender = " + gender  +
                ", age = " + age +", cityId = " + cityId ;
    }
}