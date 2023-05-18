package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.model.dao.BoardDao;
import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.BoardException;
import com.ssafy.enjoytrip.board.model.dto.Comment;
import com.ssafy.enjoytrip.board.model.dto.PageBean;
import com.ssafy.enjoytrip.util.PageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardDao boardDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public BoardServiceImpl() {
    }

    @Override
    public void writeArticle(Board boardDto) {
        try {
            boardDao.writeArticle(boardDto);
        } catch (SQLException e) {
            throw new BoardException("게시글 등록 중 오류 발생");
        }
    }

    @Override
    public List<Board> listArticle(PageBean bean) {
        try {
            int total = boardDao.getTotalArticleCount(bean);
            PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
            bean.setPageLink(page.getPageBar());
            return boardDao.listArticle(bean);
        } catch (SQLException e) {
            throw new BoardException("게시글 목록 조회 중 오류");
        }
    }

    @Override
    public Board getArticle(int boardId) {
        try {
            return boardDao.getArticle(boardId);
        } catch (SQLException e) {
            throw new BoardException("게시글 조회 중 오류");
        }
    }

    @Override
    public void updateHit(int boardId) {
        try {
            boardDao.updateHit(boardId);
        } catch (SQLException e) {
            throw new BoardException("조회수 업데이트 중 오류");
        }
    }

    @Override
    public void modifyArticle(Board boardDto) {
        try {
            boardDao.modifyArticle(boardDto);
        } catch (SQLException e) {
            throw new BoardException("게시글 수정 중 오류");
        }
    }

    @Override
    public void deleteArticle(int boardId) {
        try {
            boardDao.deleteArticle(boardId);
        } catch (SQLException e) {
            throw new BoardException("게시글 삭제 중 오류");
        }
    }
    @Override
    public Comment getComment(int commentId){
        try {
            return boardDao.getComment(commentId);
        }catch (SQLException e){
            e.printStackTrace();
            throw  new BoardException("댓글 조회중 오류 발생");
        }
    }



    @Override
    public void writeComment(Comment comment) {
        try {
            boardDao.writeComment(comment);
        } catch (SQLException e) {
            throw new BoardException("댓글 등록 중 오류 발생");
        }
    }

    @Override
    public void modifyComment(Comment comment) {
        try {

            boardDao.modifyComment(comment);
        } catch (SQLException e) {
            throw new BoardException("댓글 수정 중 오류");
        }
    }

    @Override
    public void deleteComment(int commentId) {
        try {
            boardDao.deleteComment(commentId);
        } catch (SQLException e) {
            throw new BoardException("댓글 삭제 중 오류");
        }
    }

    @Override
    public List<Comment> listComment(int boardId) {
        try{
            return boardDao.listComment(boardId);
        }catch (SQLException e){
            e.printStackTrace();
            throw new BoardException("댓글 불러오기중 오류");
        }
    }
}

