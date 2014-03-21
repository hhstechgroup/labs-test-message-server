#!/bin/sh

LOGFILE=.\log.log

timestamp() {
  date +"%T"
}

echo ------------------------------------------------------------------>>$LOGFILE
timestamp>>$LOGFILE

ant -buildfile build.xml>>$LOGFILE

start http://localhost:8080/labs-test-message-server

if [[ $? -ne 0 ]]
then
    echo "error happend">>$LOGFILE
fi

