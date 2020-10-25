# AndresRodriguezInventoryApp

SNHU Computer Science Program Project

Android Inventory App

This app makes it simple to track inventory items and their count on an Android device.

Functions with SQLite Database
  -Add item
  -Delete item
  -Edit item
  -View items in a grid
  
Permissions
  -SEND SMS TEXT
  
How to make it better

Have the UI update when an item is added.
Have the user be able to set what a low amount of each item is.
Have the FAB be a cleaner plus icon.
Have more properties for each item like date added.
Create and icon for the app.

Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?

The goal of the app is to easliy view, add, delete, and edit items in inventory. The user need was to have an app that can track items
and manage them quickly.

What screens and features were necessary to support user needs and produce a user-centered UI for the app? 
How did your UI designs keep users in mind? Why were your designs successful?

The features that were necessary were adding, deleting, reading, and editing items in inventory. My UI was simple because when the user logged in,
they were greeted with an empty grid and a FAB to begin adding items. I do think they were successfull because the user could add
an item right after they logged in.

How did you approach the process of coding your app? What techniques or strategies did you use? 
How could those be applied in the future?

I began coding my app by creating the design first. The design did change and I created the logic, but that was expected. 
Creating the design first let me focus on the logic without having to jump from design to logic. With every button, method, and even
line of code, I made sure to test. I never wrote a large chunck of code because if there were a bug, it would have been harder to find.


How did you test to ensure your code was functional? Why is this process important and what did it reveal?

I tested my code with simple outputs to log via System.out.log and Debug.log. In addition, I tested by interacting with the UI.
Testing both ways made sure the app was delivering the data to other methods and then the UI if applicable. This process revealed
some data types that needed to be converted, app crashes, and unintended database logic.

Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?
In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?

Moving data through the database and on to the grid was my biggest challenge. I was able to have the data be entered by the user, 
be added to the database, but I had a difficult time updating the grid showing the data. I was able to get all the functions working
nicely except when an item was added.

I was successful in seperating the database logic, data model, and app logic into their own files. I still need to practice the 
MVC pattern but I noticed I have improved with this project.