# *LogiCal*

## What will the application do?

The application will take a truth table as an input from 
the user and return a simplified logic for the same. The 
application will use the approach of Karnaugh Maps to calculate 
the simplest logical form. The application, for now, will be 
able to create logics with a maximum of 4 variables. By the end
of the project I want to create a logical circuit from the 
logical expression obtained.

## Who will use it?
The application can be used by students currently studying 
logic and want an automated way to calculate logic from a truth 
table. 

## Why is the project of interest to me?
I am currently studying logic along with this course and 
creating a logical expression from a truth table is quite 
common. This project is the best way I can combine my learnings
from both the courses.

## User Stories
- As a user, I want to be able to create a logical expression
from a truth table.
- As a user, I want to be able to store the inputted truth 
table along with the calculated logic as *Log* in a 
*ListOfLogs*.
- As a user, I want to be able to delete a previous log from 
the list of logs.
- As a user, I want to be able to mark a previous log as 
important.
- As a user, I want to be able to save the instance of my app to a json file.
- As a user, I want to be able to load the instance of my app.

## Phase 4: Task 2
Mon Mar 28 20:50:27 PDT 2022\
Log with logic B has been added to logs\
Mon Mar 28 20:50:28 PDT 2022\
Log with logic 1 has been added to logs\
Mon Mar 28 20:50:28 PDT 2022\
Log with logic (~B ^ A) has been added to logs\
Mon Mar 28 20:50:28 PDT 2022\
Log with logic 0 has been added to logs\
Mon Mar 28 20:50:28 PDT 2022\
Log with logic (~A) V (A ^ B ^ C) has been added to logs\
Mon Mar 28 20:50:28 PDT 2022\
Log with logic ~B has been added to logs\
Mon Mar 28 20:50:28 PDT 2022\
Log with logic (~B ^ A) has been added to important logs\
Mon Mar 28 20:50:28 PDT 2022\
Log with logic (~A) V (A ^ B ^ C) has been added to important logs\
Mon Mar 28 20:50:28 PDT 2022\
Log with logic ~B has been added to important logs\
Mon Mar 28 20:50:36 PDT 2022\
Log with logic 1 has been added to important logs\
Mon Mar 28 20:51:24 PDT 2022\
Log with logic (~A ^ ~C) V (~A ^ B) V (B ^ C) has been added to logs\
Mon Mar 28 20:51:29 PDT 2022\
Log with logic (~A ^ ~C) V (~A ^ B) V (B ^ C) has been added to important logs\
Mon Mar 28 20:51:31 PDT 2022\
Log with logic 1 has been removed from important logs

## Phase 4: Task 3

I think in my design I have divided every part of my application into 
different parts giving me the independence to deal with those parts 
individually. The use of abstract class InputTruthTable made it easier to extend to its four subclasses. Each of my classes
do only one thing which makes the code easier to maintain.

Changes 

- I would probably add more fields in classes for example all the Input
Truth Tables in Truth Table Frame rather than calling those classes from
a method. This would create a stronger relationship between the classes.
- I would probably create an interface or an abstract class for the 
classes ListOfLogsFrame and ListOfImportantLogsFrame.
- I would add some image or a logo for the app on the starting screen.
- At this time, my app does not have any colours so would add colours
that make the app more joyful to use.