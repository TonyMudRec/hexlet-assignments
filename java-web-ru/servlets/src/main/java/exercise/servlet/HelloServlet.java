package exercise.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class HelloServlet extends HttpServlet {
    // BEGIN
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name") == null ? "Guest" : req.getParameter("name");
        String result = "Hello, " + name + "!";
        res.setContentType("text/plain");
        res.getWriter().write(result);
    }
    // END
}
