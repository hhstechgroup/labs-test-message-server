Glassfish. Connection Pool and JNDI JDBC resource
asadmin start-domain domain1
asadmin add-resources path/glassfish-resource.xml
asadmin stop-domain domain1

messagingserver/hsql - JNDI

HSQLDB (change path)
Create db and start.
java -cp ./hsqldb/lib/hsqldb.jar  org.hsqldb.server.Server --database.0 file:D:\TRASH\hsqldb-2.3.2\hsqldb-2.3.2\db\messagingserver --dbname.0 messagingserver

messagingserver - db name.

Start HSQLDB GUI Client and connect:
Driver: org.hsqldb.jdbc.JDBCDriver
Url: jdbc:hsqldb:hsql://localhost:9001/messagingserver
