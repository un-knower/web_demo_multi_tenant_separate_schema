#! /bin/bash

CURRENT_HOME=`pwd`
SQL_FILES=${CURRENT_HOME}/sql/*.sql

DB_IP=127.0.0.1
DB_PORT=5432
DB_USERNAME=demo_user
DB_PASSWD=demo_pwd
DB_NAME=demo_db
#SCHEMA_NAME=$1

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

CreateSchema()
{
    read -p "input tenant schema name:" SCHEMA_NAME

    if [ ${#SCHEMA_NAME} -le 3 ]; then
        echo "input tenant schema name too short, need more than 3 character"
        return 1
    elif echo "${SCHEMA_NAME}" | grep -q '^[a-z][a-z0-9]\+$' ; then
        echo -e "\n ==> create schema : ${SCHEMA_NAME} ..."
#   psql -h ${DB_IP} -p ${DB_PORT} -U ${DB_USERNAME} -d ${DB_NAME} < ${FILE}
#   sudo -u postgres psql -f ${INIT_SQL}  | grep "ERROR" | tee -a /tmp/${DATE}.log
#CREATE_SCHEMA=`psql "host=${DB_IP} port=${DB_PORT} user=${DB_USERNAME} password=${DB_PASSWD} dbname=${DB_NAME}" << EOF
#    create schema ${SCHEMA_NAME};
#EOF`
#echo ${CREATE_SCHEMA}

psql "host=${DB_IP} port=${DB_PORT} user=${DB_USERNAME} password=${DB_PASSWD} dbname=${DB_NAME}" << EOF
    \set ON_ERROR_STOP TRUE
    create schema ${SCHEMA_NAME};
EOF
        return 0
    else
        echo "invalid input!"
        return 1
    fi

}



#start
CreateSchema

if [ $? -eq 0 ]; then
    echo "====> start import sql..."

    echo -e "\n ==> import business sql..."
    if [ -n "${SQL_FILES}" ]; then
        ImportSQL
    fi
else
    echo -e "input schema error!! \n\n"
fi
echo -e "\nimport sql completed...\n\n"

