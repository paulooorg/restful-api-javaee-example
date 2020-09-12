package io.github.paulooorg.api.infrastructure.metrics;

import java.util.Properties;

import com.codahale.metrics.ehcache.InstrumentedEhcache;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.CacheDecoratorFactory;

public class CustomInstrumentedCacheDecoratorFactory extends CacheDecoratorFactory {
	@Override
	public Ehcache createDecoratedEhcache(Ehcache cache, Properties properties) {
		return InstrumentedEhcache.instrument(MetricsServletContextListener.METRIC_REGISTRY, cache);
	}

	@Override
	public Ehcache createDefaultDecoratedEhcache(Ehcache cache, Properties properties) {
		return InstrumentedEhcache.instrument(MetricsServletContextListener.METRIC_REGISTRY, cache);
	}
}
