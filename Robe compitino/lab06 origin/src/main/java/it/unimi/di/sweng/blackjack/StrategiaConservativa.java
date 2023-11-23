package it.unimi.di.sweng.blackjack;

import org.jetbrains.annotations.NotNull;

public class StrategiaConservativa implements Strategia {
    private final Strategia next;
    private final GiocatoreBJ player;

    public StrategiaConservativa(@NotNull Strategia strategia, @NotNull GiocatoreBJ giocatore) {
        next = strategia;
        player = giocatore;
    }

    @Override
    public boolean chiediCarta() {
        if (player.getPunti() < 12) return true;
        return next.chiediCarta();
    }
}
