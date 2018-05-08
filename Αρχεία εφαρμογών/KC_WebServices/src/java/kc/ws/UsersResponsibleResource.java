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
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Γιάννης Κυρίτσης
 */
@Path("users_resp")
public class UsersResponsibleResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsersResponsibleResource
     */
    public UsersResponsibleResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.UsersResponsibleResource
     * @param center_id
     * @param user_id
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String users_resp(@QueryParam ("n1") String center_id, @QueryParam ("n2") String user_id) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        ResultSet rs;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        if (center_id == null || user_id == null) return "Σφάλμα!";
        String sql1 = "UPDATE exam_centers SET user_id = " + user_id + " WHERE exam_center_id = " + center_id; 
        myStatement.executeUpdate(sql1);
        String sql2 = "SELECT role_id FROM users WHERE user_id = " + user_id;
        rs = myStatement.executeQuery(sql2);
        while (rs.next()) {
            if (rs.getString("role_id").equals("3")) {
                sql2 = "UPDATE users SET role_id = 2 WHERE user_id = " + user_id; 
                myStatement.executeUpdate(sql2);
            }
        }
        myStatement.close();
        myConnection.close();
        return "Ενέργεια επιτυχής!";
    }
}