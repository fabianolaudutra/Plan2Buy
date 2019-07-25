package com.plan2buy.bean;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Produtcs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8072417136000628098L;
	
	private String id;
	private String name;
	private BigDecimal produtcsPrice;
	private String image;
	private BigDecimal price;
	
	
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
