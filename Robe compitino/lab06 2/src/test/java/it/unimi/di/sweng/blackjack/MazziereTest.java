package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.IterableAssert.assertThatIterable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class MazziereTest {

    @Mock
    MultiMazzo mazzo;
    @InjectMocks
    Mazziere SUT;

    @Test
    void newMazziereTest() {
        Mazziere SUT = new Mazziere();
        SUT.carteIniziali();
        assertThat(SUT.getCards()).toIterable().hasSize(1);
    }

    @Test
    void mazziereGiocaNoAssiTest(){
        when(mazzo.draw()).thenReturn(
                Card.get(Rank.QUEEN, Suit.DIAMONDS),
                Card.get(Rank.NINE, Suit.CLUBS));
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(19);
    }

    @Test
    void mazziereGiocaConAssiTest(){
        when(mazzo.draw()).thenReturn(
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.NINE, Suit.CLUBS));
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(20);
    }

    @Test
    void mazziereGiocaConAssi2Test(){
        when(mazzo.draw()).thenReturn(
                Card.get(Rank.TEN, Suit.DIAMONDS),
                Card.get(Rank.SIX, Suit.DIAMONDS),
                Card.get(Rank.ACE, Suit.CLUBS));
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(17);
    }
}
