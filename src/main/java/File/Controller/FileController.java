package File.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import File.Entity.File;
import File.Service.FileService;

@RestController
public class FileController
{
	@Autowired
	private FileService fileservice;
	
	@PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException
	{
            fileservice.saveExcelData(file);
            return ResponseEntity.ok("File uploaded and data saved successfully");
        
    }
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Optional<File>> fetchbyid(@PathVariable long id)
	{
		return new ResponseEntity<>(fileservice.fetchbyid(id),HttpStatus.OK);
	}
    
	@GetMapping("/getall")
	public ResponseEntity<List<File>> fetchbyall()
	{
		return new ResponseEntity<>(fileservice.fetchbyall(),HttpStatus.OK);
	}
	
	
}
