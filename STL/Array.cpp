#include <array>
#include <bits/stdc++.h>

using namespace std;

int main() {
  array<int, 5> arr = {1, 2, 3, 4, 5};
  cout << "The elements in array are: ";
  for (int i = 0; i < 5; i++) {
    cout << arr[i] << " ";
  }
  cout << endl;

  cout << "The element at position 2 is " << arr.at(1) << endl;

  cout << "is array empty? " << arr.empty() << endl; // checks size of array

  cout << "First Element is: " << arr.front() << endl;

  cout << "First Element is: " << arr[0] << endl;

  cout << "Last Element is: " << arr.back() << endl;

  cout << "Last Element is: " << arr[sizeof(arr) / sizeof(arr[0]) - 1] << endl;

  return 0;
}
