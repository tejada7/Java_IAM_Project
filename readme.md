[![BCH compliance](https://bettercodehub.com/edge/badge/tejada7/Java_IAM_Project?branch=master)](https://bettercodehub.com/)
![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/epita.png?raw=true)
# Identity Access Management Web Application      
# User Guide
## Version: 1.0
### Table of Contents
1. Running the application
2. Structure of the application
3. Login
4. List
5. Create
6. View
7. Edit
8. Delete

### 1. Running the application
Firstly, depending on the configuration of the application, start database service, by default, IAM works with Derby DB.
Secondly, the application has to be deployed in a web server like Apache Tomcat or Glassfish to run. The next step is to start the server, the URL for the application is:  
```localhost:8080/EPITASpecialization_IAMWeb```
which redirects to the Index page of the application, where you can access the system by giving your credentials.

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/1.png?raw=true)   

The present document can be opened from the application by either clicking on the
expand button located on the top/left of the gray container or by pressing ctrl + shift + left arrow.
Whenever a credential is invalid, the application displays a corresponding message informing the user.

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/2.png?raw=true)

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/3.png?raw=true)

### 2. Structure of the application
The following diagram shows the tree structure of the pages:
![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/4.png?raw=true)

The diagram above shows the hierarchy among the web pages: it is required to
authenticate for navigating through the application, otherwise any attempt to access
another page different than Index without authenticating will result to be redirected to
Index.   
Any attempt to access a non-existing page, the application will redirect to:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/5.png?raw=true)

### 3. Login
Once the application is running, enter username and password, then click on the button login to access the IAM.

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/6.png?raw=true)

Once logged in to the application, a list containing all the Identities (if there are in the database) is displayed.

### 4. List
If login is successful, the application redirects to Welcome page that displays a list of
identities (if there are stored in the DB) in a table format, containing search filters for the main attributes on the head of the table as well a generic filter on the most-top that can be used to search for any of the 5 main attributes:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/7.png?raw=true)

When using the filters, the table auto updates its content if element(s) match the desired criteria(s).  
To see again all the identities, just delete all the filled criteria, and the table with auto update its content.  
The number of elements seen can be configured at the bottom section of the table, the default number is ten rows per :

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/8.png?raw=true)

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/9.png?raw=true)

In that sense, the table is customizable according to user’s preferences, especially when the number of identities in DB is high.  
The image below shows the full-sized welcome page.

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/10.png?raw=true)

The column Other attributes contains dropdown to see additional attributes (in case of exist), so order to see them, click on the button as shown below:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/11.png?raw=true)  

In case of non-existing additional attributes, it displays “No records found”:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/12.png?raw=true)


Additionally, the application allows to change the number of columns to be visible in the table, by clicking on the button Columns on the most-top-right section of the table:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23
a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/13.png?raw=true)

Furthermore, the application allows to sort the table according its attributes, by clicking on any column that contain the name of the attribute, for example by birth date:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/14.png?raw=true)

Finally, the application allows to increase the size of the table container, by typing the combination ctrl+shift+left arrow, ctrl+shift+up arrow and/or ctrl+shift+right arrow:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/15.png?raw=true)

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/16.png?raw=true)

To go back one step, it is only needed to retype the combination of keys or by clicking on the expand buttons located both in the most-top-left and most-bottom-left sections of the page:

### 5. Create
To create a new Identity, click on the Create button located at the bottom of the table:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/18.png?raw=true)

Once clicked, a new view is displayed with the fields to be entered by the user. There are two sections, one related to the main attributes (first name, last name, email and birth date) and a section to create new attributes, which is optional:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/19.png?raw=true)

The minimum field required to create a new Identity is the first name, it means that however attempt to create with an empty first name, a message is displayed:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/20.png?raw=true)

In reference to the additional attributes, the user enters the name of the new attribute, including its value,

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/21.png?raw=true)

A table below the “add button” shows the additional attribute just added. After filling the fields desired and/or creating additional attributes, click on Save button. A message will be displayed that the Identity was created and simultaneously the list of Identities will be refreshed, showing the Identity just added.

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/22.png?raw=true)

### 6. View
To view an Identity in detail, one row in the list has to be selected at least and then click on View button located at the bottom of the table:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/23.png?raw=true)

### 7. Edit

To edit on Identity, select a row of the table and then click on Edit button, located at the bottom of the table. Then edit the desired attributes, including the additional ones:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/24.png?raw=true)

Additionally, it is possible to add new attributes, by going to Add attributes tab:

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/25.png?raw=true)

When clicking on button Save, a message will be displayed and the table will be
refreshed.
### 8. Delete

To delete an Identity, click on any row of the table and then click on Delete, a message is displayed and the table is refreshed.

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/26.png?raw=true)

![](https://github.com/tejada7/Java_IAM_Project/blob/5e41cb3e4a967a8536c897431b23a24f0049fac0/EPITASpecialization_IAMWeb/WebContent/resources/doc/27.png?raw=true)
