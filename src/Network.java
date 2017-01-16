import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing a neural network
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 * 
 */
public class Network implements Serializable {

	private static final long serialVersionUID = 1L;
	protected ArrayList<Layer> layers;

	/**
	 * Method returning the Layers list
	 * 
	 * @return layers list
	 */
	public ArrayList<Layer> getLayers() {
		return layers;
	}

	/**
	 * Constructor initializing a neural network
	 * 
	 * @param layerSizes
	 *            sizes of each layer of the neural network (including inputs
	 *            and outputs)
	 */
	public Network(ArrayList<Integer> layerSizes) {
		layers = new ArrayList<Layer>();

		// Create layers
		for (Integer i : layerSizes) {
			Layer layer = new Layer();
			for (int j = 0; j < i; j++) {

				// Create neurons
				Neuron neuron = new Neuron();

				// Create links
				if (layers.size() != 0) {
					Layer previousLayer = layers.get(layers.size() - 1);
					for (Neuron previousNeuron : previousLayer) {
						Link link = new Link();
						link.setOrigin(previousNeuron);
						link.setDestination(neuron);
						previousNeuron.getOutputs().add(link);
						neuron.getInputs().add(link);
					}
				}
				layer.add(neuron);
			}
			layers.add(layer);
		}
	}

	/**
	 * Method running the network with new inputs
	 * 
	 * @param inputs
	 *            new inputs to apply to the network
	 * @return the updated output layer
	 */
	public Layer run(ArrayList<Double> inputs) {
		Layer input = layers.get(0);

		// Update the input layer neurons with their new values
		for (int i = 0; i < input.size(); i++)
			input.get(i).setValue(inputs.get(i));
		for (int i = 1; i < layers.size(); i++)
			layers.get(i).computeValues();

		return layers.get(layers.size() - 1);
	}

	/**
	 * Run the network and achieve back propagation
	 * 
	 * @param inputs
	 *            inputs to apply to the network
	 * @param expectedOutput
	 *            expected value of the output layer
	 * @param learningCoeficient
	 *            learning coefficient to apply for the back propagation
	 */
	public void train(ArrayList<Double> inputs,
			ArrayList<Double> expectedOutput, Double learningCoeficient) {
		Layer output = run(inputs);
		Double delta;

		// Loop over output layer neurons to compute their gradient and delta
		for (int i = 0; i < output.size(); i++) {
			Neuron neuron = output.get(i);

			// Compute the new gradient
			neuron.setGradient((expectedOutput.get(i) - neuron.getValue())
					* neuron.getValue() * (1 - neuron.getValue()));

			// Compute weight delta and update neurons inputs weight
			for (Link link : neuron.getInputs()) {
				delta = learningCoeficient * neuron.getGradient()
						* link.getOrigin().getValue();
				link.setWeight(link.getWeight() + delta);
			}
		}

		// Point to the current layer and declare sum variable before looping
		Double sum;
		for (int i = layers.size() - 2; i > 0; i--) {
			for (Neuron neuron : layers.get(i)) {

				// Compute the sum of current neurons outputs
				// weights and gradient product
				sum = 0.0;
				for (Link link : neuron.getOutputs())
					sum += link.getDestination().getGradient()
							* link.getWeight();

				// Compute the current gradient using the previous sum
				neuron.setGradient(neuron.getValue() * (1 - neuron.getValue())
						* sum);

				// Compute delta and update inputs weight of the neuron
				for (Link link : neuron.getInputs()) {
					delta = learningCoeficient * neuron.getGradient()
							* link.getOrigin().getValue();
					link.setWeight(link.getWeight() + delta);
				}
			}
		}
	}
}
