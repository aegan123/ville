/*Tehtävägeneraattori Ville-ympäristöön.
 * Uudenvuodenaatto/joulukuusi binäärilukuharjoitus
 * Distributed under MIT licence.
 * MIT License

Copyright (c) [2018] [Juhani Vähä-Mäkilä (juhani@fmail.co.uk)]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

Except as contained in this notice, the name(s) of the above copyright holders shall not be used in advertising or otherwise to promote the sale, use or other dealings in this Software without prior written authorization. 
 */
package com.example.villeprojekti;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

/**
 * 
 * @author Juhani Vähä-Mäkilä, 2018. Licensed under MIT license.
 * @version 1.0
 */
  
public class JoulukuusiGenerator {
	private static Random rnd=new Random();
	/**Maps a "number" to boolean array.*/
	private static Map<Character, boolean[]> MAP =createMap();
	/**Boolean matrix representing a year.*/
	private Vector<boolean[]> binarymatrix;
	/**Randomly generated year.*/
	private int year;
	/**
	 * Constructs the problem by generating a random year and
	 * the boolean matrix that represents it.
	 */
	public JoulukuusiGenerator() {
		year=rnd.nextInt((LocalDate.now().getYear()-1900))+1900;
		binarymatrix=createMatrix(year);
	}
/**
 * Returns True/False whether the specified lamp should be on or not.
 * @param i Row number.
 * @param j Column number.
 * @return True/False whether the specified lamp should be on or not.
 */
	public boolean isOn(int i, int j) {
		return binarymatrix.get(i)[j];
	}
/**
 * Checks if the answer is correct.
 * @param answer User specified answer to the problem.
 * @return True if answer is correct. False otherwise.
 */
	public boolean isRightAnswer(String answer) {
		return year==Integer.parseInt(answer.replaceAll("\\s+", ""));
	}
/**
 * Creates a hashmap to map decimal numbers to 
 * boolean array representing them as binary.
 * @return Generated map.
 */
	private static HashMap<Character, boolean[]> createMap() {
		HashMap<Character, boolean[]> temp=new HashMap<Character,boolean[]>();
		temp.put('0', new boolean[]{false,false,false,false});
		temp.put('1', new boolean[]{false,false,false,true});
		temp.put('2', new boolean[]{false,false,true,false});
		temp.put('3', new boolean[]{false,false,true,true});
		temp.put('4', new boolean[]{false,true,false,false});
		temp.put('5', new boolean[]{false,true,false,true});
		temp.put('6', new boolean[]{false,true,true,false});
		temp.put('7', new boolean[]{false,true,true,true});
		temp.put('8', new boolean[]{true,false,false,false});
		temp.put('9', new boolean[]{true,false,false,true});
		return temp;
	}

	/**
	 * Creates the boolean matrix representing binary digits of the randomly generated year.
	 * @param year2 The year the matrix should represent.
	 * @return Generated boolean matrix.
	 */
	private static Vector<boolean[]> createMatrix(int year){
		String year2=Integer.toString(year);
		Vector<boolean[]> templist=new Vector<boolean[]>();
		for(int i=0;i<4;i++) {
			boolean[] luku=MAP.get(year2.charAt(i));
			templist.add(luku);
		}
		return templist;
		
	}


}
