import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Class representing the Boson neural network
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 */
public class BosonNetwork extends Network {

	private static final long serialVersionUID = 1L;
	final Double seuil = 0.5;
	private final Double trainingCoef = 0.80;

	/**
	 * Constructor initializing the Boson
	 * 
	 * @param layerSizes
	 */
	public BosonNetwork(ArrayList<Integer> layerSizes) {
		super(layerSizes);
	}

	/**
	 * Method training the boson network with a "brut force" algorithm
	 * 
	 * @param dataset
	 *            inputs training data
	 * @param learningCoeficient
	 *            learning coefficient to apply for back propagation
	 */
	public void train_algo2(ArrayList<ColisionEvent> dataset,
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
			super.train(inputs.toVector(), wantedOutput, learningCoeficient);

			traitedData.add(inputs);

			BosonEvaluation eval;
			eval = this.check(traitedData);
			while (eval.getTauxBonneClassification() < trainingCoef) {
				eval = this.check(traitedData);
				train_algo1(traitedData, learningCoeficient);
			}

			// Add the event to the trained data list
			dataset.remove(i);
			System.out.println((double) traitedData.size()
					/ ((double) (dataset.size() + traitedData.size())));
		}

		// Restore the original data set
		dataset.addAll(traitedData);
	}

	/**
	 * Method training the network with the classical algorithm
	 * 
	 * @param dataset
	 * @param learningCoeficient
	 */
	public void train_algo1(ArrayList<ColisionEvent> dataset,
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
			super.train(inputs.toVector(), wantedOutput, learningCoeficient);

			traitedData.add(inputs);

			// Add the event to the trained data list
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
				bosonEvaluation.incrementFalsePositive();
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
	public void save(File jsonFile, BosonEvaluation bosonEvaluation) {
		try {
			if (jsonFile.exists())
				jsonFile.delete();
			jsonFile.createNewFile();

			ObjectOutputStream flotEcriture = new ObjectOutputStream(
					new FileOutputStream(jsonFile));
			flotEcriture.writeObject(this);
			flotEcriture.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
