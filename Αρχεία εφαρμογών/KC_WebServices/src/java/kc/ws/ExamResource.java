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
import java.util.ArrayList;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Γιάννης Κυρίτσης
 */
@Path("exam")
public class ExamResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ExamResource
     */
    public ExamResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.ExamResource
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String exam() throws ClassNotFoundException, SQLException {
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
        //τυχαίες 5 ερωτήσεις
        Random random = new Random();
        ArrayList<Integer> ids = new ArrayList(); //Τα id των ερωτήσων που υπάρχουν στη βάση
        ArrayList<Integer> numbers = new ArrayList(); //Τυχαίοι αριθμοί από τον πίνακα ids
        String sql1 = "SELECT question_id FROM questions";
            rs = myStatement.executeQuery(sql1);
            while (rs.next()) {
                ids.add(rs.getInt("question_id"));
            }
        //Αποθήκευση 5 τυχαίων id σε λίστα  
        while (numbers.size() < 5) {
            int random_id = random.nextInt(ids.size());
            if (!numbers.contains(random_id))
                numbers.add(random_id);
        }
        //System.out.println(numbers);
        for (int j = 0; j < 5; j++) {
            String sql2 = "SELECT * FROM questions WHERE question_id = " + ids.get(numbers.get(j));
            rs = myStatement.executeQuery(sql2);
            while (rs.next()) {
                jsonA = jsonA.add(factory.createObjectBuilder()
                .add("id", rs.getString("question_id"))
                .add("question", rs.getString("question"))
                .add("answer1", rs.getString("question_answer1"))
                .add("answer2", rs.getString("question_answer2"))
                .add("answer3", rs.getString("question_answer3"))
                .add("answer4", rs.getString("question_answer4"))
                .add("answer", rs.getString("question_answer")));
            }
        }
        jsonB = jsonB.add("questions", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
}