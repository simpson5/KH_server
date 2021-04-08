--================================================
--SEMI계정
--================================================
--member테이블 생성
create table member(
    memberid varchar2(15),
    password varchar2(300) not null,
    lastname varchar2(30) not null,
    firstname varchar2(30) not null,
    email varchar2(30) not null,
    phone char(11) not null,
    interests varchar2(500),
    gender char(1),
    age number,
    point number default 1000,
    enrolldate date default sysdate,
    constraint pk_memberid primary key(memberid),
    constraint ch_member_gender check(gender in('M', 'F')),
    constraint ch_member_age check(age in(10, 20, 30, 40, 50, 60))
);
desc member;
--drop table member;
-- 회원 추가
insert into member values('admin', 1234, '관','리자', 'admin@gmail.com', '01012341234', '드라마,멜로', 'M', 30, default, default);
insert into member values('abcde', 1234, '홍','길동', 'honggd@gmail.com', '01012341234', '드라마,멜로', 'F', 20, default, default);
insert into member values('green', 1234, '김','그린', 'kimgreen@naver.com', '01092019922', '판타지,모험', 'F', 30, default, default);
insert into member values('apple', 1234, '박','사과', 'parkapple@daum.net', '01014241234', '뮤지컬,어드벤처', 'F', 40, default, default);
insert into member values('banana', 1234, '김','나나', 'kimnana@gmail.com', '01003931234', '드라마,코미디', 'F', 60, default, default);
insert into member values('hongs', 1234, '홍','민아', 'hongs@gmail.com', '01052321234', 'SF,애니메이션', 'M', 10, default, default);
insert into member values('pongpong', 1234, '박','만두', 'parkmd@gmail.com', '01042311234', '드라마,가족', 'F', 20, default, default);
insert into member values('kimkim', 1234, '김','펭수', 'kimps@gmail.com', '01012352624', '어드벤처,모험,액션', 'F', 50, default, default);
insert into member values('boricha', 1234, '보','리차', 'boricha@hanmail.net', '01012342224', '모험', 'F', 30, default, default);
insert into member values('leesarang', 1234, '이','사랑', 'leeLove@daum.net', '01012250933', '판타지,아동,코미디', 'F', 60, default, default);
insert into member values('years', 1234, '윤','만순', 'yms@gmail.com', '01012342667', '드라마,뮤지컬,모험', 'F', 20, default, default);
insert into member values('leehj', 1234, '이','하정', 'lhj@gmail.com', '01012209385', '드라마,액션', 'F', 70, default, default);
insert into member values('sinsa', 1234, '박','신사', 'parkss@gmail.com', '01093992234', '액션', 'F', 10, default, default);
insert into member values('cocos', 1234, '김','장군', 'ㅏjg@gmail.com', '01055541234', '뮤지컬', 'F', 30, default, default);
select * from member;
update member set password = '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==';
commit;

--==========================
-- Movie TABLE
--==========================
create table movie(
    movie_code varchar2(15) not null,
    movie_title varchar2(50) not null,
    runningtime number,
    director varchar2(200) not null,
    actor varchar2(500),
    description varchar2(4000),
    release_date Date not null,
    ticket_sales number,
    --movie_img varchar2(30) not null,
    originalFileName varchar2(100),
	renamedFileName varchar2(100),
    movie_video varchar2(1000) not null,
    genres varchar2(300) not null,
    schedule char not null,
    constraint pk_movie_code primary key(movie_code),
    constraint ck_schedule check(schedule in ('Y','N'))
);

desc movie;
--drop table movie;
--delete from movie;
select * from movie;
select * from ticketing;

insert into movie values('m01','토이스토리1',81,'존 라세터','톰 행크스,팀 알렌,돈 리클스,짐 바니,윌리스 쇼운','카우보이 인형 우디는 자신의 주인인 6살짜리 앤디로부터 가장 사랑을 받고 있다. 매년 앤디의 생일이 찾아오면 앤디가 선사받을 새 장난감에 의해 자신의 위치에 변동이 생길까봐 늘 불안해 한다. 그런데 앤디의 7살 생일날 심상치 않은 일이 생긴다. 바로 우주전사 인형 버즈의 출현이다. 버즈는 레이저빔, 가라데 무술, 그리고 돌출형 날개를 가진 신기한 인형인 것이다. 유감스럽게도 버즈는 앤디의 관심과 사랑을 독차지하게 되고 다른 장난감들은 앤디의 관심권에서 멀어진다. 그래서 우디는 버즈를 제거하려고 음모를 꾸미기 시작한다. 우디와 버즈의 대립은 깊어지고 인기를 되찾기 위한 경쟁이 가열되면서 두 인형은 앤디의 집 밖으로 벗어나게 된다.'
,'1995.12.30',1120,'toystory1.jpg','toystory1.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/_EAxUq_Ilf8" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','코메디,어드벤처,가족,판타지','N');
insert into movie values('m02','벅스 라이프',96,'존 라세터,앤드류 스탠튼','데이브 폴리,케빈 스페이시,줄리아 루이스 드레이퓨스,헤이든 파네티어,필리스 딜러','개미왕국의 개미들은 메뚜기 떼를 위해 허리가 휘도록 일한다. 메뚜기 떼의 두목인 하퍼에게 해마다 할당된 식량을 바쳐야 하기 때문이다. 착하고 영리한 개미 플릭은 하퍼 일당을 무찌르기 위해 덩치가 큰 곤충을 데려다가 맞서 싸우자고 제안한다. 여왕 개미는 플릭의 용기를 치하한다. 도시에 나간 플릭은 3류 서커스단의 곤충들을 용병으로 착각하고 데려온다. 사실이 밝혀지자 난감해진 플릭은 기지를 발휘, 서커스단의 곤충들이 마술쇼를 하며 하퍼의 관심을 딴 데로 돌려 놓는 사이 가짜 새를 만들어 하퍼 일당을 위협한다. 하지만 서커스 단장의 실수로 나뭇잎으로 만든 가짜 새는 불에 타버린다.',
'1998 .12.12',659,'bugslife.jpg','bugslife.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/yP66DYqPZgA" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','애니메이션,모험,판타지,코미디,가족','Y');
insert into movie values('m03','토이스토리2',85,'존 라세터,애쉬 브래넌,리 운크리치','톰 행크스,팀 알렌,조안 쿠삭,웨인 나이트,켈시 그래머','앤디는 쓸모없는 물건을 팔기위해 장난감 몇 가지를 골라 앞뜰에 전시한다. 우디는 친구인 위지가 팔려가게 될 것이라는 걸 알고 위지를 구출하러 밖으로 나간다. 그러나 우디는 장난감 수집광인 알에게 발견되어 그의 집으로 가게 된다. 대형 장난감가게를 운영하고 있는 알은 오래전 방영됐던 TV프로그램인 ‘우디의 가축몰이’를 수집하고 있었다. 알은 희귀한 장난감이 된 카우보이 우디를 손에 넣자 일본에서 토이박물관을 여는 사업가에게 팔 계획을 세우고 있었다. 우디가 알에게 유괴당하는 모습을 본 버즈는 친구들과 함께 버즈를 구하러 간다. 위험천만한 도로와 차들 그리고 승강기 탈출 작전을 펼치며 알의 장난감 가게로 침입 하게 된다. 버즈는 장난감 가게에 있던 제시와 프로스펙터, 볼스아이를 보며 예전에 인기가 많던 시절을 떠올리며 친구들과 그들과 함께 살아가겠다고 다짐하고 버즈는 장난감 가게에 있던 신형버즈에게 감금되고 신형버즈는 자신이 버즈인 것처럼 행동하고 다녔다. 그리고 버즈와 적대관계인 Z대왕이 나타나 그들을 막고 있었다. 신형버즈는 Z대왕과 대결하지만 오히려 일행을 위험에 빠지게 하고 버즈는 탈출해 버즈가 있는 곳으로 갔다. 그러나 우디는 가출몰이 친구들과 도쿄로 가겠다고 한다. 장난감은 아이들에게 사랑받아야 한다는 버즈의 이야기를 듣고 우디는 다시 생각을 고친다. 그러나 항상 소외받던 프로스펙터가 우디를 붙잡고 우디는 결국 짐에 실려 비행장까지 가게 된다. 우디는 가까스로 탈출했지만 제시가 화물에 실려 가고 우디와 볼스아이는 힘을 합쳐 제시를 구해낸다.',
'2010. 05.05',844,'toystory2.jpg','toystory2.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/mf8U_wraXWg" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','코메디,어드벤처,가족,판타지','N');
insert into movie values('m04','몬스터 주식회사',92,'피트 닥터, 데이빗 실버맨, 리 언크리치','존 굿맨(설리),빌리 크리스탈(마이크),마리 깁스(부/매리),스티브 부세미(랜달),제임스 코번(헨리 J. 워터누즈 III세),제니퍼 틸리(첼리아)','괴물 도시를 움직이는 에너지원은 어린이들의 비명소리?! 괴물 세계의 주된 에너지원은 인간 어린이들로부터 채집해온 비명소리이다. 가장 뛰어난 겁주기 선수들로 구성된 몬스터 주식회사는 그와 같은 소중한 비명을 채집하는 일을 전담하는 회사이다. 괴물들한테 한 가지 치명적인 문제가 있다면 인간 어린이들이 자신들에게 맹독성 독을 감염시킬 수 있다는 것이다. 그래서 괴물들은 어린이와 접촉해서는 절대로 안 된다. 어린 인간 소녀 부가 우연히 설리를 따라 괴물 세계에 들어오자 설리는 자신의 경력은 물론 자신의 인생이 끝장날 수 있다는 것을 깨닫게 된다. 결국, 설리는 마이크의 도움을 빌어서 자신의 실수를 만회할 수 있는 방법을 찾는다. 그러나 설리와 마이크, 그리고 부는 그들이 상상조차 하지 못했던 복잡한 모험의 세계로 빠져들게 된다.',
'2001. 12.20',870759,'monsterinc.jpg','monsterinc.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/Cmmux9XUKJo" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','코메디,어드벤처,가족,판타지','N');
insert into movie values('m05','니모를찾아서',100,'앤드류 스탠튼,리 운크리치','알버트 브룩스(마린),엘렌 드제너레스(도리),알렉산더 굴드(니모),윌렘 데포(길),브래드 가렛(블롯)','세상에서 가장 소중한 아들 니모를 다칠세라 과잉보호하며 키우던 말린은 니모가 처음 학교에 가는날 스쿠버다이버에게 납치되는 모습을 보고 아무것도 하지 못하자 절망감에 빠지지만 니모를 찾아 바다로 나간다. 니모를 구하기 위해 먼 바다로 출발하자마자 건망증에 빠진 물고기 도리와 만나게 된다. 한편 니모는 수족관에 갇히게 되는데 수족관에 갇힌 니모는 수족관의 여러 물고기들과 친구가 되고 물고기 갱단에 입단한다. 말린과 도리는 채식하려는 상어 브루스등 여러 친구들을 만나고 힘든 난관을 뚫고 아들을 찾아 모험을 해나간다. 이러한 이야기는 새와 물고기들을 타고 수족관에 까지 전해지고 니모는 아빠가 자신을 구하러 오고 있다는 소식을 듣고 감격한다. 수족관의 리더 길의 격려와 아빠에게 돌아가고 싶은 강렬한 열망에 고무된 니모는 과감히 탈출계획을 세우고 친구들의 도움으로 탈출하게 된다. 드디어 니모가 있는 시드니항에 도착한 말린은 많은 사건을 헤치고 니모와 감격적인 재회를 하게 된다.',
'2003.06.05',73483,'findingnemo.jpg','findingnemo.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/Lm78GvDvz2M" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','코메디,어드벤처,아동,가족','N');
insert into movie values('m06','인크레더블1',121,'브래드 버그','크레이그 T. 넬슨(밥 파/인크레더블),홀리 헌터(헬렌 파/엘라스티 걸),사무엘 L. 잭슨(루셔스 베스트/프로존)','정부의 방침에 의해 슈퍼히어로들은 평범한 인간으로써 살아가게 된다. 15년이 지난 후 밥은 엄청난 거구에 아랫배가 나온 중년의 아저씨가 되어 보험회사에서 일하고 있다. 과거의 모습을 그리워하며 지내는 밥에게 은밀히 미션이 전달된다. 다시 예전의 슈퍼히어로로 되고 싶어하던 밥은 인크레더블로 돌아가기로 결정한다. 미션을 받아 한 섬으로 간 인크레더블은 예전 같지 않은 몸에 로봇과 겨루어 간신히 물리친다. 집으로 돌아온 밥은 예전 같지 않은 활기찬 모습으로 가족과 즐거운 시간을 보내며 훈련을 시작한다. 밥은 가족을 속이고 계속 슈퍼히어로로 돌아가려 하지만 버디의 음모에 빠져 사로 잡히게 된다. 밥이 사라진 후 사실을 알게 된 헬렌은 에드가의 도움으로 밥의 위치를 알아내 찾으러 떠나지만 아이들은 몰래 따라간다. 헬렌은 아이들과 같이 밥을 찾아 섬에 도착해 버디와 싸우며 그들은 가족의 소중함을 깨닫고 모두 힘을 합쳐 인크레더블을 구하고 버디의 음모를 막아낸다.',
'2004 .12.15',3033105,'inc1.jpg','inc1.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/1OieCMAGcU4" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','애니메이션, 가족, 모험, 액션, 코미디','N');
insert into movie values('m07','카',121,'존 라세터','오웬 윌슨(라이트닝 맥퀸 목소리 역),폴 뉴먼(닥 허드슨 목소리 역),보니 헌트(샐리 목소리 역),마이클 키튼(클릭 힉스 목소리 역)','피스톤 컵 대회 사상 최연소 챔피언을 노리는 신예 레이스카 라이틴 맥퀸. 우승과 스타로서의 명성에만 관심이 있는 맥퀸은 피스톤 컵 재대결을 위해 캘리포니아로 가던 중 뜻하지 않게 레디에이터 스프링스라는 한적한 마을에 들어서게 된다. 자신이 쑥대밭으로 만들어 놓은 마을 도로를 복구하기 위해 맥퀸은 어쩔 수 없이 마을에 머물게 된다. 매일 떠날 궁리만 하는 맥퀸 하지만 이곳에 미스터리한 과거를 지닌 닥터 허드슨과 샐리, 그리고 메이터를 만나면서 인생의 소중한 가치를 깨닫으며 스스로 성장하게 된다.',
'2006 .07.20',573577,'car1.jpg','car1.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/AujuAwDvJqM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','가족, 코미디, 모험, 판타지, 애니메이션','N');
insert into movie values('m08','라따뚜이',115,'브래드 버드','비안코,이안 홀름(스키너),루 로마노(링귀니),브라이언 데니히(쟝고),피터 손,피터 오툴(안톤 이고)','미각과 후각이 뛰어난 레미는 요리를 좋아하는 쥐다. 그는 어느 날 요리책을 보다 집주인에게 들키고 쥐들은 모두 쫓겨나게 된다. 하수구로 도망을 가던 그는 파리에 도착하여 구스토 레스토랑으로 향한다. 새로 들어온 견습 요리사인 링귀니는 첫 날부터 스푸를 만들지만 제대로 만들지 못하고 구경하던 레미는 답답한 마음에 자신이 직접 스푸를 만들고 링귀니에게 들키게 된다. 레미가 만든 스푸가 사람들에게 좋은 반응을 얻자 링귀니는 레미의 도움을 받아 요리를 하게 된다. 링귀니는 우연치 않게 꼴레뜨에게 사실을 말하려다 연인 사이로 발전하게 된다. 요리 비평가 안톤 이고는 구스토 레스토랑에 다시 찾아가 요리 비평을 하려 하고 레미는 링귀니가 구스토의 아들인 것을 알게 되어 이를 알려 레스토랑의 주인이 되게 한다. 레미의 친구들이 찾아오고 링귀니에게 실망해 있던 그는 레스토랑의 음식 창고를 개방하고 이를 링귀니에게 들켜 쫓겨나게 된다. 안톤 이고가 찾아오는 날, 링귀니는 사실을 말하지만 꼴레뜨만 빼고 모두 그를 떠난다. 그는 레미와 같이 요리를 만들어 안톤 이고에게 선보이고 그는 무척이나 마음에 들어하고 새로이 라따뚜이라는 레스토랑을 연다.',
'2007 .07.25',1025734 ,'rata.jpg','rata.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/CsOo6y_hOEw" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','코메디,드라마,가족,판타지','N');
insert into movie values('m09','월-E',104,'앤드류 스탠튼','벤 버트(월-e/M-O),엘리사 나이트(이브),제프 가린(선장),프레드 윌러드(셸비 포스라이트),존 라첸버거(존)','텅 빈 지구에 홀로 남아 수백 년이란 시간을 외롭게 일만 하며 보내던 월-E (WALL-E: Waste Allocation Load Lifter Earth-Class, 지구 폐기물 수거-처리용 로봇). 그런 그가 매력적인 탐사 로봇 ‘이브’와 마주친 순간, 잡동사니 수집만이 낙이던 인생에도 소중한 목표가 생긴다. 이브는 지구의 미래를 결정할 열쇠가 우연히 월-E의 손에 들어간 사실을 알게 되고, 고향별로 돌아갈 날만 애타게 기다리는 인간들에게 이를 보고하기 위해 서둘러 우주로 향한다. 한편 월-E는 이브를 뒤쫓아 은하를 가로지르며, 스크린 사상 가장 짜릿한 상상이 넘치는 어드벤처를 선사한다. 이제껏 꿈에서도 볼 수 없었던 미래 세계를 배경으로 우주에서 펼쳐지는 월-E의 환상적인 모험! 애완용 바퀴벌레, 용맹스럽지만 어딘가 나사가 빠진 듯한 사회 부적응 로봇 군단 등 일련의 유쾌한 캐릭터들이 여기에 동참한다.',
'2008 .08.06',1327755,'walle.jpg','walle.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/hkta4xYaj1k" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','코메디,SF,어드벤처,아동','N');
insert into movie values('m10','업',101,'피트 닥터, 밥 피터슨','에드워드 애스너(칼 프레드릭슨), 조던 나가이(러셀 목소리), 크리스토퍼 플러머(찰스 먼츠 목소리)','평생 모험을 꿈꿔 왔던 ‘칼’ 할아버지는 수천 개의 풍선을 매달아 집을 통째로 남아메리카로 날려 버리는데,
 ‘칼’ 할아버지의 이 위대한 모험에 초대 받지 않은 불청객이 있었으니,
 바로 황야의 탐험가 ‘러셀’! 지구상에 둘도 없을 이 어색한 커플이 함께 하는 대모험.
 그들은 과연 남미의 잃어버린 세계에서 사라져 버린 꿈과 희망, 행복을 다시 찾을 수 있을까?',
 '2009 .07.29',1033026,'up.jpg','up.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/Sg6HoCam4xk" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','애니메이션, 가족, 모험, 코미디, 액션','N');
insert into movie values('m11','토이스토리3',102,'리 언크리치','톰 행크스,팀 알렌,조안 쿠삭,내드 베티,돈 리클리,마이클 키튼','모든 장난감들이 겪는 가장 슬픈일은 바로 주인이 성장해 더이상 자신들과 놀아주지 않는 것. 우디와 버즈에게도 그 위기가 찾아온다. 앤디가 대학에 진학, 집을 떠나게 된 것. 헤어짐의 불안에 떨던 토이들은 앤디 엄마의 실수로 집을 나오게 된 이들은 우여곡절 끝에 탁아소에 기증되는 신세가 된다! 그런데 오마이갓, 어린이집 애들 장난이 아니게 난폭하고 험하다. 그리고 상상도 못했던 거대한 음모까지 숨겨져 있는 어린이집 장난감의 세계. 그러다 앤디가 여전히 자신들을 사랑한다는 사실을 알게 된 토이 군단은 앤디 곁으로 돌아가기 위해 생애 가장 큰 모험을 결심한다. 우디를 중심으로 똘똘뭉친 토이들 과연 이들의 위대한 탈출은 성공할 것인가!'
,'2010.08.05',1460726,'toystory3.jpg','toystory3.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/yIK4tGW3MV8" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','드라마,어드벤처,아동,가족','N');
insert into movie values('m12','카2',113,'존 라세터, 브래드 루이스','오웬 윌슨,마이클 케인,마이클 키튼,에밀리 모티머,존 터투로,제이슨 아이삭스','최고의 스피드, 잘빠진 몸매를 자랑하는 레이싱카 라이트닝 맥퀸(목소리: 오웬 윌슨)이 돌아왔다. 오직 제 잘난 멋에 살던 ‘싹퉁 바가지’ 맥퀸을 정신 차리게 해준 레디에이터 스프링스 마을 친구들과 함께! 여기에 핀 맥미사일, 홀리 쉬프트웰 등 새로운 캐릭터들이 대거 등장하고 아시아와 유럽을 넘나드는 화려한 로케이션이 더해지면서 더욱 흥미진진한 스토리와 다채로운 볼거리를 제공한다. 전편과 다른 새로운 차원의 액션과 어드벤처로, 애니메이션판 ‘미션 임파서블’이라 일컬어지는 초특급 첩보작전! 이제 이들의 목표는 세계 그랑프리 우승이다. 그러나 우승까지 가는 길이 만만치는 않은 법. 각국의 내노라하는 레이싱카들이 죄다 모였으니, 우승이 떡 주워 먹듯 쉬울 리가 없지~ 게다가 그랑프리를 망쳐버리려는 악당들과 그들을 저지하려는 첩보원들까지 뒤얽힌 이 험난한 경기는, 과연 무사히 끝날 수 있을까?'
,'2011.07.20',465599,'car2.jpg','car2.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/1d4lznP5N-I" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','코메디,어드벤처,가족,판타지','N');
insert into movie values('m13','메리다와 마법의 숲',102,'마크 앤드류스,브렌다 샤프먼','켈리 맥도날드,빌리 코놀리,엠마 톰슨,줄리 월터스,크레이그 퍼거슨,케빈 매키드,로비 콜트레인','스코틀랜드의 전통 깊은 왕국의 공주 ‘메리다’는 드레스와 구두보다 말을 타고 활쏘는 것을 좋아하는 천방지축 장난꾸러기. 하지만 메리다의 엄마인 ‘엘리노어 왕비’는 그녀를 우아하고 아름다운 공주로 만들기 위해 메리다가 제일 싫어하는 ‘공주 수업’을 강요한다. 게다가 상상만해도 끔찍한 결혼까지! 메리다는 엄마에게 화가 나 가지 말라고 했던 비밀의 숲으로 들어갔다가 마녀를 만나 엄마를 바꿔달라고 부탁한다. 그런데 마녀의 마법에 걸린 엄마가 갑자기 곰으로 변하게 된다! 과연 메리다는 엄마의 마법을 풀고 왕국을 구할 수 있을까?! 엄마를 구하기 위한 메리다의 마법 같은 모험이 시작된다!'
,'2012.09.27',1231261,'brave.jpg','brave.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/wpz5pJ7fwPc" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','액션,판타지','N');
insert into movie values('m14','몬스터 대학교',110,'댄 스캐니온','빌리 크리스탈,존 굿맨,스티브 부세미,롭 리글 ,제니퍼 틸리,켄 정,조엘 머래이','<몬스터 주식회사>의 최강 몬스터 콤비, 이들은 한때 불꽃 튀는 라이벌이었다?! 이론만 빠삭한 "열공 몬스터" 마이크와 무늬만 엄친아 "허세 몬스터" 설리는 "몬스터 주식회사" 입사의 꿈을 안고 취업 100% 보장 특성화 대학 "몬스터 대학교"에 입학한다. 하지만 성격도 재능도 정반대인 둘은 첫날부터 삐걱거리며 급기야는 "몬대" 개교이래 최악의 라이벌이 되고야 만다. 어느 날, 이들을 겁주기 전공 퇴출 위기에 빠뜨린 엄청난 사건이 발생하고, 설리와 마이크는 어쩔 수 없이 팀을 이뤄야만 하는데… 과연 이들은 최악의 라이벌에서 최강의 콤비로 거듭날 수 있을까?'
,'2013.09.12',870759,'monsterUni.jpg','monsterUni.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/g9EbzaevX9U" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','코메디,모험,애니메이션','N');
insert into movie values('m15','인사이드 아웃',102,'피트 닥터','다이안 레인,에이미 포엘러,카일 맥라클란','“괜찮아, 다 잘 될 거야! 우리가 행복하게 만들어 줄게” 모든 사람의 머릿속에 존재하는 감정 컨트롤 본부 그곳에서 불철주야 열심히 일하는 기쁨, 슬픔, 버럭, 까칠, 소심 다섯 감정들. 이사 후 새로운 환경에 적응해야 하는 ‘라일리’를 위해 그 어느 때 보다 바쁘게 감정의 신호를 보내지만 우연한 실수로 ‘기쁨’과 ‘슬픔’이 본부를 이탈하게 되자 "라일리"의 마음 속에 큰 변화가 찾아온다. "라일리"가 예전의 모습을 되찾기 위해서는 ‘기쁨’과 ‘슬픔’이 본부로 돌아가야만 한다! 그러나 엄청난 기억들이 저장되어 있는 머릿속 세계에서 본부까지 가는 길은 험난하기만 한데… 과연, ‘라일리’는 다시 행복해질 수 있을까? 지금 당신의 머릿속에서 벌어지는 놀라운 일! 하루에도 몇번씩 변하는 감정의 비밀이 밝혀진다!'
,'2015.07.09',4969735,'inside.jpg','inside.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/1KGZtWbZtq8" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','드라마,아동','Y');
insert into movie values('m16','굿 다이노',101,'피터 손','레이몬드 오초와,제프리 라이트,프란시스 맥도먼드','외모, 성향 뭐 하나 닮은 것이 없는 알로와 스팟 우연한 사고로 엮이게 되면서 알로의 가족을 찾아 함께 모험을 떠나게 된다.  한 치 앞을 알 수 없는 여정 속 자연이 선사하는 엄청난 시련과 위대함을 맞닥뜨리게 되는데... 과연 알로는 가족을 찾을 수 있을까? 괜찮아, 내가 안아줄게!'
,'2016.01.07 ',1330181,'dinosaur.jpg','dinosaur.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/aMeL8_U1Eso" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','SF,코메디,가족,아동','N');
insert into movie values('m17','도리를 찾아서',97,'앤드류 스탠튼','엘렌 드제너레스,알버트 브룩스,이드리스 엘바,마이클 쉰,빌 헤이더','모태 건망증 ‘도리’가 문득 떠오른 가족의 기억을 믿고 캘리포니아로 떠나게 되면서 그녀의 행방을 쫓아 가는 니모와 말린의 스펙터클한 모험을 그린 3D 애니메이션.'
,'2016.07.06',2601761,'doryfinding.jpg','doryfinding.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/mS2BlBrBoxY" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','어드벤처,코메디,아동','N');
insert into movie values('m18','카3',109,'브라이언 피','오웬 윌슨,크리스 쿠퍼,나단 필리온,아이미 해머','잘나가던 슈퍼카 이제는 똥차 취급! 완벽한 컴백을 위한 마지막 트레이닝이 시작된다! 전 세계 1위의 자리를 지키던 레이싱계의 레전드 맥퀸. 어느 날 그의 앞길을 막는 최첨단 라이벌 스톰이 화려하게 데뷔를 하고, 맥퀸은 경기 도중 무리를 하다 치명적 부상까지 입는다. 절망에 빠진 맥퀸은 실력파 트레이너 크루즈를 만나 마지막 희망을 꿈꾸지만 안 맞아도 너무 안 맞는 크루즈와의 훈련은 맥퀸을 또다시 어려움에 빠트리는데…. 끝날 때까진 끝난 게 아니다! 내 마지막은 내가 정한다! 레이싱 인생을 건 맥퀸의 재도전은 성공할 수 있을까?'
,'2017.07.13',487916,'car3.jpg','car3.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/VaWmIE9m9eM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','코메디,어드벤처,가족,판타지','N');
insert into movie values('m19','코코',127,'리 언크리치','가엘 가르시아 베르날(헥터),안소니 곤잘레스(미구엘),벤자민 브랫(에르네스토 델라크루즈),르네 빅터(엘레나 할머니)','음악을 사랑하는 소년 미구엘이 우연히 죽은 자들의 세상으로 들어가 가족의 숨은 비밀을 찾게 되며 벌어지는 황홀하고 기묘한 여정'
,'2018.01.11',3513114,'coco.jpg','coco.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/LmS5KMJTWlA" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','어드벤처,코메디,뮤지컬','Y');
insert into movie values('m20','인크레더블2',125,'브래드 버드','크레이그 T. 넬슨(밥 파/ Mr. 인크레더블 (목소리)),사무엘 L. 잭슨(루시우스 베스트/ 프로존 (목소리))','엄마 "헬렌"이 국민 히어로 "일라스티걸"로 활약하고 아빠 "밥"은 삼남매를 돌보는 육아 히어로(?)를 맡으며 고군분투하는 가운데, 정체불명의 악당이 등장하면서 슈퍼파워 가족이 다시 한번 "인크레더블"한 능력을 발휘하는 이야기.'
,'2018.07.18',3033105,'inc2.png','inc2.png','<iframe width="560" height="315" src="https://www.youtube.com/embed/zON6Mu9_PC0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','액션,어드벤처','N');
insert into movie values('m21','토이스토리4',100,'조시 쿨리','톰 행크스(우디 목소리), 팀 알렌(버즈 라이트이어 목소리), 애니 파츠(보 핍 목소리)','장난감의 운명을 거부하고 떠난 새 친구 ‘포키’를 찾기 위해 길 위에 나선 ‘우디’는 우연히 오랜 친구 ‘보핍’을 만나고 그녀를 통해 새로운 세상에 눈을 뜨게 된다. 한편, ‘버즈’와 친구들은 사라진 ‘우디’와 ‘포키’를 찾아 세상 밖 위험천만한 모험을 떠나게 되는데… 당신이 기다려온 그들의 진짜 이야기가 공개된다!'
,'2019.06.20 ',3400035,'toystory4.jpg','toystory4.jpg','<iframe width="560" height="315" src="https://www.youtube.com/embed/u8GQibRXVHg" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>','애니메이션, 모험, 코미디, 가족, 판타지','N');
​

commit;

-- ==============================
-- LikeList TABLE
-- ==============================
create table likelist(
    like_no varchar2(15) not null,
    memberid varchar2(15) not null,
    movie_code varchar2(15) not null,
    constraint pk_like_no primary key(like_no),
    constraint fk_memberid foreign key(memberid) references member(memberid),
    constraint fk_movie_code foreign key(movie_code) references movie(movie_code)
);

desc likelist;

create sequence seq_like_no;

--drop table likelist;
--drop sequence seq_like_no;


select * from likelist;

commit;

--==========================================================
--영화댓글테이블
--==========================================================
create table movie_comment(
	comment_no number, --댓글고유번호
    comment_level number DEFAULT 1, --댓글(1)/대댓글(2)여부
	comment_writer varchar2(15),
    comment_content varchar2(2000),
	movie_ref varchar2(15), --영화코드
	comment_ref number, --대댓글인 경우 참조하고있는 댓글 번호, 댓글인 경우 null
	comment_date date DEFAULT sysdate,
    constraint pk_comment_no primary key(comment_no),
    constraint fk_comment_writer foreign key(comment_writer) references member(memberid) on delete set null,
    constraint fk_movie_ref foreign key(movie_ref) references movie(movie_code) on delete cascade,
    constraint fk_comment_ref foreign key(comment_ref) references movie_comment(comment_no) on delete cascade
);

create sequence seq_movie_comment;

desc movie_comment;
--drop table movie_comment;
--drop sequence seq_movie_comment;

commit;

--===========================================================
--공지사항테이블
--===========================================================
create table notice(
    notice_no number,
    notice_title varchar2(100),
    notice_content varchar2(4000),
    notice_original_filename varchar2(100),
    notice_renamed_filename varchar2(100),
    notice_date date default sysdate,
    constraint pk_notice_no primary key(notice_no)
);

create sequence seq_notice_no;

desc notice;
--drop table notice;
--drop sequence seq_notice_no;


insert into notice values(seq_notice_no.nextval, '[공지] 우리은행 전산시스템 점검에 따른 서비스 일시 중단 안내', '안녕하세요, 메가박스입니다.', null, null, default);
insert into notice values(seq_notice_no.nextval, '[공지] 픽사박스 모바일쿠폰 점검에 따른 서비스 일시 중단 안내', '안녕하세요, 픽사박스입니다. 픽사박스 모바일쿠폰 점검으로 인해 아래 시간 동안 모바일쿠폰 사용이 일시 중단 될 예정입니다. 참고하셔서 이용에 불편 없으시길 바랍니다. 더욱 안정되고 원활한 서비스로 찾아 뵙겠습니다. 일시: 2019년 12월 10일(화) 02:00 ~ 03:30 내용: 픽사박스 모바일쿠폰 사용 중지 감사합니다.', null, null, default);

commit;

--===========================================================
--faq table
--===========================================================
create table faq(
    faq_code varchar2(10),
    faq_type varchar2(20),
    faq_title varchar2(100),
    faq_content varchar2(3000),
    constraints pk_faq_code primary key(faq_code)
);

--drop table faq;

select * from faq;

insert into faq values('faq01', '멤버십', '포인트적립을 못받았어요. 어떻게 하나요?', '<p>멤버십 포인트 적립은 상영시간 전까지만 가능하며,<br />상영시간이 지난 티켓에 대해서는 사후 적립이 불가합니다.</p><p>(이용약관 "제 13조 회원의 특전"에 의거 /  http://megabox.co.kr/?menuId=terms)</p>');
insert into faq values('faq02', '멤버십', '포인트소멸은 언제, 어떻게 이루어지나요?', '멤버십 포인트는 최초 적립된 시점에서 24개월이 지난 시점의
해당 월 말일 영업종료 후 해당 기간 내에 사용하지 못한 잔여포인트가 월 단위로 자동 소멸됩니다.
회원에게 제공되는 포인트의 소멸시효가 도래하여 포인트를 소멸시키는 경우,
포인트 소멸과 관련된 내용을 최소 15일 전 e-mail 또는 SMS가 발송됩니다.
(이용약관 "제 13조 회원의 특전"에 의거 /  http://megabox.co.kr/?menuId=terms)');


commit;

--=========================================
--customerservice table
--=========================================
create table customerservice(
    board_no number not null,
    memberid varchar2(15) not null,
    board_title varchar2(30) not null,
    board_date date default sysdate,
    board_content varchar2(3000) not null,
    public_yn char not null,
    originalfilename varchar2(100),
    renamedfilename varchar2(100),
    boardType varchar2(30) not null,
    constraint pk_board_no primary key(board_no),
    constraint fk_customerservice_memberid foreign key(memberid) references member(memberid) on delete cascade,
    constraint ck_public_yn check(public_yn in ('Y','N'))
);
create sequence seq_board_no;
select * from customerservice;
select * from(select rownum as rnum, v.* from(select * from customerservice order by board_no) v) where rnum between 1 and 5;
-- drop table customerservice;
--drop sequence seq_board_no;
-- ==============================
-- cscomment TABLE : 1:1문의 댓글 테이블
-- ==============================
create table cscomment(
    cscomment_no number not null,
    board_no number not null,
    cscomment varchar2(2000) not null,
    constraint pk_cscomment_no primary key(cscomment_no),
    constraint fk_cscomment_board_no foreign key(board_no) references customerservice(board_no) on delete cascade
);
create sequence seq_cscomment_no;
commit;
select * from cscomment;
--drop table cscomment;
--drop sequence seq_cscomment_no;

--====================================
-- 상영관 테이블
-- (상영관 코드, 전체 좌석)
--====================================
create table screenarea(
    screen_code number not null,
    seat_cnt number null,
    constraint pk_screen_code primary key(screen_code)
);
​
--drop table screenarea;
--truncate table screenarea;

select * from screenarea;

insert into screenarea values(1, 83);
insert into screenarea values(2, 66);
insert into screenarea values(3, 75);
insert into screenarea values(4, 79);
insert into screenarea values(5, 66);

commit;

--============================================
--상영리스트 테이블
--(상영리스트 코드, 무비코드, 상영관코드, 상영회차, 시작시간, 끝시간, 상영날짜, 남은 좌석) **남는 좌석은 관마다 정해져있음
--============================================
create table movienow(
    show_code number not null,
    movie_code varchar2(15) not null,
    screen_code number not null,
    show_turn varchar2(5) not null,
    start_time varchar2(30) not null,
    end_time varchar2(30) not null,
    show_date date not null,
    remain_seats number null,
    constraint pk_show_code primary key(show_code),
    constraint fk_movienow_movie_code foreign key(movie_code) references movie(movie_code),
    constraint fk_screen_code foreign key(screen_code) references screenarea(screen_code)
);

create sequence seq_movienow;
--drop sequence seq_movienow;
--상영리스트
--1/10일 1관 도리 97 / 니모100
insert into movienow values(seq_movienow.nextval, 'm17', 1, '1', '08:20', '09:57', '2020-01-10', 83);
insert into movienow values(seq_movienow.nextval, 'm17', 1, '2', '10:10', '11:47', '2020-01-10', 83);
insert into movienow values(seq_movienow.nextval, 'm17', 1, '3', '12:30', '14:07', '2020-01-10', 83);
insert into movienow values(seq_movienow.nextval, 'm05', 1, '1', '14:20', '16:00', '2020-01-10', 83);
insert into movienow values(seq_movienow.nextval, 'm05', 1, '2', '17:10', '18:50', '2020-01-10', 83);
insert into movienow values(seq_movienow.nextval, 'm05', 1, '3', '20:00', '22:40', '2020-01-10', 83);
--1/11일 1관 도리 97 / 니모100
insert into movienow values(seq_movienow.nextval, 'm17', 1, '1', '09:25', '11:02', '2020-01-11', 83);
insert into movienow values(seq_movienow.nextval, 'm17', 1, '2', '13:15', '14:52', '2020-01-11', 83);
insert into movienow values(seq_movienow.nextval, 'm17', 1, '3', '19:15', '20:52', '2020-01-11', 83);
insert into movienow values(seq_movienow.nextval, 'm05', 1, '1', '11:20', '13:00', '2020-01-11', 83);
insert into movienow values(seq_movienow.nextval, 'm05', 1, '2', '16:25', '18:02', '2020-01-11', 83);
insert into movienow values(seq_movienow.nextval, 'm05', 1, '3', '21:20', '23:00', '2020-01-11', 83);
--1/12일 1관 도리 97 / 니모100
insert into movienow values(seq_movienow.nextval, 'm05', 1, '1', '10:30', '13:10', '2020-01-12', 83);
insert into movienow values(seq_movienow.nextval, 'm05', 1, '2', '14:20', '16:00', '2020-01-12', 83);
insert into movienow values(seq_movienow.nextval, 'm05', 1, '3', '16:10', '17:50', '2020-01-12', 83);
insert into movienow values(seq_movienow.nextval, 'm17', 1, '1', '18:30', '20:02', '2020-01-12', 83);
insert into movienow values(seq_movienow.nextval, 'm17', 1, '2', '20:20', '21:57', '2020-01-12', 83);
insert into movienow values(seq_movienow.nextval, 'm17', 1, '3', '22:10', '23:47', '2020-01-12', 83);
--1/10 2관 몬주
insert into movienow values(seq_movienow.nextval, 'm04', 2, '1', '08:00', '09:46', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm04', 2, '2', '13:00', '14:46', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm04', 2, '3', '17:00', '17:46', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm04', 2, '4', '22:00', '23:46', '2020-01-10', 66);
​
--1/10 2관 몬대
insert into movienow values(seq_movienow.nextval, 'm14', 2, '1', '10:00', '12:00', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm14', 2, '2', '15:00', '17:00', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm14', 2, '3', '19:00', '21:00', '2020-01-10', 66);
​
--1/11 2관 몬주
insert into movienow values(seq_movienow.nextval, 'm04', 2, '1', '08:00', '09:46', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm04', 2, '2', '13:00', '14:46', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm04', 2, '3', '17:00', '17:46', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm04', 2, '4', '22:00', '23:46', '2020-01-11', 66);
​
--1/11 2관 몬대
insert into movienow values(seq_movienow.nextval, 'm14', 2, '1', '00:30', '02:30', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm14', 2, '2', '10:00', '12:00', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm14', 2, '3', '15:00', '17:00', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm14', 2, '4', '19:00', '21:00', '2020-01-11', 66);
--1/12 2관 몬주
insert into movienow values(seq_movienow.nextval, 'm04', 2, '1', '08:00', '09:46', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm04', 2, '2', '13:00', '14:46', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm04', 2, '3', '17:00', '17:46', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm04', 2, '4', '22:00', '23:46', '2020-01-12', 66);
--1/12 2관 몬대
insert into movienow values(seq_movienow.nextval, 'm14', 2, '1', '00:30', '02:30', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm14', 2, '2', '10:00', '12:00', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm14', 2, '3', '15:00', '17:00', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm14', 2, '4', '19:00', '21:00', '2020-01-12', 66);
​
--1/10일 5관 벅스라이프(m02) 96 / 굿다이노(m16) 101
insert into movienow values(seq_movienow.nextval, 'm02', 5, '1', '08:30', '10:06', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm02', 5, '2', '10:20', '11:56', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm02', 5, '3', '11:00', '12:36', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm16', 5, '1', '12:20', ':14:01', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm16', 5, '2', '14:10', '15:51', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm16', 5, '3', '16:30', '18:11', '2020-01-10', 66);
insert into movienow values(seq_movienow.nextval, 'm16', 5, '4', '21:00', '22:41', '2020-01-10', 66);
​
--1/11일 5관
insert into movienow values(seq_movienow.nextval, 'm16', 5, '1', '09:20', '11:01', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm16', 5, '2', '11:40', '13:21', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm16', 5, '3', '14:50', '16:31', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm02', 5, '1', '20:25', '22:01', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm02', 5, '2', '23:15', '24:51', '2020-01-11', 66);
insert into movienow values(seq_movienow.nextval, 'm02', 5, '3', '24:20', '25:56', '2020-01-11', 66);
​
--1/12일 5관
insert into movienow values(seq_movienow.nextval, 'm02', 5, '1', '08:10', '09:46', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm02', 5, '2', '09:40', '11:16', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm02', 5, '3', '11:30', '13:06', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm16', 5, '1', '13:20', '15:01', '2020-01-12', 66);
insert into movienow values(seq_movienow.nextval, 'm16', 5, '2', '15:40', '17:21', '2020-01-12', 66);
​
--1/10일 4관
insert into movienow values(seq_movienow.nextval, 'm15', 4, '1', '07:30', '09:12', '2020-01-10', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '2', '10:15', '11:57', '2020-01-10', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '3', '13:45', '15:27', '2020-01-10', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '4', '16:35', '18:17', '2020-01-10', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '5', '19:20', '21:02', '2020-01-10', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '6', '22:05', '23:47', '2020-01-10', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '7', '24:50', '26:32', '2020-01-10', 79);
​
--1/11일 4관
insert into movienow values(seq_movienow.nextval, 'm15', 4, '1', '09:30', '11:12', '2020-01-11', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '2', '12:10', '13:52', '2020-01-11', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '3', '14:50', '16:32', '2020-01-11', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '4', '17:30', '19:12', '2020-01-11', 79);
insert into movienow values(seq_movienow.nextval, 'm08', 4, '1', '20:10', '22:05', '2020-01-11', 79);
insert into movienow values(seq_movienow.nextval, 'm08', 4, '2', '22:50', '24:45', '2020-01-11', 79);
​
--1/12일 4관
insert into movienow values(seq_movienow.nextval, 'm08', 4, '1', '08:30', '10:25', '2020-01-12', 79);
insert into movienow values(seq_movienow.nextval, 'm08', 4, '2', '11:10', '13:05', '2020-01-12', 79);
insert into movienow values(seq_movienow.nextval, 'm08', 4, '3', '22:35', '24:30', '2020-01-12', 79);
insert into movienow values(seq_movienow.nextval, 'm08', 4, '4', '25:15', '27:10', '2020-01-12', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '1', '13:50', '15:32', '2020-01-12', 79);
insert into movienow values(seq_movienow.nextval, 'm15', 4, '2', '16:50', '18:32', '2020-01-12', 79);
​
--1/10일 3관 코코127 업101
insert into movienow values(seq_movienow.nextval, 'm19', 3, '1', '09:00', '11:07', '2020-01-10', 75);
insert into movienow values(seq_movienow.nextval, 'm19', 3, '2', '12:00', '14:07', '2020-01-10', 75);
insert into movienow values(seq_movienow.nextval, 'm19', 3, '3', '15:30', '17:37', '2020-01-10', 75);
insert into movienow values(seq_movienow.nextval, 'm10', 3, '1', '19:15', '20:56', '2020-01-10', 75);
insert into movienow values(seq_movienow.nextval, 'm10', 3, '2', '22:15', '23:56', '2020-01-10', 75);
​
--1/11일 3관
insert into movienow values(seq_movienow.nextval, 'm19', 3, '1', '09:20', '11:27', '2020-01-11', 75);
insert into movienow values(seq_movienow.nextval, 'm19', 3, '2', '12:20', '14:27', '2020-01-11', 75);
insert into movienow values(seq_movienow.nextval, 'm19', 3, '3', '15:50', '17:57', '2020-01-11', 75);
insert into movienow values(seq_movienow.nextval, 'm19', 3, '4', '18:30', '20:37', '2020-01-11', 75);
insert into movienow values(seq_movienow.nextval, 'm10', 3, '1', '21:10', '22:51', '2020-01-11', 75);
​
--1/12일 3관
insert into movienow values(seq_movienow.nextval, 'm19', 3, '1', '08:00', '10:07', '2020-01-12', 75);
insert into movienow values(seq_movienow.nextval, 'm19', 3, '2', '10:40', '12:47', '2020-01-12', 75);
insert into movienow values(seq_movienow.nextval, 'm10', 3, '3', '13:25', '15:06', '2020-01-12', 75);
--drop table movienow;
--drop sequence seq_movienow;
--truncate table movienow;
select * from movienow;
select distinct movie_code from movienow;
​
commit;


--=====================================
-- 좌석 테이블
-- 좌석번호, 상영관코드, 예매여부, 상영리스트코드
--=====================================
create table seat(
    seat_no varchar2(15) not null, --좌석번호
    screen_code number not null, --상영관
    reservation_yn char null, --예약여부
    show_code number not null,
    constraint pk_seat_no primary key(seat_no, screen_code, show_code),
    constraint fk_seat_screen_code foreign key(screen_code) references screenarea(screen_code),
    constraint fk_seat_show_code foreign key(show_code) references movienow(show_code)
);
--truncate table seat;
--drop table seat;
select * from seat where screen_code = 1;
select * from seat where screen_code = 2 and show_code = 20;

--좌석 테이블 테스트용

--1/10) 1관 1회차: 도리 1회차
--좌석번호, 상영관, 예약여부, 상영코드(showcode)
---------------------- 1관 -------------------------
insert into seat values('A1', 1, 'N', 1);
insert into seat values('A2', 1, 'N', 1);
insert into seat values('A3' , 1, 'N' ,1);
insert into seat values('A4' , 1, 'N' ,1);
insert into seat values('A5' , 1, 'N' ,1);
insert into seat values('A6' , 1, 'N' ,1);
insert into seat values('A7' , 1, 'N' ,1);
insert into seat values('A8' , 1, 'N' ,1);
insert into seat values('A9' , 1, 'N' ,1);
insert into seat values('A10' , 1, 'N' ,1);
insert into seat values('A11' , 1, 'N' ,1);
insert into seat values('A12' , 1, 'N' ,1);

insert into seat values('B1', 1, 'N', 1);
insert into seat values('B2', 1, 'N', 1);
insert into seat values('B3' , 1, 'N' ,1);
insert into seat values('B4' , 1, 'N' ,1);
insert into seat values('B5' , 1, 'N' ,1);
insert into seat values('B6' , 1, 'N' ,1);
insert into seat values('B7' , 1, 'N' ,1);
insert into seat values('B8' , 1, 'N' ,1);
insert into seat values('B9' , 1, 'N' ,1);
insert into seat values('B10' , 1, 'N' ,1);
insert into seat values('B11' , 1, 'N' ,1);
insert into seat values('B12' , 1, 'N' ,1);

insert into seat values('C1', 1, 'N', 1);
insert into seat values('C2', 1, 'N', 1);
insert into seat values('C3' , 1, 'N' ,1);
insert into seat values('C4' , 1, 'N' ,1);
insert into seat values('C5' , 1, 'N' ,1);
insert into seat values('C6' , 1, 'N' ,1);
insert into seat values('C7' , 1, 'N' ,1);
insert into seat values('C8' , 1, 'N' ,1);
insert into seat values('C9' , 1, 'N' ,1);
insert into seat values('C10' , 1, 'N' ,1);
insert into seat values('C11' , 1, 'N' ,1);
insert into seat values('C12' , 1, 'N' ,1);

insert into seat values('D1', 1, 'N', 1);
insert into seat values('D2', 1, 'N', 1);
insert into seat values('D3' , 1, 'N' ,1);
insert into seat values('D4' , 1, 'N' ,1);
insert into seat values('D5' , 1, 'N' ,1);
insert into seat values('D6' , 1, 'N' ,1);
insert into seat values('D7' , 1, 'N' ,1);
insert into seat values('D8' , 1, 'N' ,1);
insert into seat values('D9' , 1, 'N' ,1);
insert into seat values('D10' , 1, 'N' ,1);
insert into seat values('D11' , 1, 'N' ,1);
insert into seat values('D12' , 1, 'Y' ,1);

insert into seat values('E1', 1, 'N', 1);
insert into seat values('E2', 1, 'N', 1);
insert into seat values('E3' , 1, 'N' ,1);
insert into seat values('E4' , 1, 'N' ,1);
insert into seat values('E5' , 1, 'N' ,1);
insert into seat values('E6' , 1, 'N' ,1);
insert into seat values('E7' , 1, 'N' ,1);
insert into seat values('E8' , 1, 'N' ,1);
insert into seat values('E9' , 1, 'N' ,1);
insert into seat values('E10' , 1, 'N' ,1);
insert into seat values('E11' , 1, 'N' ,1);
insert into seat values('E12' , 1, 'Y' ,1);

insert into seat values('F1', 1, 'N', 1);
insert into seat values('F2', 1, 'N', 1);
insert into seat values('F3' , 1, 'N' ,1);
insert into seat values('F4' , 1, 'N' ,1);
insert into seat values('F5' , 1, 'N' ,1);
insert into seat values('F6' , 1, 'N' ,1);
insert into seat values('F7' , 1, 'N' ,1);
insert into seat values('F8' , 1, 'N' ,1);
insert into seat values('F9' , 1, 'N' ,1);
insert into seat values('F10' , 1, 'N' ,1);
insert into seat values('F11' , 1, 'N' ,1);
insert into seat values('F12' , 1, 'Y' ,1);

insert into seat values('G1', 1, 'N', 1);
insert into seat values('G2', 1, 'N', 1);
insert into seat values('G3' , 1, 'N' ,1);
insert into seat values('G4' , 1, 'N' ,1);
insert into seat values('G5' , 1, 'N' ,1);
insert into seat values('G6' , 1, 'N' ,1);
insert into seat values('G7' , 1, 'N' ,1);
insert into seat values('G8' , 1, 'N' ,1);
insert into seat values('G9' , 1, 'N' ,1);
insert into seat values('G10' , 1, 'N' ,1);
insert into seat values('G11' , 1, 'Y' ,1);

--2관 1월11일: 몬스터주식회사 1회차
---------------------- 2관 -------------------------
insert into seat values('A1', 2, 'N', 26);
insert into seat values('A2', 2, 'N', 26);
insert into seat values('A3' , 2, 'N' , 26);
insert into seat values('A4' , 2, 'N' ,26);
insert into seat values('A5' , 2, 'N' ,26);
insert into seat values('A6' , 2, 'N' ,26);
insert into seat values('A7' , 2, 'N' ,26);
insert into seat values('A8' , 2, 'N' ,26);
insert into seat values('A9' , 2, 'N' ,26);
insert into seat values('A10' , 2, 'N' ,26);
insert into seat values('A11' , 2, 'N' ,26);

insert into seat values('B1', 2, 'N', 26);
insert into seat values('B2', 2, 'N', 26);
insert into seat values('B3' , 2, 'N' ,26);
insert into seat values('B4' , 2, 'N' ,26);
insert into seat values('B5' , 2, 'N' ,26);
insert into seat values('B6' , 2, 'N' ,26);
insert into seat values('B7' , 2, 'N' ,26);
insert into seat values('B8' , 2, 'N' ,26);
insert into seat values('B9' , 2, 'N' ,26);
insert into seat values('B10' , 2, 'N' ,26);
insert into seat values('B11' , 2, 'N' ,26);

insert into seat values('C1', 2, 'N', 26);
insert into seat values('C2', 2, 'N', 26);
insert into seat values('C3' , 2, 'N' ,26);
insert into seat values('C4' , 2, 'N' ,26);
insert into seat values('C5' , 2, 'N' ,26);
insert into seat values('C6' , 2, 'N' ,26);
insert into seat values('C7' , 2, 'N' ,26);
insert into seat values('C8' , 2, 'N' ,26);
insert into seat values('C9' , 2, 'N' ,26);
insert into seat values('C10' , 2, 'N' ,26);
insert into seat values('C11' , 2, 'N' ,26);

insert into seat values('D1', 2, 'N', 26);
insert into seat values('D2', 2, 'N', 26);
insert into seat values('D3' , 2, 'N' ,26);
insert into seat values('D4' , 2, 'N' ,26);
insert into seat values('D5' , 2, 'N' ,26);
insert into seat values('D6' , 2, 'N' ,26);
insert into seat values('D7' , 2, 'N' ,26);
insert into seat values('D8' , 2, 'N' ,26);
insert into seat values('D9' , 2, 'N' ,26);
insert into seat values('D10' , 2, 'N' ,26);
insert into seat values('D11' , 2, 'N' ,26);

insert into seat values('E1', 2, 'N', 26);
insert into seat values('E2', 2, 'N', 26);
insert into seat values('E3' , 2, 'N' ,26);
insert into seat values('E4' , 2, 'N' ,26);
insert into seat values('E5' , 2, 'N' ,26);
insert into seat values('E6' , 2, 'N' ,26);
insert into seat values('E7' , 2, 'N' ,26);
insert into seat values('E8' , 2, 'N' ,26);
insert into seat values('E9' , 2, 'N' ,26);
insert into seat values('E10' , 2, 'N' ,26);
insert into seat values('E11' , 2, 'N' ,26);

insert into seat values('F1', 2, 'N', 26);
insert into seat values('F2', 2, 'N', 26);
insert into seat values('F3' , 2, 'N' ,26);
insert into seat values('F4' , 2, 'N' ,26);
insert into seat values('F5' , 2, 'N' ,26);
insert into seat values('F6' , 2, 'N' ,26);
insert into seat values('F7' , 2, 'N' ,26);
insert into seat values('F8' , 2, 'N' ,26);
insert into seat values('F9' , 2, 'N' ,26);
insert into seat values('F10' , 2, 'N' ,26);
insert into seat values('F11' , 2, 'N' ,26);

---------------------- 3관 -------------------------
insert into seat values('A1', 3, 'N', 89);
insert into seat values('A2', 3, 'N', 89);
insert into seat values('A3' , 3, 'N' ,89);
insert into seat values('A4' , 3, 'N' ,89);
insert into seat values('A5' , 3, 'N' ,89);
insert into seat values('A6' , 3, 'N' ,89);
insert into seat values('A7' , 3, 'N' ,89);
insert into seat values('A8' , 3, 'N' ,89);
insert into seat values('A9' , 3, 'N' ,89);
insert into seat values('A10' , 3, 'N' ,89);
insert into seat values('A11' , 3, 'N' ,89);

insert into seat values('B1', 3, 'N',89);
insert into seat values('B2', 3, 'N', 89);
insert into seat values('B3' , 3, 'N' ,89);
insert into seat values('B4' , 3, 'N' ,89);
insert into seat values('B5' , 3, 'N' ,89);
insert into seat values('B6' , 3, 'N' ,89);
insert into seat values('B7' , 3, 'N' ,89);
insert into seat values('B8' , 3, 'N' ,89);
insert into seat values('B9' , 3, 'N' ,89);
insert into seat values('B10' , 3, 'N' ,89);
insert into seat values('B11' , 3, 'N' ,89);

insert into seat values('C1', 3, 'N', 89);
insert into seat values('C2', 3, 'N', 89);
insert into seat values('C3' , 3, 'N' ,89);
insert into seat values('C4' , 3, 'N' ,89);
insert into seat values('C5' , 3, 'N' ,89);
insert into seat values('C6' , 3, 'N' ,89);
insert into seat values('C7' , 3, 'N' ,89);
insert into seat values('C8' , 3, 'N' ,89);
insert into seat values('C9' , 3, 'N' ,89);
insert into seat values('C10' , 3, 'N' ,89);
insert into seat values('C11' , 3, 'N' ,89);

insert into seat values('D1', 3, 'N', 89);
insert into seat values('D2', 3, 'N', 89);
insert into seat values('D3' , 3, 'N' ,89);
insert into seat values('D4' , 3, 'N' ,89);
insert into seat values('D5' , 3, 'N' ,89);
insert into seat values('D6' , 3, 'N' ,89);
insert into seat values('D7' , 3, 'N' ,89);
insert into seat values('D8' , 3, 'N' ,89);
insert into seat values('D9' , 3, 'N' ,89);
insert into seat values('D10' , 3, 'N' ,89);
insert into seat values('D11' , 3, 'N' ,89);

insert into seat values('E1', 3, 'N', 89);
insert into seat values('E2', 3, 'N', 89);
insert into seat values('E3' , 3, 'N' ,89);
insert into seat values('E4' , 3, 'N' ,89);
insert into seat values('E5' , 3, 'N' ,89);
insert into seat values('E6' , 3, 'N' ,89);
insert into seat values('E7' , 3, 'N' ,89);
insert into seat values('E8' , 3, 'N' ,89);
insert into seat values('E9' , 3, 'N' ,89);
insert into seat values('E10' , 3, 'N' ,89);
insert into seat values('E11' , 3, 'N' ,89);

insert into seat values('F1', 3, 'N', 89);
insert into seat values('F2', 3, 'N', 89);
insert into seat values('F3' , 3, 'N' ,89);
insert into seat values('F4' , 3, 'N' ,89);
insert into seat values('F5' , 3, 'N' ,89);
insert into seat values('F6' , 3, 'N' ,89);
insert into seat values('F7' , 3, 'N' ,89);
insert into seat values('F8' , 3, 'N' ,89);
insert into seat values('F9' , 3, 'N' ,89);
insert into seat values('F10' , 3, 'N' ,89);
insert into seat values('F11' , 3, 'N' ,89);

insert into seat values('G1', 3, 'N', 89);
insert into seat values('G2', 3, 'N', 89);
insert into seat values('G3' , 3, 'N' ,89);
insert into seat values('G4' , 3, 'N' ,89);
insert into seat values('G5' , 3, 'N' ,89);
insert into seat values('G6' , 3, 'N' ,89);
insert into seat values('G7' , 3, 'N' ,89);
insert into seat values('G8' , 3, 'N' ,89);
insert into seat values('G9' , 3, 'N' ,89);

---------------------- 4관 -------------------------
insert into seat values('A1', 4, 'N', 'N');
insert into seat values('A2', 4, 'N', 'N');
insert into seat values('A3' , 4, 'N' ,'N');
insert into seat values('A4' , 4, 'N' ,'N');
insert into seat values('A5' , 4, 'N' ,'N');
insert into seat values('A6' , 4, 'N' ,'N');
insert into seat values('A7' , 4, 'N' ,'N');
insert into seat values('A8' , 4, 'N' ,'N');
insert into seat values('A9' , 4, 'N' ,'N');
insert into seat values('A10' , 4, 'N' ,'N');
insert into seat values('A11' , 4, 'N' ,'N');

insert into seat values('B1', 4, 'N', 'N');
insert into seat values('B2', 4, 'N', 'N');
insert into seat values('B3' , 4, 'N' ,'N');
insert into seat values('B4' , 4, 'N' ,'N');
insert into seat values('B5' , 4, 'N' ,'N');
insert into seat values('B6' , 4, 'N' ,'N');
insert into seat values('B7' , 4, 'N' ,'N');
insert into seat values('B8' , 4, 'N' ,'N');
insert into seat values('B9' , 4, 'N' ,'N');
insert into seat values('B10' , 4, 'N' ,'N');
insert into seat values('B11' , 4, 'N' ,'N');

insert into seat values('C1', 4, 'N', 'N');
insert into seat values('C2', 4, 'N', 'N');
insert into seat values('C3' , 4, 'N' ,'N');
insert into seat values('C4' , 4, 'N' ,'N');
insert into seat values('C5' , 4, 'N' ,'N');
insert into seat values('C6' , 4, 'N' ,'N');
insert into seat values('C7' , 4, 'N' ,'N');
insert into seat values('C8' , 4, 'N' ,'N');
insert into seat values('C9' , 4, 'N' ,'N');
insert into seat values('C10' , 4, 'N' ,'N');
insert into seat values('C11' , 4, 'N' ,'N');

insert into seat values('D1', 4, 'N', 'N');
insert into seat values('D2', 4, 'N', 'N');

insert into seat values('E1', 4, 'N', 'N');
insert into seat values('E2', 4, 'N', 'N');
insert into seat values('E3' , 4, 'N' ,'N');
insert into seat values('E4' , 4, 'N' ,'N');
insert into seat values('E5' , 4, 'N' ,'N');
insert into seat values('E6' , 4, 'N' ,'N');
insert into seat values('E7' , 4, 'N' ,'N');
insert into seat values('E8' , 4, 'N' ,'N');
insert into seat values('E9' , 4, 'N' ,'N');
insert into seat values('E10' , 4, 'N' ,'N');
insert into seat values('E11' , 4, 'N' ,'N');

insert into seat values('F1', 4, 'N', 'N');
insert into seat values('F2', 4, 'N', 'N');
insert into seat values('F3' , 4, 'N' ,'N');
insert into seat values('F4' , 4, 'N' ,'N');
insert into seat values('F5' , 4, 'N' ,'N');
insert into seat values('F6' , 4, 'N' ,'N');
insert into seat values('F7' , 4, 'N' ,'N');
insert into seat values('F8' , 4, 'N' ,'N');
insert into seat values('F9' , 4, 'N' ,'N');
insert into seat values('F10' , 4, 'N' ,'N');
insert into seat values('F11' , 4, 'N' ,'N');

insert into seat values('G1', 4, 'N', 'N');
insert into seat values('G2', 4, 'N', 'N');
insert into seat values('G3' , 4, 'N' ,'N');
insert into seat values('G4' , 4, 'N' ,'N');
insert into seat values('G5' , 4, 'N' ,'N');
insert into seat values('G6' , 4, 'N' ,'N');
insert into seat values('G7' , 4, 'N' ,'N');
insert into seat values('G8' , 4, 'N' ,'N');
insert into seat values('G9' , 4, 'N' ,'N');
insert into seat values('G10' , 4, 'N' ,'N');
insert into seat values('G11' , 4, 'N' ,'N');

insert into seat values('H1', 4, 'N', 'N');
insert into seat values('H2', 4, 'N', 'N');
insert into seat values('H3' , 4, 'N' ,'N');
insert into seat values('H4' , 4, 'N' ,'N');
insert into seat values('H5' , 4, 'N' ,'N');
insert into seat values('H6' , 4, 'N' ,'N');
insert into seat values('H7' , 4, 'N' ,'N');
insert into seat values('H8' , 4, 'N' ,'N');
insert into seat values('H9' , 4, 'N' ,'N');
insert into seat values('H10' , 4, 'N' ,'N');
insert into seat values('H11' , 4, 'N' ,'N');


---------------------- 5관 -------------------------
insert into seat values('A1', 5, 'N', 'N');
insert into seat values('A2', 5, 'N', 'N');
insert into seat values('A3' , 5, 'N' ,'N');
insert into seat values('A4' , 5, 'N' ,'N');
insert into seat values('A5' , 5, 'N' ,'N');
insert into seat values('A6' , 5, 'N' ,'N');
insert into seat values('A7' , 5, 'N' ,'N');
insert into seat values('A8' , 5, 'N' ,'N');
insert into seat values('A9' , 5, 'N' ,'N');
insert into seat values('A10' , 5, 'N' ,'N');
insert into seat values('A11' , 5, 'N' ,'N');
insert into seat values('A12' , 5, 'N' ,'N');

insert into seat values('B1', 5, 'N', 'N');
insert into seat values('B2', 5, 'N', 'N');
insert into seat values('B3' , 5, 'N' ,'N');
insert into seat values('B4' , 5, 'N' ,'N');
insert into seat values('B5' , 5, 'N' ,'N');
insert into seat values('B6' , 5, 'N' ,'N');
insert into seat values('B7' , 5, 'N' ,'N');
insert into seat values('B8' , 5, 'N' ,'N');
insert into seat values('B9' , 5, 'N' ,'N');
insert into seat values('B10' , 5, 'N' ,'N');
insert into seat values('B11' , 5, 'N' ,'N');
insert into seat values('B12' , 5, 'N' ,'N');

insert into seat values('C1', 5, 'N', 'N');
insert into seat values('C2', 5, 'N', 'N');
insert into seat values('C3' , 5, 'N' ,'N');
insert into seat values('C4' , 5, 'N' ,'N');
insert into seat values('C5' , 5, 'N' ,'N');
insert into seat values('C6' , 5, 'N' ,'N');
insert into seat values('C7' , 5, 'N' ,'N');
insert into seat values('C8' , 5, 'N' ,'N');
insert into seat values('C9' , 5, 'N' ,'N');
insert into seat values('C10' , 5, 'N' ,'N');
insert into seat values('C11' , 5, 'N' ,'N');
insert into seat values('C12' , 5, 'N' ,'N');

insert into seat values('D1', 5, 'N', 'N');
insert into seat values('D2', 5, 'N', 'N');
insert into seat values('D3' , 5, 'N' ,'N');
insert into seat values('D4' , 5, 'N' ,'N');
insert into seat values('D5' , 5, 'N' ,'N');
insert into seat values('D6' , 5, 'N' ,'N');
insert into seat values('D7' , 5, 'N' ,'N');
insert into seat values('D8' , 5, 'N' ,'N');
insert into seat values('D9' , 5, 'N' ,'N');
insert into seat values('D10' , 5, 'N' ,'N');
insert into seat values('D11' , 5, 'N' ,'N');

insert into seat values('E1', 5, 'N', 'N');
insert into seat values('E2', 5, 'N', 'N');
insert into seat values('E3' , 5, 'N' ,'N');
insert into seat values('E4' , 5, 'N' ,'N');
insert into seat values('E5' , 5, 'N' ,'N');
insert into seat values('E6' , 5, 'N' ,'N');
insert into seat values('E7' , 5, 'N' ,'N');
insert into seat values('E8' , 5, 'N' ,'N');
insert into seat values('E9' , 5, 'N' ,'N');
insert into seat values('E10' , 5, 'N' ,'N');

insert into seat values('F1', 5, 'N', 'N');
insert into seat values('F2', 5, 'N', 'N');
insert into seat values('F3' , 5, 'N' ,'N');
insert into seat values('F4' , 5, 'N' ,'N');
insert into seat values('F5' , 5, 'N' ,'N');
insert into seat values('F6' , 5, 'N' ,'N');
insert into seat values('F7' , 5, 'N' ,'N');
insert into seat values('F8' , 5, 'N' ,'N');
insert into seat values('F9' , 5, 'N' ,'N');

select * from seat;
--drop table seat;

--=====================================
--좌석 트리거 resetvation_yn 업데이트 트리거 (예매완료 시 남은좌석 -?)
--=====================================
create or replace trigger trg_reservationyn_remainseats
    before
    update on seat
    for each row
begin
    --N -> Y인 경우 (예매완료)
    if :new.reservation_yn != :old.reservation_yn and :new.reservation_yn = 'Y' then
       update movienow set remain_seats = remain_seats - 1
       where show_code = :new.show_code;
       
    --Y -> N인 경우 (예매취소)
    elsif :new.reservation_yn != :old.reservation_yn and :new.reservation_yn = 'N' then
        update movienow set remain_seats = remain_seats + 1
        where show_code = :new.show_code;
    end if;
end;
/
select * from trg_reservationyn_remainseats;
--drop trigger trg_reservationyn_remainseats;
commit;

--=====================================
--포인트 적립/사용 내역 테이블
--=====================================
create table pointList(
    pointcode number, --시퀀스
    memberid varchar2(15),
    amount number, 
    status varchar(10), 
    pointdate date default sysdate,
    movie_title varchar2(50),
    constraint pk_point_pointcode primary key(pointcode),
    constraint fk_point_memberid foreign key(memberid) references member(memberid),
    constraint ch_point_status check(status in ('적립', '사용'))
);

--drop table pointList;
--drop sequence seq_pointList;

--포인트 시퀀스
create sequence seq_pointList;
--​truncate table pointList;

select * from pointList;

commit;
--=====================================
--티켓판매량 트리거 - 무비테이블의 ticket_sales변화
--=====================================
create or replace trigger trg_movie_ticket_sales
    before
    insert or update on ticketing
    for each row
begin
    --예매인 경우
    if :new.cancel_yn = 'N' then
        update movie set ticket_sales  = ticket_sales + 1
        where movie_title = :new.movie_title;
    --예매 취소인 경우
    else
        update movie set ticket_sales = ticket_sales - 1
        where movie_title = :new.movie_title;
    end if;
end;
/
select * from ticketing;
--=====================================
--포인트 트리거 - 멤버테이블의 포인트 변화
--=====================================
create or replace trigger trg_member_point
    before
    insert on pointList
    for each row
begin
    --적립인 경우
    if :new.status = '적립' then
       update member set point = point + :new.amount
       where memberid = :new.memberid;
    --사용인 경우
    else
        update member set point = point - :new.amount
        where memberid = :new.memberid;
    end if;
end;
/
​
select * from trg_member_point;

--=====================================
--결제 테이블
--=====================================
create table payment (
	payment_code	varchar2(50) not null,
	member_id varchar2(15) not null,
    payment_method varchar2(20),
	payment_date varchar2(20) default to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS'),
	payment_price number not null,
    constraint pk_payment_code primary key(payment_code),
    constraint fk_member_id foreign key(member_id) references member(memberid)
);
​
--drop table payment;

commit;

--=====================================
--예매 테이블
--=====================================
create table ticketing (
	ticket_no number	not null, --시퀀스로
    movie_title varchar2(50) not null,
	payment_code varchar2(50) not null, --결제코드
	member_id varchar2(15) not null, --아이디
	seat_no 	varchar2(15) not null, --좌석번호(여러 개)
	screen_code number not null, --상영관코드
    start_time varchar2(30) not null, 
    end_time varchar2(30) not null,
	ticketing_date	varchar2(20) default to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS'),
	cancel_yn char default 'N', --취소여부
    cancel_date varchar2(20) default to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS'),
	payment_price number, --결제가격
    show_date date, --상영일시
    constraint pk_ticket_no primary key(ticket_no),
    constraint ck_cancel_yn check(cancel_yn in ('Y','N'))
);
​
--예매 시퀀스
create sequence seq_ticketing;

--drop table ticketing;
--drop sequence seq_ticketing;

select * from ticketing;
select * from payment;
--delete from payment where payment_method = '신용카드';

commit;

--==================================================
--drop query
--==================================================
​
SELECT  'DROP TABLE ' || object_name || ' CASCADE CONSTRAINTS;',  'DROP SEQUENCE' || object_name
FROM    user_objects
WHERE   object_type = 'TABLE';

--DROP TABLE SEAT CASCADE CONSTRAINTS;
--DROP TABLE TICKETING CASCADE CONSTRAINTS;
--DROP TABLE POINTLIST CASCADE CONSTRAINTS;
--DROP TABLE PAYMENT CASCADE CONSTRAINTS;
--DROP TABLE MOVIENOW CASCADE CONSTRAINTS;
--DROP TABLE SCREENAREA CASCADE CONSTRAINTS;
--DROP TABLE CSCOMMENT CASCADE CONSTRAINTS;
--DROP TABLE CUSTOMERSERVICE CASCADE CONSTRAINTS;
--DROP TABLE FAQ CASCADE CONSTRAINTS;
--DROP TABLE NOTICE CASCADE CONSTRAINTS;
--DROP TABLE MOVIE_COMMENT CASCADE CONSTRAINTS;
--DROP TABLE LIKELIST CASCADE CONSTRAINTS;
--DROP TABLE MOVIE CASCADE CONSTRAINTS;
--DROP TABLE MEMBER CASCADE CONSTRAINTS;
--drop sequence seq_pointList;
--
--drop sequence seq_like_no;
--drop sequence seq_movie_comment;
--drop sequence seq_notice_no;
--drop sequence seq_board_no;
--drop sequence seq_cscomment_no;
--drop sequence seq_movienow;
--drop sequence seq_ticketing;
--drop sequence seq_movienow;
