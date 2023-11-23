package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.CardStack;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Partita implements Iterable<Giocatore> {
  private final Deck mazzo = new Deck();
  private final List<Giocatore> giocatori = new ArrayList<>();
  private final Tavolo tavolo = new Tavolo();

  public Partita() {
    for (int i = 0; i < 4; i++) {
      tavolo.metti(mazzo.draw());
    }
  }

  public Partita(List<Card> carte) {
    for (Card carta : carte)
      tavolo.metti(carta);
  }

  public void addGiocatore(Giocatore giocatore) {
    giocatori.add(giocatore);
  }

  private void distribuisciCarta() {
    assert deckSize() >= giocatori.size();

    for (Giocatore giocatore : giocatori) {
      giocatore.daiCarta(mazzo.draw());
    }
  }

  public void distribuisciMano(int num) {
    // PRE CONDIZIONI
    assert num <= 3;

    for (int i = 0; i < num; i++) {
      distribuisciCarta();
    }

    // POST CONDIZIONI
    for (Giocatore giocatore : giocatori) {
      assert giocatore.numCards() <= 3 : "non si possono avere più di tre carte in mano";
      assert giocatori.get(0).numCards() == giocatore.numCards() : "non è stato dato stesso numero di carte a tutti";
      assert giocatore.numCards() == 3 || deckSize() < giocatori.size() : "si possono avere meno di tre carte solo se nel mazzo non ce ne sono abbastanza per fare un altro giro";
    }
  }


  public boolean isFinita() {
    assert giocatori.size() > 1;
    int cartegiocate = tavolo.size();
    for (Giocatore giocatore : giocatori) {
      cartegiocate += giocatore.getPunti();
    }

    return deckSize() < giocatori.size() && 52 - cartegiocate == deckSize();
  }

  private int deckSize() {
    int s = 0;
    CardStack tmp = new CardStack();
    while (!mazzo.isEmpty()) {
      tmp.push(mazzo.draw());
      s += 1;
    }
    while (!tmp.isEmpty()) {
      mazzo.push(tmp.pop());
    }
    return s;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Giocatore giocatore : giocatori) {
      s.append(giocatore.toString());
      s.append("\n");
    }
    s.append("Tavolo: ");
    s.append(tavolo);
    s.append("\n");
    s.append("Finita: ");
    s.append(isFinita());
    return s.toString();
  }


  public boolean controllaSeCartaPresenteSuTavolo(Card card) {
    return tavolo.inMostra(card);
  }

  @Override
  @NotNull
  public Iterator<Giocatore> iterator() {
    List<Giocatore> it = new ArrayList<>();
    for (Giocatore giocatore : giocatori) {
      it.add(new Giocatore(giocatore));
    }
    return it.iterator();
  }


  // TODO implementare metodo giocaCarta che esegue la giocata
  // secondo le regole e restituisce i punti ottenuti
  public int giocaCarta(Giocatore giocatore, Card card) {
    if (tavolo.inMostra(card)) return 2;
    for (Giocatore g : giocatori) {
      if (g.getMazzettoTop().equals(card.getRank())){
        int points = g.getPunti();
        g.setPunti(0);
        g.carteRubate();
        return points;
      }
    }
    return 0;
  }
}

