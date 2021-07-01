package ch.zhaw.dodelidobackend.controller;

import ch.zhaw.dodelidobackend.model.Player;
import ch.zhaw.dodelidobackend.service.PlayerService;
import ch.zhaw.dodelidobackend.service.RoundCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller class for Player modification.
 *
 * @author Kaltrim Bajrami
 * @version 2021.06.08
 */

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    private RoundCounterService roundCounterService;

    /**
     * get all players which are in game
     *
     * @return List of all Players
     */
    @RequestMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.getPlayerList();
    }

    /**
     * get all player names which are in game
     * @return List of all player names String
     */
    @RequestMapping("/playernames")
    public List<String> getAllPlayerNames(){
        return playerService.getPlayerNames();
    }

    /**
     * get all player names which are in game
     * @return List of all player names String
     */
    @RequestMapping("/playernames/{playerName}")
    public String getPlayerName(@PathVariable String playerName){
        return playerService.getPlayerName(playerName);
    }

    /**
     * get a specific player
     *
     * @param playerName to find specific player with name String
     * @return Player Instance
     */
    @RequestMapping("/players/{playerName}")
    public Player getPlayer(@PathVariable String playerName) {
        return playerService.getPlayer(playerName);

    }

    /**
     * method to add new player to the game
     *
     * @param playerName to give new Player a name String
     */
    @RequestMapping(method = RequestMethod.POST, value = "/players/{playerName}")
    public void addPlayer(@PathVariable String playerName) {
        playerService.addPlayer(playerName);
    }

    /**
     * Method that returns the player who is on the turn
     *
     * @return player whose turn it is
     */
    @RequestMapping("/whosturn")
    public Player whosTurn() {
        return playerService.whosTurn();
    }

    /**
     * Method to start and set initial turn to first player (player with ID 1)
     */
    @RequestMapping("/start")
    public void initialTurn() {
        playerService.initialTurn();
    }

    /**
     * Method which passes turn to next player
     */
    @RequestMapping("/next")
    public void nextTurn() {
        playerService.nextTurn();
    }

    /**
     * Method that punished current player
     * @author Lukas Boril
     * @version 2021.06.19
     */
    @RequestMapping("/fail")
    public void punishCurrentPlayer() {
        playerService.punishCurrentPlayer();
    }

    /**
     * Method to clear all entries in Playerlist and reset Roundcounter
     * @author Kaltrim Bajrami
     * @version 2021.06.30
     */
    @RequestMapping("/clear")
    public void clear(){
        playerService.clearPlayers();
        roundCounterService.setRoundCounterToOne();
        playerService.resetId();
    }

}
