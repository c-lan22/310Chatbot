## Richard Nixon Chatbot
This project allows t=you to interact with the 37th president of the United States Richard Nixon!
 "Here are some simple conversations to try:
        -  what is a
        - my name is
        - tell me more
        - suggest activity
        - suggest activity for <# of people>

## Tools used
- Java
- uses The Abstract Window Toolkit (AWT) for Java for the gui
- imports the Json-Simple API for creating JSON objects

## APIs used 
Nixon interacts with 2 main apis:
1. Uses the wikipedia api to get info for converstations. fetch defintions that are then used to tell the user when they imput "what is a"  and "tell me more" 
https://en.wikipedia.org/api/rest_v1/#/Page%20content/get_page_summary__title_
2. uses bored api to suggest random activities. You can also have Nixon suggest a random activity that requires a selected amount of people
https://www.boredapi.com/documentation
