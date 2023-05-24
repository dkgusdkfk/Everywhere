package com.ssafy.enjoytrip.qna.controller;

import com.ssafy.enjoytrip.qna.model.dto.Qna;
import com.ssafy.enjoytrip.qna.model.dto.QnaPageBean;
import com.ssafy.enjoytrip.qna.model.dto.QnaComment;
import com.ssafy.enjoytrip.qna.model.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/qna")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Slf4j
@RequiredArgsConstructor
public class QnaRestController {
    private final QnaService qnaService;
    private static final String SUCCESS = "success";


    @GetMapping("/all")
    public ResponseEntity<?> qnaList(QnaPageBean bean) {
        List<Qna> qnas = qnaService.listQna(bean);
        Map<String, Object> res = new HashMap<>();
        res.put("qnas",qnas);
        res.put("pageLink",bean.getPageLink());
        if (qnas == null || qnas.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{qnaId}")
    public ResponseEntity<?> detail(@PathVariable int qnaId) {
        Qna qna = qnaService.getQna(qnaId);
        qnaService.updateHit(qnaId);
        if (qna == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(qna, HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody Qna qna) {
        qnaService.writeQna(qna);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> modify(@RequestBody Qna qna) {
        qnaService.modifyQna(qna);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/{qnaId}")
    public ResponseEntity<?> delete(@PathVariable int qnaId) {
        qnaService.deleteQna(qnaId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
    @GetMapping("/comment/{qnaId}")
    public ResponseEntity<?> getQnaComments(@PathVariable int qnaId) {
        List<QnaComment> list = qnaService.listComment(qnaId);
        if (list == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/writeComment")
    public ResponseEntity<?> writeQnaComment(@RequestBody QnaComment comment) {
        qnaService.writeComment(comment);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/updateComment")
    public ResponseEntity<?> modifyQnaComment(@RequestBody QnaComment comment) {
        qnaService.modifyComment(comment);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<?> deleteQnaComment(@PathVariable int commentId) {
        qnaService.deleteComment(commentId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
