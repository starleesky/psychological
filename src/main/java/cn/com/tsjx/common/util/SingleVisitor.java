package cn.com.tsjx.common.util;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.VisitorSupport;

import cn.com.tsjx.common.constants.BizExceptionConstant;
import cn.com.tsjx.common.util.exception.BizException;
import cn.com.tsjx.common.util.lang.StringUtil;

/*import org.dom4j.Element;
import org.dom4j.VisitorSupport;*/

/**
 * 从一个XML中,迅速取得该节点的值,只能用简单如果用重复的,则不能用
 * @author crazy_cabbage
 *
 */
public class SingleVisitor extends VisitorSupport {
	private Map<String, String> nodeContents;

	public SingleVisitor(Map<String, String> nodeNames) {
		if (nodeNames == null) {
			throw new BizException(BizExceptionConstant.DOM4J_VISTOR_ERROR, StringUtil.EMPTY);
		}
		this.nodeContents = nodeNames;
	}
	public SingleVisitor(String nodeName) {
	     nodeContents = new HashMap<String, String>();
	     nodeContents.put(nodeName,StringUtil.EMPTY);
	}

	public Map<String, String> getNodeContents() {
		return nodeContents;
	}

	@Override
	public void visit(Element node) {
		if (nodeContents.containsKey(node.getName())) {
			nodeContents.put(node.getName(), node.getText());
		}
	}

}
