package File.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import File.Entity.File;
import File.Repository.FileRepository;

@Service
public class FileService 
{
	@Autowired
    private FileRepository filerepository;
	
	public void saveExcelData(MultipartFile file) throws IOException 
	{
		
	    List<File> details=new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet)
        {
        	if(row.getRowNum()==0)
        	{
        		continue;
        	
        	}
            File files=new File();
            files.setId((long)row.getCell(0).getNumericCellValue());
            files.setUsername(row.getCell(1).getStringCellValue());
            files.setEmail(row.getCell(2).getStringCellValue());
            // Set other fields
            details.add(files);
            filerepository.save(files);
        }
        workbook.close();
    }
	
	public Optional<File> fetchbyid(Long  id)
	{
		return filerepository.findById(id);
	}
	
	public List<File> fetchbyall()
	{
		return filerepository.findAll();
	}
	
	
}
