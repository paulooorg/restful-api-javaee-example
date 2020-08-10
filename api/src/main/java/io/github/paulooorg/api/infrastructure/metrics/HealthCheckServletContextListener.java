package io.github.paulooorg.api.infrastructure.metrics;

import javax.servlet.annotation.WebListener;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;

@WebListener
public class HealthCheckServletContextListener extends HealthCheckServlet.ContextListener {
	public static final HealthCheckRegistry HEALTH_CHECK_REGISTRY = new HealthCheckRegistry();
	
	static {
        HEALTH_CHECK_REGISTRY.register("database", new DatabaseHealthCheck());
    }
	
	@Override
	protected HealthCheckRegistry getHealthCheckRegistry() {
		return HEALTH_CHECK_REGISTRY;
	}
}
