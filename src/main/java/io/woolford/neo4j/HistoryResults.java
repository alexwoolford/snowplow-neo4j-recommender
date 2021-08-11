package io.woolford.neo4j;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface HistoryResults {

    class Result {

        @Property("page_url")
        public final String pageUrl;

        public final Long timestamp;

        Result(String pageUrl, Long derivedTstamp) {
            this.pageUrl = pageUrl;
            this.timestamp = derivedTstamp;
        }

    }

    @Transactional(readOnly = true)
    Collection<Result> getHistory(String domainUserid);

}
