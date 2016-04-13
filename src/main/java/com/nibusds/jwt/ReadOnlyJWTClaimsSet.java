package com.nibusds.jwt;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.minidev.json.JSONObject;

public interface ReadOnlyJWTClaimsSet {
    String getIssuer();

    String getSubject();

    List<String> getAudience();

    Date getExpirationTime();

    Date getNotBeforeTime();

    Date getIssueTime();

    String getJWTID();

    String getType();

    Object getCustomClaim(String var1);

    Map<String, Object> getCustomClaims();

    Object getClaim(String var1);

    String getStringClaim(String var1) throws ParseException;

    Boolean getBooleanClaim(String var1) throws ParseException;

    Integer getIntegerClaim(String var1) throws ParseException;

    Long getLongClaim(String var1) throws ParseException;

    Float getFloatClaim(String var1) throws ParseException;

    Double getDoubleClaim(String var1) throws ParseException;

    Map<String, Object> getAllClaims();

    JSONObject toJSONObject();
}
