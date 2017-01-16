import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing a Colision Event
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 * 
 */
public class ColisionEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private Double DER_mass_MMC;
	private Double DER_mass_transverse_met_lep;
	private Double DER_mass_vis;
	private Double DER_pt_h;
	private Double DER_deltaeta_jet_jet;
	private Double DER_mass_jet_jet;
	private Double DER_prodeta_jet_jet;
	private Double DER_deltar_tau_lep;
	private Double DER_pt_tot;
	private Double DER_sum_pt;
	private Double DER_pt_ratio_lep_tau;
	private Double DER_met_phi_centrality;
	private Double DER_lep_eta_centrality;
	private Double PRI_tau_pt;
	private Double PRI_tau_eta;
	private Double PRI_tau_phi;
	private Double PRI_lep_pt;
	private Double PRI_lep_eta;
	private Double PRI_lep_phi;
	private Double PRI_met;
	private Double PRI_met_phi;
	private Double PRI_met_sumet;
	private Double PRI_jet_num;
	private Double PRI_jet_leading_pt;
	private Double PRI_jet_leading_eta;
	private Double PRI_jet_leading_phi;
	private Double PRI_jet_subleading_pt;
	private Double PRI_jet_subleading_eta;
	private Double PRI_jet_subleading_phi;
	private Double PRI_jet_all_pt;

	private Boolean interest;

	/**
	 * Getter returning the interest attributes value
	 * 
	 * @return the interest attributes value
	 */
	public Boolean getInterest() {
		return interest;
	}

	/**
	 * Setter updating the interest value
	 * 
	 * @param interest
	 *            new value
	 */
	public void setInterest(Boolean interest) {
		this.interest = interest;
	}

	/**
	 * Getter returning the DER_mass_MMC attributes value
	 * 
	 * @return the DER_mass_MMC attributes value
	 */
	public Double getDER_mass_MMC() {
		return DER_mass_MMC;
	}

	/**
	 * Setter updating the dER_mass_MMC value
	 * 
	 * @param dER_mass_MMC
	 *            new value
	 */
	public void setDER_mass_MMC(Double dER_mass_MMC) {
		DER_mass_MMC = dER_mass_MMC;
	}

	/**
	 * Getter returning the DER_mass_transverse_met_lep attributes value
	 * 
	 * @return the DER_mass_transverse_met_lep attributes value
	 */
	public Double getDER_mass_transverse_met_lep() {
		return DER_mass_transverse_met_lep;
	}

	/**
	 * Setter updating the DER_mass_transverse_met_lep value
	 * 
	 * @param dER_mass_transverse_met_lep
	 *            new value
	 */
	public void setDER_mass_transverse_met_lep(
			Double dER_mass_transverse_met_lep) {
		DER_mass_transverse_met_lep = dER_mass_transverse_met_lep;
	}

	/**
	 * Getter returning the DER_mass_vis attributes value
	 * 
	 * @return the DER_mass_vis attributes value
	 */
	public Double getDER_mass_vis() {
		return DER_mass_vis;
	}

	/**
	 * Setter updating the DER_mass_vis value
	 * 
	 * @param dER_mass_vis
	 *            new value
	 */
	public void setDER_mass_vis(Double dER_mass_vis) {
		DER_mass_vis = dER_mass_vis;
	}

	/**
	 * Getter returning the DER_pt_h attributes value
	 * 
	 * @return the DER_pt_h attributes value
	 */
	public Double getDER_pt_h() {
		return DER_pt_h;
	}

	/**
	 * Setter updating the DER_pt_h value
	 * 
	 * @param dER_pt_h
	 *            new value
	 */
	public void setDER_pt_h(Double dER_pt_h) {
		DER_pt_h = dER_pt_h;
	}

	/**
	 * Getter returning the DER_deltaeta_jet_jet attributes value
	 * 
	 * @return the DER_deltaeta_jet_jet attributes value
	 */
	public Double getDER_deltaeta_jet_jet() {
		return DER_deltaeta_jet_jet;
	}

	/**
	 * Setter updating the DER_deltaeta_jet_jet value
	 * 
	 * @param dER_deltaeta_jet_jet
	 *            new value
	 */
	public void setDER_deltaeta_jet_jet(Double dER_deltaeta_jet_jet) {
		DER_deltaeta_jet_jet = dER_deltaeta_jet_jet;
	}

	/**
	 * Getter returning the DER_mass_jet_jet attributes value
	 * 
	 * @return the DER_mass_jet_jet attributes value
	 */
	public Double getDER_mass_jet_jet() {
		return DER_mass_jet_jet;
	}

	/**
	 * Setter updating the DER_mass_jet_jet value
	 * 
	 * @param dER_mass_jet_jet
	 *            new value
	 */
	public void setDER_mass_jet_jet(Double dER_mass_jet_jet) {
		DER_mass_jet_jet = dER_mass_jet_jet;
	}

	/**
	 * Getter returning the DER_prodeta_jet_jet attributes value
	 * 
	 * @return the DER_prodeta_jet_jet attributes value
	 */
	public Double getDER_prodeta_jet_jet() {
		return DER_prodeta_jet_jet;
	}

	/**
	 * Setter updating the DER_prodeta_jet_jet value
	 * 
	 * @param dER_prodeta_jet_jet
	 *            new value
	 */
	public void setDER_prodeta_jet_jet(Double dER_prodeta_jet_jet) {
		DER_prodeta_jet_jet = dER_prodeta_jet_jet;
	}

	/**
	 * Getter returning the DER_deltar_tau_lep attributes value
	 * 
	 * @return the DER_deltar_tau_lep attributes value
	 */
	public Double getDER_deltar_tau_lep() {
		return DER_deltar_tau_lep;
	}

	/**
	 * Setter updating the DER_deltar_tau_lep value
	 * 
	 * @param dER_deltar_tau_lep
	 *            new value
	 */
	public void setDER_deltar_tau_lep(Double dER_deltar_tau_lep) {
		DER_deltar_tau_lep = dER_deltar_tau_lep;
	}

	/**
	 * Getter returning the DER_pt_tot attributes value
	 * 
	 * @return the DER_pt_tot attributes value
	 */
	public Double getDER_pt_tot() {
		return DER_pt_tot;
	}

	/**
	 * Setter updating the DER_pt_tot value
	 * 
	 * @param dER_pt_tot
	 *            new value
	 */
	public void setDER_pt_tot(Double dER_pt_tot) {
		DER_pt_tot = dER_pt_tot;
	}

	/**
	 * Getter returning the DER_sum_pt attributes value
	 * 
	 * @return the DER_sum_pt attributes value
	 */
	public Double getDER_sum_pt() {
		return DER_sum_pt;
	}

	/**
	 * Setter updating the DER_sum_pt value
	 * 
	 * @param dER_sum_pt
	 *            new value
	 */
	public void setDER_sum_pt(Double dER_sum_pt) {
		DER_sum_pt = dER_sum_pt;
	}

	/**
	 * Getter returning the DER_pt_ratio_lep_tau attributes value
	 * 
	 * @return the DER_pt_ratio_lep_tau attributes value
	 */
	public Double getDER_pt_ratio_lep_tau() {
		return DER_pt_ratio_lep_tau;
	}

	/**
	 * Setter updating the DER_pt_ratio_lep_tau value
	 * 
	 * @param dER_pt_ratio_lep_tau
	 *            new value
	 */
	public void setDER_pt_ratio_lep_tau(Double dER_pt_ratio_lep_tau) {
		DER_pt_ratio_lep_tau = dER_pt_ratio_lep_tau;
	}

	/**
	 * Getter returning the DER_met_phi_centrality attributes value
	 * 
	 * @return the DER_met_phi_centrality attributes value
	 */
	public Double getDER_met_phi_centrality() {
		return DER_met_phi_centrality;
	}

	/**
	 * Setter updating the DER_met_phi_centrality value
	 * 
	 * @param dER_met_phi_centrality
	 *            new value
	 */
	public void setDER_met_phi_centrality(Double dER_met_phi_centrality) {
		DER_met_phi_centrality = dER_met_phi_centrality;
	}

	/**
	 * Getter returning the DER_lep_eta_centrality attributes value
	 * 
	 * @return the DER_lep_eta_centrality attributes value
	 */
	public Double getDER_lep_eta_centrality() {
		return DER_lep_eta_centrality;
	}

	/**
	 * Setter updating the DER_lep_eta_centrality value
	 * 
	 * @param dER_lep_eta_centrality
	 *            new value
	 */
	public void setDER_lep_eta_centrality(Double dER_lep_eta_centrality) {
		DER_lep_eta_centrality = dER_lep_eta_centrality;
	}

	/**
	 * Getter returning the PRI_tau_pt attributes value
	 * 
	 * @return the PRI_tau_pt attributes value
	 */
	public Double getPRI_tau_pt() {
		return PRI_tau_pt;
	}

	/**
	 * Setter updating the PRI_tau_pt value
	 * 
	 * @param pRI_tau_pt
	 *            new value
	 */
	public void setPRI_tau_pt(Double pRI_tau_pt) {
		PRI_tau_pt = pRI_tau_pt;
	}

	/**
	 * Getter returning the PRI_tau_eta attributes value
	 * 
	 * @return the PRI_tau_eta attributes value
	 */
	public Double getPRI_tau_eta() {
		return PRI_tau_eta;
	}

	/**
	 * Setter updating the PRI_tau_eta value
	 * 
	 * @param pRI_tau_eta
	 *            new value
	 */
	public void setPRI_tau_eta(Double pRI_tau_eta) {
		PRI_tau_eta = pRI_tau_eta;
	}

	/**
	 * Getter returning the PRI_tau_phi attributes value
	 * 
	 * @return the PRI_tau_phi attributes value
	 */
	public Double getPRI_tau_phi() {
		return PRI_tau_phi;
	}

	/**
	 * Setter updating the PRI_tau_phi value
	 * 
	 * @param pRI_tau_phi
	 *            new value
	 */
	public void setPRI_tau_phi(Double pRI_tau_phi) {
		PRI_tau_phi = pRI_tau_phi;
	}

	/**
	 * Getter returning the PRI_lep_pt attributes value
	 * 
	 * @return the PRI_lep_pt attributes value
	 */
	public Double getPRI_lep_pt() {
		return PRI_lep_pt;
	}

	/**
	 * Setter updating the PRI_lep_pt value
	 * 
	 * @param pRI_lep_pt
	 *            new value
	 */
	public void setPRI_lep_pt(Double pRI_lep_pt) {
		PRI_lep_pt = pRI_lep_pt;
	}

	/**
	 * Getter returning the PRI_lep_eta attributes value
	 * 
	 * @return the PRI_lep_eta attributes value
	 */
	public Double getPRI_lep_eta() {
		return PRI_lep_eta;
	}

	/**
	 * Setter updating the PRI_lep_eta value
	 * 
	 * @param pRI_lep_eta
	 *            new value
	 */
	public void setPRI_lep_eta(Double pRI_lep_eta) {
		PRI_lep_eta = pRI_lep_eta;
	}

	/**
	 * Getter returning the PRI_lep_phi attributes value
	 * 
	 * @return the PRI_lep_phi attributes value
	 */
	public Double getPRI_lep_phi() {
		return PRI_lep_phi;
	}

	/**
	 * Setter updating the PRI_lep_phi value
	 * 
	 * @param pRI_lep_phi
	 *            new value
	 */
	public void setPRI_lep_phi(Double pRI_lep_phi) {
		PRI_lep_phi = pRI_lep_phi;
	}

	/**
	 * Getter returning the PRI_met attributes value
	 * 
	 * @return the PRI_met attributes value
	 */
	public Double getPRI_met() {
		return PRI_met;
	}

	/**
	 * Setter updating the PRI_met value
	 * 
	 * @param pRI_met
	 *            new value
	 */
	public void setPRI_met(Double pRI_met) {
		PRI_met = pRI_met;
	}

	/**
	 * Getter returning the PRI_met_phi attributes value
	 * 
	 * @return the PRI_met_phi attributes value
	 */
	public Double getPRI_met_phi() {
		return PRI_met_phi;
	}

	/**
	 * Setter updating the PRI_met_phi value
	 * 
	 * @param pRI_met_phi
	 *            new value
	 */
	public void setPRI_met_phi(Double pRI_met_phi) {
		PRI_met_phi = pRI_met_phi;
	}

	/**
	 * Getter returning the PRI_met_sumet attributes value
	 * 
	 * @return the PRI_met_sumet attributes value
	 */
	public Double getPRI_met_sumet() {
		return PRI_met_sumet;
	}

	/**
	 * Setter updating the PRI_met_sumet value
	 * 
	 * @param pRI_met_sumet
	 *            new value
	 */
	public void setPRI_met_sumet(Double pRI_met_sumet) {
		PRI_met_sumet = pRI_met_sumet;
	}

	/**
	 * Getter returning the PRI_jet_num attributes value
	 * 
	 * @return the PRI_jet_num attributes value
	 */
	public Double getPRI_jet_num() {
		return PRI_jet_num;
	}

	/**
	 * Setter updating the PRI_jet_num value
	 * 
	 * @param pRI_jet_num
	 *            new value
	 */
	public void setPRI_jet_num(Double pRI_jet_num) {
		PRI_jet_num = pRI_jet_num;
	}

	/**
	 * Getter returning the PRI_jet_leading_pt attributes value
	 * 
	 * @return the PRI_jet_leading_pt attributes value
	 */
	public Double getPRI_jet_leading_pt() {
		return PRI_jet_leading_pt;
	}

	/**
	 * Setter updating the PRI_jet_leading_pt value
	 * 
	 * @param pRI_jet_leading_pt
	 *            new value
	 */
	public void setPRI_jet_leading_pt(Double pRI_jet_leading_pt) {
		PRI_jet_leading_pt = pRI_jet_leading_pt;
	}

	/**
	 * Getter returning the PRI_jet_leading_eta attributes value
	 * 
	 * @return the PRI_jet_leading_eta attributes value
	 */
	public Double getPRI_jet_leading_eta() {
		return PRI_jet_leading_eta;
	}

	/**
	 * Setter updating the PRI_jet_leading_eta value
	 * 
	 * @param pRI_jet_leading_eta
	 *            new value
	 */
	public void setPRI_jet_leading_eta(Double pRI_jet_leading_eta) {
		PRI_jet_leading_eta = pRI_jet_leading_eta;
	}

	/**
	 * Getter returning the PRI_jet_leading_phi attributes value
	 * 
	 * @return the PRI_jet_leading_phi attributes value
	 */
	public Double getPRI_jet_leading_phi() {
		return PRI_jet_leading_phi;
	}

	/**
	 * Setter updating the PRI_jet_leading_phi value
	 * 
	 * @param pRI_jet_leading_phi
	 *            new value
	 */
	public void setPRI_jet_leading_phi(Double pRI_jet_leading_phi) {
		PRI_jet_leading_phi = pRI_jet_leading_phi;
	}

	/**
	 * Getter returning the PRI_jet_subleading_pt attributes value
	 * 
	 * @return the PRI_jet_subleading_pt attributes value
	 */
	public Double getPRI_jet_subleading_pt() {
		return PRI_jet_subleading_pt;
	}

	/**
	 * Setter updating the PRI_jet_subleading_pt value
	 * 
	 * @param pRI_jet_subleading_pt
	 *            new value
	 */
	public void setPRI_jet_subleading_pt(Double pRI_jet_subleading_pt) {
		PRI_jet_subleading_pt = pRI_jet_subleading_pt;
	}

	/**
	 * Getter returning the PRI_jet_subleading_eta attributes value
	 * 
	 * @return the PRI_jet_subleading_eta attributes value
	 */
	public Double getPRI_jet_subleading_eta() {
		return PRI_jet_subleading_eta;
	}

	/**
	 * Setter updating the PRI_jet_subleading_eta value
	 * 
	 * @param pRI_jet_subleading_eta
	 *            new value
	 */
	public void setPRI_jet_subleading_eta(Double pRI_jet_subleading_eta) {
		PRI_jet_subleading_eta = pRI_jet_subleading_eta;
	}

	/**
	 * Getter returning the PRI_jet_subleading_phi attributes value
	 * 
	 * @return the PRI_jet_subleading_phi attributes value
	 */
	public Double getPRI_jet_subleading_phi() {
		return PRI_jet_subleading_phi;
	}

	/**
	 * Setter updating the PRI_jet_subleading_phi value
	 * 
	 * @param pRI_jet_subleading_phi
	 *            new value
	 */
	public void setPRI_jet_subleading_phi(Double pRI_jet_subleading_phi) {
		PRI_jet_subleading_phi = pRI_jet_subleading_phi;
	}

	/**
	 * Getter returning the PRI_jet_all_pt attributes value
	 * 
	 * @return the PRI_jet_all_pt attributes value
	 */
	public Double getPRI_jet_all_pt() {
		return PRI_jet_all_pt;
	}

	/**
	 * Setter updating the PRI_jet_all_pt value
	 * 
	 * @param pRI_jet_all_pt
	 *            new value
	 */
	public void setPRI_jet_all_pt(Double pRI_jet_all_pt) {
		PRI_jet_all_pt = pRI_jet_all_pt;
	}

	/**
	 * Method returning the event in the CSV original format
	 * 
	 * @return the event in the CSV original format
	 */
	public String tocsv() {
		return DER_mass_MMC + "," + DER_mass_transverse_met_lep + ","
				+ DER_mass_vis + "," + DER_pt_h + "," + DER_deltaeta_jet_jet
				+ "," + DER_mass_jet_jet + "," + DER_prodeta_jet_jet + ","
				+ DER_deltar_tau_lep + "," + DER_pt_tot + "," + DER_sum_pt
				+ "," + DER_pt_ratio_lep_tau + "," + DER_met_phi_centrality
				+ "," + DER_lep_eta_centrality + "," + PRI_tau_pt + ","
				+ PRI_tau_eta + "," + PRI_tau_phi + "," + PRI_lep_pt + ","
				+ PRI_lep_eta + "," + PRI_lep_phi + "," + PRI_met + ","
				+ PRI_met_phi + "," + PRI_met_sumet + "," + PRI_jet_num + ","
				+ PRI_jet_leading_pt + "," + PRI_jet_leading_eta + ","
				+ PRI_jet_leading_phi + "," + PRI_jet_subleading_pt + ","
				+ PRI_jet_subleading_eta + "," + PRI_jet_subleading_phi + ","
				+ PRI_jet_all_pt + "\n";
	}

	/**
	 * Method returning a vector with the collision event vector
	 * 
	 * @return the collision event vector
	 */
	public ArrayList<Double> toVector() {
		ArrayList<Double> res = new ArrayList<Double>();
		res.add(DER_mass_MMC);
		res.add(DER_mass_transverse_met_lep);
		res.add(DER_mass_vis);
		res.add(DER_pt_h);
		res.add(DER_deltaeta_jet_jet);
		res.add(DER_mass_jet_jet);
		res.add(DER_prodeta_jet_jet);
		res.add(DER_deltar_tau_lep);
		res.add(DER_pt_tot);
		res.add(DER_sum_pt);
		res.add(DER_pt_ratio_lep_tau);
		res.add(DER_met_phi_centrality);
		res.add(DER_lep_eta_centrality);
		res.add(PRI_tau_pt);
		res.add(PRI_tau_eta);
		res.add(PRI_tau_phi);
		res.add(PRI_lep_pt);
		res.add(PRI_lep_eta);
		res.add(PRI_lep_phi);
		res.add(PRI_met);
		res.add(PRI_met_phi);
		res.add(PRI_met_sumet);
		res.add(PRI_jet_num);
		res.add(PRI_jet_leading_pt);
		res.add(PRI_jet_leading_eta);
		res.add(PRI_jet_leading_phi);
		res.add(PRI_jet_subleading_pt);
		res.add(PRI_jet_subleading_eta);
		res.add(PRI_jet_subleading_phi);
		res.add(PRI_jet_all_pt);
		return res;
	}
}
