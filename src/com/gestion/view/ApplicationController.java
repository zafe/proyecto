package com.gestion.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gestion.dal.Users;
import com.gestion.database.DBConnection;
import com.gestion.database.DBProperties;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApplicationController {




    @FXML
    private BorderPane appContent;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private AnchorPane acMain;

    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnCamiones;
    @FXML
    private ImageView imgCamionBtn;
    @FXML
    private Button btnVentas;
    @FXML
    private ImageView imgVentasBtn;
    @FXML
    private Button btnCompras;
    @FXML
    private ImageView imgComprasBtn;
    @FXML
    private Button btnEmployees;
    @FXML
    private ImageView imgEmployeesBtn;
    @FXML
    private Button btnSettings;
    @FXML
    private ImageView imgSettingsBtn;
    

   

    String usrName;
    String id;

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
     DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    Users users = new Users();
    //UsersGetway usersGetway = new UsersGetway();

    //private userNameMedia usrNameMedia;

    /*public userNameMedia getUsrNameMedia() {
        return usrNameMedia;
    }

    public void setUsrNameMedia(userNameMedia usrNameMedia) {
        lblUserId.setText(usrNameMedia.getId());
        lblUsrName.setText(usrNameMedia.getUsrName());
        id = usrNameMedia.getId();
        usrName = usrNameMedia.getUsrName();

        this.usrNameMedia = usrNameMedia;
    }*/


    

    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";

    Image home = new Image("/resources/images/icon/Home.png");
    Image homeRed = new Image("/resources/images/icon/HomeRed.png");
    Image camiones = new Image("/resources/images/icon/truck.png");
    Image camionesRed = new Image("/resources/images/icon/truckRed.png");
    Image ventas = new Image("/resources/images/icon/sells.png");
    Image ventasRed = new Image("/resources/images/icon/sellsRed.png");
    Image compras = new Image("/resources/images/icon/compras.png");
    Image comprasRed = new Image("/resources/images/icon/comprasRed.png");
    Image employees = new Image("/resources/images/icon/Account.png");
    Image employeesRed = new Image("/resources/images/icon/AccountRed.png");
    Image settings = new Image("/resources/images/icon/Settings.png");
    Image settingsRed = new Image("/resources/images/icon/settingsRed.png");

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    public void initialize(URL url, ResourceBundle rb) {


    }



    @FXML
    private void acMain(KeyEvent event) {
        if (event.getCode() == KeyCode.F11) {
            Stage stage = (Stage) acMain.getScene().getWindow();
            stage.setFullScreen(true);
        }
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event){
       

    }

    @FXML
    private void btnCamionesOnClick(ActionEvent event) throws IOException {
       
    }

    @FXML
    private void btnVentasOnClick(ActionEvent event) throws IOException {
        

    }

    @FXML
    private void btnComprasOnClick(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void btnEmployeesOnClick(ActionEvent event) {

        
           
    }

    @FXML
    private void btnSettingsOnClick(ActionEvent event) {
        

    }

    @FXML
    private void hlUpdateAccount(ActionEvent event) {

    }


    @FXML
    private void acMainOnMouseMove(MouseEvent event) {

    }

    public void permission() {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from "+db+".UserPermission where UserId=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(17) == 0) {
                	btnVentas.setDisable(true);
                }
                if (rs.getInt(15) == 0) {
                	btnCompras.setDisable(true);
                } else {

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void homeActive() {
        
    	imgCamionBtn.setImage(camiones);
    	imgComprasBtn.setImage(ventas);
        imgVentasBtn.setImage(compras);
        imgEmployeesBtn.setImage(employees);
        imgSettingsBtn.setImage(settings);
        btnHome.setStyle(activeStyle);
        btnCamiones.setStyle(defultStyle);
        btnCompras.setStyle(defultStyle);
        btnVentas.setStyle(defultStyle);
        btnEmployees.setStyle(defultStyle);
        btnSettings.setStyle(defultStyle);
    }

    private void camionesActive() {
        
    	imgCamionBtn.setImage(camionesRed);
    	imgComprasBtn.setImage(ventas);
        imgVentasBtn.setImage(compras);
        imgEmployeesBtn.setImage(employees);
        imgSettingsBtn.setImage(settings);
        btnHome.setStyle(defultStyle);
        btnCamiones.setStyle(activeStyle);
        btnCompras.setStyle(defultStyle);
        btnVentas.setStyle(defultStyle);
        btnEmployees.setStyle(defultStyle);
        btnSettings.setStyle(defultStyle);
    }

    private void ventasActive() {
        
    	imgCamionBtn.setImage(camiones);
    	imgComprasBtn.setImage(ventasRed);
        imgVentasBtn.setImage(compras);
        imgEmployeesBtn.setImage(employees);
        imgSettingsBtn.setImage(settings);
        btnHome.setStyle(defultStyle);
        btnCamiones.setStyle(defultStyle);
        btnCompras.setStyle(activeStyle);
        btnVentas.setStyle(defultStyle);
        btnEmployees.setStyle(defultStyle);
        btnSettings.setStyle(defultStyle);
    }

    private void comprasActive() {
        
    	imgCamionBtn.setImage(camiones);
    	imgComprasBtn.setImage(ventas);
        imgVentasBtn.setImage(comprasRed);
        imgEmployeesBtn.setImage(employees);
        imgSettingsBtn.setImage(settings);
        btnHome.setStyle(defultStyle);
        btnCamiones.setStyle(defultStyle);
        btnCompras.setStyle(defultStyle);
        btnVentas.setStyle(activeStyle);
        btnEmployees.setStyle(defultStyle);
        btnSettings.setStyle(defultStyle);
    }

    private void employeesActive() {
        
    	imgCamionBtn.setImage(camiones);
    	imgComprasBtn.setImage(ventas);
        imgVentasBtn.setImage(compras);
        imgEmployeesBtn.setImage(employees);
        imgSettingsBtn.setImage(settings);
        btnHome.setStyle(defultStyle);
        btnCamiones.setStyle(defultStyle);
        btnCompras.setStyle(defultStyle);
        btnVentas.setStyle(defultStyle);
        btnEmployees.setStyle(activeStyle);
        btnSettings.setStyle(defultStyle);
    }

    private void settingsActive() {
        
    	imgCamionBtn.setImage(camiones);
    	imgComprasBtn.setImage(ventas);
        imgVentasBtn.setImage(compras);
        imgEmployeesBtn.setImage(employees);
        imgSettingsBtn.setImage(settingsRed);
        btnHome.setStyle(defultStyle);
        btnCamiones.setStyle(defultStyle);
        btnCompras.setStyle(defultStyle);
        btnVentas.setStyle(defultStyle);
        btnEmployees.setStyle(defultStyle);
        btnSettings.setStyle(activeStyle);
    }



}
