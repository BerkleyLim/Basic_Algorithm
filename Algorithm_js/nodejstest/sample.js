var express = require('express');

var app = express();

// function solution(N, number) {
//     const s = [new Set()];

//     // let : 블록 유효 범위를 갖는 지역 변수를 선언하며, 선언과 동시에 임의의 값으로 초기화 할 수도 있음
//     // fill() 객체 : 배열의 처음 인덱스 부터 끝 인덱스의 이전까지 정적인 값 하나로 채운다.
//     // 
//     for (let i = 1; i <= 8; i++) {
//         s.push(new Set([new Array(i).fill(N).join('') * 1]));

//         for (let j = 1; j <= i; j++) {
//             //  전개 연산자 : ...
//             //  var, key, const를 사용해 변수들의 유효 범위를 명시적으로 선언 가능
//             //  단 다른 속성이면 에러!
//             //  자세한건 https://yuddomack.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-%EB%AC%B8%EB%B2%95-%EB%B9%84%EA%B5%AC%EC%A1%B0%ED%99%94-%ED%95%A0%EB%8B%B9 참조
//             for (const x1 of [...s[j]]) {
//                 for (const x2 of [...s[i-j]]) {
//                     s[i].add(x1 + x2);
//                     s[i].add(x1 - x2);
//                     s[i].add(x1 * x2);

//                     if (x2) {
//                         s[i].add((x1 / x2) - (x1 / x2) %  1);
//                     }
//                 }
//             }

//             if (s[i].has(number)) {
//                 return i;
//             }
//         }

//     }

//     return -1;
// }



// // 답 : 4, 3
// answer = [
//     solution(5,12), solution(2,11)
// ]

app.get('/', (req,res) => {
    // res.send(answer[0] + " " + answer[1]);
});

app.listen(3000, function() {
    const s = [new Set()];

    N = 5, number = 12;

    // console.log(s)
    // let : 블록 유효 범위를 갖는 지역 변수를 선언하며, 선언과 동시에 임의의 값으로 초기화 할 수도 있음
    // fill() 객체 : 배열의 처음 인덱스 부터 끝 인덱스의 이전까지 정적인 값 하나로 채운다.
    // 
    for (let i = 1; i <= 8; i++) {
        s.push(new Set([new Array(i).fill(N).join('') * 1]));
        // console.log(s)
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
                    // console.log(x1 + " " + x2)
                }
            }

            if (s[i].has(number)) {
                // console.log(i);
            }
        }

    }

    //console.log(-1);
});