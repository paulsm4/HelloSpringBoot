package com.vogella.springboot2.data;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.vogella.springboot2.domain.Todo;

import reactor.core.publisher.Flux;

public interface TodoRepository extends ReactiveCrudRepository<Todo, Long> {
	 Flux<Todo> findBySummaryContainingIgnoreCase(String summary);
	 
	 @Query("{ 'category' : ?0 }")
	 Flux<Todo> findByCategory(@Param("category") String categoryName);
}
