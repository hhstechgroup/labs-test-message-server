Scenario: OpenPages
When the user opens the default page
When clicks link with text 'SMS'
When clicks link with text 'Emails'
When clicks link with text 'Info'
When clicks link with text 'Emails'

Scenario: Send Email
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 1' and body 'Hello'
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 2' and body 'hi'
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 3' and body 'how are you?'
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 4' and body 'fine LOL'
When 'lol@lol.lol' send email to test mailbox with subject 'Hi 1' and body 'Good Joyke =)'
When '123@mail.ru' send email to test mailbox with subject 'WARNING' and body from file '$fileName'
When press 'Refresh' button

Scenario: Email quickSearch
When the user opens the default page
When clicks link with text 'Emais'
When the user fills 'quickSearchEmailPanel' field with 'i'
When press 'Search' button
When the user fills 'quickSearchEmailPanel' field with 'F'
When press 'Search' button
When the user fills 'quickSearchEmailPanel' field with '1'
When press 'Search' button
When the user fills 'quickSearchEmailPanel' field with 'w'
When press 'Search' button

Scenario: Do Filter Email
When the user fills 'inputEmailSender' field with 'Sen'
When clicks on element with id/name/className 'DoFilter'
When clicks on element with id/name/className 'CancelFilter'
When press 'Refresh' button
When press 'Refresh' button
When clicks link with text 'Info'
When clicks link with text 'SMS'

Scenario: SortByColume Email
When clicks link with text 'Emails'
When clicks on element with id/name/className 'columnId'
When clicks on element with id/name/className 'columnId'
When clicks on element with id/name/className 'sender'
When clicks on element with id/name/className 'sender'
When clicks on element with id/name/className 'subject'
When clicks on element with id/name/className 'subject'
When clicks on element with id/name/className 'text'
When clicks on element with id/name/className 'text'
When clicks on element with id/name/className 'deliveryDate'
When clicks on element with id/name/className 'deliveryDate'

Scenario: Emails delete
When the user opens the default page
When clicks link with text 'Emails'
When press 'Refresh' button
When clicks on element with id/name/className 'selectAllEmailReportChkBox'
When make 'selectAllEmailReportChkBox' checked
When make 'selectAllEmailReportChkBox' unchecked
When clicks on element with id/name/className 'reportRecordEmailChkBox'
When make 'reportRecordEmailChkBox' checked
When make 'reportRecordEmailChkBox' unchecked
When make 'reportRecordEmailChkBox' checked
When press 'Delete' button
When press 'Refresh' button
When clicks on element with id/name/className 'reportRecordEmailChkBox'
When make 'reportRecordEmailChkBox' checked
When press 'Delete' button
When press 'Refresh' button
When clicks on element with id/name/className 'selectAllEmailReportChkBox'
When make 'selectAllEmailReportChkBox' checked
When press 'Delete' button
When press 'Refresh' button