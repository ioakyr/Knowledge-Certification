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
@Path("add_question")
public class AddQuestionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddQuestionResource
     */
    public AddQuestionResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.AddQuestionResource
     * @param question
     * @param answer1
     * @param answer2
     * @param answer3
     * @param answer4
     * @param answer
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String add_question(@QueryParam ("n1") String question, @QueryParam ("n2") String answer1, @QueryParam ("n3") String answer2, @QueryParam ("n4") String answer3, @QueryParam ("n5") String answer4, @QueryParam ("n6") String answer) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        String message = "Σφάλμα!";
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        if (question == null || question.isEmpty()) return message;
        String sql = "INSERT INTO questions (question, question_answer1, question_answer2, question_answer3, "
                       + "question_answer4, question_answer) "
                       + "VALUES ('" + question + "', '" + answer1 + "', '" + answer2 + "', '" + answer3 + "', '" + answer4 + "', '" + answer + "')";
        try {
            myStatement.executeUpdate(sql);
            message = "Επιτυχής εισαγωγή!";
        }
        catch (SQLException a) {
            message = "Η ερώτηση υπάρχει!"; 
        }
        myStatement.close();
        myConnection.close();
        return message;
    }
}