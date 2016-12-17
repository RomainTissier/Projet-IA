import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		StaticTestNetwork.test2();
	}

	public void dataSetCreationTest() {
		System.out.println("Chargement du fichier: ");
		ColisionEventList cel = new ColisionEventList(new File("training.csv"));
		System.out.println(cel.size() + " lignes chargés");
		ArrayList<ColisionEvent> training = cel.getRandomDataSet(200000);
		ArrayList<ColisionEvent> test = cel.getRandomDataSet(50000, training);
		System.out.println("END");
	}

	public void testStaticNetwork() {
		StaticTestNetwork.test((float) 0.4, 12);
	}
}
