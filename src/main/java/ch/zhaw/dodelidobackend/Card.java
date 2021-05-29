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


    public enum Animals {TURTOISE, ZEBRA, FLAMINGO, CAMEL, PENGUIN}
    public enum Colors {WHITE, YELLOW, BLUE, RED, GREEN}
}
