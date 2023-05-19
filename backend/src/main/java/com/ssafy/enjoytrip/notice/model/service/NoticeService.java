package com.ssafy.enjoytrip.notice.model.service;

import com.ssafy.enjoytrip.notice.model.dto.NoticePageBean;
import com.ssafy.enjoytrip.notice.model.dto.Notice;

import java.util.List;

public interface NoticeService {

	void writeNotice(Notice boardDto);
	List<Notice> listNotice(NoticePageBean bean);
	Notice getNotice(int boardId);
	void updateHit(int boardId);
	void modifyNotice(Notice boardDto);
	void deleteNotice(int boardId);

}
