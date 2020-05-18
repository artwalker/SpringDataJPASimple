package org.example.dao;

import org.example.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author HackerStar
 * @create 2020-05-18 15:53
 */
public interface IRoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
}
