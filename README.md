Server has two applications. 1.SmartSense and 2. credit system. 

cmu-app-server - Initiative by Team Smart Sense  ( Ira Jain, Emilia Torino, Tushar Dadlani, Anubhav)
Hit cmu-app-server.herokuapp.com/sensor/smartSense/bid to bid and get current winner  
Hit cmu-app-server.herokuapp.com/sensor/smartSense/currentWin -> to get current winner before bidding 
Hit cmu-app-server.herokuapp.com/sensor/smartSense/nest to start the server bidding logic for controlling nest 


Hit cmu-app-server.herokuapp.com/sensor/credit/getUserCredit to get the credits for a user 
Hit cmu-app-server.herokuapp.com/CreditSystem.jsp to give the credits to a user 


This can be downloaded directly from github.

To Build : mvn package
To run server locally on 8080 : sh target/bin/webapp
