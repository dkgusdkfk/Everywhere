package com.ssafy.enjoytrip.qna.model.dao;

import com.ssafy.enjoytrip.qna.model.dto.Qna;
import com.ssafy.enjoytrip.qna.model.dto.QnaPageBean;
import com.ssafy.enjoytrip.qna.model.dto.QnaComment;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface QnaDao {

    void writeQna(Qna qnaDto) throws SQLException;

    List<Qna> listQna(QnaPageBean bean) throws SQLException;

    int getTotalQnaCount(QnaPageBean bean) throws SQLException;

    Qna getQna(int qnaNo) throws SQLException;

    void updateHit(int qnaNo) throws SQLException;

    void modifyQna(Qna qnaDto) throws SQLException;

    void deleteQna(int qnaNO) throws SQLException;
    List<QnaComment> listComment(int qnaId) throws SQLException;
    QnaComment getComment(int commentId) throws SQLException;
    void writeComment(QnaComment comment) throws SQLException;

    void deleteComment(int commentId) throws SQLException;

    void modifyComment(QnaComment comment) throws SQLException;
}
