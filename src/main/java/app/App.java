package app;

import data.Client;
import logger.Event;
import logger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(String msg, ApplicationContext context) {
        String message = msg.replaceAll(Integer.toString(client.getId()), client.getFullName());
        Event event = context.getBean("event", Event.class);
        event.setMessage(message);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        //create context for test in main
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

    //    App app = (App) ctx.getBean("app"); // id
        App app = ctx.getBean("app", App.class); //id + class

        app.logEvent("New event for user 1", ctx);
        app.logEvent("Other event for user 2", ctx);

        ctx.close();
    }
}
