package com.plan2buy.business;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import com.plan2buy.bean.Produtcs;
import com.plan2buy.iface.ProductsIFace;



@Path("/produtcs")
public class ProdutcsServices {

	@GET
	@Produces("application/json")
	public ArrayList<Produtcs> productsGet(){
		
		return new ProductsIFace().productsGet();
	}
	
}
