package spring.form.acorn;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import data.dao.CommentDaoInter;
import data.dto.CommentDto;
import data.util.SpringFileWrite;

@RestController
@CrossOrigin
public class CommentController {
	
	@Autowired
	private CommentDaoInter dao;
	
	@RequestMapping(value="/comment/regist", consumes = {"multipart/form-data"} ,method = RequestMethod.POST)
	public int register(MultipartHttpServletRequest request, @ModelAttribute("CommentDto") CommentDto dto, BindingResult result) {
		
		if(dto.getImagefile()!=null) {
			String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/comment");
			String fileName = new Date().getTime()+"_"+dto.getImagefile().getOriginalFilename();
			dto.setImage(fileName);
			SpringFileWrite sfw = new SpringFileWrite();
			sfw.writeFileRename(dto.getImagefile(), path, fileName);
		}else
			dto.setImage("basic_user.png");	
		dao.insertComment(dto);	
		
		return 1;
	}
	
	
}
