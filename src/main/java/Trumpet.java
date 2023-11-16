import org.jetbrains.annotations.NotNull;

public class Trumpet implements MusicalInstrument {
    @Override
    public @NotNull String play() {
        return "pepepe";
    }

    @Override
    public String playLouder() {
        return null;
    }
}
