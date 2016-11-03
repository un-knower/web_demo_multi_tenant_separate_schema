#! /bin/bash

echo "====> drop database"
sudo -u postgres dropdb demo_db

#echo "====> drop user"
#sudo -u postgres dropuser sps