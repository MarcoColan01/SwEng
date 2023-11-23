package it.unimi.di.sweng.lab04;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


@Timeout(2)
public class PokerHandTest {


    @Test
    void newPokerHandTest() {
        PokerHand SUT = new PokerHand(List.of(
                Card.get(Rank.FOUR, Suit.CLUBS),
                Card.get(Rank.SIX, Suit.DIAMONDS),
                Card.get(Rank.KING, Suit.SPADES),
                Card.get(Rank.ACE, Suit.HEARTS)
        ));
        assertThatIterable(SUT).hasSize(4);
    }

    @Test
    void newPokerTableTest() {
        PokerTable SUT = new PokerTable(3);
        assertThat(SUT).hasSize(3);
        for (PokerHand giocatore : SUT) {
            assertThatIterable(giocatore).hasSize(5);
        }
    }

    @Test
    void onePairTest() {
        PokerHand ph = new PokerHand(List.of(
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.SPADES)));
        assertThat(ph.getRank()).isEqualTo(HandRank.ONE_PAIR);
    }

    @Test
    void onePairChainedTest() {
        PokerHand ph = new PokerHand(List.of(
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.SPADES)));
        ChainedHandEvaluator chain = new OnePairEvaluator(null);
        assertThat(chain.handEvaluator(ph)).isEqualTo(HandRank.ONE_PAIR);
    }
    @Test
    void noOnePairChainedTest() {
        PokerHand ph = new PokerHand(List.of(
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.SEVEN, Suit.SPADES)));
        ChainedHandEvaluator mockedNull = mock(ChainedHandEvaluator.class);
        when(mockedNull.handEvaluator(ph)).thenThrow(new NoSuchElementException());
        ChainedHandEvaluator chain = new OnePairEvaluator(mockedNull);
        assertThatThrownBy(() -> chain.handEvaluator(ph)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void twoPairTest(){
        PokerHand ph = new PokerHand(List.of(
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.DIAMONDS),
                Card.get(Rank.SIX, Suit.SPADES),
                Card.get(Rank.NINE, Suit.HEARTS),
                Card.get(Rank.FOUR, Suit.DIAMONDS)));
        assertThat(ph.getRank()).isEqualTo(HandRank.TWO_PAIR);
    }

    @Test
    void flushEvaluatorTest(){
        PokerHand ph = new PokerHand(List.of(
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.NINE, Suit.HEARTS),
                Card.get(Rank.FOUR, Suit.HEARTS)));
        assertThat(ph.getRank()).isEqualTo(HandRank.FLUSH);
    }

    @Test
    void chainTest1(){
        PokerHand ph = new PokerHand(List.of(
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.NINE, Suit.CLUBS),
                Card.get(Rank.SEVEN, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.SPADES)));
        assertThat(ph.getRank()).isEqualTo(HandRank.ONE_PAIR);
    }

    @Test
    void getHandTest(){
        PokerTable SUT = new PokerTable(3);
        int playerPos = 0;
        for(PokerHand ph: SUT){
            assertThatIterable(ph).containsExactlyElementsOf(SUT.getHand(playerPos++));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "5H 4H 2C AC JD, 5H 4H 5C AC JD, -1",
            "5H 4H 2C AC JD, 5H 4H QC AC JD, 1",
            "5H 4H 2C AC JD, 5H 5H QC AC JD, 0"
    })
    void sortPokerHandsTest(String mano1, String mano2, int res){
        PokerHand ph1 = of(mano1);
        PokerHand ph2 = of(mano2);
        switch(res){
            case -1 -> assertThat(ph1.compareTo(ph2)).isNegative();
            case 0 -> assertThat(ph1.compareTo(ph2)).isZero();
            case 1 -> assertThat(ph1.compareTo(ph2)).isPositive();
        }
    }

    private static PokerHand of(String mano) {
        List<Card> cards = new ArrayList<>();
        for(String str: mano.split(" ")){
            Suit s = parseSuit(str);
            Rank r = parseRank(str);
            cards.add(Card.get(r,s));
        }
        return new PokerHand(cards);
    }

    private static Rank parseRank(String str) {
        return switch (str.charAt(0)){
            case '1', 'A' -> Rank.ACE;
            case '2' -> Rank.TWO;
            case '3' -> Rank.THREE;
            case '4' -> Rank.FOUR;
            case '5' -> Rank.FIVE;
            case '6' -> Rank.SIX;
            case '7' -> Rank.SEVEN;
            case '8' -> Rank.EIGHT;
            case '9' -> Rank.NINE;
            case '0' -> Rank.TEN;
            case 'J' -> Rank.JACK;
            case 'Q' -> Rank.QUEEN;
            case 'K' -> Rank.KING;
            default -> throw new IllegalStateException();
        };
    }

    private static Suit parseSuit(String str) {
        return switch (str.charAt(0)){
            case 'D' -> Suit.DIAMONDS;
            case 'H' -> Suit.HEARTS;
            case 'C' -> Suit.CLUBS;
            case 'S' -> Suit.SPADES;
            default -> throw new IllegalStateException();
        };
    }
}
