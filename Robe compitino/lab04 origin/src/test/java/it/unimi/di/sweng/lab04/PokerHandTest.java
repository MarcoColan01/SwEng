package it.unimi.di.sweng.lab04;



import static org.assertj.core.api.Assertions.*;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;


@Timeout(2)
public class PokerHandTest {
  @Test
  void newPokerHandTest() {
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.DIAMONDS),
                    Card.get(Rank.KING, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.SPADES)
                    )
    );
    assertThatIterable(ph).containsExactlyInAnyOrder(
            Card.get(Rank.ACE, Suit.CLUBS),
            Card.get(Rank.FIVE, Suit.DIAMONDS),
            Card.get(Rank.KING, Suit.CLUBS),
            Card.get(Rank.QUEEN, Suit.CLUBS),
            Card.get(Rank.FOUR, Suit.SPADES));
  }

  @Test
  void newPokerTablePlayersNumTest() {
    int n = 3;
    PokerTable pt = new PokerTable(n);
    assertThat(pt).hasSize(3);
  }

  @Test
  void newPokerTableCardsForEachPlayer() {
    int n = 3;
    PokerTable pt = new PokerTable(n);
    assertThat(pt).allSatisfy(new Consumer<PokerHand>() {
      @Override
      public void accept(PokerHand mano) {
        assertThatIterable(mano).hasSize(5);
      }
    });
  }

  @Test
  void IsOnePairTest(){
    PokerHand pt = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.SPADES)
            )
    );
  assertThat(pt.getRank()).isEqualTo(HandRank.ONE_PAIR);
  }

  @Test
  void IsOnePairTest2(){
    PokerHand pt = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.SPADES)
            )
    );
    ChainedHandEvaluator eval = new PairEvaluator(null);
    assertThat(eval.handEvaluator(pt)).isEqualTo(HandRank.ONE_PAIR);
  }

  @Test
  void IsOnePairTest3(){
    PokerHand pt = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.SPADES)
            )
    );
    ChainedHandEvaluator eval = new PairEvaluator(null);
    assertThatThrownBy(() -> eval.handEvaluator(pt)).isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void IsFlushTest1(){
    PokerHand pt = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.CLUBS)
            )
    );
    ChainedHandEvaluator eval = new FlushEvaluator(null);
    assertThat(eval.handEvaluator(pt)).isEqualTo(HandRank.FLUSH);
  }

  @Test
  void IsFlushTest2(){
    PokerHand pt = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.SPADES)
            )
    );
    ChainedHandEvaluator eval = new FlushEvaluator(null);
    assertThatThrownBy(() -> eval.handEvaluator(pt)).isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void isTwoPairTest1() {
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.ACE, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.SPADES)
            )
    );
    ChainedHandEvaluator eval = new TwoPairEvaluator(null);
    assertThat(eval.handEvaluator(ph)).isEqualTo(HandRank.TWO_PAIR);
  }

  @Test
  void isTwoPairTest2() {
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.SIX, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.SPADES)
            )
    );
    ChainedHandEvaluator eval = new TwoPairEvaluator(null);
    assertThatThrownBy(() -> eval.handEvaluator(ph)).isInstanceOf(NoSuchElementException.class);
  }

  @Test
  void chainTest() {
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.SIX, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.SPADES)
            )
    );
    assertThat(ph.getRank()).isEqualTo(HandRank.HIGH_CARD);
  }

  @Test
  void chainTest1() {
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.ACE, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.SPADES)
            )
    );
    assertThat(ph.getRank()).isEqualTo(HandRank.TWO_PAIR);
  }

  @Test
  void getHandTest(){
    PokerTable pt = new PokerTable(3);
    assertThat(pt.getHand(1).toString()).isEqualTo(pt.toString(1));
  }

  @Test
  void compareTest() {
    PokerHand Marco = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.CLUBS)
            )
    );
    PokerHand Ale = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.ACE, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.SPADES)
            )
    );
    assertThatComparable(Marco.compareTo(Ale)).isEqualTo(1);
  }

  @Test
  void compareTest1() {
    PokerHand Ale = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.ACE, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.SPADES)
            )
    );
    PokerHand Marco = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.CLUBS)
            )
    );
    assertThatComparable(Ale.compareTo(Marco)).isEqualTo(-1);
  }

  @Test
  void compareTest2() {
    PokerHand Marco = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.CLUBS)
            )
    );
    PokerHand Ale = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.QUEEN, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.CLUBS)
            )
    );
    assertThatComparable(Marco.compareTo(Ale)).isEqualTo(0);
  }

  @Test
  void changeHandTest(){
    List<Card> cards = List.of(
            Card.get(Rank.ACE, Suit.CLUBS),
            Card.get(Rank.FOUR, Suit.CLUBS),
            Card.get(Rank.FIVE, Suit.CLUBS),
            Card.get(Rank.QUEEN, Suit.CLUBS)
    );
    PokerTable pt = new PokerTable(3);
    pt.change(1, cards);
    assertThatIterable(pt.getHand(1)).containsAll(cards);
  }

  @Test
  void rankingTest() {
    PokerTable pt = new PokerTable(3);
    List<Card> Ale = List.of(
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.ACE, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.SPADES)
    );
    List<Card> Marco = List.of(
            Card.get(Rank.ACE, Suit.CLUBS),
            Card.get(Rank.FOUR, Suit.CLUBS),
            Card.get(Rank.FIVE, Suit.CLUBS),
            Card.get(Rank.QUEEN, Suit.CLUBS),
            Card.get(Rank.THREE, Suit.CLUBS)
    );
    List<Card> Carlo = List.of(
            Card.get(Rank.ACE, Suit.CLUBS),
            Card.get(Rank.KING, Suit.DIAMONDS),
            Card.get(Rank.FIVE, Suit.CLUBS),
            Card.get(Rank.QUEEN, Suit.CLUBS),
            Card.get(Rank.THREE, Suit.CLUBS)
    );
    pt.setHand(0,Ale);
    pt.setHand(1,Marco);
    pt.setHand(2,Carlo);
    //Marco-Ale-Carlo -> 1-0-2
    int[] classificaNota = {1, 0, 2};
    int i = 0;
    Iterator<Integer> classifica = pt.getRanking();
    while (classifica.hasNext()) {
      assertThat(classifica.next()).isEqualTo(classificaNota[i++]);
    }
  }
}
