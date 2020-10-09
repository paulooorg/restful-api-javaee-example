FROM fedora:30

ARG DB
ARG USER
ARG PASS
ARG VERSION

ENV INSTALL_DIR /data
ENV PGDATA /data

RUN mkdir -m a=rwx ${INSTALL_DIR}
RUN dnf install -y postgresql-server postgresql-contrib postgresql-devel
RUN chown postgres ${INSTALL_DIR}

USER postgres

RUN initdb -D ${INSTALL_DIR}
RUN echo "listen_addresses='*'" >> ${INSTALL_DIR}/postgresql.conf &&\
    echo "host all  all  0.0.0.0/0  md5" >> ${INSTALL_DIR}/pg_hba.conf
RUN pg_ctl -D /data start -w &&\
    psql --command "CREATE USER ${USER} WITH SUPERUSER PASSWORD '${PASS}';" &&\
    createdb -O ${USER} ${DB}

EXPOSE 5432

CMD ["postgres", "-D", "/data", "-c", "config_file=/data/postgresql.conf"]