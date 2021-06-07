package ch.zhaw.dodelidobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlayerService {


    private List<Player> playerList = new ArrayList<>();


    public List<Player> getPlayerList() {
        return playerList;
    }

    public Player getPlayer(String name) {
        return playerList.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
    }


    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        playerList.add(player);
    }
}
