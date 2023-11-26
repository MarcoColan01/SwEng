import org.jetbrains.annotations.NotNull;

public class HighVolumeMusicalInstrument extends DecoratorMusicalInstruments implements MusicalInstrument{
    public HighVolumeMusicalInstrument(MusicalInstrument strumento){
        super(strumento);
    }

    @Override
    String specificDecoration(String ret) {
        return ret.toUpperCase();
    }
}
