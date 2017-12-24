package app.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "goods", schema = "shop" /*, catalog = ""*/)
public class GoodsEntity {
    private int goodsId;
    private int sellerId;
    private Integer buyerId;
    private String description;
    private String shortDesc;
    private long price;
    private String photoAddress;

    public GoodsEntity() {

    }

    public GoodsEntity(int goodsId, int sellerId, String description, String shortDesc, long price, String photoAddress) {
        this.goodsId = goodsId;
        this.sellerId = sellerId;
        this.description = description;
        this.shortDesc = shortDesc;
        this.price = price;
        this.photoAddress = photoAddress;
    }

    @Id
    @Column(name = "goods_id", nullable = false)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "seller_id", nullable = false)
    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name = "buyer_id", nullable = true)
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "short_desc", nullable = true, length = -1)
    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Basic
    @Column(name = "photo_address", nullable = true, length = 100)
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
        GoodsEntity that = (GoodsEntity) o;
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
