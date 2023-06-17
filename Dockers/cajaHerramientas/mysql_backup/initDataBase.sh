echo "initDataBase.sh"

apt-get update \
     && apt-get install -y timedatectl \
echo "instal"

## /usr/bin/mysqld_safe --skip-grant-tables
echo "mysqld_safe"

sleep 20
echo "tiempo"

## mysql -u root -proot -e "CREATE DATABASE GS00101"
echo "mysql created"

## mysql -u root -proot < mysql_backup/GS00101.sql
echo "mysql restored"