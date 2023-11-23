package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Suit;

public class FlushEvaluator implements ChainedHandEvaluator {
    private final ChainedHandEvaluator next;

    public FlushEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand ph) {
        Suit suit = ph.iterator().next().getSuit();
        for(Card card: ph){
            if(!card.getSuit().equals(suit)) return next.handEvaluator(ph);
        }
        return HandRank.FLUSH;

    }
}
