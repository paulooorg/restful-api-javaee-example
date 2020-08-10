package io.github.paulooorg.api.infrastructure.metrics;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {
	private Logger logger = LogManager.getLogger(DatabaseHealthCheck.class.getSimpleName());
	
	@Override
	protected Result check() throws Exception {
		EntityManager em = null;
		try {
			em = getConnection();
			int resultSize = em.createNativeQuery("select 1").getResultList().size();
			if (resultSize >= 1) {
				return Result.healthy();
			}
			return Result.unhealthy("failed to execute 'select 1;'");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.unhealthy(e);
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}
	
	private EntityManager getConnection() {
		return Persistence.createEntityManagerFactory("apiPU").createEntityManager();
	}
}
