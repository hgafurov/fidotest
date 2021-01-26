package uz.hg.fidotest.service;

import java.util.List;

import uz.hg.fidotest.model.Doc;


public interface DocService {
	
	Doc save(Doc doc);
	List<Doc> getAll();
	Doc findByRegNo(String regNo);
	Doc findById(Long id);
	void delete(Long id);
}
