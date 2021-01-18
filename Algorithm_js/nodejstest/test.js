// express를 npm으로 설치한 것 호출
// npm install express -save
// 참조 : https://m.blog.naver.com/PostView.nhn?blogId=azure0777&logNo=220465552731&proxyReferer=https:%2F%2Fwww.google.com%2F
// nodemon으로 사용 가능

// express란 : 메인 파일이라고 하는 진입점

// 설치된 module은 require 명령어를 통해 불러 올 수 있고, express module을 express 라는 이름의 변수로 저장
var express = require('express');

// express에 ()가 붙는 것으로 봐서 express module은 함수!
// express module은 그냥 함수가 아니라 생성자이다.
var app = express();

// 서버의 root에 get 요청이 들어오면 Hello World!라는 신호를 클라이언트에 보내라는 명령어
// app.get : eventListener, 두개의 argument를 받는데
// 첫번째 : 조건, 두번째 : 반응 함수
app.get('/', function (req, res) {
    res.send('hello world!');
});


// app.listen : 두개의 argument를 받음, 첫번째 : port번호, 두번째 : 함수
app.listen(3000, function() {
    console.log('Server On!');
});

// 이후 node test.js 하면 됨