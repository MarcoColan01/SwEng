package it.unimi.di.sweng.lab02;
public class BowlingGame implements Bowling {

    private int score = 0;
    private final int[] lanci = new int[21];
    private int lancioCurr = 0;
    private int frame = 0;
    private boolean endGame = false;
    @Override
    public void roll(int pins) {
        lanci[lancioCurr] = pins;
        lancioCurr++;
        if(pins == 10 && lancioCurr < 18) lancioCurr++;
    }

    @Override
    public int score() {
        for(int i = 0; i < lanci.length; i++){
            if(isSpare(i, frame)) score += lanci[i+1];
            if(isStrike(i)){
               strikeScore(i);
            }
            if(endGame){
                break;
            }
            score += lanci[i];
            frame++;
            if(frame == 2) frame = 0;
        }
        return score;
    }

    private void strikeScore(int i) {
        if (i == 17) {
            score += lanci[i] + lanci[i + 2] + lanci[i + 3];
            endGame = true;
        }else if (i == 18){
            score += lanci[i] + lanci[i + 1] + lanci[i + 2];
            endGame = true;
        }else if (lanci[i+2] != 10){
            score += lanci[i+2] + lanci[i+3];
            frame = 0;
        }else{
            score += 20;
        }
    }

    private boolean isStrike(int i) {
        return lanci[i] == 10;
    }

    private boolean isSpare(int i, int frame) {
       return i > 0 && lanci[i] + lanci[i-1] == 10 && frame == 1 && lanci[i-1] != 10;
    }
}
