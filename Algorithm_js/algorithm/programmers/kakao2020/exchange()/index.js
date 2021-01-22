var express = require('express');

var app = express();

// 괄호 변환
// p 길이 : 2 ~ 1000 (무조건 '(' , ')' 로 이뤄짐)
// 괄호는 항상 같다.
function solution(p) {
    var answer = "";

    var left = 0;   // 왼쪽 괄호 갯수
    var right = 0;  // 오른쪽 괄호 갯수
    var isFalse = false;  // 올바른 괄호 여부 검사 (true :올바른 괄호 아님, false : 올바른 괄호)
0
    if (p.length == 0) {
        return '';
    }
    // array 배열에서는 (괄호,자리번호)
    for (let i = 0; i < p.length; i++) {
        // console.log(i);

        if (p[i] == '(') left++;
        if (p[i] == ')') right++;

        // 오른쪽 괄호가 왼쪽 괄호 수가 넘을 씨 올바른 괄호 아님
        // 즉, 쪼개야함
        if (right > left) isFalse = true;

        // 균형 잡힌 u가 나와서 쪼갠다 (u,v)
        if (left == right) {

            // 올바른 괄호가 x
            if (isFalse) {
                answer += '(';
                answer += solution(p.slice(i + 1, p.length));
                answer += ')';

                // 첫뻔재, 마지막 문자 제거 후 나머지는 괄호 바꾼 후 뒤로 붙힌다.
                for (let j = 1; j < i; j++) {
                    if (p[j] == ')') answer += '(';
                    if (p[j] == '(') answer += ')';
                }
                return answer;
            } else {
                // 올바른 괄호 O
                answer += p.slice(0, i + 1); // 그냥 대입
                answer += solution(p.slice(i + 1, p.length)); // 나머지의 대해서 재귀 호출
                return answer;
            }

        }
    }

}


app.get('/', (req) => {
    req.send();
});

app.listen(3000, () => {
    console.log(solution("(()())()")); // "(()())()"
    console.log(solution(")(")); // "()"
    console.log(solution("()))((()")); // "()(())()"
})