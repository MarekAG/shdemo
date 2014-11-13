package com.marekag.spring.service;

import java.util.List;

import com.marekag.spring.domain.Ball;

public interface BallManager {
	
	void addBall(Ball ball);
	Ball getBall(Ball ball);
	Ball getBall(Long id);
	List<Ball> getAllBalls();
	void updateBall(Ball ball, String type);
	void deleteBall(Ball ball);
	void deleteBall(Long id);
	void deleteAllBalls();	

}
