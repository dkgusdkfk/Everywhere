package com.ssafy.enjoytrip.notice.model.service;

import com.ssafy.enjoytrip.notice.model.dao.NoticeDao;
import com.ssafy.enjoytrip.notice.model.dto.Notice;
import com.ssafy.enjoytrip.notice.model.dto.NoticeException;
import com.ssafy.enjoytrip.notice.model.dto.PageBean;
import com.ssafy.enjoytrip.util.PageUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeDao noticeDao;

    @Override
    public void writeNotice(Notice noticeDto) {
        try {
            noticeDao.writeNotice(noticeDto);
        } catch (SQLException e) {
            throw new NoticeException("게시글 등록 중 오류 발생");
        }
    }

    @Override
    public List<Notice> listNotice(PageBean bean) {
        try {
            int total = noticeDao.getTotalNoticeCount(bean);
            PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
            bean.setPageLink(page.getPageBar());
            return noticeDao.listNotice(bean);
        } catch (SQLException e) {
            throw new NoticeException("게시글 목록 조회 중 오류");
        }
    }

    @Override
    public Notice getNotice(int noticeId) {
        try {
            return noticeDao.getNotice(noticeId);
        } catch (SQLException e) {
            throw new NoticeException("게시글 조회 중 오류");
        }
    }

    @Override
    public void updateHit(int noticeId) {
        try {
            noticeDao.updateHit(noticeId);
        } catch (SQLException e) {
            throw new NoticeException("조회수 업데이트 중 오류");
        }
    }

    @Override
    public void modifyNotice(Notice noticeDto) {
        try {
            noticeDao.modifyNotice(noticeDto);
        } catch (SQLException e) {
            throw new NoticeException("게시글 수정 중 오류");
        }
    }

    @Override
    public void deleteNotice(int noticeId) {
        try {
            noticeDao.deleteNotice(noticeId);
        } catch (SQLException e) {
            throw new NoticeException("게시글 삭제 중 오류");
        }
    }
}

