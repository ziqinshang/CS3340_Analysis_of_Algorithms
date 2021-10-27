//
//  asn1_b.cpp
//  CS3340A1
//
//  Created by David Shang on 2021-02-03.
//
#include <iostream>
#include <vector>
#include <cstring>
#include <chrono>
#include <stdio.h>
//#include "BigInt.hpp"
using namespace std;
struct bign{
    int d[1000];
    int len;
    bign(){
        memset(d,0,sizeof(d));
        len=0;
    }
    bign(int n){
        memset(d,n,sizeof(d));
        len=1;
    }
};
bign change(char str[]){
    bign a;
    a.len=strlen(str);
    for(int i=0;i<a.len;i++){
        a.d[i]=str[a.len-i-1]-'0';
    }
    return a;
}
bign add(bign a,bign b){
    bign c;
    int carry=0;
    for(int i=0;i<a.len||i<b.len;i++){
        int temp=a.d[i]+b.d[i]+carry;
        c.d[c.len++]=temp%10;
        carry=temp/10;
    }
    if(carry!=0){
       c.d[c.len++] =carry;
    }
    return c;
}
void print(bign a){
    for(int i=a.len-1;i>=0;i--)
    {
        printf("%d",a.d[i]);
    }
}
bign fib2(int n) {
    char str1[1000] = "0";
    char str2[1000] = "0";
    char str3[1000] = "1";
    bign a=change(str1);
    bign b=change(str2);
    bign c=change(str3);
        if (n < 2) {
            return n;
        }
        for (int i = 2; i <= n; ++i) {
            a = b;
            b = c;
            c = add(a, b);
        }
    return c;
}
int main(int argc, const char * argv[]) {
    int fib;
    cout<<"Enter the fibonacci number you wish to compute\n";
    //cin>>fib;
    for(int i = 0; i<=25; i++){
        cout<<"The Fibonacci number of "<<i*20<<" is\n";
        print(fib2(i*20));
        cout<<"\n";
    }

}
