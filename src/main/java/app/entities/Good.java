package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "goods")
public class Good{
    private int goodsId;
    private int sellerId;
    private String name;
    private Integer buyerId;
    private String description;
    private String shortDesc;
    private double price;
    private String photoAddress;

    public Good() {

    }

    public Good(int sellerId, String name, String shortDesc, String description, double price, String photoAddress) {
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.shortDesc = shortDesc;
        this.price = price;
        this.photoAddress = photoAddress;
    }

    @Id
    @Column(name = "goodsId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "sellerId", nullable = false)
    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name = "buyerId", nullable = true)
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "description", nullable = true, length = 5000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "shortDesc", nullable = true, length = 500)
    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "photoAddress", nullable = true, length = 100)
    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good that = (Good) o;
        return goodsId == that.goodsId &&
                sellerId == that.sellerId &&
                price == that.price &&
                Objects.equals(buyerId, that.buyerId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(shortDesc, that.shortDesc) &&
                Objects.equals(photoAddress, that.photoAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsId, sellerId, buyerId, description, shortDesc, price, photoAddress);
    }
}
