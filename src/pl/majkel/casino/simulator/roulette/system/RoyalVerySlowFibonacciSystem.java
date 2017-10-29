package pl.majkel.casino.simulator.roulette.system;

import pl.majkel.casino.simulator.roulette.Dozen;
import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;
import pl.majkel.casino.simulator.roulette.utils.SlowFibonacci;
import pl.majkel.casino.simulator.roulette.utils.VerySlowFibonacci;

public class RoyalVerySlowFibonacciSystem extends RouletteSystem{

	public RoyalVerySlowFibonacciSystem(int bank) {
		super(bank);
	}

	public void nextBet(SpinResult spinResult) throws BankrutException {
		//cashBackIncreasingBank();
		if (bank - 2 * betValue > 0) {
			bank -= 2 * betValue;
			if (spinResult.getNumber() > 0 && spinResult.getNumber() <= 24) {
				bank += betValue * 3;
				if(startingBank < bank){
					startingBank = bank;
					betValue = 1;
					betLevel = 1;
				} else if(startingBank - bank < betValue){
					betLevel = VerySlowFibonacci.calculateBetLevelForValue(startingBank - bank);
					betValue= VerySlowFibonacci.calculate(betLevel);
				}
			} else {
				betValue= VerySlowFibonacci.calculate(betLevel);
				betLevel++;
			}
		} else {
			throw new BankrutException("Przegrales! Stan konta: " + bank + "stawka: " + betValue);
		}
	}
	
	public String getName(){
		return "System królewski z rozwleczonym Fibonaccim";
	}

	@Override
	public void nextBet(SpinResult spinResult, RouletteStats stats) throws BankrutException, NotSupportedYetException {
		if (bank - 2 * betValue > 0) {
			bank -= 2 * betValue;
			if (spinResult.getDozen() != Dozen.ZERO && spinResult.getDozen() != stats.getLastNotZeroDozen()) {
				bank += betValue * 3;
				if(startingBank < bank){
					startingBank = bank;
					betValue = 1;
					betLevel = 1;
				} else if(startingBank - bank < betValue){
					betLevel = VerySlowFibonacci.calculateBetLevelForValue(startingBank - bank);
					betValue= VerySlowFibonacci.calculate(betLevel);
				}

			} else {
				betValue= VerySlowFibonacci.calculate(betLevel);
				betLevel++;
			}
		} else {
			throw new BankrutException("Przegrales! Stan konta: " + bank + "stawka: " + betValue);
		}
	}
}
