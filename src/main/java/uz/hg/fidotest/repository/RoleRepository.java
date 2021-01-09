package uz.hg.fidotest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.hg.fidotest.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findByName(String name);
}
