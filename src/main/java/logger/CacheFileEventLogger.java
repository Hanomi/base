package logger;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(int cacheSize, String filename) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() >= cacheSize) {
            cache.forEach(super::logEvent);
            cache.clear();
        }
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            cache.forEach(super::logEvent);
        }
        System.out.println("destroy");
    }
}
