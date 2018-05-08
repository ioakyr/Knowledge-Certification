/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kc.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author Γιάννης Κυρίτσης
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.LoginResource
     * @param username
     * @param password
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces("text/plain")
    public int login(@QueryParam ("n1") String username, @QueryParam ("n2") String password) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost:3306/kc_db?user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        int key = 0;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        String sql1 = "SELECT * FROM users WHERE username = '" 
                    + username + "' AND password = '" 
                    + password + "'";
        ResultSet rs;
        rs = myStatement.executeQuery(sql1);
            
        while (rs.next()) {
            key = rs.getInt("role_id");
        }
        myStatement.close();
        myConnection.close();
        return key;
    }
}