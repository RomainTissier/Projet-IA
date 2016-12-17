import java.util.ArrayList;

public class StaticTestNetwork {
	public static void test(float v1, float v2) {
		Entry x1 = new Entry();
		Entry x2 = new Entry();
		x1.setValue(v1);
		x2.setValue(v2);

		Lien w1 = new Lien(), w2 = new Lien();
		w1.getInputs().add(x1);
		w2.getInputs().add(x2);
		w1.setWeight((float) 0.9);
		w2.setWeight((float) 0.1);
		FonctionActivation fa = new FonctionActivation() {
			@Override
			public Float compute(Float input) {
				if (input > 0.5) {
					return (float) 1.0;
				}
				return (float) 0.0001;
			}
		};
		Noeud node = new Noeud();
		node.setFonctionActivation(fa);
		node.getInputs().add(w1);
		node.getInputs().add(w2);

		Output out = new Output();
		out.getInputs().add(node);
		System.out.println(node.getValue());
	}

	public static void test2() {
		ArrayList<Integer> ai = new ArrayList<Integer>();
		ai.add(1);
		ArrayList<FonctionActivation> faa = new ArrayList<FonctionActivation>();
		FonctionActivation fa = new FonctionActivation() {
			@Override
			public Float compute(Float input) {
				if (input > 0.5) {
					return (float) 1.0;
				}
				return (float) 0.0001;
			}
		};
		faa.add(fa);

		Network n = new Network(2, ai, faa);
		ArrayList<Float> entry = new ArrayList<Float>();
		entry.add((float) 0);
		entry.add((float) 0.5);
		ArrayList<Float> attendu = new ArrayList<Float>();
		attendu.add((float) 1);

		ArrayList<Float> res = n.run(entry);
		for (Float f : res)
			System.out.println("res: " + res);
		System.out.println("Erreur: " + erreurquadratique(res, attendu));
	}

	public static Float erreurquadratique(ArrayList<Float> result,
			ArrayList<Float> attendu) {
		Float res = (float) 0.0;
		for (int i = 0; i < result.size(); i++) {
			res += (attendu.get(i) - result.get(i))
					* (attendu.get(i) - result.get(i));
		}
		return (float) (res / 2);
	}
}
