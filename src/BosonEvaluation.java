import java.io.Serializable;

/**
 * Class representing the Boson network Evaluation structure, it inherits from
 * Evaluation, which contains generic indicators
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 * 
 */
public class BosonEvaluation extends Evaluation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer infZero;
	private Integer supOne;
	private Integer quartOne;
	private Integer quartTwo;
	private Integer quartThree;
	private Integer quartFour;

	/**
	 * Constructor initializing the Boson evaluation object
	 */
	public BosonEvaluation() {
		super();
		infZero = 0;
		supOne = 0;
		quartOne = 0;
		quartTwo = 0;
		quartThree = 0;
		quartFour = 0;
	}

	/**
	 * Increment the inferior or zero variable
	 */
	public void incrementInfZero() {
		infZero++;
	}

	/**
	 * Increment the supOne variable
	 */
	public void incrementSupOne() {
		supOne++;
	}

	/**
	 * Increment the quartOne variable
	 */
	public void incrementQuartOne() {
		quartOne++;
	}

	/**
	 * Increment the quartTwo variable
	 */
	public void incrementQuartTwo() {
		quartTwo++;
	}

	/**
	 * Increment the quartThree variable
	 */
	public void incrementQuartThree() {
		quartThree++;
	}

	/**
	 * Increment the quartFour variable
	 */
	public void incrementQuartFour() {
		quartFour++;
	}

	/**
	 * Get the infZero variable value
	 * 
	 * @return infZero counter value
	 */
	public Integer getInfZero() {
		return infZero;
	}

	/**
	 * Get the supOne variable value
	 * 
	 * @return supOne counter value
	 */
	public Integer getSupOne() {
		return supOne;
	}

	/**
	 * Get the quartOne variable value
	 * 
	 * @return quartOne counter value
	 */
	public Integer getQuartOne() {
		return quartOne;
	}

	/**
	 * Get the quartTwo variable value
	 * 
	 * @return quartTwo counter value
	 */
	public Integer getQuartTwo() {
		return quartTwo;
	}

	/**
	 * Get the quartThree variable value
	 * 
	 * @return quartThree counter value
	 */
	public Integer getQuartThree() {
		return quartThree;
	}

	/**
	 * Get the quartFour variable value
	 * 
	 * @return quartFour counter value
	 */
	public Integer getQuartFour() {
		return quartFour;
	}

	@Override
	public String toString() {
		String res = super.toString();
		res += "Range:\n";
		res += "\t[-inf;0[ => " + infZero + "\n";
		res += "\t[0;0.25[ => " + quartOne + "\n";
		res += "\t[0.25;0.5[ => " + quartTwo + "\n";
		res += "\t[0.5;0.75[ => " + quartThree + "\n";
		res += "\t[0.75;1] => " + quartFour + "\n";
		res += "\t]1;+inf[ => " + supOne + "\n";
		return res;
	}
}
