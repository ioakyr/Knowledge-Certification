/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kc.ws;

import java.sql.Connection;
import java.sql.DriverManager;
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
@Path("users_match")
public class UsersMatchResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsersMatchResource
     */
    public UsersMatchResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.UsersMatchResource
     * @param exam_id
     * @param user_id
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String users_match(@QueryParam ("n1") String exam_id, @QueryParam ("n2") String user_id) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        if (exam_id == null || user_id == null) return "Σφάλμα!";
        String sql = "INSERT INTO exam_matches (exam_id, user_id) VALUES (" + exam_id + ", " + user_id + ")";
        myStatement.executeUpdate(sql);
        myStatement.close();
        myConnection.close();
        return "Ενέργεια επιτυχής!";
    }
}