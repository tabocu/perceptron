import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import perceptron.Perceptron;
import perceptron.PerceptronFacade;
import perceptron.utils.ReaderUtils;

public class Main {

	public static void main(String[] args) {
		perceptron();
		
//		int maxIt1 = 4;
//		test("and2.csv",maxIt1);
//		test("or2.csv",maxIt1);
//		test("xor2.csv",maxIt1);
//		test("and3.csv",maxIt1);
//		test("or3.csv",maxIt1);
//		test("xor3.csv",maxIt1);
//		test("and4.csv",maxIt1);
//		test("or4.csv",maxIt1);
//		test("xor4.csv",maxIt1);
//		
//		int maxIt2 = 8;
//		test("and2.csv",maxIt2);
//		test("or2.csv",maxIt2);
//		test("xor2.csv",maxIt2);
//		test("and3.csv",maxIt2);
//		test("or3.csv",maxIt2);
//		test("xor3.csv",maxIt2);
//		test("and4.csv",maxIt2);
//		test("or4.csv",maxIt2);
//		test("xor4.csv",maxIt2);
//		
//		int maxIt3 = 50;
//		test("and2.csv",maxIt3);
//		test("or2.csv",maxIt3);
//		test("xor2.csv",maxIt3);
//		test("and3.csv",maxIt3);
//		test("or3.csv",maxIt3);
//		test("xor3.csv",maxIt3);
//		test("and4.csv",maxIt3);
//		test("or4.csv",maxIt3);
//		test("xor4.csv",maxIt3);
	}
	
	public static void test(String fileName, int maxIterations) {
		String canonicalFileName = "examples/" + fileName;
		try {
			Perceptron p = ReaderUtils.readPerceptron(new BufferedReader(new FileReader(canonicalFileName)), maxIterations);
			int [][] inputs = ReaderUtils.readInput(new BufferedReader(new FileReader(canonicalFileName)));
			int [] outputs = ReaderUtils.readOutput(new BufferedReader(new FileReader(canonicalFileName)));
			
			int wrong = 0, right = 0;
			
			for (int i = 0; i < outputs.length; i++) {
				if (p.execute(inputs[i]) == outputs[i])
					right++;
				else
					wrong++;
			}
			System.out.printf("%s - %d - %1.2f%% - W: ",fileName,maxIterations,(double)right/(double)(right+wrong)*100);
			for (int i = 0; i < p.getInputSize() - 1; i++) {
				System.out.printf("%1.0f, ",p.getWeight(i));
			}
			System.out.printf("%1.0f",p.getWeight(p.getInputSize() - 1));
			System.out.printf(" - Bias: %1.0f\n",p.getBias());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void perceptron() {
		Perceptron pAnd = new Perceptron(3);
		Perceptron pOr = new Perceptron(3);
		Perceptron pXor = new Perceptron(3);
		
		int [][] inputs = {{0, 0, 0},
				           {0, 0, 1},
			         	   {0, 1, 0},
						   {0, 1, 1},
						   {1, 0, 0},
				           {1, 0, 1},
			         	   {1, 1, 0},
						   {1, 1, 1}};
		
		int [] outputsAnd = {0, 0, 0, 0, 0, 0, 0, 1};
		int [] outputsOr  = {0, 1, 1, 1, 1, 1, 1, 1};
		int [] outputsXor = {0, 1, 1, 0, 1, 0, 0, 0};
		
		PerceptronFacade.perceptronTraining(pAnd, inputs, outputsAnd, 30);
		PerceptronFacade.perceptronTraining(pOr, inputs, outputsOr, 30);
		PerceptronFacade.perceptronTraining(pXor, inputs, outputsXor, 30);
		System.out.println("---------------------");
		System.out.println("| INPUT |  OUTPUT   |");
		System.out.println("| 1 2 3 |AND|OR |XOR|");
		System.out.println("---------------------");
		
		for (int i = 0; i < 8; i++) {
			System.out.printf("| %d %d %d | %d | %d | %d |\n", inputs[i][0],
														    inputs[i][1],
														    inputs[i][2],
														    pAnd.execute(inputs[i]),
														    pOr.execute(inputs[i]),
														    pXor.execute(inputs[i]));
		}
		System.out.println("---------------------");
	}
	
	public static void perceptronFile() {
		try {
			Perceptron pAnd = ReaderUtils.readPerceptron(new BufferedReader(
					new FileReader("examples/and.csv")), 100);
			int [][] inputs = ReaderUtils.readInput(new BufferedReader(
					new FileReader("examples/and.csv")));
			for (int [] input : inputs)
				System.out.println(pAnd.execute(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Perceptron pOr = ReaderUtils.readPerceptron(new BufferedReader(
					new FileReader("examples/or.csv")), 100);
			int [][] inputs = ReaderUtils.readInput(new BufferedReader(
					new FileReader("examples/or.csv")));
			for (int [] input : inputs)
				System.out.println(pOr.execute(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
