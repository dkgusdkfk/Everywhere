package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.model.dto.Board;
import com.ssafy.enjoytrip.board.model.dto.Comment;
import com.ssafy.enjoytrip.board.model.dto.PageBean;
import com.ssafy.enjoytrip.board.model.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public void boardList(PageBean bean, Model model) {
        log.debug("boardlist...................pageBean:{}", bean);
        List<Board> boards = boardService.listArticle(bean);
        log.debug("boardlist....................{}", boards);
        model.addAttribute("boards", boards);
    }

    @GetMapping("/write")
    public void write() {
        log.debug("boardService.................. write 화면으로 이동");
    }

    @PostMapping("/write")
    public String write(Board board) {
        log.debug("board.................. write");
        log.debug("board.................. 요청데이터 board : {}", board.toString());

        boardService.writeArticle(board);
        return "redirect:/board/list";
    }

    @GetMapping("/view")
    public void detail(int boardId, PageBean bean, Model model) {
        Board board = boardService.getArticle(boardId);
        boardService.updateHit(boardId);
        model.addAttribute("board", board);
        model.addAttribute("comments", boardService.listComment(boardId));
    }

    @GetMapping("/modify")
    public void modify(int boardId, PageBean bean, Model model) {
        log.debug("board modify.................");
        Board board = boardService.getArticle(boardId);
        log.debug("update....................{}", board);
        model.addAttribute("board", board);
        log.debug("board................. modify 화면으로 이동");
    }

    @PostMapping("/modify")
    public String modify(Board board, PageBean bean, RedirectAttributes ra) {
        log.debug("board................. update");
        log.debug("board................. 요청데이터 board : {}", board.toString());
        boardService.modifyArticle(board);
        ra.addAttribute("pageNo", bean.getPageNo());
        ra.addAttribute("key", bean.getKey());
        ra.addAttribute("word", bean.getWord());
        return "redirect:/board/view?boardId=" + board.getBoardId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int boardId) {
        log.debug("book delete..................");
        boardService.deleteArticle(boardId);
        return "redirect:/board/list";
    }

    @PostMapping("/writeComment")
    public String writeComment(Comment comment) {
        boardService.writeComment(comment);
        return "redirect:/board/view?boardId=" + comment.getBoardId();
    }

    @PutMapping("/modifyComment")
    public String modifyComment(@RequestParam int boardId, @RequestParam int commentId, String content) {
        Comment comment = boardService.getComment(commentId);
        comment.setContent(content);
        boardService.modifyComment(comment);
        return "redirect:/board/view?boardId=" + boardId;
    }

    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam int boardId,@RequestParam int commentId) {
        log.debug("댓글 delete..................");
        boardService.deleteComment(commentId);
        return "redirect:/board/view?boardId=" + boardId;
    }
}
