# Branch Spec Map

## 사용법
- 브랜치명과 사용할 spec 파일을 1:1로 매핑한다.
- 경로는 `Specs/` 기준 상대 경로로 작성한다.
- 동일 브랜치 재작업 시 기존 매핑을 재사용한다.

## 매핑
- `feature/example` -> `feature-example.md`

## 규칙
- 브랜치명과 파일명이 같으면 이 파일 없이 `Specs/<WORK_BRANCH>.md`를 우선 사용한다.
- 매핑이 없으면 `Specs/default.md`를 사용한다.
