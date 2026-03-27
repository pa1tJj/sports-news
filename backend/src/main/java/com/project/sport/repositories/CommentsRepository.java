package com.project.sport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sport.entities.CommentsEntity;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long>{

}
