package org.jash.elastically.repository;

import java.util.List;

import org.jash.elastically.entity.Learning;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningElasticRepository extends ElasticsearchRepository<Learning, String>{
	List<Learning> findByTopicName(String topicName);

}
