--------------------------------------------------------
--  DDL for Table MEMBER
--------------------------------------------------------
CREATE TABLE "MEMBER"
(	"MEMBER_ID" VARCHAR2(20 BYTE) primary key,
     "MEMBER_PW" VARCHAR2(30 BYTE),
     "MEMBER_NAME" VARCHAR2(50 BYTE),
     "MEMBER_GENDER" VARCHAR2(10 BYTE),
     "TEL1" VARCHAR2(20 BYTE),
     "TEL2" VARCHAR2(20 BYTE),
     "TEL3" VARCHAR2(20 BYTE),
     "HP1" VARCHAR2(20 BYTE),
     "HP2" VARCHAR2(20 BYTE),
     "HP3" VARCHAR2(20 BYTE),
     "SMSSTS_YN" VARCHAR2(20 BYTE),
     "EMAIL1" VARCHAR2(20 BYTE),
     "EMAIL2" VARCHAR2(20 BYTE),
     "EMAILSTS_YN" VARCHAR2(20 BYTE),
     "ZIPCODE" VARCHAR2(20 BYTE),
     "ROADADDRESS" VARCHAR2(500 BYTE),
     "JIBUNADDRESS" VARCHAR2(500 BYTE),
     "DETAILADDRESS" VARCHAR2(500 BYTE),
     "MEMBER_BIRTH_Y" VARCHAR2(20 BYTE),
     "MEMBER_BIRTH_M" VARCHAR2(20 BYTE),
     "MEMBER_BIRTH_D" VARCHAR2(20 BYTE),
     "MEMBER_BIRTH_SL" VARCHAR2(20 BYTE),
     "JOINDATE" DATE DEFAULT sysdate,
     "DEL_YN" VARCHAR2(20 BYTE) DEFAULT 'N'
) ;

--------------------------------------------------------
--  DDL for Table GOODS
--------------------------------------------------------
CREATE TABLE "GOODS"
(	"GOODS_ID" NUMBER(20,0) primary key,
     "GOODS_SORT" VARCHAR2(50 BYTE),
     "GOODS_TITLE" VARCHAR2(100 BYTE),
     "GOODS_WRITER" VARCHAR2(50 BYTE),
     "GOODS_PUBLISHER" VARCHAR2(50 BYTE),
     "GOODS_PRICE" NUMBER(10,0),
     "GOODS_SALES_PRICE" NUMBER(10,0),
     "GOODS_POINT" NUMBER(10,0),
     "GOODS_PUBLISHED_DATE" DATE,
     "GOODS_TOTAL_PAGE" NUMBER(5,0),
     "GOODS_ISBN" VARCHAR2(50 BYTE),
     "GOODS_DELIVERY_PRICE" NUMBER(10,0),
     "GOODS_DELIVERY_DATE" DATE,
     "GOODS_STATUS" VARCHAR2(50 BYTE),
     "GOODS_INTRO" VARCHAR2(2000 BYTE),
     "GOODS_WRITER_INTRO" VARCHAR2(2000 BYTE),
     "GOODS_PUBLISHER_COMMENT" VARCHAR2(2000 BYTE),
     "GOODS_RECOMMENDATION" VARCHAR2(2000 BYTE),
     "GOODS_CONTENTS_ORDER" CLOB,
--    product arrive date ????????
     "GOODS_PADATE" DATE DEFAULT sysdate
) ;

--------------------------------------------------------
--  DDL for Table GOODS_DETAIL_IMAGE
--------------------------------------------------------
CREATE TABLE "GOODS_DETAIL_IMAGE"
(	"IMAGE_ID" NUMBER(20,0) primary key,
     "GOODS_ID" NUMBER(20,0),
     "FILENAME" VARCHAR2(50 BYTE),
--    registration ID, ????? ?????
     "REG_ID" VARCHAR2(20 BYTE),
     "FILETYPE" VARCHAR2(40 BYTE),
    --    registration DATE, ?????
     "RGDATE" DATE DEFAULT sysdate
) ;

--------------------------------------------------------
--  DDL for Table SHOPPING_CART
--------------------------------------------------------
CREATE TABLE "SHOPPING_CART"
(	"CART_ID" NUMBER(10,0) primary key,
     "GOODS_ID" NUMBER(20,0),
     "MEMBER_ID" VARCHAR2(20 BYTE),
     "DEL_YN" VARCHAR2(20 BYTE) DEFAULT 'N',
     "CREDATE" DATE DEFAULT sysdate,
     "CART_GOODS_QTY" NUMBER(4,0) DEFAULT 1
) ;

--------------------------------------------------------
--  DDL for Table GOODS_ORDER
--------------------------------------------------------
CREATE TABLE "GOODS_ORDER"
--SEQ, sequence.
(	"ORDER_SEQ_NUM" NUMBER(20,0) primary key,
     "ORDER_ID" NUMBER(20,0),
     "MEMBER_ID" VARCHAR2(20 BYTE),
     "GOODS_ID" NUMBER(20,0),
     "ORDERER_NAME" VARCHAR2(50 BYTE),
     "GOODS_TITLE" VARCHAR2(100 BYTE),
--  QTY,  Quantity.
     "ORDER_GOODS_QTY" NUMBER(5,0),
     "GOODS_SALES_PRICE" NUMBER(5,0),
     "GOODS_FILENAME" VARCHAR2(60 BYTE),
     "RECEIVER_NAME" VARCHAR2(50 BYTE),
     "RECEIVER_HP1" VARCHAR2(20 BYTE),
     "RECEIVER_HP2" VARCHAR2(20 BYTE),
     "RECEIVER_HP3" VARCHAR2(20 BYTE),
     "RECEIVER_TEL1" VARCHAR2(20 BYTE),
     "RECEIVER_TEL2" VARCHAR2(20 BYTE),
     "RECEIVER_TEL3" VARCHAR2(20 BYTE),
     "DELIVERY_ADDRESS" VARCHAR2(500 BYTE),
     "DELIVERY_METHOD" VARCHAR2(40 BYTE),
     "DELIVERY_MESSAGE" VARCHAR2(300 BYTE),
     "GIFT_WRAPPING" VARCHAR2(20 BYTE),
     "PAY_METHOD" VARCHAR2(200 BYTE),
     "CARD_COM_NAME" VARCHAR2(50 BYTE),
     "CARD_PAY_MONTH" VARCHAR2(20 BYTE),
     "PAY_ORDERER_HP_NUM" VARCHAR2(20 BYTE),
     "DELIVERY_STATE" VARCHAR2(20 BYTE) DEFAULT 'delivery_prepared',
     "PAY_ORDER_TIME" DATE DEFAULT sysdate,
     "ORDERER_HP" VARCHAR2(50 BYTE)
) ;


-------------------------------------------------------------------------------
--GOODS_DETAIL_IMAGE;
-------------------------------------------------------------------------------
select * from goods_detail_image;
-- rename GOODS_DETAIL_IMAGE to GOODS_DETAIL_IMAGE;

Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (1,1,'도둑맞은 집중력.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (2,1,'도둑맞은 집중력_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (3,2,'만일 내가 인생을 다시 산다면.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (4,2,'만일 내가 인생을 다시 산다면_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (5,3,'모든 삶은 흐른다.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (6,3,'모든 삶은 흐른다_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (7,4,'내면소통.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (8,4,'내면소통_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (9,5,'도파민네이션.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (10,5,'도파민네이션_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (11,6,'지적 대화를 위한 넓고 얕은 지식 1.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (12,6,'지적 대화를 위한 넓고 얕은 지식 1_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (13,7,'당신도 느리게 나이 들 수 있습니다.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (14,7,'당신도 느리게 나이 들 수 있습니다_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (15,8,'사피엔스.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (16,8,'사피엔스_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (17,9,'유현준의 인문 건축 기행.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (18,9,'유현준의 인문 건축 기행_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (19,10,'클루지.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (20,10,'클루지_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (21,11,'지적 대화를 위한 넓고 얕은 지식_제로 편.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (22,11,'지적 대화를 위한 넓고 얕은 지식_제로 편_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (23,12,'남에게 보여주려고 인생을 낭비하지 마라.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (24,12,'남에게 보여주려고 인생을 낭비하지 마라_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (25,13,'생각이 너무 많은 어른들을 위한 심리학.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (26,13,'생각이 너무 많은 어른들을 위한 심리학_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (27,14,'미움받을 용기.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (28,14,'미움받을 용기_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (29,15,'지적 대화를 위한 넓고 얕은 지식 2.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (30,15,'지적 대화를 위한 넓고 얕은 지식 2_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgdate)
values (31,16,'마이클 샌델 정의란 무엇인가.jpg','admin','main_image',to_date('24/04/01','RR/MM/DD'));
Insert into GOODS_DETAIL_IMAGE (IMAGE_ID,GOODS_ID,FILENAME,REG_ID,FILETYPE,rgDATE)
values (32,16,'마이클 샌델 정의란 무엇인가_상세.jpg','admin','detail_image1',to_date('24/04/01','RR/MM/DD'));


-------------------------------------------------------------------------------
--goods;
-------------------------------------------------------------------------------
-- select * from goods;
--delete from goods where goods_id = 1;
-- padate = product arrive date
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (1,'인문','도둑맞은 집중력','작가1','출판1',20000,18000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (2,'인문','만일 내가 인생을 다시 산다면','작가1','출판2',24000,19000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (3,'인문','모든 삶은 흐른다','작가3','출판3',10000,9000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (4,'인문','내면소통','작가4','출판4',15000,13000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (5,'인문','도파민네이션','작가5','출판5',30000,28000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (6,'인문','지적 대화를 위한 넓고 얕은 지식 1','작가6','출판6',23000,18000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (7,'인문','당신도 느리게 나이 들 수 있습니다','작가7','출판7',10000,8000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (8,'인문','사피엔스','작가8','출판8',33000,28000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (9,'인문','유현준의 인문 건축 기행','작가9','출판9',9000,8000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (10,'인문','클루지','작가10','출판10',10000,8000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (11,'인문','지적 대화를 위한 넓고 얕은 지식_제로 편','작가11','출판11',22000,11000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (12,'인문','남에게 보여주려고 인생을 낭비하지 마라','작가12','출판12',30000,18000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (13,'인문','생각이 너무 많은 어른들을 위한 심리학','작가13','출판13',24000,13000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (24,'인문','미움받을 용기','작가24','출판24',10000,10000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (15,'인문','지적 대화를 위한 넓고 얕은 지식 2','작가15','출판15',15000,24000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));
Insert into GOODS (GOODS_ID,GOODS_SORT,GOODS_TITLE,GOODS_WRITER,GOODS_PUBLISHER,GOODS_PRICE,GOODS_SALES_PRICE,GOODS_POINT,GOODS_PUBLISHED_DATE,GOODS_TOTAL_PAGE,GOODS_ISBN,GOODS_DELIVERY_PRICE,GOODS_DELIVERY_DATE,GOODS_STATUS,GOODS_INTRO,GOODS_WRITER_INTRO,GOODS_PUBLISHER_COMMENT,GOODS_RECOMMENDATION,goods_contents_order,goods_padate)
values (16,'인문','정의란 무엇인가','작가16','출판16',20000,18000,1000,to_date('24/11/20','RR/MM/DD'),300,'2112121',3000,to_date('24/06/03','RR/MM/DD'),'steadyseller','intro','intro','intro','intro','index',to_date('24/07/01','RR/MM/DD'));

-------------------------------------------------------------------------------
--member;
-------------------------------------------------------------------------------
-- select * from member;
----alter table member add detailaddress VARCHAR2(500 byte);
Insert into MEMBER (MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_GENDER,TEL1,TEL2,TEL3,HP1,HP2,HP3,SMSSTS_YN,EMAIL1,EMAIL2,EMAILSTS_YN,ZIPCODE,ROADADDRESS,JIBUNADDRESS, detailaddress, MEMBER_BIRTH_Y,MEMBER_BIRTH_M,MEMBER_BIRTH_D,member_birth_sl,JOINDATE,DEL_YN)
values ('admin','1212','어드민','01','02','1111','1111','010','1111','1111','Y','admin','naver.com','Y','06253','서울 강남구 강남대로 298 (역삼동)','서울 강남구 역삼동 838','럭키빌딩 101호','2000','5','10','2',to_date('24/03/16','RR/MM/DD'),'N');
Insert into MEMBER (MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_GENDER,TEL1,TEL2,TEL3,HP1,HP2,HP3,SMSSTS_YN,EMAIL1,EMAIL2,EMAILSTS_YN,ZIPCODE,ROADADDRESS,JIBUNADDRESS, detailaddress, MEMBER_BIRTH_Y,MEMBER_BIRTH_M,MEMBER_BIRTH_D,member_birth_sl,JOINDATE,DEL_YN)
values ('lee','1212','이병승','01','02','2222','2222','010','2222','2222','Y','lee','naver.com','Y','13547','경기 성남시 분당구 고기로 25 (동원동)','경기 성남시 분당구 동원동 79-1','럭키빌딩 101호','2005','8','10','1',to_date('24/03/25','RR/MM/DD'),'N');

--select * from "ORDER";
--rename "ORDER" to "GOODS_ORDER";
select * from goods_order;
Insert into goods_order(order_seq_num,order_id,member_id,goods_id,orderer_name,goods_title,order_goods_qty,goods_sales_price,goods_filename,receiver_name,receiver_hp1,receiver_hp2,receiver_hp3,receiver_tel1,receiver_tel2,RECEIVER_TEL3,delivery_address,delivery_method,delivery_message,gift_wrapping,pay_method,card_com_name,card_pay_month,pay_orderer_hp_num,delivery_state,pay_order_time,orderer_hp)
values (1,2,'lee',2,'이병승','만일 내가 인생을 다시 산다면',1,13000,'만일 내가 인생을 다시 산다면.jpg','이병승','010','2222','3333','02','1111','2222','우편번호:13547<br>도로명 주소:경기 성남시 분당구 고기로 25 (동원동)<br>[지번 주소:경기 성남시 분당구 동원동 79-1]<br>럭키빌딩 101호','일반택배',null,'no','신용카드<Br>카드사:삼성<br>할부개월수:일시불','삼성','일시불','010-2222-2222','cancel_order',to_date('24/04/01','RR/MM/DD'),'010-2222-3333');
--------------------------------------------------------
--  DDL for Sequence ORDER_SEQ_NUM
--------------------------------------------------------

CREATE SEQUENCE  "ORDER_SEQ_NUM"  MINVALUE 0 MAXVALUE 10000000 INCREMENT BY 1 START WITH 400 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_GOODS_ID
--------------------------------------------------------

CREATE SEQUENCE  "SEQ_GOODS_ID"  MINVALUE 100 MAXVALUE 1000000 INCREMENT BY 1 START WITH 400 CACHE 20 ORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_IMAGE_ID
--------------------------------------------------------

CREATE SEQUENCE  "SEQ_IMAGE_ID"  MINVALUE 1 MAXVALUE 11111111 INCREMENT BY 1 START WITH 400 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQ_ORDER_ID
--------------------------------------------------------

CREATE SEQUENCE  "SEQ_ORDER_ID"  MINVALUE 0 MAXVALUE 10000000 INCREMENT BY 1 START WITH 400 NOCACHE  ORDER  NOCYCLE ;
