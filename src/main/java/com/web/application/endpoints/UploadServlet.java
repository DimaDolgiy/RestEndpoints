/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.application.endpoints;


import com.web.application.utils.UploadUtils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


/**
 *
 * @author dima_home
 */
@MultipartConfig
public class UploadServlet extends HttpServlet {
    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String message;
        
        if(request.getParameter("upload") != null)
        {
            
            Part file = request.getPart("uploadedFile");

            if(file != null)
            {
                boolean result;

                InputStream fileContent = file.getInputStream();

                try
                {
                    result = UploadUtils.getInstance().processUploadedFile(fileContent, 50000);

                    if(result)
                    {
                        message = "process upload file";
                        session.setAttribute("message", message);
                    }
                    else
                    {
                        message = "limit exceeded - maximum 3 files";
                        session.setAttribute("message", message);
                    }


                } catch (SQLException ex) 
                {
                   ex.printStackTrace();
                }

                response.sendRedirect("index.jsp");
            }  
            
        }
        
        if(request.getParameter("loadResult") != null)
        {
            getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }  
}
