package pl.majkel.casino.simulator.test;

import java.util.Arrays;
import pl.majkel.casino.simulator.RouletteSimulator;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;
import pl.majkel.casino.simulator.roulette.system.LabouchereSystem;
import pl.majkel.casino.simulator.roulette.system.RouletteSystem;
import pl.majkel.casino.simulator.roulette.system.RoyalLinearBettingSystem;
import pl.majkel.casino.simulator.roulette.system.RoyalSlowFibonacciSystem;
import pl.majkel.casino.simulator.roulette.system.RoyalSystem;
import pl.majkel.casino.simulator.roulette.system.RoyalVerySlowFibonacciSystem;

public class CasinoTest {

	public static void main(String[] args) throws BankrutException, NotSupportedYetException {
		//LabouchereDefenceSystem system = new LabouchereDefenceSystem(400, Arrays.asList(1,1));
		LabouchereSystem system = new LabouchereSystem(1000, Arrays.asList(1));
		//RouletteSystem system = new System63Defence(300, 4);
		//RouletteSystem system = new FibonacciProgressionSystem(1000);
		//RouletteSystem system = new System63Defence(300, 4);
		//RouletteSystem system = new RoyalSystem(1000);
		//RouletteSystem system = new System63(1000);

		//simulator.simulateMontlyGame(100000, 12);
		//simulator.simulateContinouslyGameDependsOnStats(1000000, 6);
		//simulator.simulateMontlyGame(4, 12);
//		if(system.isSystemCompleted()){
//			System.out.println("Zwyci�sttwo dla hordy!!! ostatnia stawka: " + system.getBetValue());
//		}
		//simulator.printStats();
		//RouletteSystem system = new RoyalLinearBettingSystem(1000);
		RouletteSimulator simulator = new RouletteSimulator(system);
		simulator.simulateContinouslyGame(10000);
		System.out.println("Stan konta " + system.getName() + ": " + system.getBank());
		System.out.println("Najwyzszy stan konta: " + simulator.getLargestBankValue());
		System.out.println("Najnizszy stan konta: " + simulator.getLessBankValue());
		System.out.println("Cash back: " + system.getCashBackProfit());
		System.exit(0);
	}

	

}
