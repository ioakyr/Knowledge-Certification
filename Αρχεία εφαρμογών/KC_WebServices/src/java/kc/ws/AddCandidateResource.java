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
@Path("add_candidate")
public class AddCandidateResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddCandidateResource
     */
    public AddCandidateResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.AddCandidateResource
     * @param username
     * @param password
     * @param full_name
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String addCandidate(@QueryParam ("n1") String username, @QueryParam ("n2") String password, @QueryParam ("n3") String full_name) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        String message = "Σφάλμα!";
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        if (username == null || username.isEmpty()) return message;
        String sql = "INSERT INTO users (username, password, full_name, role_id) "
                   + "VALUES ('" + username + "', '" + password + "', '" + full_name + "', 3);";
        try {
            myStatement.executeUpdate(sql);
            message = "Επιτυχής εισαγωγή!";
        }
        catch (SQLException a) {
            message = "Ο χρήστης υπάρχει!"; 
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
}