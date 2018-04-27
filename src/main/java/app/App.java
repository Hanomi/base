package app;

import data.Client;
import logger.EventLogger;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(String msg) {
        String message = msg.replaceAll(Integer.toString(client.getId()), client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {

    }
}
