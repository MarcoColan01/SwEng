import org.jetbrains.annotations.NotNull;

public class HighVolumeMusicalInstrument implements MusicalInstrument{
    private final MusicalInstrument strumento;
    public HighVolumeMusicalInstrument(MusicalInstrument strumento){
        this.strumento = strumento;
    }
    @Override
    public @NotNull String play(){
        return strumento.play().toUpperCase();
    }
}
