package com.marekag.spring.service;


import static org.junit.Assert.assertEquals;
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
		
		Player newPlayer = playerManager.getPlayer(player);
		
		assertEquals(NAME_1, newPlayer.getName());
		assertEquals(LASTNAME_1, newPlayer.getLastName());
		assertEquals(AGE_1, newPlayer.getAge());
		assertEquals(POSITION_1, newPlayer.getPosition());
		
	}
	
	@Test
	public void updatePlayerTest() {
		
		Player player = new Player();
		player.setName(NAME_1);
		player.setLastName(LASTNAME_1);
		player.setAge(AGE_1);
		player.setPosition(POSITION_1);
		
		playerManager.addPlayer(player);
		
		Player newPlayer = playerManager.getPlayer(player);
		
		assertEquals(NAME_1, newPlayer.getName());
		assertEquals(LASTNAME_1, newPlayer.getLastName());
		assertEquals(AGE_1, newPlayer.getAge());
		assertEquals(POSITION_1, newPlayer.getPosition());
		
		newPlayer.setPosition(POSITION_2);
		
		Player newestPlayer = playerManager.getPlayer(newPlayer);
		
		assertEquals(NAME_1, newestPlayer.getName());
		assertEquals(LASTNAME_1, newestPlayer.getLastName());
		assertEquals(AGE_1, newestPlayer.getAge());
		assertEquals(POSITION_2, newestPlayer.getPosition());
	}
	
	@Test
	public void deletePlayerTest() {
		
		Player player = new Player();
		player.setName(NAME_1);
		player.setLastName(LASTNAME_1);
		player.setAge(AGE_1);
		player.setPosition(POSITION_1);
		
		playerManager.addPlayer(player);
		
		Player newPlayer = playerManager.getPlayer(player);
		
		assertEquals(NAME_1, newPlayer.getName());
		assertEquals(LASTNAME_1, newPlayer.getLastName());
		assertEquals(AGE_1, newPlayer.getAge());
		assertEquals(POSITION_1, newPlayer.getPosition());
		
		playerManager.deletePlayer(newPlayer);

		assertNull(playerManager.getPlayer(newPlayer));
		assertNull(playerManager.getPlayer(player));

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
