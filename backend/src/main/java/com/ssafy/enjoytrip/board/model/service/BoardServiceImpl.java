package com.ssafy.enjoytrip.board.model.service;

import com.ssafy.enjoytrip.board.model.dao.BoardDao;
import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.BoardException;
import com.ssafy.enjoytrip.board.model.dto.Comment;
import com.ssafy.enjoytrip.board.model.dto.BoardPageBean;
import com.ssafy.enjoytrip.util.PageUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;

    @Transactional
    @Override
    public void writeArticle(Board boardDto) {
        try {
            log.debug("planId ------------------------------------------------- :{}",boardDto.getPlanId());
            if(boardDto.getPlanId()==0) boardDao.writeArticleWithNull(boardDto);
            else boardDao.writeArticle(boardDto);
        } catch (SQLException e) {
            throw new BoardException("게시글 등록 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public List<Board> listArticle(BoardPageBean bean) {
        try {
            List<Board> first = boardDao.listArticle(bean);
            int total = boardDao.getTotalArticleCount(bean);
            PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
            bean.setPageLink(page.getPageBar());
            return first;
        } catch (SQLException e) {
            throw new BoardException("게시글 목록 조회 중 오류");
        }
    }

    @Override
    @Transactional
    public Board getArticle(int boardId) {
        try {
            return boardDao.getArticle(boardId);
        } catch (SQLException e) {
            throw new BoardException("게시글 조회 중 오류");
        }
    }

    @Override
    @Transactional
    public void updateHit(int boardId) {
        try {
            boardDao.updateHit(boardId);
        } catch (SQLException e) {
            throw new BoardException("조회수 업데이트 중 오류");
        }
    }

    @Override
    @Transactional
    public void modifyArticle(Board boardDto) {
        try {
            boardDao.modifyArticle(boardDto);
        } catch (SQLException e) {
            throw new BoardException("게시글 수정 중 오류");
        }
    }

    @Override
    @Transactional
    public void deleteArticle(int boardId) {
        try {
            boardDao.deleteArticle(boardId);
        } catch (SQLException e) {
            throw new BoardException("게시글 삭제 중 오류");
        }
    }

    @Override
    @Transactional
    public Comment getComment(int commentId) {
        try {
            return boardDao.getComment(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BoardException("댓글 조회중 오류 발생");
        }
    }


    @Override
    @Transactional
    public void writeComment(Comment comment) {
        try {
            boardDao.writeComment(comment);
        } catch (SQLException e) {
            throw new BoardException("댓글 등록 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public void modifyComment(Comment comment) {
        try {

            boardDao.modifyComment(comment);
        } catch (SQLException e) {
            throw new BoardException("댓글 수정 중 오류");
        }
    }

    @Override
    @Transactional
    public void deleteComment(int commentId) {
        try {
            boardDao.deleteComment(commentId);
        } catch (SQLException e) {
            throw new BoardException("댓글 삭제 중 오류");
        }
    }

    @Override
    @Transactional
    public List<Comment> listComment(int boardId) {
        try {
            return boardDao.listComment(boardId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BoardException("댓글 불러오기중 오류");
        }
    }
}

