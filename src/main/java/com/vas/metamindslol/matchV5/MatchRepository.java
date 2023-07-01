package com.vas.metamindslol.matchV5;

import com.vas.metamindslol.items.ItemDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<LOLMatchDD, Long> {

    //@Query("SELECT M FROM LOLMatchDD M "
    //+ " JOIN FETCH M.participants P "
    //+ " WHERE P.summonerName=:summonerName")
    @Query("SELECT M FROM LOLMatchDD M WHERE M.gameId IN (SELECT M.gameId FROM LOLMatchDD  "
    + " JOIN  M.participants P "
    + " WHERE P.summonerName=:summonerName)")
    public List<LOLMatchDD> getMatchesBySummonerName(String summonerName);
}
