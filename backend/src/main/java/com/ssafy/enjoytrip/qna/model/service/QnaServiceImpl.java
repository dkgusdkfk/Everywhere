package com.ssafy.enjoytrip.qna.model.service;

import com.ssafy.enjoytrip.qna.model.dao.QnaDao;
import com.ssafy.enjoytrip.qna.model.dto.Qna;
import com.ssafy.enjoytrip.qna.model.dto.QnaComment;
import com.ssafy.enjoytrip.qna.model.dto.QnaException;
import com.ssafy.enjoytrip.qna.model.dto.QnaPageBean;
import com.ssafy.enjoytrip.util.PageUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {
    private final QnaDao qnaDao;

    @Transactional
    @Override
    public void writeQna(Qna qnaDto) {
        try {
            qnaDao.writeQna(qnaDto);
        } catch (SQLException e) {
            throw new QnaException("게시글 등록 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public List<Qna> listQna(QnaPageBean bean) {
        try {
            int total = qnaDao.getTotalQnaCount(bean);
            PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
            bean.setPageLink(page.getPageBar());
            return qnaDao.listQna(bean);
        } catch (SQLException e) {
            throw new QnaException("게시글 목록 조회 중 오류");
        }
    }

    @Override
    @Transactional
    public Qna getQna(int qnaId) {
        try {
            return qnaDao.getQna(qnaId);
        } catch (SQLException e) {
            throw new QnaException("게시글 조회 중 오류");
        }
    }

    @Override
    @Transactional
    public void updateHit(int qnaId) {
        try {
            qnaDao.updateHit(qnaId);
        } catch (SQLException e) {
            throw new QnaException("조회수 업데이트 중 오류");
        }
    }

    @Override
    @Transactional
    public void modifyQna(Qna qnaDto) {
        try {
            qnaDao.modifyQna(qnaDto);
        } catch (SQLException e) {
            throw new QnaException("게시글 수정 중 오류");
        }
    }

    @Override
    @Transactional
    public void deleteQna(int qnaId) {
        try {
            qnaDao.deleteQna(qnaId);
        } catch (SQLException e) {
            throw new QnaException("게시글 삭제 중 오류");
        }
    }

    @Override
    @Transactional
    public QnaComment getComment(int commentId) {
        try {
            return qnaDao.getComment(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new QnaException("댓글 조회중 오류 발생");
        }
    }


    @Override
    @Transactional
    public void writeComment(QnaComment comment) {
        try {
            qnaDao.writeComment(comment);
        } catch (SQLException e) {
            throw new QnaException("댓글 등록 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public void modifyComment(QnaComment comment) {
        try {
            if(qnaDao.getComment(comment.getCommentId())==null){
                throw new QnaException("commentId가 잘못되었습니다");
            }
            qnaDao.modifyComment(comment);
        } catch (SQLException e) {
            throw new QnaException("댓글 수정 중 오류");
        }
    }

    @Override
    @Transactional
    public void deleteComment(int commentId) {
        try {
            qnaDao.deleteComment(commentId);
        } catch (SQLException e) {
            throw new QnaException("댓글 삭제 중 오류");
        }
    }

    @Override
    @Transactional
    public List<QnaComment> listComment(int qnaId) {
        try {
            return qnaDao.listComment(qnaId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new QnaException("댓글 불러오기중 오류");
        }
    }
}

