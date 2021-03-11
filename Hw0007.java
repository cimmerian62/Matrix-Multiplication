/*
course: CSC191
project: Hw7
date: March 19, 2020
author: Josiah Luikham
purpose: Matrix Multiplication
 */
import java.util.Scanner;
import java.util.Scanner;
class Matrix {
    final int MAXROW = 10;
    final int MAXCOL = 10;
    int A[][];
    int nR, nC;
    
    Matrix() {
        A = new int[MAXROW][MAXCOL];
        nR = nC = 0;
    }
    
    void setMatrix(int nR, int nC) {
        Scanner in = new Scanner(System.in);
        
        this.nR = nR;
        this.nC = nC;
        
        for (int r = 0; r < nR; r++)
            for (int c = 0; c < nC; c++) {
                System.out.print("Enter a number for ["+r+"]["+c+"]: ");
                A[r][c] = in.nextInt();
            }
    }
    
    void printMatrix() {
        for (int r = 0; r < nR; r++) {
            for (int c = 0; c < nC; c++)
                System.out.printf("%5d", A[r][c]);
            System.out.println();
        }
    }
    
    
    //multiply m1.A[][] and m2.A[][] into m3.A[][] using only loops and return m3
    Matrix multiplyIterative(Matrix m1, Matrix m2) {
        if (m1.nC != m2.nR)
                return null;
            Matrix m3 = new Matrix();
            m3.nR = m1.nR;
            m3.nC = m2.nC;
        
            for (int r = 0; r < m3.nR; r++)
                for (int c = 0; c < m3.nC; c++) {
                    m3.A[r][c] = 0;
                    for (int i = 0; i < m1.nC; i++)
                        m3.A[r][c] += m1.A[r][i]*m2.A[i][c];
            }
                     
        return m3;      
    }
    
    //multiply m1.A[][] and m2.A[][] into m3.A[][] recursively and return m3
    //reduce c2 for recursive call
    Matrix multiplyRecursive(Matrix m1, Matrix m2, int c2) { //at lowest level of recursion 1st column is created, at highest level, last column is created
        
        if (c2 == 0) //base case returns a matrix
            return new Matrix();
        
        Matrix m3 = multiplyRecursive(m1, m2, c2-1); // the new matrix returned is m3
        
        m3.nR = m1.nR;  //m3's parameters are defined
        m3.nC = m2.nC;
        
        for (int r = 0; r < m3.nR; r++){ //this for loop counts down rows
            m3.A[r][c2-1] = 0;
            for (int i = 0; i < m1.nC; i++) //this one synchronizes the multiplication of numbers in the matrices
                m3.A[r][c2-1] += m1.A[r][i]*m2.A[i][c2-1];
        }
        return m3;
    }
 
    //multiply m1.A[][] and m2.A[][]
    Matrix multiplyRecursive(Matrix m1, Matrix m2) {
        return multiplyRecursive(m1, m2, m2.nC);
    }
}

class Hw0007 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        Matrix m3;
        String res;

        do {
            System.out.println("Enter the first matrix: ");
            System.out.println("How many rows?: ");
            int nR = in.nextInt();
            System.out.println("How many columns?: ");
            int nC = in.nextInt();
            m1.setMatrix(nR, nC);
            m1.printMatrix();
            System.out.println("Enter the second matrix: ");
            System.out.println("row size is "+nC);
            nR = nC;
            System.out.println("How many columns?: ");
            nC = in.nextInt();
            m2.setMatrix(nR, nC);
            m2.printMatrix();
            
            m3 = m1.multiplyIterative(m1, m2);
            System.out.println("The result(iterative): ");
            m3.printMatrix();
            m3 = m1.multiplyRecursive(m1, m2);
            System.out.println("The result(recursive): ");
            m3.printMatrix();
            
            System.out.println("continue? ");
            res = in.next();

        } while (res.charAt(0) == 'y' || res.charAt(0) == 'Y');
    }  
}