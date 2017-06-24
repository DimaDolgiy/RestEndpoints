<%-- 
    Document   : index
    Created on : Jun 22, 2017, 1:55:08 PM
    Author     : dima_home
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

        <title>Upload Example</title>

    </head>
    <body>
        
        <div class="container">
            <div class="row">
                    
                <div class="jumbotron" style="margin-top: 30px">
                    <h2 style="text-align: center"> Choose file to upload</h2>
                    
                <form action="uploadFile" method="post" enctype="multipart/form-data">
                        
                    <p>
                        <div class="btn-group">
                             <span class="btn btn-success fileinput-button">
                                <i class="icon-plus icon-white"></i>
                                <span>Choose file...</span>
                                <input type="file" name="uploadedFile">
                            </span>
                            
                            <input type="submit" value="Upload file" name="upload" class="btn btn-success"/>
                            
                            <input type="submit" value="Download result" name="loadResult" class="btn btn-default"/>
                        </div>      
                    </p>
                    
                    <p>
                        ${message}
                    </p>
                </form>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
