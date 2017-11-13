package perceptron;
public class PerceptronFacade {
	
	private PerceptronFacade() {}
	
	public static int perceptronTraining(Perceptron perceptron, int [][] inputs, int [] outputs, int maxIterations) {
		int counter = 0;
		boolean isTrained = false;
		
		while (!isTrained && (counter++) < maxIterations) {
			isTrained = true;
			
			for (int i = 0 ; i < outputs.length; i++) {
				int error = outputs[i] - perceptron.execute(inputs[i]);
				
				if (error != 0) {
					fixWeights(perceptron, inputs[i], error);
					isTrained = false;
				}
			}
		}
		return counter;
	}
	
	private static void fixWeights(Perceptron perceptron, int [] input, int error) {
		for (int j = 0; j < perceptron.getInputSize(); j++) {
			double weight = perceptron.getWeight(j) + error*input[j];
			perceptron.setWeight(j, weight);
		}
		double bias = perceptron.getBias() - error;
		perceptron.setBias(bias);
	}
}