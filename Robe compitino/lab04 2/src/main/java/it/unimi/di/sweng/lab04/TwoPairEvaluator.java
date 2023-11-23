package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class TwoPairEvaluator implements ChainedHandEvaluator {

    private final ChainedHandEvaluator next;

    public TwoPairEvaluator(ChainedHandEvaluator next){this.next = next;}
    @Override
    public HandRank handEvaluator(PokerHand ph) {
        EnumSet<Rank> present = EnumSet.noneOf(Rank.class);
        boolean primaCoppia = false;
        for(Card card: ph){
            if(present.contains(card.getRank())){
                if(primaCoppia) return HandRank.TWO_PAIR;
                else primaCoppia = true;
            }else{
                present.add(card.getRank());
            }
        }
        if(next == null) throw new NoSuchElementException();
        else return next.handEvaluator(ph);
    }
}
