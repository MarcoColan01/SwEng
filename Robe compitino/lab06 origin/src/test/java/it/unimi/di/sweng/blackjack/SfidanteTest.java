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
public class SfidanteTest {
    @Mock
    Mazziere banco;
    @InjectMocks
    Sfidante SUT;

    @Test
    void IterableTest() {
        Card card = Card.get(Rank.ACE, Suit.CLUBS);

        when(banco.draw()).thenReturn(card);
        SUT.carteIniziali();

        assertThat(SUT.getCards()).toIterable().contains(card);
    }

    @Test
    void giocaNoAssiTest(){
        when(banco.draw()).thenReturn(
                Card.get(Rank.KING, Suit.CLUBS),
                Card.get(Rank.QUEEN, Suit.DIAMONDS)
        );
        Strategia strategia = mock(Strategia.class);
        SUT.setStrategia(strategia);
        when(strategia.chiediCarta()).thenReturn(true, false);
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(10);
        verify(banco).draw();
    }

    @Test
    void giocaConAssiTest(){
        when(banco.draw()).thenReturn(
                Card.get(Rank.ACE, Suit.CLUBS),
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.ACE, Suit.HEARTS),
                Card.get(Rank.EIGHT, Suit.SPADES)
        );
        Strategia strategia = mock(Strategia.class);
        SUT.setStrategia(strategia);
        when(strategia.chiediCarta()).thenReturn(true,true,true,true,false);
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(21);
    }

    @Test
    void isSballatoTest(){
        when(banco.draw()).thenReturn(
                Card.get(Rank.TEN, Suit.SPADES)
        );

        Strategia strategia = mock(Strategia.class);
        SUT.setStrategia(strategia);
        when(strategia.chiediCarta()).thenReturn(true, true, true, true, false);

        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(30);
        verify(banco, times(3)).draw();
    }

}
