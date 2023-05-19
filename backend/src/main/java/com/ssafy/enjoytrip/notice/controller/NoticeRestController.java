package com.ssafy.enjoytrip.notice.controller;

import com.ssafy.enjoytrip.notice.model.dto.Notice;
import com.ssafy.enjoytrip.notice.model.dto.NoticePageBean;
import com.ssafy.enjoytrip.notice.model.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/rest/notice")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Slf4j
@RequiredArgsConstructor
public class NoticeRestController {
    private final NoticeService noticeService;
    private static final String SUCCESS = "success";

    @GetMapping("/all")
    public ResponseEntity<?> noticeList(NoticePageBean bean) {
        List<Notice> notices = noticeService.listNotice(bean);
        Map<String, Object> res = new HashMap<>();
        res.put("notices", notices);
        res.put("pageLink", bean.getPageLink());
        if (notices == null || notices.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<?> detail(@PathVariable int noticeId) {
        Notice notice = noticeService.getNotice(noticeId);
        noticeService.updateHit(noticeId);
        if (notice == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody Notice notice) {
        noticeService.writeNotice(notice);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> modify(@RequestBody Notice notice) {
        noticeService.modifyNotice(notice);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<?> delete(@PathVariable int noticeId) {
        noticeService.deleteNotice(noticeId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
