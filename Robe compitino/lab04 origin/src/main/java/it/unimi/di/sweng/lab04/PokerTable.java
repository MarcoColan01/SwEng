package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PokerTable implements Iterable<PokerHand> {
    private final Deck deck = new Deck();

    private final PokerHand[] players;

    public PokerTable(int n) {
        players = new PokerHand[n];
        for(int giocatore = 0; giocatore < n; giocatore++) {
            players[giocatore] = new PokerHand(getCards());
        }
    }

    @NotNull
    private List<Card> getCards() {
        List<Card> carte = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            carte.add(deck.draw());
        }
        return carte;
    }

    @NotNull
    @Override
    public Iterator<PokerHand> iterator() {
        return Arrays.asList(players).iterator();
    }
    public String toString(int i){
        return players[i].toString();
    }


    public PokerHand getHand(int i) {
        return players[i];
    }

    private Card getOneCard(int giocatore){
        return players[giocatore].iterator().next();
    }

    public void change(int giocatore, List<Card> cards) {
        Card card = getOneCard(giocatore);
        List<Card> newHand = new ArrayList<>(cards);
        newHand.add(card);
        this.players[giocatore] = new PokerHand(newHand);
    }

    public void setHand(int player, List<Card> listaCarte) {
        players[player] = new PokerHand(listaCarte);
    }

    public Iterator<Integer> getRanking() {
        EnumMap<HandRank, Integer> mappa = new EnumMap<>(HandRank.class);
        for(int i = 0; i < players.length; i++)
            mappa.put(players[i].getRank(), i);
        List<PokerHand> copia = new ArrayList<>(Arrays.asList(players));
        copia.sort(Comparator.reverseOrder());
        return new Iterator<Integer>() {
            int corr = 0;
            @Override
            public boolean hasNext() {
                if(corr < copia.size()) return true;
                return false;
            }
            @Override
            public Integer next() {
                if(!hasNext()) throw new NoSuchElementException();
                int prec = corr;
                corr++;
                return mappa.get(copia.get(prec).getRank());
            }
        };
    }
}
