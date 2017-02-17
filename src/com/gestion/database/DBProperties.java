package com.gestion.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class DBProperties {
	Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;

    public void mkDbProperties() {
        
        try {
            output = new FileOutputStream("database.properties");
            properties.setProperty("host", "localhost");
            properties.setProperty("port", "3306");
            properties.setProperty("db", "logserv");
            properties.setProperty("user", "root");
            properties.setProperty("password", "123456isa");
            properties.store(output, null);
            output.close();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String loadPropertiesFile() {
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            return properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("DDDD");
        }
        return "";
    }

}
