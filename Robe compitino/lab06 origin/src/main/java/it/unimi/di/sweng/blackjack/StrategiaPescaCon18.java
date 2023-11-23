package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import static it.unimi.di.sweng.blackjack.BlackJack.cardValue;

public class StrategiaPescaCon18 implements Strategia {
    private final Strategia next;
    private final GiocatoreBJ player;
    private final Mazziere mazziere;
    public StrategiaPescaCon18(@NotNull Strategia strategia, @NotNull GiocatoreBJ giocatore, @NotNull Mazziere mazziere) {
        this.next = strategia;
        this.player = giocatore;
        this.mazziere = mazziere;
    }

    @Override
    public boolean chiediCarta() {
        Card card = mazziere.getCards().next();
        if (player.getPunti() <= 18 && (cardValue(card) >= 9 || cardValue(card) == 1))
            return true;
        return next.chiediCarta();
    }
}
