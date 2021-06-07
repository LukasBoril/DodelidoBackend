package ch.zhaw.dodelidobackend;

/**
 * Player Class for Player instance
 * Default with getters and setters
 *
 * @author Kaltrim Bajrami
 * @version 2021.06.02
 */


public class Player {

    private String name;
    private static int id = 0;
    private int healthPoints;
    private boolean yourTurn;

    public Player() {

    }

    public Player(String name) {
        super();
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

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Player.id = id;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public boolean getYourTurn() {
        return yourTurn;
    }

    public void setYourTurn(Boolean yourTurn) {
        this.yourTurn = yourTurn;
    }


}
