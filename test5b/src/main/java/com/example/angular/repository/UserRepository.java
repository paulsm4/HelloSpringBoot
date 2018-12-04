package com.example.angular.repository;

import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.angular.entity.WebsiteUser;

//public interface UserRepository extends JpaRepository<WebsiteUser, Long> {
// Default REST Endpoint= "/webSiteUsers; custom endpoint= "/users"
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "users", path="users")
public interface UserRepository extends PagingAndSortingRepository<WebsiteUser, Long> {
	List<WebsiteUser> findByName(@Param("name") String name);
}
