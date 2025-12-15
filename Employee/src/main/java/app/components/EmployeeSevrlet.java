package main.java.app.components;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import main.java.app.components.EmployeeBean;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import com.google.gson.Gson;

@WebServlet("/api/emp/*")
public class EmployeeSevrlet extends HttpServlet {

    EmployeeBean bean = new EmployeeBean();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");

        String id = req.getPathInfo();

        try {
            if (id == null || id.equals("/")) {
                res.getWriter().print(
                        gson.toJson(bean.GEtAllEmployee()));
            } else {
                int idd = Integer.parseInt(id.substring(1));
                res.getWriter().print(
                        gson.toJson(bean.FindByID(idd)));

            }
        } catch (Exception e) {
            System.out.println("Error Shows"+e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
            res.setContentType("application/json");

            try {
                Employeeentry emp = gson.fromJson(req.getReader(),Employeeentry.class);
                bean.AddEmployee(emp);
                res.getWriter().print("New Employee Added");
            } 
            catch(Exception e){
                System.out.println("No New Employee add"+e);
            }
    }
}
