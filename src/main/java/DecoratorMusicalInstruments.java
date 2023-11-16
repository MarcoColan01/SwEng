import org.jetbrains.annotations.NotNull;

public abstract class DecoratorMusicalInstruments implements MusicalInstrument{
    private final MusicalInstrument strumento;
    protected DecoratorMusicalInstruments(MusicalInstrument strumento){
        this.strumento = strumento;
    }

    @Override
    public @NotNull String play(){
        String ret = strumento.play();
        ret = specificDecoration(ret);
        return ret;
    }

     String specificDecoration(String ret) {
        return ret;
    }
}
