package com.example.lab5.beer.interceptor;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.security.enterprise.SecurityContext;
import lombok.extern.java.Log;

import java.util.UUID;

import com.example.lab5.beer.Beer;

@Interceptor
@LogMethodCall
@Priority(10)
@Log
public class LogMethodCallInterceptor {

    private final SecurityContext securityContext;

    @Inject
    public LogMethodCallInterceptor(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        UUID id = null;
        if ((context.getParameters()[0] instanceof Beer beer)) {
            id = beer.getId();
        } else if ((context.getParameters()[0] instanceof UUID)) {
            id = (UUID) context.getParameters()[0];
        }

        log.info("Student " + securityContext.getCallerPrincipal().getName() + " invoked method " + context.getMethod().getName() + " with UUID " + id);
        return context.proceed();
    }
}
