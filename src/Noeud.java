public class Noeud extends GenericNetworkElement {
	private FonctionActivation fonctionActivation;

	public FonctionActivation getFonctionActivation() {
		return fonctionActivation;
	}

	public void setFonctionActivation(FonctionActivation fonctionActivation) {
		this.fonctionActivation = fonctionActivation;
	}

	private Float somme() {
		Float res = (float) 0;
		for (GenericNetworkElement gne : inputs)
			res += gne.getValue();
		return res;
	}

	@Override
	public Float getValue() {
		return fonctionActivation.compute(somme());
	}

}
