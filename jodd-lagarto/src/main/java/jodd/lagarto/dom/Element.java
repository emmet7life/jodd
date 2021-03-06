// Copyright (c) 2003-2013, Jodd Team (jodd.org). All Rights Reserved.

package jodd.lagarto.dom;

import jodd.lagarto.Tag;

import java.io.IOException;

/**
 * Tag node.
 */
public class Element extends Node {

	protected final boolean voidElement;
	protected final boolean selfClosed;

	protected Element(LagartoDOMBuilder domBuilder, Tag tag, boolean voidElement, boolean selfClosed) {
		super(domBuilder, NodeType.ELEMENT, tag.getName());
		this.voidElement = voidElement;
		this.selfClosed = selfClosed;

		int attrCount = tag.getAttributeCount();
		for (int i = 0; i < attrCount; i++) {
			String key = tag.getAttributeName(i);
			String value = tag.getAttributeValue(i);
			setAttribute(key, value);
		}
	}

	// ---------------------------------------------------------------- clone

	/**
	 * Internal constructor.
	 */
	public Element(LagartoDOMBuilder domBuilder, String name, boolean voidElement, boolean selfClosed) {
		super(domBuilder, NodeType.ELEMENT, name);
		this.voidElement = voidElement;
		this.selfClosed = selfClosed;
	}

	@Override
	public Element clone() {
		return cloneTo(new Element(domBuilder, nodeName, voidElement, selfClosed));
	}

	// ---------------------------------------------------------------- html

	/**
	 * Returns <code>true</code> if element is void.
	 */
	public boolean isVoidElement() {
		return voidElement;
	}

	/**
	 * Returns <code>true</code> if element can self-close itself when empty.
	 */
	public boolean isSelfClosed() {
		return selfClosed;
	}

	@Override
	public void toHtml(Appendable appendable) throws IOException {
		getDomBuilder().getRenderer().renderElement(this, appendable);
	}

	@Override
	public String toString() {
		return '<' + nodeName + '>';
	}
}
