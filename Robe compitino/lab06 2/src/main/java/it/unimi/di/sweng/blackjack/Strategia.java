package it.unimi.di.sweng.blackjack;

public interface Strategia {
  Strategia STAY = () -> false;
    boolean chiediCarta();
}

