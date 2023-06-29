package com.vas.metamindslol.matchV5;

import com.vas.metamindslol.items.ItemDD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<LOLMatchDD, Long> {
}
