/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.application.data;


/**
 *
 * @author dima_home
 */
public class ExpectedResult {
    
    private String value;    
    private Integer count;    
    

    public ExpectedResult(String value, Integer count) {
        this.value = value;
        this.count = count;
    }
    
    public void incCount() {
        count++;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    
    
    
}
