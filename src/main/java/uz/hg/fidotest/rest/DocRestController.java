package uz.hg.fidotest.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uz.hg.fidotest.dto.CreateUserDto;
import uz.hg.fidotest.dto.UserDto;
import uz.hg.fidotest.model.Doc;
import uz.hg.fidotest.model.User;
import uz.hg.fidotest.service.DocService;

@RestController
@RequestMapping("/api/v1/doc/")
public class DocRestController {
	
	private final DocService docService;
	
	@Autowired
	public DocRestController(DocService docService) {
		this.docService = docService;		
	}
	
	@PostMapping("save-doc")
	public ResponseEntity<?> docSave(@RequestBody Doc doc){
		
		// Bu erda Doc validation bo'lishi kerak
		
		doc = docService.save(doc);
		if (doc == null) {
			Map<Object, Object> response = new HashMap<>();
			response.put("warn", "Doc saqlanmadi");
			response.put("msg", "Docni saqlash omadsiz yakunlandi");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );
		}
		
		return new ResponseEntity<>(doc, HttpStatus.OK);		
	}
	
	@GetMapping("get-all")
	public ResponseEntity<?> docs(){
		
		// Bu erda Doc validation bo'lishi kerak
		
		List<Doc> docs = docService.getAll();
		if (docs == null) {
			Map<Object, Object> response = new HashMap<>();
			response.put("warn", "Doc topilmadi");
			response.put("msg", "So'rov bo'yicha hujjarlar topilmadi");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );
		}
		
		return new ResponseEntity<>(docs, HttpStatus.OK);		
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		
		// Bu erda Doc validation bo'lishi kerak
		
		Doc doc = docService.findById(id);
		if (doc == null) {
			Map<Object, Object> response = new HashMap<>();
			response.put("warn", "Doc saqlanmadi");
			response.put("msg", "Docni saqlash omadsiz yakunlandi");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );
		}
		
		return new ResponseEntity<>(doc, HttpStatus.OK);		
	}
	
	@PostMapping("update")
	public ResponseEntity<?> updateDoc(@RequestBody  Doc doc) {
		Doc uDoc = docService.save(doc);	
		if (uDoc != null) {
			return new ResponseEntity<>(uDoc, HttpStatus.OK);
		}
		Map<Object, Object> response = new HashMap<>();
		response.put("warn", "Hujjat saqlanmadi");
		response.put("msg", "Hujjatni saqlash omadsiz yakunlandi");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );					
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteDoc(@PathVariable Long id) {
		docService.delete(id);	
		return new ResponseEntity<>(HttpStatus.OK);				
	}

	@GetMapping("task2")
	public ResponseEntity<?> task2() {
		List<Doc> docs = docService.task2();
		if (docs == null) {
			Map<Object, Object> response = new HashMap<>();
			response.put("warn", "Doc topilmadi");
			response.put("msg", "So'rov bo'yicha hujjarlar topilmadi");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );
		}
		
		return new ResponseEntity<>(docs, HttpStatus.OK);					
	}
	
	@GetMapping("task3")
	public ResponseEntity<?> task3() {
		List<Doc> docs = docService.task3();
		if (docs == null) {
			Map<Object, Object> response = new HashMap<>();
			response.put("warn", "Doc topilmadi");
			response.put("msg", "So'rov bo'yicha hujjarlar topilmadi");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );
		}
		
		return new ResponseEntity<>(docs, HttpStatus.OK);					
	}
	
	@GetMapping("task4")
	public ResponseEntity<?> task4() {
		List<Doc> docs = docService.task4();
		if (docs == null) {
			Map<Object, Object> response = new HashMap<>();
			response.put("warn", "Doc topilmadi");
			response.put("msg", "So'rov bo'yicha hujjarlar topilmadi");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );
		}
		
		return new ResponseEntity<>(docs, HttpStatus.OK);					
	}
}
