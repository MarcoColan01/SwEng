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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MazziereTest {
    @Mock
    MultiMazzo mazzo;
    @InjectMocks
    Mazziere SUT;

    @Test
    void IterableTest() {
        Card card = Card.get(Rank.ACE, Suit.CLUBS);

        when(mazzo.draw()).thenReturn(card);
        SUT.carteIniziali();

        assertThat(SUT.getCards()).toIterable().contains(card);
    }

    @Test
    void giocaNoAssiTest(){
        when(mazzo.draw()).thenReturn(
                Card.get(Rank.KING, Suit.CLUBS),
                Card.get(Rank.QUEEN, Suit.DIAMONDS)
        );
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(20);
    }
}
