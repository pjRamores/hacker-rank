package com.hackerrank.algo.impl;

import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/magic-square-forming
 */
public class MagicSquareForming {
    
    private enum Direction {
        VERTICAL,
        HORIZONTAL;
    }
    
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        byte [][] magicSquare = new byte[][]{{4, 9, 2}, {3, 5, 7}, {8, 1, 6}};
        byte [][] square = new byte[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                square[i][j] = in.nextByte();
            }
        }
        in.close();
        
        int cost = calculateCost(magicSquare, square);
        
        rotate(magicSquare);
        cost = Math.min(cost, calculateCost(magicSquare, square));
        
        rotate(magicSquare);
        cost = Math.min(cost, calculateCost(magicSquare, square));
        
        rotate(magicSquare);
        cost = Math.min(cost, calculateCost(magicSquare, square));
        
        flip(magicSquare, Direction.HORIZONTAL);
        cost = Math.min(cost, calculateCost(magicSquare, square));
        
        rotate(magicSquare);
        cost = Math.min(cost, calculateCost(magicSquare, square));
        
        rotate(magicSquare);
        cost = Math.min(cost, calculateCost(magicSquare, square));
        
        rotate(magicSquare);
        cost = Math.min(cost, calculateCost(magicSquare, square));
        
        System.out.println(cost);
    }
    
    private static int calculateCost(byte [][] magicSquare, byte [][] square) {
        int cost = 0;
        for(int i = 0; i < magicSquare.length; i++) {
            for(int j = 0; j < magicSquare[i].length; j++) {
                cost += Math.abs(magicSquare[i][j] - square[i][j]);
            }
        }
        return cost;
    }
    
    /*
     * rotate clock-wise direction
     */
    private static void rotate(byte arr [][]) {
        byte [] temp = new byte[arr[0].length];
        byte sideLen = (byte) arr[0].length;
        for(int i = sideLen-1; i >= 0; i--) { //top side
            temp[i] = arr[0][i];
            arr[0][i] = arr[sideLen-i-1][0];
        }
        for(int i = 1; i < sideLen; i++) { //left side
            arr[i][0] = arr[sideLen-1][i];
        }
        for(int i = 1; i < sideLen-1; i++) { //bottom side
            arr[sideLen-1][i] = arr[sideLen-i-1][sideLen-1];
        }
        for(int i = sideLen-1; i > 0; i--) { //right side
            arr[i][sideLen-1] = temp[i];
        }
    }
    
    private static void flip(byte arr [][], Direction direction) {
        switch(direction) {
            case HORIZONTAL :
                for(int i = 0; i < arr.length; i++) {
                    int lastIndex = arr[i].length-1;
                    arr[i][0] -= arr[i][lastIndex];
                    arr[i][lastIndex] += arr[i][0];
                    arr[i][0] = (byte) (arr[i][lastIndex] - arr[i][0]);
                }
                break;
            case VERTICAL :
                for(int j = 0; j < arr[0].length; j++) {
                    int lastIndex = arr.length-1;
                    arr[0][j] -= arr[lastIndex][j];
                    arr[lastIndex][j] += arr[0][j];
                    arr[0][j] = (byte) (arr[lastIndex][j] - arr[0][j]);
                }
                break;
        }
    }
}
