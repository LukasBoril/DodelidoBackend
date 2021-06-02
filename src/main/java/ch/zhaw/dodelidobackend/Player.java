package ch.zhaw.dodelidobackend;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Player {

    private String name;
    private static Integer id = 0;
    private Integer healthPoints;
    private Boolean yourTurn;


    public Player (String name){
        setName(name);
        setId(id++);
        setHealthPoints(100);
        setYourTurn(false);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Player.id = id;
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Boolean getYourTurn() {
        return yourTurn;
    }

    public void setYourTurn(Boolean yourTurn) {
        this.yourTurn = yourTurn;
    }



}
