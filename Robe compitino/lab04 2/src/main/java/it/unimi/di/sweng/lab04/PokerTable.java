package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PokerTable implements Iterable<PokerHand> {
    private final List<PokerHand> players;
    private final Deck deck = new Deck();

    public PokerTable(int n){
        this.players = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            List<Card> cards = new ArrayList<Card>();
            for(int j = 0; j < 5; j++){
                cards.add(deck.draw());
            }
            players.add(new PokerHand(cards));
        }
    }

    @NotNull
    @Override
    public Iterator<PokerHand> iterator() {
        return new ArrayList<>(players).iterator();
    }

    public PokerHand getHand(int playerPos) {
        if(playerPos >= players.size()) throw new IllegalArgumentException();
        List<Card> cards = new ArrayList<>();
        players.get(playerPos).iterator().forEachRemaining(cards::add);
        return new PokerHand(cards);
    }

    public void change(int i, PokerHand ph) {
        List<Card> newCards = new ArrayList<>();
        ph.iterator().forEachRemaining(newCards::add);
        players.set(i, new PokerHand(newCards));
    }

    public Iterator<Integer> getRanking(){
        EnumMap<HandRank, Integer> mappa = new EnumMap<>(HandRank.class);
        int i = 0;
        for(PokerHand mano: players)
            mappa.put(mano.getRank(), i++);
        List<PokerHand> copia = new ArrayList<>();
        players.iterator().forEachRemaining(copia::add);
        copia.sort(Comparator.reverseOrder());
        return new Iterator<Integer>() {
            int corr = 0;
            @Override
            public boolean hasNext() {
                return corr < copia.size();
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
