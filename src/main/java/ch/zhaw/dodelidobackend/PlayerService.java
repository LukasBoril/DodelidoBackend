package ch.zhaw.dodelidobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    /**
     * get all players which are in game
     *
     * @return List of all Players
     */
    public List<Player> getPlayerList() {
        return playerList;
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
}
