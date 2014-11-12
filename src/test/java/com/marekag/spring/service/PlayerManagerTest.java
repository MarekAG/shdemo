package com.marekag.spring.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.marekag.spring.domain.Player;
import com.marekag.spring.service.PlayerManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class PlayerManagerTest {
	
	@Autowired
	PlayerManager playerManager;
	
	private final static String NAME_1 = "Grzegorz";
	private final static String LASTNAME_1 = "Niciński";
	private final static String POSITION_1 = "Striker";
	private final static int AGE_1 = 35;

	private final static String NAME_2 = "Krzysztof";
	private final static String LASTNAME_2 = "Sobieraj";
	private final static String POSITION_2 = "Defender";
	private final static int AGE_2 = 31;
	
	@Test
	public void addPlayerTest() {		
		
		Player player = new Player();
		player.setName(NAME_1);
		player.setLastName(LASTNAME_1);
		player.setAge(AGE_1);
		player.setPosition(POSITION_1);
		
		playerManager.addPlayer(player);
		
		assertNotNull(playerManager.getPlayer(player));
		Player newPlayer = playerManager.getPlayer(player);
		
		assertEquals(NAME_1, newPlayer.getName());
		assertEquals(LASTNAME_1, newPlayer.getLastName());
		assertEquals(AGE_1, newPlayer.getAge());
		assertEquals(POSITION_1, newPlayer.getPosition());
		
	}
	
	@Test
	public void updatePlayerTest() {
		
		
		// dodanie dwóch piłkarzy
		Player player = new Player();
		player.setName(NAME_1);
		player.setLastName(LASTNAME_1);
		player.setAge(AGE_1);
		player.setPosition(POSITION_1);
		
		playerManager.addPlayer(player);
		
		Player player2 = new Player();
		player2.setName(NAME_1);
		player2.setLastName(LASTNAME_1);
		player2.setAge(AGE_1);
		player2.setPosition(POSITION_1);
		
		playerManager.addPlayer(player2);
		
		assertNotNull(playerManager.getPlayer(player));
		Player newPlayer = playerManager.getPlayer(player);
		// pobranie pierwszego piłkarza
		assertEquals(NAME_1, newPlayer.getName());
		assertEquals(LASTNAME_1, newPlayer.getLastName());
		assertEquals(AGE_1, newPlayer.getAge());
		assertEquals(POSITION_1, newPlayer.getPosition());
		// zmiana pozycji
		newPlayer.setPosition(POSITION_2);
		
		assertNotNull(playerManager.getPlayer(newPlayer));
		Player newestPlayer = playerManager.getPlayer(newPlayer);
		// sprawdzenie czy pierwszy się zmienił
		assertEquals(NAME_1, newestPlayer.getName());
		assertEquals(LASTNAME_1, newestPlayer.getLastName());
		assertEquals(AGE_1, newestPlayer.getAge());
		assertEquals(POSITION_2, newestPlayer.getPosition());
		
		assertNotNull(playerManager.getPlayer(player2));
		Player newPlayer2 = playerManager.getPlayer(player2);
		// sprawdzenie czy drugi się nie zmienił
		assertEquals(NAME_1, newPlayer2.getName());
		assertEquals(LASTNAME_1, newPlayer2.getLastName());
		assertEquals(AGE_1, newPlayer2.getAge());
		assertEquals(POSITION_1, newPlayer2.getPosition());
	}
	
	@Test
	public void deletePlayerTest() {
		
		//dodanie dwóch piłkarzy
		Player player = new Player();
		player.setName(NAME_1);
		player.setLastName(LASTNAME_1);
		player.setAge(AGE_1);
		player.setPosition(POSITION_1);
		
		playerManager.addPlayer(player);
		
		Player player2 = new Player();
		player2.setName(NAME_2);
		player2.setLastName(LASTNAME_2);
		player2.setAge(AGE_2);
		player2.setPosition(POSITION_2);
		
		playerManager.addPlayer(player2);
		
		assertNotNull(playerManager.getPlayer(player));
		Player newPlayer = playerManager.getPlayer(player);
		// sprawdzenie czy pierwszy jest w bazie
		assertEquals(NAME_1, newPlayer.getName());
		assertEquals(LASTNAME_1, newPlayer.getLastName());
		assertEquals(AGE_1, newPlayer.getAge());
		assertEquals(POSITION_1, newPlayer.getPosition());
		// usunięcie pierwszego z bazy
		playerManager.deletePlayer(newPlayer);
		// ponowne sprawdzenie czy pierwszy jest w bazie
		assertNull(playerManager.getPlayer(newPlayer));
		assertNull(playerManager.getPlayer(player));
		
		// sprawdzenie czy możemy pobrać drugiego
		assertNotNull(playerManager.getPlayer(player2));
		Player newPlayer2 = playerManager.getPlayer(player2);
		
		// sorawdzenie czy to na pewno ten rekord
		assertEquals(NAME_2, newPlayer2.getName());
		assertEquals(LASTNAME_2, newPlayer2.getLastName());
		assertEquals(AGE_2, newPlayer2.getAge());
		assertEquals(POSITION_2, newPlayer2.getPosition());

	}
	
	@Test
	public void deleteAllPlayersTest() {
		playerManager.deleteAllPlayers();
		
		assertEquals(0, playerManager.getAllPlayers().size());
		
		Player player = new Player();
		player.setName(NAME_1);
		player.setLastName(LASTNAME_1);
		player.setAge(AGE_1);
		player.setPosition(POSITION_1);
		
		playerManager.addPlayer(player);

		Player player2 = new Player();
		player2.setName(NAME_2);
		player2.setLastName(LASTNAME_2);
		player2.setAge(AGE_2);
		player2.setPosition(POSITION_2);
		
		playerManager.addPlayer(player2);
		
		assertEquals(2, playerManager.getAllPlayers().size());
		
		playerManager.deleteAllPlayers();
		
		assertEquals(0, playerManager.getAllPlayers().size());
		
	}
	
	@Test
	public void getPlayerTest() {
		assertEquals(0, playerManager.getAllPlayers().size());
		
		Player player = new Player();
		player.setName(NAME_1);
		player.setLastName(LASTNAME_1);
		player.setAge(AGE_1);
		player.setPosition(POSITION_1);
		
		playerManager.addPlayer(player);

		Player player2 = new Player();
		player2.setName(NAME_2);
		player2.setLastName(LASTNAME_2);
		player2.setAge(AGE_2);
		player2.setPosition(POSITION_2);
		
		playerManager.addPlayer(player2);
				
		assertNotNull(playerManager.getPlayer(player.getId()));
		Player newPlayer = playerManager.getPlayer(player.getId());
		newPlayer.setName(NAME_1);
		newPlayer.setLastName(LASTNAME_1);
		newPlayer.setAge(AGE_1);
		newPlayer.setPosition(POSITION_1);
		
	}
	
	@Test
	public void getAllPlayersTest() {
		
		playerManager.deleteAllPlayers();
		
		assertEquals(0, playerManager.getAllPlayers().size());
		
		Player player = new Player();
		player.setName(NAME_1);
		player.setLastName(LASTNAME_1);
		player.setAge(AGE_1);
		player.setPosition(POSITION_1);
		
		playerManager.addPlayer(player);

		Player player2 = new Player();
		player2.setName(NAME_2);
		player2.setLastName(LASTNAME_2);
		player2.setAge(AGE_2);
		player2.setPosition(POSITION_2);
		
		playerManager.addPlayer(player2);
		
		assertEquals(2, playerManager.getAllPlayers().size());
		
		List<Player> players = playerManager.getAllPlayers();
		
		assertTrue(player.equals(players.get(0)));
		assertTrue(player2.equals(players.get(1)));
		
		playerManager.deleteAllPlayers();
		
		assertEquals(0, playerManager.getAllPlayers().size());
		
	}
	

}
