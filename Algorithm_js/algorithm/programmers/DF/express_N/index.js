var express = require('express');

var app = express();

// 사칙연산
// N = 1 ~ 9
// N으로만 사칙연산하여 number값을 만든다.
// N을 몇번 이용한 최솟값만 추출하여라!
// number = 1 ~ 32000
// 괄호 + 사칙연산만 가능 (나누기에서 나머지는 무시)
// 최솟값이 8보다 클 시 -1로 return


// DF 문제이지만, BFS로 응용해서 문제 풀기, set 활용
// 문제 풀이 참조 : https://allo-ew.tistory.com/118
function solution(N, number) {
    const s = [new Set()];

    // let : 블록 유효 범위를 갖는 지역 변수를 선언하며, 선언과 동시에 임의의 값으로 초기화 할 수도 있음
    // fill() 객체 : 배열의 처음 인덱스 부터 끝 인덱스의 이전까지 정적인 값 하나로 채운다.
    // 
    for (let i = 1; i <= 8; i++) {
        s.push(new Set([new Array(i).fill(N).join('') * 1]));

        for (let j = 1; j <= i; j++) {
            //  전개 연산자 : ...
            //  var, key, const를 사용해 변수들의 유효 범위를 명시적으로 선언 가능
            //  단 다른 속성이면 에러!
            //  자세한건 https://yuddomack.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-%EB%AC%B8%EB%B2%95-%EB%B9%84%EA%B5%AC%EC%A1%B0%ED%99%94-%ED%95%A0%EB%8B%B9 참조
            for (const x1 of [...s[j]]) {
                for (const x2 of [...s[i-j]]) {
                    s[i].add(x1 + x2);
                    s[i].add(x1 - x2);
                    s[i].add(x1 * x2);

                    if (x2) {
                        s[i].add((x1 / x2) - (x1 / x2) %  1);
                    }
                }
            }

            if (s[i].has(number)) {
                return i;
            }
        }

    }

    return -1;
}


// 참조 사항
// function solution(N, number) {
//     var answer = 1;
    
//     if (N == number) {
//         return answer;
//     } else {
//         answer++;
//     }
    
//     var list = [N];
//     // 본 알고리즘 시작
//     // 배열 요소 제거 방법
//     // - push 메서드 : 배열의 마지막에 새로운 요소를 추가한 후, 변경된 배열의 길이를 반환
//     // - pop 메서드 : 배열의 마지막 요소를 제거한 후, 제거한 요소를 반환
//     // - unshift 메서드 : 배열의 첫 번째 자리에 새로운 요소를 추가한 후, 변경된 배열의 길이를 반환
//     // - shift 메서드 : 배열의 첫 번째 요소를 제거한 후, 제거한 요소를 반환
//     while(answer <= 8) {
//         var length = list.length;

//         // for in 문 : for (변수 in 객체) => 해당 객체에 모든 연거할 수 있는 프로퍼티를 순회
//         // for of 문 : for (변수 of 객체) => 순회 할 수 있게 하는 객체 (Array, Map, Set, arguments 등)
//         // 참조 : https://yjshin.tistory.com/entry/JavaScript-%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-for-%EB%AC%B8-for-in-%EB%AC%B8-for-of-%EB%AC%B8
//         for (i in length) {
//             // 
//         }
//     }
    
//     return (answer > 8) ? -1 : answer;
// }

// 답 : 4, 3
answer = [
    solution(5,12), solution(2,11)
]

app.get('/', (req,res) => {
    res.send(answer[0] + " " + answer[1]);
});

app.listen(3000, function() {
    console.log(answer[0] + " " + answer[1]);
});