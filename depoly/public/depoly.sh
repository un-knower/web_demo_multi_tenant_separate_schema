#! /bin/bash

CURRENT_HOME=`pwd`
INIT_SQL=${CURRENT_HOME}/init.sql
SQL_FILES=${CURRENT_HOME}/sql/*.sql

DB_IP=127.0.0.1
DB_PORT=5432
DB_USERNAME=demo_user
DB_PASSWD=demo_pwd
DB_NAME=demo_db

#export PGPASSWORD=sps

DATE=`date +%Y-%m-%d`

ImportSQL()
{
    for FILE in ${SQL_FILES}; do
        echo "==> import sql file : ${FILE}"
        #psql -h ${DB_IP} -p ${DB_PORT} -U ${DB_USERNAME} -d ${DB_NAME} < ${FILE}
        psql -f ${FILE} "host=${DB_IP} port=${DB_PORT} user=${DB_USERNAME} password=${DB_PASSWD} dbname=${DB_NAME}" | grep "ERROR" | tee -a /tmp/${DATE}.log
    done
}


CreateUser()
{
    echo -e "\n ==> create user : ${DB_USERNAME} ..."

psql "host=${DB_IP} port=${DB_PORT} user=postgres " << EOF
    \set ON_ERROR_STOP TRUE
    create user ${DB_USERNAME} with password '${DB_PASSWD}';
EOF

}


CreateDB()
{
    echo -e "\n ==> create database : ${DB_NAME} ..."

psql "host=${DB_IP} port=${DB_PORT} user=postgres " << EOF
    \set ON_ERROR_STOP TRUE
    create database ${DB_NAME} owner ${DB_USERNAME};
    grant all privileges on database ${DB_NAME} to ${DB_USERNAME};
EOF


}


InitSQL(){

    if [ -n "${INIT_SQL}" ]; then
        echo -e "\n ==> import init sql..."
        #psql -h ${DB_IP} -p ${DB_PORT} -U ${DB_USERNAME} -d ${DB_NAME} < ${FILE}
        sudo -u postgres psql -f ${INIT_SQL}  | grep "ERROR" | tee -a /tmp/${DATE}.log
    fi
}



#start
echo -e "====> start init..."
CreateUser

CreateDB


if [ $? -eq 0 ]; then

    echo -e "\n ==> import business sql..."
    if [ -n "${SQL_FILES}" ]; then
        ImportSQL
    fi
else
    echo -e "init sql not exists!! \n\n"
fi
echo -e "import sql completed...\n\n"
