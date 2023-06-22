package com.vas.metamindslol.champion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ChampionRepository extends JpaRepository<StaticChampion, Integer> {


}
