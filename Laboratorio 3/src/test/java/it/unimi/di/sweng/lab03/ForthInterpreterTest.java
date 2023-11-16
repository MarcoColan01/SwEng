package it.unimi.di.sweng.lab03;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;


@Timeout(2)
public class ForthInterpreterTest {
  private Interpreter interpreter;

  @BeforeEach
  public void setUp() {
    interpreter = new ForthInterpreter();
  }

  @Test
  public void stampaVuota(){
    interpreter.input("");
    assertThat(interpreter.toString()).isEqualTo(" <- Top");
  }

  @ParameterizedTest
  @CsvSource({"1 2, 1 2 <- Top", "1, 1 <- Top"})
  public void stampaNonVuota(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({"1   2, 1 2 <- Top", "'1\n2', 1 2 <- Top", "'1   2 \n3', 1 2 3 <- Top"})
  public void stampeConNewline(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({"1 2 +, 3 <- Top", "1 2 + 5 +, 8 <- Top"})
  public void stampaSomme(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({"1 2+, Token error '2+'", "1 2 +5 +, Token error '+5'"})
  public void stampaSommeEccezione(String in, String output){
    assertThatThrownBy(() -> interpreter.input(in))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(output);
  }

  @ParameterizedTest
  @CsvSource({"1 +, Stack Underflow"})
  public void stackUnderflow(String in, String output){
    assertThatThrownBy(() -> interpreter.input(in))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(output);
  }

  @ParameterizedTest
  @CsvSource({"1 2 *, 2 <- Top", "1 2 * 5 *, 10 <- Top"})
  public void stampaProdotti(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({"1 2 -, -1 <- Top", "1 2 /, 0 <- Top"})
  public void stampaDivisioniSottrazioni(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({"1 2 3 dup, 1 2 3 3 <- Top", "1 dup, 1 1 <- Top"})
  public void dupTest(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({"1 2 3 swap, 1 3 2 <- Top", "1 2 3 4 swap, 1 2 4 3 <- Top"})
  public void swapTest(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({"1 2 3 drop, 1 2 <- Top", "1 drop, <- Top"})
  public void dropTest(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @Test
  public void operazioniCimaTest(){
    interpreter.input("1 2 + 3 * 4 dup 5 + drop swap");
    assertThat(interpreter.toString()).isEqualTo("4 9 <- Top");
  }

  @Test
  public void operazioniCimaTestEccezione(){
    assertThatThrownBy(() -> interpreter.input("1 2 + 3 * drop swap"))
            .isInstanceOf(NoSuchElementException.class);
  }

  @ParameterizedTest
  @CsvSource({": raddoppia 2 * ; 5 raddoppia dup raddoppia, 10 20 <- Top"})
  public void nuovaWordTest(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

}
