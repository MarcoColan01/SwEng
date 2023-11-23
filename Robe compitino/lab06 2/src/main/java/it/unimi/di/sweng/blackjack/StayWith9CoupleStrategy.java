package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.Iterator;

import static it.unimi.di.sweng.blackjack.BlackJack.cardValue;

public class StayWith9CoupleStrategy implements Strategia {

    private final GiocatoreBJ giocatore;
    private final Strategia next;
    private final Mazziere mazziere;
    public StayWith9CoupleStrategy(Strategia next, GiocatoreBJ giocatore, Mazziere mazziere) {
        this.giocatore = giocatore;
        this.next = next;
        this.mazziere = mazziere;
    }

    @Override
    public boolean chiediCarta() {
        int contNines = 0;
        Iterator<Card> it = giocatore.getCards();
        while(it.hasNext()){
            if(cardValue(it.next()) == 9) contNines++;
        }
        if(contNines == 2){
            Iterator<Card> it2 = mazziere.getCards();
            while(it.hasNext()){
                if(cardValue(it.next()) == 7) return true;
            }
        }
        return next.chiediCarta();
    }
}
