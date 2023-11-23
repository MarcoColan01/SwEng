package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.Map;

public class TwoPairEvaluator implements ChainedHandEvaluator {
    private final ChainedHandEvaluator next;

    public TwoPairEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand ph) {
        Map<Rank, Integer> app = new HashMap<>();
        boolean firstCouple = false;
        for (Card card : ph) {
            if (app.containsKey(card.getRank())) {
                if (app.get(card.getRank()) == 1){
                    if(firstCouple) return HandRank.TWO_PAIR;
                    else firstCouple = true;
                }
            } else app.put(card.getRank(), 1);
        }
        System.out.println("be");
        return next.handEvaluator(ph);
    }
}

