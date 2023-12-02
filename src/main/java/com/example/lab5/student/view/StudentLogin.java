package com.example.lab5.student.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@RequestScoped
@Named
@Log
public class StudentLogin {

    private final HttpServletRequest request;

    private final SecurityContext securityContext;

    private final FacesContext facesContext;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;

    @Inject
    public StudentLogin(
            HttpServletRequest request,
            @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext,
            FacesContext facesContext
    ) {
        this.request = request;
        this.securityContext = securityContext;
        this.facesContext = facesContext;
    }

    @SneakyThrows
    public void loginAction() {
        Credential credential = new UsernamePasswordCredential(login, new Password(password));
        AuthenticationStatus status = securityContext.authenticate(request, extractResponseFromFacesContext(),
                withParams().credential(credential));
        facesContext.responseComplete();
    }

    private HttpServletResponse extractResponseFromFacesContext() {
        return (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }


    
}
