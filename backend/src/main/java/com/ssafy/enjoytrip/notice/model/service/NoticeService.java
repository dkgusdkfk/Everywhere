package com.ssafy.enjoytrip.notice.model.service;

import com.ssafy.enjoytrip.notice.model.dto.Notice;
import com.ssafy.enjoytrip.notice.model.dto.PageBean;

import java.util.List;

public interface NoticeService {

	void writeNotice(Notice boardDto);
	List<Notice> listNotice(PageBean bean);
	Notice getNotice(int boardId);
	void updateHit(int boardId);
	void modifyNotice(Notice boardDto);
	void deleteNotice(int boardId);

}
