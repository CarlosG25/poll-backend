FROM mariadb:10.2

# make the "en_US.UTF-8" locale so ~postgres~ mysql/mariadb will be utf-8 enabled by default
RUN apt-get update \
&& apt-get install -y locales \
&& rm -rf /var/lib/apt/lists/* \
&& localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8

ENV LANG en_US.utf8

# Config
ADD my.cnf /etc/mysql
ADD mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf
