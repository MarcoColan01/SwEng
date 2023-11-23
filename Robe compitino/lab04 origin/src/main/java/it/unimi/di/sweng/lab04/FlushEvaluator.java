package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;

public class FlushEvaluator implements ChainedHandEvaluator{
    private final ChainedHandEvaluator next;
    public FlushEvaluator (@Nullable ChainedHandEvaluator next){
        this.next = next;
    }
    @Override
    public HandRank handEvaluator(PokerHand ph) {
        boolean ok = true;
        Suit app = ph.iterator().next().getSuit();
        for(Card card: ph){
            if(!card.getSuit().equals(app)) {
                ok = false;
            }
        }
        if(ok) return HandRank.FLUSH;
        if (next == null) throw new NoSuchElementException();
        return next.handEvaluator(ph);
    }
}
