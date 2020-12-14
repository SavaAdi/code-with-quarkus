package com.adisava.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@PerformanceLogEvent
@Interceptor
public class PerformanceLogEventInterceptor {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PerformanceLogEventInterceptor.class);

    static List<Event> events = new ArrayList<>();

    @AroundInvoke
    public Object logEvent(InvocationContext ctx) throws Exception {
        long start = System.currentTimeMillis();
        String methodName = ctx.getMethod().getName();
        events.add(new Event(methodName,
                Arrays.deepToString(ctx.getParameters())));
        Object obj = ctx.proceed();

        LOGGER.info(methodName + " method executed in: " + (System.currentTimeMillis() - start));
        events.forEach(event -> {
           LOGGER.info("Event : " + event); // if you want to show all invocations of this method
        });
        return ctx.proceed();
    }

}
