/**
 * Copyright (C) 2010-2018 Structr GmbH
 *
 * This file is part of Structr <http://structr.org>.
 *
 * Structr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Structr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Structr.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.structr.web.entity;

import java.net.URI;
import org.structr.common.PropertyView;
import org.structr.schema.SchemaService;
import org.structr.schema.json.JsonSchema;
import org.structr.schema.json.JsonType;
import org.structr.web.entity.dom.DOMElement;

/**
 * Represents a component. A component is an assembly of elements
 */
public interface Component extends DOMElement {

	static class Impl { static {

		final JsonSchema schema = SchemaService.getDynamicSchema();
		final JsonType type     = schema.addType("Component");

		type.setImplements(URI.create("https://structr.org/v1.1/definitions/Component"));
		type.setExtends(URI.create("#/definitions/DOMElement"));

		type.addIntegerProperty("position");
		type.addStringProperty("kind", PropertyView.Public, PropertyView.Ui);


		// FIXME: do we need this type?
	}}

	/*

	private static final int MAX_DEPTH                          = 10;
	public static final String REQUEST_CONTAINS_UUID_IDENTIFIER = "request_contains_uuids";
	private static final Logger logger                          = LoggerFactory.getLogger(Component.class.getName());

	public static final Property<Integer> position   = new IntProperty("position").indexed();
	public static final Property<String>  kind       = new StringProperty("kind").indexed();

	public static final org.structr.common.View uiView = new org.structr.common.View(Component.class, PropertyView.Ui,
		type, name, kind
	);

	public static final org.structr.common.View publicView = new org.structr.common.View(Component.class, PropertyView.Public,
		type, name, kind
	);

	private final Map<String, AbstractNode> contentNodes = new WeakHashMap<>();
	private final Set<String> subTypes                   = new LinkedHashSet<>();

	@Override
	public void onNodeDeletion() {

		try {

			final App app = StructrApp.getInstance(securityContext);

			for (AbstractNode contentNode : contentNodes.values()) {

				app.delete(contentNode);

			}

		} catch (Throwable t) {
			logger.error("Exception while deleting nested Components: {}", t.getMessage());
		}
	}

	private void collectChildren(List<Component> children, DOMNode startNode, int depth, AbstractRelationship ref) {

		if (depth > MAX_DEPTH) {

			return;

		}

		if (ref != null) {

			if (startNode instanceof Component) {

				children.add((Component) startNode);

				return;

			}

		}

		// collection of properties must not depend on page
		for (AbstractRelationship rel : startNode.getChildRelationships()) {

			DOMNode endNode = (DOMNode)rel.getTargetNode();

			if (endNode == null) {

				continue;

			}

			collectChildren(children, endNode, depth + 1, rel);

		}
	}

	@Override
	public Set<PropertyKey> getPropertyKeys(final String propertyView) {

		Set<PropertyKey> augmentedPropertyKeys = new LinkedHashSet<PropertyKey>();

		for (PropertyKey key : super.getPropertyKeys(propertyView)) {

			augmentedPropertyKeys.add(key);

		}

		// FIXME: use getPropertyKeyForName() of specific node type
		for (String key : contentNodes.keySet()) {
			augmentedPropertyKeys.add(new GenericProperty(key));
		}

		for (String subType : subTypes) {

			augmentedPropertyKeys.add(new GenericProperty(subType.toLowerCase().concat("s")));

		}

		return augmentedPropertyKeys;
	}

	@Override
	public <T> T getProperty(PropertyKey<T> key) {

		// try local properties first
		if (contentNodes.containsKey(key.dbName())) {

			AbstractNode node = contentNodes.get(key.dbName());

			if ((node != null) && (node != this)) {

				return (T)node.getProperty(Content.content);

			}

		} else if (subTypes.contains(SchemaHelper.normalizeEntityName(key.dbName()))) {

			List<Component> results = new LinkedList<>();

			collectChildren(results, this, 0, null);

			return (T)results;

		}

		return super.getProperty(key);
	}

	public Map<String, AbstractNode> getContentNodes() {
		return contentNodes;
	}

	private static boolean hasAttribute(HttpServletRequest request, String key) {
		return (key != null) && (request.getAttribute(key) != null);
	}

	// ----- public static methods -----
	public static boolean isVisible(HttpServletRequest request, AbstractNode node, AbstractRelationship incomingRelationship, String parentComponentId) {

		if (request == null) {

			return true;

		}

		// check if component is in "list" mode
		if (node instanceof Component) {

			Boolean requestContainsUuidsValue = (Boolean) request.getAttribute(REQUEST_CONTAINS_UUID_IDENTIFIER);
			boolean requestContainsUuids      = false;

			if (requestContainsUuidsValue != null) {

				requestContainsUuids = requestContainsUuidsValue.booleanValue();

			}

			String componentId = node.getProperty(GraphObject.id);

			// new default behaviour: make all components visible
			// only filter if uuids are present in the request URI
			// and we are examining a top-level component (children
			// of filtered components are not reached anyway)
			if (requestContainsUuids) {

				if (hasAttribute(request, componentId) || (parentComponentId != null)) {

					return true;

				}

				return false;

			} else {

				return true;

			}

		}

		// we can return false here by default, as we're only examining nodes of type Component
		return false;
	}

	@Override
	public <T> Object setProperty(PropertyKey<T> key, T value) throws FrameworkException {

		if (contentNodes.containsKey(key.dbName())) {

			AbstractNode node = contentNodes.get(key.dbName());

			if (node != null) {

				return node.setProperty(Content.content, value.toString());

			}

		} else {

			return super.setProperty(key, value);

		}

		return null;
	}

	@Override
	public short getNodeType() {
		return ELEMENT_NODE;
	}

	@Override
	public void render(RenderContext renderContext, int depth) throws FrameworkException {
		super.render(renderContext, depth - 1);
	}
	*/
}
