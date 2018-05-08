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
@Path("add_exam")
public class AddExamResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddExamResource
     */
    public AddExamResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.AddExamResource
     * @param datetime
     * @param center_id
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String addExam(@QueryParam ("n1") String datetime, @QueryParam ("n2") String center_id) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        String message = "Σφάλμα!";
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        if (datetime == null || datetime.isEmpty()) return message;
        String sql = "INSERT INTO exams (exam_datetime, exam_center_id) "
                   + "VALUES ('" + datetime + "', '" + center_id + "')";
        try {
            myStatement.executeUpdate(sql);
            message = "Επιτυχής εισαγωγή!";
        }
        catch (SQLException a) {
            message = "Μη αποδεκτή εξέταση!"; 
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
}