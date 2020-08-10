package io.github.paulooorg.api.infrastructure.metrics;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.codahale.metrics.Timer;

@Provider
public class JAXRSMetricsFilter implements ContainerRequestFilter, ContainerResponseFilter {
	private Timer.Context timingContext;
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		timingContext.stop();
		String statusCode = Integer.valueOf(responseContext.getStatus()).toString();
		MetricsServletContextListener.METRIC_REGISTRY.meter(createMetricName("jaxrs.mark", statusCode, requestContext)).mark();
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		MetricsServletContextListener.METRIC_REGISTRY.meter(createMetricName("jaxrs.mark", "", requestContext)).mark();
		timingContext = MetricsServletContextListener.METRIC_REGISTRY.timer(createMetricName("jaxrs.timer", "", requestContext)).time();
	}
	
	private String createMetricName(String prefix, String suffix, ContainerRequestContext requestContext) {
		StringBuilder metricNameBuilder = new StringBuilder();
		metricNameBuilder.append(prefix).append(".");
		metricNameBuilder.append(requestContext.getMethod()).append(".");
		metricNameBuilder.append(requestContext.getUriInfo().getPath());
		if (!suffix.trim().isEmpty()) {
			metricNameBuilder.append(".").append(suffix);
		}
		return metricNameBuilder.toString();
	}
}
