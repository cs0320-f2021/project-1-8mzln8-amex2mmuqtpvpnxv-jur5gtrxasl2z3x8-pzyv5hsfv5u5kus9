#Client Classes
**ApiClient** -- class the enacsuplates given requests by building an HTTPClient
**ClientAuth** -- class that reads through given API Keys
**ClientRequestGenerator** -- class that generates HTTP Requests

#Core Classes
**FileParser** -- class that parses given files passed through

#Main Classes
**APIAggregator**  -- class that calls and  parses through the API String and creates a List of Objects, the object type 
specifically corresponds to one of the 3 classes created for the user data from the website (Review, Rent and User)

**CommandAction** -- Interface is an interface that all the command classes implement

**Galaxy** -- class representing the stars as an Object 

**Main** --  class for running and executing the REPL

**MathBot** class for Mathbot operations, used in combination for MathBot commands to excute commands specified 
in REPL

**MathBotCommands** class that contains all the operations for Mathbot class and and commands (i.e subtract add)

**NaiveNeighborsCommands** --  contains all the operations for Mathbot class and and commands (i.e subtract add)

**Rent** -- class objectifying the rent data for a given user in an API call (i.e all the fields for the rent)

**Review** -- class objectifying the review data for a given user in an API call (i.e all the fields for the review)

**User** -- class objectifying the user data for a given user in an API call (i.e all the fields for the user)

**Star** -- class objectifying the stars data for a row  in a CSV  (i.e all the fields for the Star)






# API
Our API Implementation functions are as follows, oncea command to parse the API is called in REPL, an API aggregator 
is instanitated in order to get data from the API output, there are cases in the API aggregator to ensure that there
isnt any error and that the data is correct, a json is then instanitated to convert the data to a list.

# KD Tree



# Extensions
TA mentioned that we can shorten Main even further by using some sort of hashmap containing list of all possible
commands mapping to their respective command class 


# README
To build use:
`mvn package`

To run use:
`./run`

To start the server use:
`./run --gui [--port=<port>]`


