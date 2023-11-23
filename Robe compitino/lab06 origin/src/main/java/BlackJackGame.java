import it.unimi.di.sweng.blackjack.*;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    public static void main(String[] args) {
        Mazziere banco = new Mazziere();

        Sfidante carlo = new Sfidante("Carlo", banco);
        Sfidante mattia = new Sfidante("Mattia", banco);
        Sfidante vanessa = new Sfidante("Vanessa", banco);

        List<Sfidante> sfidanti = new ArrayList<>();

        carlo.setStrategia(new StrategiaConservativa(
                                new StrategiaPescaCon18(
                                    Strategia.NULLSTRATEGY, carlo, banco), carlo));

        mattia.setStrategia(new RandomStrategy(
                                new StrategiaPescaCon18(
                                    Strategia.NULLSTRATEGY, mattia, banco)));
        vanessa.setStrategia(new StrategiaPescaCon18(
                                new RandomStrategy(
                                    Strategia.NULLSTRATEGY), vanessa, banco));
        sfidanti.add(carlo);
        sfidanti.add(mattia);
        sfidanti.add(vanessa);

        for(Sfidante sfidante: sfidanti){
            sfidante.carteIniziali();
        }
        banco.carteIniziali();

        for(Sfidante sfidante: sfidanti){
            sfidante.gioca();
        }
        banco.gioca();

        System.out.println(banco.asString());
        for (Sfidante sfidante : sfidanti) {
            System.out.println(sfidante.asString());
            if (sfidante.isSballato() || (sfidante.getPunti() < banco.getPunti() && !banco.isSballato()))
                System.out.println("Vince il banco.");
            else if (banco.isSballato() || sfidante.getPunti() > banco.getPunti())
                System.out.println("Il banco perde!!!");
            else
                System.out.println("Pareggio.");
        }
    }

}
