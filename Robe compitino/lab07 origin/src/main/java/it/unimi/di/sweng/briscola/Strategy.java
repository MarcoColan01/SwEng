package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface Strategy {
  Strategy NULL = new Strategy() {
    @Override
    public @NotNull Card chooseCard(@NotNull Player me, @NotNull Player other, @NotNull Suit briscola) {
      List<Card> cards = new ArrayList<>();
      for(Card card: me){
        cards.add(card);
      }
      Random rand = new Random();
      return cards.get(rand.nextInt(cards.size()));
    }
  };
  @NotNull
  Card chooseCard(@NotNull Player me, @NotNull Player other, @NotNull Suit briscola);
}
