package com.marekag.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Manufacturer {
	
	private Long id;
	
	private String name = "";
	private String address ="";
	private int YOC = 0;
	private List<Ball> balls = new ArrayList<Ball>();
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getYOC() {
		return YOC;
	}
	public void setYOC(int yOC) {
		YOC = yOC;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Ball> getBalls() {
		return balls;
	}
	public void setBalls(List<Ball> balls) {
		for (Ball ball: balls) {
			ball.setManufacturer(this);
		   }

		   this.balls = balls;
	}

	

}
