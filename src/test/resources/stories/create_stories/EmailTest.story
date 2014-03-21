Scenario: Send Email
When 'masya@mail.ru' send 'motia@gmail.ch' email to 'localhost':'25' with 'YO' and 'hi!!!'
When 'vasya@gopomail.net' send 'motia@gmail.ch' email to 'localhost':'25' with 'YO' and 'hi2!!!'
When 'motia@gmail.ch' send 'motia@gmail.ch' email to 'localhost':'25' with 'YO' and 'hi2!!!'
When 'vesyolii_ogyrechik@pz.de' send 'vasya@gopomail.net' email to 'localhost':'25' with 'YO' and 'hi2!!!'
When 'lol@lol.lol' send 'vasya@gopomail.net' email to 'localhost':'25' with 'YO' and 'hi2!!!'
When '1chupacabre@l2.we' send '1qwe@qwe.qwe' email to 'localhost':'25' with 'WAZZZAP 1' and html '<html><body><h2>HIIIIIIII 1</h2></body></html>'
When '2chupacabre@l2.we' send '2qwe@qwe.qwe' email to 'localhost':'25' with 'WAZZZAP 2' and html '<html><body><h2>HIIIIIIII 2</h2></body></html>'
When '3chupacabre@l2.we' send '3qwe@qwe.qwe' email to 'localhost':'25' with 'WAZZZAP 3' and html '<html><body><h2>HIIIIIIII 3</h2></body></html>'
When '4chupacabre@l2.we' send '4qwe@qwe.qwe' email to 'localhost':'25' with 'WAZZZAP 4' and html '<html><body><h2>HIIIIIIII 4</h2></body></html>'

Scenario: Email quickSearch
When the user opens the default page
When clicks on element with id/name/className 'emailItem'
When the user fills 'quickSearchEmail' field with 'i'
When press 'Search' button
When the user fills 'quickSearchEmail' field with 'F'
When press 'Search' button
When the user fills 'quickSearchEmail' field with '1'
When press 'Search' button
When the user fills 'quickSearchEmail' field with 'w'
When press 'Search' button

Scenario: SortByColume Email
When clicks on element with id/name/className 'emailItem'
When clicks on element with id/name/className 'emailId'
When clicks on element with id/name/className 'emailId'
When clicks on element with id/name/className 'sender'
When clicks on element with id/name/className 'sender'
When clicks on element with id/name/className 'subject'
When clicks on element with id/name/className 'subject'
When clicks on element with id/name/className 'text'
When clicks on element with id/name/className 'text'

Scenario: Emails delete
When the user opens the default page
When clicks on element with id/name/className 'emailItem'
When press 'Refresh' button
When make 'selectAllEmailReportChkBox' checked
When make 'selectAllEmailReportChkBox' unchecked