package app;

import data.Client;
import logger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(String msg) {
        String message = msg.replaceAll(Integer.toString(client.getId()), client.getFullName());
    //    eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        //create context for test in main
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

    //    App app = (App) ctx.getBean("app"); // id
        App app = ctx.getBean("app", App.class); //id + class

        app.logEvent("New event for user 1");
        app.logEvent("Other event for user 2");
    }
}
