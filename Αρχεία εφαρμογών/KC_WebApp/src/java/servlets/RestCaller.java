package servlets;


import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Γιάννης Κυρίτσης
 */
class RestCaller {
    private static final String BASE_URI = "http://localhost:8080/KC_WebServices/webresources";
    private Client client = javax.ws.rs.client.ClientBuilder.newClient();
    private WebTarget webTarget = client.target(BASE_URI);
    ArrayList<Integer> unanswered = new ArrayList();
    int i;
    public JSONObject exam() {
        WebTarget resource = webTarget.path("exam");
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        return new JSONObject(build.get(String.class));
        //JSONObject json=new JSONObject(build.get(String.class));
        //System.out.println("get lessons return test ----> "+build.get(String.class));
        //return new JSONObject(build.get(String.class));
    }
    public RestCaller() {
        for (int j = 0; j < 5; j++)
            unanswered.add(j);
        i = 0;
    }/*
     public static void main(String args[]) {
        RestCaller  rc = new RestCaller();
        rc.i++;
        System.out.println(rc.i);
    }*/
}
