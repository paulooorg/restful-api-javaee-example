package io.github.paulooorg.api.infrastructure.cdi;

import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import java.sql.SQLException;

@ApplicationScoped
public class LiquibaseProducer {
    @Resource(lookup = "java:/datasources/apiDS")
    private DataSource dataSource;

    @Produces
        @LiquibaseType
        public CDILiquibaseConfig createConfig() {
            CDILiquibaseConfig config = new CDILiquibaseConfig();
            //TODO: Wildfly error db/changelog/changelog-master.yaml
            config.setChangeLog("db/changelog/changelog-master.xml");
            return config;
        }

        @Produces
        @LiquibaseType
        public DataSource createDataSource() throws SQLException {
        return dataSource;
    }

    @Produces
    @LiquibaseType
    public ResourceAccessor create() {
        return new ClassLoaderResourceAccessor(getClass().getClassLoader());
    }
}
