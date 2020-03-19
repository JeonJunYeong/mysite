package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.AdminRepository;
import com.douzone.mysite.vo.SiteVo;


@Service
public class AdminService {

	private static final String SAVE_PATH = "/mysite-uploads";

	
	@Autowired
	private AdminRepository adminRepository;
	
	public String getFile(MultipartFile multipartFile) {
		
		String url = "";
		
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			String originFilename = multipartFile.getOriginalFilename();
			
			int lastIdx = originFilename.lastIndexOf('.');
			String extName = originFilename.substring(lastIdx+1);
			
			String saveFilename = generateSaveFilename(extName);
		
			byte[] fileDate = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFilename);
			os.write(fileDate);
			os.close();
			
			
			url = saveFilename;
			
		}catch(Exception e) {
			throw new RuntimeException("file upload error : "+e);
		}
		
		return url;
		
	}
	
	private String generateSaveFilename(String extName) {
		
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);	
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("."+extName);
				
				
		
		return fileName;
	}

	public boolean update(SiteVo vo) {
		
		boolean result =adminRepository.update(vo);
		
		return result;
	}

	public SiteVo find() {
		
		SiteVo now = adminRepository.find();
		
		return now;
	}

}
