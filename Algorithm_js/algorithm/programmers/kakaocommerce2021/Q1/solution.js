var express = require('express');

var app = express();

function solution(n, record) {
    var answer = [];

    var server = new Array(n+1);

    for (let i = 1; i <= n; i++) {
        server[i] = new Array();
    }

    var num = 0;
    var name = '';
    for (var i = 0; i < record.length; i++) {
        var str = record[i].split(" ");
        num = str[0];
        name = str[1];

        num *= 1;

        if (server[num].includes(name))
            continue;

        if (server[num].length >= 5) {
            server[num].shift();
        }
        server[num].push(name)

    }

    server.shift();

    
    // console.log(server)
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < server[i].length; j++) {
            answer.push(server[i][j]);
        }
    }

    return answer;
}

app.get('/', (req,res) => {
    // res.send(answer[0] + " " + answer[1]);
});

app.listen(3000, function() {
    n = 1;
    record = ["1 fracta", "1 sina","1 hana","1 robel","1 abc", "1 sina", "1 lynn"]; // ["sina", "hana", "robel", "abc", "lynn"]
    console.log(solution(n,record));

    n = 4;
    record = ["1 a","1 b","1 abc","3 b","3 a","1 abcd","1 abc","1 aaa","1 a","1 z","1 q", "3 k", "3 q", "3 z", "3 m", "3 b"];
    // ["abc", "abcd", "aaa", "z", "q", "k", "q", "z", "m", "b"]
    console.log(solution(n,record));
});