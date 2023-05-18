package com.ssafy.enjoytrip.board.model.dao;

import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.Comment;
import com.ssafy.enjoytrip.board.model.dto.PageBean;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public interface BoardDao {

    void writeArticle(Board boardDto) throws SQLException;

    List<Board> listArticle(PageBean bean) throws SQLException;

    int getTotalArticleCount(PageBean bean) throws SQLException;

    Board getArticle(int articleNo) throws SQLException;

    void updateHit(int articleNo) throws SQLException;

    void modifyArticle(Board boardDto) throws SQLException;

    void deleteArticle(int articleNO) throws SQLException;
    List<Comment> listComment(int boardId) throws SQLException;
    Comment getComment(int commentId) throws SQLException;
    void writeComment(Comment comment) throws SQLException;

    void deleteComment(int commentId) throws SQLException;

    void modifyComment(Comment comment) throws SQLException;
}
