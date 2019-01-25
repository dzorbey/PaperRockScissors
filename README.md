# Paper-Rock-Scissors Game Api Project
# Author: Delikan Zorbey Gokyildiz

This project is an Api implementation of the paper-rock--scissors game that is by default running on port 8080 once started up.

The access point for the swagger API documentation is : http://localhost:8080/swagger-ui.html#/game-controller

the actual endpoint to be tested is : http://localhost:8080/game/play

Example curl command for testing : 
    curl -X GET "http://localhost:8080/game/play" -H "accept: application/json" -H "inputHeader: ROCK"
    
    
    
    
Apart from functional testing via Mock instances in the project, there is a seperate integration project that can be initiated to test the API endpoints by exact input/response objects. Once the rest api is running you can initite the api endpoint testing via the RunTests.java class inside integration project.    
