package com.ssafy.enjoytrip.notice.model.dao;

import com.ssafy.enjoytrip.notice.model.dto.NoticePageBean;
import com.ssafy.enjoytrip.notice.model.dto.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface NoticeDao {

    void writeNotice(Notice noticeDto) throws SQLException;

    List<Notice> listNotice(NoticePageBean bean) throws SQLException;

    int getTotalNoticeCount(NoticePageBean bean) throws SQLException;

    Notice getNotice(int noticeNo) throws SQLException;

    void updateHit(int noticeNo) throws SQLException;

    void modifyNotice(Notice noticeDto) throws SQLException;

    void deleteNotice(int noticeNO) throws SQLException;

}
