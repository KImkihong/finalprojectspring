package data.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class CommentDto {
	private int com_num;
	private String content;
	private MultipartFile imagefile;
	private String image;
	private int regroup;
	private int restep;
	private int relevel;
	private Timestamp com_writeday;
	private int rec_num;
	private String email;
	
}
