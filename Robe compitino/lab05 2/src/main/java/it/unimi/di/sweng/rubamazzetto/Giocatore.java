package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Giocatore {

  private final String nome;
  private final List<Card> mano;
  private Rank mazzettoTop;
  private final Partita partita;

  private int punti;

  public Giocatore(String nome, Partita partita) {
    this.nome = nome;
    partita.addGiocatore(this);
    this.partita = partita;
    this.mano = new ArrayList<>();
  }

  public Giocatore(Giocatore g) {
    this.partita = g.partita;
    this.mano = g.mano;
    this.punti = g.punti;
    this.nome = g.nome;
    this.mazzettoTop = mano.get(0).getRank();

  }

  public Giocatore(String nome, Partita partita, List<Card> carte, int i) {
    this.nome = nome;
    this.mano = carte;
    this.punti = i;
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
    // TODO. scegli carta in base alla catena di strategie del giocatore

    // chiede a partita di giocare secondo le regole la carta seclta con una chiamata tipo:
    // partita.giocaCarta(this, card);
    // questa funzione gli restituisce l'eventuale numero di punti ottenuti
    SelettoreCarta chain = new choiseCardOnTable(new choiseCardOnEnemyTop(new choiseCardEqualMyTop(SelettoreCarta.Random, this),this), this);
    Card cardToPlay = chain.choiseStrategy(partita, mano);
    punti += partita.giocaCarta(this, cardToPlay);
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

    public String getNome() {
    return nome;
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

  public void mazzoRubato() {
    mano.clear();
  }

  public void setPunti(int i) {
    punti = i;
  }
}
