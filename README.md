1. Configure MySQL DB.
	1.Download and install MySQL Server 
	(If you use Windows - comfortable to use MySQL Windows Installer http://dev.mysql.com/downloads/windows/installer)
	2. Configure DB
		Port - 3306
		Login - root
		Password - root
	3. Create database in server
		Create database messagingserver; (May it execute in workbench)
2. Configure Glassfish.
	1. Download JDBC MySQL connector - mysql-connector-java-5.1.29.zip (http://dev.mysql.com/downloads/connector/j/)
	2. Extract and move ${ConnectorPath}/mysql-connector-java-5.1.29-bin.jar to ${GlassfishPath}/glassfish/lib/
	3. Copy ${OurProject}/scripts/glassfish-recource.xml to ${GlassfishPath}/bin/
	4. asadmin start-domain domain1
	5. asadmin add add-resources glassfish-resource.xml
	6. Check your JNDI in glassfish
3. Deploy your project