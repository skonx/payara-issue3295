/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.trendev.mysecuredrestapi;

import java.util.Map;
import java.util.TreeMap;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author jsie
 */
@DeclareRoles({
    "Special"
})
//@FormAuthenticationMechanismDefinition(
//        loginToContinue =
//        @LoginToContinue(
//                useForwardToLogin = false,
//                loginPage = "/login.html",
//                errorPage = "/login-error.html"
//        ))
//realName is not important...
@BasicAuthenticationMechanismDefinition(realmName = "foo-bar")
@ApplicationScoped
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> map = new TreeMap<>();
        map.put("jersey.config.jsonFeature", "JacksonFeature");
        return map;
    }
}
