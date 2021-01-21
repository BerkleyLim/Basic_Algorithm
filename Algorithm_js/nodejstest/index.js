
var express = require('express');

var app = express();

app.get('/',(req, res) => {
    res.send('hello world!');
});

app.listen(3000, () => {
    // https://yuddomack.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-%EB%AC%B8%EB%B2%95-%EB%B9%84%EA%B5%AC%EC%A1%B0%ED%99%94-%ED%95%A0%EB%8B%B9
    [a1, a2, ...rest_a] = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    console.log(a1); // 1
    console.log(a2); // 2
    console.log(rest_a); // [3, 4, 5, 6, 7, 8, 9]

    var [b1, b2, ...rest_b] = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    let [c1, c2, ...rest_c] = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    const [d1, d2, ...rest_d] = [1, 2, 3, 4, 5, 6, 7, 8, 9];

    var { a1 : awesome_name, a2 : dumb , ...rest_a } = { a1 : 10, a2 : 20, a3 : 30, a4 : 40 };
    console.log(awesome_name); // 10
    console.log(dumb); // 20
})