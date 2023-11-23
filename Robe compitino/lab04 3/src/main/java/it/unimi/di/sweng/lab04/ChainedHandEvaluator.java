package it.unimi.di.sweng.lab04;

public interface ChainedHandEvaluator {
    ChainedHandEvaluator HIGHCARD = (PokerHand ph) -> HandRank.HIGH_CARD;
    HandRank handEvaluator(PokerHand ph);

}
