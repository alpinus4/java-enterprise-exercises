package com.example.lab5.student.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;


@RequestScoped
@Named
public class StudentLogout {

    private final HttpServletRequest request;

    @Inject
    public StudentLogout(HttpServletRequest request) {
        this.request = request;
    }

    @SneakyThrows
    public String logoutAction() {
        request.logout();//Session invalidate can possibly not work with JASPIC.
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

    
}
