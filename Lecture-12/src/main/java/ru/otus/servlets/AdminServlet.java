package ru.otus.servlets;

import ru.otus.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.otus.Main.dbService;

public class AdminServlet extends HttpServlet{
    private static final String DEFAULT_USER_NAME = "UNKNOWN";
    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("locale", request.getLocale());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("authorize", request.getSession().getAttribute(Constants.ADMIN_AUTH_PARAM));
        pageVariables.put("parameters", dbService.getCacheStatus());

        String login = (String) request
                .getSession()
                .getAttribute(Constants.LOGIN_PARAMETER_NAME);
        pageVariables.put("login", login != null ? login : DEFAULT_USER_NAME);

        return pageVariables;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        if (pageVariables.get(Constants.ADMIN_AUTH_PARAM) == null) {
            response.sendRedirect(request.getContextPath() +"/login");
        } else {
            response.getWriter()
                    .println(TemplateProcessor
                            .instance()
                            .getPage(ADMIN_PAGE_TEMPLATE, pageVariables));

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
