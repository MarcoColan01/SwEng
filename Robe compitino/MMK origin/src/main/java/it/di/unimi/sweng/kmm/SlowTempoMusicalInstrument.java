package it.di.unimi.sweng.kmm;

public class SlowTempoMusicalInstrument extends DecoratorMusicalInstrument implements MusicalInstrument {
    public SlowTempoMusicalInstrument(MusicalInstrument instrument) {
        super(instrument);
    }
    @Override
    String specificDecoration(String suono){
        StringBuilder sb = new StringBuilder();
        String app = "AEIOUaeiou";
        for (char ch : suono.toCharArray()) {
            sb.append(ch);
            if (app.contains(String.valueOf(ch))) sb.append(ch);
        }
        return sb.toString();
    }

}
