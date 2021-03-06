package logger;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Event {
    private int id;
    private String message;
    private Date date;
    private DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.id = (int) (Math.random() * 100) + 1;
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }

    public static boolean isDay() {
        LocalTime localTime = LocalTime.now();
        return localTime.getHour() >= 8 && localTime.getHour() < 17;
    }
}
