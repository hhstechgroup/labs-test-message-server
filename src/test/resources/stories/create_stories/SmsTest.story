Scenario: SMS Send
When the user opens the default page
When clicks link with text 'SMS Messages'
When press 'Refresh SMS' button
When the sender 'Tom' send sms to '54545454' the body is 'Hello Bob it's Tom'
When press 'Refresh SMS' button
When the user fills 'inputSmsSender' field with 'Sen'
When clicks on element with id/name/className 'DoFilter'
When clicks on element with id/name/className 'CancelFilter'
When press 'Refresh SMS' button
When press 'Refresh SMS' button
When clicks link with text 'Server Info'
When clicks link with text 'SMS Messages'
When clicks on element with id/name/className 'columnId'
When clicks on element with id/name/className 'selectAllSMSReportChkBox'
When clicks on element with id/name/className 'reportRecordSMSChkBox'
When clicks on element with id/name/className 'reportRecordSMSChkBox'
When clicks on element with id/name/className 'columnId'
When clicks on element with id/name/className 'sortByid'





