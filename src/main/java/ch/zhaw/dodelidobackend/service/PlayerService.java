package ch.zhaw.dodelidobackend.service;

import ch.zhaw.dodelidobackend.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Class for the players
 *
 * @author Kaltrim Bajrami
 * @version 2021.06.08
 */
@Service
public class PlayerService {


    private List<Player> playerList = new ArrayList<>();
    private RoundCounterService roundCounterService;

    /**
     * get all players which are in game
     *
     * @return List of all Players
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * get all player names which are in game
     * @return List of all player names in String
     */
    public List<String> getPlayerNames() {
        List players = new ArrayList();
        for(Player p : playerList){
            players.add(p.getName());
        }
        return players;
    }

    /**
     * get a specific player
     *
     * @param playerName to find specific player with name String
     * @return Player Instance
     */
    public Player getPlayer(String playerName) {
        return playerList.stream().filter(t -> t.getName().equals(playerName)).findFirst().orElse(null);
    }

    /**
     * get a specific player
     *
     * @param playerName to find specific player with name String
     * @return Player getName String value
     */
    public String getPlayerName(String playerName) {
        Player player = playerList.stream().filter(t -> t.getName().equals(playerName)).findFirst().orElse(null);
        return player.getName();
    }

    /**
     * get a specific player
     *
     * @param Id to find specific player with Id Integer
     * @return Player Instance
     */

    public Player getPlayer(Integer Id) {
        return playerList.stream().filter(t -> t.getId().equals(Id)).findFirst().orElse(null);
    }

    /**
     * Method that returns the player who is on the turn
     *
     * @return Player whose turn it is
     */
    public Player whosTurn() {
        return playerList.stream().filter(t -> t.getYourTurn().equals(true)).findFirst().get();
    }

    /**
     * Method to start and set initial turn to first player (player with ID 1)
     */
    public void initialTurn() {
        playerList.get(0).setYourTurn(true);
    }

    /**
     * Method to pass turn to next player, works with player IDs
     */
    public void nextTurn() {
        Integer playerId = whosTurn().getId();
        Player lastPlayer = whosTurn();
        Player nextPlayer;
        if (playerId++ < playerList.size()) {
            nextPlayer = getPlayer(playerId++);
            nextPlayer.setYourTurn(true);
            lastPlayer.setYourTurn(false);
        } else {
            lastPlayer.setYourTurn(false);
            initialTurn();
        }

    }

    /**
     * method to add new player to the game
     *
     * @param playerName to give new Player a name String
     */
    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        playerList.add(player);
    }

    /**
     * method to be called once a player makes a mistake
     * method gets current player, determined the amount of damage by accessing the roundCounter
     * Subsequent the roundCounter is set to zero again
     * and updates the healtPoints of the current player
     * @author Lukas Boril
     * @version 2021.06.19
     */
    public void punishCurrentPlayer() {
        Player currentPlayer = whosTurn();
        int currentHealt = currentPlayer.getHealthPoints();
        int damage = RoundCounterService.getCurrentRoundCounterValue();
        currentPlayer.setHealthPoints(currentHealt-damage);
        RoundCounterService.setRoundCounterToZero();
    }

    public void clearPlayers() {
        playerList.clear();
    }
}
