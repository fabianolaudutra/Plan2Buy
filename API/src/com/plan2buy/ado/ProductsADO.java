package com.plan2buy.ado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.plan2buy.bean.Produtcs;
import com.plan2buy.factory.ConnectionFactory;

public class ProductsADO extends ConnectionFactory {

	private static ProductsADO instance;

	public static ProductsADO getInstance() {
		if (instance == null)
			instance = new ProductsADO();
		return instance;

	}

	public ArrayList<Produtcs> productsGet() {

		Connection con = null;
		PreparedStatement pSt = null;
		con = createConnection();
		ResultSet rs = null;

		ArrayList<Produtcs> produtcsList = new ArrayList<Produtcs>();

		try {

			pSt = con.prepareStatement("SELECT p.id, p.name, p.price, p.image from produtcs p ");
			rs = pSt.executeQuery();

			while (rs.next()) {
				Produtcs produtcs = new Produtcs();
				produtcs.setId(rs.getString("id"));
				produtcs.setName(rs.getString("name"));
				produtcs.setPrice(rs.getBigDecimal("price"));
				produtcs.setImage(rs.getString("image"));
				produtcsList.add(produtcs);
			}

		} catch (Exception e) {
			System.out.println("Erro to list Produtcs" + e);
			e.printStackTrace();
		} finally {
			closeConnection((com.mysql.jdbc.Connection) con, pSt, rs);
		}

		return produtcsList;
	}

}
