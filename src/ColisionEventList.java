import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ColisionEventList extends ArrayList<ColisionEvent> {

	private static final long serialVersionUID = 7886459674222557917L;

	public ColisionEventList(File csvFile) {
		super();
		ArrayList<String> load = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csvFile));

			String line = br.readLine();
			for (line = br.readLine(); line != null; line = br.readLine()) {
				load.add(line);
				String parsedLine[] = line.split(",");
				ColisionEvent ce = new ColisionEvent();
				ce.setDER_mass_MMC(Float.parseFloat(parsedLine[1]));
				ce.setDER_mass_transverse_met_lep(Float
						.parseFloat(parsedLine[2]));
				ce.setDER_mass_vis(Float.parseFloat(parsedLine[3]));
				ce.setDER_pt_h(Float.parseFloat(parsedLine[4]));
				ce.setDER_deltaeta_jet_jet(Float.parseFloat(parsedLine[5]));
				ce.setDER_mass_jet_jet(Float.parseFloat(parsedLine[6]));
				ce.setDER_prodeta_jet_jet(Float.parseFloat(parsedLine[7]));
				ce.setDER_deltar_tau_lep(Float.parseFloat(parsedLine[8]));
				ce.setDER_pt_tot(Float.parseFloat(parsedLine[9]));
				ce.setDER_sum_pt(Float.parseFloat(parsedLine[10]));
				ce.setDER_pt_ratio_lep_tau(Float.parseFloat(parsedLine[11]));
				ce.setDER_met_phi_centrality(Float.parseFloat(parsedLine[12]));
				ce.setDER_lep_eta_centrality(Float.parseFloat(parsedLine[13]));
				ce.setPRI_tau_pt(Float.parseFloat(parsedLine[14]));
				ce.setPRI_tau_eta(Float.parseFloat(parsedLine[15]));
				ce.setPRI_tau_phi(Float.parseFloat(parsedLine[16]));
				ce.setPRI_lep_pt(Float.parseFloat(parsedLine[17]));
				ce.setPRI_lep_eta(Float.parseFloat(parsedLine[18]));
				ce.setPRI_lep_phi(Float.parseFloat(parsedLine[19]));
				ce.setPRI_met(Float.parseFloat(parsedLine[20]));
				ce.setPRI_met_phi(Float.parseFloat(parsedLine[21]));
				ce.setPRI_met_sumet(Float.parseFloat(parsedLine[22]));
				ce.setPRI_jet_num(Float.parseFloat(parsedLine[23]));
				ce.setPRI_jet_leading_pt(Float.parseFloat(parsedLine[24]));
				ce.setPRI_jet_leading_eta(Float.parseFloat(parsedLine[25]));
				ce.setPRI_jet_leading_phi(Float.parseFloat(parsedLine[26]));
				ce.setPRI_jet_subleading_pt(Float.parseFloat(parsedLine[27]));
				ce.setPRI_jet_subleading_eta(Float.parseFloat(parsedLine[28]));
				ce.setPRI_jet_subleading_phi(Float.parseFloat(parsedLine[29]));
				ce.setPRI_jet_all_pt(Float.parseFloat(parsedLine[30]));
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

	public ArrayList<ColisionEvent> getRandomDataSet(int size) {
		return getRandomDataSet(size, new ArrayList<ColisionEvent>());
	}

	public ArrayList<ColisionEvent> getRandomDataSet(int size,
			ArrayList<ColisionEvent> except) {
		ArrayList<ColisionEvent> res = new ArrayList<ColisionEvent>();
		ArrayList<ColisionEvent> currentList = (ArrayList<ColisionEvent>) this
				.clone();

		for (ColisionEvent ex : except) {
			currentList.remove(ex);
		}
		System.out.println("Currentliste: " + currentList.size());
		while (res.size() < size) {
			int i = (int) (Math.random() * currentList.size());
			res.add(currentList.get(i));
			currentList.remove(i);
		}

		return res;
	}
}
