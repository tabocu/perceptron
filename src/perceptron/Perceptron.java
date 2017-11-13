package perceptron;

public class Perceptron {
	private int m_size;
	
	private double[] m_weights;
	private double m_bias;
	
	Function <Integer,Double> m_actFunction;
	
	public Perceptron(int size) {
		m_weights = new double[size];
		m_size = size;
		m_actFunction = (Double input) -> (input >= 0 ? 1 : 0);
	}
	
	public int execute(int[] inputs) {
		double net = 0;
		for (int i = 0; i < m_size; i++)
			net += inputs[i]*m_weights[i];
		net += (-1)*m_bias;
		//System.out.printf("Net: %f\n",net);
		return m_actFunction.function(net);
	}
	
	public void setActivationFunction(Function <Integer,Double> actFunction) {
		m_actFunction = actFunction;
	}
	
	public double getBias() {
		return m_bias;
	}
	
	public void setBias(double bias) {
		m_bias = bias;
	}
	
	public double getWeight(int pos) {
		return m_weights[pos];
	}
	
	public void setWeight(int pos, double weight) {
		m_weights[pos] = weight;
	}
	
	public int getInputSize() {
		return m_size;
	}
}
