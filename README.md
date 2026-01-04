# file-extension-filter

### API 요약 표

| 기능 | Method | URL | Description |
| --- | --- | --- | --- |
| 초기 상태 조회 | GET | /api/file-extensions | 전체 확장자 상태 조회 |
| 고정 확장자 체크 변경 | PATCH | /api/file-extensions/fixed | 차단 여부 토글 |
| 커스텀 확장자 추가 | POST | /api/file-extensions/custom | 확장자 추가 |
| 커스텀 확장자 삭제 | DELETE | /api/file-extensions/custom/{name} | 확장자 삭제 |

### 데이터베이스 설계

```
file_extension
-----------------------
id           (PK)
name         VARCHAR(20) UNIQUE
type         ENUM(FIXED, CUSTOM)
blocked      BOOLEAN
created_at   TIMESTAMP
```

### 폴더 구조

```java
repo/
 ├ frontend/
 └ backend/
```

### 배포 환경

```markdown
- AWS EC2 단일 인스턴스에 배포
- Docker Compose를 사용하여 프론트엔드, 백엔드, Nginx 컨테이너 구성
- Nginx를 리버스 프록시로 사용하고 SSL 설정을 통해 HTTPS 적용
```
