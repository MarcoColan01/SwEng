package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.Iterator;

import static it.unimi.di.sweng.blackjack.BlackJack.cardValue;

public class AskWith12Strategy implements Strategia {
    private final GiocatoreBJ sfidante;
    private final Mazziere banco;
    private final Strategia next;
    public AskWith12Strategy(Strategia next, GiocatoreBJ sfidante, Mazziere banco) {
        this.next = next;
        this.banco = banco;
        this.sfidante = sfidante;
    }


    @Override
    public boolean chiediCarta() {
        Iterator<Card> it = banco.getCards();
        while(it.hasNext()){
            Card card = it.next();
            if((cardValue(card) == 2 || cardValue(card) == 3) && sfidante.getPunti() == 12) return true;
        }
        return next.chiediCarta();
    }
}
