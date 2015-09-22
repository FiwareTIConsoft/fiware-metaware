/*
 * Copyright (C) 2015 Telecom Italia S.p.A.
 *
 * This file is part of Metaware.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Author: Marco Terrinoni marco.terrinoni at consoft.it
 */
package com.tilab.fiware.metaware.dao.impls.mongodb.core;

import static com.tilab.fiware.metaware.dao.impls.mongodb.core.SingltDaoProv.INSTANCE;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 * Utilities collection class.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class Util {

    /**
     * Extracts the attributes of a Java object and produces a Map.
     *
     * @param object the Java object to be analyzed.
     * @return the Map containing the attributes of the Java object.
     */
    public static Map<String, Object> extractObjectAttributesAsAMap(Object object) {
        return INSTANCE.getObjectMapper().convertValue(object, Map.class);
    }

    /**
     * Compare two ObjectIds.
     *
     * @param obj1 the first Id.
     * @param obj2 the second Id.
     * @return true if the first ObjectId is equal to the second ObjectId.
     */
    public static boolean compareIds(Object obj1, Object obj2) {
        if (obj1 instanceof String && obj2 instanceof String) {
            ObjectId id1 = new ObjectId((String) obj1);
            ObjectId id2 = new ObjectId((String) obj2);
            return id1.compareTo(id2) == 0;
        } else if (obj1 instanceof String && obj2 instanceof ObjectId) {
            ObjectId id1 = new ObjectId((String) obj1);
            ObjectId id2 = (ObjectId) obj2;
            return id1.compareTo(id2) == 0;
        } else if (obj1 instanceof ObjectId && obj2 instanceof String) {
            ObjectId id1 = (ObjectId) obj1;
            ObjectId id2 = new ObjectId((String) obj2);
            return id1.compareTo(id2) == 0;
        } else if (obj1 instanceof ObjectId && obj2 instanceof ObjectId) {
            ObjectId id1 = (ObjectId) obj1;
            ObjectId id2 = (ObjectId) obj2;
            return id1.compareTo(id2) == 0;
        } else {
            return false;
        }
    }
}
