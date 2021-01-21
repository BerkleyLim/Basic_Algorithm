// 문제가 개편되었습니다. 이로 인해 함수 구성이나 테스트케이스가 변경되어, 과거의 코드는 동작하지 않을 수 있습니다.
// 새로운 함수 구성을 적용하려면 [코드 초기화] 버튼을 누르세요. 단, [코드 초기화] 버튼을 누르면 작성 중인 코드는 사라집니다.
function solution(N, number) {
    const cache = new Array(9).fill(0).map(el => new Set());
    for(let i=1; i<9; i++){
        cache[i].add('1'.repeat(i) * N);
        for (let j=1; j < i; j++) {
            for(const arg1 of cache[j]){
                for(const arg2 of cache[i-j]){
                    cache[i].add(arg1+arg2);
                    cache[i].add(arg1-arg2);
                    cache[i].add(arg1*arg2);
                    cache[i].add(arg1/arg2>>0);
                }
            }
        }
        if(cache[i].has(number)) return i; 
    }
    return -1;
}