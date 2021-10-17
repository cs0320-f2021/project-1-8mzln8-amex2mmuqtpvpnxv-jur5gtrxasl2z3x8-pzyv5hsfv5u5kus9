
#Known Bugs



#API Classes
**ApiClient** -- class the encapsulates given requests by building an HTTPClient

**ClientAuth** -- class that reads through given API Keys

**ClientRequestGenerator** -- class that generates HTTP Requests

**APIAggregator** -- class that calls and  parses through the API String and creates a List of Objects, the object type
specifically corresponds to one of the 3 classes created for the user data from the website (Review, Rent and User)


#Bloomfilter Classes
**AndSimilarityComparator** -- class taken from bloomfilter repo

**BloomFilter** -- class taken from bloomfilter repo

**BloomFilterRecommender** -- class taken from bloomfilter repo

**XnorSimilarityComparator** -- class taken from bloomfilter repo

#Commands
**ApiCommands** -- class containing action items on REPL commands pertaining to API functionality, implements REPLCommand

**CommandActions** -- class containing a HashMap that maps to REPL commands represented as a String to a REPLCommand type, implements REPLCommand

**MathBothCommands** -- class containing action items on REPL commands pertaining to the MathBot class, implements REPLCommand

**NaiveNeighborsCommands** -- class containing action items on REPL commands pertaining to the stars project functionality

**RecommenderCommands** -- class containing action items on REPL commands pertaining to recommender functionality, implements REPLCommand

**REPLCommand** -- interface representing a REPL command type 

**RunwayCommands** -- class representing action items on REPL commands pertaining to Rent the Runway functionality, implements REPLCommand

#Core Classes
**FileParser** -- class that parses given files passed through

#Main Classes

**Main** -- the main class where the REPL is created

**MathBot** -- a class containing mathematical operations

#KD Tree
**KDTree** -- class representing a K-D Tree object

**Node** -- class representing a Node object in a tree

**NodeComparator** -- class containing a Comparator for a Node object

#ORM
**Database** -- class taken from ORM repo

#Recommender
##Tables
**Interests** -- a class containing info on the student's id and interests

**Negative** -- a class containing info on the student's id and responses in the question asking about negative traits

**Positive** -- a class containing info on the student's id and responses in the question asking about positive traits

**Skills** -- a class containing info in the student's id and more quantifiable skills


**APIData** -- a class containing info pulled from the API

**Item** -- an interface representing an item

**Recommender** -- an interface representing a recommender

**Student** -- a class representing a student in the dataset with fields corresponding to their responses

#Runway
**Rent** -- class objectifying the rent data for a given user in an API call (i.e all the fields for the rent)

**Review** -- class objectifying the review data for a given user in an API call (i.e all the fields for the review)

**User** -- class objectifying the user data for a given user in an API call (i.e all the fields for the user)

#Stars
**Star** -- class objectifying the stars data for a row  in a CSV  (i.e all the fields for the Star)

**Galaxy** -- class containing a list of all stars in the dataset





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
-TA mentioned that we can shorten Main even further by using some sort of hashmap containing list of all possible
commands mapping to their respective command class (Addressed in User Story 1)

-Assign students based on the order that they are assigned. In the case where a student has no optimal partners or two out of the three isn't optimal, i think that we can continue with that so long as their connection with student A isn't as high.

-If the number of students is larger than the desired team size, IF and only IF there are no other oversized/undersized groups, we will keep that way. If there are any,  remove the least compatible member of the oversized group and add to an undersized one/join with an oversized member provided they are optimal to certain threshold (so if like the numerical value of Member D in ABCD is 10,  and he has the lowest group compatibility, if joining the undersized group HJ is a better fit from his preference, we can add to that group.



#User Story 4 Discussions
Due to time constraints, I settled on prioritizing the students randomly by the order that they are assigned. 
In terms of presenting the data, print lines from command would work, but we could also find a way to display the data 
externally as well. 

# README
To build use:
`mvn package`

To run use:
`./run`

To start the server use:
`./run --gui [--port=<port>]`


