/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kc.ws;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * REST Web Service
 *
 * @author Γιάννης Κυρίτσης
 */
@Path("add_candidate_file")
public class AddCandidateFromFileResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddCandidateFromFileResource
     */
    public AddCandidateFromFileResource() {
    }

    /**
     * Retrieves representation of an instance of kc.ws.AddCandidateFromFileResource
     * @param file
     * @return an instance of java.lang.String
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.FileNotFoundException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String add_candidate_file(@QueryParam ("n1") String file) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        //TODO return proper representation object
        String myDatabase = "jdbc:mysql://localhost/kc_db?useUnicode=yes&characterEncoding=UTF-8&user=root&password=220895";
        Connection myConnection;
        Statement myStatement;
        Class.forName("com.mysql.jdbc.Driver");
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
        if (file == null) return "Σφάλμα!";
        //Read from excel
        FileInputStream fis;
        XSSFWorkbook wb;
        int rows, columns = 0, tmp;
        XSSFSheet sheet;
        XSSFRow row;
        XSSFCell cell;
        String [][] candidate_info;
        try {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException a) {
            return "Το αρχείο δεν υπάρχει!";
        }
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheetAt(1);
        rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > columns) columns = tmp;
            }
        }
        candidate_info = new String[rows][columns];
        for(int r = 0; r < rows; r++) {
            row = sheet.getRow(r);
            if(row != null) {
                for(int c = 0; c < columns; c++) {
                    cell = row.getCell(c);
                    if(cell != null) {
                        candidate_info[r][c] = new DataFormatter().formatCellValue(cell);
                    }
                }
            }
        }
        //Εισαγωγή υποψηφίων - Admininstrator
        for (String[] info : candidate_info) {    
            String sql = "INSERT INTO users (username, password, full_name, role_id)"
                       + "VALUES ('" + info[0] + "', '" + info[1] + "', '" + info[2] + "', 3)";
            myStatement.executeUpdate(sql);
        }
        myStatement.close();
        myConnection.close();
        return "Εισαγωγή επιτυχής!";
    }
}