## 주요 기능 및 설계 고려 사항

### 1. 커스텀 확장자 추가

커스텀 확장자 추가 기능은 **프론트엔드와 백엔드에서 이중 검증**을 적용하고,

**DB 레벨에서도 Unique 제약 조건을 설정**하여 안정성을 높였습니다.

- 입력값 **앞뒤 공백 및 점(.) 제거**
- **소문자 영문 + 숫자 조합만 허용**
- **중복 확장자 등록 방지**
- **최대 길이 20자 제한**
- **커스텀 확장자 최대 200개 제한**

입력 검증에는 `@Valid` 및 커스텀 검증 로직을 함께 사용하여

잘못된 요청이 비즈니스 로직까지 전달되지 않도록 했습니다.

</br>

### 2. 고정 확장자 차단 토글

고정 확장자는 **차단 여부만 변경 가능**하도록 설계했습니다.

- 커스텀 확장자를 고정 확장자처럼 토글하려는 경우 **예외 발생**
- 고정/커스텀 확장자의 역할이 섞이지 않도록 **명확히 분리**

</br>

### 3. 커스텀 확장자 삭제

커스텀 확장자는 삭제가 가능하지만, 고정 확장자는 삭제할 수 없습니다.

- 고정 확장자를 삭제하려는 요청에 대해 **예외 응답 반환**

</br>

### 4. API 응답 일관화 및 에러 처리 설계

API 응답은 `SuccessType`, `ErrorType`, `BaseResponse`, `ResponseEntity`를 활용하여

**성공 / 실패 응답 구조를 일관되게 관리**하도록 설계했습니다.

또한 단순한 실패 응답이 아닌, **실패 원인을 구체적으로 구분하여 응답**하도록 구현했습니다.

</br>

### 5. 확장자 엔티티 설계

확장자를 fixed, custom 두 개의 엔티티로 분리하지 않고,

**하나의 FileExtension 엔티티로 통합 관리**하도록 설계했습니다.

- 확장자 유형은 enum(Type)으로 구분
- 공통 필드와 로직을 한 곳에서 관리
- 정책 차이는 비즈니스 로직 레벨에서 제어

이를 통해 불필요한 테이블 분리로 인한 중복 구조를 피하고,

확장자 유형이 추가되더라도 **확장 가능한 구조**를 유지할 수 있도록 했습니다.

</br>


## API 요약 표

| 기능 | Method | URL | Description |
| --- | --- | --- | --- |
| 초기 상태 조회 | GET | /api/file-extensions | 전체 확장자 상태 조회 |
| 고정 확장자 체크 변경 | PATCH | /api/file-extensions/fixed/{id} | 차단 여부 토글 |
| 커스텀 확장자 추가 | POST | /api/file-extensions/custom | 확장자 추가 |
| 커스텀 확장자 삭제 | DELETE | /api/file-extensions/custom/{id} | 확장자 삭제 |
</br>

## 데이터베이스 설계

```
file_extension
-----------------------
id           (PK)
name         VARCHAR(20) UNIQUE
type         ENUM(FIXED, CUSTOM)
blocked      BOOLEAN
created_at   TIMESTAMP
modified_at   TIMESTAMP
```
</br>

## 폴더 구조

```java
repo/
 ├ frontend/
 └ backend/
```
</br>

## 배포 환경

```markdown
- AWS EC2 단일 인스턴스에 배포
- Docker Compose를 사용하여 프론트엔드, 백엔드, Nginx 컨테이너 구성
- Nginx를 리버스 프록시로 사용하고 SSL 설정을 통해 HTTPS 적용
```
