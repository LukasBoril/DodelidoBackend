package ch.zhaw.dodelidobackend.model;

/**
 * Player Class for Player instance
 * Default with getters and setters
 *
 * @author Kaltrim Bajrami
 * @version 2021.06.08
 */


public class Player {
    /**
     * Variables for player instances. The idCounter is used to increment the ID of the players
     */
    private String name;
    private Integer id;
    private static Integer idCounter = 0;
    private Integer healthPoints;
    private Boolean yourTurn;

    /**
     *
     */
    public Player(){

    }
    /**
     * Constructor to create new Player
     *
     * @param name
     */
    public Player(String name) {
        setName(name);
        setId(++idCounter);
        setHealthPoints(100);
        setYourTurn(false);
    }

    /**
     * Method to get players Name
     *
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set players Name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get Players ID
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Method to set Players ID
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * MEthod to get the Healthpoints of the Player
     *
     * @return
     */
    public Integer getHealthPoints() {
        return healthPoints;
    }

    /**
     * Method to set Value of Healthpoints
     *
     * @param healthPoints
     */
    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * Getter Method to check move
     *
     * @return
     */
    public Boolean getYourTurn() {
        return yourTurn;
    }

    /**
     * Setter Method to set game move
     *
     * @param yourTurn
     */
    public void setYourTurn(Boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

    /**
     *
     */
    public static void resetIdCounter(){
        idCounter = 0;
    }


}
