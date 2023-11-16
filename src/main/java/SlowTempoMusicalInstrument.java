import org.jetbrains.annotations.NotNull;

public class SlowTempoMusicalInstrument implements MusicalInstrument {
    private final MusicalInstrument strumento;
    public SlowTempoMusicalInstrument(MusicalInstrument strumento) {
        this.strumento = strumento;
    }

    @Override
    public @NotNull String play() {
        String suono = strumento.play();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < suono.length(); i++){
            sb.append(suono.charAt(i));
            if(isVocal(suono.charAt(i))){
                sb.append(suono.charAt(i));
            }
        }
        return sb.toString();
    }

    private boolean isVocal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
