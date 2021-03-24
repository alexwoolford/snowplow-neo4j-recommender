package io.woolford.neo4j.service;

import io.woolford.neo4j.entity.Page;
//import io.woolford.neo4j.entity.PageView;
import io.woolford.neo4j.repository.PageRepository;
//import io.woolford.neo4j.repository.PageViewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommendationsService {

    private final PageRepository pageRepository;

    public RecommendationsService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Transactional(readOnly = true)
    public List<Page> getRecommendations(String networkUserId) {
        List<Page> result = pageRepository.getRecommendations(networkUserId);
        return result;
    }

}
