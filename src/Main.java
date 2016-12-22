import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entry point of the program
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 * 
 */
public class Main {

	/**
	 * Entry point of the program
	 * 
	 * @param args
	 *            argument of the program (see bellow)
	 */
	public static void main(String[] args) {

		// Check program arguments
		if (args.length < 3) {
			System.out
					.println("Param: <learning_coeficient> <passes> <training.csv> <size input> <size hidden layer 1> <size hidden layer 2> [...] <size hidden layer n> <size output>");
			System.out
					.println("\t* <learning_coeficient> is the learning coeficient use by backpropagation");
			System.out
					.println("\t* <passes> is the number of learning passes for the training set");
			System.out.println("\t* <training.csv> is the training file");
			System.out
					.println("\t* <size> is the number of neurons of the layer");
			return;
		}

		// Load data set
		System.out.print("Loading data...");
		ColisionEventList cel = new ColisionEventList(new File(args[2]));
		System.out.println(" [OK]");
		System.out.print("Creating random and test dataset ... ");
		ArrayList<ColisionEvent> training = cel.getRandomDataSet(200000);
		ArrayList<ColisionEvent> test = cel.getRandomDataSet(50000, training);
		System.out.println("[OK]");

		// Parse argument
		ArrayList<Integer> layerSizes = new ArrayList<Integer>();
		for (int i = 3; i < args.length; i++) {
			layerSizes.add(Integer.parseInt(args[i]));
		}
		Double learningCoeficient = Double.parseDouble(args[0]);
		BosonNetwork bosonNetwork = new BosonNetwork(layerSizes);
		Integer nb_passes = Integer.parseInt(args[1]);

		// Train the network
		for (int i = 0; i < nb_passes; i++) {
			System.out.print("Learning pass " + (i + 1) + "... ");
			bosonNetwork.train(training, learningCoeficient);
			System.out.println("[OK]");
		}

		// Compute performance

		System.out.println("Learning result:");
		BosonEvaluation learningEvaluation = bosonNetwork.check(training);
		System.out.println(learningEvaluation.toString());

		System.out.println("Test result:");
		BosonEvaluation testEvaluation = bosonNetwork.check(test);
		System.out.println(testEvaluation.toString());

		// Ask user a JSON save
		System.out.println("Would you like to save in result in JSON ?");
		Scanner sc = new Scanner(System.in);
		String ans = sc.next();
		if (ans.startsWith("y") || ans.startsWith("Y")) {
			System.out.println("Name the new outputfile:");
			ans = sc.next();
			bosonNetwork.toJSON(new File(ans), testEvaluation);
		}
		sc.close();
	}
}
