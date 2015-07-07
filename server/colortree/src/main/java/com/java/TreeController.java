package com.java;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.tree.HelperJson;
import com.java.tree.MoutTree;
import com.java.tree.SixColor;

@RestController
@RequestMapping("/rest")
public class TreeController {
	
	@RequestMapping("/getTreeInicial")
	public TreeJson getTreeInicial() {
		
		MoutTree.createTree2();
		TreeJson tree  = HelperJson.convertMapToJson();
		return tree;
	}
	
	@RequestMapping("/getTreeFinal")
	public TreeJson getTreeFinal() {
		
		
		TreeJson tree = MoutTree.mainExecute();
		return tree;
	}
	
	@RequestMapping("/getSixColor")
	public TreeJson getSixColor() {
		MoutTree.regraSix(MoutTree.createTree2(), new SixColor());
		return HelperJson.convertMapToJson();
	}
	
}