package it.unimi.di.sweng.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.IterableAssert.assertThatIterable;

public class MultiMazzoTest {
    @Test
    void newMultiMazzoTest(){
        int nMazzi = 6;
        MultiMazzo SUT = new MultiMazzo(nMazzi);
        for(int i = 0; i < nMazzi*52; i++){
            SUT.draw();
        }
        assertThat(SUT.isEmpty()).isTrue();
    }

    @Test
    void drawException(){
        int nMazzi = 6;
        MultiMazzo SUT = new MultiMazzo(nMazzi);
        for(int i = 0; i < nMazzi*52; i++){
            SUT.draw();
        }
        assertThatThrownBy(() ->SUT.draw()).isInstanceOf(IllegalCallerException.class);
    }
}