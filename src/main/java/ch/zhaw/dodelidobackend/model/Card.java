package ch.zhaw.dodelidobackend.model;

/**
 * a model class  that allows to manage the animal and color attribute of a Card.
 *  @author Nadine Duss
 *  @version 2021.06.05
 */
public class Card {
    private Animals animal;
    private Colors color;

    /**
     * Initialises a new Card with a specific value of an animal and color enum.
     * @param animal a specific Animal enum value
     * @param color a specific Color enum value
     */
    public Card(Animals animal, Colors color) {
        this.animal = animal;
        this.color = color;
    }

    // needed in RestControllerTest for Jackson databinding
    public Card() {}

    /**
     * Initialises a new Card with a specific value of an animal and color
     * as a String which will be translated to the corresponding enum value of
     * Animal or Color.
     * @param animal a String that can be converted to an Animal enum value
     * @param color a String that can be converted to a Color enum value
     */
    public Card(String animal, String color) {
        this.animal = Animals.valueOf(animal.toUpperCase());
       this.color = Colors.valueOf(color.toUpperCase());
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Animals getAnimal() {
        return animal;
    }

    public void setAnimal(Animals animal) {
        this.animal = animal;
    }

    /**
     * method to check whether the animal and the color of two card are identical
     * @param obj   the Card object to check for content equality
     * @return true if the content (animal and color) of the two cards is identical, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
       //reference equality check
        if (obj == this) {
            return true;
        }
        if((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        Card testCard = (Card) obj;
        return (animal == testCard.getAnimal() && color == testCard.getColor());
        }

    /**
     * Method to create a unique hashcode for each specific Card object based
     * on its attribute color and animal.
     * @return hashcode value
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 3;
        result = prime * result
                + ((color == null) ? 0 : color.hashCode());
        result = prime * result
                + ((animal == null) ? 0 : animal.hashCode());
        return result;
    }

    /**
     * Enumeration defining the 5 possible animals that a Card Object can have as attribute.
     */
    public enum Animals {TURTOISE, ZEBRA, FLAMINGO, CAMEL, PENGUIN}

    /**
     * Enumeration defining the 5 possible colors that a Card Object can have as attribute.
     */
    public enum Colors {WHITE, YELLOW, BLUE, RED, GREEN}
}
