package cn.com.tsjx.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeData implements java.io.Serializable{
    private static final long serialVersionUID = 8270962482533287171L;
    
    private Long id;
    private String text;
    private Map<String, Object> state = new HashMap<String, Object>();
	private List<TreeData> children = new ArrayList<TreeData>();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public List<TreeData> getChildren() {
        return children;
    }
    public void setChildren(List<TreeData> children) {
        this.children = children;
    }
    public Map<String, Object> getState() {
        return state;
    }
    public void setState(Map<String, Object> state) {
        this.state = state;
    }	
}
