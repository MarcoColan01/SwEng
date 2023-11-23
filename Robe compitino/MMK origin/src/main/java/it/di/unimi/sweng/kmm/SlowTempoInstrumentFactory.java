package it.di.unimi.sweng.kmm;

public class SlowTempoInstrumentFactory implements InstrumentFactory {
    @Override
    public MusicalInstrument createTrumpet() {
        return new SlowTempoMusicalInstrument(new Trumpet());
    }

    @Override
    public MusicalInstrument createIronRod() {
        return new SlowTempoMusicalInstrument(new GermanPercussionMusicalInstrument(new IronRod()));
    }

    @Override
    public MusicalInstrument createWaterGlass() {
        return new SlowTempoMusicalInstrument(new WaterGlassMusicalInstrument());
    }
}
