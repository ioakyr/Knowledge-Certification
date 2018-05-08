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

/**
 * REST Web Service
 *
 * @author Γιάννης Κυρίτσης
 */
@Path("exam_confirm")
public class ExamConfirmResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Exam_confirmResource
     */
    public ExamConfirmResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.ExamConfirmResource
     * @param username
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @GET
    @Produces("text/plain")
    public int exam_confirm(@QueryParam ("n1") String username) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        ResultSet rs;
        int match_id = 0;
        int key = 0;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        //Εύρεση match_id
        String sql1 = "SELECT match_id FROM exam_matches, users WHERE username = '" + username + "' AND exam_matches.user_id = users.user_id";
        rs = myStatement.executeQuery(sql1);
        while (rs.next()) {
            match_id = rs.getInt("match_id");
        }
        //Έλεγχος αν υπάρχει διαθέσιμη εξέταση και ο χρήστης τι δικαιούται
        String sql2 = "SELECT exams.exam_id, exam_status, username FROM exams, exam_matches, users "
                    + "WHERE users.user_id = exam_matches.user_id "
                    + "AND exams.exam_id = exam_matches.exam_id AND exam_status = 1 AND username = '" 
                    + username + "'";
        rs = myStatement.executeQuery(sql2);
        while (rs.next()) {
            key = match_id; //Αποθήκευση match_id ως key
        }
        //Έλεγχος αν έχει κάνει ήδη την εξέταση
        String sql3 = "SELECT * FROM logs WHERE match_id = '" + match_id + "'";
        rs = myStatement.executeQuery(sql3);
        while (rs.next()) {            
            key = 0;
        }
        myStatement.close();
        myConnection.close();        
        return key;
    }
}