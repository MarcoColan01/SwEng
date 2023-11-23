package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;

public class choiseCardEqualMyTop implements SelettoreCarta{
    private SelettoreCarta next;
    private Giocatore meStesso;
    public choiseCardEqualMyTop(SelettoreCarta next, Giocatore player){
        this.next = next;
        this.meStesso = player;
    }

    @Override
    public Card choiseStrategy(Partita partita, List<Card> mano) {
        boolean isFirst = true;
        for(Giocatore giocatore: partita){
            for(Card card: mano){
                if(giocatore == meStesso && card.getRank().equals(giocatore.getMazzettoTop())) return card;
            }
        }
        return next.choiseStrategy(partita, mano);
    }
}
