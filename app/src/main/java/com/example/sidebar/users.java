package com.example.sidebar;

public class users {
    private String id,name,passeord,imgurl;
    public users(){

    }
    public users(String id,String imageUrl,String name,String password)
    {
        this.id=id;
        this.imgurl=imageUrl;
        this.name=name;
        this.passeord=password;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasseord() {
        return passeord;
    }

    public String getImgurl() {
        return imgurl;
    }
}
