package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StrategiaTest {
    @Test
    void strategiaStayTest(){
        Strategia SUT = Strategia.STAY;
        assertThat(SUT.chiediCarta()).isFalse();
    }

    @Test
    void strategiaChiediCon12Test(){
        GiocatoreBJ giocatore = mock(GiocatoreBJ.class);
        Mazziere mazziere = mock(Mazziere.class);
        Strategia SUT = new AskWith12Strategy(Strategia.STAY, giocatore, mazziere);
        when(giocatore.getPunti()).thenReturn(12);
        when(mazziere.getCards()).thenReturn(
                List.of(
                        Card.get(Rank.TWO, Suit.HEARTS),
                        Card.get(Rank.SIX, Suit.HEARTS)).iterator());
        assertThat(SUT.chiediCarta()).isTrue();
    }

    @Test
    void strategiaStaiCon16Test(){
        GiocatoreBJ giocatore = mock(GiocatoreBJ.class);
        Mazziere mazziere = mock(Mazziere.class);
        Strategia SUT = new StayWith16Strategy(Strategia.STAY, giocatore, mazziere);
        when(giocatore.getCards()).thenReturn(List.of(
                Card.get(Rank.SEVEN, Suit.HEARTS),
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.THREE, Suit.HEARTS)).iterator());
        when(giocatore.getPunti()).thenReturn(16);
        when(mazziere.getCards()).thenReturn(
                List.of(
                        Card.get(Rank.TWO, Suit.HEARTS),
                        Card.get(Rank.TEN, Suit.HEARTS)).iterator());
        assertThat(SUT.chiediCarta()).isFalse();
    }

    @Test
    void strategiaStaiConCoppia9Test(){
        GiocatoreBJ giocatore = mock(GiocatoreBJ.class);
        Mazziere mazziere = mock(Mazziere.class);
        Strategia SUT = new StayWith9CoupleStrategy(Strategia.STAY, giocatore, mazziere);
        when(giocatore.getCards()).thenReturn(List.of(
                Card.get(Rank.ACE, Suit.HEARTS),
                Card.get(Rank.NINE, Suit.HEARTS),
                Card.get(Rank.NINE, Suit.SPADES)).iterator());
        when(giocatore.getPunti()).thenReturn(18);
        when(mazziere.getCards()).thenReturn(
                List.of(
                        Card.get(Rank.TWO, Suit.HEARTS),
                        Card.get(Rank.SEVEN, Suit.HEARTS)).iterator());
        assertThat(SUT.chiediCarta()).isFalse();
    }

    @Test
    void strategiaConcatenata1Test(){
        Mazziere mazziere = mock(Mazziere.class);
        Sfidante SUT = new Sfidante("Marco", mazziere);
        GiocatoreBJ giocatore = mock(GiocatoreBJ.class);
        SUT.setStrategia(new AskWith12Strategy(new StayWith16Strategy(
                Strategia.STAY, giocatore, mazziere),
                giocatore, mazziere));
        when(giocatore.getCards()).thenReturn(List.of(
                Card.get(Rank.SIX, Suit.HEARTS),
                Card.get(Rank.FOUR, Suit.HEARTS),
                Card.get(Rank.TWO, Suit.HEARTS)).iterator());
        when(mazziere.getCards()).thenReturn(
                List.of(
                        Card.get(Rank.TWO, Suit.HEARTS),
                        Card.get(Rank.SEVEN, Suit.HEARTS)).iterator());
        when(giocatore.getPunti()).thenReturn(12);
        when(mazziere.draw()).thenReturn(Card.get(Rank.FOUR, Suit.HEARTS));
        SUT.gioca();
        assertThat(giocatore.getPunti()).isEqualTo(16);
    }
}
