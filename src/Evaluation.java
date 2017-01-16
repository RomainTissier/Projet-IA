import java.io.Serializable;

/**
 * Structure representing the evaluation of a network by embedding indicators
 * 
 * @authors Cuartero Jean-Louis, Delpech Marc, Tissier Romain
 * 
 */
public class Evaluation implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer truePositive;
	private Integer trueNegative;
	private Integer falsePositive;
	private Integer falseNegative;

	/**
	 * Constructor initializing indicator to zero
	 */
	public Evaluation() {
		truePositive = 0;
		trueNegative = 0;
		falsePositive = 0;
		falseNegative = 0;
	}

	/**
	 * Retourne le taux de bonne classification
	 * 
	 * @return taux de bonne classification
	 */
	public Double getTauxBonneClassification() {
		return (double) (truePositive + trueNegative)
				/ ((double) (truePositive + trueNegative + falsePositive + falseNegative));
	}

	/**
	 * Increment the true positive indicator
	 */
	public void incrementTruePositive() {
		truePositive++;
	}

	/**
	 * Increment the true negative indicator
	 */
	public void incrementTrueNegative() {
		trueNegative++;
	}

	/**
	 * Increment the false positive indicator
	 */
	public void incrementFalsePositive() {
		falsePositive++;
	}

	/**
	 * Increment the false negative indicator
	 */
	public void incrementFalseNegative() {
		falseNegative++;
	}

	/**
	 * Getter returning the true positive indicator value
	 * 
	 * @return the true positive indicator value
	 */
	public Integer getTruePositive() {
		return truePositive;
	}

	/**
	 * Getter returning the true negative indicator value
	 * 
	 * @return the true negative indicator value
	 */
	public Integer getTrueNegative() {
		return trueNegative;
	}

	/**
	 * Getter returning the false positive indicator value
	 * 
	 * @return the false positive indicator value
	 */
	public Integer getFalsePositive() {
		return falsePositive;
	}

	/**
	 * Getter returning the false negative indicator value
	 * 
	 * @return the false negative indicator value
	 */
	public Integer getFalseNegative() {
		return falseNegative;
	}

	@Override
	public String toString() {
		return "TP: " + truePositive + " TN: " + trueNegative + " FP: "
				+ falsePositive + " FN: " + falseNegative + "\n";
	}

}
