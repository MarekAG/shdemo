package com.marekag.spring.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.marekag.spring.domain.Ball;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BallManagerTest {
	
	@Autowired
	BallManager ballManager;
	
	private final static String NAME_1 = "Pilka1";
	private final static String COLOR_1 = "Red";
	private final static String TYPE_1 = "Football";
	private final static int SIZE_1 = 5;

	private final static String NAME_2 = "Pilka2";
	private final static String COLOR_2 = "Blue";
	private final static String TYPE_2 = "Volleyball";
	private final static int SIZE_2 = 4;
	
	int initialNrOfBalls = 0;
	int finalNrOfBalls = 0;
	
	@Before
	public void beforeTests() {
		initialNrOfBalls = ballManager.getAllBalls().size();
		System.out.println("Przed "+ initialNrOfBalls);
	}
	
	@After
	public  void afterTests() {
		finalNrOfBalls = ballManager.getAllBalls().size();
		System.out.println("Po "+ finalNrOfBalls);
	}
	
	@Test
	public void addBallTest() {		
		
		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);
		
		ballManager.addBall(ball);
		
		assertNotNull(ballManager.getBall(ball));
		Ball newBall = ballManager.getBall(ball);
		
		assertEquals(NAME_1, newBall.getName());
		assertEquals(COLOR_1, newBall.getColor());
		assertEquals(SIZE_1, newBall.getSize());
		assertEquals(TYPE_1, newBall.getType());
		
		ballManager.deleteBall(ball.getId());
		
	}
	
	@Test
	public void updateBallTest() {
		
		
		// dodanie dwóch piłek
		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);
		
		ballManager.addBall(ball);
		
		Ball ball2 = new Ball();
		ball2.setName(NAME_1);
		ball2.setColor(COLOR_1);
		ball2.setSize(SIZE_1);
		ball2.setType(TYPE_1);
		
		ballManager.addBall(ball2);
		
		assertNotNull(ballManager.getBall(ball));
		Ball newBall = ballManager.getBall(ball);
		// pobranie pierwszej piłki
		assertEquals(NAME_1, newBall.getName());
		assertEquals(COLOR_1, newBall.getColor());
		assertEquals(SIZE_1, newBall.getSize());
		assertEquals(TYPE_1, newBall.getType());
		// zmiana typu
		newBall.setType(TYPE_2);
		
		assertNotNull(ballManager.getBall(newBall));
		Ball newestBall = ballManager.getBall(newBall);
		// sprawdzenie czy pierwsza się zmieniła
		assertEquals(NAME_1, newestBall.getName());
		assertEquals(COLOR_1, newestBall.getColor());
		assertEquals(SIZE_1, newestBall.getSize());
		assertEquals(TYPE_2, newestBall.getType());
		
		assertNotNull(ballManager.getBall(ball2));
		Ball newBall2 = ballManager.getBall(ball2);
		// sprawdzenie czy druga się nie zmieniła
		assertEquals(NAME_1, newBall2.getName());
		assertEquals(COLOR_1, newBall2.getColor());
		assertEquals(SIZE_1, newBall2.getSize());
		assertEquals(TYPE_1, newBall2.getType());
		
		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());
	}
	
	@Test
	public void deleteBallTest() {
		
		//dodanie dwóch piłek
		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);
		
		ballManager.addBall(ball);
		
		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);
		
		ballManager.addBall(ball2);
		
		assertNotNull(ballManager.getBall(ball));
		Ball newBall = ballManager.getBall(ball);
		// sprawdzenie czy pierwsza jest w bazie
		assertEquals(NAME_1, newBall.getName());
		assertEquals(COLOR_1, newBall.getColor());
		assertEquals(SIZE_1, newBall.getSize());
		assertEquals(TYPE_1, newBall.getType());
		// usunięcie pierwszej z bazy
		ballManager.deleteBall(newBall);
		// ponowne sprawdzenie czy pierwsza jest w bazie
		assertNull(ballManager.getBall(newBall));
		assertNull(ballManager.getBall(ball));
		
		// sprawdzenie czy możemy pobrać drugą
		assertNotNull(ballManager.getBall(ball2));
		Ball newBall2 = ballManager.getBall(ball2);
		
		// sorawdzenie czy to na pewno ten rekord
		assertEquals(NAME_2, newBall2.getName());
		assertEquals(COLOR_2, newBall2.getColor());
		assertEquals(SIZE_2, newBall2.getSize());
		assertEquals(TYPE_2, newBall2.getType());
		
		ballManager.deleteBall(ball2.getId());

	}
	
	@Test
	public void getByColor() {
		
		List<Ball> blueBalls = ballManager.getBallsByColor(COLOR_2);
		
		for(Ball testingBall : blueBalls) {
			assertEquals(COLOR_2, testingBall.getColor());
		}
		
		int initialBlueBallsSize = blueBalls.size();
		
		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);
		
		ballManager.addBall(ball);
		
		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);
		
		ballManager.addBall(ball2);
		
		List<Ball> blueBalls2 = ballManager.getBallsByColor(COLOR_2);
		
		assertEquals(initialBlueBallsSize+1, blueBalls2.size());
		
		for(Ball testingBall : blueBalls2) {
			assertEquals(COLOR_2, testingBall.getColor());
		}
		
		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());
	}
//	@Test
//	public void deleteAllBallsTest() {
//		ballManager.deleteAllBalls();
//		
//		assertEquals(0, ballManager.getAllBalls().size());
//		
//		Ball ball = new Ball();
//		ball.setName(NAME_1);
//		ball.setColor(COLOR_1);
//		ball.setSize(SIZE_1);
//		ball.setType(TYPE_1);
//		
//		ballManager.addBall(ball);
//
//		Ball Ball2 = new Ball();
//		Ball2.setName(NAME_2);
//		Ball2.setColor(COLOR_2);
//		Ball2.setSize(SIZE_2);
//		Ball2.setType(TYPE_2);
//		
//		ballManager.addBall(Ball2);
//		
//		assertEquals(2, ballManager.getAllBalls().size());
//		
//		ballManager.deleteAllBalls();
//		
//		assertEquals(0, ballManager.getAllBalls().size());
//		
//	}
	
	@Test
	public void getBallTest() {
		assertEquals(initialNrOfBalls, ballManager.getAllBalls().size());
		
		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);
		
		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);
		
		ballManager.addBall(ball2);
				
		assertNotNull(ballManager.getBall(ball.getId()));
		Ball newBall = ballManager.getBall(ball.getId());
		newBall.setName(NAME_1);
		newBall.setColor(COLOR_1);
		newBall.setSize(SIZE_1);
		newBall.setType(TYPE_1);
		
		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());
		
	}
	
	@Test
	public void getAllBallsTest() {
		
	//	ballManager.deleteAllBalls();
		
		assertEquals(initialNrOfBalls, ballManager.getAllBalls().size());
		
		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);
		
		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);
		
		ballManager.addBall(ball2);
		
		assertEquals(initialNrOfBalls+2, ballManager.getAllBalls().size());
		
		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());
		
	//	List<Ball> balls = ballManager.getAllBalls();
		
//		assertTrue(ball.equals(balls.get(0)));
//		assertTrue(Ball2.equals(balls.get(1)));
		
	//	ballManager.deleteAllBalls();
		
	//	assertEquals(0, ballManager.getAllBalls().size());
		
	}
	

}
