/*******************************************************************************
 * Copyright © Microsoft Open Technologies, Inc.
 * 
 * All Rights Reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * THIS CODE IS PROVIDED *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 * ANY IMPLIED WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A
 * PARTICULAR PURPOSE, MERCHANTABILITY OR NON-INFRINGEMENT.
 * 
 * See the Apache License, Version 2.0 for the specific language
 * governing permissions and limitations under the License.
 ******************************************************************************/
package com.microsoft.aad.adal4j;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.nimbusds.jwt.JWTClaimsSet;
import net.minidev.json.JSONObject;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class AdalJWTClaimsSetTest extends AbstractAdalTests {

    static final String AUDIENCE_CLAIM = "aud";

    @Test
    public void testNullAudience() throws ParseException {

        JSONObject json = new JSONObject();
        json.put("aud", null);
        final JWTClaimsSet obj = JWTClaimsSet.parse(json);
        final JSONObject jo = obj.toJSONObject();
        Assert.assertFalse(jo.containsKey(AUDIENCE_CLAIM));
    }

    @Test
    public void testEmptyAudience() throws ParseException {

        JSONObject json = new JSONObject();
        json.put("aud", "");
        json.put("iss", "issuer");
        final JWTClaimsSet obj = JWTClaimsSet.parse(json);
        final JSONObject jo = obj.toJSONObject();
        Assert.assertTrue(jo.containsKey(AUDIENCE_CLAIM));
    }

    @Test
    public void testPopulatedAudience() throws ParseException {

//        List<String> audience = new ArrayList<>();
//        audience.add("aud1");
        JSONObject json = new JSONObject();
        json.put("aud", "aud1");
        json.put("iss", "issuer");
        final JWTClaimsSet obj = JWTClaimsSet.parse(json);
        JSONObject jo = obj.toJSONObject();
        Assert.assertTrue(jo.containsKey(AUDIENCE_CLAIM));
    }
}
