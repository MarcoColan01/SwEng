package it.di.unimi.sweng.kmm;

public class WaterGlassMusicalInstrument extends WaterGlass implements MusicalInstrument {
    @Override
    public String play() {
        return this.tap();
    }
}
