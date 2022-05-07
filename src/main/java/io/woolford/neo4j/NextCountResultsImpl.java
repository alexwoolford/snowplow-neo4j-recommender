package io.woolford.neo4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NextCountResultsImpl implements NextCountResults {

    @Value("${spring.data.neo4j.database}")
    String database;

    Neo4jClient neo4jClient;

    NextCountResultsImpl(Neo4jClient neo4jClient){
        this.neo4jClient = neo4jClient;
    }

    @Override
    public Collection<Result> getNextCount(String url) {
        return neo4jClient
                .query("MATCH (pv:PageView) " +
                        "WHERE pv.page_url = $url " +
                        "MATCH (pv)-[:NEXT]->(pvNext) " +
                        "WHERE pvNext.page_url <> $url " +
                        "WITH pvNext " +
                        "RETURN pvNext.page_url AS page_url, COUNT(*) AS count " +
                        "ORDER BY count DESC " +
                        "LIMIT 3"
                )
                .in(database)
                .bind(url).to("url")
                .fetchAs(Result.class)
                .mappedBy((typeSystem, record) -> new Result(record.get("page_url").asString(),
                        record.get("count").asLong()))
                .all();
    }
}