public class Entry extends GenericNetworkElement {

	private Float value;

	public Entry() {
		super();
	}

	public Entry(Float value) {
		super();
		this.value = value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	@Override
	public Float getValue() {
		return value;
	}

}
