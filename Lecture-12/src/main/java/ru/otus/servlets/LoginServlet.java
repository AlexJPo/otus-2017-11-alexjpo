package ru.otus.servlets;

import ru.otus.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PAGE_TEMPLATE = "login.html";
    private static final String LOGIN_VARIABLE_NAME = "";

    private static final String LOGIN = "admin";
    private static final String PASSWORD = "123";

    private String login;

    private static String getPage(String login) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(LOGIN_VARIABLE_NAME, login == null ? "" : login);

        return TemplateProcessor
                .instance()
                .getPage(LOGIN_PAGE_TEMPLATE, pageVariables);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestLogin = request.getParameter(Constants.LOGIN_PARAMETER_NAME);
        String requestPassword = request.getParameter(Constants.PASSWORD_PARAMETER_NAME);

        if (requestLogin != null && requestPassword != null && isAdmin(requestLogin, requestPassword)) {
            saveToVariable(requestLogin);
            saveToSession(request, requestLogin);

            response.sendRedirect(request.getContextPath() + "/admin");
        }

        String page = getPage(login);
        response.getWriter().println(page);

        setOK(response);
    }

    private void saveToCookie(HttpServletResponse response, String requestLogin) {
        response.addCookie(new Cookie("L12.1-login", requestLogin));
    }

    private void saveToServlet(HttpServletRequest request, String requestLogin) {
        request.getServletContext().setAttribute("login", requestLogin);
        request.getServletContext().setAttribute(Constants.ADMIN_AUTH_PARAM, true);
    }

    private void saveToSession(HttpServletRequest request, String requestLogin) {
        request.getSession().setAttribute("login", requestLogin);
        request.getSession().setAttribute(Constants.ADMIN_AUTH_PARAM, true);
    }


    private boolean isAdmin(String login, String pass) {
        return login.equalsIgnoreCase(LOGIN) && pass.equals(PASSWORD);
    }

    private void saveToVariable(String requestLogin) {
        login = requestLogin != null ? requestLogin : login;
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
