/**
 * Copyright (C) 2010-2018 Structr GmbH
 *
 * This file is part of Structr <http://structr.org>.
 *
 * Structr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Structr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Structr.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.structr.rest.entity;

import org.structr.common.PropertyView;
import org.structr.common.View;
import org.structr.core.entity.AbstractNode;
import org.structr.core.property.EndNodes;
import org.structr.core.property.Property;

import java.util.List;

public class TestEleven extends AbstractNode {
    public static final Property<List<TestTwo>> testTwos = new EndNodes<>("test_twos", ElevenTwoOneToMany.class);
    public static final Property<List<TestTwo>> testTwosAlt = new EndNodes<>("testTwos", ElevenTwoOneToMany.class);

    public static final View defaultView = new View(TestEleven.class, PropertyView.Public,
            name, testTwos, testTwosAlt
    );
}
