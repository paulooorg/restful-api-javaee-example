package io.github.paulooorg.api.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.github.paulooorg.api.infrastructure.exception.BusinessExceptionMapper;
import io.github.paulooorg.api.infrastructure.exception.ValidationExceptionMapper;
import io.github.paulooorg.api.resources.UserResource;

@ApplicationPath("api/v1")
public class JAXRSConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.addAll(getResources());
        classes.addAll(getProviders());
        return classes;
    }
    
    private Set<Class<?>> getResources() {
    	Set<Class<?>> classes = new HashSet<>();
        classes.add(UserResource.class);
        return classes;
    }
    
    private Set<Class<?>> getProviders() {
    	Set<Class<?>> providers = new HashSet<>();
    	providers.add(BusinessExceptionMapper.class);
    	providers.add(ValidationExceptionMapper.class);
        return providers;
    }
}
