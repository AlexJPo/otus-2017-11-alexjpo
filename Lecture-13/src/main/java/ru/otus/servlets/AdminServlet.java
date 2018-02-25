package ru.otus.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.otus.AppConfiguration;
import ru.otus.orm.dataset.AddressDataSet;
import ru.otus.orm.dataset.PhoneDataSet;
import ru.otus.orm.dataset.UserDataSet;
import ru.otus.orm.implementations.DBServiceImpl;
import ru.otus.service.DataBaseService;
import ru.otus.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configurable
public class AdminServlet extends HttpServlet {
    private static final String DEFAULT_USER_NAME = "UNKNOWN";
    private static final String ADMIN_PAGE_TEMPLATE = "admin.html";

    @Autowired
    private DBServiceImpl dbService;
    @Autowired
    private ApplicationContext context;

    public void init() {
        context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        dbService = (DBServiceImpl)context.getBean("dbServiceBean");
        dbService.prepareData();
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
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
            response.setContentType("text/html;charset=utf-8");
            response.getWriter()
                    .println(TemplateProcessor
                            .instance()
                            .getPage(ADMIN_PAGE_TEMPLATE, pageVariables));

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
