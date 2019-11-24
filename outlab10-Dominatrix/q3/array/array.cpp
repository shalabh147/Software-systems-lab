/** @file array.cpp 
@author Team Dominatrix
@details Illustrates doxygen-style comments for documenting a C++
program file and the functions in that file.
*/

#include <iostream>
#include <stdlib.h>

using namespace std;

/**
 * This function generates an array of dimension 7*7 and fills it with random 
 * numbers from the interval 20 to 419 
 *
 * @param myArr The array to be filled
 * @return 0
 */
int fillArray(int myArr[7][7])                 
{
    for(int i=0;i<7;i++)
    {
        for(int j=0;j<7;j++)                    
        {
            myArr[i][j]=20+rand()%400;              
        }
    }
    return 0;
}

/**
 * This function prints an array by going through it in a spiral fashion.
 * 
 * That is, it starts at the top-left corner and prints the first column in a line. \n
 * Then it turns left and prints the last row (6 elements as the first has already been printed). \n
 * Then it turns left again and prints the last column (again 6 elements). \n
 * Turns left again and print the remaining 5 elements from the first row.  \n
 * It caries on like this till the whole array is printed.
 * @see printArray
 * @param myArr The array to be printed
 * @return void
 */
void printSpiral(int myArr[7][7])
{
    cout << "Array Spiral Function." << endl;
    for(int j=0;j<1;j++)                           
    {                                              
        for(int i=0;i<7;i++)
        {
            cout<< myArr[i][j]<< "  ";
        }

    }
    cout << endl;

    for(int i=6;i<7;i++)                          
    {                                            
        for(int j=1;j<7;j++)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for(int j=6;j<7;j++)                          
    {                                             
        for(int i=5;i>=0;i--)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for (int i=0;i<1;i++)                          
    {                                              
        for (int j=5;j>=1;j--)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for (int j=1; j<2;j++)
    {                                             
        for (int i=1; i<6;i++)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for (int i=5; i<6;i++)                        
    {
        for (int j=2; j<6; j++)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for (int j=5; j<6; j++)                        
    {
        for (int i=4; i>0; i--)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for (int i=1; i<2; i++)                        
    {
       for (int j=4; j>1; j--)
       {
           cout << myArr[i][j] << "  ";
       }
    }
    cout << endl;

    for (int j=2; j<3; j++)                        
    {
        for (int i=2; i<5; i++)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for (int i=4; i<5; i++)                        
    {
        for (int j=3; j<5; j++)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for (int j=4; j<5; j++)                        
    {
        for (int i=3; i>1; i--)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for (int i=2; i<3; i++)                        
    {
        for (int j=3; j<4; j++)
        {
            cout << myArr[i][j] << "  ";
        }
    }
    cout << endl;

    for (int i=3; i<4; i++)                         
    {
        for (int j=3; j<4; j++)
        {
            cout << myArr[i][j] << "  ";
        }
    }
  cout << endl << endl;
}

/**
* This function prints a range of specifed columns (s to e-1 , including both) of the input array.
 * 
 * @param myArr The array to be printed
   @param s start column
   @param e end column
 * @return void
*/
void printCol(int myArr[7][7], int s, int e)                     
{  
    cout << "Column Output" << endl;                
    for (int j=s-1; j<e-1; j++)
    {                                               
        for (int i=0; i<7; i++)
        {
            cout << myArr[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl << endl;
}

/**
As the name suggests this function finds the smallest elements and returns it

@param myArr The input array
@return the smallest element 
*/
int findMin (int myArr[7][7])
{
    int i,j;
    int min = myArr[0][0];

    for (i=0; i<7; i++)                             
    {
        for (j=0; j<7; j++)
        {
            if(myArr[i][j] < min)                   
            {                                       
                min = myArr[i][j];
            }                                       
        }                                           

    }
    return min;
}
/**
    Finds the average over all values in the input array by summing over them all and dividing by 49 (7*7) and returns it
    @param myArr The input array
    @return average value
*/
int findAverage (int myArr[7][7])                  
{                                                   
    int i,j;
    int sum = 0;

    for (i=0; i<7; i++)                             
    {
        for (j=0; j<7; j++)
        {
            sum = sum + myArr[i][j];
        }

    }
    return sum/49;       
}

/**
    Prints an input array normally (i.e. in a rectangular/tabular form)
    @param myArr input array
    @return void
*/
void printArray (int myArr[7][7])                     
{
    cout << "Normal Array Output" << endl;

    for (int i =0; i<7; i++)
    {
        for (int j=0; j<7; j++)
        {
            cout << myArr[i][j] << "  ";
        }
        cout << endl;
    }
    cout << endl;                                   
    cout << endl;
}

/**
    The main function which implements all the other functions
*/
int main(){
    
    int myArr [7][7];
    fillArray(myArr);                               
    printArray(myArr);
    printSpiral(myArr);
    printCol(myArr, 2, 5);
    cout<<"Min:"<<findMin(myArr);
    cout<<" Average:"<<findAverage(myArr);
    cout<<endl;

    return 0;
}
 