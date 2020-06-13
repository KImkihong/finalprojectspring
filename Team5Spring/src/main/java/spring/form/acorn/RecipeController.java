package spring.form.acorn;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import data.dao.RecipeDaoInter;
import data.dto.IngredientDto;
import data.dto.RecipeDto;
import data.dto.RecipeOrderDto;

@RestController
@CrossOrigin
public class RecipeController {
	
	@Autowired
	private RecipeDaoInter rdao;
	
	int start=0;
	int end=10;
	
	@GetMapping("/recipe/list")
	public List<RecipeDto> getList(){    

		 List<RecipeDto> list = rdao.getList(start,end);
		 start+=10;
		 end+=10;
		 return list;	
	}
	
	@GetMapping("/recipe/select")
	public List<Object> selectData(@RequestParam int rec_num){
		List<Object> rlist = new ArrayList<Object>();
		RecipeDto dto = rdao.getSelectedRecipe(rec_num);
		List<IngredientDto> ilist = rdao.getIngre(rec_num);
		List<RecipeOrderDto> olist = rdao.getOrder(rec_num);
		rlist.add(dto);
		rlist.add(ilist);
		rlist.add(olist);
		return rlist;
	}
	
	@RequestMapping(value="/recipe/regist",consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public void regist(MultipartHttpServletRequest request, @ModelAttribute RecipeOrderDto dto) {
		System.out.println(dto.getPhotofile().getOriginalFilename());
	}
	
	@GetMapping("/recipe/delete")
	public void deleteData(@RequestParam int rec_num, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("WEB-INF/image/recipe");
		
		rdao.deleteOrder(rec_num);
		rdao.deleteIngre(rec_num);
		rdao.deleteRecipe(rec_num);
	}
}
