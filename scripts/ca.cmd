robocopy apache-ant-1.9.3 c:/apache-ant-1.9.3 /E
set ANT_HOME=c:/apache-ant-1.9.3
set PATH=%PATH%;%ANT_HOME%/bin
ant -buildfile build.xml
exit