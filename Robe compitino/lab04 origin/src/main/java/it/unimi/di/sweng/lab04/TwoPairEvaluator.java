package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class TwoPairEvaluator implements ChainedHandEvaluator {
    private final ChainedHandEvaluator next;
    public TwoPairEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand ph) {
        EnumMap<Rank, Integer> present = new EnumMap<>(Rank.class);
        boolean firstCouple = false;
        for(Card card : ph) {
            if(!present.containsKey(card.getRank())) present.put(card.getRank(), 1);
            else if (!firstCouple) {
                firstCouple = true;
                present.replace(card.getRank(), 2);
            } else return HandRank.TWO_PAIR;
        }
        if(next != null) return next.handEvaluator(ph);
        else throw new NoSuchElementException();
    }
}
