package io.woolford.neo4j.repository;

import io.woolford.neo4j.entity.PageUrl;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecommendationsRepository extends Neo4jRepository<List<PageUrl>, Long> {

    @Query( "MATCH (user:network_userid {id: $network_userid})-[:VIEWED]->(page:page_url)<-[:VIEWED]-(other_user:network_userid)-[:VIEWED]->(other_page:page_url) " +
            "WHERE user <> other_user " +
            "AND NOT EXISTS ( ( {id: $0}) -[:VIEWED]->(other_page:page_url) ) " +
            "AND other_page.id <> \"https://woolford.io/\" " +
            "AND NOT other_page.id STARTS WITH \"https://woolford.io/tags/\" " +
            "WITH other_page.id AS page_url, COUNT(other_user) AS frequency " +
            "ORDER BY frequency DESC " +
            "RETURN page_url")
    List<PageUrl> getRecommendations(@Param("network_userid") String network_userid);

}
