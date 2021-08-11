package io.woolford.neo4j.repository;

import io.woolford.neo4j.entity.Page;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends Neo4jRepository<Page, Long> {

    @Query( "MATCH (user:User {domain_userid: $domain_userid})-[:VIEWED]->(page:Page)<-[:VIEWED]-(other_user:User)-[:VIEWED]->(other_page:Page) " +
            "WHERE user <> other_user " +
            "AND other_page.page_url <> \"https://woolford.io/\" " +
            "AND NOT other_page.page_url STARTS WITH \"https://woolford.io/tags/\" " +
            "WITH other_page AS page_url, COUNT(other_user) AS frequency " +
            "ORDER BY frequency DESC " +
            "RETURN page_url " +
            "LIMIT 3")
    List<Page> getRecommendations(@Param("domain_userid") String domain_userid);

}
