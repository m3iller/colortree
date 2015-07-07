package com.java.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NodeTree implements Comparator<NodeTree> {

	private String currentValue;
	private String oldValue;
	private String newValue;
	private Integer idNo;
	private List<NodeTree> folhas;
	private NodeTree noPai;
	
	private Integer nivel;
	private Integer position;
	private Boolean changeValue;
	
	public Boolean getChangeValue() {
		return changeValue;
	}

	public void setChangeValue(Boolean changeValue) {
		this.changeValue = changeValue;
	}

	public void addFolha(NodeTree no) {
		if(folhas == null) {
			folhas = new ArrayList<NodeTree>();
		}
		folhas.add(no);
	}
	
	public boolean isRoot(NodeTree node) {
		if(node != null && node.noPai == null){
			return true;
		} else {
			return false;
		}
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public List<NodeTree> getFolhas() {
		return folhas;
	}

	public void setFolhas(List<NodeTree> folhas) {
		this.folhas = folhas;
	}

	public NodeTree getNoPai() {
		return noPai;
	}

	public void setNoPai(NodeTree noPai) {
		this.noPai = noPai;
	}
	
	public Integer getIdNo() {
		return idNo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}

	public int compare(NodeTree o1,NodeTree o2) {
		return o1.getNivel().compareTo(o2.getNivel());
	}

}
