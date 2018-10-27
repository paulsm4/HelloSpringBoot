package com.vogella.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.example.entity.IssueReport;
import com.vogella.example.repositories.IssueRepository;

/**
 * Substantially the same as parallel "IssueController", except:
 * - Will return JSON-formatted data (instead of attempting to render HTML template
 * - Current API methods read-only: hence no POST mappings
 */
@RestController
public class IssueRestController {
	IssueRepository issueRepository;
	
	public IssueRestController(IssueRepository issueRepository) {
		this.issueRepository = issueRepository;
	}
	
    @GetMapping(value="/api/issues")
    public List<IssueReport> getIssues() {
        return issueRepository.findAllButPrivate();
    }

    @GetMapping(value="/api/issues/{id}")
    public Optional<IssueReport> getIssue(@PathVariable(value="id") long id) {
        return issueRepository.findById(id);
    }
}
