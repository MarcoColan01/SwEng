package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mockito;


import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@Timeout(2)
public class PokerHandTest{


  @Test
  void newPokerHandTest() {
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.QUEEN, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.HEARTS)
                    )
    );
    assertThatIterable(ph).containsExactlyInAnyOrder(
            Card.get(Rank.KING, Suit.HEARTS),
            Card.get(Rank.QUEEN, Suit.SPADES),
            Card.get(Rank.THREE, Suit.DIAMONDS),
            Card.get(Rank.ACE, Suit.CLUBS),
            Card.get(Rank.FIVE, Suit.HEARTS)
    );
  }

  @Test
  void newPokerTableTest(){
    PokerTable pt = new PokerTable(3);
    assertThatIterable(pt).hasSize(3);
    for(PokerHand ph: pt){
      assertThatIterable(ph).hasSize(5);
    }
  }

  @Test
  void testPair() {
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.HEARTS)
            )
    );
    ChainedHandEvaluator ch = new OnePairEvaluator(null);
    assertThat(ch.handEvaluator(ph)).isEqualTo(HandRank.ONE_PAIR);
  }

  @Test
  void testNotPair(){
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.FOUR, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.HEARTS)
            )
    );
    ChainedHandEvaluator mockedNULL = Mockito.mock(ChainedHandEvaluator.class);
    Mockito.when(mockedNULL.handEvaluator(any())).thenThrow(AbstractMethodError.class);
    ChainedHandEvaluator ch = new OnePairEvaluator(mockedNULL);
    assertThatThrownBy(() -> ch.handEvaluator(ph)).isInstanceOf(AbstractMethodError.class);
  }

  @Test
  void twoPairTest1(){
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.HEARTS)
            )
    );
    ChainedHandEvaluator ch = new TwoPairEvaluator(null);
    assertThat(ch.handEvaluator(ph)).isEqualTo(HandRank.TWO_PAIR);
  }

  @Test
  //TEST CATENA
  void testChain1(){
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.HEARTS)
            )
    );
    assertThat(ph.getRank()).isEqualTo(HandRank.ONE_PAIR);
  }

  @Test
    //TEST CATENA
  void testChain2(){
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.HEARTS)
            )
    );
    assertThat(ph.getRank()).isEqualTo(HandRank.TWO_PAIR);
  }

  @Test
    //TEST CATENA
  void testChain3(){
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.SIX, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FOUR, Suit.HEARTS)
            )
    );
    assertThat(ph.getRank()).isEqualTo(HandRank.HIGH_CARD);
  }

  @Test
  void flushTest(){
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.SIX, Suit.HEARTS),
                    Card.get(Rank.THREE, Suit.HEARTS),
                    Card.get(Rank.ACE, Suit.HEARTS),
                    Card.get(Rank.FOUR, Suit.HEARTS)
            )
    );
    assertThat(ph.getRank()).isEqualTo(HandRank.FLUSH);
  }

  @Test
  void getHandTest(){
    PokerTable pt = new PokerTable(4);
    int playerPos = 0;
    for(PokerHand cards: pt){
      assertThatIterable(cards).containsExactlyElementsOf(pt.getHand(playerPos));
      playerPos++;
    }
  }

  @Test
  void sortPokerHandsTest1(){
    PokerHand ph1 = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.HEARTS)
            )
    );

    PokerHand ph2 = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.HEARTS)
            )
    );
    assertThat(ph2.compareTo(ph1)).isEqualTo(1);
  }

  @Test
  void sortPokerHandsTest2(){
    PokerHand ph1 = new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.FOUR, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.HEARTS)
            )
    );

    PokerHand ph2 = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.SIX, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.HEARTS)
            )
    );
    assertThat(ph2.compareTo(ph1)).isEqualTo(0);
  }

  @Test
  void changeTest(){
    PokerTable pt = new PokerTable(6);
    PokerHand ph = new PokerHand(
            List.of(
                    Card.get(Rank.ACE, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.SIX, Suit.DIAMONDS),
                    Card.get(Rank.FIVE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.HEARTS)
            )
    );
    pt.change(2, ph);
    assertThatIterable(pt.getHand(2)).containsExactlyElementsOf(ph);
  }

  @Test
  void getRankingTest(){
    PokerTable pt = new PokerTable(3);
    pt.change(0, new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.THREE, Suit.HEARTS)
            )
    ));

    pt.change(1, new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.SIX, Suit.HEARTS),
                    Card.get(Rank.THREE, Suit.HEARTS),
                    Card.get(Rank.ACE, Suit.HEARTS),
                    Card.get(Rank.FOUR, Suit.HEARTS)
            )
    ));

    pt.change(2, new PokerHand(
            List.of(
                    Card.get(Rank.KING, Suit.HEARTS),
                    Card.get(Rank.KING, Suit.SPADES),
                    Card.get(Rank.THREE, Suit.DIAMONDS),
                    Card.get(Rank.ACE, Suit.CLUBS),
                    Card.get(Rank.FIVE, Suit.HEARTS)
            )
    ));
    int[] expectedRanking = {1,0,2};
    int i = 0;
    Iterator<Integer> ranking = pt.getRanking();
    while(ranking.hasNext()){
      assertThat(ranking.next()).isEqualTo(expectedRanking[i++]);
    }
  }
}
