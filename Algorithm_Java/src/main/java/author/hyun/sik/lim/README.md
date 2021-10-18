# 메모리 사용량 확인 메소드
Runtime.getRuntime().gc();
long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
System.out.print(usedMemory + " bytes");

# 처리시간 확인 메소드
long start = System.currentTimeMillis(); //시작하는 시점 계산
 
/*
실행시간을 측정하고싶은 코드
*/
 
long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
System.out.println( "실행 시간 : " + ( end - start )/1000.0 +”초”); //실행 시간 계산 및 출력


