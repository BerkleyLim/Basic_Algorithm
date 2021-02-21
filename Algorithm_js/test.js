// 직사각형 만들기!
// 필수 4개의 점 중 3개의 좌표가 있다
// 직사각형 만드는데 필요한 나머지 한 점의 좌표를 return 하도록 하라
// 직사각형의 각 변 x축 y축에 평행해야할 것!
/*
제한사항
v는 세 점의 좌표가 들어있는 2차원 배열입니다.
v의 각 원소는 점의 좌표를 나타내며, 좌표는 [x축 좌표, y축 좌표] 순으로 주어집니다.
좌표값은 1 이상 10억 이하의 자연수입니다.
직사각형을 만드는 데 필요한 나머지 한 점의 좌표를 [x축 좌표, y축 좌표] 순으로 담아 return 해주세요.
*/
function solution(v) {
    var answer = [];

    const x =[], y =[];

    // forEach 문 
    // 첫번쨰 인수는 배열의 각각의 item 
    // 두번쨰 인수는 배열의 index 
    // 세번째 인수는 배열 그자체

    v.forEach((item,idx,arr) => {
        if (!x[item[0]]) x[item[0]] = -1;
        else x[item[0]] = 0;

        if (!y[item[1]]) y[item[1]] = -1;
        else y[item[1]] = 0;
    })

    v.forEach((item,idx,arr) => {
        if (x[item[0]] == -1) answer[0] = item[0];
        if (y[item[1]] == -1) answer[1] = item[1];
    })

    return answer;
}

/*
            v	            result
[[1, 4], [3, 4], [3, 10]]	[1, 10]
[[1, 1], [2, 2], [1, 2]]	[2, 1]

*/
var v = [[1, 4], [3, 4], [3, 10]];
var answer = solution(v);
console.log(answer);
