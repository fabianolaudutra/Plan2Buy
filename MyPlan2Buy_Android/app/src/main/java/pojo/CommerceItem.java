package pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Fabiano.
 */
public class CommerceItem implements Serializable {

    private String id;
/*
    public CommerceItem(String id, String product_id, Integer quantity, BigDecimal amount) {
        this.id = id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.amount = amount;
    }
*/
    private String product_id;
    private Integer quantity;
    private BigDecimal amount;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
