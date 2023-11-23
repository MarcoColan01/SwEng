package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PokerHand implements Iterable<Card>, Comparable<PokerHand>{
    private final List<Card> cards;

    public PokerHand(List<Card> cards) {
        this.cards = new ArrayList<>();
        for(Card card: cards){
            this.cards.add(Card.get(card.getRank(), card.getSuit()));
        }
    }

    @NotNull
    @Override
    public Iterator<Card> iterator() {
        List<Card> cardsIt = new ArrayList<>();
        for(Card card: cards){
            cardsIt.add(Card.get(card.getRank(), card.getSuit()));
        }
        return cardsIt.iterator();
    }

    public HandRank getRank() {
       ChainedHandEvaluator chain = new FlushEvaluator(
               new TwoPairEvaluator(
                       new OnePairEvaluator(ChainedHandEvaluator.HIGHCARD)));
       return chain.handEvaluator(this);
    }

    @Override
    public int compareTo(@NotNull PokerHand o) {
        return 0;
    }
}


