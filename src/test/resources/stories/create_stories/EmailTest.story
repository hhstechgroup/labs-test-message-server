Scenario: Send Email
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 1' and body 'Hello'
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 2' and body 'hi'
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 3' and body 'how are you?'
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 4' and body 'fine LOL'
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 1' and body 'Good Joyke =)'

Scenario: Sort
When the user opens the 'http://localhost:8080/labs-test-message-server/' link
When clicks link with text 'Email Messages'
When clicks link with text 'SMS Messages'
When clicks link with text 'Server Info'