# RestEndpoints
Install database :

 sudo apt-get install postgresql
 
 Create db in postgres, after that restore db from project:
 
 sudo -u postgres psql upload_data < filePath/uploadData.sql
  
 Run and test:
 
 Build project in the IDE (I used netbeans 8.1, webserver tomcat version 8...)
 
 Run app from IDE, on the page you will see three buttons (Choose file, Upload file abd Download result)
 
 Click 'Choose file' and select test file 'readMe.txt' in the project package, after selecting file, click button 'Upload' and then system shows the message 'process upload file' repeat this actions three times in 30 seconds to check limit for upload. After that click 'Download result' to see the result in the json file 
 
 
 
 
