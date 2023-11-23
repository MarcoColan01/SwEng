package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiMazzo implements DeckInterface {
    private final List<Deck> multiMazzo = new ArrayList<>();

    public MultiMazzo(int numMazzi) {
        for (int i = 0; i < numMazzi; i++) {
            multiMazzo.add(new Deck());
        }
    }

    @Override
    public @NotNull Card draw() {
        if(multiMazzo.isEmpty()) throw new IllegalCallerException("Non si può estrarre una carta da un mazzo vuoto!");
        int deckToDraw = new Random().nextInt(multiMazzo.size());
        Card card = multiMazzo.get(deckToDraw).draw();
        if (multiMazzo.get(deckToDraw).isEmpty())
            multiMazzo.remove(deckToDraw);
        return card;
    }

    @Override
    public boolean isEmpty() {
        for (Deck deck : multiMazzo) {
            if (!deck.isEmpty()) return false;
        }
        return true;
    }
}
