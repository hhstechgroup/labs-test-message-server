Scenario: SMS Send
When the user opens the default page
When clicks link with text 'SMS Messages'
When press 'Refresh SMS' button
When the user fills 'inputSmsSender' field with 'Sen'
When the sender 'Tom' send sms to '54545454' the body is 'Hello Bob it's Tom'
When press 'Refresh SMS' button
When press 'Refresh SMS' button