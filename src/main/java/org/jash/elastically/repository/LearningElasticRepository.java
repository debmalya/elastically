package org.jash.elastically.repository;

import org.jash.elastically.entity.Learning;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningElasticRepository extends ElasticsearchRepository<Learning, String>{

}
