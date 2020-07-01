package spring.form.acorn;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
	
	final int end =5;
	
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
		List<RecipeDto> list = new ArrayList<RecipeDto>();
		int count= 0;
		if(search!=null && search.substring(0, 1).equals("#")) {	//재료검색일 때
				List<Integer> numList = dao.getRec_nums(scroll*5, end, search,sort,food_cate);
				count=dao.getRec_numCount(search,food_cate);
				for(int rec_num : numList) {
					RecipeDto dto = dao.getSelectedRecipe(rec_num);
					list.add(dto);
				}
		}else {		//전체리스트, 분류검색,제목검색 일때
			list = dao.getList(scroll*5,end,search,food_cate,sort);
			count=dao.getRecipeCount(search, food_cate);
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
		//마지막 콤마제거
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
					file.delete();	//파일이 존재하면 지우기
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
	public void update(MultipartHttpServletRequest request, @ModelAttribute("RecipeDto") RecipeDto rdto, BindingResult result,
			@RequestParam(required = false) List<String> delcomp) {
		//기존 정보 저장
		RecipeDto ori_dto = dao.getSelectedRecipe(rdto.getRec_num());
		String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/recipe");
		SpringFileWrite sfw = new SpringFileWrite();
		
		//대표사진 교체
		if(rdto.getRepre_photofile()!=null) {	//교체 함
			String fileName = new Date().getTime()+"_"+rdto.getRepre_photofile().getOriginalFilename();
			rdto.setRepre_photo(fileName);
			sfw.writeFileRename(rdto.getRepre_photofile(), path, fileName);
			//기존 대표 이미지 삭제
			File file = new File(path+"\\"+ori_dto.getRepre_photo());
			if(file.exists())
				file.delete();				
		}else {			
			rdto.setRepre_photo(ori_dto.getRepre_photo());
		}
		
		//완성사진 교체
		if(rdto.getComp_photoList()!=null) { //사진이 추가된 경우	
			List<MultipartFile> curr_compfiles = rdto.getComp_photoList();
			if(delcomp!=null) {	//삭제된 사진이 있을 경우
				//일단 삭제
				for(String del:delcomp) {
					File file = new File(path+"\\"+del);
					if(file.exists())
						file.delete();
				}
				//기존 리스트에서 삭제 목록 지우기
				String [] split = ori_dto.getComp_photo().split(",");
				List<String> files = new ArrayList<String>();
				Collections.addAll(files, split);
				String compfiles = "";
				for(String file:files) {
					if(!(delcomp.contains(file)))
						compfiles+=file+",";
				}
				//새로 추가된 사진 저장 및 추가
				for(MultipartFile comfile:curr_compfiles) {
					String fileName = new Date().getTime()+"_"+comfile.getOriginalFilename();
					sfw.writeFileRename(comfile, path, fileName);
					compfiles+=fileName+",";
				}
				//마지막 콤마 제거
				if(compfiles.length()>0)
					compfiles = compfiles.substring(0,compfiles.length()-1);
				rdto.setComp_photo(compfiles);
			}else {	//삭제된 사진 없이 추가만된 경우
				String files = ori_dto.getComp_photo();
				System.out.println(curr_compfiles.size());
				for(MultipartFile comfile:curr_compfiles) {
					String fileName = new Date().getTime()+"_"+comfile.getOriginalFilename();
					sfw.writeFileRename(comfile, path, fileName);
					files+=","+fileName;
				}
				rdto.setComp_photo(files);
			}		
		}else {	//사진이 추가 되지 않았을 경우
			if(delcomp!=null) {	//삭제된 사진이 있을 경우
				//일단 삭제
				for(String del:delcomp) {
					File file = new File(path+"\\"+del);
					if(file.exists())
						file.delete();
				}
				//기존 리스트에서 삭제 목록 지우기
				String [] split = ori_dto.getComp_photo().split(",");
				List<String> files = new ArrayList<String>();
				Collections.addAll(files, split);
				String compfiles = "";
				for(String file:files) {
					if(!(delcomp.contains(file)))
						compfiles+=file+",";
				}
				//마지막 콤마 제거
				if(compfiles.length()>0)
					compfiles = compfiles.substring(0,compfiles.length()-1);
				rdto.setComp_photo(compfiles);
			}else {	//삭제된 사진도 없을 경우
				rdto.setComp_photo(ori_dto.getComp_photo());
			}			
		}
		
		//기존 이미지들 얻어오기
		List<String> ori_step= dao.getImage(rdto.getRec_num());
		//생존 이미지들 얻어오기
		List<String> live_step = new ArrayList<String>();
		for(RecipeOrderDto dto:rdto.getOrderList()) {
			if(dto.getPhoto()!=null)
				live_step.add(dto.getPhoto());
		}
		//생존하지 못한 놈들 지우기
		for(String step:ori_step) {
			if(!live_step.contains(step)) {
				File file = new File(path+"\\"+step);
				if(file.exists())
					file.delete();
			}				
		}
		//레시피 순서 삭제
		dao.deleteOrder(rdto.getRec_num());
		//순서 다시 저장
		for(RecipeOrderDto odto:rdto.getOrderList()) {
			odto.setRec_num(rdto.getRec_num());
			if(odto.getPhotofile()!=null) {
				String fileName = new Date().getTime()+"_"+odto.getPhotofile().getOriginalFilename();
				odto.setPhoto(fileName);
				sfw.writeFileRename(odto.getPhotofile(), path, fileName);
			}
			dao.insertOrder(odto);
		}
		
		//재료 기존꺼 삭제후 저장
		dao.deleteIngre(rdto.getRec_num());
		for(IngredientDto idto: rdto.getIngreList()) {
			idto.setRec_num(rdto.getRec_num());
			dao.insertIngre(idto);
		}
		//레시피 업데이트
		dao.updateRecipe(rdto);
	}
	
}
