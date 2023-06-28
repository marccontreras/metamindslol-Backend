package com.vas.metamindslol.summoner;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface  SummonerRepository extends JpaRepository<SummonerDD, Integer> {

    @Query("SELECT S FROM SummonerDD S "
    + " WHERE S.name= :summonerName AND S.platform =  :region")

   public SummonerDD findByNameAndRegion(String summonerName, @Param("region") LeagueShard region);
/*
    @Query("SELECT S FROM Summoner S "
            + " WHERE S.name= :summonerName AND S.platform =  :region")

    public Summoner findByNameAndRegion(String summonerName, @Param("region") String region);
    */

}
