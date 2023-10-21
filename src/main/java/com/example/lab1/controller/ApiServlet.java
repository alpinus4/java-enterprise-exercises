package com.example.lab1.controller;

import java.io.*;

import com.example.lab1.student.StudentController;
import com.example.lab1.student.StudentService;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(urlPatterns = {
        ApiServlet.Paths.API + "/*"
})
@MultipartConfig(maxFileSize = 200 * 1024)
public class ApiServlet extends HttpServlet {
    private StudentController studentController;

    public static final class Paths {

        public static final String API = "/api";

    }
    public static final class Patterns {

        private static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
        public static final Pattern STUDENTS = Pattern.compile("/students/?");

        public static final Pattern STUDENT = Pattern.compile("/students/(%s)".formatted(UUID.pattern()));

        public static final Pattern STUDENT_AVATAR = Pattern.compile("/students/(%s)/avatar".formatted(UUID.pattern()));

    }

    private String parseRequestPath(HttpServletRequest request) {
        String path = request.getPathInfo();
        path = path != null ? path : "";
        return path;
    }


    private static UUID extractUuid(Pattern pattern, String path) {
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return UUID.fromString(matcher.group(1));
        }
        throw new IllegalArgumentException("No UUID in path.");
    }


    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("PATCH")) {
//            doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }

    public void init() throws ServletException {
        super.init();
        studentController = (StudentController) getServletContext().getAttribute("studentController");
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.STUDENTS.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(studentController.getStudents()));
                return;
            } else if (path.matches(Patterns.STUDENT.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.STUDENT, path);
                response.getWriter().write(jsonb.toJson(studentController.getStudent(uuid)));
                return;
            }  else if (path.matches(Patterns. STUDENT_AVATAR.pattern())) {
                response.setContentType("image/png");//could be dynamic but atm we support only one format
                UUID uuid = extractUuid(Patterns.STUDENT_AVATAR, path);
                byte[] avatar = studentController.getStudentAvatar(uuid);
                response.setContentLength(avatar.length);
                response.getOutputStream().write(avatar);
                return;
            }

        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.STUDENT_AVATAR.pattern())) {
                UUID uuid = extractUuid(Patterns.STUDENT_AVATAR, path);
                studentController.putStudentAvatar(uuid, request.getPart("avatar").getInputStream());
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.STUDENT_AVATAR.pattern())) {
                UUID uuid = extractUuid(Patterns.STUDENT_AVATAR, path);
                studentController.deleteStudentAvatar(uuid);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }



    public void destroy() {
    }
}
