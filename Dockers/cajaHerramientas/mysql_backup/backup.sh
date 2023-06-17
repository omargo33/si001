echo "base a respaldar $1";

mysqldump -u root -proot --default-character-set=utf8 --opt --single-transaction --routines --triggers --events --add-drop-database --add-drop-table --complete-insert --tz-utc --databases $1 > /home/backup/$1.sql