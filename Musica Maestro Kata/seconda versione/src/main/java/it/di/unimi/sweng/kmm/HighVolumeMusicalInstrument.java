package it.di.unimi.sweng.kmm;

public class HighVolumeMusicalInstrument extends DecoratorMusicalInstrument implements MusicalInstrument{
    private MusicalInstrument instrument;
    public HighVolumeMusicalInstrument(MusicalInstrument instrument) {
        super(instrument);
    }
    @Override
    String specificDecoration(String suono) {
        return suono.toUpperCase();
    }
}
