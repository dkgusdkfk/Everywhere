package com.ssafy.enjoytrip.notice.model.dao;

import com.ssafy.enjoytrip.notice.model.dto.Notice;
import com.ssafy.enjoytrip.notice.model.dto.PageBean;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public interface NoticeDao {

    void writeNotice(Notice noticeDto) throws SQLException;

    List<Notice> listNotice(PageBean bean) throws SQLException;

    int getTotalNoticeCount(PageBean bean) throws SQLException;

    Notice getNotice(int noticeNo) throws SQLException;

    void updateHit(int noticeNo) throws SQLException;

    void modifyNotice(Notice noticeDto) throws SQLException;

    void deleteNotice(int noticeNO) throws SQLException;

}
