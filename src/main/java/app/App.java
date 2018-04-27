package app;

import data.Client;
import logger.Event;
import logger.EventLogger;
import logger.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> eventLoggers;

    @Autowired
    public App(Client client, @Qualifier("cacheFileEventLogger") EventLogger defaultLogger, Map<EventType, EventLogger> eventLoggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.eventLoggers = eventLoggers;
    }

    public void logEvent(EventType type, Event event, String msg) {
        String message = msg.replaceAll(Integer.toString(client.getId()), client.getFullName());
        event.setMessage(message);

        EventLogger logger = eventLoggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        //create context for test in main
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

    //    App app = (App) ctx.getBean("app"); // id
        App app = ctx.getBean("app", App.class); //id + class

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
    }
}
