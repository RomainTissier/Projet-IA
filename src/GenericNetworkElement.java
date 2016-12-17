import java.util.ArrayList;

public abstract class GenericNetworkElement {
	protected ArrayList<GenericNetworkElement> inputs;

	public GenericNetworkElement() {
		inputs = new ArrayList<GenericNetworkElement>();
	}

	public abstract Float getValue();

	public ArrayList<GenericNetworkElement> getInputs() {
		return inputs;
	}
}
