package com.practice.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class readConfigFile {

    Properties properties;
    String path = "C:\\Users\\W3villa\\Desktop\\Automation Framework Development\\FrameworkDevelopment\\configuration\\config.properties";

    public readConfigFile(){

        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream(path);
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl(){
        String url = properties.getProperty("baseUrl");

        if(url!=null)
        {
            return url;
        }
        else {
            throw new RuntimeException("Url is not specified in Configuration file.");
        }
    }
    public String getBrowser(){
        String browser = properties.getProperty("browser");

        if(browser!=null)
        {
            return browser;
        }
        else {
            throw new RuntimeException("browser is not specified in Configuration file.");
        }
    }

    public String getEmail(){
        String emailId = properties.getProperty("email");
        if(emailId!=null){
            return emailId;
        }else {
            throw new RuntimeException(("Email id is not specified in Configuration file"));
        }
    }

    public String getPassword(){
        String password = properties.getProperty("password");
        if(password!=null){
            return password;
        }else {
            throw new RuntimeException(("Email id is not specified in Configuration file"));
        }
    }
}
