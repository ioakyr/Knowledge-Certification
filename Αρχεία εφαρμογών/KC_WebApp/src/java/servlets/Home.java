package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import org.json.JSONObject;

/**
 *
 * @author Γιάννης Κυρίτσης
 */
@WebServlet(urlPatterns = {"/Home"})
public class Home extends HttpServlet {
    private static final String BASE_URI = "http://localhost:8080/KC_WebServices/webresources";
    private final Client client = javax.ws.rs.client.ClientBuilder.newClient();
    private final WebTarget webTarget = client.target(BASE_URI);
    ArrayList<Integer> unanswered; //Λίστα με τις αναπάντητες ερωτήσεις
    Integer i, result; //Αριθμός ερώτησης κάθε φορά και counter σε διαφορετικές περιπτώσεις, αποτέλεσμα εξέτασης
    JSONObject myJson; //Αποθηκεύεται το αποτέλεσμα της exam και άλλων web services (σε διαφορετικές περιπτώσεις)
    //int counter = 10;
    String answer, user_answer; //Aπάντηση ερώτησης και απάντηση χρήστη
    JSONObject x; //Μεταβλητή η οποία χειρίζεται τα πεδία ενός JSONObject 
    //Κλήση web services
    public JSONObject exam() {
        WebTarget resource = webTarget.path("exam");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class));
    }
    public JSONObject exam_info(String n1) {
        WebTarget resource = webTarget.path("exam_info");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject centers_available() {
        WebTarget resource = webTarget.path("centers_available");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject centers() {
        WebTarget resource = webTarget.path("centers");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject users_available_match() {
        WebTarget resource = webTarget.path("users_available_match");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public JSONObject users_available_resp() {
        WebTarget resource = webTarget.path("users_available_resp");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class)); 
    }
    public String users_match(String n1, String n2) {
        WebTarget resource = webTarget.path("users_match");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String users_resp(String n1, String n2) {
        WebTarget resource = webTarget.path("users_resp");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String exam_enable(String n1) {
        WebTarget resource = webTarget.path("exam_enable");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String answer_question(String n1, String n2, String n3, String n4) {
        WebTarget resource = webTarget.path("answer_question");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        resource = resource.queryParam("n3", n3);
        resource = resource.queryParam("n4", n4);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_candidate_file(String n1) {
        WebTarget resource = webTarget.path("add_candidate_file");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_question_file(String n1) {
        WebTarget resource = webTarget.path("add_question_file");
        resource = resource.queryParam("n1", n1);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_candidate(String n1, String n2, String n3) {
        WebTarget resource = webTarget.path("add_candidate");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        resource = resource.queryParam("n3", n3);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_question(String n1, String n2, String n3, String n4, String n5, String n6) {
        WebTarget resource = webTarget.path("add_question");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        resource = resource.queryParam("n3", n3);
        resource = resource.queryParam("n4", n4);
        resource = resource.queryParam("n5", n5);
        resource = resource.queryParam("n6", n6);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_center(String n1, String n2) {
        WebTarget resource = webTarget.path("add_center");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_exam(String n1, String n2) {
        WebTarget resource = webTarget.path("add_exam");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    public String add_result(String n1, int n2) {
        WebTarget resource = webTarget.path("add_result");
        resource = resource.queryParam("n1", n1);
        resource = resource.queryParam("n2", n2);
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return build.get(String.class); 
    }
    //Ερωτήσεις από web service - 5 τυχαίες
    public void test(int i, PrintWriter out) {
                out.println("<form action=\"\" method=\"post\" style=\"text-align: center; margin-top: 10%;\">");
                out.println("<h4>" + (i + 1) + ". " + x.get("question") + "</h4>");
                out.println("<div style=\"margin-left: 45%; width: 200px; text-align: left;\">");
                out.println("<input style=\"float: left;\"checked type=\"radio\" name=\"option\" value=\"" + x.get("answer1") + "\">" + x.get("answer1") + "</input><br>");
                out.println("<input type=\"radio\" name=\"option\" value=\"" + x.get("answer2") + "\">" + x.get("answer2") + "</input><br>");
                out.println("<input type=\"radio\" name=\"option\" value=\"" + x.get("answer3") + "\">" + x.get("answer3") + "</input><br>");
                out.println("<input type=\"radio\" name=\"option\" value=\"" + x.get("answer4") + "\">" + x.get("answer4") + "</input><br>");
                out.println("</div>");
                out.println("<br><input name=\"confirm\" type=\"submit\" value=\"Επιβεβαίωση\">");
                out.println("<input name=\"next\" type=\"submit\" value=\"Επόμενη\">");
                out.println("</form>");
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
        //response.setHeader("Refresh", "1");
        //Τρέχον session
        HttpSession session = request.getSession(false);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
        response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility 
        request.setCharacterEncoding("UTF-8"); //Default charset
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Home</title>");
            //out.println("<script type=\"text/javascript\" src=\"script.js\"></script>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
                        "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");
            //Έλεγχος session
            if(session != null) {
                //Navigation bar
                out.println("<nav class=\"navbar navbar-inverse\">\n" +
                            "   <div class=\"container-fluid\">\n" +
                            "       <div class=\"navbar-header\">\n" +
                            "           <a class=\"navbar-brand\">-Εξέταση Γνώσεων-</a>\n" +
                            "       </div>\n" +
                            "       <ul class=\"nav navbar-nav\">\n" +
                            "       <!--<li><a href=\"moderator.php\">Αρχική</a></li>-->");
                            //Εμφάνιση ενεργειών διαχειριστή
                            if (session.getAttribute("role").equals("1")) {
                                out.println("       <li><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"\">Εισαγωγή χρήστη<span class=\"caret\"></span></a>\n" +
                                            "         <ul style=\"min-width: 150px;\" class=\"dropdown-menu\">\n" +
                                            "           <li><a href=\"?option=users_form\">Από φόρμα</a></li>\n" +
                                            "           <li><a href=\"?option=users_file\">Από excel </a></li>\n" +
                                            "         </ul></li>\n" +
                                            "       <li><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"\">Εισαγωγή ερώτησης<span class=\"caret\"></span></a>\n" + 
                                            "         <ul style=\"min-width: 168px;\" class=\"dropdown-menu\">\n" +
                                            "           <li><a href=\"?option=questions_form\">Από φόρμα</a></li>\n" +
                                            "           <li><a href=\"?option=questions_file\">Από excel </a></li>\n" +
                                            "         </ul></li>\n" +
                                            "       <li><a href=\"?option=centers\">Εισαγωγή κέντρου</a></li>\n" + 
                                            "       <li><a href=\"?option=responsibles\">Πιστοποίηση υπεύθυνου κέντρου</a></li>\n" + 
                                            "       <li><a href=\"?option=exams\">Εισαγωγή εξέτασης</a></li>\n");
                            }
                            out.println("       <!--<li><a href=\"?option=categories\">Διαχείριση κατηγοριών</a></li>-->\n" +
                                        "       <!--<li><a href=\"?option=category_tree\">Σχεδιάγραμμα κατηγοριών</a></li>-->\n" +
                                        "       </ul>\n" +
                                        "       <ul class=\"nav navbar-nav navbar-right\">\n" +
                                        "           <li><a><span class=\"glyphicon glyphicon-user\"></span> Γεια σου, " + session.getAttribute("name") + "</a></li>\n" +
                                        "           <li><a href=\"Logout\"><span class=\"glyphicon glyphicon-log-out\"></span> Έξοδος</a></li>\n" +
                                        "       </ul>\n" +
                                        "   </div>\n" +
                                        "</nav>");
                if (session.getAttribute("role").equals("3")) { //Εξεταζόμενος
                    //Έλεγχος αν ο χρήστης συμμετέχει σε εξέταση ή αν η εξέταση είναι ενεργή ή έχει ολοκληρωθεί
                    String confirm = (String) session.getAttribute("confirm");
                    if (confirm.equals("0")) {
                        out.println("<div style=\"text-align: center; margin-top: 10%;\"><i>Δεν είναι διαθέσιμη κάποια εξέταση για εσάς!</i></div>");
                        return;
                    }
                    //out.println(counter);
                    //Αποθήκευση μεταβλητών ως session attributes προς αποφυγή μοιράσματος αυτών σε παράλληλη εκτέλεση
                    i = (Integer) session.getAttribute("question");
                    myJson = (JSONObject) session.getAttribute("exam");
                    unanswered = (ArrayList<Integer>) session.getAttribute("unanswered");
                    x = (JSONObject) session.getAttribute("answer");
                    //Αποτέλεσμα
                    result = (Integer) session.getAttribute("result");
                    //Αρχικοποίηση μεταβλητών
                    if (i == null) {
                        unanswered = new ArrayList();
                        for (int j = 0; j < 5; j++)
                            unanswered.add(j);
                        session.setAttribute("unanswered", unanswered);
                        i = 0;
                        session.setAttribute("question", i);
                        myJson = exam();
                        session.setAttribute("exam", myJson);
                        result = 0;
                        session.setAttribute("result", result);
                    }       
                    //response.setIntHeader("Refresh", 1);
                    //out.println("<p id=\"demo\">00:30</p>");

                    //Λειτουργίες
                    //confirm button - όχι βέλτιστος κώδικας
                    if (request.getParameter("confirm") != null) {
                        user_answer = request.getParameter("option");
                        answer = (String) x.get("answer");
                        String question_id = (String) x.get("id");
                        answer_question(confirm, question_id, answer, user_answer);
                        System.out.println(answer + ": " + user_answer + " --> " + session.getAttribute("name"));
                        //Έλεγχος απάντησης
                        if (answer.equals(user_answer)) { 
                            result++;
                            session.setAttribute("result", result);  
                        }
                        if (i < 4 && unanswered.size() > 0) {
                            unanswered.remove(i);
                            if (unanswered.isEmpty()) {
                                //out.println("Ok");
                                System.out.println(result);
                                add_result(confirm, result);
                                session.invalidate();
                                response.sendRedirect("Login");
                                //Χρειάζεται για να γίνει η ανακατεύθυνση
                                return;
                            }
                            else {
                                while (!unanswered.contains(i + 1) && i < 4) {
                                    i++;
                                    session.setAttribute("question", i);
                                }
                                if (i > 3) {
                                    i = unanswered.get(0);
                                    session.setAttribute("question", i);
                                }
                                else {
                                    i++;
                                    session.setAttribute("question", i);
                                }
                            }
                        }
                        else {
                            if (i == 4) 
                                unanswered.remove(i);
                            if (!unanswered.isEmpty()) {
                                i = unanswered.get(0); //Πρώτο στοιχείο λίστας  
                                session.setAttribute("question", i);
                            }
                            else {
                                //out.println("Ok");
                                System.out.println(result);
                                add_result(confirm, result);
                                session.invalidate();
                                response.sendRedirect("Login");
                                //Χρειάζεται για να γίνει η ανακατεύθυνση
                                return;
                            }
                        }      
                    }
                    //next button
                    if (request.getParameter("next") != null) {
                        while (!unanswered.contains(i + 1) && i < 4) {
                            i++;
                            session.setAttribute("question", i);
                        }
                        if (i < 4) {
                          i++;
                          session.setAttribute("question", i);
                        }
                        else {
                            i = unanswered.get(0);
                            session.setAttribute("question", i);
                        }    
                    }
                    //Κλήση της test για την εξέταση του χρήστη
                    x = (JSONObject) myJson.getJSONArray("questions").get(i);
                    session.setAttribute("answer", x);
                    test(i, out);
                    System.out.println(unanswered + " --> " + session.getAttribute("name"));/*
                    Timer timer = new Timer();
                    TimerTask vodTimer = new TimerTask() {
                        @Override
                        public void run() {
                            //counter = 10;
                            counter--;
                            //out.println("<script> alert(counter); </script>");
                            if (counter < 0)
                                timer.cancel();
                        }
                    };

                    timer.schedule(vodTimer, 1000, 1000);*/
                }
                else if (session.getAttribute("role").equals("2")) { //Υπεύθυνος εξεταστικού κέντρου
                    if (request.getParameter("status") != null)
                        exam_enable(request.getParameter("status"));
                    if (request.getParameter("match") != null)
                        users_match(request.getParameter("exams"), request.getParameter("match"));
                    String user = (String) session.getAttribute("name");
                    out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                    out.println("<th>Ημερομηνία και ώρα</th><th>Όνομα κέντρου</th><th>Διεύθυνση</th>" + 
                                "<th>Κατάσταση</th><th>Ενέργεια</th>");
                    out.println("<tr>");
                    //Κατάσταση
                    String status;
                    //Αρχικοποίηση
                    myJson = exam_info(user);
                    i = 0;
                    while (i < myJson.getJSONArray("resposible").length()) { //Μέγεθος JSONArray
                        x = (JSONObject) myJson.getJSONArray("resposible").get(i);
                        //Εκτέλεση ενέργειας web service 
                        //Μετατροπή 0-1 σε κατάλληλο string
                        if (x.getString("status").equals("1"))
                            status  = "Ενεργή";
                        else
                            status  = "Ανενεργή";
                        out.println("<td>" + x.getString("datetime") + "</td>");
                        out.println("<td>" + x.getString("center_name") + "</td>");
                        out.println("<td>" + x.getString("center_addr") + "</td>");
                        out.println("<td>" + status + "</td>");
                        out.println("<td style=\"width: 20%\"><form method=\"post\" action=\"\">");
                        if (status.equals("Ανενεργή"))
                            out.println("<button type=\"submit\" name=\"status\" id=\"linkButton\" value=\"" + x.getString("id") + "\"><span class=\"glyphicon glyphicon-off\" style=\"color: green\"></span>Ενεργοποίηση</button>");
                        else
                            out.println("<button type=\"submit\" name=\"status\" id=\"linkButton\" value=\"" + x.getString("id") + "\"><span class=\"glyphicon glyphicon-off\" style=\"color: red\"></span>Απενεργοποίηση</button>");
                        out.println("</form></td></tr>");
                        i++;
                    }
                    out.println("</table>");
                    //Αρχικοποίηση μεταβλητών και δήλωση βοηθητικών
                    i = 0;
                    JSONObject myJson2 = users_available_match(), y;
                    out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                    out.println("<th>username</th><th>password</th><th>Ονοματεπώνυμο</th><th>Επιλογή Κέντρου</th><th>Ενέργεια</th>"); 
                    out.println("<tr>");
                    while (i < myJson2.getJSONArray("users").length()) { //Μέγεθος JSONArray
                        int j = 0;
                        y = (JSONObject) myJson2.getJSONArray("users").get(i);
                        out.println("<td>" + y.getString("username") + "</td>");
                        out.println("<td>" + y.getString("password") + "</td>");
                        out.println("<td>" + y.getString("full_name") + "</td>");
                        out.println("<td>");
                        out.println("<form method=\"post\" action=\"\"><select name=\"exams\">");
                        while (j < myJson.getJSONArray("resposible").length()) { //Μέγεθος JSONArray
                            x = (JSONObject) myJson.getJSONArray("resposible").get(j);
                            out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                            j++;
                        }
                        out.println("</select></td>");
                        out.println("<td style=\"width: 20%\">");
                        if (j != 0) //Αν υπάρχει εξέταση με το επιλεγμένο κέντρο
                            out.println("<button type=\"submit\" name=\"match\" id=\"linkButton\" value=\"" + y.getString("id") + "\"><span class=\"glyphicon glyphicon-ok\" style=\"color: green\"></span>Αντιστοίχιση</button>");
                        out.println("</form></td></tr>");
                        i++;
                    }
                    out.println("</table>");
                }
                else { //Διαχειριστής
                    if ("users_file".equals(request.getParameter("option"))) {
                        /*out.println("<form method=\"post\" action=\"\">");
                        out.println("<button type=\"submit\" name=\"add_candidate_file\" id=\"linkButton\" value=\"1\"><span class=\"glyphicon glyphicon-ok-sign\" style=\"color: green\"></span>Εισαγωγή εξεταζομένων</button>");
                        out.println("<button type=\"submit\" name=\"add_question_file\" id=\"linkButton\" value=\"1\"><span class=\"glyphicon glyphicon-ok-sign\" style=\"color: green\"></span>Εισαγωγή ερωτήσεων</button>");
                        out.println("</form>");
                        */
                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 340px;\"><h4>Εισαγωγή εξεταζόμενου από αρχείο</h4>\n" +
                                    "<form method=\"post\" action=\"\">\n" +
                                    "<input name=\"file\" type=\"file\" accept=\".xlsx\">\n" +
                                    "<input type=\"submit\"></div></form>");  
                        if (request.getParameter("file") != null) {
                            add_candidate_file(getServletContext().getRealPath("/") + request.getParameter("file"));
                        }
                    }
                    if ("questions_file".equals(request.getParameter("option"))) {
                       out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 340px;\"><h4>Εισαγωγή εξεταζόμενου από αρχείο</h4>\n" +
                                    "<form method=\"post\" action=\"\">\n" +
                                    "<input name=\"file\" type=\"file\" accept=\".xlsx\">\n" +
                                    "<input type=\"submit\"></div></form>");  
                        if (request.getParameter("file") != null) {
                            add_question_file(getServletContext().getRealPath("/") + request.getParameter("file"));
                        } 
                    }
                    if ("questions_form".equals(request.getParameter("option"))) {
                        //Εισαγωγή ερώτησης
                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 340px;\"><h4>Εισαγωγή ερώτησης</h4>");
                        out.println("<form action=\"\" method=\"post\">\n" +
                                    "Ερώτηση:<br>\n" +
                                    "<input required type=\"text\" name=\"question\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Πιθανή απάντηση 1:<br>\n" +
                                    "<input required type=\"text\" name=\"answer1\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Πιθανή απάντηση 2:<br>\n" +
                                    "<input required type=\"text\" name=\"answer2\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Πιθανή απάντηση 3:<br>\n" +
                                    "<input required type=\"text\" name=\"answer3\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Πιθανή απάντηση 4:<br>\n" +
                                    "<input required type=\"text\" name=\"answer4\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Απάντηση:<br>\n" +
                                    "<input required type=\"text\" name=\"answer\" value=\"\">\n" +
                                    "<br><br>\n" +
                                    "<button name=\"add_question\"type=\"submit\" class=\"btn btn-success\">Εισαγωγή</button>" + 
                                    "</form>");
                        //Εκτέλεση και εμφάνιση αποτελέσματος του web service
                        if (request.getParameter("add_question") != null)
                            out.println("<br>" + add_question(request.getParameter("question"), 
                                                               request.getParameter("answer1"), 
                                                               request.getParameter("answer2"),
                                                               request.getParameter("answer3"), 
                                                               request.getParameter("answer4"),
                                                               request.getParameter("answer")) + "</div>");
                        }
                        if ("users_form".equals(request.getParameter("option"))) {
                            //Εισαγωγή εξεταζόμενου
                            out.print("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 340px;\"><h4>Εισαγωγή εξεταζόμενου</h4>");
                            out.println("<form action=\"\" method=\"post\">\n" +
                                        "Όνομα χρήστη:<br>\n" +
                                        "<input required type=\"text\" name=\"username\" value=\"\">\n" +
                                        "<br>\n" +
                                        "Κωδικός πρόσβασης:<br>\n" +
                                        "<input required type=\"text\" name=\"password\" value=\"\">\n" +
                                        "<br>\n" +
                                        "Ονοματεπώνυμο: <br>\n" +
                                        "<input required type=\"text\" name=\"full_name\" value=\"\">\n" +
                                        "<br><br>\n" +
                                        "<button name=\"add_candidate\"type=\"submit\" class=\"btn btn-success\">Εισαγωγή</button>" + 
                                        "</form>");
                            //Εκτέλεση και εμφάνιση αποτελέσματος του web service
                            if (request.getParameter("add_candidate") != null)
                                out.println("<br><b>" + 
                                            add_candidate(request.getParameter("username"), 
                                                          request.getParameter("password"), 
                                                          request.getParameter("full_name")) + "</b></div>");
                    }
                    if ("centers".equals(request.getParameter("option"))) {
                        //Εισαγωγή εξεταστικού κέντρου
                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 340px;\"><h4>Εισαγωγή εξετ/κού κέντρου</h4>");
                        out.println("<form action=\"\" method=\"post\">\n" +
                                    "Όνομα κέντρου:<br>\n" +
                                    "<input required type=\"text\" name=\"center_name\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Διεύθυνση Κέντρου:<br>\n" +
                                    "<input required type=\"text\" name=\"center_addr\" value=\"\">\n" +
                                    "<br><br>\n" +
                                    "<button name=\"add_center\"type=\"submit\" class=\"btn btn-success\">Εισαγωγή</button>" + 
                                    "</form>");
                        //Εκτέλεση και εμφάνιση αποτελέσματος του web service
                        if (request.getParameter("add_center") != null)
                            out.println("<br><b>" + 
                                        add_center(request.getParameter("center_name"), 
                                                   request.getParameter("center_addr")) + "</b></div>");
                    }
                    //Πιστοποίηση
                    if (request.getParameter("resp") != null)
                        users_resp(request.getParameter("centers"), request.getParameter("resp"));
                    if ("responsibles".equals(request.getParameter("option"))) {
                        out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                        out.println("<th>Όνομα κέντρου</th><th>Διεύθυνση</th>");
                        out.println("<tr>");
                        //Αρχικοποίηση
                        myJson = centers_available();
                        i = 0;
                        while (i < myJson.getJSONArray("centers").length()) { //Μέγεθος JSONArray
                            x = (JSONObject) myJson.getJSONArray("centers").get(i);
                            //Εκτέλεση ενέργειας web service 
                            out.println("<tr><td>" + x.getString("center_name") + "</td>");
                            out.println("<td>" + x.getString("center_addr") + "</td>");
                            i++;
                        }
                        out.println("</table>");
                        //Αρχικοποίηση μεταβλητών και δήλωση βοηθητικών
                        i = 0;
                        JSONObject myJson2 = users_available_resp(), y;
                        out.println("<table class=\"table table-striped table-bordered table-hover table-condensed\">");
                        out.println("<th>username</th><th>password</th><th>Ονοματεπώνυμο</th><th>Επιλογή Κέντρου</th><th>Ενέργεια</th>"); 
                        out.println("<tr>");
                        while (i < myJson2.getJSONArray("users").length()) { //Μέγεθος JSONArray
                            int j = 0;
                            y = (JSONObject) myJson2.getJSONArray("users").get(i);
                            out.println("<td>" + y.getString("username") + "</td>");
                            out.println("<td>" + y.getString("password") + "</td>");
                            out.println("<td>" + y.getString("full_name") + "</td>");
                            out.println("<td>");
                            out.println("<form method=\"post\" action=\"\"><select name=\"centers\">");
                            while (j < myJson.getJSONArray("centers").length()) { //Μέγεθος JSONArray
                                x = (JSONObject) myJson.getJSONArray("centers").get(j);
                                out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                                j++;
                            }
                            out.println("</select></td>");
                            out.println("<td style=\"width: 20%;\">");
                            if (j != 0) //Αν υπάρχουν διαθέσιμα εξεταστικά κέντρα - χωρίς υπεύθυνο 
                                out.println("<button type=\"submit\" name=\"resp\" id=\"linkButton\" value=\"" + y.getString("id") + "\"><span class=\"glyphicon glyphicon-ok\" style=\"color: green\"></span>Πιστοποίηση</button>");
                            out.println("</form></td></tr>");
                            i++;
                        }
                        out.println("</table>");
                    }
                    if ("exams".equals(request.getParameter("option"))) {
                        int j = 0;
                        myJson = centers();
                        //Εισαγωγή εξέτασης
                        out.println("<br><br><br><center><div class=\"well\" style=\"text-align: center; width: 340px;\"><h4>Εισαγωγή εξέτασης</h4>");
                        out.println("<form action=\"\" method=\"post\">\n" +
                                    "Ημερομηνία και ώρα:<br>\n" +
                                    "<input required type=\"datetime-local\" name=\"datetime\" value=\"\">\n" +
                                    "<br>\n" +
                                    "Εξεταστικό κέντρο:<br>\n");
                                    out.println("<select name=\"centers\">");
                                    while (j < myJson.getJSONArray("centers").length()) { //Μέγεθος JSONArray
                                        x = (JSONObject) myJson.getJSONArray("centers").get(j);
                                        out.println("<option value=\"" + x.getString("id") + "\">" + x.getString("center_name") + "</option>");
                                        j++;
                            }
                            out.println("</select></td>");
                                    //"<input required type=\"text\" name=\"center\" value=\"\">\n" +
                                    out.println("<br><br>\n" +
                                    "<button name=\"add_exam\"type=\"submit\" class=\"btn btn-success\">Εισαγωγή</button>" + 
                                    "</form>");
                        //Εκτέλεση και εμφάνιση αποτελέσματος του web service
                        if (request.getParameter("add_exam") != null)
                            out.println("<br><b>" + 
                                        add_exam(request.getParameter("datetime"), 
                                                   request.getParameter("centers")) + "</b></div>");
                    }
                }  
            }
            //Αν δεν υπάρχει session (ή έχει λήξει)
            else {
                String contextPath = "/KC_WebApp/Login";
                response.sendRedirect(contextPath);
                //Χρειάζεται για να γίνει η ανακατεύθυνση
                return;
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