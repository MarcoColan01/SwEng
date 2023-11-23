package it.unimi.di.sweng.blackjack;

public interface Strategia {
  Strategia NULLSTRATEGY = () -> false;

  boolean chiediCarta();
}

