package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Giocatore {

  private final String nome;
  private List<Card> mano;
  private Rank mazzettoTop;
  private final Partita partita;

  private int punti;

  public Giocatore(String nome, Partita partita) {
    this.nome = nome;
    this.mano = new ArrayList<>();
    partita.addGiocatore(this);
    this.partita = partita;
    this.mazzettoTop = mano.get(0).getRank();
  }

  public Giocatore(Giocatore giocatore){
    this.nome = giocatore.nome;
    this.mano = giocatore.mano;
    this.partita = giocatore.partita;
    this.mazzettoTop = mano.get(0).getRank();

  }

  public Giocatore(String nome, Partita partita, List<Card> cards, int punti){
    this.nome = nome;
    this.mano = cards;
    this.punti = punti;
    this.mazzettoTop = mano.get(0).getRank();
    partita.addGiocatore(this);
    this.partita = partita;

  }
  public Rank getMazzettoTop() {
    return mazzettoTop;
  }

  public int getPunti() {
    return punti;
  }

  public void daiCarta(Card carta) {
    mano.add(carta);
  }


  public void turno() {
    SelettoreCarta chain = new choiseOnTable(new choiseOnEnemyTop(SelettoreCarta.Random));
    Card cardToPlay = chain.choiseStrategy(partita, mano);
    punti += partita.giocaCarta(this, cardToPlay);
  }

  public List<Card> getCards(){
    return mano;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder(nome);
    s.append(": ");
    s.append("[").append(mano.size()).append("]");
    if (punti > 0) {
      s.append("mazzetto con ");
      s.append(punti);
      s.append(" carte, cima ");
      s.append(mazzettoTop);
      s.append("; ");
    }
    for (Card card : mano) {
      s.append(card.toString());
      s.append(", ");
    }
    return s.toString();
  }

  public int numCards() {
    return mano.size();
  }

  @Override
  public boolean equals(Object o){
    if(this == o) return true;
    if(!(o instanceof Giocatore)) return false;
    Giocatore g = (Giocatore) o;
    return (nome == g.nome && mano == g.mano && mazzettoTop == g.mazzettoTop && punti == g.punti);
  }

  @Override
  public int hashCode(){
    return Objects.hash(mano, nome, mazzettoTop, punti);
  }

  public void setPunti(int i) {
    this.punti = i;
  }

  public void carteRubate() {
    mano.clear();
  }
}

