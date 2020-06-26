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
import data.util.TimeDiffrence;

@RestController
@CrossOrigin
public class RecipeController {
	
	@Autowired
	private RecipeDaoInter dao;
	@Autowired
	private ConnectDaoInter cdao;
	
	final int end =3;
	
	@GetMapping("/recipe/count")
	public HashMap<String, Integer> getCount(@RequestParam int rec_num){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("readcount", dao.getReadcount(rec_num));
		map.put("joayo", cdao.getCountJoayo(rec_num));
		map.put("scrap", cdao.getCountScrap(rec_num));
		
		return map;
	}
	
	@GetMapping("/recipe/list")
	public HashMap<String,Object> getList(@RequestParam(required = false) String search,
			 @RequestParam(required=false, defaultValue="rec_num") String sort,
	         @RequestParam(required = false) String food_cate,@RequestParam(required=false, defaultValue="0") int scroll){    
		System.out.println(scroll);
		System.out.println(search);
		List<RecipeDto> list = new ArrayList<RecipeDto>();
		int count= 0;
		if(search!=null) {	
			if(search.substring(0, 1).equals("#")) {//���˻��� ��
				List<Integer> numList = dao.getRec_nums(scroll*3, end, search,sort);
				count=dao.getRec_numCount(search);
				for(int rec_num : numList) {
					RecipeDto dto = dao.getSelectedRecipe(rec_num);
					list.add(dto);
				}
			}else {	//����˻��� ���
				list = dao.getList(scroll*3,end,search,"",sort);
				count=dao.getRecipeCount(search, "");
			}
		}else {		//��ü����Ʈ�� �з��˻��϶�
			
			list = dao.getList(scroll*3,end,"",food_cate,sort);
			count=dao.getRecipeCount("",food_cate);
		}
		
		TimeDiffrence td = new TimeDiffrence();
		for(RecipeDto dto:list) {
			String timeDiffer = td.formatTimeString(dto.getWriteday());
			dto.setTimeDiffer(timeDiffer);
		}		
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", list);
		return map;	
	}
	
	@GetMapping("/recipe/select")
	public RecipeDto selectData(@RequestParam int rec_num){
		dao.updateReadcount(rec_num);
		RecipeDto dto = dao.getSelectedRecipe(rec_num);
		List<IngredientDto> ilist = dao.getIngre(rec_num);
		List<RecipeOrderDto> olist = dao.getOrder(rec_num);
		dto.setIngreList(ilist);
		dto.setOrderList(olist);
		return dto;
	}
	
	@RequestMapping(value="/recipe/regist",consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public int regist(MultipartHttpServletRequest request, @ModelAttribute("RecipeDto") RecipeDto rdto, BindingResult result) {
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
		
		return dao.getMaxCount();
		
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
		//���� ���� ����
		RecipeDto ori_dto = dao.getSelectedRecipe(rdto.getRec_num());
		List<IngredientDto> ori_ilist = dao.getIngre(rdto.getRec_num());
		List<RecipeOrderDto> ori_olist = dao.getOrder(rdto.getRec_num());
		String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/recipe");
		
	}
}
