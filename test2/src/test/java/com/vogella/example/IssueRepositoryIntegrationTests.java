package com.vogella.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.vogella.example.entity.IssueReport;
import com.vogella.example.repositories.IssueRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class IssueRepositoryIntegrationTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Test
	public void addNewIssue() {
		System.out.println("addNewIssue()...");
		final String email = "test@test.io";

		List<IssueReport> resultSet = issueRepository.findAll();
		System.out.println("resultSet@1=" + resultSet.size() + "...");
		
		IssueReport tmp = new IssueReport();
		tmp.setEmail(email);
		entityManager.persist(tmp);
		entityManager.flush();
		System.out.println("added " + email + "...");

		resultSet = issueRepository.findAll();
		System.out.println("resultSet@2=" + resultSet.size() + "...");
		assertThat(resultSet.size() > 0);
		
		issueRepository.deleteAll();
		System.out.println("deleted all...");
		entityManager.flush();

		resultSet = issueRepository.findAll();
		System.out.println("resultSet@3=" + resultSet.size() + "...");
		assertThat(resultSet.size() == 0);
	}
	

}
