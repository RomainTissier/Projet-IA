import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class representing the Boson neural network
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 */
public class BosonNetwork extends Network {
	final Double seuil = 0.5;

	/**
	 * Constructor initializing the Boson
	 * 
	 * @param layerSizes
	 */
	public BosonNetwork(ArrayList<Integer> layerSizes) {
		super(layerSizes);
	}

	/**
	 * Method training the boson network
	 * 
	 * @param dataset
	 *            inputs training data
	 * @param learningCoeficient
	 *            learning coefficient to apply for back propagation
	 */
	public void train(ArrayList<ColisionEvent> dataset,
			Double learningCoeficient) {
		ArrayList<ColisionEvent> traitedData = new ArrayList<ColisionEvent>();

		// Load inputs from event and train the network
		int i;
		ColisionEvent inputs;
		while (dataset.size() > 0) {

			// Get a random element in the data set
			i = (int) (Math.random() * dataset.size());
			inputs = dataset.get(i);

			// Determine the wanted output value
			ArrayList<Double> wantedOutput = new ArrayList<Double>();
			if (inputs.getInterest())
				wantedOutput.add(1.0);
			else
				wantedOutput.add(0.0);

			// Train the network by using the inherited train method
			this.train(inputs.toVector(), wantedOutput, learningCoeficient);

			// Add the event to the trained data list
			traitedData.add(inputs);
			dataset.remove(i);
		}

		// Restore the original data set
		dataset.addAll(traitedData);
	}

	/**
	 * Check the performance of the network by testing it with different inputs
	 * 
	 * @param testList
	 *            inputs to test the network
	 * @return BosonEvaluation object that contains performance indicators
	 */
	public BosonEvaluation check(ArrayList<ColisionEvent> testList) {
		BosonEvaluation bosonEvaluation = new BosonEvaluation();

		// Loop over the testList object
		for (ColisionEvent colisionEvent : testList) {

			// Run the network and get the output value
			Double output = this.run(colisionEvent.toVector()).get(0)
					.getValue();

			// Update indicators
			if (output >= seuil && colisionEvent.getInterest() == true)
				bosonEvaluation.incrementTruePositive();
			else if (output >= seuil && colisionEvent.getInterest() == false)
				bosonEvaluation.incrementFalseNegative();
			else if (output < seuil && colisionEvent.getInterest() == false)
				bosonEvaluation.incrementTrueNegative();
			else if (output < seuil && colisionEvent.getInterest() == true)
				bosonEvaluation.incrementFalseNegative();
			if (output < 0)
				bosonEvaluation.incrementInfZero();
			else if (output > 1)
				bosonEvaluation.incrementSupOne();
			else if (output >= 0 && output < 0.25)
				bosonEvaluation.incrementQuartOne();
			else if (output >= 0.25 && output < 0.5)
				bosonEvaluation.incrementQuartTwo();
			else if (output >= 0.5 && output < 0.75)
				bosonEvaluation.incrementQuartThree();
			else if (output >= 0.75 && output <= 1)
				bosonEvaluation.incrementQuartFour();
		}

		return bosonEvaluation;
	}

	/**
	 * Method saving the current network architecture in a JSON file
	 * 
	 * @param jsonFile
	 *            JSON file
	 * @param bosonEvaluation
	 *            evaluation of the boson network
	 */
	public void toJSON(File jsonFile, BosonEvaluation bosonEvaluation) {
		try {

			// if the JSON file already exist, erase it
			if (jsonFile.exists())
				jsonFile.delete();
			jsonFile.createNewFile();

			FileWriter jsonWriter;
			jsonWriter = new FileWriter(jsonFile);
			jsonWriter.write("{\n");

			// Write confusion matrix
			jsonWriter.write("\tTP:\"" + bosonEvaluation.getTruePositive()
					+ "\"\n");
			jsonWriter.write("\tTN:\"" + bosonEvaluation.getTrueNegative()
					+ "\"\n");
			jsonWriter.write("\tFP:\"" + bosonEvaluation.getFalsePositive()
					+ "\"\n");
			jsonWriter.write("\tFN:\"" + bosonEvaluation.getFalseNegative()
					+ "\"\n");

			// Write layers list
			jsonWriter.write("\tlayers:[\n");
			for (Layer layer : layers) {

				jsonWriter.write("\t\tlayer:{\n");
				for (Neuron neuron : layer) {

					// Write neuron by neuron
					jsonWriter.write("\t\t\tneuron:{\n");

					// Write input and output weights
					jsonWriter.write("\t\t\t\tinputs:[\n");
					for (Link link : neuron.getInputs())
						jsonWriter.write("\t\t\t\t\t\"" + link.getWeight()
								+ "\"\n");
					jsonWriter.write("\t\t\t\t]\n");
					jsonWriter.write("\t\t\t\toutputs:[\n");
					for (Link link : neuron.getOutputs())
						jsonWriter.write("\t\t\t\t\t\"" + link.getWeight()
								+ "\"\n");
					jsonWriter.write("\t\t\t\t]\n");
					jsonWriter.write("\t\t\t}\n");
				}
				jsonWriter.write("\t\t}\n");
			}
			jsonWriter.write("\t]\n");
			jsonWriter.write("}\n");
			jsonWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
