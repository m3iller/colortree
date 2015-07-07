package com.java.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.java.TreeJson;

public class MoutTree {

	public static HashMap<Integer, NodeTree> arvore = new HashMap<Integer, NodeTree>();
	public static HashMap<Integer, List<NodeTree>> niveisArvore = new HashMap<Integer, List<NodeTree>>();

	public static void main(String[] args) {
		mainExecute();
	}
	
	public static TreeJson mainExecute() {
		NodeTree noPai = createTree2();
		mostraArvore();

		SixColor six = new SixColor();

		boolean condition;
		regraSix(noPai, six);

		condition = true;
		condition = sixto3(six, condition);

		for (Entry<Integer, NodeTree> entry : arvore.entrySet()) {
			if (entry.getValue().getNewValue() != null) {
				entry.getValue()
						.setOldValue(entry.getValue().getCurrentValue());
				entry.getValue()
						.setCurrentValue(entry.getValue().getNewValue());
				entry.getValue().setNewValue(null);
			}
		}
		mostraArvore();
		return HelperJson.convertMapToJson();
	}

	private static boolean sixto3(SixColor six, boolean condition) {
		while (condition) {
			int nivel = 2;
			int passo = 0;

			condition = false;
			for (Entry<Integer, NodeTree> entry : arvore.entrySet()) {

				
				if (entry.getValue().getNewValue() != null) {
					entry.getValue().setOldValue(
							entry.getValue().getCurrentValue());
					entry.getValue().setCurrentValue(
							entry.getValue().getNewValue());
					entry.getValue().setNewValue(null);
				}
				if (entry.getValue().getCurrentValue() != null
						&& Integer.valueOf(entry.getValue().getCurrentValue()) >= 3) {
					condition = true;
				}
			}

			if (passo == 0 && condition == false) {
				arvore.get(0).setNewValue(six.changeRoot(arvore.get(0)));
				break;
			}

			mostraArvore();

			six.shitDown(arvore.get(0));
			montarNiveis();
			six.newValueNosAltos(nivel);
			nivel++;
		}
		return condition;
	}

	public static void regraSix(NodeTree noPai, SixColor six) {
		boolean condition = true;
		while (condition) {
			condition = false;
			for (Entry<Integer, NodeTree> entry : arvore.entrySet()) {
				System.out.println(entry.getKey() + ":"
						+ entry.getValue().getCurrentValue() + "");

				if (entry.getValue().getNewValue() != null) {
					entry.getValue().setOldValue(
							entry.getValue().getCurrentValue());
					entry.getValue().setCurrentValue(
							entry.getValue().getNewValue());
					entry.getValue().setNewValue(null);
				}
				if (entry.getValue().getCurrentValue() != null
						&& Integer.valueOf(entry.getValue().getCurrentValue()) >= 111) {
					condition = true;
				}
			}
			if (condition) {
				six.sixColorRecursive(noPai);
			}
		}

		System.out.println("\n Apos diferencas entre os nos");
		mostraArvore();

		six.convertBinariesToDecimal();

		System.out.println("\n Converte para inteiro");
		mostraArvore();

		for (Entry<Integer, NodeTree> entry : arvore.entrySet()) {
			entry.getValue().setOldValue(null);
			entry.getValue().setNewValue(null);
		}
	}

	public static NodeTree createTree() {
		// TreeColor arvore = new TreeColor();
		List<NodeTree> list1 = new ArrayList<NodeTree>();

		// String.format("%05d",
		// Integer.parseInt(Integer.toBinaryString(randInt(0, 5))));;
		NodeTree noPai = new NodeTree();
		noPai.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(7))));
		noPai.setNivel(0);
		noPai.setPosition(0);

		NodeTree no1 = new NodeTree();
		no1.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(10))));
		no1.setNoPai(noPai);
		no1.setNivel(1);
		no1.setPosition(0);

		NodeTree no2 = new NodeTree();
		no2.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(0))));
		no2.setNoPai(no1);
		no2.setNivel(2);
		no2.setPosition(0);

		NodeTree no3 = new NodeTree();
		no3.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(12))));
		no3.setNoPai(noPai);
		no3.setPosition(1);
		no3.setNivel(1);

		noPai.addFolha(no1);
		noPai.addFolha(no3);
		no1.addFolha(no2);

		list1.add(noPai);
		list1.add(no1);
		list1.add(no2);
		list1.add(no3);
		
		int i = 0;
		for (NodeTree n : list1) {
			n.setIdNo(i);
			arvore.put(i, n);
			i++;
		}

		return noPai;
	}

	public static void mostraArvore() {
		List<NodeTree> nosNivel = new ArrayList<NodeTree>();

		for (Entry<Integer, NodeTree> entry : arvore.entrySet()) {
			nosNivel.add(entry.getValue());
		}

		Collections.sort(nosNivel, new Comparator<NodeTree>() {
			public int compare(NodeTree s1, NodeTree s2) {
				return s1.getNivel().compareTo(s2.getNivel());
			}
		});

		int nivel = 0;
		String printar = "";
		for (NodeTree no : nosNivel) {
			if (no.getNivel() != nivel) {
				printar = printar + " \n ";
			}
			printar = printar + " no: " + no.getCurrentValue();
			nivel = no.getNivel();
		}

		System.out.println(printar);
	}

	public static void montarNiveis() {
		List<NodeTree> nosNivel = new ArrayList<NodeTree>();

		for (Entry<Integer, NodeTree> entry : arvore.entrySet()) {
			nosNivel.add(entry.getValue());
		}

		Collections.sort(nosNivel, new Comparator<NodeTree>() {
			public int compare(NodeTree s1, NodeTree s2) {
				return s1.getNivel().compareTo(s2.getNivel());
			}
		});

		int nivel = 0;
		List<NodeTree> nNivel = new ArrayList<NodeTree>();
		for (NodeTree no : nosNivel) {
			if (no.getNivel() != nivel) {
				niveisArvore.put(nivel, nNivel);
				nNivel = new ArrayList<NodeTree>();
				nNivel.add(no);
			} else {
				nNivel.add(no);
			}
			nivel = no.getNivel();
		}

		niveisArvore.put(nivel, nNivel);
		System.out.println(niveisArvore.get(2));
	}
	
	public static NodeTree createTree2() {
		// TreeColor arvore = new TreeColor();
		List<NodeTree> list1 = new ArrayList<NodeTree>();
		// String.format("%05d",
		// Integer.parseInt(Integer.toBinaryString(randInt(0, 5))));;
		NodeTree noPai = new NodeTree();
		noPai.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(7))));
		noPai.setNivel(0);
		noPai.setPosition(0);

		NodeTree no1 = new NodeTree();
		no1.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(3))));
		no1.setNoPai(noPai);
		no1.setNivel(1);
		no1.setPosition(0);

		NodeTree no2 = new NodeTree();
		no2.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(4))));
		no2.setNoPai(noPai);
		no2.setNivel(1);
		no2.setPosition(1);

		NodeTree no3 = new NodeTree();
		no3.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(5))));
		no3.setNoPai(noPai);
		no3.setPosition(1);
		no3.setNivel(1);
		
		NodeTree no4 = new NodeTree();
		no4.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(2))));
		no4.setNoPai(no1);
		no4.setPosition(0);
		no4.setNivel(2);
		
		NodeTree no5 = new NodeTree();
		no5.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(2))));
		no5.setNoPai(no3);
		no5.setPosition(0);
		no5.setNivel(2);
		
		NodeTree no6 = new NodeTree();
		no6.setCurrentValue(String.format("%05d",
				Integer.parseInt(Integer.toBinaryString(3))));
		no6.setNoPai(no3);
		no6.setPosition(1);
		no6.setNivel(2);

		noPai.addFolha(no1);
		noPai.addFolha(no2);
		noPai.addFolha(no3);
		
		no3.addFolha(no5);
		no3.addFolha(no6);
		
		no1.addFolha(no4);
		no1.addFolha(no4);
		
		list1.add(noPai);
		list1.add(no1);
		list1.add(no2);
		list1.add(no3);
		list1.add(no4);
		list1.add(no5);
		list1.add(no6);
		
		int i = 0;
		for (NodeTree n : list1) {
			n.setIdNo(i);
			arvore.put(i, n);
			i++;
		}
		return noPai;
	}
}
