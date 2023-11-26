package it.di.unimi.sweng.kmm;

public abstract class DecoratorMusicalInstrument implements MusicalInstrument {
    private final MusicalInstrument instrument;

    protected DecoratorMusicalInstrument(MusicalInstrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public String play() {
        String ret = instrument.play();
        ret = specificDecoration(ret);
        return ret;
    }

    String specificDecoration(String ret) {
        return ret;
    }
}
