package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();
        String query = "SELECT id, title FROM articles ORDER BY id LIMIT 10 OFFSET ?;";
        int articlesOffset = 0;
        String page;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            page = request.getParameter("page");
            if (page != null) {
                articlesOffset = (Integer.parseInt(page) - 1) * 10;
            } else {
                page = "1";
            }
            statement.setInt(1, articlesOffset);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                articles.add(Map.of(
                        "id", rs.getString("id"),
                        "title", rs.getString("title")
                ));
            }

        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.setAttribute("articles", articles);
        request.setAttribute("page", Integer.parseInt(page));
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String basicQuery = "SELECT title, body FROM articles WHERE id = ?;";
        int id = Integer.parseInt(getId(request));
        String title;
        String body;
        try {
            PreparedStatement statement = connection.prepareStatement(basicQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.first();
            if (id > 100) {
                response.sendError(404);
                return;
            }
            title = rs.getString("title");
            body = rs.getString("body");
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.setAttribute("title", title);
        request.setAttribute("body", body);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
