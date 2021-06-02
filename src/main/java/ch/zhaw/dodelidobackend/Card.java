package ch.zhaw.dodelidobackend;

public class Card {
    private Animals animal;
    private Colors color;

    public Card(Animals animal, Colors color) {
        this.animal = animal;
        this.color = color;
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
