import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
			if (args.length == 2) {
				try {
					ObjectInputStream flotLecture = new ObjectInputStream(
							new FileInputStream(args[0]));
					BosonNetwork bn = (BosonNetwork) flotLecture.readObject();

					System.out.println("Test result:");
					ColisionEventList ce = new ColisionEventList(new File(
							args[1]));
					normalize(ce);
					BosonEvaluation testEvaluation = bn.check(ce);
					System.out.println(testEvaluation.toString());
					bn.save(new File("testout"), new BosonEvaluation());
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				return;
			}
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

		// NORMALIZE DATA
		System.out.print("Normalizing data...");
		ColisionEvent coef = normalize(training);
		normalize(test, coef);
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
			System.out.print("Learning pass " + (i + 1) + "... \n");
			bosonNetwork.train_algo2(training, learningCoeficient);
			System.out.println("[OK]");
			System.out.println("Learning result:");
			BosonEvaluation learningEvaluation = bosonNetwork.check(training);
			System.out.println(learningEvaluation.toString());

			System.out.println("Test result:");
			BosonEvaluation testEvaluation = bosonNetwork.check(test);
			System.out.println(testEvaluation.toString());
		}

		// Compute performance

		System.out.println("Learning result:");
		BosonEvaluation learningEvaluation = bosonNetwork.check(training);
		System.out.println(learningEvaluation.toString());

		System.out.println("Test result:");
		BosonEvaluation testEvaluation = bosonNetwork.check(test);
		System.out.println(testEvaluation.toString());

		System.out.println("YOLO:");
		normalize(cel);
		BosonEvaluation be = bosonNetwork.check(cel);
		System.out.println(be.toString());
		// Ask user a JSON save
		System.out.println("Would you like to save this result ?");
		Scanner sc = new Scanner(System.in);
		String ans = sc.next();
		if (ans.startsWith("y") || ans.startsWith("Y")) {
			System.out.println("Name the new outputfile:");
			ans = sc.next();
			bosonNetwork.save(new File(ans), testEvaluation);
		}
		sc.close();
	}

	/**
	 * Compute coeficient and normalize data
	 * 
	 * @param training
	 *            training set
	 * @return coeficients
	 */
	public static ColisionEvent normalize(ArrayList<ColisionEvent> training) {
		ColisionEvent coeficient = new ColisionEvent();
		coeficient.setDER_mass_MMC(0.0);
		coeficient.setDER_mass_transverse_met_lep(0.0);
		coeficient.setDER_mass_vis(0.0);
		coeficient.setDER_pt_h(0.0);
		coeficient.setDER_deltaeta_jet_jet(0.0);
		coeficient.setDER_mass_jet_jet(0.0);
		coeficient.setDER_prodeta_jet_jet(0.0);
		coeficient.setDER_deltar_tau_lep(0.0);
		coeficient.setDER_pt_tot(0.0);
		coeficient.setDER_sum_pt(0.0);
		coeficient.setDER_pt_ratio_lep_tau(0.0);
		coeficient.setDER_met_phi_centrality(0.0);
		coeficient.setDER_lep_eta_centrality(0.0);
		coeficient.setPRI_tau_pt(0.0);
		coeficient.setPRI_tau_eta(0.0);
		coeficient.setPRI_tau_phi(0.0);
		coeficient.setPRI_lep_pt(0.0);
		coeficient.setPRI_lep_eta(0.0);
		coeficient.setPRI_lep_phi(0.0);
		coeficient.setPRI_met(0.0);
		coeficient.setPRI_met_phi(0.0);
		coeficient.setPRI_met_sumet(0.0);
		coeficient.setPRI_jet_num(0.0);
		coeficient.setPRI_jet_leading_pt(0.0);
		coeficient.setPRI_jet_leading_eta(0.0);
		coeficient.setPRI_jet_leading_phi(0.0);
		coeficient.setPRI_jet_subleading_pt(0.0);
		coeficient.setPRI_jet_subleading_eta(0.0);
		coeficient.setPRI_jet_subleading_phi(0.0);
		coeficient.setPRI_jet_all_pt(0.0);

		for (ColisionEvent ce : training) {
			if (coeficient.getDER_mass_MMC() < Math.abs(ce.getDER_mass_MMC()))
				coeficient.setDER_mass_MMC(Math.abs(ce.getDER_mass_MMC()));
			if (coeficient.getDER_mass_transverse_met_lep() < Math.abs(ce
					.getDER_mass_transverse_met_lep()))
				coeficient.setDER_mass_transverse_met_lep(Math.abs(ce
						.getDER_mass_transverse_met_lep()));
			if (coeficient.getDER_mass_vis() < Math.abs(ce.getDER_mass_vis()))
				coeficient.setDER_mass_vis(Math.abs(ce.getDER_mass_vis()));
			if (coeficient.getDER_pt_h() < Math.abs(ce.getDER_pt_h()))
				coeficient.setDER_pt_h(Math.abs(ce.getDER_pt_h()));
			if (coeficient.getDER_deltaeta_jet_jet() < Math.abs(ce
					.getDER_deltaeta_jet_jet()))
				coeficient.setDER_deltaeta_jet_jet(Math.abs(ce
						.getDER_deltaeta_jet_jet()));
			if (coeficient.getDER_mass_jet_jet() < Math.abs(ce
					.getDER_mass_jet_jet()))
				coeficient.setDER_mass_jet_jet(Math.abs(ce
						.getDER_mass_jet_jet()));
			if (coeficient.getDER_prodeta_jet_jet() < Math.abs(ce
					.getDER_prodeta_jet_jet()))
				coeficient.setDER_prodeta_jet_jet(Math.abs(ce
						.getDER_prodeta_jet_jet()));
			if (coeficient.getDER_deltar_tau_lep() < Math.abs(ce
					.getDER_deltar_tau_lep()))
				coeficient.setDER_deltar_tau_lep(Math.abs(ce
						.getDER_deltar_tau_lep()));
			if (coeficient.getDER_pt_tot() < Math.abs(ce.getDER_pt_tot()))
				coeficient.setDER_pt_tot(Math.abs(ce.getDER_pt_tot()));
			if (coeficient.getDER_sum_pt() < Math.abs(ce.getDER_sum_pt()))
				coeficient.setDER_sum_pt(Math.abs(ce.getDER_sum_pt()));
			if (coeficient.getDER_pt_ratio_lep_tau() < Math.abs(ce
					.getDER_pt_ratio_lep_tau()))
				coeficient.setDER_pt_ratio_lep_tau(Math.abs(ce
						.getDER_pt_ratio_lep_tau()));
			if (coeficient.getDER_met_phi_centrality() < Math.abs(ce
					.getDER_met_phi_centrality()))
				coeficient.setDER_met_phi_centrality(Math.abs(ce
						.getDER_met_phi_centrality()));
			if (coeficient.getDER_lep_eta_centrality() < Math.abs(ce
					.getDER_lep_eta_centrality()))
				coeficient.setDER_lep_eta_centrality(Math.abs(ce
						.getDER_lep_eta_centrality()));
			if (coeficient.getPRI_tau_pt() < Math.abs(ce.getPRI_tau_pt()))
				coeficient.setPRI_tau_pt(Math.abs(ce.getPRI_tau_pt()));
			if (coeficient.getPRI_tau_eta() < Math.abs(ce.getPRI_tau_eta()))
				coeficient.setPRI_tau_eta(Math.abs(ce.getPRI_tau_eta()));
			if (coeficient.getPRI_tau_phi() < Math.abs(ce.getPRI_tau_phi()))
				coeficient.setPRI_tau_phi(Math.abs(ce.getPRI_tau_phi()));
			if (coeficient.getPRI_lep_pt() < Math.abs(ce.getPRI_lep_pt()))
				coeficient.setPRI_lep_pt(Math.abs(ce.getPRI_lep_pt()));
			if (coeficient.getPRI_lep_eta() < Math.abs(ce.getPRI_lep_eta()))
				coeficient.setPRI_lep_eta(Math.abs(ce.getPRI_lep_eta()));
			if (coeficient.getPRI_lep_phi() < Math.abs(ce.getPRI_lep_phi()))
				coeficient.setPRI_lep_phi(Math.abs(ce.getPRI_lep_phi()));
			if (coeficient.getPRI_met() < Math.abs(ce.getPRI_met()))
				coeficient.setPRI_met(Math.abs(ce.getPRI_met()));
			if (coeficient.getPRI_met_phi() < Math.abs(ce.getPRI_met_phi()))
				coeficient.setPRI_met_phi(Math.abs(ce.getPRI_met_phi()));
			if (coeficient.getPRI_met_sumet() < Math.abs(ce.getPRI_met_sumet()))
				coeficient.setPRI_met_sumet(Math.abs(ce.getPRI_met_sumet()));
			if (coeficient.getPRI_jet_num() < Math.abs(ce.getPRI_jet_num()))
				coeficient.setPRI_jet_num(Math.abs(ce.getPRI_jet_num()));
			if (coeficient.getPRI_jet_leading_pt() < Math.abs(ce
					.getPRI_jet_leading_pt()))
				coeficient.setPRI_jet_leading_pt(Math.abs(ce
						.getPRI_jet_leading_pt()));
			if (coeficient.getPRI_jet_leading_eta() < Math.abs(ce
					.getPRI_jet_leading_eta()))
				coeficient.setPRI_jet_leading_eta(Math.abs(ce
						.getPRI_jet_leading_eta()));
			if (coeficient.getPRI_jet_leading_phi() < Math.abs(ce
					.getPRI_jet_leading_phi()))
				coeficient.setPRI_jet_leading_phi(Math.abs(ce
						.getPRI_jet_leading_phi()));
			if (coeficient.getPRI_jet_subleading_pt() < Math.abs(ce
					.getPRI_jet_subleading_pt()))
				coeficient.setPRI_jet_subleading_pt(Math.abs(ce
						.getPRI_jet_subleading_pt()));
			if (coeficient.getPRI_jet_subleading_eta() < Math.abs(ce
					.getPRI_jet_subleading_eta()))
				coeficient.setPRI_jet_subleading_eta(Math.abs(ce
						.getPRI_jet_subleading_eta()));
			if (coeficient.getPRI_jet_subleading_phi() < Math.abs(ce
					.getPRI_jet_subleading_phi()))
				coeficient.setPRI_jet_subleading_phi(Math.abs(ce
						.getPRI_jet_subleading_phi()));
			if (coeficient.getPRI_jet_all_pt() < Math.abs(ce
					.getPRI_jet_all_pt()))
				coeficient.setPRI_jet_all_pt(Math.abs(ce.getPRI_jet_all_pt()));
		}
		normalize(training, coeficient);

		return coeficient;
	}

	/**
	 * Normalize data using given parameters
	 * 
	 * @param list
	 *            dataset to normalize
	 * @param coeficient
	 *            use for normalizing
	 * @return coeficient
	 */
	public static ColisionEvent normalize(ArrayList<ColisionEvent> list,
			ColisionEvent coeficient) {

		// Applique les coeficients
		for (ColisionEvent ce : list) {
			ce.setDER_mass_MMC(ce.getDER_mass_MMC()
					/ coeficient.getDER_mass_MMC());
			ce.setDER_mass_transverse_met_lep(ce
					.getDER_mass_transverse_met_lep()
					/ coeficient.getDER_mass_transverse_met_lep());
			ce.setDER_mass_vis(ce.getDER_mass_vis()
					/ coeficient.getDER_mass_vis());
			ce.setDER_pt_h(ce.getDER_pt_h() / coeficient.getDER_pt_h());
			ce.setDER_deltaeta_jet_jet(ce.getDER_deltaeta_jet_jet()
					/ coeficient.getDER_deltaeta_jet_jet());
			ce.setDER_mass_jet_jet(ce.getDER_mass_jet_jet()
					/ coeficient.getDER_deltaeta_jet_jet());
			ce.setDER_prodeta_jet_jet(ce.getDER_prodeta_jet_jet()
					/ coeficient.getDER_prodeta_jet_jet());
			ce.setDER_deltar_tau_lep(ce.getDER_deltar_tau_lep()
					/ coeficient.getDER_deltar_tau_lep());
			ce.setDER_pt_tot(ce.getDER_pt_tot() / coeficient.getDER_pt_tot());
			ce.setDER_sum_pt(ce.getDER_sum_pt() / coeficient.getDER_sum_pt());
			ce.setDER_pt_ratio_lep_tau(ce.getDER_pt_ratio_lep_tau()
					/ coeficient.getDER_pt_ratio_lep_tau());
			ce.setDER_met_phi_centrality(ce.getDER_met_phi_centrality()
					/ coeficient.getDER_met_phi_centrality());
			ce.setDER_lep_eta_centrality(ce.getDER_lep_eta_centrality()
					/ coeficient.getDER_lep_eta_centrality());
			ce.setPRI_tau_pt(ce.getPRI_tau_pt() / coeficient.getPRI_tau_pt());
			ce.setPRI_tau_eta(ce.getPRI_tau_eta() / coeficient.getPRI_tau_eta());
			ce.setPRI_tau_phi(ce.getPRI_tau_phi() / coeficient.getPRI_tau_phi());
			ce.setPRI_lep_pt(ce.getPRI_lep_pt() / coeficient.getPRI_lep_pt());
			ce.setPRI_lep_eta(ce.getPRI_lep_eta() / coeficient.getPRI_lep_eta());
			ce.setPRI_lep_phi(ce.getPRI_lep_phi() / coeficient.getPRI_lep_phi());
			ce.setPRI_met(ce.getPRI_met() / coeficient.getPRI_met());
			ce.setPRI_met_phi(ce.getPRI_met_phi() / coeficient.getPRI_met_phi());
			ce.setPRI_met_sumet(ce.getPRI_met_sumet()
					/ coeficient.getPRI_met_sumet());
			ce.setPRI_jet_num(ce.getPRI_jet_num() / coeficient.getPRI_jet_num());
			ce.setPRI_jet_leading_pt(ce.getPRI_jet_leading_pt()
					/ coeficient.getPRI_jet_leading_pt());
			ce.setPRI_jet_leading_eta(ce.getPRI_jet_leading_eta()
					/ coeficient.getPRI_jet_leading_eta());
			ce.setPRI_jet_leading_phi(ce.getPRI_jet_leading_phi()
					/ coeficient.getPRI_jet_leading_phi());
			ce.setPRI_jet_subleading_pt(ce.getPRI_jet_subleading_pt()
					/ coeficient.getPRI_jet_subleading_pt());
			ce.setPRI_jet_subleading_eta(ce.getPRI_jet_subleading_eta()
					/ coeficient.getPRI_jet_subleading_eta());
			ce.setPRI_jet_subleading_phi(ce.getPRI_jet_subleading_phi()
					/ coeficient.getPRI_jet_subleading_phi());
			ce.setPRI_jet_all_pt(ce.getPRI_jet_all_pt()
					/ coeficient.getPRI_jet_all_pt());
		}
		return coeficient;
	}
}
