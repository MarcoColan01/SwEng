package it.di.unimi.sweng.kmm;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KMMTest {
    @Test
    void trumpetTest() {
        MusicalInstrument trumpet = new Trumpet();
        assertThat(trumpet.play()).isEqualTo("pepepe");
    }

    @Test
    void hornTest() {
        MusicalInstrument horn = new Horn();
        assertThat(horn.play()).isEqualTo("papapa");
    }

    @Test
    void waterglassTest() {
        WaterGlass wg = new WaterGlass();
        assertThat(wg.tap()).isEqualTo("diding");
    }

    @Test
    void ironrodTest() {
        GermanPercussion ironrod = new IronRod();
        assertThat(ironrod.spiel()).isEqualTo("tatang");
    }

    @Test
    void waterglassAsInstrumentTest() {
        MusicalInstrument waterglass = new WaterGlassMusicalInstrument();
        assertThat(waterglass.play()).isEqualTo("diding");
    }

    @Test
    void germanPercussionAsInstrumentTest() {
        MusicalInstrument gp = new GermanPercussionMusicalInstrument(new IronRod());
        assertThat(gp.play()).isEqualTo("tatang");
    }

    @Test
    void orchestraTest() {
        Orchestra SUT = new Orchestra();
        SUT.add(new GermanPercussionMusicalInstrument(new IronRod()));
        SUT.add(new WaterGlassMusicalInstrument());
        SUT.add(new Trumpet());
        assertThat(SUT.play()).isEqualTo("tatang\ndiding\npepepe");
    }

    @Test
    void orchestraVuotaTest() {
        Orchestra SUT = new Orchestra();
        assertThat(SUT.play()).isEqualTo("");
    }

    @Test
    void ironRodSuonoForteTest() {
        MusicalInstrument instrument = mock(MusicalInstrument.class);
        when(instrument.play()).thenReturn("tatang");
        MusicalInstrument SUT = new HighVolumeMusicalInstrument(instrument);
        assertThat(SUT.play()).isEqualTo("TATANG");
    }

    @Test
    void orchestraSuonoForteTest() {
        MusicalInstrument instrument1 = mock(HighVolumeMusicalInstrument.class);
        MusicalInstrument instrument2 = mock(HighVolumeMusicalInstrument.class);
        MusicalInstrument instrument3 = mock(HighVolumeMusicalInstrument.class);
        when(instrument1.play()).thenReturn("TATANG");
        when(instrument2.play()).thenReturn("PEPEPE");
        when(instrument3.play()).thenReturn("DIDING");
        Orchestra SUT = new Orchestra();
        SUT.add(instrument1);
        SUT.add(instrument2);
        SUT.add(instrument3);
        assertThat(SUT.play()).isEqualTo("TATANG\nPEPEPE\nDIDING");
    }
    @Test
    void strumentoSuonoLentoTest() {
        MusicalInstrument instrument = mock(MusicalInstrument.class);
        when(instrument.play()).thenReturn("tatang");
        MusicalInstrument SUT = new SlowTempoMusicalInstrument(instrument);
        assertThat(SUT.play()).isEqualTo("taataang");
    }

    @Test
    void abstractFactoryTest(){
        InstrumentFactory SUT = new SlowTempoInstrumentFactory();
        MusicalInstrument instrument1 = SUT.createTrumpet();
        assertThat(instrument1.play()).isEqualTo("peepeepee");
    }
    @Test
    void slowTempoOrchestraTest(){
        Orchestra SUT = new Orchestra();
        InstrumentFactory factory = new SlowTempoInstrumentFactory();
        SUT.add(factory.createTrumpet());
        SUT.add(factory.createIronRod());
        SUT.add(factory.createWaterGlass());
        assertThat(SUT.play()).isEqualTo("peepeepee\ntaataang\ndiidiing");
    }
}
