package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class OnePairEvaluator implements ChainedHandEvaluator {
    private final ChainedHandEvaluator next;

    public OnePairEvaluator(ChainedHandEvaluator next) {this.next = next;}


    @Override
    public HandRank handEvaluator(PokerHand ph) {
        Map<Rank, Integer> app = new HashMap<>();
        for(Card card: ph){
            System.out.println(card.toString());
            if(app.containsKey(card.getRank())) return HandRank.ONE_PAIR;
            else app.put(card.getRank(), 1);
        }
        if (next == null) throw new NoSuchElementException();
        else return next.handEvaluator(ph);
    }
}
