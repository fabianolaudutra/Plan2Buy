package com.plan2buy.iface;

import java.util.ArrayList;

import com.plan2buy.ado.ShoppingCartADO;
import com.plan2buy.bean.CommerceItem;
import com.plan2buy.bean.ShoppingCart;

public class ShoppingCartIface {

	
	//return products  basket 
    public ArrayList<ShoppingCart> shoppingcartGet() {
		
		return ShoppingCartADO.getInstance().shoppingcart();
	}
	
    // delete items  basket
 	public void  shoppingcartItemsIdDelete(String id){
	 
 		ShoppingCartADO.getInstance().shoppingcartItemsIdDelete(id);
 	}
	
 	 // insert items  basket return CommerceItem
 	public ArrayList<CommerceItem>  shoppingcartItemsPost(CommerceItem commerceItem){
 		//System.out.println( " commerceItem :  " + commerceItem.getProduct_id() +": " + commerceItem.getQuantity() );
 		
 		return ShoppingCartADO.getInstance().shoppingcartItemsPost(commerceItem);
 	}
 	
}
