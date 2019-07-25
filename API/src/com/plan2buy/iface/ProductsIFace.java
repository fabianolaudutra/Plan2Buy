package com.plan2buy.iface;

import java.util.ArrayList;

import com.plan2buy.ado.ProductsADO;
import com.plan2buy.bean.Produtcs;


public class ProductsIFace {

public ArrayList<Produtcs> productsGet() {
		
		return ProductsADO.getInstance().productsGet();
	}
	
	
}
