package it.di.unimi.sweng.kmm;

import java.util.ArrayList;
import java.util.List;

public class Orchestra implements MusicalInstrument{
    private final List<MusicalInstrument> instruments = new ArrayList<>();
    public void add(MusicalInstrument instrument) {
        this.instruments.add(instrument);
    }

    public String play() {
        StringBuilder sb = new StringBuilder();
        if(instruments.isEmpty()) return sb.toString();
        for(MusicalInstrument instrument: instruments){
            sb.append(instrument.play()).append("\n");
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }
}
