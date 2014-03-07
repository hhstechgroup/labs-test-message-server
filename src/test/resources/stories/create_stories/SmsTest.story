Scenario: OpenPages
When the user opens the default page
When clicks link with text 'SMS'
When clicks link with text 'Emails'
When clicks link with text 'Info'
When clicks link with text 'SMS'

Scenario: SendSMS
When clicks link with text 'SMS'
Given create sender sms
Then wait for '5' sec
When press 'Refresh' button
When the sender 'Stuart' send sms to '21212121' the body is 'Hello Tom it's Stuart'
When press 'Refresh' button
When the sender 'Tom' send sms to '31313131' the body is 'Hello Stuart'
When press 'Refresh' button
When the sender 'Bob' send sms to '41414141' the body is 'Hello Tom'
When press 'Refresh' button
When the sender 'Tom' send sms to '54545454' the body is 'Hello Bob it's Tom'
When press 'Refresh' button
When the sender 'Semon' send sms to '565656' the body is 'well'
When press 'Refresh' button
When the sender 'Sara' send sms to '565656' the body is 'Hi'
When press 'Refresh' button
When the sender 'Sara' send sms to '565656' the body is 'Fine thenks'
When press 'Refresh' button
When the sender 'Sender1' send sms to '575757' the body is 'by by!'
When press 'Refresh' button

Scenario: SortByColume Email
When clicks on element with id/name/className 'columId'
When clicks on element with id/name/className 'columId'
When clicks on element with id/name/className 'deliveryDate'
When clicks on element with id/name/className 'deliveryDate'
When clicks on element with id/name/className 'sender'
When clicks on element with id/name/className 'sender'


Scenario: SMS quickSearch
When the user fills 'quickSearchSmsPanel' field with 'i'
When press 'Search' button
When the user fills 'quickSearchSmsPanel' field with 'F'
When press 'Search' button
When the user fills 'quickSearchSmsPanel' field with '1'
When press 'Search' button
When the user fills 'quickSearchSmsPanel' field with 'w'
When press 'Search' button

Scenario: Do Filter SMS
When the user fills 'inputSmsSender' field with 'Sen'
When clicks on element with id/name/className 'DoFilter'
When clicks on element with id/name/className 'CancelFilter'
When press 'Refresh' button
When press 'Refresh' button
When clicks link with text 'Info'
When clicks link with text 'SMS'

Scenario: SMS delete
When make 'selectAllSMSReportChkBox' checked
When make 'selectAllSMSReportChkBox' unchecked
When make 'reportRecordSMSChkBox' checked
When make 'reportRecordSMSChkBox' unchecked
When make 'reportRecordSMSChkBox' checked
When press 'Delete' button
When press 'Refresh' button
When make 'selectAllSMSReportChkBox' checked
When press 'Delete' button
When press 'Refresh' button









