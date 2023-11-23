package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;
import java.util.Random;

public interface SelettoreCarta {
    SelettoreCarta Random = (partita, mano) -> mano.get(new Random().nextInt(mano.size()-1));
    Card choiseStrategy(Partita partita, List<Card> mano);
}
