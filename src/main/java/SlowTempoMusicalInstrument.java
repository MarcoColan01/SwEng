import org.jetbrains.annotations.NotNull;

public class SlowTempoMusicalInstrument implements MusicalInstrument {
    private final MusicalInstrument strumento;
    public SlowTempoMusicalInstrument(MusicalInstrument strumento) {
        this.strumento = strumento;
    }

    @Override
    public @NotNull String play() {
        return null;
    }

    private boolean isVocal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
