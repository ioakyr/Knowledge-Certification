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
@Path("answer_question")
public class AnswerQuestionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnswerQuestionResource
     */
    public AnswerQuestionResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.AnswerQuestionResource
     * @param match_id
     * @param id
     * @param answer
     * @param user_answer
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String answerQuestion(@QueryParam ("n1") String match_id, @QueryParam ("n2") String id, @QueryParam ("n3") String answer, @QueryParam ("n4") String user_answer) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        String message = "Επιτυχής εισαγωγή!";
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        String sql = "INSERT INTO logs (match_id, question_id, question_answer, question_answer_user) "
                    + "VALUES ('" + match_id + "', '" + id + "', "
                    + "'" + answer + "', "
                    + "'" + user_answer + "')";
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