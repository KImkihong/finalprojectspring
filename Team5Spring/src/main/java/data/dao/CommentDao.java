package data.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.CommentDto;

@Repository
public class CommentDao extends SqlSessionDaoSupport implements CommentDaoInter {

	@Override
	public int getMaxNum() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("maxNumOfComment");
	}

	@Override
	public void updateRestep(int regroup, int restep) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("regroup", regroup);
		map.put("restep", restep);
		getSqlSession().update("updateRestepOfCommenet", map);
	}

	@Override
	public void insertComment(CommentDto dto) {
		// TODO Auto-generated method stub
		int regroup =dto.getRegroup();
		int restep = dto.getRestep();
		int relevel = dto.getRelevel();
		//System.out.println(dto.getNum());
		
		//�������� ������� �Ǵ�
		if(dto.getCom_num()==0) {	//����
			//������ ��� �׷��ȣ�� num�� max�� +1
			regroup = this.getMaxNum()+1;
			relevel = 0;
			restep = 0;
		}else {
			//���� �׷� �� restep ���� ū �����ʹ� 1����
			this.updateRestep(regroup, restep);
			//db���� +�ؼ� ����
			relevel=1;
			restep++;
		}
		
		//dto�� ���� ���� 3���� �־��ֱ�
		dto.setRegroup(regroup);
		dto.setRelevel(relevel);
		dto.setRestep(restep);
		
		getSqlSession().insert("insertOfComment", dto);
	}

	@Override
	public List<CommentDto> getCommentlist(int rec_num,int start, int end) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("start",start);
		map.put("end",end);
		map.put("rec_num",rec_num);
		return getSqlSession().selectList("getListOfComment", map);
	}

	@Override
	public void deleteCommenet(int com_num) {
		// TODO Auto-generated method stub
		getSqlSession().update("deleteComment", com_num);
	}

	@Override
	public CommentDto getComment(int com_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getComment", com_num) ;
	}

	@Override
	public int getCount(int rec_num) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
