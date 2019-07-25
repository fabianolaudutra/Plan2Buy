package com.plan2buy.business;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.plan2buy.bean.CommerceItem;
import com.plan2buy.bean.ShoppingCart;
import com.plan2buy.iface.ShoppingCartIface;

@Path("/shoppingcart")
public class ShoppingCartService {

	
		/*
		@DELETE
		@Path("/items/{id}")
		@Produces("application/json")
		public String  test(@PathParam("id") String id) {
		 return id;
		}
		*/
		
		@DELETE
		@Path("/items/{id}")
		@Produces("application/json")
		public void shoppingcartItemsIdDelete(@PathParam("id") String id) {
			try {
				
				//System.out.println("testa" + id);
				new ShoppingCartIface().shoppingcartItemsIdDelete(id);
			} catch (Exception e) {
				throw new RuntimeException("Delete error :" + e);
			}

		}
		
		@GET
		@Produces("application/json")
		public ArrayList<ShoppingCart> shoppingcartGet(){
			
			return new ShoppingCartIface().shoppingcartGet();
		}
		
		@POST 
		@Path("/items/{idp}/{qtd}")
		@Produces("application/json")
		public ArrayList<CommerceItem> items(@PathParam("idp") String idprodutcs, @PathParam("qtd") int qtd){
			//System.out.println(idprodutcs + "- " +qtd );
			CommerceItem commerceItem = new  CommerceItem();
			commerceItem.setProduct_id(idprodutcs);
			commerceItem.setQuantity(qtd);
			
			//System.out.println( " commerceItem :  " + commerceItem.getProduct_id() +": " + commerceItem.getQuantity() );
			
			 return  new ShoppingCartIface().shoppingcartItemsPost(commerceItem);
		}
		
}

