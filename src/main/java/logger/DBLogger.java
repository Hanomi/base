package logger;

import org.springframework.jdbc.core.JdbcTemplate;

public class DBLogger implements EventLogger {
    private JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void logEvent(Event event) {
        jdbcTemplate.update("INSERT INTO events (id, msg) VALUE (?, ?)", event.getId(), event.getMessage());
    }
}
