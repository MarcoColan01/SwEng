import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.*;


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

    @Test
    void adapterInstrumentsTest(){
        MusicalInstrument wg = new WaterGlassMusicalInstrument();
        GermanPercussion dummy = new IronRod();
        MusicalInstrument ironRod = new GermanPercussionMusicalInstrument(dummy);
        assertThat(wg.play()).isEqualTo("diding");
        assertThat(ironRod.play()).isEqualTo("tatang");
    }

    @Test
    void orchestraTest(){
        Orchestra orchestra = new Orchestra();
        orchestra.add(new GermanPercussionMusicalInstrument(new IronRod()));
        orchestra.add(new WaterGlassMusicalInstrument());
        orchestra.add(new Trumpet());
        assertThat(orchestra.play()).isEqualTo("tatang\ndiding\npepepe");
    }

    @Test
    void suonoForteTest(){
       MusicalInstrument trumpet = new Trumpet();
       MusicalInstrument SUT = new HighVolumeMusicalInstrument(trumpet);
       assertThat(SUT.play()).isEqualTo("PEPEPE");
    }
}
