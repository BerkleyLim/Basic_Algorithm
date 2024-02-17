# 파일 안내 리스트

## ex01.py

- xyz321 입력 시 나오는 결과 값 출력
- 배열을 묻는 질문 리스트
- Python 환경에서 배열 삽입 시 append() 사용, remove()는 특정 값 제거
- 배열의 내부 중 일부분만 출력
- 문제명
```
# 문제 : xyz321 입력 시 실행 결고

x = input('입력 : ')        # console에서 입력 : xyz321
a = ['abg123', 'def456', 'ghi789']  # 0 : abg123, 1 : def456, 2:ghi789
a.append(x)                         # 0 : abg123, 1 : def456, 2:ghi789, 3 : xyz321
a.remove('def456')                  # 0 : abg123, 1 : ghi789, 2 : xyz321
print(a[1][-3:], a[2][:-3], sep = ',')      # 789, xyz
for i in range(3,6) :
  print(i, end = ' ')               # 3 4 5
  
# 정답 
# 789,xyz
# 3 4 5
```

<br/>

## ex02.py

- 실행 결과 추출

- 문제 

```
lol = [[1,2,3], [4,5], [6,7,8,9]]
print(lol[0])
print(lol[2][1])

# lol 설명
# [0] : [1,2,3]
# [1] : [4,5]
# [2] : [6,7,8,9]
# len() : 3

for sub in lol:   
  for item in sub:
    print(item, end='  ')
  print()
  
# 로직 설명
# sub : lol의 각 배열마다 삽입
# item : sub의 각 배열 요소 저장
# sub[3] = {
#   {1, 2, 3} -> 배열 길이 3
#   {4, 5}  -> 배열 길이 2
#   {6, 7, 8, 9} -> 배열 길이 4
# }


# 실행 결과
# [1, 2, 3]
# 7
# 1  2  3  
# 4  5  
# 6  7  8  9  
```
