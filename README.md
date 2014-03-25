# How-To

1. Configure MySQL DB.
 1. Download and install MySQL Server. 
	> (If you use Windows - comfortable to use MySQL Windows Installer http://dev.mysql.com/downloads/windows/installer)
 2. Configure DB
		1. Port - 3306
		2. Login - root
		3. Password - root
 3. Create database in server
		Query 'Create database messagingserver;' (Can it execute in workbench)
2. Configure Glassfish.
	1. Download JDBC MySQL connector - mysql-connector-java-5.1.29.zip (http://dev.mysql.com/downloads/connector/j/)
	2. Extract and move ${ConnectorPath}/mysql-connector-java-5.1.29-bin.jar to ${GlassfishPath}/glassfish/lib/
	3. Copy ${OurProject}/scripts/glassfish-recource.xml to ${GlassfishPath}/bin/
	4. asadmin start-domain domain1
	5. asadmin add-resources glassfish-resource.xml
	6. Check your JNDI in glassfish
3. Configure ActiveMQ.
    1. Download ActiveMQ from http://activemq.apache.org/.
    2. Extract to your destination folder.
    3. Copy ${OurProject}/scripts/activemq.xml to ${ActiveMQ}/conf/ with replace source.
    4. Run ${ActiveMQ}/bin/activemq.bat
3. Deploy your project

* Configure Sonar (Optional)
	- Download sonar from http://www.sonarsource.org/downloads/
	- Extract archive
	- Run  ...\sonar-4.1.2\bin\windows-x86-64\StartSonar.bat
	- Build project using maven sonar:sonar
	- View report on http://localhost:9000/