FROM openjdk:11

ARG WILDFLY_USER
ARG WILDFLY_PASS
ARG WILDFLY_VERSION
ARG DS_USER
ARG DS_PASS
ARG DS_DB
ARG DS_NAME

ENV WILDFLY_INSTALL_DIR /opt
ENV WILDFLY_HOME ${WILDFLY_INSTALL_DIR}/wildfly-${WILDFLY_VERSION}
ENV DEPLOYMENT_DIR ${WILDFLY_HOME}/standalone/deployments
ENV CONFIGURATION_DIR ${WILDFLY_HOME}/standalone/configuration
ENV JBOSS_CLI ${WILDFLY_HOME}/bin/jboss-cli.sh

RUN useradd -b /opt -s /bin/sh -d ${WILDFLY_INSTALL_DIR} serveradmin && echo serveradmin:serveradmin | chpasswd

RUN curl -O https://download.jboss.org/wildfly/${WILDFLY_VERSION}/wildfly-${WILDFLY_VERSION}.zip \
    && unzip wildfly-${WILDFLY_VERSION}.zip -d ${WILDFLY_INSTALL_DIR} \
    && rm wildfly-${WILDFLY_VERSION}.zip \
    && chown -R serveradmin:serveradmin /opt \
    && chmod a+x ${WILDFLY_HOME}/bin/standalone.sh \
    && chmod -R a+rw ${WILDFLY_INSTALL_DIR}

USER serveradmin

ENV JAVA_OPTS='-server -Xms1G -Xmx1G -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:ParallelGCThreads=20 -XX:ConcGCThreads=5 -XX:InitiatingHeapOccupancyPercent=70'

RUN ${WILDFLY_HOME}/bin/add-user.sh -u ${WILDFLY_USER} -p ${WILDFLY_PASS} --silent

RUN bash -c '${WILDFLY_HOME}/bin/standalone.sh &' && \
    curl --location --output /tmp/postgresql-42.2.16.jar --url https://jdbc.postgresql.org/download/postgresql-42.2.16.jar && \
    bash -c 'until `${JBOSS_CLI} -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do echo `${JBOSS_CLI} -c ":read-attribute(name=server-state)" 2> /dev/null`; sleep 1; done' && \
    ${JBOSS_CLI} --connect --command="module add --name=org.postgresql --resources=/tmp/postgresql-42.2.16.jar --dependencies=javax.api,javax.transaction.api" && \
    ${JBOSS_CLI} --connect --command="/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-xa-datasource-class-name=org.postgresql.xa.PGXADataSource)" && \
    ${JBOSS_CLI} --connect --command="data-source add \
        --name=${DS_NAME} \
        --jndi-name=java:/datasources/${DS_NAME} \
        --user-name=${DS_USER} \
        --password=${DS_PASS} \
        --driver-name=postgresql \
        --connection-url=jdbc:postgresql://postgres:5432/${DS_DB} \
        --check-valid-connection-sql=select 1 \
        --validate-on-match=false \
        --background-validation=true \
        --background-validation-millis=10000 \
        --enabled=true" && \
    ${JBOSS_CLI} --connect --command=":shutdown" && \
    rm -rf ${WILDFLY_HOME}/standalone/configuration/standalone_xml_history/ ${WILDFLY_HOME}/standalone/log/* && \
    rm -f /tmp/*.jar

ENTRYPOINT ${WILDFLY_HOME}/bin/standalone.sh -b=0.0.0.0 -bmanagement=0.0.0.0

COPY ./wildfly/api.war ${DEPLOYMENT_DIR}

EXPOSE 8080
EXPOSE 9990