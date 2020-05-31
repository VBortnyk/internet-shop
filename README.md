# Internet-shop
#### Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Author](#author)
#
#### <a name="purpose">Project purpose</a>
The aim of this project is to simulate the work of an internet shop with basic operations available. 
Two types of users can sign in and due to their roles they are authorised to perform certain actions.
Users may look through the list of all available products, add products to their carts and make orders,
they may also dismiss their orders. Admin has the authority to edit the database: add products to 
storage, delete products and delete users from the list of users registered at the shop.
#
#### <a name="purpose">Project structure</a>
* Java 11
* Maven 3.6.3
* maven-checkstyle-plugin 3.1.1
* maven-war-plugin 3.2.3
* javax.servlet-api 3.1.0
* jstl 1.2
* junit 4.12
* log4j 1.2.17
* mysql-connector-java 8.0.20
#

#### <a name="purpose">For developers</a>
1. Open the project in your IDE.
2. Choose sdk 11.0  or higher in Project Structure.
3. Configure Tomcat:
add the artifact internet-shop:war exploded;
add as URL http://localhost:8080/
4. Start MySQLWorkbench.
5. At internet-shop.src.main.java.dev.internet.shop.util.ConnectionUtil class use your username 
and password for your MySQLWorkbench to create a Connection.
6. Run internet-shop.src.main.resources.init_db.sql to create all the tables required by this app.
7. Change a path to log file in internet-shop.src.main.resources.log4j.properties.
8. Run the project.

You should load a trial storage and test customers by clicking "inject data" button.
In this case you will be able to authorise as admin with ADMIN role (login = "admin"", password = 1) or
user with USER role (login = "vip", password = 1). Also, you will have three items in the store:
Apple, Pear and Plum.
#

#### Author
Viacheslav Bortnyk: https://github.com/Oleh888/internet-shop

