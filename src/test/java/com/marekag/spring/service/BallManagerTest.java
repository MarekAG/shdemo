package com.marekag.spring.service;

import static org.junit.Assert.*;

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
import com.marekag.spring.domain.Manufacturer;

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

	private final static String NAME_MAN_1 = "Firma1";
	private final static String ADDRESS_1 = "Adres1";
	private final static int YOC_1 = 1981;

	private final static String NAME_MAN_2 = "Firma2";
	private final static String ADDRESS_2 = "Adres2";
	private final static int YOC_2 = 1992;

	int initialNrOfBalls = 0;
	int initialNrOfManufacturers = 0;
	int finalNrOfBalls = 0;
	int finalNrOfManufacturers = 0;

	@Before
	public void beforeTests() {
		initialNrOfBalls = ballManager.getAllBalls().size();
		initialNrOfManufacturers = ballManager.getAllManufacturers().size();
		System.out.println("Piłki przed " + initialNrOfBalls + "\nProducenci przed "+ initialNrOfManufacturers);
	}

	@After
	public void afterTests() {
		finalNrOfBalls = ballManager.getAllBalls().size();
		finalNrOfManufacturers = ballManager.getAllManufacturers().size();
		System.out.println("Piłki po " + finalNrOfBalls + "\nProducenci po "+ finalNrOfManufacturers);
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
	public void addManufacturerTest() {

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer);

		assertNotNull(ballManager.getManufacturer(manufacturer));
		Manufacturer newManufacturer = ballManager
				.getManufacturer(manufacturer);

		assertEquals(NAME_MAN_1, newManufacturer.getName());
		assertEquals(ADDRESS_1, newManufacturer.getAddress());
		assertEquals(YOC_1, newManufacturer.getYOC());

		ballManager.deleteManufacturer(manufacturer);

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
		ballManager.updateBall(newBall, TYPE_2);
		// newBall.setType(TYPE_2);

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
	public void updateManufacturerTest() {

		// dodanie dwóch producentów
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer);

		Manufacturer manufacturer2 = new Manufacturer();
		manufacturer2.setName(NAME_MAN_1);
		manufacturer2.setAddress(ADDRESS_1);
		manufacturer2.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer2);

		assertNotNull(ballManager.getManufacturer(manufacturer));
		Manufacturer newManufacturer = ballManager
				.getManufacturer(manufacturer);
		// pobranie pierwszego producenta
		assertEquals(NAME_MAN_1, newManufacturer.getName());
		assertEquals(ADDRESS_1, newManufacturer.getAddress());
		assertEquals(YOC_1, newManufacturer.getYOC());
		// zmiana nazwy
		ballManager.updateManufacturer(newManufacturer, NAME_MAN_2);
		// newManufacturer.setName(NAME_MAN_2);

		assertNotNull(ballManager.getManufacturer(newManufacturer));
		Manufacturer newestManufacturer = ballManager
				.getManufacturer(newManufacturer);
		// sprawdzenie czy pierwszy się zmienił
		assertEquals(NAME_MAN_2, newestManufacturer.getName());
		assertEquals(ADDRESS_1, newestManufacturer.getAddress());
		assertEquals(YOC_1, newestManufacturer.getYOC());

		assertNotNull(ballManager.getManufacturer(manufacturer2));
		Manufacturer newManufacturer2 = ballManager
				.getManufacturer(manufacturer2);
		// sprawdzenie czy drugi się nie zmienił
		assertEquals(NAME_MAN_1, newManufacturer2.getName());
		assertEquals(ADDRESS_1, newManufacturer2.getAddress());
		assertEquals(YOC_1, newManufacturer2.getYOC());

		ballManager.deleteManufacturer(manufacturer);
		ballManager.deleteManufacturer(manufacturer2.getId());
	}

	@Test
	public void deleteBallTest() {

		// dodanie dwóch piłek
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

	// TODO: refactor to Manufacturer
	@Test
	public void deleteManufacturerTest() {

		// dodanie dwóch producentów
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer);

		Manufacturer manufacturer2 = new Manufacturer();
		manufacturer2.setName(NAME_MAN_2);
		manufacturer2.setAddress(ADDRESS_2);
		manufacturer2.setYOC(YOC_2);

		ballManager.addManufacturer(manufacturer2);

		assertNotNull(ballManager.getManufacturer(manufacturer));
		Manufacturer newManufacturer = ballManager
				.getManufacturer(manufacturer);
		// sprawdzenie czy pierwszy jest w bazie
		assertEquals(NAME_MAN_1, newManufacturer.getName());
		assertEquals(ADDRESS_1, newManufacturer.getAddress());
		assertEquals(YOC_1, newManufacturer.getYOC());
		// usunięcie pierwszej z bazy
		ballManager.deleteManufacturer(newManufacturer);
		// ponowne sprawdzenie czy pierwszy jest w bazie
		assertNull(ballManager.getManufacturer(newManufacturer));
		assertNull(ballManager.getManufacturer(manufacturer));

		// sprawdzenie czy możemy pobrać drugiego
		assertNotNull(ballManager.getManufacturer(manufacturer2));
		Manufacturer newManufacturer2 = ballManager
				.getManufacturer(manufacturer2);

		// sorawdzenie czy to na pewno ten rekord
		assertEquals(NAME_MAN_2, newManufacturer2.getName());
		assertEquals(ADDRESS_2, newManufacturer2.getAddress());
		assertEquals(YOC_2, newManufacturer2.getYOC());

		ballManager.deleteManufacturer(manufacturer2);

	}

	@Test
	public void getByColor() {

		List<Ball> blueBalls = ballManager.getBallsByColor(COLOR_2);

		for (Ball testingBall : blueBalls) {
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

		assertEquals(initialBlueBallsSize + 1, blueBalls2.size());

		for (Ball testingBall : blueBalls2) {
			assertEquals(COLOR_2, testingBall.getColor());
		}

		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());
	}
	
	@Test
	public void getByYOC() {

		List<Manufacturer> manufacturersFrom1992 = ballManager.getManufacturersByYOC(YOC_2);

		for (Manufacturer manufacturer : manufacturersFrom1992) {
			assertEquals(YOC_2, manufacturer.getYOC());
		}

		int initialManufacturersFrom1992Size = manufacturersFrom1992.size();

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer);

		Manufacturer manufacturer2 = new Manufacturer();
		manufacturer2.setName(NAME_MAN_2);
		manufacturer2.setAddress(ADDRESS_2);
		manufacturer2.setYOC(YOC_2);

		ballManager.addManufacturer(manufacturer2);

		List<Manufacturer> manufacturersFrom1992_2 = ballManager.getManufacturersByYOC(YOC_2);

		assertEquals(initialManufacturersFrom1992Size + 1, manufacturersFrom1992_2.size());

		for (Manufacturer testingManufacturer : manufacturersFrom1992_2) {
			assertEquals(YOC_2, testingManufacturer.getYOC());
		}

		ballManager.deleteManufacturer(manufacturer);
		ballManager.deleteManufacturer(manufacturer2);
	}

	// @Test
	// public void deleteAllBallsTest() {
	// ballManager.deleteAllBalls();
	//
	// assertEquals(0, ballManager.getAllBalls().size());
	//
	// Ball ball = new Ball();
	// ball.setName(NAME_1);
	// ball.setColor(COLOR_1);
	// ball.setSize(SIZE_1);
	// ball.setType(TYPE_1);
	//
	// ballManager.addBall(ball);
	//
	// Ball Ball2 = new Ball();
	// Ball2.setName(NAME_2);
	// Ball2.setColor(COLOR_2);
	// Ball2.setSize(SIZE_2);
	// Ball2.setType(TYPE_2);
	//
	// ballManager.addBall(Ball2);
	//
	// assertEquals(2, ballManager.getAllBalls().size());
	//
	// ballManager.deleteAllBalls();
	//
	// assertEquals(0, ballManager.getAllBalls().size());
	//
	// }

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
		
		assertEquals(initialNrOfBalls + 2, ballManager.getAllBalls().size());

		assertNotNull(ballManager.getBall(ball.getId()));
		Ball newBall = ballManager.getBall(ball.getId());
		assertEquals(NAME_1, newBall.getName());
		assertEquals(COLOR_1, newBall.getColor());
		assertEquals(SIZE_1, newBall.getSize());
		assertEquals(TYPE_1, newBall.getType());

		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());

	}
	
	@Test
	public void getManufacturerTest() {
		assertEquals(initialNrOfManufacturers, ballManager.getAllManufacturers().size());

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer);

		Manufacturer manufacturer2 = new Manufacturer();
		manufacturer2.setName(NAME_MAN_2);
		manufacturer2.setAddress(ADDRESS_2);
		manufacturer2.setYOC(YOC_2);

		ballManager.addManufacturer(manufacturer2);

		assertNotNull(ballManager.getManufacturer(manufacturer.getId()));
		Manufacturer newManufacturer = ballManager.getManufacturer(manufacturer.getId());
		assertEquals(NAME_MAN_1, newManufacturer.getName());
		assertEquals(ADDRESS_1, newManufacturer.getAddress());
		assertEquals(YOC_1, newManufacturer.getYOC());

		ballManager.deleteManufacturer(manufacturer.getId());
		ballManager.deleteManufacturer(manufacturer2);

	}

	@Test
	public void getAllBallsTest() {

		// ballManager.deleteAllBalls();

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

		assertEquals(initialNrOfBalls + 2, ballManager.getAllBalls().size());

		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());

		// List<Ball> balls = ballManager.getAllBalls();

		// assertTrue(ball.equals(balls.get(0)));
		// assertTrue(Ball2.equals(balls.get(1)));

		// ballManager.deleteAllBalls();

		// assertEquals(0, ballManager.getAllBalls().size());

	}
	
	@Test
	public void getAlManufacturersTest() {

		assertEquals(initialNrOfManufacturers, ballManager.getAllManufacturers().size());

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer);

		Manufacturer manufacturer2 = new Manufacturer();
		manufacturer2.setName(NAME_MAN_2);
		manufacturer2.setAddress(ADDRESS_2);
		manufacturer2.setYOC(YOC_2);

		ballManager.addManufacturer(manufacturer2);

		assertEquals(initialNrOfManufacturers + 2, ballManager.getAllManufacturers().size());

		ballManager.deleteManufacturer(manufacturer.getId());
		ballManager.deleteManufacturer(manufacturer2);
		
	}
	
	@Test
	public void assignBallToManufacturerTest() {
		
		assertEquals(initialNrOfManufacturers, ballManager.getAllManufacturers().size());

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer);

		Manufacturer manufacturer2 = new Manufacturer();
		manufacturer2.setName(NAME_MAN_2);
		manufacturer2.setAddress(ADDRESS_2);
		manufacturer2.setYOC(YOC_2);

		ballManager.addManufacturer(manufacturer2);

		assertEquals(initialNrOfManufacturers + 2, ballManager.getAllManufacturers().size());

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

		assertEquals(initialNrOfBalls + 2, ballManager.getAllBalls().size());

		ballManager.assignBallToManufacturer(ball2, manufacturer2);
		
		Manufacturer manufacturerFromDB = ballManager.getManufacturer(manufacturer2);
		
		List<Ball> manufacturerFromDBBalls = manufacturerFromDB.getBalls();
		assertTrue(manufacturerFromDBBalls.contains(ball2));
		assertFalse(manufacturerFromDBBalls.contains(ball));
		

		ballManager.deleteManufacturer(manufacturer);
		ballManager.deleteManufacturer(manufacturer2.getId());
		
		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());
	
	}
	
	@Test
	public void removeManufacturerFromBallTest() {
		
		assertEquals(initialNrOfManufacturers, ballManager.getAllManufacturers().size());

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer);

		Manufacturer manufacturer2 = new Manufacturer();
		manufacturer2.setName(NAME_MAN_2);
		manufacturer2.setAddress(ADDRESS_2);
		manufacturer2.setYOC(YOC_2);

		ballManager.addManufacturer(manufacturer2);

		assertEquals(initialNrOfManufacturers + 2, ballManager.getAllManufacturers().size());

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

		assertEquals(initialNrOfBalls + 2, ballManager.getAllBalls().size());

		ballManager.assignBallToManufacturer(ball2, manufacturer2);
		
		Manufacturer manufacturerFromDB = ballManager.getManufacturer(manufacturer2);
		
		List<Ball> manufacturerFromDBBalls = manufacturerFromDB.getBalls();
		assertTrue(manufacturerFromDBBalls.contains(ball2));
		assertFalse(manufacturerFromDBBalls.contains(ball));
		
		ballManager.removeManufacturerFromBall(ball2);
				
		List<Ball> manufacturerFromDBBalls2= ballManager.getManufacturerBalls(manufacturer2);

		assertFalse(manufacturerFromDBBalls2.contains(ball2));
		assertFalse(manufacturerFromDBBalls2.contains(ball));

		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());
		
		ballManager.deleteManufacturer(manufacturer);
		ballManager.deleteManufacturer(manufacturer2);	
	}
	
	@Test
	public void getManufacturerBallsTest() {
		
		assertEquals(initialNrOfManufacturers, ballManager.getAllManufacturers().size());

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYOC(YOC_1);

		ballManager.addManufacturer(manufacturer);

		Manufacturer manufacturer2 = new Manufacturer();
		manufacturer2.setName(NAME_MAN_2);
		manufacturer2.setAddress(ADDRESS_2);
		manufacturer2.setYOC(YOC_2);

		ballManager.addManufacturer(manufacturer2);

		assertEquals(initialNrOfManufacturers + 2, ballManager.getAllManufacturers().size());

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

		assertEquals(initialNrOfBalls + 2, ballManager.getAllBalls().size());

		int initialManufacturer1NrOfBalls = ballManager.getManufacturerBalls(manufacturer).size();
		int initialManufacturer2NrOfBalls = ballManager.getManufacturerBalls(manufacturer2).size();

		
		ballManager.assignBallToManufacturer(ball2, manufacturer2);
		ballManager.assignBallToManufacturer(ball, manufacturer2);
		
		assertEquals(initialManufacturer1NrOfBalls, ballManager.getManufacturerBalls(manufacturer).size());
		assertEquals(initialManufacturer2NrOfBalls + 2, ballManager.getManufacturerBalls(manufacturer2).size());
		
		
		List<Ball> manufacturerFromDBBalls= ballManager.getManufacturerBalls(manufacturer2);

		assertTrue(manufacturerFromDBBalls.contains(ball2));
		assertTrue(manufacturerFromDBBalls.contains(ball));

		ballManager.deleteBall(ball.getId());
		ballManager.deleteBall(ball2.getId());
		
		ballManager.deleteManufacturer(manufacturer);
		ballManager.deleteManufacturer(manufacturer2.getId());
		
		
	
	}

}
