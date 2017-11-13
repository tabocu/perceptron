package perceptron.utils;

import java.io.BufferedReader;
import java.io.IOException;

import perceptron.Perceptron;
import perceptron.PerceptronFacade;

public class ReaderUtils {
	
	private ReaderUtils() {}
	
	static final String SEPARATOR = ";";
	
	public static Perceptron readPerceptron(BufferedReader bufferedReader, int iterations) throws IOException {
		String line = bufferedReader.readLine();
		
		String [] dimensionsStr = line.split(SEPARATOR);
		int inputSize = Integer.parseInt(dimensionsStr[0]);
		int trainningSetSize = Integer.parseInt(dimensionsStr[1]);
		
		int [][] trainingSetInput = new int[trainningSetSize][inputSize];
		int [] trainingSetOutput = new int[trainningSetSize];
		
		for (int i = 0; i < trainningSetSize; i++) {
			line = bufferedReader.readLine();
			String [] inputStr = line.split(SEPARATOR);
			for (int j = 0; j < inputSize; j++) 
				trainingSetInput[i][j] = Integer.parseInt(inputStr[j]);
			trainingSetOutput[i] = Integer.parseInt(inputStr[inputSize]);
		}
		
		Perceptron perceptron = new Perceptron(inputSize);
		PerceptronFacade.perceptronTraining(perceptron, trainingSetInput, trainingSetOutput, iterations);
		return perceptron;
	}
	
	public static int [][] readInput(BufferedReader bufferedReader) throws IOException {
		String line = bufferedReader.readLine();
		
		String [] dimensionsStr = line.split(SEPARATOR);
		int inputSize = Integer.parseInt(dimensionsStr[0]);
		int trainningSetSize = Integer.parseInt(dimensionsStr[1]);
		
		int [][] trainingSetInput = new int[trainningSetSize][inputSize];
		
		for (int i = 0; i < trainningSetSize; i++) {
			line = bufferedReader.readLine();
			String [] inputStr = line.split(SEPARATOR);
			for (int j = 0; j < inputSize; j++) 
				trainingSetInput[i][j] = Integer.parseInt(inputStr[j]);
		}
		return trainingSetInput;
	}
	
	public static int [] readOutput(BufferedReader bufferedReader) throws IOException {
		String line = bufferedReader.readLine();
		
		String [] dimensionsStr = line.split(SEPARATOR);
		int inputSize = Integer.parseInt(dimensionsStr[0]);
		int trainningSetSize = Integer.parseInt(dimensionsStr[1]);
		int [] trainingSetOutput = new int[trainningSetSize];
		
		for (int i = 0; i < trainningSetSize; i++) {
			line = bufferedReader.readLine();
			String [] inputStr = line.split(SEPARATOR);
			trainingSetOutput[i] = Integer.parseInt(inputStr[inputSize]);
		}
		return trainingSetOutput;
	}
}
