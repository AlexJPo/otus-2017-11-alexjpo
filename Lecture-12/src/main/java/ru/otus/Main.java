package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import ru.otus.orm.dataset.AddressDataSet;
import ru.otus.orm.dataset.PhoneDataSet;
import ru.otus.orm.dataset.UserDataSet;
import ru.otus.orm.implementations.DBServiceImpl;
import ru.otus.servlets.*;

public class Main {
    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";
    public static DBServiceImpl dbService = new DBServiceImpl();
    private static int timeout = 2000;

    public static void main(String[] args) throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new LoginServlet()), "/login");
        context.addServlet(AdminServlet.class, "/admin");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        prepareData();


       /* Thread thread = new Thread(() -> {
            while (true) {*/
                dbService.readAddress(1);
                dbService.read(1);
                dbService.readPhone(1);

                dbService.readAddress(2);
                dbService.readPhone(3);
             /*   try {
                    Thread.sleep(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();*/

        server.start();
        server.join();
    }

    private static void prepareData() {
        AddressDataSet addressDataSet = new AddressDataSet();
        addressDataSet.setStreet("some address for user");
        dbService.saveAddress(addressDataSet);

        UserDataSet userDataSet = new UserDataSet();
        userDataSet.setAge(25);
        userDataSet.setName("user1");
        dbService.save(userDataSet);

        PhoneDataSet phoneDataSet = new PhoneDataSet();
        phoneDataSet.setUserId(userDataSet);
        phoneDataSet.setPhone("55134");
        dbService.savePhone(phoneDataSet);
    }
}
