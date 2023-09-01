package com.vas.metamindslol.matchV5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<LOLMatchDD, Long> {

    //@Query("SELECT M FROM LOLMatchDD M "
    //+ " JOIN FETCH M.participants P "
    //+ " WHERE P.summonerName=:summonerName")
    @Query("SELECT M FROM LOLMatchDD M WHERE M.gameId IN (SELECT M.gameId FROM LOLMatchDD  "
            + " JOIN  M.participants P "
            + " WHERE P.summonerName=:summonerName "
            + ")"
            + "ORDER BY M.gameCreation DESC limit 20 ")
    public List<LOLMatchDD> getMatchesBySummonerName(String summonerName);

    @Query("SELECT M FROM LOLMatchDD M WHERE M.gameId IN(SELECT M.gameId FROM LOLMatchDD  "
            + " JOIN  M.participants P "
            + " WHERE P.summonerName=:summonerName )"
            + " ORDER BY M.gameCreation DESC limit 1")
    public LOLMatchDD getMostRecentMatchBySummonerName(String summonerName);
}
