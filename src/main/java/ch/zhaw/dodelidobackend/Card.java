package ch.zhaw.dodelidobackend;

/**
 * a model class  that allows to manage the animal and color of a Card.
 *  @author Nadine Duss
 *  @version 2021.06.05
 */
public class Card {
    private Animals animal;
    private Colors color;

    public Card(Animals animal, Colors color) {
        this.animal = animal;
        this.color = color;
    }

    public Card(String animal, String color) {
        this.animal = Animals.valueOf(animal.toUpperCase());
       this.color = Colors.valueOf(color.toUpperCase());
    }

    public Card() {}

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
     * method to chgeckl whether the aniaml and the color of two card are identical
     * @param obj   the card to check for content equality
     * @return true if the content of the two cards is identical otherwise false
     */
    @Override
    public boolean equals(Object obj) {
       //Referenzgleichheheit
        if (obj == this) {
            return true;
        }
        if((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        Card testCard = (Card) obj;
        return (animal == testCard.getAnimal() && color == testCard.getColor());
        }

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
    public enum Animals {TURTOISE, ZEBRA, FLAMINGO, CAMEL, PENGUIN}
    public enum Colors {WHITE, YELLOW, BLUE, RED, GREEN}
}
