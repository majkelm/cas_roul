package pl.majkel.casino.simulator.roulette.system;

import java.util.LinkedList;
import java.util.List;
import pl.majkel.casino.simulator.roulette.Color;
import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;

public class LabouchereDefenceSystem extends RouletteSystem {

	private List<Integer> numberSequence = new LinkedList<Integer>();
	private List<Integer> startingNumberSequence = new LinkedList<Integer>();
	private boolean systemCompleted = false;

	public LabouchereDefenceSystem(int bank, List<Integer> numberSequence) {
		super(bank);
		this.numberSequence.addAll(numberSequence);
		this.startingNumberSequence.addAll(numberSequence);
	}

	public void nextBet(SpinResult spinResult) throws BankrutException {
		systemCompleted = numberSequence.isEmpty();
		cashBack();
		if (!systemCompleted) {
			if(numberSequence.size() == 1) {
				betValue = numberSequence.get(0);
			} else {
				betValue = numberSequence.get(0) + numberSequence.get(numberSequence.size() - 1);
			} 
			if (bank - betValue > 0) {
				bank -= betValue;
				if (spinResult.getColor().equals(Color.BLACK)) {
					bank += betValue * 2;
					numberSequence.remove(0);
					if (numberSequence.size() > 0) {
						numberSequence.remove(numberSequence.size() - 1);
					}
					if (numberSequence.isEmpty()) {
						systemCompleted = true;
					}

				} else {
					numberSequence.add(betValue);
				}
			} else {
				numberSequence.clear();
				numberSequence.addAll(startingNumberSequence);
				//throw new BankrutException("Bankrut przy stawce wynosz¹cej: " + betValue);
			}
		} 
		else {
			numberSequence.addAll(startingNumberSequence);
		}
	}
	

	@Override
	public String getName() {
		return "Labouchere Defence system ";
	}

	public boolean isSystemCompleted() {
		return systemCompleted;
	}

	@Override
	public void nextBet(SpinResult spinResult, RouletteStats stats) throws BankrutException, NotSupportedYetException {
		systemCompleted = numberSequence.isEmpty();
		Color betColor = Color.BLACK; 
		if(systemCompleted && bank >= startingBank + 100){
			cashBackProfit += (bank - startingBank)/2;
			startingBank += (bank - startingBank)/2; 
			bank = startingBank;

		}
		if (!systemCompleted) {
			if(numberSequence.size() == 1) {
				betValue = numberSequence.get(0);
			} else {
				betValue = numberSequence.get(0) + numberSequence.get(numberSequence.size() - 1);
			} 
			//if (bank - betValue > 0) {
				bank -= betValue;
				if (spinResult.getColor().equals(betColor)) {
					bank += betValue * 2;
					numberSequence.remove(0);
					if (numberSequence.size() > 0) {
						numberSequence.remove(numberSequence.size() - 1);
					}
					if (numberSequence.isEmpty()) {
						systemCompleted = true;
					}

				} else {
					numberSequence.add(betValue);
				}
//			} else {
//				numberSequence.clear();
//				numberSequence.addAll(startingNumberSequence);
//				//throw new BankrutException("Bankrut przy stawce wynosz¹cej: " + betValue);
//			}
		} 
		else {
			numberSequence.addAll(startingNumberSequence);
			betColor = calculateColorToBetDependsOnStats(stats);
		}
	}
	
	public Color calculateColorToBetDependsOnStats(RouletteStats stats){
		return stats.getBlackSpin() < stats.getRedSpin() ? Color.RED : Color.BLACK;
		//return Color.RED;
	}


}
