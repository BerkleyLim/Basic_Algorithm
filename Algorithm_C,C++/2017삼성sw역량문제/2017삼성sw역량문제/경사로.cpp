/*
 ���� : https ://www.acmicpc.net/problem/14890
*/

#include <iostream>
using namespace std;

int n, x;
int arr[110][110];
int visited[110][110];
int ans;

int rightCheck(int s, int t, int idx){
    //idx = 1 ������
    //idx = 2 �Ʒ�
    
    if(idx==1){
        for(int j=t+1;j<=t+x;j++){
            if(j>n) return 0;
            if(visited[s][j] == 1) return 0;
            
            if(arr[s][t] != arr[s][j] + 1) return 0;;
        }
        
        //���� �� ������ visited üũ
        for(int j=t+1;j<=t+x;j++) visited[s][j] = 1;
    }
    else{
        for(int i=s+1;i<=s+x;i++){
            if(i>n) return 0;
            if(visited[i][t] == 1) return 0;
            
            if(arr[s][t] != arr[i][t] + 1) return 0;
        }
        
        //���� �� ������ visited üũ
        for(int i=s+1;i<=s+x;i++) visited[i][t] = 1;
    }
    return 1;
}

int leftcheck(int s, int t, int idx){
    //idx = 1 ����
    //idx = 2 ����
    
    if(idx==1){
        //���� check
        for(int j=t-1;j>=t-x;j--){
            if(j<0) return 0;
            if(visited[s][j] == 1) return 0;
            
            if(arr[s][t] != arr[s][j] + 1) return 0;

        }
        
        //���� �� ������ visited üũ
        for(int j=t-1;j>=t-x;j--) visited[s][j] = 1;
    }
    else{
        for(int i=s-1;i>=s-x;i--){
            if(i<0) return 0;
            if(visited[i][t] == 1) return 0;
            
            if(arr[s][t] != arr[i][t] + 1) return 0;

        }
        
        //���� �� ������ visited üũ
        for(int i=s-1;i>=s-x;i--) visited[i][t] = 1;
    }
    return 1;
}

void go(){
    //�� ���� Ȯ��
    for(int i=0;i<n;i++){
        int end_flag = 0;
        for(int j=0;j<n-1;j++){
            
            if(arr[i][j] == arr[i][j+1]) continue;
            else if(arr[i][j] == arr[i][j+1] + 1){
                //�����ʸ� ��� Ȯ��
                int right = rightCheck(i, j, 1);
                if(right == 0 ){
                    end_flag = 1;
                    break;
                }
                else j += (x-1);
            }
            else if(arr[i][j] + 1 == arr[i][j+1]){
                
                j++;
                
                if(arr[i][j] < arr[i][j+1] && x>=2){
                    end_flag= 1;
                    break;
                }
                
                if(arr[i][j] == arr[i][j+1] + 1){
                    //������ ���� �� Ȯ��
                    int right = rightCheck(i, j, 1);
                    int left = leftcheck(i, j, 1);
                    if(right==1 && left == 1){
                        j += (x-1);
                    }
                    else{
                        end_flag=1;
                        break;
                    }
                }
                else{
                    //���ʸ� Ȯ��
                    int left = leftcheck(i, j, 1);
                    if(left != 1){
                        end_flag=1;
                        break;
                    }
                }
                
            }
            else{
                end_flag = 1;
                break;
            }
            
        }
        if(end_flag==0) ans++;
    }
    
    for(int i=0;i<n;i++) for(int j=0;j<n;j++) visited[i][j] = 0;
    
    
    //����üũ
    for(int j=0;j<n;j++){
        int end_flag = 0;
        for(int i=0;i<n-1;i++){
            
            if(arr[i][j] == arr[i+1][j]) continue;
            else if(arr[i][j] == arr[i+1][j] + 1){
                //�����ʸ� ��� Ȯ��
                int right = rightCheck(i, j, 2);
                if(right == 0 ){
                    end_flag = 1;
                    break;
                }
                else i += (x-1);
            }
            else if(arr[i][j] + 1 == arr[i+1][j]){
                
                i++;
                if(arr[i][j]  < arr[i+1][j] && x>=2){
                    end_flag = 1;
                    break;
                }
                
                if(arr[i][j] == arr[i+1][j] + 1){
                    //������ ���� �� Ȯ��
                    int right = rightCheck(i, j, 2);
                    int left = leftcheck(i, j, 2);
                    if(right==1 && left == 1){
                        i += (x-1);
                    }
                    else{
                        end_flag=1;
                        break;
                    }
                }
                else{
                    //���ʸ� Ȯ��
                    int left = leftcheck(i, j, 2);
                    if(left!=1){
                        end_flag=1;
                        break;
                    }
                }
                
            }
            else{
                end_flag = 1;
            }
            
        }
        if(end_flag==0) ans++;
    }
    
}
    


int main(){
    cin>>n>>x;
    
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cin>>arr[i][j];
            visited[i][j] = 0;
        }
    }
    
    go();
    cout<<ans<<'\n';
}