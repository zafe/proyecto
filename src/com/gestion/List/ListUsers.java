package com.gestion.List;

public class ListUsers {
	public String userId;
    public String userName;
    
    public ListUsers(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String employeeName) {
        this.userName = userName;
    }

}
