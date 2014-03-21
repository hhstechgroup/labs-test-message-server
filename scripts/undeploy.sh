#!/bin/sh

#type -asadmin start-domain domain1

LOGFILE=.\log.log

timestamp() {
  date +"%T"
}

echo ------------------------------------------------------------------>>$LOGFILE
timestamp>>$LOGFILE

#asadmin add-resources .\\tmp\\glassfish-resource.xml>>$LOGFILE

ant -buildfile build.xml undeploy>>$LOGFILE

if [[ $? -ne 0 ]]
then
    echo "error happend">>$LOGFILE
fi