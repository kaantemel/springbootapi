package com.layermark.springbootapi.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByPname(String name);
    Optional<Project> findProjectByCourseName(String name);
}
