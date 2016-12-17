public class Lien extends GenericNetworkElement {
	private Float weight;

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	@Override
	public Float getValue() {
		return weight * inputs.get(0).getValue();
	}
}
