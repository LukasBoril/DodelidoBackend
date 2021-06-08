package ch.zhaw.dodelidobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    public Player getPlayer(Integer Id) {
        return playerList.stream().filter(t -> t.getId().equals(Id)).findFirst().orElse(null);
    }

    public Player whosTurn() {
        return playerList.stream().filter(t -> t.getYourTurn().equals(true)).findFirst().get();
    }

    public void initialTurn(){
        playerList.get(0).setYourTurn(true);
    }

    public void nextTurn(){
        Integer playerId = whosTurn().getId();
        Player lastPlayer = whosTurn();
        Player nextPlayer;
        if(playerId++ < playerList.size()) {
            nextPlayer = getPlayer(playerId++);
            nextPlayer.setYourTurn(true);
            lastPlayer.setYourTurn(false);
        }
        else{
            nextPlayer = getPlayer(1);
            nextPlayer.setYourTurn(true);
            lastPlayer.setYourTurn(false);
        }

    }

    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        playerList.add(player);
    }
}
