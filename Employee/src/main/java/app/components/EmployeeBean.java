    package main.java.app.components;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBean {

    public List<Employeeentry> GEtAllEmployee() throws SQLException {
        try (var conn = ShopDb.pool.getConnection()) {
            List<Employeeentry> emps = new ArrayList<>();
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("select Emp_id , Emp_name, Emp_salary from employee");
            while (rs.next()) {
                var emp = new Employeeentry(rs);
                emps.add(emp);
            }
            rs.close();
            stmt.close();
            return emps;
        }
    }

    public Employeeentry FindByID(int id) throws Exception {

        Employeeentry emp = null;

        try (var conn = ShopDb.pool.getConnection()) {
            var stmt = conn.prepareStatement("select Emp_id , Emp_name , Emp_salary from employee where Emp_id=?");
            stmt.setInt(1, id);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    emp = new Employeeentry(rs);
                }
            }
        }
        return emp;

    }

    public int AddEmployee(Employeeentry em) throws Exception {

        try (var conn = ShopDb.pool.getConnection()) {
            var pstmt = conn.prepareStatement("insert into employee (Emp_id ,Emp_name ,Emp_salary) values(?,?,?)");
            pstmt.setInt(1, em.getId());
            pstmt.setNString(2, em.getName());
            pstmt.setDouble(3, em.getSalary());

            return pstmt.executeUpdate();
        }

    }
}
