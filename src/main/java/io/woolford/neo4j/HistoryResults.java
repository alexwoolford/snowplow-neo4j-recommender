package io.woolford.neo4j;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collection;

public interface HistoryResults {

    class Result {

        @Property("page_url")
        public final String pageUrl;

        @Property("derived_tstamp")
        public final ZonedDateTime derivedTstamp;

        Result(String pageUrl, ZonedDateTime derivedTstamp) {
            this.pageUrl = pageUrl;
            this.derivedTstamp = derivedTstamp;
        }

    }

    @Transactional(readOnly = true)
    Collection<Result> getHistory(String networkUserid);

}
