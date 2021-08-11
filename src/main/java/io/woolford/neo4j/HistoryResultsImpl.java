package io.woolford.neo4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HistoryResultsImpl implements HistoryResults {

    @Value("${spring.data.neo4j.database}")
    String database;

    Neo4jClient neo4jClient;

    HistoryResultsImpl(Neo4jClient neo4jClient){
        this.neo4jClient = neo4jClient;
    }

    @Override
    public Collection<Result> getHistory(String domainUserid) {
        return neo4jClient
                .query("MATCH(n:User {domain_userid: $domain_userid})-[r:VIEWED]->(p:Page) " +
                        "RETURN p.page_url AS page_url, r.timestamp AS timestamp " +
                        "ORDER BY timestamp"
                )
                .in(database)
                .bind(domainUserid).to("domain_userid")
                .fetchAs(Result.class)
                .mappedBy((typeSystem, record) -> new Result(record.get("page_url").asString(),
                        record.get("timestamp").asLong()))
                .all();
    }
}
