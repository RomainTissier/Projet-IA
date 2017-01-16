import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing a neuron within the network
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 * 
 */
public class Neuron implements Serializable {
	private ArrayList<Link> inputs;
	private ArrayList<Link> outputs;
	private Double gradient;
	private Double value;

	/**
	 * Constructor initializing a neuron with empty inputs and outputs and 0
	 * gradient and value
	 */
	public Neuron() {
		inputs = new ArrayList<Link>();
		outputs = new ArrayList<Link>();
		gradient = 0.0;
		value = 0.0;
	}

	/**
	 * Getter returning the input list of the neuron
	 * 
	 * @return the input list of the neuron
	 */
	public ArrayList<Link> getInputs() {
		return inputs;
	}

	/**
	 * Setter updating the input list of the neuron
	 * 
	 * @param inputs
	 *            new input list
	 */
	public void setInputs(ArrayList<Link> inputs) {
		this.inputs = inputs;
	}

	/**
	 * Getter returning the output list of the neuron
	 * 
	 * @return the output list of the neuron
	 */
	public ArrayList<Link> getOutputs() {
		return outputs;
	}

	/**
	 * Setter updating the output list of the neuron
	 * 
	 * @param outputs
	 *            new output list
	 */
	public void setOutputs(ArrayList<Link> outputs) {
		this.outputs = outputs;
	}

	/**
	 * Getter returning the gradient value of the neuron
	 * 
	 * @return the gradient value of the neuron
	 */
	public Double getGradient() {
		return gradient;
	}

	/**
	 * Updating the gradient value
	 * 
	 * @param gradient
	 *            new value
	 */
	public void setGradient(Double gradient) {
		this.gradient = gradient;
	}

	/**
	 * Getter returning the value (issued from activation function) of the
	 * neuron
	 * 
	 * @return the value of the neuron
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * Update the value of the neuron
	 * 
	 * @param value
	 *            new value
	 */
	public void setValue(Double value) {
		this.value = value;
	}
}
