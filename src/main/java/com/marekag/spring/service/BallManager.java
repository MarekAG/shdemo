package com.marekag.spring.service;

import java.util.List;

import com.marekag.spring.domain.Ball;
import com.marekag.spring.domain.Manufacturer;

public interface BallManager {
	
	void addBall(Ball ball);
	Ball getBall(Ball ball);
	Ball getBall(Long id);
	List<Ball> getBallsByColor(String color);
	List<Ball> getAllBalls();
	void updateBall(Ball ball, String type);
	void deleteBall(Ball ball);
	void deleteBall(Long id);
	
	void addManufacturer(Manufacturer manufacturer);
	Manufacturer getManufacturer(Manufacturer manufacturer);
	Manufacturer getManufacturer(Long id);
	List<Manufacturer> getManufacturersByYOC(int YOC);
	List<Manufacturer> getAllManufacturers();
	void updateManufacturer(Manufacturer manufacturer, String name);
	void deleteManufacturer(Manufacturer manufacturer);
	void deleteManufacturer(Long id);
	
	List<Ball> getManufacturerBalls(Manufacturer manufacturer);
	List<Ball> getManufacturerBalls(Long id);
	void assignBallToManufacturer(Ball ball, Manufacturer manufacturer);
	void assignBallToManufacturer(Long ballId, Long manufacturerId);
	void removeManufacturerFromBall(Ball ball);
	void removeManufacturerFromBall(Long id);

}
