package com.example.springdatajpatransaction3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;

@Component
@Aspect
public class DatasourceAspect {

    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Object monitorConnection(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before getConnection()" + joinPoint.getSignature()  );

        Object result = joinPoint.proceed();
        if (result instanceof Connection) {
            Connection connection = (Connection) result;
            return createConnectionProxy(connection);
        }

        return result;
    }

    private Connection createConnectionProxy(Connection originalConnection) {
        return (Connection) Proxy.newProxyInstance(
                originalConnection.getClass().getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    if ("commit".equals(method.getName())) {
                        System.out.println("Before commit()");
                    } else if ("close".equals(method.getName())) {
                        System.out.println("Before close()");
                    }
                    return method.invoke(originalConnection, args);
                });
    }
}
