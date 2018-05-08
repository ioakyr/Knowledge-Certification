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
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Γιάννης Κυρίτσης
 */
@Path("centers_available")
public class CentersAvailableResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CentersAvailableResource
     */
    public CentersAvailableResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.CentersAvailableResource
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String centerss_available() throws ClassNotFoundException, SQLException {
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
        String sql = "SELECT * FROM exam_centers WHERE user_id IS NULL";
        rs = myStatement.executeQuery(sql);
        while (rs.next()) {
            jsonA = jsonA.add(factory.createObjectBuilder()
            .add("id", rs.getString("exam_center_id"))
            .add("center_name", rs.getString("exam_center_name"))
            .add("center_addr", rs.getString("exam_center_addr")));
        }
        jsonB = jsonB.add("centers", jsonA);
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
    }
}