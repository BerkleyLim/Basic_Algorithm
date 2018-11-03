/*
스타트와 링크 : https://www.acmicpc.net/problem/14889
*/ 

#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int arr[25][25];
int ans;
int n;
int group[25];

void check(){
    vector<int> g1;
    vector<int> g2;
    for(int i=0;i<n;i++){
        if(group[i] == 1) g1.push_back(i);
        else g2.push_back(i);
    }
    int s1 = 0;
    int s2 = 0;
    for(int i=0;i<n/2-1;i++){
        for(int j=i+1;j<n/2;j++){
            s1 += arr[g1[i]][g1[j]] + arr[g1[j]][g1[i]];
            s2 += arr[g2[i]][g2[j]] + arr[g2[j]][g2[i]];
        }
    }
    
    if(ans > abs(s1-s2)) ans = abs(s1-s2);
    g1.clear();
    g2.clear();
}

void setGroup(int cur, int cnt){
    if(cnt == n/2){
        //check
        check();
        return;
    }
    
    for(int i=cur ; i<n;i++){
        group[i] = 1;
        setGroup(i+1, cnt+1);
        group[i] = 0;
    }
}

int main(){
    cin>>n;
    ans = 10000000;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cin>>arr[i][j];
        }
    }
    
    setGroup(0,0);
    cout<<ans<<'\n';
}