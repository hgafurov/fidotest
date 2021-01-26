package uz.hg.fidotest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.hg.fidotest.model.Doc;

public interface DocRepository extends JpaRepository<Doc, Long>{

	Doc findByRegNo(String regNo);
}
