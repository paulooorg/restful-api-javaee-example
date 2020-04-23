package io.github.paulooorg.api.infrastructure.cdi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {
    @Produces
    public Logger create(InjectionPoint injectionPoint) {
        return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}
