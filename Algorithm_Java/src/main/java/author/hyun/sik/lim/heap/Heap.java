package author.hyun.sik.lim.heap;

public class Heap {
    // 최대 힙 삽입
    int[] maxHeap;
    int heapSize = 0;
    
    void insert_max_heap(int x) {
        
        maxHeap[++heapSize] = x;
        
        for (int i = heapSize; i > 1; i /= 2) {
            // 마지막 노드가 자신의 부모 노드보다 크면 swap
            if (maxHeap[i/2] < maxHeap[i]) {
                swap(i/2, i);
            } else {
                break;
            }
        }
    }
    
    // 힙 삭제
    int delete_max_heap() {
        if (heapSize == 0) {
            // 배열이 빌때
            return 0;
        }
        
        int item = maxHeap[1]; // 루트 노드의 값을 저장
        maxHeap[1] = maxHeap[heapSize]; // 마지막 노드의 값을 루트 노드에 둔다.
        maxHeap[heapSize--] = 0; // 힙 크기를 하나 줄이고 마지막 노드를 0으로 초기화
        
        for (int i = 1; i * 2 <=heapSize;) {
            // 마지막 노드가 왼쪽 노드와 오른쪽 노드보다 크면 반복문을 나간다.
            if (maxHeap[i] > maxHeap[i*2] && maxHeap[i] > maxHeap[i*2+1]) {
                break;
            }
            
            // 왼쪽 노드가 더 큰 경우, 왼쪽 노드와 마지막 노드를 swap
            else if (maxHeap[i*2] > maxHeap[i*2+1]) {
                swap(i, i*2);
                i = i*2;
              }
              // 오른쪽 노드가 더 큰 경우, 오른쪽 노드와 마지막 노드를 swap
              else {
                swap(i, i*2+1);
                i = i*2+1;
              }
            }
            return item;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
}
