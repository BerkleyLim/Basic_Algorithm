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