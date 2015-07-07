package com.java.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.java.TreeJson;

public class HelperJson {
	
	public static TreeJson convertMapToJson() {
		TreeJson no = convertObjetJson( MoutTree.arvore.get(0));
		return no;
	}

	public static TreeJson convertObjetJson(NodeTree noTree) {
		TreeJson no = new TreeJson();
		List<NodeTree> filhos = noTree.getFolhas();
		List<TreeJson> doidera = new ArrayList<TreeJson>();
		
		no.setId(String.valueOf(noTree.getIdNo()));
		no.setData("");
		no.setName(noTree.getCurrentValue());
		
		if(filhos != null) {
			for(NodeTree nf: filhos) {
				nf = MoutTree.arvore.get(nf.getIdNo());
				System.out.println(nf.getIdNo());
				doidera.add(convertObjetJson(nf));
				no.setChildren(doidera);
			}
		} else {
			return no;
		}
		return no;
	}
	
	public HashMap<Integer, List<NodeTree>> convertJsonToMap() {
		
		return null;
	}
}
