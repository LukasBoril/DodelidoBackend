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
    private Integer id ;
    private static Integer idCounter = 0;
    private Integer healthPoints;
    private Boolean yourTurn;



    public Player(String name) {
        setName(name);
        setId(++idCounter);
        setHealthPoints(100);
        setYourTurn(false);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
