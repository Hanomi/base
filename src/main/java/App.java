public class App {
    private Client client;
    private EventLogger eventLogger;

    public void logEvent(String msg) {
        String message = msg.replaceAll(Integer.toString(client.getId()), client.getFullName());
        eventLogger.logEvent(message);
    }

    public static void main(String[] args) {
        App app = new App();

        app.client = new Client(1, "Bender Rodriguez");
        app.eventLogger = new EventLogger();

        app.logEvent("New event for user 1");
    }
}
