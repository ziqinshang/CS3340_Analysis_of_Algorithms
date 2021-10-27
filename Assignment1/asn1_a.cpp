//
//  asn1_a.cpp
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
int fib1(int n){
    if (n <= 1)
        return n;
    return fib1(n-1) + fib1(n-2);
}
int main() {
    int fib;
    cout<<"F(i ∗ 5), where 0 ≤ i ≤ 10, \n";
        for(int i = 0; i<10; i++){
            cout<<"The Fibonacci number of "<<i*5<<" is\n";
            cout<<fib1(i*5);
            cout<<"\n";
        }
}
