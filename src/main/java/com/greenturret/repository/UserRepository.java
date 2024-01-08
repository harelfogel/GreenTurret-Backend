package com.greenturret.repository;

import com.greenturret.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // You can define custom query methods here if needed
}
