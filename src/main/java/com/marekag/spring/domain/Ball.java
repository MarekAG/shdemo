package com.marekag.spring.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "ball.all", query = "SELECT b FROM Ball b"),
	@NamedQuery(name = "ball.getByColor", query = "SELECT b FROM Ball b WHERE b.color = :color"),
	@NamedQuery(name = "ball.deleteAll", query = "DELETE FROM Ball")
	})
public class Ball {
	
	private Long id;
	
	private String name = "";
	private String color = "";
	private String type = "";
	private int size = 5;
	private Manufacturer manufacturer = new Manufacturer();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="MANUFACTURER_ID", nullable=true)
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	

}

