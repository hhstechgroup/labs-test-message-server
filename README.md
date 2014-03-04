First of all, all scripts are located in scripts folder.

Glassfish. Create Connection Pool and JNDI JDBC resource.

Execute following commands for glassfish:

asadmin start-domain domain1

asadmin add-resources path/glassfish-resource.xml

asadmin stop-domain domain1

After executing this command you can see JNDI on your localhost:4848 called messagingserver/hsql.

Execute following commands for HSQLDB:

(You must change the path to your.)

Create db and start. In -cp you must specify path to your hsqldb location.

java -cp ./hsqldb/lib/hsqldb.jar  org.hsqldb.server.Server --database.0 file:D:\TRASH\hsqldb-2.3.2\hsqldb-2.3.2\db\messagingserver --dbname.0 messagingserver

After executing this command you can see db called messagingserver.


Execute following commands for client (not necessarily):

Start HSQLDB GUI Client and connect:

Driver: org.hsqldb.jdbc.JDBCDriver

Url: jdbc:hsqldb:hsql://localhost:9001/messagingserver
