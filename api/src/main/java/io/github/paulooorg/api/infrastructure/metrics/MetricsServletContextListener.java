package io.github.paulooorg.api.infrastructure.metrics;

import java.util.concurrent.TimeUnit;

import javax.servlet.annotation.WebListener;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.CachedThreadStatesGaugeSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.servlets.MetricsServlet;

@WebListener
public class MetricsServletContextListener extends MetricsServlet.ContextListener {
	public static final MetricRegistry METRIC_REGISTRY = new MetricRegistry();
	
	static {
		METRIC_REGISTRY.register("gc", new GarbageCollectorMetricSet());
		METRIC_REGISTRY.register("threads", new CachedThreadStatesGaugeSet(10, TimeUnit.SECONDS));
		METRIC_REGISTRY.register("memory", new MemoryUsageGaugeSet());
    }
	
	@Override
	protected MetricRegistry getMetricRegistry() {
		return METRIC_REGISTRY;
	}
}
