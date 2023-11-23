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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SfidanteTest {

    @Mock
    Mazziere banco;
    @InjectMocks
    Sfidante SUT;

    @Test
    void newSfidanteTest(){
        SUT.carteIniziali();
        assertThat(SUT.getCards()).toIterable().hasSize(2);
    }

    @Test
    void giocaSfidanteTest(){
        Strategia strategia = mock(Strategia.class);
        when(banco.draw()).thenReturn(
                Card.get(Rank.FOUR, Suit.DIAMONDS),
                Card.get(Rank.SIX, Suit.CLUBS));
        when(strategia.chiediCarta()).thenReturn(true, true,false);
        SUT.setStrategia(strategia);
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(10);
    }

    @Test
    void giocaSfidanteConAssoTest1(){
        Strategia strategia = mock(Strategia.class);
        when(banco.draw()).thenReturn(
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.SIX, Suit.CLUBS));
        when(strategia.chiediCarta()).thenReturn(true, true,false);
        SUT.setStrategia(strategia);
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(17);
    }

    @Test
    void giocaSfidanteConAssoTest2(){
        Strategia strategia = mock(Strategia.class);
        when(banco.draw()).thenReturn(
                Card.get(Rank.TEN, Suit.DIAMONDS),
                Card.get(Rank.EIGHT, Suit.CLUBS),
                Card.get(Rank.ACE, Suit.CLUBS));
        when(strategia.chiediCarta()).thenReturn(true, true,true,false);
        SUT.setStrategia(strategia);
        SUT.gioca();
        assertThat(SUT.getPunti()).isEqualTo(19);
    }
}
