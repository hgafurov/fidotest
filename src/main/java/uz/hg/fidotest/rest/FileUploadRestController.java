package uz.hg.fidotest.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadRestController {
	
	
	@PostMapping("/api/v1/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") 
										MultipartFile uploadfile) {
		
		String fileName = "";
		
		if (uploadfile.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }
		
		try {
			fileName = saveUploadedFiles(Arrays.asList(uploadfile));
		} catch (IOException e) {
			System.out.println("======" + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Map<Object, Object> response = new HashMap<>();
		response.put("url", "/api/v1/download?file="+fileName);
		response.put("file", fileName);
		response.put("msg", "Successfully uploaded!");
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	@GetMapping("/api/v1/download")
	public ResponseEntity<?> downloadFile(@RequestParam("file") String param) throws IOException {
		System.out.println("====" + param);
		File file = new File("fidofiles\\" + param);
		Path path = Paths.get(file.getAbsolutePath());
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	    
	    HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + param);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

		
		return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
	}
	
	
	private String saveUploadedFiles(List<MultipartFile> files) throws IOException {

		String fileName = "";
        
		for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }

            byte[]bytes = file.getBytes();
            fileName = file.getOriginalFilename();
            Path path = Paths.get("fidofiles/" + fileName);
            Files.write(path, bytes);

        }
        return fileName;
    }
}
