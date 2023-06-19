package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper om = new ObjectMapper();
        return om.readValue(new File("src/main/resources/users.json"), List.class);
        // END
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        // BEGIN
        PrintWriter out = response.getWriter();
        ObjectMapper om = new ObjectMapper();
        StringBuilder sb = new StringBuilder("""
                 <!DOCTYPE html>
                            <html lang=\"ru\">
                                <head>
                                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"\s
                                rel="stylesheet"\s
                                integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"\s
                                crossorigin="anonymous">
                                    <meta charset=\"UTF-8\">
                                    <title>Example application | Users</title>
                                </head>
                                <body>
                """);

        List<Map<String, String>> users = om.convertValue(getUsers(), new TypeReference<>() {
        });
        String fullname;
        String id;
        for (Map<String, String> user : users) {
            fullname = user.get("firstName") + " " + user.get("lastName");
            id = user.get("id");
            sb.append("<p>")
                    .append(id)
                    .append(" ")
                    .append("<a href='/users/")
                    .append(id)
                    .append("'>")
                    .append(fullname)
                    .append("</a>")
                    .append("</p>");
        }
        sb.append("""
                    </body>
                </html>
                """);
        out.write(sb.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        // BEGIN
        PrintWriter out = response.getWriter();
        ObjectMapper om = new ObjectMapper();
        List<Map<String, String>> users = om.convertValue(getUsers(), new TypeReference<>() {
        });
        int counter = 0;
        StringBuilder sb = new StringBuilder("""
                 <!DOCTYPE html>
                            <html lang=\"ru\">
                                <head>
                                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"\s
                                rel="stylesheet"\s
                                integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"\s
                                crossorigin="anonymous">
                                    <meta charset=\"UTF-8\">
                                    <title>Example application | Users</title>
                                </head>
                                <body>
                """);
        for (Map<String, String> user : users) {
            if (user.get("id").equals(id)) {
                for (String key : user.keySet()) {
                    sb.append("<p>")
                            .append(key)
                            .append(" : ")
                            .append(user.get(key))
                            .append("</p>");
                }
                counter++;
            }
        }
        if (counter == 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        sb.append("""
                    </body>
                </html>
                """);
        out.write(sb.toString());
        // END
    }
}
