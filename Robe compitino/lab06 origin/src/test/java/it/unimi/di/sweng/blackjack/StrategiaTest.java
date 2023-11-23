package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StrategiaTest {
    Strategia SUT;
    @Test
    void nullStrategyTest() {
        SUT = Strategia.NULLSTRATEGY;
        assertThat(SUT.chiediCarta()).isFalse();
    }

    @Test
    void strategiaConservativaTest() {
        GiocatoreBJ giocatore = mock(GiocatoreBJ.class);
        SUT = new StrategiaConservativa(Strategia.NULLSTRATEGY, giocatore);

        assertThat(SUT.chiediCarta()).isTrue();
    }

    @Test
    void strategiaConservativaNonPescaTest() {
        GiocatoreBJ giocatore = mock(GiocatoreBJ.class);
        SUT = new StrategiaConservativa(Strategia.NULLSTRATEGY, giocatore);
        when(giocatore.getPunti()).thenReturn(12);

        assertThat(SUT.chiediCarta()).isFalse();
    }

    @Test
    void strategiaPescaAncheCon18Test() {
        GiocatoreBJ giocatore = mock(GiocatoreBJ.class);
        Mazziere mazziere = mock(Mazziere.class);
        SUT = new StrategiaPescaCon18(Strategia.NULLSTRATEGY, giocatore, mazziere);
        when(giocatore.getPunti()).thenReturn(18);
        when(mazziere.getCards()).thenReturn(
                List.of(Card.get(Rank.NINE, Suit.HEARTS)).iterator());

        assertThat(SUT.chiediCarta()).isTrue();
    }

    @Test
    void strategiaConservativaDelegaTest() {
        GiocatoreBJ giocatore = mock(GiocatoreBJ.class);
        Strategia next = mock(Strategia.class);
        SUT = new StrategiaConservativa(next, giocatore);
        when(giocatore.getPunti()).thenReturn(12);

        SUT.chiediCarta();
        verify(next).chiediCarta();
    }

    @Test
    void strategiaPescaAncheCon18DelegaTest() {
        GiocatoreBJ giocatore = mock(GiocatoreBJ.class);
        Mazziere mazziere = mock(Mazziere.class);
        Strategia next = mock(Strategia.class);
        SUT = new StrategiaPescaCon18(next, giocatore, mazziere);
        when(giocatore.getPunti()).thenReturn(18);
        when(mazziere.getCards()).thenReturn(
                List.of(Card.get(Rank.EIGHT, Suit.HEARTS)).iterator());

        SUT.chiediCarta();
        verify(next).chiediCarta();
    }
}
