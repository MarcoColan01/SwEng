import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Orchestra implements MusicalInstrument{
    private final List<MusicalInstrument> strumenti = new ArrayList<>();
    @Override
    public @NotNull String play() {
        StringBuilder sb = new StringBuilder();
        for(MusicalInstrument strumento: strumenti){
            sb.append(strumento.play()).append("\n");

        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    @Override
    public String playLouder() {
        return null;
    }

    public void add(MusicalInstrument strumento) {
        strumenti.add(strumento);
    }
}
