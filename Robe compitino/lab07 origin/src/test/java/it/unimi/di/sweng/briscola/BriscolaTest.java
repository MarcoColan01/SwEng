package it.unimi.di.sweng.briscola;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BriscolaTest {
    @Test
    void newBriscolaTest(){
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d = mock(Deck.class);
        when(d.draw()).thenReturn(
                Card.get(Rank.ASSO, Suit.COPPE),
                Card.get(Rank.ASSO, Suit.COPPE),
                Card.get(Rank.ASSO, Suit.COPPE),
                Card.get(Rank.ASSO, Suit.COPPE),
                Card.get(Rank.ASSO, Suit.COPPE),
                Card.get(Rank.ASSO, Suit.COPPE),
                Card.get(Rank.ASSO, Suit.COPPE)
        );
        Briscola SUT = new Briscola(p1,p2,d);
        assertThat(SUT.isBriscola(Card.get(Rank.ASSO, Suit.COPPE))).isTrue();
    }

    @Test
    void establishTurnWinnerTest(){
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d = mock(Deck.class);
        when(d.draw()).thenReturn(Card.get(Rank.ASSO, Suit.COPPE));
        Briscola SUT = new Briscola(p1,p2,d);
        assertThat((Iterable<Card>) SUT.establishTurnWinner(Card.get(Rank.RE, Suit.COPPE),
                Card.get(Rank.TRE, Suit.COPPE))).isEqualTo(p2);
    }

    @Test
    void establishGameWinnerTest(){
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d = mock(Deck.class);
        when(d.draw()).thenReturn(Card.get(Rank.ASSO, Suit.COPPE));
        when(p1.compareTo(p2)).thenReturn(1);
        Briscola SUT = new Briscola(p1,p2,d);
        assertThat((Iterable<Card>) SUT.establishGameWinner()).isEqualTo(p1);
    }

    @Test
    void establishGameWinnerTest2(){
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d = mock(Deck.class);
        when(d.draw()).thenReturn(Card.get(Rank.ASSO, Suit.COPPE));
        when(p1.compareTo(p2)).thenReturn(0);
        Briscola SUT = new Briscola(p1,p2,d);
        assertThat((Iterable<Card>) SUT.establishGameWinner()).isEqualTo(Player.NULL);
    }
}
