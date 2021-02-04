package uz.hg.fidotest.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.hg.fidotest.model.Doc;
import uz.hg.fidotest.repository.DocRepository;
import uz.hg.fidotest.service.DocService;

@Service
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

	@Override
	public List<Doc> task2() {
		Calendar c = Calendar.getInstance();         
		c.set(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
	    Date d1 = c.getTime();
	    c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	    Date d2 = c.getTime();
	    System.out.println(d1);
		return docRepository.task2(d1, d2);
	}

	@Override
	public List<Doc> task3() {
		Calendar c = Calendar.getInstance(); 
		c.add(Calendar.MONTH, -1 * c.get(Calendar.MONTH) % 3);
		c.set(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
	    Date d1 = c.getTime();
	    c.add(Calendar.MONTH, 2);
	    c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	    Date d2 = c.getTime();
	    System.out.println(d1);
	    System.out.println(d2);
		return docRepository.task3(d1, d2);
	}

	@Override
	public List<Doc> task4() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DATE, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
	    Date d1 = c.getTime();
	    c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	    Date d2 = c.getTime();
	    System.out.println(d1);
		return docRepository.task4(d1, d2);		
	}
		

}
