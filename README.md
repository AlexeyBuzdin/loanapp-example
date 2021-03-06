### Business requirements
+    User can apply for loan by passing amount and term to api.
-    Loan application risk analysis is performed. Risk is considered too high if:
    +  the attempt to take loan is made after 00:00 with max possible amount.
    -  reached max applications (e.g. 3) per day from a single IP.
-    Loan is issued if there are no risks associated with the application. If so, client gets response status ok. However, if risk is surrounding the application, client error with message.
-    Client should be able to extend a loan. Loan term gets extended for one week, interest gets increased by a factor of 1.5.
-    The whole history of loans is visible for clients, including loan extensions.

### To Run
- for UNIX:  `./gradlew run`
- for Windows: `.\gradlew.bat run`

### TODO left
- Add different user roles
- Add Swagger docs
- Orika mapping for Loan
- Add integration for Client full name fetching
- Add Validation handling
- Add transactions
