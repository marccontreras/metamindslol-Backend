
package com.vas.metamindslol.summoner;

import jakarta.persistence.*;
import lombok.Data;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public final class SummonerDD implements Serializable
{
    private static final long serialVersionUID = 7941348691314302772L;

    private int         profileIconId;
    private String      name;
    private int         summonerLevel;
    private String      accountId;
    private String      puuid;
    @Id
    private String summonerId;
    private long        revisionDate;
    @Enumerated(EnumType.STRING)
    //@Type(LeagueShard.class )
    //@Type(PostgreSQLEnumType.class)
    private LeagueShard platform;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        SummonerDD summonerDD = (SummonerDD) o;
        return profileIconId == summonerDD.profileIconId && summonerLevel == summonerDD.summonerLevel && revisionDate == summonerDD.revisionDate && Objects.equals(name, summonerDD.name) && Objects.equals(accountId, summonerDD.accountId) && Objects.equals(puuid, summonerDD.puuid) && Objects.equals(summonerId, summonerDD.summonerId) && platform == summonerDD.platform;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(profileIconId, name, summonerLevel, accountId, puuid, summonerId, revisionDate, platform);
    }

    @Override
    public String toString()
    {
        return "Summoner{" +
                "profileIconId=" + profileIconId +
                ", name='" + name + '\'' +
                ", summonerLevel=" + summonerLevel +
                ", accountId='" + accountId + '\'' +
                ", puuid='" + puuid + '\'' +
                ", id='" + summonerId + '\'' +
                ", revisionDate=" + revisionDate +
                ", platform=" + platform +
                '}';
    }

}
