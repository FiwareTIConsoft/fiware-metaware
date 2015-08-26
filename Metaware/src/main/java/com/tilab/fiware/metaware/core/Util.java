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
 * Author: Marco Terrinoni <marco.terrinoni at consoft.it>
 */
package com.tilab.fiware.metaware.core;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.xml.bind.DatatypeConverter;

/**
 * Utilities collection class.
 *
 * @author Marco Terrinoni <marco.terrinoni at consoft.it>
 */
public class Util {

    private static final int SALT_LENGTH = 16;

    public static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom rnd = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[SALT_LENGTH];
        rnd.nextBytes(salt);
        return Arrays.toString(salt);
    }

    public static String[] decodeBasicAuth(String auth) {
        String uap = auth.replaceFirst("[B|b]asic", ""); // remove "(B|b)asic" from the string
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(uap);
        if (decodedBytes == null || decodedBytes.length == 0) {
            return null;
        } else {
            return new String(decodedBytes).split(":", 2);
        }
    }
}
