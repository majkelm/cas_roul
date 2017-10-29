package pl.majkel.casino.simulator.roulette.system;

import pl.majkel.casino.simulator.roulette.Color;
import pl.majkel.casino.simulator.roulette.RouletteStats;
import pl.majkel.casino.simulator.roulette.SpinResult;
import pl.majkel.casino.simulator.roulette.exception.BankrutException;
import pl.majkel.casino.simulator.roulette.exception.NotSupportedYetException;

public class ProgressionDefenceStartAfterXRepetetedSystem extends RouletteSystem {

	private boolean systemRunning = false;

	public ProgressionDefenceStartAfterXRepetetedSystem(int bank, int resignationPoint) {
		super(bank);
		this.resignationPoint = resignationPoint;
	}

	@Override
	public void nextBet(SpinResult spinResult) {
//		if (bank >= startingBank + 100) {
//			cashBackProfit += (bank - startingBank) / 2;
//			startingBank += (bank - startingBank) / 2;
//			bank = startingBank;
//
//		}
//		if (bank - betValue > 0) {
//			bank -= betValue;
//			if (spinResult.getNumber() > 0 && spinResult.getNumber() <= 12) {
//				bank += betValue * 3;
//				betValue = 1;
//			} else {
//				if (betLevel <= resignationPoint) {
//					betLevel++;
//					betValue = Fibonacci.calculate(betLevel);
//				} else {
//					betLevel = 1;
//					betValue = 1;
//				}
//			}
//		} else {
//			betValue = 1;
//		}
	}

	@Override
	public void nextBet(SpinResult spinResult, RouletteStats stats) throws BankrutException, NotSupportedYetException {
		cashBackIncreasingBank();
		Color colorToBet = calculateColorToBet(stats);
		if (systemRunning && colorToBet != null) {
			if (bank - betValue > 0) {
				bank -= betValue;
				if (spinResult.getColor().equals(colorToBet)) {
					bank += betValue * 2;
					betValue = 1;
				} else {
					if (betLevel <= resignationPoint) {
						betLevel++;
						betValue = betLevel * 2;
					} else {
						betLevel = 1;
						betValue = 1;
						systemRunning = false;
					}
				}
			} else {
				betValue = 1;
				systemRunning = false;
			}
		} else {
			return;
		}
	}

	private Color calculateColorToBet(RouletteStats stats) {
		if (stats.getAllSpins() > 6) {
			if (stats.getRedSpin() == 0) {
				systemRunning = true;
				return Color.RED;
			} else if (stats.getBlackSpin() == 0) {
				systemRunning = true;
				return Color.BLACK;
			}
		}
		return null;
	}

	public String getName() {
		return "Progresja defensywna z graniem na kolor przeciwny do tego ktory sie pojawil ostatnio w dluzszej serii";
	}

}
