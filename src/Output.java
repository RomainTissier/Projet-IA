public class Output extends GenericNetworkElement {

	@Override
	public Float getValue() {
		Float res = (float) 0;
		for (GenericNetworkElement gne : inputs)
			res += gne.getValue();
		return res;
	}

}
