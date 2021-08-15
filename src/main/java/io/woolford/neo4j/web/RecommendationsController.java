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

@CrossOrigin(origins = "https://woolford.io", maxAge = 3600)
@RestController
public class RecommendationsController {

    private final Logger LOG = LoggerFactory.getLogger(RecommendationsController.class);

    private final PageRepository pageRepository;
    private final HistoryResultsImpl historyResults;

    public RecommendationsController(PageRepository pageRepository, HistoryResultsImpl historyResults) {
        this.pageRepository = pageRepository;
        this.historyResults = historyResults;
    }

    @GetMapping("/recommendations/{domain_userid}")
    public ResponseEntity<List<Page>> getRecommendations(@PathVariable String domain_userid) {

        LOG.info("retrieving recommendations for domain_userid: " + domain_userid);

        return ResponseEntity.ok(pageRepository.getRecommendations(domain_userid));
    }

    @GetMapping("/history/{domain_userid}")
    public ResponseEntity<Collection<HistoryResults.Result>> getHistory(@PathVariable String domain_userid) {

        LOG.info("retrieving history for domain_userid: " + domain_userid);

        return ResponseEntity.ok(historyResults.getHistory(domain_userid));
    }

}
