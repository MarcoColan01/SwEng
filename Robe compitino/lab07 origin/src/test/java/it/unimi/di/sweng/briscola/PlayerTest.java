package it.unimi.di.sweng.briscola;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIterable;

public class PlayerTest {
    @Test
    void iterablePlayerTest(){
        Player SUT = new Player("Marco");
        SUT.giveCard(Card.get(Rank.CAVALLO, Suit.COPPE));
        SUT.giveCard(Card.get(Rank.CINQUE, Suit.BASTONI));
        assertThatIterable(SUT).hasSize(2);
    }

    @Test
    void iterable2PlayerTest(){
        Player player1 = new Player("Marco");
        Player player2 = new Player("Luca");

        player1.giveCard(Card.get(Rank.CAVALLO, Suit.COPPE));
        player1.giveCard(Card.get(Rank.CINQUE, Suit.BASTONI));

        player2.giveCard(Card.get(Rank.RE, Suit.COPPE));
        player2.giveCard(Card.get(Rank.FANTE, Suit.BASTONI));
        assertThatIterable(player1).hasSize(2);
        assertThatIterable(player2).hasSize(2);
    }

    @Test
    void comparePlayersTest(){
        Player me = new Player("Marco");
        Player enemy = new Player("Luca");

        me.addWonCardsToPersonalDeck(Card.get(Rank.CAVALLO, Suit.COPPE),
                Card.get(Rank.CINQUE, Suit.BASTONI));

        enemy.addWonCardsToPersonalDeck(Card.get(Rank.RE, Suit.COPPE),
                Card.get(Rank.FANTE, Suit.BASTONI));

        assertThat(me.compareTo(enemy)).isNegative();
    }
}
