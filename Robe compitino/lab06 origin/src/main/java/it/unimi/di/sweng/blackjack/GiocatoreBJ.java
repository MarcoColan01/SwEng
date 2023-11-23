package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

import static it.unimi.di.sweng.blackjack.BlackJack.cardValue;

public interface GiocatoreBJ {
  void carteIniziali();

  void gioca();

  Iterator<Card> getCards();

  @NotNull String getName();

  default int getPunti() {
    int points = 0;
    boolean ace = false;
    Iterator<Card> it = getCards();
    while(it.hasNext()){
      Card card = it.next();
      points += cardValue(card);
      if(card.getRank().equals(Rank.ACE)) ace = true;
    }
    if(ace && points+10 <= 21) points+=10;
    return points;
  }

  default boolean isSballato() {
    return getPunti() > 21;
  }

  default @NotNull String asString() {
    final StringBuilder sb = new StringBuilder(getName());
    sb.append(": [")
        .append(getPunti())
        .append("] ");
    for (Card card : (Iterable<Card>) this::getCards)
      sb.append(card).append(" ");
    if (isSballato())
      sb.append("SBALLATO");
    return sb.toString();
  }
}
