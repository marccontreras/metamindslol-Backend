package com.vas.metamindslol;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import no.stelar7.api.r4j.impl.lol.raw.ImageAPI;

public  class R4JInstance {
    private static final String apiKey = "RGAPI-6821394f-81ec-49f7-9db1-a12390788487";

    static APICredentials creds = new APICredentials(apiKey);
    public static final R4J baseAPI = new R4J(creds);
    public static final DDragonAPI dDragonAPI = baseAPI.getDDragonAPI();

    public static final ImageAPI imageAPI  = baseAPI.getImageAPI();

    public static final R4J.LOLAPI loLAPI = baseAPI.getLoLAPI();


}