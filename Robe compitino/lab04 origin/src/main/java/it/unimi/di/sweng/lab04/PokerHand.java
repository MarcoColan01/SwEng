package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PokerHand implements Iterable<Card>, Comparable<PokerHand> {
    private List<Card> mano;
    static private ChainedHandEvaluator chain = new FlushEvaluator(new TwoPairEvaluator(new PairEvaluator(new HighCardEvaluator()))) {};
    public PokerHand(List<Card> carte){
        this.mano = new ArrayList<>(carte);
    }

    @NotNull
    @Override
    public Iterator<Card> iterator() {
        return new ArrayList<>(mano).iterator();
    }

    public HandRank getRank() {
        return chain.handEvaluator(this);
    }

    @Override
    public int compareTo(@NotNull PokerHand o) {
        if(this.getRank().ordinal() == o.getRank().ordinal())
            return 0;
        else if (this.getRank().ordinal() > o.getRank().ordinal())
            return 1;
        else
            return -1;
    }
}


