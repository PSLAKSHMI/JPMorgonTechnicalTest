package main;

import model.calculation.Calculate;

import trade_logic.OutputGenerator;
import utils.ErrorInstructionGenerator;

import java.util.Set;

public class AppMain {

	public static void main(String[] args) {
		final Set<Calculate> instructions = ErrorInstructionGenerator.getFakeInstructions();
		final OutputGenerator reportGenerator = new OutputGenerator();

		System.out.println(reportGenerator.generateInstructionsReport(instructions));
	}
}