// express를 npm으로 설치한 것 호출
// npm install express -save
// 참조 : https://m.blog.naver.com/PostView.nhn?blogId=azure0777&logNo=220465552731&proxyReferer=https:%2F%2Fwww.google.com%2F
// nodemon으로 사용 가능

// express란 : 메인 파일이라고 하는 진입점
var express = require('express');

var app = express();

app.get('/', function (req, res) {
    res.send('hello world!');
});

app.listen(3000, function() {
    console.log('Server On!');
});

// 이후 node test.js 하면 됨