## 사용했던 터미널 명령어

1. DB 연결을 위한 Network 체크
* telnet localhost 3306 -> 로컬에 telnet 이 설치 되어있지 않음
* curl -v telnet://localhost:3306 -> 연결 성공
* 위 2개의 방법이 안된다면 bash 내장 기능 활용 -> (echo > /dev/tcp/localhost/3306) >/dev/null 2>&1 && echo "열림" || echo "닫힘"
