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
@Path("exam_info")
public class ExamInfoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ExamInfoResource
     */
    public ExamInfoResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.ExamInfoResource
     * @param username
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String exam_info(@QueryParam ("n1") String username) throws ClassNotFoundException, SQLException {
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
        String sql = "SELECT exam_id, exam_datetime, exam_center_name, exam_center_addr, exam_status FROM exams, exam_centers, users WHERE exams.exam_center_id = exam_centers.exam_center_id AND users.user_id = exam_centers.user_id AND username = '" + username + "'";
        rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_id"))
            .add("datetime", rs.getString("exam_datetime"))
            .add("center_name", rs.getString("exam_center_name"))
            .add("center_addr", rs.getString("exam_center_addr"))
            .add("status", rs.getString("exam_status")));
        }
        jsonB = jsonB.add("resposible", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
}