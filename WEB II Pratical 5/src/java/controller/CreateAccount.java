/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import javax.json.Json;
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
@WebServlet(name = "CreateAccount", urlPatterns = {"/CreateAccount"})
public class CreateAccount extends HttpServlet {

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");

        //JSON -> Java
        Gson gson = new Gson();
        
        //User user = gson.fromJson(request.getReader(), User.class);
        JsonObject user = gson.fromJson(request.getReader(), JsonObject.class);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_5?useSSL=false", "root", "Sanjana123!$");
            Statement s = c.createStatement();
            s.executeUpdate("INSERT INTO "
                    + "`user`(`mobile`,`first_name`,`last_name`,`password`,`country`) "
                    + "VALUES('" + user.get("mobile").getAsString() + "','" + user.get("firstName").getAsString() + "','" + user.get("lastName").getAsString() + "','" + user.get("password").getAsString() + "','" + user.get("country").getAsString() + "')");

            response.getWriter().write("Success");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error");
        }
    }

}
