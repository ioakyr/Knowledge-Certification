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
@Path("add_result")
public class AddResultResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddResultResource
     */
    public AddResultResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.AddResultResource
     * @param match_id
     * @param result
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String add_result(@QueryParam ("n1") String match_id, @QueryParam ("n2") String result) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        String message = "Επιτυχής εισαγωγή!";
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        String sql = "INSERT INTO results (match_id, result) VALUES (" + match_id + ", " + result + ")";
        try {
            myStatement.executeUpdate(sql);
        }
        catch (SQLException a) {
            message = "Σφάλμα!";
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
}