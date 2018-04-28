package aop;

import logger.Event;
import logger.EventLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LoggingAspect {
    private Map<Class<?>, Integer> counter = new HashMap<>();
    private int count;
    private static final int MAX_ALLOWED = 20;

    private EventLogger eventLogger;

    @Autowired
    public void setEventLogger(@Qualifier("fileEventLogger") EventLogger eventLogger) {
        this.eventLogger = eventLogger;
    }

    @Pointcut("execution(* *.logEvent(..)) && within(*.*Logger)")
    private void allLogEventMethods() {}

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if (counter.containsKey(clazz)) {
            counter.put(clazz, counter.get(clazz) + 1);
        } else {
            counter.put(clazz, 1);
        }
    }

    @Around("execution(* *.logEvent(..)) && within(*.ConsoleEventLogger) && args(event)")
    public void aroundMethod(ProceedingJoinPoint pJoinPoint, Object event) {
        if (count < MAX_ALLOWED) {
            try {
                pJoinPoint.proceed(new Object[] {event});
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            eventLogger.logEvent((Event) event);
        }
    }

    public void print() {
        counter.forEach((c, i) -> System.out.println(c.getSimpleName() + " " + i));
    }
}
