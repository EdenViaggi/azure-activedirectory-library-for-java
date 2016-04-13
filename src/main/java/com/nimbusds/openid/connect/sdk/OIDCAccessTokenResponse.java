//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nimbusds.openid.connect.sdk;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.oauth2.sdk.AccessTokenResponse;
import com.nimbusds.oauth2.sdk.SerializeException;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import com.nimbusds.oauth2.sdk.token.Tokens;
import com.nimbusds.oauth2.sdk.util.JSONObjectUtils;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONObject;

@Immutable
public class OIDCAccessTokenResponse extends AccessTokenResponse {
    private final JWT idToken;
    private final String idTokenString;

    public OIDCAccessTokenResponse(AccessToken accessToken, RefreshToken refreshToken) {
        this(accessToken, refreshToken, (String)null);
    }

    public OIDCAccessTokenResponse(AccessToken accessToken, RefreshToken refreshToken, JWT idToken) {
        this(accessToken, refreshToken, (JWT)idToken, (Map)null);
    }

    public OIDCAccessTokenResponse(AccessToken accessToken, RefreshToken refreshToken, JWT idToken, Map<String, Object> customParams) {
        super(new Tokens(accessToken, refreshToken), customParams);
        this.idToken = idToken;
        this.idTokenString = null;
    }

    public OIDCAccessTokenResponse(AccessToken accessToken, RefreshToken refreshToken, String idTokenString) {
        this(accessToken, refreshToken, (String)idTokenString, (Map)null);
    }

    public OIDCAccessTokenResponse(AccessToken accessToken, RefreshToken refreshToken, String idTokenString, Map<String, Object> customParams) {
        super(new Tokens(accessToken, refreshToken), customParams);
        this.idToken = null;
        this.idTokenString = idTokenString;
    }

    public JWT getIDToken() {
        if(this.idToken != null) {
            return this.idToken;
        } else if(this.idTokenString != null) {
            try {
                return JWTParser.parse(this.idTokenString);
            } catch (ParseException var2) {
                return null;
            }
        } else {
            return null;
        }
    }

    public String getIDTokenString() {
        if(this.idTokenString != null) {
            return this.idTokenString;
        } else if(this.idToken != null) {
            if(this.idToken.getParsedString() != null) {
                return this.idToken.getParsedString();
            } else {
                try {
                    return this.idToken.serialize();
                } catch (IllegalStateException var2) {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public JSONObject toJSONObject() throws SerializeException {
        JSONObject o = super.toJSONObject();
        String idTokenOut = this.getIDTokenString();
        if(idTokenOut != null) {
            o.put("id_token", idTokenOut);
        }

        return o;
    }

    public static OIDCAccessTokenResponse parse(JSONObject jsonObject) throws com.nimbusds.oauth2.sdk.ParseException {
        AccessTokenResponse atr = AccessTokenResponse.parse(jsonObject);
        JWT idToken = null;
        if(jsonObject.containsKey("id_token")) {
            try {
                idToken = JWTParser.parse(JSONObjectUtils.getString(jsonObject, "id_token"));
            } catch (ParseException var4) {
                throw new com.nimbusds.oauth2.sdk.ParseException("Couldn\'t parse ID token: " + var4.getMessage(), var4);
            }
        }

        HashMap customParams = new HashMap();
        customParams.putAll(atr.getCustomParams());
        customParams.remove("id_token");
        return new OIDCAccessTokenResponse(atr.getTokens().getAccessToken(), atr.getTokens().getRefreshToken(), idToken, customParams);
    }

    public static OIDCAccessTokenResponse parse(HTTPResponse httpResponse) throws com.nimbusds.oauth2.sdk.ParseException {
        httpResponse.ensureStatusCode(new int[]{200});
        JSONObject jsonObject = httpResponse.getContentAsJSONObject();
        return parse(jsonObject);
    }
}
