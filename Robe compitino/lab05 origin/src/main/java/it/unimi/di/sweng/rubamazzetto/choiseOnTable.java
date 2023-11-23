package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;

public class choiseOnTable implements SelettoreCarta{
    private final SelettoreCarta next;

    public choiseOnTable(SelettoreCarta next){this.next = next;}
    @Override
    public Card choiseStrategy(Partita partita, List<Card> mano) {
        for(Card card: mano){
            if(partita.controllaSeCartaPresenteSuTavolo(card)){
                System.out.println("cc");
                return card;
            }
        }
        return next.choiseStrategy(partita, mano);
    }
}
