# AddressBookAPI
A simple address book restful API that is designed to maximise the use of HTTP methods and statuses and to utilise resources well. It is implemented using Spring Boot with JPA and a H2 database for the data layer.

The example makes useof hateoas to work with the best practice of using Hypermedia As The Engine Of Application State (HATEOAS).

There are two entities accessed by the API - Contact and Addresss. THese are modelled as Contacts having the possibility to have many addresses.

There is also some working examples of test coverage for the controller testing some basic scenarios for the web layer.

# This application is functional but not complete and would require further refinenment including error handling, validation and test coverage if more time was available.

# Running the Project

1. Download the repository to your local machine with your favourite Java IDE, maven and Java installed. 
2. Navigate to /address-book/src/main/java/com/moo/rest/addressbook/AddressBookApplication.java and right-click and run as a Java Application. 
3. It should start running - it should only take a few seconds too load (once Maven has finished downloading the dependencies)
4. The API should be accessible via your localhost:8080

# API EndPoints

The API endpoints are best consumed for testing purposes using POSTMAN. When 

GET EndPoints:

- /contacts 
Returns all contacts in the addressBook

Example:

[{"id":8001,"firstName":"Tony","secondName":"Stark","emailAddress":"tony.stark@avengers.com","addresses":[{"id":8888,"doorNumber":95,"streetName":"London Road","town":"Bromley","postCode":"ABC 321"},{"id":9999,"doorNumber":59,"streetName":"London Road","town":"Bromley","postCode":"ABC 321"}]},{"id":8002,"firstName":"Dave","secondName":"Lister","emailAddress":"dave.lister@reddwarf.com","addresses":[]}]

- /contacts/{id}
Returns a contact with that specific id


- /contacts/{id}/addresses
Returns all addresses for a contact with a specific id

- /addresses/{id}
Returns an address with a specific id

- /contacts/byName/{name}
Returns any contacts with an exact match surname.

POST EndPoints:

/contacts
- Allows you to post a contact to the database

DELETE EndPoints:

/contacts/{id}
- Allows you to delete a specific contact with a specified id.

