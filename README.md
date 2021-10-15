
# Known Bugs
- There is an indexing issue with the KDTree construction. Notably, the basic KDTree was working properly, but when we started implementing the REPL commands and interacting with the User class, the coordinates and indices were likely mixed up. This would be a relatively okay fix, but we just did not have enough time to debug.
- The users command returns an ERROR. We are not sure why this is, and we did not have time to debug this.
- Since users did not load in the data to our tree, none of our system tests have passed. However, we are confident that our KNN algorithm for our tree works properly, as the JUnit tests for those pass. It's only a matter of the interaction with the API and the REPL class. 
- If we had more time for this assignment, we would likely have been able to resolve most of the bugs, as we have the different components working. The meshing of the components is still a work in progress.


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

Another extension is essentially maybe sorting on skill so  students with highest skill have the greatest say
on where they want to go.

We could have also assigned students on their top 5 preferences and then assigned randomly otherwise.
Another Approach is creating a new group out of the remaining students 

# User Story 4- Rationale 
Team Format:
- Add first x Students to x groups where x is the number of teams. 
- Adding the remaining (studentList - x) students based on preference, only 
constraint is that each group has a max of like studentList / n (to ensure uniform distribution). if a students 
most preferred destination is full, move them to the next preference.
- The preference itself will be calculated by the bloom filter and KDtree ranking scored together
- the remaining students will be assigned to random groups to fill them (or if they're all full just any group)


# README
To build use:
`mvn package`

To run use:
`./run`

To start the server use:
`./run --gui [--port=<port>]`


