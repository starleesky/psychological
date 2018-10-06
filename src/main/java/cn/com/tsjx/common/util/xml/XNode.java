package cn.com.tsjx.common.util.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class XNode {

	private final Node node;
	private final String name;
	private final String body;
	private final Properties attributes;
	private final XPathParser xpathParser;

	XNode(Node node, XPathParser xpathParser) {
		this.xpathParser = xpathParser;
		this.node = node;
		this.name = node.getNodeName();
		this.body = this.parseBody(node);
		this.attributes = this.parseAttributes(node);
	}

	/**
	 * 解析节点字符串形式的内容
	 * @param node
	 * @return
	 */
	private String parseBody(Node node) {
		String data = this.getBodyData(node);
		if (data == null) {
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node child = children.item(i);
				data = this.getBodyData(child);
				if (data != null) {
					break;
				}
			}
		}
		return data;
	}

	private String getBodyData(Node child) {
		if (child.getNodeType() == Node.CDATA_SECTION_NODE || child.getNodeType() == Node.TEXT_NODE) {
			return ((CharacterData) child).getData();
		}
		return null;
	}

	/**
	 * 解析节点属性
	 * @param node 节点对象
	 * @return
	 */
	private Properties parseAttributes(Node node) {
		Properties attributes = new Properties();
		NamedNodeMap attributeNodes = node.getAttributes();
		if (attributeNodes != null) {
			for (int i = 0; i < attributeNodes.getLength(); i++) {
				Node attribute = attributeNodes.item(i);
				attributes.put(attribute.getNodeName(), attribute.getNodeValue());
			}
		}
		return attributes;
	}

	/**
	 * 返回当前节点的父节点对象，如果没有父节点返回空。
	 * @return
	 */
	public XNode getParent() {
		Node parent = node.getParentNode();
		if (parent == null || !(parent instanceof Element)) {
			return null;
		} else {
			return new XNode(parent, xpathParser);
		}
	}

	/**
	 * 返回当前节点的前一个节点
	 * @return
	 */
	public XNode getPreviousNode() {
		Node child = node.getPreviousSibling();
		if (child != null) {
			if (child.getNodeType() != Node.ELEMENT_NODE) {
				child = child.getPreviousSibling();
			}
			if (child != null) {
				return new XNode(child, xpathParser);
			}
		}
		return null;
	}

	/**
	 * 返回当前节点的前一个节点
	 * @return
	 */
	public XNode getNextNode() {
		Node child = node.getNextSibling();
		if (child != null) {
			if (child.getNodeType() != Node.ELEMENT_NODE) {
				child = child.getNextSibling();
			}
			if (child != null) {
				return new XNode(child, xpathParser);
			}
		}
		return null;
	}

	/**
	 * 返回当前节点的子节点
	 * @return
	 */
	public List<XNode> getChildren() {
		List<XNode> children = new ArrayList<XNode>();
		NodeList nodeList = node.getChildNodes();
		if (nodeList != null) {
			for (int i = 0, n = nodeList.getLength(); i < n; i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					children.add(new XNode(node, xpathParser));
				}
			}
		}
		return children;
	}

	/**
	 * 返回第一个子节点
	 * @return
	 */
	public XNode getFirstChild() {
		Node child = node.getFirstChild();
		if (child != null) {
			if (child.getNodeType() != Node.ELEMENT_NODE) {
				child = child.getNextSibling();
			}
			if (child != null) {
				return new XNode(child, xpathParser);
			}
		}
		return null;
	}

	/**
	 * 返回最后一个子节点
	 * @return
	 */
	public XNode getLastChild() {
		Node child = node.getLastChild();
		if (child != null) {
			if (child.getNodeType() != Node.ELEMENT_NODE) {
				child = child.getPreviousSibling();
			}
			if (child != null) {
				return new XNode(child, xpathParser);
			}
		}
		return null;
	}

	/**
	 * 返回子节点名称与值属性构造成的{@link Properties}对象。<br>
	 * 示例：<br>
	 * 
	 * <pre>
	 * <entry name="" value="" />
	 * </pre>
	 * @return
	 */
	public Properties getChildrenAsProperties() {
		Properties properties = new Properties();
		for (XNode child : this.getChildren()) {
			String name = child.getStringAttribute("name");
			String value = child.getStringAttribute("value");
			if (name != null && value != null) {
				properties.setProperty(name, value);
			}
		}
		return properties;
	}

	/**
	 * 返回当前节点在文档中的路径
	 * @return
	 */
	public String getPath() {
		StringBuilder builder = new StringBuilder();
		Node current = node;
		while (current != null && current instanceof Element) {
			if (current != node) {
				builder.insert(0, "/");
			}
			builder.insert(0, current.getNodeName());
			current = current.getParentNode();
		}
		return builder.toString();
	}

	public String evalString(String expression) {
		return this.xpathParser.evalString(node, expression);
	}

	public Boolean evalBoolean(String expression) {
		return this.xpathParser.evalBoolean(node, expression);
	}

	public Double evalDouble(String expression) {
		return this.xpathParser.evalDouble(node, expression);
	}

	public List<XNode> evalNodes(String expression) {
		return this.xpathParser.evalNodes(node, expression);
	}

	public XNode evalNode(String expression) {
		return this.xpathParser.evalNode(node, expression);
	}

	public String getName() {
		return this.name;
	}

	public Node getNode() {
		return this.node;
	}

	/**
	 * 返回String类型Body值。
	 * @return
	 */
	public String getStringBody() {
		return getStringBody(null);
	}

	/**
	 * 返回String类型Body值。
	 * @param def 默认值
	 * @return
	 */
	public String getStringBody(String def) {
		if (body == null) {
			return def;
		} else {
			return body;
		}
	}

	/**
	 * 返回Boolean类型Body值。
	 * @return
	 */
	public Boolean getBooleanBody() {
		return getBooleanBody(null);
	}

	/**
	 * 返回Boolean类型Body值。
	 * @param def 默认值
	 * @return
	 */
	public Boolean getBooleanBody(Boolean def) {
		if (body == null) {
			return def;
		} else {
			return Boolean.valueOf(body);
		}
	}

	/**
	 * 返回Integer类型Body值。
	 * @return
	 */
	public Integer getIntBody() {
		return getIntBody(null);
	}

	/**
	 * 返回Integer类型Body值。
	 * @param def 默认值
	 * @return
	 */
	public Integer getIntBody(Integer def) {
		if (body == null) {
			return def;
		} else {
			return Integer.parseInt(body);
		}
	}

	/**
	 * 返回Long类型Body值。
	 * @return
	 */
	public Long getLongBody() {
		return getLongBody(null);
	}

	/**
	 * 返回Long类型Body值。
	 * @param def 默认值
	 * @return
	 */
	public Long getLongBody(Long def) {
		if (body == null) {
			return def;
		} else {
			return Long.parseLong(body);
		}
	}

	/**
	 * 返回Double类型Body值。
	 * @return
	 */
	public Double getDoubleBody() {
		return getDoubleBody(null);
	}

	/**
	 * 返回Double类型Body值。
	 * @param def 默认值
	 * @return
	 */
	public Double getDoubleBody(Double def) {
		if (body == null) {
			return def;
		} else {
			return Double.parseDouble(body);
		}
	}

	/**
	 * 返回Float类型Body值。
	 * @return
	 */
	public Float getFloatBody() {
		return getFloatBody(null);
	}

	/**
	 * 返回Float类型Body值。
	 * @param def 默认值
	 * @return
	 */
	public Float getFloatBody(Float def) {
		if (body == null) {
			return def;
		} else {
			return Float.parseFloat(body);
		}
	}

	/**
	 * 返回String类型的属性值。
	 * @param name 属性名
	 * @return
	 */
	public String getStringAttribute(String name) {
		return getStringAttribute(name, null);
	}

	/**
	 * 返回String类型的属性值。
	 * @param name 属性名
	 * @param def 默认值
	 * @return
	 */
	public String getStringAttribute(String name, String def) {
		String value = attributes.getProperty(name);
		if (value == null) {
			return def;
		} else {
			return value;
		}
	}

	/**
	 * 返回Boolean类型的属性值。
	 * @param name 属性名
	 * @return
	 */
	public Boolean getBooleanAttribute(String name) {
		return getBooleanAttribute(name, null);
	}

	/**
	 * 返回Boolean类型的属性值。
	 * @param name 属性名
	 * @param def 默认值
	 * @return
	 */
	public Boolean getBooleanAttribute(String name, Boolean def) {
		String value = attributes.getProperty(name);
		if (value == null) {
			return def;
		} else {
			return Boolean.valueOf(value);
		}
	}

	/**
	 * 返回Integer类型的属性值。
	 * @param name 属性名
	 * @return
	 */
	public Integer getIntAttribute(String name) {
		return getIntAttribute(name, null);
	}

	/**
	 * 返回Integer类型的属性值。
	 * @param name 属性名
	 * @param def 默认值
	 * @return
	 */
	public Integer getIntAttribute(String name, Integer def) {
		String value = attributes.getProperty(name);
		if (value == null) {
			return def;
		} else {
			return Integer.parseInt(value);
		}
	}

	/**
	 * 返回Long类型的属性值。
	 * @param name 属性名
	 * @return
	 */
	public Long getLongAttribute(String name) {
		return getLongAttribute(name, null);
	}

	/**
	 * 返回Long类型的属性值。
	 * @param name 属性名
	 * @param def 默认值
	 * @return
	 */
	public Long getLongAttribute(String name, Long def) {
		String value = attributes.getProperty(name);
		if (value == null) {
			return def;
		} else {
			return Long.parseLong(value);
		}
	}

	/**
	 * 返回Double类型的属性值。
	 * @param name 属性名
	 * @return
	 */
	public Double getDoubleAttribute(String name) {
		return getDoubleAttribute(name, null);
	}

	/**
	 * 返回Double类型的属性值。
	 * @param name 属性名
	 * @param def 默认值
	 * @return
	 */
	public Double getDoubleAttribute(String name, Double def) {
		String value = attributes.getProperty(name);
		if (value == null) {
			return def;
		} else {
			return Double.parseDouble(value);
		}
	}

	/**
	 * 返回Float类型的属性值。
	 * @param name 属性名
	 * @return
	 */
	public Float getFloatAttribute(String name) {
		return getFloatAttribute(name, null);
	}

	/**
	 * 返回Float类型的属性值。
	 * @param name 属性名
	 * @param def 默认值
	 * @return
	 */
	public Float getFloatAttribute(String name, Float def) {
		String value = attributes.getProperty(name);
		if (value == null) {
			return def;
		} else {
			return Float.parseFloat(value);
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<");
		builder.append(name);
		for (Map.Entry<Object, Object> entry : attributes.entrySet()) {
			builder.append(" ");
			builder.append(entry.getKey());
			builder.append("=\"");
			builder.append(entry.getValue());
			builder.append("\"");
		}
		List<XNode> children = this.getChildren();
		if (children.size() > 0) {
			builder.append(">\n");
			for (XNode node : children) {
				builder.append(node.toString());
			}
			builder.append("</");
			builder.append(name);
			builder.append(">");
		} else if (body != null) {
			builder.append(">");
			builder.append(body);
			builder.append("</");
			builder.append(name);
			builder.append(">");
		} else {
			builder.append("/>");
		}
		builder.append("\n");
		return builder.toString();
	}
}
