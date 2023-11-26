import org.jetbrains.annotations.NotNull;

public class GermanPercussionMusicalInstrument implements MusicalInstrument {
    private final GermanPercussion germanPercussion;
    public GermanPercussionMusicalInstrument(GermanPercussion germanPercussion){
        assert germanPercussion != null;
        this.germanPercussion = germanPercussion;
    }
    @Override
    public @NotNull String play() {
        return germanPercussion.spiel();
    }
}
