package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;

public class choiseCardOnEnemyTop implements SelettoreCarta {
    private SelettoreCarta next;
    private Giocatore meStesso;
    public choiseCardOnEnemyTop(SelettoreCarta next, Giocatore player){
        this.meStesso = player;
        this.next = next;
    }

    @Override
    public Card choiseStrategy(Partita partita, List<Card> mano) {
        for (Card card: mano ) {
            System.out.println("1");
            for (Giocatore giocatore : partita) {
                System.out.println("2");
                if (giocatore.getMazzettoTop().equals(card.getRank())) {
                    System.out.println("Caaa");
                    return card;
                }
            }
        }
        return next.choiseStrategy(partita, mano);
    }
}
