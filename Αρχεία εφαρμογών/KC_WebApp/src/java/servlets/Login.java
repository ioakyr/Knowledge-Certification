package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Γιάννης Κυρίτσης
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    private static final String BASE_URI = "http://localhost:8080/KC_WebServices/webresources";
    private final Client client = javax.ws.rs.client.ClientBuilder.newClient();
    private final WebTarget webTarget = client.target(BASE_URI);
    String user, pass;
    //web service για είσοδο χρήστη
    public String login(String n1, String n2) {
        WebTarget resource = webTarget.path("login");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
        return build.get(String.class); 
    }
    //web service για επιβεβαίωση εξέτασης
    public String exam_confirm(String n1) {
        WebTarget resource = webTarget.path("exam_confirm");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
        return build.get(String.class); 
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Ένας χρήστης ανά browser
        HttpSession session = request.getSession(false);
        if (session != null) {
            String contextPath = "Home";
                response.sendRedirect(contextPath);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            //login form με css και bootstrap
            out.println("<form name=\"moderator_login_form\" method=\"post\" action=\"\">\n" +
                        "   <div id=\"login\" style=\"position: fixed; top: 50%; left: 50%; width: 25em; margin-top: -16em; margin-left: -12em;\" class=\"mainbox col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2\">\n" +
                        "       <div class=\"panel panel-info\">\n" +
                        "           <div class=\"panel-heading\">\n" +
                        "               <div class=\"panel-title\">Σύνδεση χρήστη</div>\n" +
                        "           </div>\n" +
                        "           <center><img src=\"user.png\" height=\"100\" width=\"100\"></center>\n" +
                        "           <div style=\"padding-top: 30px;\" class=\"panel-body\">\n" +
                        "               <div style=\"margin-bottom: 25px;\" class=\"input-group\">\n" +
                        "                   <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-user\"></i></span>\n" +
                        "                   <input id=\"login-username\" type=\"text\" class=\"form-control\" name=\"username\" placeholder=\"Όνομα χρήστη\" required autofocus>\n" +
                        "               </div>\n" +
                        "               <div style=\"margin-bottom: 25px;\" class=\"input-group\">\n" +
                        "                   <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-lock\"></i></span>\n" +
                        "                   <input id=\"login-password\" type=\"password\" class=\"form-control\" name=\"password\" placeholder=\"Κωδικός πρόσβασης\" required>\n" +
                        "           </div>\n" +
                        "           <button name=\"login\" type=\"submit\" class=\"btn btn-primary btn-lg btn-block\">Σύνδεση</button>\n" +
                        "           </div>");
            //Δήλωση μεταβλητών
            user = request.getParameter("username");
            pass = request.getParameter("password");
            //Έλεγχος αν υπάρχει χρήστης
            if (!login(user, pass).equals("0")) {
                session = request.getSession();  
                session.setAttribute("name", user);
                //Έλεγχος αν ο χρήστης συμμετέχει σε εξέταση ή αν η εξέταση είναι ενεργή ή αν έχει ολοκληρωθεί
                String confirm = exam_confirm(user);
                session.setAttribute("confirm", confirm);
                session.setAttribute("role", login(user, pass));
                String contextPath = "Home";
                response.sendRedirect(contextPath);
            }
            else {
                if (request.getParameter("login") != null) {
                    out.println("<br><div class=\"alert alert-danger\">Λάθος όνομα χρήστη ή κωδικός πρόσβασης.</div>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}