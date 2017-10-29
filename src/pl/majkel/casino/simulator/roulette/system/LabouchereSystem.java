package pl.majkel.casino.simulator.roulette.system;

import java.util.LinkedList;
import java.util.List;
import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;

public class LabouchereSystem extends RouletteSystem {

	private List<Integer> numberSequence = new LinkedList<Integer>();
	private List<Integer> startingNumberSequence = new LinkedList<Integer>();
	private boolean systemCompleted = false;

	public LabouchereSystem(int bank, List<Integer> numberSequence) {
		super(bank);
		this.numberSequence.addAll(numberSequence);
		this.startingNumberSequence.addAll(numberSequence);
	}

	public void nextBet(SpinResult spinResult) throws BankrutException {
		systemCompleted = numberSequence.isEmpty();
		if (!systemCompleted) {
			if(numberSequence.size() == 1) {
				betValue = numberSequence.get(0);
			} else {
				betValue = numberSequence.get(0) + numberSequence.get(numberSequence.size() - 1);
			} 
			if (bank - betValue > 0) {
				bank -= betValue;
				if (spinResult.isEven()) {
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
	public void nextBet(SpinResult randNumber, RouletteStats stats) throws BankrutException, NotSupportedYetException {
		throw new NotSupportedYetException();		
	}

	@Override
	public String getName() {
		return "Labouchere system ";
	}

	public boolean isSystemCompleted() {
		return systemCompleted;
	}

}
