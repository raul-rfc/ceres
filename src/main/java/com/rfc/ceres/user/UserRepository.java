package com.rfc.ceres.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Optional<User> findByUserName(String username);
}
