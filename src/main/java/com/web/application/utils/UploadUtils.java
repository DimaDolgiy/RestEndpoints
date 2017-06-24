/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.application.utils;

import com.google.gson.Gson;
import com.web.application.data.ExpectedResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author dima_home
 */
public class UploadUtils {
    
    private final static int PROCESSES_LIMIT = 3;
    
    private volatile Integer processCount = 0;

    private static UploadUtils instance;

    private DataSource dataSource;
    
    
    
    
    private UploadUtils() 
    {
        try {
            Context ctx = new InitialContext();
            
            dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/postgres");   
        } catch (NamingException e) {
              e.printStackTrace();
        }
    }
    
    public static synchronized UploadUtils getInstance()
    {
        if (instance == null)
        {
            instance = new UploadUtils();
        }
        
        return instance;
    }
    
    public synchronized boolean processUploadedFile(InputStream inputStream) throws IOException, SQLException
    {
        return processUploadedFile(inputStream, 0);
    }
    
    public synchronized boolean processUploadedFile(final InputStream inputStream, final long waitTime) throws SQLException
    {
        if (processCount < PROCESSES_LIMIT)
        {
            processCount++;
            Thread th = new Thread(new Runnable() 
            {
                @Override
                public void run() 
                {
                    if (waitTime > 0) 
                    {
                        try {
                            Thread.sleep(waitTime);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Date date = new Date();

                    ByteArrayOutputStream os = new ByteArrayOutputStream();

                    byte[] buf = new byte[1000];

                    try {
                        for (int nChunk = inputStream.read(buf); nChunk!=-1; nChunk = inputStream.read(buf))
                        {
                            os.write(buf, 0, nChunk); 
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    String fileValue = new String(os.toByteArray());

                    try (Connection connection = dataSource.getConnection()) 
                    {            
                        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO expected_result (date_upload, file_data) VALUES ( ? , ? )");
                        
                        preparedStatement.setDate(1, new java.sql.Date(date.getTime()));
                        
                        preparedStatement.setString(2, fileValue);

                        preparedStatement.executeUpdate();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } 
                    
                    processCount--;
                }
            });
            th.setDaemon(true);
            
            th.start();
            
            return true;
        }
        
        return false;
    }
    
    public synchronized String loadResult() throws SQLException, IOException
    {        
        try(Connection connection = dataSource.getConnection())
        {
            LinkedHashMap<String, ExpectedResult> resultMap = new LinkedHashMap();

            Statement statement = connection.createStatement();
            
            String selectQuery = "SELECT file_data FROM expected_result";
            
            ResultSet resultSet = statement.executeQuery(selectQuery);
            
            String[] arrayLines;
            
            while(resultSet.next())
            {
                String line = resultSet.getString("file_data");
                
                if(line != null)
                {
                    arrayLines = line.split("\n");
                
                    for(String value : arrayLines)
                    {
                        ExpectedResult er = resultMap.get(value);
                        if(er == null)
                        {
                            resultMap.put(value, new ExpectedResult(value, 1));
                        }
                        else
                        {
                            er.incCount();
                        }
                    } 
                }   
            }
            
            return new Gson().toJson(resultMap, LinkedHashMap.class);
        }
    }

}
