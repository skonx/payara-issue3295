/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.trendev.mysecuredrestapi.security;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

/**
 *
 * @author jsie
 */
@DeclareRoles({
    "Special"
})
@FormAuthenticationMechanismDefinition(
        loginToContinue =
        @LoginToContinue(
                loginPage = "/login.html",
                errorPage = "/login-error.html"
        ))
//@BasicAuthenticationMechanismDefinition(realmName = "foo-bar")
@ApplicationScoped
public class DefaultSecurityConfig {
}
