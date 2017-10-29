package pl.majkel.casino.simulator.roulette.system;

import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;

public abstract class RouletteSystem {
	
	protected int bank;
	protected int betValue = 1;
	protected int betLevel = 1;
	protected int resignationPoint = 1;
	protected int cashBackProfit = 0;
	protected int startingBank;
	
	public RouletteSystem(int bank) {
		this.bank = bank;
		this.startingBank = bank;
	}

	public abstract void nextBet(SpinResult randNumber) throws BankrutException;
	public abstract void nextBet(SpinResult randNumber, RouletteStats stats) throws BankrutException, NotSupportedYetException;
	public abstract String getName();
	

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public int getBetValue() {
		return betValue;
	}

	public int getCashBackProfit() {
		return cashBackProfit;
	}
	
	void cashBack(){
		if(bank >= startingBank + 50){
			cashBackProfit += bank - startingBank;
			bank = startingBank;
			betValue = 1;
		}
	}
	
	void cashBackIncreasingBank(){
		if(bank >= startingBank + 100){
			cashBackProfit += (bank - startingBank)/2;
			startingBank += (bank - startingBank)/2; 
			bank = startingBank;
			betValue = 1;
		}
	}
	

}
