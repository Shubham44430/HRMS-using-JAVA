package main.java.app.components;
import java.sql.*;
import java.sql.ResultSet;

public class Employeeentry {

    private int id;
    private String name;
    private int salary;

    public Employeeentry(ResultSet ad) throws SQLException {

        id = ad.getInt("Emp_id");
        name = ad.getString("Emp_name");
        salary = ad.getInt("Emp_salary");

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

}