package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.Iterator;

import static it.unimi.di.sweng.blackjack.BlackJack.cardValue;

public class StayWith16Strategy implements Strategia {
    private final GiocatoreBJ giocatore;
    private final Strategia next;
    private final Mazziere mazziere;

    public StayWith16Strategy(Strategia next, GiocatoreBJ giocatore, Mazziere mazziere) {
        this.giocatore = giocatore;
        this.next = next;
        this.mazziere = mazziere;
    }

    @Override
    public boolean chiediCarta() {
        if(giocatore.getNumCards() > 2){
            Iterator<Card> it = giocatore.getCards();
            int cont = 0;
            while(it.hasNext()){
                cont += cardValue(it.next());
            }
            if(cont == 16){
                return true;
            }
        }
        return next.chiediCarta();
    }
}
