package io.github.paulooorg.api.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.github.paulooorg.api.infrastructure.authentication.AuthenticationFilter;
import io.github.paulooorg.api.infrastructure.authorization.AuthorizationFilter;
import io.github.paulooorg.api.infrastructure.exception.ApiExceptionMapper;
import io.github.paulooorg.api.infrastructure.exception.BusinessExceptionMapper;
import io.github.paulooorg.api.infrastructure.exception.ValidationExceptionMapper;
import io.github.paulooorg.api.infrastructure.json.CustomObjectMapper;
import io.github.paulooorg.api.infrastructure.metrics.JAXRSMetricsFilter;
import io.github.paulooorg.api.resources.ClientResource;
import io.github.paulooorg.api.resources.LoanResource;
import io.github.paulooorg.api.resources.LoginResource;
import io.github.paulooorg.api.resources.ModalityResource;
import io.github.paulooorg.api.resources.SimulationResource;
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
        classes.add(ModalityResource.class);
        classes.add(ClientResource.class);
        classes.add(LoanResource.class);
        classes.add(SimulationResource.class);
        classes.add(LoginResource.class);
        return classes;
    }
    
    private Set<Class<?>> getProviders() {
    	Set<Class<?>> providers = new HashSet<>();
    	providers.add(BusinessExceptionMapper.class);
    	providers.add(ValidationExceptionMapper.class);
    	providers.add(ApiExceptionMapper.class);
    	providers.add(CustomObjectMapper.class);
    	providers.add(AuthenticationFilter.class);
    	providers.add(AuthorizationFilter.class);
    	providers.add(JAXRSMetricsFilter.class);
        return providers;
    }
}
