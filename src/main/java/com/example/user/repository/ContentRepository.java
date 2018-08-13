package com.example.user.repository;

import com.example.user.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    @Query(value = "SELECT * FROM content WHERE title = 'email'", nativeQuery = true)
    Content findEmail();

    @Query(value = "SELECT * FROM content WHERE title = 'address'", nativeQuery = true)
    Content findAddress();

    @Query(value = "SELECT * FROM content WHERE title = 'phone'", nativeQuery = true)
    Content findPhone();
}