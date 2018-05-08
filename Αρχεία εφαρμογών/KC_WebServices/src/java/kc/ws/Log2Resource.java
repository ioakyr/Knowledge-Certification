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
@Path("log2")
public class Log2Resource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Log2Resource
     */
    public Log2Resource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.Log2Resource
     * @param center_id
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam ("n1") String center_id) throws ClassNotFoundException, SQLException {
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
        String sql = "SELECT exams.exam_id, exam_datetime, exam_center_name, exam_center_addr, username, result FROM exams, exam_centers, users, results, exam_matches WHERE users.user_id = exam_matches.user_id AND results.match_id = exam_matches.match_id AND exams.exam_id = exam_matches.exam_id AND exam_centers.exam_center_id = exams.exam_center_id AND exams.exam_center_id = " + center_id;
        rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_id"))
            .add("datetime", rs.getString("exam_datetime"))
            .add("center_name", rs.getString("exam_center_name"))
            .add("center_addr", rs.getString("exam_center_addr"))
            .add("username", rs.getString("username"))
            .add("result", rs.getString("result")));
        }
        jsonB = jsonB.add("results", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
}