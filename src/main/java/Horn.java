import org.jetbrains.annotations.NotNull;

public class Horn implements MusicalInstrument {
    @Override
    public @NotNull String play() {
        return "papapa";
    }

    @Override
    public String playLouder() {
        return null;
    }
}
