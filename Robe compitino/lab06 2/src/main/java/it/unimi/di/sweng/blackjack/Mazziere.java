package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Mazziere  implements GiocatoreBJ{
    private List<Card> mano = new ArrayList<>();
    private MultiMazzo multimazzo = new MultiMazzo(6);

    @Override
    public void carteIniziali() {
        mano.add(multimazzo.draw());
    }

    @Override
    public void gioca() {
        while(getPunti() < 17){
            Card card = multimazzo.draw();
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

    @Override
    public int getNumCards() {
        return mano.size();
    }

    public Card draw() {
        return multimazzo.draw();
    }
}
