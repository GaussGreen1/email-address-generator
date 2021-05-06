# email-address-generator
Generate email addresses starting from a list of strings.

A Java/Spring Boot project deployed on Heroku, it uses the Spring Web dependency to create the APIs. The main endpoint (/generator) can be called with the POST method to load the input strings, and with the GET method to return the email addresses from the uploaded strings. Both these APIs reply with a JSON "RestResponse" bean, which will print error messages if something went wrong, or a regular output.

## Usage
Making a POST call to /generator will upload the list of strings from its body, making it ready to be turned into email addresses:

![screenshot](https://i.imgur.com/59fyVUj.png)



A GET call to /generator will now generate email addresses from the names that have been uploaded so far, alphabetically sorted and adding a number at the end of its prefix should there be duplicates. It will also clear the waiting list:

![screenshot](https://i.imgur.com/yZjoM2P.png)


##
## Logging and service health
Service status/health can be checked by calling /actuator/health.

Logs of all API calls and errors can be checked with a GET call to the /logs endpoint:

![screenshot](https://i.imgur.com/yvyiyZb.png)
