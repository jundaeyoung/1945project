# 🙌안녕하세요. 1945를 만든 E조 입니다.
<br>
## 🖥️ 프로젝트 소개
자바 스윙을 이용하여 실제 STRIKER 1945 게임을 모티브로 구현함. 

플레이어가 총알을 발사해서 적군을 물리치는 게임으로 
1분 동안 살아남으면 GAMECLEAR, 목숨 3개를 잃으면 GAMEOVER가 되도록 설정함. 

<br>

## 🕰️ 개발 기간
* 23.02.23~23.02.27

### 🧑‍🤝‍🧑 맴버구성
 - 팀원1 : 강민정 
1. Item 클래스 속도 아이템, 목숨 아이템 구현
2. Item과 Player가 닿았을 시 절댓값 조정하여 속도와 목숨 구현
3. Bullet에 접근하여 Enemy가 죽었을 때 랜덤값에 따라 아이템이 나오도록 구현
4. Player의 목숨이 0이 되면 게임 오버 구현
5. Timer 클래스를 이용하여 제한 시간 1분동안 Player가 살아남으면 게임 클리어 구현


 - 팀원2 : 이서영
1. Enemy 클래스 유닛별 생명력, 공격속도, 이동속도, 이동패턴 구현
2. Enemy 클래스 유닛별 피격 범위, 총알 발사 위치 조정
3. Timer 클래스 제한 시간 구현
4. 생성된 Enemy클래스 AirlineFrame의 ArrayList로 관리
5. ArrayList 사용 방식에 호환되도록 전체적인 코드 수정
6. Timer 클래스를 활용한 EnemyUnit 클래스들 소환 흐름 구현

 - 팀원3 : 이지운
1. Enemy1, 2 클래스 리스트로 구현하여 생성 
2.Enemy1, 2 클래스 움직임 구현
3. BackgroundEnemyService 구현
4. EnemyBullet calss 구현
5. BackgroundPlayer 오류 수정

 - 팀원4 : 전대영 
1. mainframe 설계
2. Player 방향 전환, 공격 기능, 속도 설계
3. BackgroundPlayer 벽 충돌 감지 기능 추가
4. Bullet 클래스 player가 space를 누르면 공격하도록 설계 
5. EnemyBullet calss 구현
6. 배경 흘러가도록 구현
7. 생명력 기능 추가 player가 공격당하면 하트 사라지도록 설계
8. BGM 추가
9. 스코어 기능 추가, 적 유닛 종류마다 스코어 다르게 설계
10. 아이템 오류 수정


### ⚙️ 개발 환경
- `Java`
- `JDK 11.0`
- **IDE** : Eclipse


## 📌 주요 기능
#### 클래스 - <a href="https://github.com/jundaeyoung/1945project/wiki" >상세보기 - WIKI 이동</a>

- DB값 검증
- ID찾기, PW찾기
- 로그인 시 쿠키(Cookie) 및 세션(Session) 생성
#### 회원가입 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(Member)" >상세보기 - WIKI 이동</a>
- 주소 API 연동
- ID 중복 체크
#### 마이 페이지 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(Member)" >상세보기 - WIKI 이동</a>
- 주소 API 연동
- 회원정보 변경

#### 영화 예매 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EC%98%81%ED%99%94-%EC%98%88%EB%A7%A4)" >상세보기 - WIKI 이동</a>
- 영화 선택(날짜 지정)
- 영화관 선택(대분류/소분류 선택) 및 시간 선택
- 좌석 선택
- 결제 페이지
- 예매 완료
#### 메인 페이지 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EB%A9%94%EC%9D%B8-Page)" >상세보기 - WIKI 이동</a>
- YouTube API 연동
- 메인 포스터(영화) 이미지 슬라이드(CSS)
#### 1대1문의 및 공지사항 - <a href="" >상세보기 - WIKI 이동</a> 
- 글 작성, 읽기, 수정, 삭제(CRUD)

#### 관리자 페이지 
- 영화관 추가(대분류, 소분류)
- 영화 추가(상영시간 및 상영관 설정)
