package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Mazziere implements GiocatoreBJ {
    private final List<Card> mano = new ArrayList<>();
    private MultiMazzo mazzo = new MultiMazzo(6);

    @Override
    public void carteIniziali() {
        mano.add(mazzo.draw());
    }

    @Override
    public void gioca() {
        while(getPunti() < 17){
            Card card = mazzo.draw();
            mano.add(card);
        }
    }

    @Override
    public Iterator<Card> getCards() {
        return Collections.unmodifiableList(mano).iterator();
    }

    @Override
    public @NotNull String getName() {
        return "Banco";
    }

    public Card draw() {
        return mazzo.draw();
    }
}
