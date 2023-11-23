package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.NoSuchElementException;

public class PairEvaluator implements ChainedHandEvaluator{
    private final ChainedHandEvaluator next;
    public PairEvaluator(@Nullable ChainedHandEvaluator next){
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand ph) {
        EnumMap<Rank, Boolean> present = new EnumMap<>(Rank.class);
        for (Card card : ph) {
            if(present.containsKey(card.getRank()))
                return HandRank.ONE_PAIR;
            else
                present.put(card.getRank(), true);
        }
        if(next != null)
            return next.handEvaluator(ph);
        else throw new NoSuchElementException();
    }
}
