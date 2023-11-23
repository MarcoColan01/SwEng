package it.di.unimi.sweng.kmm;

public class GermanPercussionMusicalInstrument implements MusicalInstrument {
    private final GermanPercussion gp;
    public GermanPercussionMusicalInstrument(GermanPercussion gp){
        assert gp != null;
        this.gp = gp;
    }
    @Override
    public String play() {
        return gp.spiel();
    }
}
