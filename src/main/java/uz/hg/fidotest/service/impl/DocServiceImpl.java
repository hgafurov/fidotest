package uz.hg.fidotest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uz.hg.fidotest.model.Doc;
import uz.hg.fidotest.repository.DocRepository;
import uz.hg.fidotest.service.DocService;

public class DocServiceImpl implements DocService {

	private final DocRepository docRepository;
	
	@Autowired
	public DocServiceImpl(DocRepository docRepository) {
	   this.docRepository = docRepository;	
	}
	
	@Override
	public Doc save(Doc doc) {
		return docRepository.save(doc);
	}

	@Override
	public List<Doc> getAll() {
		return docRepository.findAll();
	}

	@Override
	public Doc findByRegNo(String regNo) {
		return docRepository.findByRegNo(regNo);
	}

	@Override
	public Doc findById(Long id) {
		return docRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		docRepository.deleteById(id);		
	}
		

}
