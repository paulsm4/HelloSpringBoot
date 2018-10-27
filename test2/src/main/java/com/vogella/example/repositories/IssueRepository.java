package com.vogella.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vogella.example.entity.IssueReport;

public interface IssueRepository extends JpaRepository<IssueReport, Long> {
    @Query(value = "SELECT i FROM IssueReport i WHERE markedAsPrivate = false")
    List<IssueReport> findAllButPrivate();
    List<IssueReport> findAllByEmail(String email);
}
