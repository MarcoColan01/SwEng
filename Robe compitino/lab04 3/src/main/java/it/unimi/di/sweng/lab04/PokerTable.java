package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PokerTable implements Iterable<PokerHand>{
    private final List<PokerHand> giocatori;
    private final Deck deck;

    public PokerTable(int n) {
        deck = new Deck();
        giocatori = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            List<Card> cards = new ArrayList<>();
            for(int j = 0; j < 5; j++){cards.add(deck.draw());}
            giocatori.add(new PokerHand(cards));
        }
    }

    @NotNull
    @Override
    public Iterator<PokerHand> iterator() {
        return Collections.unmodifiableList(giocatori).iterator();
    }

    public PokerHand getHand(int i) {
        List<Card> cards = new ArrayList<>();
        for(Card card: giocatori.get(i)){
            cards.add(card);
        }
        PokerHand ph = new PokerHand(cards);
        return ph;
    }
}
