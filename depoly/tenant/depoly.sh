#! /bin/bash

CURRENT_HOME=`pwd`
SQL_FILES=${CURRENT_HOME}/sql/*.sql

DB_IP=127.0.0.1
DB_PORT=5432
DB_USERNAME=sps
DB_PASSWD=sps
DB_NAME=sps_db
SCHEMA_NAME=$1

#export PGPASSWORD=sps

DATE=`date +%Y-%m-%d`

ImportSQL()
{
    for FILE in ${SQL_FILES}; do
        echo "==> import sql file : ${FILE}"
        #psql -h ${DB_IP} -p ${DB_PORT} -U ${DB_USERNAME} -d ${DB_NAME} < ${FILE}
        psql -f ${FILE} "host=${DB_IP} port=${DB_PORT} user=${DB_USERNAME} password=${DB_PASSWD} dbname=${DB_NAME} options=--search_path=${SCHEMA_NAME}" \
         | grep "ERROR" | tee -a /tmp/${DATE}.log

    done
}


#start
echo "====> start import sql..."
if [ -n "${SCHEMA_NAME}" ]; then
    echo -e "\n ==> create schema : ${SCHEMA_NAME} ..."
    #psql -h ${DB_IP} -p ${DB_PORT} -U ${DB_USERNAME} -d ${DB_NAME} < ${FILE}
#    sudo -u postgres psql -f ${INIT_SQL}  | grep "ERROR" | tee -a /tmp/${DATE}.log
CREATE_SCHEMA=`psql "host=${DB_IP} port=${DB_PORT} user=${DB_USERNAME} password=${DB_PASSWD} dbname=${DB_NAME}" << EOF
    create schema ${SCHEMA_NAME};
EOF`
    echo ${CREATE_SCHEMA}

    echo -e "\n ==> import business sql..."
    if [ -n "${SQL_FILES}" ]; then
        ImportSQL
    fi
else
    echo -e "input schema error!! \n\n"
fi
echo -e "import sql completed...\n\n"

