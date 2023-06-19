package com.vas.metamindslol.summoner;


import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@Entity
public class Temporal {
    @Id
    private String id;
}