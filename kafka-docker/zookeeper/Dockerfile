FROM java:openjdk-8-jre

MAINTAINER Tom Van den Bulck (tomvdbulck@gmail.com)

# Based on https://github.com/31z4/zookeeper-docker



ENV ZOO_USER=zookeeper \
    ZOO_CONF_DIR=/conf \
    ZOO_DATA_DIR=/data \
    ZOO_DATA_LOG_DIR=/datalog \
    ZOO_PORT=2181 \
    ZOO_TICK_TIME=2000 \
    ZOO_INIT_LIMIT=5 \
    ZOO_SYNC_LIMIT=2 \
    ZOO_MAX_CLIENT_CNXNS=60

# Add a user and create directories
RUN set -ex; \
    adduser "$ZOO_USER"; \
    mkdir -p "$ZOO_DATA_LOG_DIR" "$ZOO_DATA_DIR" "$ZOO_CONF_DIR";


ARG GPG_KEY=C823E3E5B12AF29C67F81976F5CECB3CB5E9BD2D
ARG DISTRO_NAME=zookeeper-3.4.10


# Download required packages
# Download Apache Zookeeper, verify its PGP signature, untar and clean up
RUN apt-get update && \
    apt-get install -y wget dnsutils gnupg2 && \
    set -ex; \
    wget -q "http://www.apache.org/dist/zookeeper/$DISTRO_NAME/$DISTRO_NAME.tar.gz"; \
    wget -q "http://www.apache.org/dist/zookeeper/$DISTRO_NAME/$DISTRO_NAME.tar.gz.asc"; \
    export GNUPGHOME="$(mktemp -d)"; \
    gpg --keyserver ha.pool.sks-keyservers.net --recv-key "$GPG_KEY" || \
    gpg --keyserver pgp.mit.edu --recv-keys "$GPG_KEY" || \
    gpg --keyserver keyserver.pgp.com --recv-keys "$GPG_KEY"; \
    gpg --batch --verify "$DISTRO_NAME.tar.gz.asc" "$DISTRO_NAME.tar.gz"; \
    tar -xzf "$DISTRO_NAME.tar.gz"; \
    mv "$DISTRO_NAME/conf/"* "$ZOO_CONF_DIR"; \
    rm -rf "$GNUPGHOME" "$DISTRO_NAME.tar.gz" "$DISTRO_NAME.tar.gz.asc"; \
    apt-get clean

WORKDIR $DISTRO_NAME

VOLUME ["$ZOO_DATA_DIR", "$ZOO_DATA_LOG_DIR"]

EXPOSE $ZOO_PORT 2888 3888

ENV PATH=$PATH:/$DISTRO_NAME/bin \
    ZOOCFGDIR=$ZOO_CONF_DIR

ADD scripts/start-zookeeper.sh /usr/bin/start-zookeeper.sh
RUN chmod +x /usr/bin/start-zookeeper.sh

VOLUME ["$ZOO_DATA_DIR", "$ZOO_DATA_LOG_DIR"]


COPY scripts/docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["zkServer.sh", "start-foreground"]


