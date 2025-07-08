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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Sanjana
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        
        Gson gson = new Gson();
        User u = gson.fromJson(request.getReader(), User.class);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_5?useSSL=false", "root", "Sanjana123!$");
            Statement s = c.createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM `user` WHERE `mobile`='" + u.getMobile() + "' &&"
                    + "`password` = '" + u.getPassword() + "'");
            
            if (rs.next()) {
                
                User ul = new User();
                ul.setFirstName(rs.getString("first_name"));
                ul.setFirstName(rs.getString("last_name"));
                ul.setFirstName(rs.getString("mobile"));
                ul.setFirstName(rs.getString("country"));
                
                request.getSession().setAttribute("user", ul);
                response.getWriter().write("Success");

                //System.out.println(request.getSession().getAttribute("user"));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
            response.getWriter().write("login faild!");
            
        }
        
    }

}
