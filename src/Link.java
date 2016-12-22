/**
 * Class representing a Link between two neuron
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 * 
 */
public class Link {
	private Double weight;
	private Neuron origin;
	private Neuron destination;

	/**
	 * Constructor initializing a link with a random weight within -1 and 1
	 */
	public Link() {
		weight = Math.random() * 2 - 1;
	}

	/**
	 * Getter returning the weight of the link
	 * 
	 * @return the weight of the link
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * Setter updating the weight of the link
	 * 
	 * @param weight
	 *            new weight value
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * Getter returning the origin neuron
	 * 
	 * @return the origin neuron
	 */
	public Neuron getOrigin() {
		return origin;
	}

	/**
	 * Setter updating the origin neuron
	 * 
	 * @param origin
	 *            new origin neuron
	 */
	public void setOrigin(Neuron origin) {
		this.origin = origin;
	}

	/**
	 * Getter returning the destination neuron
	 * 
	 * @return the destination neuron
	 */
	public Neuron getDestination() {
		return destination;
	}

	/**
	 * Setter updating the destination neuron
	 * 
	 * @param destination
	 *            new destination neuron
	 */
	public void setDestination(Neuron destination) {
		this.destination = destination;
	}

}
