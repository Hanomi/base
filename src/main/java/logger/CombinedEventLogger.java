package logger;

import java.util.Set;

public class CombinedEventLogger implements EventLogger {
    private Set<EventLogger> loggers;

    public Set<EventLogger> getLoggers() {
        return loggers;
    }

    public CombinedEventLogger(Set<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(f -> f.logEvent(event));
    }
}
