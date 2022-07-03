package io.github.gabrielgfw.quarkussocial.rest;

import io.github.gabrielgfw.quarkussocial.domain.model.User;
import io.github.gabrielgfw.quarkussocial.rest.dto.CreateUserRequest;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.h2.command.ddl.CreateUser;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST          // Define o método HTTP para a entidade.
    @Transactional // Abre uma conexão com o bnaco de dados.
    public Response createUser(CreateUserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.persist();
        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers() {
        PanacheQuery<User> query = User.findAll();
        return Response.ok(query.list()).build();
    }

    @GET
    @Path("{id}")
    public Response listUserById(@PathParam("id") Long id) {
        User user = User.findById(id);
        return Response.ok(user).build();
    }

    @DELETE
    @Transactional
    @Path("{id}") // Permite passar informação através do path da URL - /delete/1
    public Response deleteUser(@PathParam("id") Long id) {
        User user = User.findById(id);
        // Validando a existência do ID informado:
        if(user != null) {
            user.delete();
            return Response.ok().build();
        }
        // Retornando um 404 caso o usuário não tenha sido encontrado.
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData) {
        User user = User.findById(id);

        if(user != null) {
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
