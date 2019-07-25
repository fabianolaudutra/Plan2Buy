package pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Fabiano on 08/05/2016.
 */
public class ShoppingCart implements Serializable {
    private String items;
    private BigDecimal amount;
    /*
    public ShoppingCart (String items, BigDecimal amount){
        this.items = items;
        this.amount = amount;
    }
*/
    public String getItems() {
        return items;
    }
    public void setItems(String items) {
        this.items = items;
    }


    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
