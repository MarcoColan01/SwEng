package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;

public class choiseCardOnTable implements SelettoreCarta{
    private SelettoreCarta next;
    private Giocatore meStesso;
    public choiseCardOnTable(SelettoreCarta next, Giocatore player){
        this.meStesso = player;
        this.next = next;
    }
    @Override
    public Card choiseStrategy(Partita partita, List<Card> mano) {
        for(Card card: mano){
            if(partita.controllaSeCartaPresenteSuTavolo(card)) return card;
        }

        return next.choiseStrategy(partita, mano);
    }
}
