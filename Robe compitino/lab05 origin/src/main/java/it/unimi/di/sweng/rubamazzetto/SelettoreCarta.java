package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;
import java.util.Random;

public interface SelettoreCarta {
    public static final SelettoreCarta Random = new SelettoreCarta() {
        @Override
        public Card choiseStrategy(Partita partita, List<Card> mano) {
            Random rand = new Random();
            int index = rand.nextInt(mano.size() - 1);
            System.out.println(index);
            return mano.get(index);
        }
    };
    Card choiseStrategy(Partita partita, List<Card> mano);
}
