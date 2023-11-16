import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class MMKTest {

    @Test
    void strumentiBaseTest(){
        MusicalInstrument trumpet = new Trumpet();
        MusicalInstrument horn = new Horn();
        assertThat(trumpet.play()).isEqualTo("pepepe");
        assertThat(horn.play()).isEqualTo("papapa");
    }

    @Test
    void altriStrumentiTest(){
        GermanPercussion ironRod = new IronRod();
        WaterGlass wg = new WaterGlass();
        assertThat(ironRod.spiel()).isEqualTo("tatang");
        assertThat(wg.tap()).isEqualTo("diding");
    }
}
