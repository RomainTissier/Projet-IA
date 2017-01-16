import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing a layer within a network, ie a list of neuron
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 * 
 */
public class Layer extends ArrayList<Neuron> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Compute the value of the layer (activation function)
	 */
	public void computeValues() {
		Double sum;

		// Loop over the neurons of the current layer
		for (Neuron neuron : this) {
			sum = 0.0;

			// Sum the inputs values and theirs weight
			for (Link link : neuron.getInputs())
				sum += link.getWeight() * link.getOrigin().getValue();

			// We use a sigmoid activation function
			neuron.setValue(1 / (1 + Math.exp(-sum)));
		}
	}
}
