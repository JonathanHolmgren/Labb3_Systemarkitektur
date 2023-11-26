package org.golfshop.interceptor;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Logging
public class LoggingInterCeptor {
    Logger logger = LoggerFactory.getLogger(LoggingInterCeptor.class);
    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        logger.info("Call to method: " + ctx.getMethod().getName() + " in "
                + ctx.getMethod().getDeclaringClass());
        return ctx.proceed();

    }
}
