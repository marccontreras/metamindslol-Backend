package com.vas.metamindslol;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import no.stelar7.api.r4j.impl.lol.raw.ImageAPI;

public  class R4JInstance {
    private static final String apiKey = "RGAPI-f7ec0d1d-8322-418b-a963-23fdf4804e0b";
//    public static  String apiKey = "";

    static APICredentials creds = new APICredentials(apiKey);
    public static final R4J baseAPI = new R4J(creds);
    public static final DDragonAPI dDragonAPI = baseAPI.getDDragonAPI();
    public static final R4J.LOLAPI loLAPI = baseAPI.getLoLAPI();


}