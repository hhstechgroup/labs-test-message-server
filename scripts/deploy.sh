#!/bin/sh

LOGFILE=.\log.log

timestamp() {
  date +"%T"
}

echo ------------------------------------------------------------------>>$LOGFILE
timestamp>>$LOGFILE

start cmd /k ca.cmd

if [[ $? -ne 0 ]]
then
    echo "error happend">>$LOGFILE
fi

exit

