package org.golfshop.interceptor;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@LogCreateProduct
public class LogCreateProductInterceptor {

    Logger logger = LoggerFactory.getLogger(LogCreateProductInterceptor.class);

    @AroundInvoke
    public Object logCreateProduct(InvocationContext ctx) throws Exception{
        logger.info("Product has benn created: " + Arrays.toString(ctx.getParameters()) + ctx.getMethod().getName() +
                ctx.getMethod().getDeclaringClass());

        return ctx.proceed();
    }
}

//+ " in class "
//                + ctx.getMethod().getDeclaringClass()