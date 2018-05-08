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
@Path("add_center")
public class AddExamCenterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddExamCenterResource
     */
    public AddExamCenterResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.AddExamCenterResource
     * @param center_name
     * @param center_addr
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String addExamCenter(@QueryParam ("n1") String center_name, @QueryParam ("n2") String center_addr) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        String message = "Σφάλμα!";
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        if (center_name == null || center_name.isEmpty()) return message;
        String sql = "INSERT INTO exam_centers (exam_center_name, exam_center_addr) "
                   + "VALUES ('" + center_name + "', '" + center_addr + "')";
        try {
            myStatement.executeUpdate(sql);
            message = "Επιτυχής εισαγωγή!";
        }
        catch (SQLException a) {
            message = "Το κέντρο υπάρχει!"; 
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
}