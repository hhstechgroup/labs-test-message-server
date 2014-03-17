

Scenario: SendSMS
When the sender 'Stuart' send sms to '21212121' the body is 'Hello Tom it's Stuart'
When the sender 'Tom' send sms to '31313131' the body is 'Hello Stuart'
When the sender 'Bob' send sms to '41414141' the body is 'Hello Tom'
When the sender 'Tom' send sms to '54545454' the body is 'Hello Bob it's Tom'
When the sender 'Semon' send sms to '565656' the body is 'well'
When the sender 'Sara' send sms to '565656' the body is 'Hi'
When the sender 'Sara' send sms to '565656' the body is 'Fine thenks'
When the sender 'Sender1' send sms to '575757' the body is 'by by!'

