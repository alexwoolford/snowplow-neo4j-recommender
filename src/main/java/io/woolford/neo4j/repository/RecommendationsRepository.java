package io.woolford.neo4j.repository;

import io.woolford.neo4j.entity.PageUrl;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;


public interface RecommendationsRepository extends Neo4jRepository<String, Long> {

    @Query("MATCH (user:network_userid {id: $0})-[:VIEWED]->(page:page_url)<-[:VIEWED]-(other_user:network_userid)-[:VIEWED]->(other_page:page_url)\n" +
            "WHERE user <> other_user\n" +
            "AND NOT EXISTS ( ( {id: $0}) -[:VIEWED]->(other_page:page_url) )\n" +
            "AND other_page.id <> \"https://woolford.io/\"\n" +
            "AND NOT other_page.id STARTS WITH \"https://woolford.io/tags/\"\n" +
            "WITH other_page.id AS page_url, COUNT(other_user) AS frequency\n" +
            "ORDER BY frequency DESC\n" +
            "RETURN page_url")
    List<PageUrl> getRecommendations(String network_userid);


}
