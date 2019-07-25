package com.plan2buy.ado;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.plan2buy.bean.Produtcs;
import com.plan2buy.bean.ShoppingCart;
import com.plan2buy.bean.CommerceItem;
import com.plan2buy.util.Util;
import com.plan2buy.factory.ConnectionFactory;

public class ShoppingCartADO extends ConnectionFactory {

	private static ShoppingCartADO instance;
	Util utl = new Util();
	
	public static ShoppingCartADO getInstance() {
		if (instance == null)
			instance = new ShoppingCartADO();
		return instance;

	}

	public void shoppingcartItemsIdDelete(String id) {
		Connection con = null;
		PreparedStatement pSt = null;
		con = createConnection();
		ResultSet rs = null;
		try {

			pSt = con.prepareStatement("delete  from shoppingcart  where shoppingcart.items = ? ");
			pSt.setString(1, id);
			pSt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Erro to remove shoppingcart item" + e);
			e.printStackTrace();
		} finally {
			closeConnection((com.mysql.jdbc.Connection) con, pSt, rs);
		}
	}

	public ArrayList<ShoppingCart> shoppingcart() {

		Connection con = null;
		PreparedStatement pSt = null;
		con = createConnection();
		ResultSet rs = null;
		
		ArrayList<ShoppingCart> shoppingcartList = new ArrayList<ShoppingCart>();

		try {

			pSt = con.prepareStatement("select sc.items,p.name,   ci.quantity, sc.amount from shoppingcart sc,"
					+ " commerceitem ci,  produtcs p "
					+ " where sc.items = ci.id  and ci.product_id = p.id ");
			
			rs = pSt.executeQuery();

			while (rs.next()) {
				ShoppingCart shoppingcart = new ShoppingCart();
				
				shoppingcart.setItems(rs.getString("items"));
				shoppingcart.setNameProduct(rs.getString("name"));
				shoppingcart.setQuanty(rs.getInt("quantity"));
				shoppingcart.setAmount(rs.getBigDecimal("amount"));
				shoppingcartList.add(shoppingcart);
			}

		} catch (Exception e) {
			System.out.println("Erro to list shoppingcart by session" + e);
			e.printStackTrace();
		} finally {
			closeConnection((com.mysql.jdbc.Connection) con, pSt, rs);
		}

		return shoppingcartList;
	}

	public ArrayList<CommerceItem> shoppingcartItemsPost(CommerceItem commerceItem) {

		Connection con = null;
		PreparedStatement pSt = null;
		con = createConnection();
		ResultSet rs = null;
		String nextID = utl.nextId();
		ArrayList<CommerceItem> ret =new ArrayList<CommerceItem>();
		
		try {
			
			
			pSt = con.prepareStatement("INSERT INTO commerceitem (`id`, `product_id`, `quantity`,`amount`)VALUES(?, ?, ?, ?)");
			pSt.setString(1, nextID);
			pSt.setString(2, commerceItem.getProduct_id());
			pSt.setInt(3, commerceItem.getQuantity());
			pSt.setBigDecimal(4, getAmount(commerceItem));

			pSt.executeUpdate();
			
			ret = this.returnCommerceItemID(nextID);

		} catch (Exception e) {
			System.out.println("Erro to insert  commerceitem itens" + e);
			e.printStackTrace();
		} finally {
			
			closeConnection((com.mysql.jdbc.Connection) con, pSt, rs);
		}
		
		insertSC(ret);
		return ret;
	}
	
	private void insertSC (ArrayList<CommerceItem> commerce){
		
		Connection con = null;
		PreparedStatement pSt = null;
		con = createConnection();
		ResultSet rs = null;
		
		ArrayList<CommerceItem> ret =new ArrayList<CommerceItem>();
		
		try {
			
			
			pSt = con.prepareStatement("INSERT INTO shoppingcart (`items`, `amount`)VALUES(?, ?)");
			pSt.setString(1, commerce.get(0).getId());
			pSt.setBigDecimal(2, commerce.get(0).getAmount());

			pSt.executeUpdate();
			
			

		} catch (Exception e) {
			System.out.println("Erro to insert  Shopping itens" + e);
			e.printStackTrace();
		} finally {
			
			closeConnection((com.mysql.jdbc.Connection) con, pSt, rs);
		}
		
		
	}
	
	
	/*public ArrayList<CommerceItem> updateShoppingcartItemsByUser(ShoppingCart shoppingCart) {

		Connection con = null;
		PreparedStatement pSt = null;
		con = createConnection();
		ResultSet rs = null;
		
		ArrayList<CommerceItem> ret =new ArrayList<CommerceItem>();
		
		try {
			
			
			pSt = con.prepareStatement("INSERT INTO commerceitem ('id', 'product_id',"
							+ " 'quantity', 'amount') VALUES ( ? , ?  , ? , ? )");
			pSt.setString(1, nextID);
			pSt.setString(2, commerceItem.getProduct_id());
			pSt.setInt(3, commerceItem.getQuantity());
			pSt.setBigDecimal(4, getAmount(commerceItem));

			pSt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Erro to insert  commerceitem itens" + e);
			e.printStackTrace();
		} finally {
			
			closeConnection((com.mysql.jdbc.Connection) con, pSt, rs);
		}
		
		return ret;
	}*/
	
	
	
	
	
	private BigDecimal getAmount(CommerceItem commercetem){
		
		Connection con = null;
		PreparedStatement pSt = null;
		con = createConnection();
		ResultSet rs = null;
		BigDecimal itemCost  = BigDecimal.ZERO;
		BigDecimal amountRet = BigDecimal.ZERO;
		
		try {
			pSt = con.prepareStatement("select po.price from produtcs po where po.id = ? ");
			pSt.setString(1, commercetem.getProduct_id());
			rs = pSt.executeQuery();
			while (rs.next()) {
				itemCost =rs.getBigDecimal("price");
			}
			
			
			amountRet = utl.calculateCost(commercetem.getQuantity(), itemCost);
			
						
		} catch (Exception e) {
			System.out.println("Erro to get price to Produtcs" + e);
			e.printStackTrace();
		}finally {
			closeConnection((com.mysql.jdbc.Connection) con, pSt, rs);
		}
		
		return amountRet;
	}
	
	
	private ArrayList<CommerceItem> returnCommerceItemID(String id){
		Connection con = null;
		PreparedStatement pSt = null;
		con = createConnection();
		ResultSet rs = null;
		ArrayList<CommerceItem> commerceItemList =new ArrayList<CommerceItem>();
		try {
			
			pSt = con.prepareStatement("SELECT ci.id , ci.product_id, ci.quantity,"
					+ "ci.amount FROM commerceitem ci WHERE ci.id = ?");
			pSt.setString(1, id);
			rs = pSt.executeQuery();

			while (rs.next()) {
				CommerceItem commerceItem = new CommerceItem();
				commerceItem.setId(id);
				commerceItem.setProduct_id(rs.getString("product_id"));
				commerceItem.setQuantity(rs.getInt("quantity"));
				commerceItem.setAmount(rs.getBigDecimal("amount"));
				commerceItemList.add(commerceItem);
			}
			
		} catch (Exception e) {
			System.out.println("Erro to list commerceitem by ID" + e);
			e.printStackTrace();
		} finally {
			closeConnection((com.mysql.jdbc.Connection) con, pSt, rs);
		}
		
		return commerceItemList;
	}
	

}
