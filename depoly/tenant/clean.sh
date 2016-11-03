#! /bin/bash


DB_IP=127.0.0.1
DB_PORT=5432
DB_USERNAME=sps
DB_PASSWD=sps
DB_NAME=demo_db
SCHEMA_NAME=$1



#start
echo "====> start drop schema..."
if [ -n "${SCHEMA_NAME}" ]; then
    echo -e "\n ==> create schema : ${SCHEMA_NAME} ..."
DROP_SCHEMA=`psql "host=${DB_IP} port=${DB_PORT} user=${DB_USERNAME} password=${DB_PASSWD} dbname=${DB_NAME}" << EOF
    drop schema ${SCHEMA_NAME} cascade;
EOF`
    echo ${DROP_SCHEMA}

else
    echo -e "input schema error!! \n\n"
fi
echo -e "import sql completed...\n\n"