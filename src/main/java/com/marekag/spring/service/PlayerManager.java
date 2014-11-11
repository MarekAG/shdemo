package com.marekag.spring.service;

import java.util.List;

import com.marekag.spring.domain.Player;

public interface PlayerManager {
	
	void addPlayer(Player player);
	Player getPlayer(Player player);
	Player getPlayer(Long id);
	List<Player> getAllPlayers();
	void updatePlayer(Player player, String position);
	void deletePlayer(Player player);
	void deletePlayer(Long id);
	void deleteAllPlayers();	

}
