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
 */
package com.example.villeprojekti;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class JoulukuusiGenerator {
	private static HashMap<String,boolean[]> MAP =createMap();
	private static Random rnd=new Random();
	private Vector<Vector<Boolean>> binarymatrix;
	private String year;

	public JoulukuusiGenerator() {
		year=Integer.toString(rnd.nextInt((LocalDate.now().getYear()-1900))+1900);
		binarymatrix=createMatrix(year);
	}
public Vector<Vector<Boolean>> getBinarymatrix() {
		return binarymatrix;
	}
/**
 * 
 * @return
 */
	public boolean isRightAnswer(String answer) {
		return answer.equals(year);
	}
/**
 * Creates a hashmap to map decimal numbers to binary numbers
 * in a boolean array.
 * @return
 */
	private static HashMap<String, boolean[]> createMap() {
		HashMap<String, boolean[]> temp=new HashMap<String,boolean[]>();
		temp.put("0", new boolean[]{false,false,false,false});
		temp.put("1", new boolean[]{false,false,false,true});
		temp.put("2", new boolean[]{false,false,true,false});
		temp.put("3", new boolean[]{false,false,true,true});
		temp.put("4", new boolean[]{false,true,false,false});
		temp.put("5", new boolean[]{false,true,false,true});
		temp.put("6", new boolean[]{false,true,true,false});
		temp.put("7", new boolean[]{false,true,true,true});
		temp.put("8", new boolean[]{true,false,false,false});
		temp.put("9", new boolean[]{true,false,false,true});
		return temp;
	}

	private static Vector<Vector<Boolean>> createMatrix(String year2) {
		Vector<Vector<Boolean>> templist=new Vector<Vector<Boolean>>(4,0);
		for(int i=0;i<4;i++) {
			boolean[] luku=MAP.get(year2.substring(i, i+1));
			Vector<Boolean> temp=new Vector<Boolean>(4,0);
			for(int j=0;j<4;j++) {
				temp.add(new Boolean(luku[j]));
			}
			templist.add(temp);
		}
		
		
		return templist;
	}


}
