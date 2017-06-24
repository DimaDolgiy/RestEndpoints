/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.application.endpoints;

import com.web.application.data.UploadUtils;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dima_home
 */
public class ResultServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        super.doGet(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try 
        {
            String loadedJson = UploadUtils.getInstance().loadResult();

            byte[] bytesArray = loadedJson.getBytes("UTF-8");

            response.setContentType("application/json");
            response.setContentLength(bytesArray.length);
            response.setHeader("Content-disposition","attachment;filename="+ "expected-result.json");
            response.getOutputStream().write(loadedJson.getBytes("UTF-8"));


        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
}
