package com.example.lab5.student.controller;

import java.io.InputStream;
import java.util.UUID;

import com.example.lab5.brewery.BreweryService;
import com.example.lab5.component.DtoFunctionFactory;
import com.example.lab5.student.StudentService;
import com.example.lab5.student.dto.GetStudentResponse;
import com.example.lab5.student.dto.GetStudentsResponse;
import com.example.lab5.student.dto.PostStudentRequest;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import java.util.logging.Level;

@Log
@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentRestController implements StudentController {

    private StudentService studentService;

    private final DtoFunctionFactory dtoFunctionFactory;

    @Inject
    public StudentRestController(DtoFunctionFactory dtoFunctionFactory) {
        this.dtoFunctionFactory = dtoFunctionFactory;
    }

    @EJB
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }


    @GET
    @Override
    public GetStudentsResponse getStudents() {
        return dtoFunctionFactory.studentsToResponse().apply(studentService.findAll());
    }

    @GET
    @Path("/{id}")
    @Override
    public GetStudentResponse getStudent(@PathParam("id") UUID id) {
        return dtoFunctionFactory.studentToResponse().apply(studentService.find(id).orElseThrow(NotFoundException::new));
    }

    @POST
    @Override
    public void postStudent(PostStudentRequest request) {
        try {
            var id = UUID.randomUUID();
            studentService.create(dtoFunctionFactory.requestToStudent().apply(id, request));
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (EJBException ex) {
            //Any unchecked exception is packed into EJBException. Business exception can be itroduced here.
            if (ex.getCause() instanceof IllegalArgumentException) {
                log.log(Level.WARNING, ex.getMessage(), ex);
                throw new BadRequestException(ex);
            }
            throw ex;
        }
    }

    @Override
    public void deleteStudentAvatar(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudentAvatar'");
    }

    @Override
    public byte[] getStudentAvatar(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentAvatar'");
    }

    @Override
    public void putStudentAvatar(UUID id, InputStream avatar) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putStudentAvatar'");
    }
    
}
