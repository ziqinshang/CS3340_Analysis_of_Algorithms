//
//  asn1_c.cpp
//  CS3340A1
//
//  Created by David Shang on 2021-02-03.
//
#include <iostream>
#include <stdio.h>
#include <vector>
//#include "BigInt.hpp"
using namespace std;
vector<int> int2vec(int k){
    vector<int> temp;
    while(k!=0){
        temp.push_back(k%10);
        k = k/10;
    }
    return temp;
}
vector<vector<int>> m_multiply(vector<vector<int>>& a, vector<vector<int>>& b) {
        vector<vector<int>> c{{0, 0}, {0, 0}};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
vector<vector<int>> m_pow(vector<vector<int>>& a, int n) {
        vector<vector<int>> ret{{1, 0}, {0, 1}};
        while (n > 0) {
            if (n & 1) {
                ret = m_multiply(ret, a);
            }
            n >>= 1;
            a = m_multiply(a, a);
        }
        return ret;
    }
int fib3(int n) {
        if (n < 2) {
            return n;
        }
        vector<vector<int>> t{{1, 1}, {1, 0}};
        vector<vector<int>> result = m_pow(t, n - 1);
        return result[0][0];
    }
int main(int argc, const char * argv[]) {
    int fib;
    cout<<"Enter the fibonacci number you wish to compute\n";
    cin>>fib;
    cout<<fib3(fib);
}
