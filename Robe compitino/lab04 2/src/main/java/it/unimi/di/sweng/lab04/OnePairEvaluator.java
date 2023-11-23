package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class OnePairEvaluator implements ChainedHandEvaluator {

    private final ChainedHandEvaluator next;

    public OnePairEvaluator(@NotNull ChainedHandEvaluator next){this.next = next;}
    @Override
    public HandRank handEvaluator(PokerHand ph) {
        EnumSet<Rank> present = EnumSet.noneOf(Rank.class);
        for(Card card: ph){
            if(present.contains(card.getRank())) return HandRank.ONE_PAIR;
            else present.add(card.getRank());
        }
        return next.handEvaluator(ph);
    }
}
