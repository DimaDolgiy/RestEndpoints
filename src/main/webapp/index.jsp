<%-- 
    Document   : index
    Created on : Jun 22, 2017, 1:55:08 PM
    Author     : dima_home
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>File Upload Example in JSP and Servlet - Java web application</title>

    </head>
    <body>

        <div>

            <h3> Choose File to Upload in Server </h3>
            
            <p>Message: ${message}</p>

            <form action="uploadFile" method="post" enctype="multipart/form-data">

                <input type="file" name="uploadedFile" />

                <input type="submit" value="upload" name="upload" />
                
                
                
                <input type="submit" value="loadResult" name="loadResult"/>
            </form>     
            
            

        </div>

       
    </body>
</html>
