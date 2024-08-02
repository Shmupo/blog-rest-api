# REST Blog API

A website API featuring posts and comments.
Posts have a one-to-many relationship with comments.
Designed around Spring MVC.

## Description

An in-depth paragraph about your project and overview of use.

### Dependencies

Java 17
Spring
Spring Web
Spring Data JPA
MySQL Driver
Lombok

### Installing

Download the repository and open it as a project in an IDE.
It is recommended to use IntelliJ, as this was the IDE used during development. Alternate ways will not be explained.
It is recommended to use Postman to interact with the api. Alternate ways will not be explained.

### Executing program

Run a mySQL server at the port 3306 with a database called 'blogdb'
Execute main within src/main/java/com/example/blog/rest/api/BlogRestApiApplication.java

### How To Use

Interact with the program via the Postman application

The url path /posts accesses the posts
	This supports GET and POST actions

The url path /posts/{postId} accesses the post with the give postId
	This supports GET, PUT, and DELETE actions

the url path /posts/{postId}/comments accesses the comments of the post with the given postId
	This supports GET and POST actions

The url path /posts/{postId}/comments/{commentId} accesses the specific comment of commentId of the post with the given postId
	This supports GET, PUT, and DELETE actions

## Authors

Andrew Doan
[https://github.com/Shmupo]

## Version History

* 0.1
    	* Initial Release

* 0.2
	* Removing 'comments' variable from Post
	* Fixing Comments bugs

## License

This project is licensed under the [MIT LISENSE] License - see the LICENSE.md file for details