package app.entities;

import java.sql.Connection;

public class Goods {
    private String name;
    private String desc;
    private String short_desc;
    private long id;
    private String photoPath;
    private User seller;
    private double price;

    public Goods() {

    }

    public Goods(String name, String short_desc, String desc, long id, String photoPath, User seller, double price) {
        this.name = name;
        this.short_desc = short_desc;
        this.desc = desc;
        this.id = id;
        this.photoPath = photoPath;
        this.seller = seller;
        this.price = price;
    }

//-----------------------------------------
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
//------------------------------------------
}
