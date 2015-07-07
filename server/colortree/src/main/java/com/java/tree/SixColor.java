package com.java.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

public class SixColor {

	
	// se o restutado for de 3 a 5 deve setar o menor valor possivel
	public void newValueNosAltos(Integer nivel){
		List<NodeTree>nos = MoutTree.niveisArvore.get(nivel);
		
		int maxValue=0;
		for(NodeTree no: nos) {
			if(Integer.valueOf(no.getNewValue()) >= maxValue ) {
				maxValue = Integer.valueOf(no.getNewValue());
			}
		}
		
		for(NodeTree no: nos) {
			
			if(Integer.valueOf(no.getNewValue()) == maxValue) {
				int valuePai = Integer.valueOf(no.getNoPai().getNewValue());
				int valueFolha = -1;
				if(no.getFolhas() != null) {
					valueFolha = Integer.valueOf(no.getFolhas().get(0).getNewValue());
				}
				
				int valorNovo = 0;
				if(valorNovo != valuePai && valorNovo != valueFolha) {
					no.setNewValue(String.valueOf(0));
					continue;
				}
				valorNovo =1;
				if(valorNovo != valuePai && valorNovo != valueFolha) {
					no.setNewValue(String.valueOf(1));
					continue;
				}
				
				valorNovo =1;
				if(valorNovo != valuePai && valorNovo != valueFolha) {
					no.setNewValue(String.valueOf(2));
					continue;
				}
			}
		}
	}
	
	public String changeRoot(NodeTree pai) {
		if(pai.isRoot(pai) ) {
			if(Integer.valueOf(pai.getCurrentValue()) == 0 ) {
				return "1";
			}
			if(Integer.valueOf(pai.getCurrentValue()) == 1 ) {
				return "2";
			}
			if(Integer.valueOf(pai.getCurrentValue()) == 0 ) {
				return "0";
			}
		}
		return null;
	}
	
	public void shitDown(NodeTree pai) {
		//condicao de parada 
		if(pai.getFolhas() == null) {
			return;
		}
		
		//set o root com um valor de 0 a 5
		changeRoot(pai);
		List<NodeTree> filhos = pai.getFolhas();
		for(NodeTree no:filhos) {
			no.setNewValue(pai.getCurrentValue());
			System.out.println("No:"  +  no.getCurrentValue() + " Novo Valor:" + no.getNewValue());
			shitDown(MoutTree.arvore.get(no.getIdNo()));
		}
	}
	
	public void sixColorRecursive(NodeTree pai) {
		//condicao de parada para o algoritmo
		if(pai.getFolhas() == null) {
			//System.out.println(pai.getCurrentValue());
			return;
		}
		
		//set o root com um valor de 0 a 5
		if(pai.isRoot(pai) && Integer.parseInt(pai.getCurrentValue(), 2) >= 6) {
			//String value = String.format("%05d", Integer.parseInt(Integer.toBinaryString(randInt(0, 5))));
			pai.setNewValue("00000");
		}
		
		List<NodeTree> filhos = pai.getFolhas();
		
		for(NodeTree no:filhos) {
			//System.out.println("No:" + no.getCurrentValue());
			no.setNewValue(diffBinarios(pai.getCurrentValue(), no.getCurrentValue()));
			sixColorRecursive(MoutTree.arvore.get(no.getIdNo()));
		}
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public String diffBinarios(String bin1, String bin2) {
		try {
			char[] b1 = bin1.toCharArray();
			char[] b2 = bin2.toCharArray();
			
			System.out.println("b1:"+ String.valueOf(b1));
			System.out.println("b2:"+ String.valueOf(b2));
			
			int i;
			int position =-1;
			int numberDiff=0;
			for(i=bin1.length()-1; i >= 0; i--) {
				position += 1;
				if(b1[i] == b2[i]) {
					continue;
				}
				if(b1[i] != b2[i]) {
					numberDiff = Integer.valueOf(String.valueOf(b2[i]));
					break;
				}
			}
			
			String value = Integer.toBinaryString(position)+String.valueOf(numberDiff);
			System.out.println("" + String.format("%05d", Integer.parseInt(value)));
			System.out.println("\n");
			return String.format("%05d", Integer.parseInt(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void convertBinariesToDecimal() {
		for (Entry<Integer, NodeTree> entry : MoutTree.arvore.entrySet())
		{
			Long decimal = Long.parseLong(entry.getValue().getCurrentValue(), 2);
			entry.getValue().setCurrentValue(String.valueOf(decimal));
		}
	}
	
	private HashMap<Integer, List<NodeTree>> nosNivel;
	private Integer nivel;
	
	public HashMap<Integer, List<NodeTree>> getNosNivel() {
		return nosNivel;
	}
	public void setNosNivel(HashMap<Integer, List<NodeTree>> nosNivel) {
		this.nosNivel = nosNivel;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
}
