/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adjacencymatrix;

import java.util.ArrayList;

/**
 *
 * @author isaac
 */
public class AdjacencyMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<String>();
        input.add("A = 2*A");
        input.add("B = C + 5");
        input.add("C = 4*D");
        input.add("D = 8 + B");
        
        int n = input.size();
	int adjaMatrix[][] = new int[n][n];
        int P[][] = new int[n][n];
        int O[][] = new int[n][n];
	char c;
        boolean result;
        
        for(int i = 0; i < n; i++){
            for(int j = 2; j < input.get(i).length(); j++){
                c = input.get(i).charAt(j);
                if('A' <= c && c <= 'Z')
                    adjaMatrix[i][c-'A'] = 1;
            }
        }
        
        for(String x : input)
            System.out.println(x);
        System.out.println("");
       
       P = adjaMatrix;
       for(int i = 2; i <= n; i++){
           showMatrix(P);
           O = orMatrices(P,powerMatrix(adjaMatrix,i));
           P = O;
       }
       
       int i;
       for(i=0; i<n; i++){
           if(P[i][i] == 1)
               continue;
           else
               break;
       }
       if(i != n)
           result = false;
       else
           result = true;
       
        System.out.println(result);
    }
    
    public static void showMatrix(int[][] a){
        int n = a[0].length;
        for(int p = 0; p < n; p++){
            for(int q = 0; q < n; q++){
                System.out.print(a[p][q] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static int[][] multiplyMatrices(int[][] a, int[][] b){ //Only works if the two matrices are squared and have the same dimension
        int n = a[0].length;
        int product[][] = new int[n][n];
        
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++){
                product[i][j]= 0;
                for (int k=0; k<n; k++)                        
                    product[i][j]+= a[i][k]*b[k][j];
                }
        return product;
    }
    
    public static int[][] powerMatrix(int[][] A, int p){//Only for squared matrixes
        int n = A[0].length;
        int[][] P = new int[n][n];
        int[][] Q = new int[n][n];
        
        //P = I
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++){
                if(i==j)
                    P[i][j]=1;
                else
                    P[i][j]=0;
            }
        
        for(int i=0;i<p; i++){
            Q = multiplyMatrices(P,A);
            P = Q;
        }
        
        return P;
    }
    
    public static int[][] orMatrices(int[][] A, int[][] B){ //Squared matrices same order
        int n = A[0].length;
        int[][] O = new int[n][n];
        
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++){
                if(A[i][j] == 1 || B[i][j] == 1)
                    O[i][j] = 1;
                else
                    O[i][j] = 0;
            }
        
        return O;
    }
    
}
