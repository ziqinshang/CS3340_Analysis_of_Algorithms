The main function is inside Program.java. Run Program.java and the login window will appear.
The test credentials are: admin(username) cs2212b(password)

All the credentials are stored in CredentialsDB.txt file where each line contains a pair of username 
and password seperated by space.
All the selection conflicts are stored in Conflicts.txt file.

Once successfully submitted username and password, the main window will be presented.
On the top section, you can choose analysis on one of the 5 countrys, and any time period between 2010 and 2018
On the bottom section, you can choose one of 6 viewers to add or remove from viewer list which is display synchronouly in the bottom right corner.
After that you can select one of the 8 analysis methods, if any of the selection creates conflict a message will appear in the top right corner.
Once completed selection, click "Recalculate" button and the program will call appropraite functions to fetch data and process, eventually create viewers to be displayed in the center white section.

The behavior of each components:
In main window, once completed selection and clicked recalculate button, mainWindow then call the
requestHandler and provide it with user selections, the requestHandler then decides what data is needed 
to fetch based on analysis method, after getting desired data, it calls the create methods in Viewer class
by the order which user added. In these methods, the graphs created will be added to the center panel of
the mainWindow. The analysis is now compled.