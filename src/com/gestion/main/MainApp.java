package com.gestion.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestion.database.DBModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application{
	


public MainApp() {
        
        DBModel model = new DBModel();
        model.createDataBase();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
        	
        	
        	Parent root = FXMLLoader.load(getClass().getResource("/com/gestion/view/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome to LogiServ -Login");
            //primaryStage.getIcons().add(new Image("/image/icon.png"));
         
            primaryStage.setMaximized(false);
            primaryStage.setMinHeight(600.0);
            primaryStage.setMinWidth(400.0);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
