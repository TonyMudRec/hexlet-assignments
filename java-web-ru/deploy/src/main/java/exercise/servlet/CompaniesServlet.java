package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        Stream<String> stream = getCompanies().stream();
        PrintWriter out = response.getWriter();
        if (request.getQueryString() == null || request.getQueryString().isEmpty()) {
            stream.forEach(out::println);
        } else {
            String search = request.getParameter("search");
            AtomicBoolean isFound = new AtomicBoolean(false);
            stream
                    .filter(c -> c.contains(search))
                    .forEach(c -> {
                        out.println(c);
                        isFound.set(true);
                    });
            if (!isFound.get()) {
                out.println("Companies not found");
            }
        }
        // END
    }
}
