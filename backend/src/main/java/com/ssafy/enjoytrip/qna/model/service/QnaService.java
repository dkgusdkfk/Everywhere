package com.ssafy.enjoytrip.qna.model.service;

import com.ssafy.enjoytrip.qna.model.dto.Qna;
import com.ssafy.enjoytrip.qna.model.dto.QnaPageBean;
import com.ssafy.enjoytrip.qna.model.dto.QnaComment;

import java.util.List;

public interface QnaService {

	void writeQna(Qna qnaDto);
	List<Qna> listQna(QnaPageBean bean);
	Qna getQna(int qnaId);
	void updateHit(int qnaId);
	void modifyQna(Qna qnaDto);
	void deleteQna(int qnaId);
	QnaComment getComment(int commentId);
	void writeComment(QnaComment comment);
	void modifyComment(QnaComment comment);
	void deleteComment(int commentId);
	List<QnaComment> listComment(int qnaId);

}
