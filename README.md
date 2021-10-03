#Client Classes
**ApiClient** -- class the encapsulates given requests by building an HTTPClient
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

**MathBot** class for MathBot operations, used in combination for MathBot commands to execute commands specified 
in REPL

**MathBotCommands** class that contains all the operations for MathBot class and commands (i.e subtract add)

**NaiveNeighborsCommands** --  contains all the operations for MathBot class and commands (i.e subtract add)

**Rent** -- class objectifying the rent data for a given user in an API call (i.e all the fields for the rent)

**Review** -- class objectifying the review data for a given user in an API call (i.e all the fields for the review)

**User** -- class objectifying the user data for a given user in an API call (i.e all the fields for the user)

**Star** -- class objectifying the stars data for a row  in a CSV  (i.e all the fields for the Star)

**KDTree** -- class representing a K-D Tree object

**Node** -- class representing a Node object in a tree

**NodeComparator** -- class containing a Comparator for a Node object

**RunwayCommands** -- class that contains all the commands for Rent the Runway data, including similar and classify





# API
Our API Implementation functions are as follows: Once a command to parse the API is called in REPL, an API aggregator 
is instantiated in order to get data from the API output, there are cases in the API aggregator to ensure that there
isn't any error and that the data is correct, a json is then instantiated to convert the data to a list.

# KD Tree
Our KD Tree implementation involves the creation of Node objects to fill in our KDTree object with. The KD Tree constructor
takes in a list of coordinates, inputs the coordinates into Node objects, and inputs the Node in a tree. The Nodes are 
sorted like a Binary Search Tree, with each level sorting on a different axis. After the KDTree is constructed, one can
search for the k nearest neighbors to certain coordinates using the KNNSearch method. The user indirectly interacts with
this method in the REPL, as the KNNSearch method is called when the commands of classify or similar are called. 

# Test Classes
**GalaxyTest** -- testing for the Galaxy class

**MathBotTest** -- testing for the MathBot Class

**StarTest** -- testing for the Star class

**KDTreeTest** -- testing for the KDTree class

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


