package it.unimi.di.sweng.briscola;

public class Main {
    public static void main(String[] args) {
        Player carlo = new Player("carlo");
        Player mattia = new Player("mattia");
        Deck deck = Deck.createFullDeck();
        carlo.setFirstStrategy(new StrategiaBriscolaMax(new StrategiaCartaMin(Strategy.NULL)));
        carlo.setSecondStrategy(new StrategiaBriscolaMin(new StrategiaCartaMax(Strategy.NULL)));

        mattia.setFirstStrategy(new StrategiaBriscolaMax(new StrategiaCartaMin(Strategy.NULL)));
        mattia.setSecondStrategy(new StrategiaBriscolaMin(new StrategiaCartaMax(Strategy.NULL)));
        Briscola briscola = new Briscola(carlo, mattia, deck); //dà carte iniziali e estrae briscola

        for (int i = 0; i < 20; i++) {
            briscola.playTurn(); //esegue scelta carte da giocare e attua giocata

            if (briscola.availableCards())
                briscola.giveEachPlayerOneCard();
        }

        Player winner = briscola.establishGameWinner();
        winner.shoutResult(); // il giocatore esulta per la propria vittoria, ma attenzione ai pareggi
    }
}

