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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.bson.types.ObjectId;

/**
 * JSON serialization class for ObjectId. This customization is used for make the serialization of
 * the ObjectId as a "normal string", instead of full serialization with timestamp and date.
 *
 * @author Marco Terrinoni marco.terrinoni at consoft.it
 */
public class ObjectIdSerializer extends JsonSerializer<ObjectId> {

    /**
     * Custom serialization for ObjectId.
     *
     * @param t  the ObjectId to be serialized.
     * @param jg the JsonGenerator.
     * @param sp the SerializeProvider
     * @throws IOException if a problem occurs while producing the final String.
     */
    @Override
    public void serialize(ObjectId t, JsonGenerator jg, SerializerProvider sp)
            throws IOException {
        jg.writeString(t.toHexString()); // just write the ObjectId as it is (hex-string)
    }
}
