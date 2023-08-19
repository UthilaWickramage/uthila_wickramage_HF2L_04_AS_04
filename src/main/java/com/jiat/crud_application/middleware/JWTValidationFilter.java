package com.jiat.crud_application.middleware;

import com.jiat.crud_application.entity.User;
import com.jiat.crud_application.services.UserService;
import com.jiat.crud_application.util.JWTTokenUtil;
import io.fusionauth.jwt.JWTExpiredException;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(1)
@PreMatching
public class JWTValidationFilter implements ContainerRequestFilter {
    @Inject
    private JWTTokenUtil tokenUtil;
    @Inject
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String path = containerRequestContext.getUriInfo().getPath();
        if (path.equals("auth")|| path.equals("refresh-token") || path.equals("search_employee") || path.equals("view_employee")) {
            return;
        }

        if (containerRequestContext.getHeaders().getFirst("Authorization") == null) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        } else {
            String tokenFromAuthorization = containerRequestContext.getHeaders().getFirst("Authorization").split(" ")[1];
            try {
                User user = userService.getUserByNameAndPassword(tokenUtil.getNameFromToken(tokenFromAuthorization),tokenUtil.getPasswordFromToken(tokenFromAuthorization));
                if (!tokenUtil.validateToken(tokenFromAuthorization, user)) {
                    containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } catch (JWTExpiredException | NullPointerException ex) {
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("OH NO! TOKEN EXPIRED!").build());
            } catch (Exception e) {
                e.printStackTrace();
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }
}
