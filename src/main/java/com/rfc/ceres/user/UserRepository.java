package com.rfc.ceres.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Optional<User> findByUserName(String username);
}
