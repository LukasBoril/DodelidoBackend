package ch.zhaw.dodelidobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    //get all players
    @RequestMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.getPlayerList();
    }

    //get specific player
    @RequestMapping("/players/{playerName}")
    public Player getPlayer(@PathVariable String playerName) {
        return playerService.getPlayer(playerName);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/players/{playerName}")
    public void addPlayer(@PathVariable String playerName) {
        playerService.addPlayer(playerName);
    }

}
