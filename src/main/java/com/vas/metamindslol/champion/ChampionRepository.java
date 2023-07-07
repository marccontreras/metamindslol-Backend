package com.vas.metamindslol.champion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChampionRepository extends JpaRepository<StaticChampionDD, Integer> {

    @Query("SELECT D FROM StaticChampionDD D "
            + " WHERE D.name=:championName")
    public StaticChampionDD getChampionByName(@Param("championName") String championName);
}
