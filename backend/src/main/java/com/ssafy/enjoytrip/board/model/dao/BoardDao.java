package com.ssafy.enjoytrip.board.model.dao;

import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.Comment;
import com.ssafy.enjoytrip.board.model.dto.BoardPageBean;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface BoardDao {

    void writeArticle(Board boardDto) throws SQLException;
    void writeArticleWithNull(Board boardDto) throws SQLException;

    List<Board> listArticle(BoardPageBean bean) throws SQLException;

    int getTotalArticleCount(BoardPageBean bean) throws SQLException;

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
