import java.util.ArrayList;

public class Network {
	private final ArrayList<ArrayList<GenericNetworkElement>> couches;

	public Network(int inputs, ArrayList<Integer> nbcouches,
			ArrayList<FonctionActivation> fas) {
		couches = new ArrayList<ArrayList<GenericNetworkElement>>();
		ArrayList<GenericNetworkElement> entrees = new ArrayList<GenericNetworkElement>();
		for (int i = 0; i < inputs; i++) {
			entrees.add(new Entry());
		}
		couches.add(entrees);
		for (int i = 1; i <= nbcouches.size(); i++) {
			ArrayList<GenericNetworkElement> coucheI = new ArrayList<GenericNetworkElement>();
			for (int j = 0; j < nbcouches.get(i - 1); j++) {
				Noeud n = new Noeud();
				n.setFonctionActivation(fas.get(i - 1));
				for (int k = 0; k < couches.get(couches.size() - 1).size(); k++) {
					Lien l = new Lien();
					l.setWeight((float) 1.0);
					l.getInputs().add(couches.get(couches.size() - 1).get(k));
					n.getInputs().add(l);
				}
				coucheI.add(n);
			}
			couches.add(coucheI);
		}

		ArrayList<GenericNetworkElement> sorties = new ArrayList<GenericNetworkElement>();
		for (int i = 0; i < couches.get(couches.size() - 1).size(); i++) {
			Output o = new Output();
			o.getInputs().add(couches.get(couches.size() - 1).get(i));
			sorties.add(o);
		}
		couches.add(sorties);
	}

	public ArrayList<Float> run(ArrayList<Float> entries) {
		ArrayList<Float> res = new ArrayList<Float>();
		for (int i = 0; i < entries.size(); i++) {
			((Entry) couches.get(0).get(i)).setValue(entries.get(i));
		}

		for (int i = 0; i < couches.get(couches.size() - 1).size(); i++) {
			res.add(couches.get(couches.size() - 1).get(i).getValue());
		}

		return res;
	}
}
