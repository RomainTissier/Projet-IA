import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class extending ArrayList representing a data set of collision event
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 * 
 */
public class ColisionEventList extends ArrayList<ColisionEvent> {

	private static final long serialVersionUID = 7886459674222557917L;

	/**
	 * Constructor loading a data set from a CSV file
	 * 
	 * @param csvFile
	 *            file containing event to load
	 */
	public ColisionEventList(File csvFile) {
		super();
		ArrayList<String> load = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csvFile));

			String line = br.readLine();

			// Read line by line the file and parse data
			for (line = br.readLine(); line != null; line = br.readLine()) {
				load.add(line);
				String parsedLine[] = line.split(",");
				ColisionEvent ce = new ColisionEvent();
				ce.setDER_mass_MMC(Double.parseDouble(parsedLine[1]));
				ce.setDER_mass_transverse_met_lep(Double
						.parseDouble(parsedLine[2]));
				ce.setDER_mass_vis(Double.parseDouble(parsedLine[3]));
				ce.setDER_pt_h(Double.parseDouble(parsedLine[4]));
				ce.setDER_deltaeta_jet_jet(Double.parseDouble(parsedLine[5]));
				ce.setDER_mass_jet_jet(Double.parseDouble(parsedLine[6]));
				ce.setDER_prodeta_jet_jet(Double.parseDouble(parsedLine[7]));
				ce.setDER_deltar_tau_lep(Double.parseDouble(parsedLine[8]));
				ce.setDER_pt_tot(Double.parseDouble(parsedLine[9]));
				ce.setDER_sum_pt(Double.parseDouble(parsedLine[10]));
				ce.setDER_pt_ratio_lep_tau(Double.parseDouble(parsedLine[11]));
				ce.setDER_met_phi_centrality(Double.parseDouble(parsedLine[12]));
				ce.setDER_lep_eta_centrality(Double.parseDouble(parsedLine[13]));
				ce.setPRI_tau_pt(Double.parseDouble(parsedLine[14]));
				ce.setPRI_tau_eta(Double.parseDouble(parsedLine[15]));
				ce.setPRI_tau_phi(Double.parseDouble(parsedLine[16]));
				ce.setPRI_lep_pt(Double.parseDouble(parsedLine[17]));
				ce.setPRI_lep_eta(Double.parseDouble(parsedLine[18]));
				ce.setPRI_lep_phi(Double.parseDouble(parsedLine[19]));
				ce.setPRI_met(Double.parseDouble(parsedLine[20]));
				ce.setPRI_met_phi(Double.parseDouble(parsedLine[21]));
				ce.setPRI_met_sumet(Double.parseDouble(parsedLine[22]));
				ce.setPRI_jet_num(Double.parseDouble(parsedLine[23]));
				ce.setPRI_jet_leading_pt(Double.parseDouble(parsedLine[24]));
				ce.setPRI_jet_leading_eta(Double.parseDouble(parsedLine[25]));
				ce.setPRI_jet_leading_phi(Double.parseDouble(parsedLine[26]));
				ce.setPRI_jet_subleading_pt(Double.parseDouble(parsedLine[27]));
				ce.setPRI_jet_subleading_eta(Double.parseDouble(parsedLine[28]));
				ce.setPRI_jet_subleading_phi(Double.parseDouble(parsedLine[29]));
				ce.setPRI_jet_all_pt(Double.parseDouble(parsedLine[30]));
				if (parsedLine.length > 31) {
					if (parsedLine[32].equals("s")) {
						ce.setInterest(true);
					} else {
						ce.setInterest(false);
					}
				}
				this.add(ce);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method returning a random data set from the original data set
	 * 
	 * @param size
	 *            number of element to add in the random data set
	 * @return the random data set
	 */
	public ArrayList<ColisionEvent> getRandomDataSet(int size) {
		return getRandomDataSet(size, new ArrayList<ColisionEvent>());
	}

	/**
	 * Method returning a random data set from the original data set excepting
	 * the except event list
	 * 
	 * @param size
	 *            size number of element to add in the random data set
	 * @param except
	 *            the list of event to not include in the random list
	 * @return the random data set
	 */
	public ArrayList<ColisionEvent> getRandomDataSet(int size,
			ArrayList<ColisionEvent> except) {
		ArrayList<ColisionEvent> res = new ArrayList<ColisionEvent>();

		// Clone the original data set into a temporary list
		@SuppressWarnings("unchecked")
		ArrayList<ColisionEvent> currentList = (ArrayList<ColisionEvent>) this
				.clone();

		// Remove the except event in this new list
		for (ColisionEvent ex : except)
			currentList.remove(ex);

		// Take <size> element randomly from this list
		while (res.size() < size) {
			int i = (int) (Math.random() * currentList.size());
			res.add(currentList.get(i));
			currentList.remove(i);
		}

		return res;
	}
}
