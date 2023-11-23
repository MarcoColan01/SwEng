package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PokerHand implements Iterable<Card>, Comparable<PokerHand>{
    private final List<Card> cards;

    public PokerHand(List<Card> cards){
        this.cards = new ArrayList<>(cards);
    }

    @NotNull
    @Override
    public Iterator<Card> iterator() {
        return new ArrayList<>(cards).iterator();
    }

    public HandRank getRank() {
        ChainedHandEvaluator chain = new FlushEvaluator(new TwoPairEvaluator(new OnePairEvaluator(ChainedHandEvaluator.NULL)));
        return chain.handEvaluator(this);
    }

    @Override
    public int compareTo(@NotNull PokerHand o) {
        if(this.getRank().ordinal() > o.getRank().ordinal()) return 1;
        else if(this.getRank().ordinal() < o.getRank().ordinal()) return -1;
        else return 0;
    }
}


