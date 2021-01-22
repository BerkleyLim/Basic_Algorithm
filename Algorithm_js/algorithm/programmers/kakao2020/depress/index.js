var express = require('express');

var app = express();

// 문자열은 앞부터 짜른다.
// 여기서 token별로 나눠 짜르는 것이다.
function solution(s) {
    var answer = s.length;
    
    // 문자열 자르기 알고리즘 : 문자열 길이의 절반 이상의 토큰으로 짜르면 시간낭비!
    for (let tokenNumber = 1; tokenNumber <= (s.length / 2); tokenNumber++) {
        let token = s.slice(0, tokenNumber);
        let depressCount = 1; // 압축 갯수
        let min = 0;

        let i = tokenNumber
        for (; i < s.length; i += tokenNumber) {
            // 만일 앞문자와 뒷문자가 같을 시 계산
            if (token === s.slice(i, i + tokenNumber)) {
                depressCount++;
            } else {
                // depressCount 는 숫자, 따라서 문자로 변경 후 자릿수대로 출력을 위해 지정
                (depressCount > 1) ? min += depressCount.toString().length + token.length : min += token.length;
                depressCount = 1;

                token = s.slice(i, i + tokenNumber);
            }
        }

        // 나머지 뒷 문자열 대입
        (depressCount > 1) 
            ? min += depressCount.toString().length + s.slice(i-tokenNumber, s.length).length 
            : min +=  s.slice(i-tokenNumber, s.length).length;

        answer = Math.min(answer,min);
    }

    return answer;
}


app.get('/', (req) => {
    req.send();
});

app.listen(3000, () => {
    console.log(solution("aabbaccc")); // 7
    console.log(solution("ababcdcdababcdcd")); // 9
    console.log(solution("abcabcdede")); // 8
    console.log(solution("abcabcabcabcdededededede")); // 14
    console.log(solution("xababcdcdababcdcd")); // 17
})