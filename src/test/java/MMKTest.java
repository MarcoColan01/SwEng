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
}
