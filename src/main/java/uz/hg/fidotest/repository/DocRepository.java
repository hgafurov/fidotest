package uz.hg.fidotest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uz.hg.fidotest.model.Doc;

public interface DocRepository extends JpaRepository<Doc, Long>{

	Doc findByRegNo(String regNo);
	
	@Query("SELECT d FROM Doc d WHERE d.formaDostav='Email' AND d.correspondent='ЦБ' AND d.regDate BETWEEN :d1 AND :d2")
	List<Doc> task2(Date d1, Date d2);

	@Query("SELECT d FROM Doc d WHERE NOT (d.formaDostav='Курьер' AND d.correspondent='ГНИ') AND d.regDate BETWEEN :d1 AND :d2")
	List<Doc> task3(Date d1, Date d2);

	@Query("SELECT d FROM Doc d WHERE d.correspondent='ТСЖ' AND (d.regDate BETWEEN :d1 AND :d2) AND NOT (UPPER(d.tema) LIKE '%КРЕДИТ%' OR UPPER(d.tema) LIKE '%KREDIT%')")
	List<Doc> task4(Date d1, Date d2);
}
