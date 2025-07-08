/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import sun.jvm.hotspot.utilities.soql.SOQLException;

/**
 *
 * @author Sanjana
 */
@WebServlet(name = "AllUsers", urlPatterns = {"/AllUsers"})
public class AllUsers extends HttpServlet {

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_5?useSSL=false", "root", "Sanjana123!$");
            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM `user`");

            ArrayList<User> users = new ArrayList<>();

            while (rs.next()) {

                User u = new User();
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setMobile(rs.getString("mobile"));
                u.setCountry(rs.getString("country"));

                users.add(u);

            }

            Gson gson = new Gson();
            String json = gson.toJson(users);
            response.getWriter().write(json);

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
            response.getWriter().write("Data loading failed");
        }

    }

}
