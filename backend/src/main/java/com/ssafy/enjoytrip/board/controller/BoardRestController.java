package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.Comment;
import com.ssafy.enjoytrip.board.model.dto.PageBean;
import com.ssafy.enjoytrip.board.model.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/rest/board")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Slf4j
public class BoardRestController {
    private BoardService boardService;
    private static final String SUCCESS = "success";

    @Autowired
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> boardList(PageBean bean) {
        List<Board> boards = boardService.listArticle(bean);
        Map<String, Object> res = new HashMap<>();
        res.put("boards",boards);
        res.put("pageLink",bean.getPageLink());
        if (boards == null || boards.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> detail(@PathVariable int boardId) {
        Board board = boardService.getArticle(boardId);
        boardService.updateHit(boardId);
        if (board == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody Board board) {
        boardService.writeArticle(board);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> modify(@RequestBody Board board) {
        boardService.modifyArticle(board);
        System.out.println(boardService.getArticle(board.getBoardId()).getRegisterTime() + "======================================================================");
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> delete(@PathVariable int boardId) {
        boardService.deleteArticle(boardId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
    @GetMapping("/comment/{boardId}")
    public ResponseEntity<?> getComments(@PathVariable int boardId) {
        List<Comment> list = boardService.listComment(boardId);
        if (list == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/writeComment")
    public ResponseEntity<?> writeComment(@RequestBody Comment comment) {
        boardService.writeComment(comment);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/updateComment")
    public ResponseEntity<?> modifyComment(@RequestBody Comment comment) {
        boardService.modifyComment(comment);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId) {
        boardService.deleteComment(commentId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
