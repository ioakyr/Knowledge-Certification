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
@Path("exam_enable")
public class ExamEnableResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ExamEnableResource
     */
    public ExamEnableResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.ExamEnableResource
     * @param id
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String exam_enable(@QueryParam ("n1") String id) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        String sql = "UPDATE exams SET exam_status = !exam_status WHERE exam_id = " + id;
        int rs = myStatement.executeUpdate(sql);
        if (rs <= 0) return "Σφάλμα!";
        myStatement.close();
        myConnection.close();
        return "Επιτυχής ενέργεια!";
    }
}