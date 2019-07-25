package pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Fabiano on 08/05/2016.
 */
public class Produtcs implements Serializable {





    private String id;
    private String name;
/*
    public Produtcs(String id, String name, BigDecimal produtcsPrice, String image, BigDecimal price, int qtsTotal) {
        this.id = id;
        this.name = name;
        this.produtcsPrice = produtcsPrice;
        this.image = image;
        this.price = price;
        this.qtsTotal = qtsTotal;
    }
*/
    private BigDecimal produtcsPrice;
    private String image;
    private BigDecimal price;


    public int getQtsTotal() {
        return qtsTotal;
    }

    public void setQtsTotal(int qtsTotal) {
        this.qtsTotal = qtsTotal;
    }

    private int qtsTotal;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getProdutcsPrice() {
        return produtcsPrice;
    }
    public void setProdutcsPrice(BigDecimal produtcsPrice) {
        this.produtcsPrice = produtcsPrice;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
