/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kc.ws;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Γιάννης Κυρίτσης
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(kc.ws.AddCandidateFromFileResource.class);
        resources.add(kc.ws.AddCandidateResource.class);
        resources.add(kc.ws.AddExamCenterResource.class);
        resources.add(kc.ws.AddExamResource.class);
        resources.add(kc.ws.AddQuestionFromFileResource.class);
        resources.add(kc.ws.AddQuestionResource.class);
        resources.add(kc.ws.AddResultResource.class);
        resources.add(kc.ws.AnswerQuestionResource.class);
        resources.add(kc.ws.CentersAvailableResource.class);
        resources.add(kc.ws.CentersResource.class);
        resources.add(kc.ws.ExamConfirmResource.class);
        resources.add(kc.ws.ExamEnableResource.class);
        resources.add(kc.ws.ExamInfoResource.class);
        resources.add(kc.ws.ExamResource.class);
        resources.add(kc.ws.Log1Resource.class);
        resources.add(kc.ws.Log2Resource.class);
        resources.add(kc.ws.Log3Resource.class);
        resources.add(kc.ws.Log4Resource.class);
        resources.add(kc.ws.LoginResource.class);
        resources.add(kc.ws.UsersAvailableMatchResource.class);
        resources.add(kc.ws.UsersAvailableResponsibleResource.class);
        resources.add(kc.ws.UsersMatchResource.class);
        resources.add(kc.ws.UsersResponsibleResource.class);
    } 
}