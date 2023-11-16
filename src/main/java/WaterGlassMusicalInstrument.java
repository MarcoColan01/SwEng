import org.jetbrains.annotations.NotNull;

public class WaterGlassMusicalInstrument extends WaterGlass implements MusicalInstrument{
    @Override
    public @NotNull String play() {
        return this.tap();
    }

    @Override
    public String playLouder() {
        return null;
    }
}
