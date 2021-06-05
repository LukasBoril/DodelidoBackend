package ch.zhaw.dodelidobackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * class to test the equals and hashcode methods of the Card class.
 *  @author Nadine Duss
 *  @version 2021.06.05
 */
public class CardTester {

    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;

    /**
     * Create several new cards.
     */

    @BeforeEach
    public void setUp() {
        card1 = new Card(Card.Animals.TURTOISE, Card.Colors.BLUE);
        card2 = new Card("turtoise", "blue");
        card3 = new Card(Card.Animals.CAMEL, Card.Colors.WHITE);
        card4 = new Card(Card.Animals.CAMEL, Card.Colors.GREEN);
    }

    @Test
    public void testEqualContent() {
        assertTrue(card1.equals(card2));
    }

    @Test
    public void testNonEqual() {
        assertFalse(card1.equals(card3));
        assertFalse(card1.equals(card4));
        assertFalse(card2.equals(card3));
        assertFalse(card2.equals(card4));
        assertFalse(card3.equals(card4));
    }

    @Test
    public void testEqualHashcode() {
        assertTrue(card1.hashCode() == card2.hashCode());
    }

    @Test
    public void testNonEqualHashcode() {
        assertFalse(card1.hashCode() == card3.hashCode());
        assertFalse(card1.hashCode() == card4.hashCode());
        assertFalse(card2.hashCode() == card3.hashCode());
        assertFalse(card2.hashCode() == card4.hashCode());
        assertFalse(card3.hashCode() == card4.hashCode());

    }

}
