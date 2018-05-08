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
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
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
@Path("log4")
public class Log4Resource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Log4Resource
     */
    public Log4Resource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.Log4Resource
     * @param user_id
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam ("n1") String user_id) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        ResultSet rs;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonB = factory.createObjectBuilder();
        JsonArrayBuilder jsonA = factory.createArrayBuilder();
        String sql = "SELECT logs.question_id, question, logs.question_answer, logs.question_answer_user, datetime FROM logs, questions, exam_matches, results WHERE user_id = " + user_id + " AND exam_matches.match_id = logs.match_id AND logs.question_id = questions.question_id AND logs.match_id = results.match_id";
        rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("question_id"))
            .add("question", rs.getString("question"))
            .add("answer", rs.getString("question_answer"))
            .add("answer", rs.getString("question_answer"))
            .add("answer_user", rs.getString("question_answer_user"))
            .add("datetime", rs.getString("datetime")));
        }
        jsonB = jsonB.add("user", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
}