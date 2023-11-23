package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiMazzo  implements  DeckInterface{
  private final List<Deck> multimazzo = new ArrayList<>();

  public MultiMazzo(int numMazzi) {
    for(int i = 0; i < numMazzi; i++){
      Deck deck = new Deck();
      deck.shuffle();
      multimazzo.add(deck);
    }
  }

  @Override
  public @NotNull Card draw() {
    if(isEmpty()) throw new IllegalCallerException("Multimazzo vuoto!");
    Random rand = new Random();
    int deckToDraw = rand.nextInt(multimazzo.size());
    Card card = multimazzo.get(deckToDraw).draw();
    if(multimazzo.get(deckToDraw).isEmpty()) multimazzo.remove(deckToDraw);
    return card;
  }

  @Override
  public boolean isEmpty() {
    return multimazzo.isEmpty();
  }
}
