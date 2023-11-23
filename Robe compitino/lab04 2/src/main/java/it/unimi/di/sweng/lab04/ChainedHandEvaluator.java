package it.unimi.di.sweng.lab04;

public interface ChainedHandEvaluator {
    ChainedHandEvaluator NULL = ph -> HandRank.HIGH_CARD;
    HandRank handEvaluator(PokerHand ph);
}
