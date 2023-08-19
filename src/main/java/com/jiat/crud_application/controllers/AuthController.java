package com.jiat.crud_application.controllers;

import com.jiat.crud_application.dto.AuthResponseDTO;
import com.jiat.crud_application.entity.User;
import com.jiat.crud_application.services.UserService;
import com.jiat.crud_application.util.JWTTokenUtil;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;

@Singleton
@Path("/")
public class AuthController {
 @Inject
    JWTTokenUtil jwtTokenUtil;
 @Inject
    UserService userService;

 @Path("/auth")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response auth(@FormParam("name") String name,@FormParam("password") String password){

    User user = userService.getUserByNameAndPassword(name,password);
         if(user!=null) {
             String token = jwtTokenUtil.generateAccessToken(user);
             String refreshToken = jwtTokenUtil.generateRefreshToken(user);
             Date expireDateFromToken = jwtTokenUtil.getExpiredDateFromToken(token);

             AuthResponseDTO dto = new AuthResponseDTO(token, refreshToken, expireDateFromToken.toString());
             return Response.ok().entity(dto).build();
         }else{
             return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid Name or Password").build();
     }
 }

 @Path("refresh-token")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response refreshToken(@FormParam("refreshToken") String refreshToken){
     User user = userService.getUserByNameAndPassword(jwtTokenUtil.getNameFromToken(refreshToken), jwtTokenUtil.getPasswordFromToken(refreshToken));
     if(!jwtTokenUtil.validateToken(refreshToken,user)){
         return Response.status(Response.Status.UNAUTHORIZED).entity("INVALID REFRESH TOKEN").build();
     }else{
         String newAccessToken = jwtTokenUtil.generateAccessToken(user);
         Date expireDateFromNewAccessToken = jwtTokenUtil.getExpiredDateFromToken(newAccessToken);
         AuthResponseDTO dto = new AuthResponseDTO(newAccessToken,refreshToken,expireDateFromNewAccessToken.toString());

         return  Response.ok().entity(dto).build();
     }
 }
}
