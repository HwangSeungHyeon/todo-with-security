# todo-with-security

## 👨‍🏫 프로젝트 소개
Spring Security와 JWT를 사용하여 할일 작성 어플리케이션 백엔드 서버 구축을 연습해보는 프로젝트입니다.

## ⏲️ 개발기간
- 2024.01.16(월) ~ 2024.01.18(목)

## 📚️ Stacks

### ✔️ Language
<img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white">

### ✔️ Version Control
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

### ✔️ IDE
<img src="https://img.shields.io/badge/intellij idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">

### ✔️ Framework
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">

### ✔️ DB
<img src="https://img.shields.io/badge/supabase-3FCF8E?style=for-the-badge&logo=supabase&logoColor=white">

### API Document
#### Todo API
|기능|Method|Status Code|URI|Request|Response|
|----|------|----------|---|--------|--------|
|할 일 상세조회|GET|200|/todos/{todoId}|{<br>todoId: Long<br>}|{<br>todoId: Long,<br>title: String,<br>content: String,<br> createName: String,<br>createAt: LocalDateTime,<br>updateAt: LocalDateTime,<br>status: Boolean, <br>comments: MutableList\<CommentEntity\><br>}|
|할 일 목록 조회|GET|200|/todos||{<br>todoId: Long,<br>title: String,<br>content: String,<br> createName: String,<br>createAt: LocalDateTime,<br>updateAt: LocalDateTime,<br>status: Boolean<br>}|
|할 일 작성|POST|201|/todos|{<br>title: String,<br>content: String<br>}|{<br>todoId: Long,<br>title: String,<br>content: String,<br> createName: String,<br>createAt: LocalDateTime,<br>updateAt: LocalDateTime,<br>status: Boolean<br>}|
|선택한 할 일 수정|GET|200|/todos/{todoId}|{<br>todoId: Long<br>title: String,<br>content: String<br>}|{<br>todoId: Long,<br>title: String,<br>content: String,<br> createName: String,<br>createAt: LocalDateTime,<br>updateAt: LocalDateTime,<br>status: Boolean<br>}|
|선택한 할 일 상태 수정|GET|200|/todos/{todoId}|{<br>todoId: Long<br>}|{<br>todoId: Long,<br>title: String,<br>content: String,<br> createName: String,<br>createAt: LocalDateTime,<br>updateAt: LocalDateTime,<br>status: Boolean<br>}|
|선택한 할 일 삭제|GET|204|/todos/{todoId}|{<br>todoId: Long<br>}||

#### Comment API
|기능|Method|Status Code|URI|Request|Response|
|----|------|----------|---|--------|--------|
|댓글 단일 조회|GET|200|/todos/{todoId}/comments/{commentId}|{<br>todoId: Long<br>commentId: Long<br>}|{<br>commentId: Long,<br>content: String,<br>name: String<br>createAt: LocalDateTime,<br>updateAt: LocalDateTime<br>}|
|댓글 목록 조회|GET|200|/todos/{todoId}/comments|{<br>todoId: Long<br>}|{<br>commentId: Long,<br>content: String,<br>name: String<br>createAt: LocalDateTime,<br>updateAt: LocalDateTime<br>}|
|댓글 작성|POST|201|/todos/{todoId}/comments|{<br>todoId: Long,<br>content: String<br>}|{<br>commentId: Long,<br>content: String,<br>name: String<br>createAt: LocalDateTime,<br>updateAt: LocalDateTime<br>}|
|선택한 댓글 수정|GET|200|/todos/{todoId}/comments/{commentId}|{<br>todoId: Long,<br>commentId: Long,<br>content: String<br>}|{<br>commentId: Long,<br>content: String,<br>name: String<br>createAt: LocalDateTime,<br>updateAt: LocalDateTime<br>}|
|선택한 댓글 삭제|GET|204|/todos/{todoId}/comments/{commentId}|{<br>todoId: Long,<br>commentId: Long<br>}||

#### User API
|기능|Method|Status Code|URI|Request|Response|
|----|------|----------|---|--------|--------|
|회원가입|POST|200|/signup|{<br>name: String,<br>email: String,<br>password: String<br>}|{<br>userId: Long,<br>name: String,<br>email: String<br>}|
|로그인|POST|200|/login|{<br>email: String,<br>password: String<br>}|{<br>userId: Long,<br>name: String,<br>email: String<br>}|

### ERD
![ERD](https://github.com/HwangSeungHyeon/todo-with-security/blob/develop/img/ERD.png)
