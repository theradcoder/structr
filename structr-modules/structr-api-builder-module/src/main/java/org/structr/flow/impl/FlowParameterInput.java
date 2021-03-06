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
package org.structr.flow.impl;

import org.structr.common.PropertyView;
import org.structr.common.View;
import org.structr.core.property.EndNode;
import org.structr.core.property.Property;
import org.structr.core.property.StartNode;
import org.structr.core.property.StringProperty;
import org.structr.flow.api.DataSource;
import org.structr.flow.engine.Context;
import org.structr.flow.impl.rels.FlowCallParameter;
import org.structr.flow.impl.rels.FlowDataInput;

public class FlowParameterInput extends FlowBaseNode {

	public static final Property<FlowCall> call 				= new EndNode<>("call", FlowCallParameter.class);
	public static final Property<DataSource> dataSource 		= new StartNode<>("dataSource", FlowDataInput.class);

	public static final Property<String> key             		= new StringProperty("key");

	public static final View defaultView 						= new View(FlowDataSource.class, PropertyView.Public, key, call, dataSource);
	public static final View uiView      						= new View(FlowDataSource.class, PropertyView.Ui, key, call, dataSource);


	public void process(final Context context, final Context functionContext) {
		DataSource _ds = getProperty(dataSource);
		String _key = getProperty(key);

		if(_ds != null && _key != null) {
			Object data = _ds.get(context);
			functionContext.setParameter(_key, data);
		}

	}
}
