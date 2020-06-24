package spring.form.acorn;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import data.dao.ConnectDaoInter;
import data.dao.RecipeDaoInter;
import data.dto.IngredientDto;
import data.dto.RecipeDto;
import data.dto.RecipeOrderDto;
import data.util.SpringFileWrite;

@RestController
@CrossOrigin
public class RecipeController {
	
	@Autowired
	private RecipeDaoInter dao;
	@Autowired
	private ConnectDaoInter cdao;
	
	int start=0;
	int end=3;
	//count
	@GetMapping("/recipe/count")
	public HashMap<String, Integer> getCount(@RequestParam int rec_num){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("readcount", dao.getReadcount(rec_num));
		map.put("joayo", cdao.getCountJoayo(rec_num));
		map.put("scrap", cdao.getCountScrap(rec_num));
		
		return map;
	}
	
	@GetMapping("/recipe/list")
	public List<RecipeDto> getList(@RequestParam(required = false) String field,
	         @RequestParam(required = false) String search,
	         @RequestParam(required = false) String food_cate,@RequestParam(required=false, defaultValue="0") int scroll){    
		System.out.println("접속:"+start);
		List<RecipeDto> list = new ArrayList<RecipeDto>();
		if(field!=null) {	//���˻��� ��
			if(scroll==0) {
				start=0;
			}
			if(field.equals("���")) {
				List<Integer> numList = dao.getRec_nums(start, end, search);
				for(int rec_num : numList) {
					RecipeDto dto = dao.getIngreRecipe(rec_num);
					list.add(dto);
				}
			}else {
				list = dao.getList(start,end,search,"");
				start+=3;
			}
			start+=3;
		}else {		//��ü����Ʈ�� �������� �˻��� ��
			if(scroll==0) {
				start=0;
			}		
			
			list = dao.getList(start,end,"",food_cate);
			 start+=3;
		}
		return list;	
	}
	
	@GetMapping("/recipe/select")
	public RecipeDto selectData(@RequestParam int rec_num, HttpServletRequest r){
		
		String path=r.getSession().getServletContext().getRealPath("/WEB-INF/image/profile");
		System.out.println(path);
		dao.updateReadcount(rec_num);
		RecipeDto dto = dao.getSelectedRecipe(rec_num);
		List<IngredientDto> ilist = dao.getIngre(rec_num);
		List<RecipeOrderDto> olist = dao.getOrder(rec_num);
		dto.setIngreList(ilist);
		dto.setOrderList(olist);
		return dto;
	}
	
	@RequestMapping(value="/recipe/regist",consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public void regist(MultipartHttpServletRequest request, @ModelAttribute("RecipeDto") RecipeDto rdto, BindingResult result) {
		String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/recipe");
		SpringFileWrite sfw = new SpringFileWrite();
		String comp_photo="";
		for(MultipartFile file:rdto.getComp_photoList()) {
			if(!file.getOriginalFilename().equals("") && !(file.getOriginalFilename()==null)) {
				String fileName = new Date().getTime()+"_"+file.getOriginalFilename();
				sfw.writeFileRename(file, path, fileName);
				comp_photo+=fileName+",";
			}			
		}		
		//������ �޸�����
		if(comp_photo.length()>0)
			comp_photo = comp_photo.substring(0,comp_photo.length()-1);
		rdto.setComp_photo(comp_photo);
		
		if(rdto.getRepre_photofile()!=null) {
			String fileName = new Date().getTime()+"_"+rdto.getRepre_photofile().getOriginalFilename();
			rdto.setRepre_photo(fileName);
			sfw.writeFileRename(rdto.getRepre_photofile(), path, fileName);
		}
		dao.insertRecipe(rdto);
		int rec_num = dao.getMaxCount();
		
		for(IngredientDto idto: rdto.getIngreList()) {
			idto.setRec_num(rec_num);
			dao.insertIngre(idto);
		}
		for(RecipeOrderDto odto: rdto.getOrderList()) {
			odto.setRec_num(rec_num);
			if(odto.getPhotofile()!=null) {
				String fileName = new Date().getTime()+"_"+odto.getPhotofile().getOriginalFilename();
				odto.setPhoto(fileName);
				sfw.writeFileRename(odto.getPhotofile(), path, fileName);
			}
			dao.insertOrder(odto);
		}
		
	}
	
	@GetMapping("/recipe/delete")
	public void deleteData(@RequestParam int rec_num, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("WEB-INF/image/recipe");
		RecipeDto dto = dao.getSelectedRecipe(rec_num);
		StringTokenizer st;
		if(dto.getComp_photo()!=null) {
			st = new StringTokenizer(dto.getComp_photo(),",");		
			while(st.hasMoreTokens()) {
				File file = new File(path+"\\"+st.nextToken());
				if(file.exists())
					file.delete();	//������ �����ϸ� �����
			}
		}
		String repre_photo=dto.getRepre_photo();
		File file = new File(path+"\\"+repre_photo);
		if(file.exists())
			file.delete();
		
		List<RecipeOrderDto> olist = dao.getOrder(rec_num);
		for(RecipeOrderDto odto: olist) {
			File ofile = new File(path+"\\"+odto.getPhoto());
			if(ofile.exists())
				ofile.delete();
		}
		
		dao.deleteIngre(rec_num);
		dao.deleteOrder(rec_num);
		dao.deleteRecipe(rec_num);
	}
	
	@GetMapping("/recipe/updateform")
	public RecipeDto updateform(@RequestParam int rec_num) {
		RecipeDto dto = dao.getSelectedRecipe(rec_num);
		List<IngredientDto> ilist = dao.getIngre(rec_num);
		List<RecipeOrderDto> olist = dao.getOrder(rec_num);
		dto.setIngreList(ilist);
		dto.setOrderList(olist);		
		return dto;
	}
	
	@RequestMapping(value="/recipe/update",consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public void update(MultipartHttpServletRequest request, @ModelAttribute("RecipeDto") RecipeDto rdto, BindingResult result) {
		
		
	}
}
