package it.unimi.di.sweng.lab04;

public class HighCardEvaluator implements ChainedHandEvaluator{

    @Override
    public HandRank handEvaluator(PokerHand ph) {
        return HandRank.HIGH_CARD;
    }
}
