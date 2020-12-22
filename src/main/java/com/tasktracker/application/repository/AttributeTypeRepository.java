package com.tasktracker.application.repository;

import com.tasktracker.application.models.AttributeType;
import com.tasktracker.application.models.EAttributeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeTypeRepository extends JpaRepository<AttributeType, Long> {
  Optional<AttributeType> findByName(EAttributeType name);
}
