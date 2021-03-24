package io.woolford.neo4j.web;

import io.woolford.neo4j.HistoryResults;
import io.woolford.neo4j.HistoryResultsImpl;
import io.woolford.neo4j.entity.Page;
import io.woolford.neo4j.repository.PageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

@RestController
public class RecommendationsController {

    private final Logger LOG = LoggerFactory.getLogger(RecommendationsController.class);

    private final PageRepository pageRepository;
    private final HistoryResultsImpl historyResults;

    public RecommendationsController(PageRepository pageRepository, HistoryResultsImpl historyResults) {
        this.pageRepository = pageRepository;
        this.historyResults = historyResults;
    }

    @GetMapping("/recommendations/{network_userid}")
    public ResponseEntity<List<Page>> getRecommendations(@PathVariable String network_userid) {

        LOG.info("retrieving recommendations for network_userid: " + network_userid);

        return ResponseEntity.ok(pageRepository.getRecommendations(network_userid));
    }

    @GetMapping("/history/{network_userid}")
    public ResponseEntity<Collection<HistoryResults.Result>> getHistory(@PathVariable String network_userid) {

        LOG.info("retrieving history for network_userid: " + network_userid);

        return ResponseEntity.ok(historyResults.getHistory(network_userid));
    }

}
