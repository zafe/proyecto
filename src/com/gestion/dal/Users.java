package com.gestion.dal;

import com.gestion.List.ListUsers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Users {
	public ObservableList<String> allUser = FXCollections.observableArrayList();

    public String id;
    public String userName;
    public String fullName;
    public String emailAddress;
    public String contactNumber;
    public String salary;
    public String address;
    public String password;
    public String status;
    public String imagePath;
   // public Blob userImage;
//    public File userImage;
    public String date;
    public String creatorId;
    public Image image;

    public ObservableList<ListUsers> userLists = FXCollections.observableArrayList();

}
