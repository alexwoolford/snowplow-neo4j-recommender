# snowplow-neo4j-recommender

![graph-recommender](img/graph-recommender.png)

The graph is built, dynamically, using the Neo4j connector:

Once the data is in Kafka, we can build a graph of the `network_userid`'s and `page_url`'s

    http PUT cp01.woolford.io:8083/connectors/snowplow-neo4j/config <<< '
    {
    "connector.class": "streams.kafka.connect.sink.Neo4jSinkConnector",
    "key.converter": "org.apache.kafka.connect.storage.StringConverter",
    "name": "snowplow-neo4j",
    "neo4j.authentication.basic.password": "V1ctoria",
    "neo4j.authentication.basic.username": "neo4j",
    "neo4j.server.uri": "bolt://neo4j.woolford.io:7687",
    "neo4j.topic.cypher.snowplow-enriched-json-good": "MERGE (network_userid:network_userid {network_userid: event.network_userid}) MERGE (page_url:page_url {page_url: event.page_url} ) MERGE (network_userid)-[:VIEWED {derived_tstamp: datetime(event.derived_tstamp)}]->(page_url)",
    "topics": "snowplow-enriched-json-good",
    "value.converter": "org.apache.kafka.connect.json.JsonConverter",
    "value.converter.schemas.enable": "false"
    }'

Here's an explanation of the Neo4j Cypher statement:

![cypher-statement](img/cypher-statement.png)

The REST service returns a list of page recommendations for a `network_userid` (which, is the guid starting with `8a51...` below):

    http localhost:8081/recommendations/8a5107ba-bffa-47de-ba9a-6fc74f08ac62
    [
        {
            "pageUrl": "https://woolford.io/2018-02-11-cowrie/"
        },
        {
            "pageUrl": "https://woolford.io/2020-07-11-streaming-joins/"
        }
    ]

It's also possible to retrieve a list of views for each `network_userid`, e.g.

    MATCH(n:network_userid {network_userid: "88bd67c1-66ce-4cc6-82a2-9e128017f379"})-[r:VIEWED]->(p:page_url)
    RETURN n.network_userid AS network_userid, p.page_url AS page_url, r.derived_tstamp AS derived_tstamp
    ORDER BY r.derived_tstamp
