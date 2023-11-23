package it.unimi.di.sweng.briscola;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Answers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StrategiaTest {
    @ParameterizedTest
    @CsvSource({
            "AC RD, AC 3D, DD, RD"
    })
    void strategiaBriscolaMaxTest(String mano1, String mano2, String briscola, String res) {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        final Iterator<Card> itp1 = of(mano1);
        final Iterator<Card> itp2 = of(mano2);
        doReturn(itp1).when(p1).iterator();
        doReturn(itp2).when(p2).iterator();
        Strategy SUT = new StrategiaBriscolaMax(null);
        assertThat(SUT.chooseCard(p1, p2, parseSuit(briscola)))
                .isEqualTo(Card.get(parseRank(res), parseSuit(res)));
    }

    @ParameterizedTest
    @CsvSource({
            "AC RD, AC 3D, SS, RD"
    })
    void strategiaCartaMaxTest(String mano1, String mano2, String briscola, String res) {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        final Iterator<Card> itp1 = of(mano1);
        final Iterator<Card> itp2 = of(mano2);
        doReturn(itp1).when(p1).iterator();
        doReturn(itp2).when(p2).iterator();
        Strategy SUT = new StrategiaCartaMax(null);
        assertThat(SUT.chooseCard(p1, p2, parseSuit(briscola)))
                .isEqualTo(Card.get(parseRank(res), parseSuit(res)));
    }

    @ParameterizedTest
    @CsvSource({
            "AC RD 2D, AC 3D RS, DD, 2D"
    })
    void strategiaBriscolaMinTest(String mano1, String mano2, String briscola, String res) {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        final Iterator<Card> itp1 = of(mano1);
        final Iterator<Card> itp2 = of(mano2);
        doReturn(itp1).when(p1).iterator();
        doReturn(itp2).when(p2).iterator();
        Strategy SUT = new StrategiaBriscolaMin(null);
        assertThat(SUT.chooseCard(p1, p2, parseSuit(briscola)))
                .isEqualTo(Card.get(parseRank(res), parseSuit(res)));
    }

    @ParameterizedTest
    @CsvSource({
            "AC RD 2D, AC 3D RS, SS, 2D"
    })
    void strategiaCartaMinTest(String mano1, String mano2, String briscola, String res) {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        final Iterator<Card> itp1 = of(mano1);
        final Iterator<Card> itp2 = of(mano2);
        doReturn(itp1).when(p1).iterator();
        doReturn(itp2).when(p2).iterator();
        Strategy SUT = new StrategiaCartaMin(null);
        assertThat(SUT.chooseCard(p1, p2, parseSuit(briscola)))
                .isEqualTo(Card.get(parseRank(res), parseSuit(res)));
    }

    @ParameterizedTest
    @CsvSource({
            "AC RD 2D, AC 3D RS, SS, RD"
    })
    void strategieChainedTest1(String mano1, String mano2, String briscola, String res) {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        when(p1.iterator()).thenAnswer(invocation -> of(mano1));
        when(p2.iterator()).thenAnswer(invocation -> of(mano2));
        Strategy SUT = new StrategiaBriscolaMax(new StrategiaCartaMax(null));
        assertThat(SUT.chooseCard(p1, p2, parseSuit(briscola)))
                .isEqualTo(Card.get(parseRank(res), parseSuit(res)));
    }

    @ParameterizedTest
    @CsvSource({
            "AC RD 2D, AC 3D RS, SS, RD"
    })
    void strategieChainedTest2(String mano1, String mano2, String briscola, String res) {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        when(p1.iterator()).thenAnswer(invocation -> of(mano1));
        when(p2.iterator()).thenAnswer(invocation -> of(mano2));
        Strategy SUT = new StrategiaBriscolaMax(Strategy.NULL);
        assertThat(SUT.chooseCard(p1, p2, parseSuit(briscola))).isInstanceOf(Card.class);
    }


    private static Iterator<Card> of(String mano) {
        List<Card> cards = new ArrayList<>();
        for (String str : mano.split(" ")) {
            Suit s = parseSuit(str);
            Rank r = parseRank(str);
            cards.add(Card.get(r, s));
        }
        return cards.iterator();
    }

    private static Rank parseRank(String str) {
        return switch (str.charAt(0)) {
            case '1', 'A' -> Rank.ASSO;
            case '2' -> Rank.DUE;
            case '3' -> Rank.TRE;
            case '4' -> Rank.QUATTRO;
            case '5' -> Rank.CINQUE;
            case '6' -> Rank.SEI;
            case '7' -> Rank.SETTE;
            case 'C' -> Rank.CAVALLO;
            case 'R' -> Rank.RE;
            case 'F' -> Rank.FANTE;
            default -> throw new IllegalStateException();
        };
    }

    private static Suit parseSuit(String str) {
        return switch (str.charAt(1)) {
            case 'S' -> Suit.SPADE;
            case 'B' -> Suit.BASTONI;
            case 'C' -> Suit.COPPE;
            case 'D' -> Suit.DENARI;
            default -> throw new IllegalStateException();
        };
    }
}
