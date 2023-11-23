package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import it.unimi.di.sweng.rubamazzetto.Giocatore;
import it.unimi.di.sweng.rubamazzetto.Partita;
import it.unimi.di.sweng.rubamazzetto.Tavolo;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RubaMazzettoTest {

    @Test
    void newPartitaTest(){
        Partita partita = mock(Partita.class);
        Giocatore g1 = mock(Giocatore.class);
        Giocatore g2 = mock(Giocatore.class);
        Giocatore g3 = mock(Giocatore.class);
        partita.addGiocatore(g1);
        partita.addGiocatore(g2);
        partita.addGiocatore(g3);
        Iterator<Giocatore> it = partita.iterator();
        when(partita.iterator()).thenReturn(it);
    }

    @Test
    void distribuisciManoTest(){
        Partita partita = new Partita();
        partita.distribuisciMano(3);
    }

    @Test
    void checkCartaTavoloTest(){
        //ArrayList cards = mock(ArrayList.class);
        List<Card> cards = List.of(
                Card.get(Rank.FIVE, Suit.DIAMONDS),
                Card.get(Rank.FOUR, Suit.SPADES),
                Card.get(Rank.KING, Suit.CLUBS),
                Card.get(Rank.SIX, Suit.HEARTS)
        );
        Partita partita = new Partita(cards);
        assertThat(partita.controllaSeCartaPresenteSuTavolo(Card.get(Rank.KING, Suit.CLUBS))).isTrue();
    }

    @Test
    void giocaCartaTest(){
        List<Card> cards = List.of(
                Card.get(Rank.FIVE, Suit.DIAMONDS),
                Card.get(Rank.FOUR, Suit.SPADES),
                Card.get(Rank.KING, Suit.CLUBS),
                Card.get(Rank.SIX, Suit.HEARTS)
        );
        Partita partita = new Partita(cards);
        assertThat(partita.giocaCarta(mock(Giocatore.class), Card.get(Rank.KING, Suit.CLUBS))).isEqualTo(2);
    }

    @Test
    void giocatoreTurnoTest1(){
        Partita partita = new Partita(
                List.of(
                        Card.get(Rank.THREE, Suit.DIAMONDS),
                        Card.get(Rank.FOUR, Suit.HEARTS),
                        Card.get(Rank.ACE, Suit.CLUBS),
                        Card.get(Rank.SEVEN, Suit.DIAMONDS),
                        Card.get(Rank.KING, Suit.SPADES)
                ));
        Giocatore giocatore1 = new Giocatore("Marco", partita);
        giocatore1.daiCarta(Card.get(Rank.SEVEN, Suit.DIAMONDS));
        giocatore1.turno();
        assertThat(giocatore1.getPunti()).isEqualTo(2);
    }

    @Test
    void giocatoreTurnoTest2(){
        Partita partita = new Partita(
                List.of(
                        Card.get(Rank.SEVEN, Suit.CLUBS),
                        Card.get(Rank.SEVEN, Suit.CLUBS),
                        Card.get(Rank.ACE, Suit.SPADES),
                        Card.get(Rank.SEVEN, Suit.CLUBS),
                        Card.get(Rank.KING, Suit.DIAMONDS)
                ));
        List<Card> mazzo1 = new ArrayList<>();
        mazzo1.add(Card.get(Rank.THREE, Suit.DIAMONDS));
        mazzo1.add(Card.get(Rank.FOUR, Suit.HEARTS));
        mazzo1.add(Card.get(Rank.SIX, Suit.CLUBS));
        mazzo1.add(Card.get(Rank.FIVE, Suit.DIAMONDS));
        mazzo1.add(Card.get(Rank.QUEEN, Suit.SPADES));

        Giocatore giocatore1 = new Giocatore("Marco", partita, mazzo1, 10);
        Giocatore giocatore2 = new Giocatore("Massimiliano", partita, mazzo1, 0);
        giocatore2.turno();
        assertThat(giocatore1.getPunti()).isEqualTo(0);
        assertThat(giocatore2.getPunti()).isEqualTo(10);
    }

    @Test
    void giocatoreTurnoTest3(){
        Partita partita = new Partita(
                List.of(
                        Card.get(Rank.SEVEN, Suit.CLUBS),
                        Card.get(Rank.SEVEN, Suit.CLUBS),
                        Card.get(Rank.ACE, Suit.SPADES),
                        Card.get(Rank.SEVEN, Suit.CLUBS),
                        Card.get(Rank.KING, Suit.DIAMONDS)
                ));
        List<Card> mazzo1 = new ArrayList<>();
        mazzo1.add(Card.get(Rank.THREE, Suit.DIAMONDS));
        mazzo1.add(Card.get(Rank.FOUR, Suit.HEARTS));
        mazzo1.add(Card.get(Rank.SIX, Suit.CLUBS));
        mazzo1.add(Card.get(Rank.THREE, Suit.DIAMONDS));
        mazzo1.add(Card.get(Rank.QUEEN, Suit.SPADES));

        Giocatore giocatore1 = new Giocatore("Marco", partita, mazzo1, 0);
        int points = giocatore1.getPunti();
        giocatore1.turno();
        assertThat(giocatore1.getPunti()).isEqualTo(points);
    }
}
