package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Sfidante implements GiocatoreBJ {

    final private String name;
    final private Mazziere banco;
    final private List<Card> mano = new ArrayList<>();
    private Strategia strategia;


    public Sfidante(@NotNull String name, @NotNull Mazziere banco) {
        this.name = name;
        this.banco = banco;
    }

    public void setStrategia(@NotNull Strategia strategia) {
        this.strategia = strategia;
    }

    @Override
    public void carteIniziali() {
        mano.add(banco.draw());
        mano.add(banco.draw());
    }

    @Override
    public void gioca() {
        while (strategia.chiediCarta()) {
            mano.add(banco.draw());
            if (isSballato()) return;
        }
    }

    @Override
    public Iterator<Card> getCards() {
        return Collections.unmodifiableList(mano).iterator();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public int getNumCards() {
        return mano.size();
    }
}
