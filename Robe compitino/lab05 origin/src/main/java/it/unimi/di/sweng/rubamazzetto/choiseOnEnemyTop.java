package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class choiseOnEnemyTop implements SelettoreCarta{
    private final SelettoreCarta next;
    public choiseOnEnemyTop(SelettoreCarta next){
        this.next = next;
    }

    @Override
    public Card choiseStrategy(Partita partita, List<Card> mano) {
        for(Card card: mano){
           for(Giocatore giocatore: partita){
               if(giocatore.getMazzettoTop().equals(card.getRank())){
                    return card;
                }
            }
        }
        return next.choiseStrategy(partita, mano);
    }
}
