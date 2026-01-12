### GIT 명령어 간단 정리

git status -> 현재 Git 상태 확인

git diff ~
 -> 나온 파일 리스트 변경점 확인(modified)

git add ~
 -> git stage 에 올리기

git reset
 -> git stage 초기화

git commit -m ~
 -> 커밋 메시지 작성

git log
 -> 올라간 커밋 확인

git config --local user.name/email ~
 -> 깃 커밋 등록자 설정

git push
 -> 레포지토리에 커밋 푸시

git checkout -b ~
 -> 브랜치 생성

git branch
 -> 현재 브랜치 확인

git merge 브랜치명
 -> 작성한 브랜치 소스를 현재 브랜치로 합치기

롤백하기
1. git log -> 커밋 해시코드 복사
2. git reset --hard 해시코드 -> 해당 커밋으로 돌아간다

커밋메시지 작성하며 머지하기
git merge --no-ff --log 브랜치명

#### 리베이스*
1. 소스를 작업하고 커밋까지 해뒀는데 해당 소스가 main 브랜치에서 변경이 일어났다.
2. 그런데 그걸 머지했다
 -> CONFLICT 발생
3. 여기서 소스 반영을 뭘하지 정해서 수정해도 되지만 커밋 로그가 지저분하게 남는다.

그래서 만약 내가 작업한 소스에 변경점이 일어났다면 머지하고 수정하기 보다는 git rebase 를 입력하자.

그러면 이미 올라간 최신 소스가 받아지며 내가 소스를 선택해서 수정할 수 있다.

이후, git add ~ -> git rebase --continue