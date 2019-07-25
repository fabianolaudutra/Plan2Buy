package com.plan2buy.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Util {

	private SecureRandom random = new SecureRandom();
	  BigDecimal itemCost=new BigDecimal(BigInteger.ZERO,  2);
    BigDecimal totalCost=new BigDecimal(BigInteger.ZERO,  2);
    
    // generete ID
	  public String nextId() {
	    return new BigInteger(30, random).toString(32);
	  }
	
	
	  //   calculate
   public BigDecimal calculateCost(int itemQuantity,BigDecimal itemPrice)
     { 
         BigDecimal   itemCost = itemPrice.multiply(new BigDecimal(itemQuantity)); 
           return totalCost.add(itemCost); 
     }
	
}
