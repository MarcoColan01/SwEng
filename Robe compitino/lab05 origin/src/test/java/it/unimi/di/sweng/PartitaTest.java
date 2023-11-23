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
import static org.mockito.Mockito.when;

public class PartitaTest {
    @Test
    void setUpPartita(){
        Partita partita = new Partita();
        Giocatore giocatore1 = new Giocatore("Marco", partita);
        Giocatore giocatore2 = new Giocatore("Massimiliano", partita);
        Giocatore giocatore3 = new Giocatore("Pablo", partita);
        assertThatIterable(partita).containsExactlyInAnyOrder(giocatore1, giocatore2, giocatore3);
    }

    @Test
    void distribuisciManoTest(){
        Partita partita = new Partita();
        Giocatore Marco = new Giocatore("Marco", partita);
        Giocatore Massimiliano = new Giocatore("Massimiliano", partita);
        Giocatore Pablo = new Giocatore("Pablo", partita);
        partita.distribuisciMano(3);
    }

    @Test
    void checkTableCardTest()  {
        List<Card> carte = List.of(
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.QUEEN, Suit.HEARTS),
                Card.get(Rank.JACK, Suit.SPADES),
                Card.get(Rank.TEN, Suit.CLUBS)
        );
        Partita p = new Partita(carte);
        assertThat(p.controllaSeCartaPresenteSuTavolo(Card.get(Rank.ACE, Suit.DIAMONDS))).isTrue();

    }

    @Test
    void giocaCartaTest(){
        Partita partita = new Partita(List.of(
                Card.get(Rank.ACE, Suit.DIAMONDS),
                Card.get(Rank.QUEEN, Suit.HEARTS),
                Card.get(Rank.JACK, Suit.SPADES),
                Card.get(Rank.TEN, Suit.CLUBS)
        ));
        Giocatore Marco = new Giocatore("Marco", partita);
        Card c = Card.get(Rank.ACE, Suit.CLUBS);
        Marco.daiCarta(c);
        assertThat(partita.giocaCarta(Marco, c)).isEqualTo(2);
    }

    @Test
    void giocatoreTurnoTest(){
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
}
