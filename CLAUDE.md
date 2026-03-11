# CLAUDE.md

## 이 프로젝트에 대해
개인 블로그 & 성장 기록 저장소.
Spring Boot + React 기반 블로그 개발 + 기술 블로그 글 + AI 협업 기록을 한곳에서 관리한다.
"처음부터 끝까지 완주"가 목표.
블로그라는 플랫폼 한정은 아니다
블로그 성격을 띄지만 앞으로 장난감처럼 하고 싶은 기능들 추가 예정

## Tech Stack
- Backend: Java 17, Spring Boot
- Frontend: React, TypeScript, Vite
- Database: MySQL
- Infra: AWS EC2, RDS (목표)

## 디렉토리 구조
```
├── Post/           완성된 기술 블로그 글
├── Post-Drafts/    초안 (피드백 전 단계)
├── Server/         Spring Boot 백엔드
├── Web/            React 프론트엔드
├── Workflow-Week/  주간 회고
└── AI-Develop/     AI 프롬프트 기록 & 학습 정리
    ├── prompts/    잘 된 프롬프트 저장
    ├── sessions/   대화 요약
    └── learnings/  개념 정리
```

## 설계 원칙 (코드)
- 인터페이스 기반 설계 (DIP 준수)
- 생성자 주입 사용 — 필드 주입 금지
- 도메인 로직은 도메인 객체가 가진다
- 외부 의존성으로부터 도메인 보호

## Claude Code 역할 (점검)
- 코드 설계 리뷰 (DIP, 책임 분리 확인)
- Codex가 작성한 코드 검토
- 리팩토링 방향 제안
- 시니어 개발자로서 공격적인 리뷰 진행
- 많은 방향성 제시
- 최종 커밋 전 품질 확인
- 직접 기능 구현은 하지 않는다

## AI 협업 워크플로우
1. Codex → 기능 구현, 테스트 코드 작성
2. 본인 → 코드 읽고 판단
3. Claude Code → 설계 관점 리뷰
4. Claude Code → 최종 판단 후 PR

## 커밋 컨벤션
- feat: 새로운 기능 추가
- fix: 버그 수정
- docs: 문서 수정 (README.md 등)
- style: 코드 의미에 영향을 주지 않는 변경 (포맷팅, 세미콜론 등)
- refactor: 코드 리팩토링
- chore: 빌드 업무 수정, 패키지 매니저 설정 등 (환경설정)
- test: 테스트 코드 추가
- pref: 성능 개선(알고리즘 최적화, 쿼리 튜닝 등)
- ci: CI 설정 파일 및 스크립트 수정

## 기록 규칙
- [ ] 새로운 개념을 사용하면 Claude Code가 learnings/ 에 저장
- [ ] 사용한 프롬프트는 Claude Code가 prompts/ 하위에 일자별로 저장, 같은 일자면 수정하여 추가
- [ ] 세션 종료 시 Claude Code가 sessions/ 에 저장