import org.jetbrains.annotations.NotNull;

public class SlowTempoMusicalInstrument extends DecoratorMusicalInstruments implements MusicalInstrument {
    public SlowTempoMusicalInstrument(MusicalInstrument strumento) {
        super(strumento);
    }

    @Override
    String specificDecoration(String ret) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ret.length(); i++){
            sb.append(ret.charAt(i));
            if(isVocal(ret.charAt(i))){
                sb.append(ret.charAt(i));
            }
        }
        return sb.toString();
    }

    private boolean isVocal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
