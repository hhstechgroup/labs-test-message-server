Scenario: OpenPages
When the user opens the default page
When clicks on element with id/name/className 'jmsItem'

Scenario: SendJMS
When clicks link with text 'JMS'
When press 'Refresh' button
When send jms to without body