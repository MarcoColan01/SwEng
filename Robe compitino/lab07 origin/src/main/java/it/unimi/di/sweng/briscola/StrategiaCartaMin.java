package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class StrategiaCartaMin implements Strategy {
    private final Strategy next;
    public StrategiaCartaMin(Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Player me, @NotNull Player other, @NotNull Suit briscola) {
        Iterator<Card> it = me.iterator();
        Card cardToPlay = it.next();
        Card app = cardToPlay;
        while (it.hasNext()) {
            Card card = it.next();
            if (!card.getSuit().equals(briscola) && (card.getRank().points() <= cardToPlay.getRank().points() || cardToPlay == app))
                cardToPlay = card;
        }
        if (cardToPlay.getSuit().equals(app.getSuit()) && cardToPlay.getRank().equals(app.getRank()))
            return next.chooseCard(me, other, briscola);
        return cardToPlay;
    }
}
