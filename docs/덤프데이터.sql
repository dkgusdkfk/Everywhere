
use everywhere;

insert into user values('ssafy','1234','김기헌','ssafy@ssafy.com','1','1',null);
insert into user values('admin','1','admin','admin@ssafy.com','1','1',null);
insert into user values('dkgusdkfk','1234','백아현','ss@ssafy.com','1','4',null);
insert into user values('1','1','1','1@1.com','1','2',null);

insert into board values(1,'admin','게시판 테스트','test content',default,default);
insert into board values(2,'ssafy','안녕하세요','안녕하세요',default,default);
insert into board values(3,'dkgusdkfk','첫 글','처음',default,default);

insert into comment values(1,1,'ssafy','test',default);
insert into comment values(1,2,'admin','댓글',default);
insert into comment values(1,3,'dkgusdkfk','hello',default);
insert into comment values(1,4,'dkgusdkfk','ssafy',default);
insert into comment values(2,5,'ssafy','test',default);
insert into comment values(3,6,'ssafy','test',default);

insert into hotplace values(125409,3);
insert into hotplace values(125415,1);
insert into hotplace values(125419,1);

insert into likelist values(125409,'admin');
insert into likelist values(125409,'1');
insert into likelist values(125409,'dkgusdkfk');
insert into likelist values(125415,'dkgusdkfk');
insert into likelist values(125415,'dkgusdkfk');

insert into notice values(1,'admin','공지사항 테스트','test',default,default);
insert into notice values(2,'admin','공지사항','공지1',default,default);
insert into notice values(3,'admin','공지사항2','공지2',default,default);

insert into qna values(1,'ssafy','여행질문','질문있어요',default,default);
insert into qna values(2,'1','제주도 질문','질문있어요',default,default);
insert into qna values(3,'dkgusdkfk','가평 질문','질문있어요',default,default);

insert into qna_comment values(1,1,'admin','네',default);
insert into qna_comment values(1,2,'ssafy','네',default);
insert into qna_comment values(1,3,'dkgusdkfk','네',default);
insert into qna_comment values(2,4,'dkgusdkfk','네',default);

