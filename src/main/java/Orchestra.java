import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Orchestra implements MusicalInstrument{
    private final List<MusicalInstrument> strumenti = new ArrayList<>();
    @Override
    public @NotNull String play() {
        return null;
    }

    public void add(MusicalInstrument strumento) {

    }
}
