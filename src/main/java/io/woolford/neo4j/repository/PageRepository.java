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
            "AND other_page.is_post = true " +
            "WITH other_page, COUNT(other_user) AS frequency " +
            "ORDER BY frequency DESC " +
            "RETURN other_page " +
            "LIMIT 3")
    List<Page> getRecommendations(@Param("domain_userid") String domain_userid);

}
