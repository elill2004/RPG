/*
Name: Pekka's Adventure
Date: 2022-03-02
Author: Elill Mathivannan
Version: 1.0.0
*/
import arc.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.image.*;

public class epicrpg{
	public static void main(String[] args){
		Console con = new Console(1300, 1080);
		//load map from csv file
		String strMap[][] = rpgtools.loadMap(con, "rpgmap.csv");
		//create game variables for movement, options, and stats
		int intX;
		int intY;
		char chrOption = ' ';
		char chrMovement = ' ';
		int intRow;
		int intCol;
		int intTempx = 0;
		int intTempy = 0;
		int intHP = 50;
		int intAttack = 10;
		int intPotion = 0;
		int intDefense = 5;
		int intLevel = 1;
		int intMaxHP = 50;
		int intDefeat = 0;
		//main character file
		BufferedImage imgHero = con.loadImage("hero2.png");
		
		for(int i =0; i<28; i++){
			for(int j =0; j < 30; j++){
				//get starting point for character in array
				if(strMap[i][j].equals("h")){
					intTempx = j;
					intTempy = i; 
				}
			}
		}
		//starting position
		intX = intTempx*30;
		intY = intTempy*30;
		//game code
		while(intHP > 0 && intDefeat < 10 && chrOption != 'q'){
			//open start menu and give user options
			rpgtools.Start(con);
			chrOption = con.getChar();
			if(chrOption == 'p' && intHP > 0){
				//draw map and hero
				rpgtools.drawMap(con, strMap);
				con.drawImage(imgHero, intX, intY);
			}else if(chrOption == 'h'){
				//open up help menu
				rpgtools.Help(con);
			}
			while(chrOption == 'p' && intHP > 0 && intDefeat < 10){
				//draw HUD, get user input, draw map again
				rpgtools.HUD(con, intHP, intAttack, intDefense, "");
				chrMovement = con.getChar();
				rpgtools.drawMap(con, strMap);
				//variable used to store character's position in the array later
				String strLocation = "";
				//movement option
				if(chrMovement == 'w'){
					intY -= 30;
				}else if(chrMovement == 'a'){
					intX -= 30;
				}else if(chrMovement == 's'){
					intY += 30;
				}else if(chrMovement == 'd'){
					intX += 30;
				}
				//go through array for every movement
				for(intRow = 0; intRow < 28; intRow++){
					for(intCol = 0; intCol < 30; intCol++){
						intTempx = intCol;
						intTempy = intRow;
						//kill user if position = water
						if(strMap[intRow][intCol].equals("w")){
							if((intY/30) == intTempy && (intX/30) == intTempx){
								intHP = 0;
							}
						}
						//make sure user does not walk into tree
						else if(strMap[intRow][intCol].equals("t")){
							if((intY/30) == intTempy && (intX/30) == intTempx && chrMovement == 'w'){
								intY += 30;
							}else if((intY/30) == intTempy && (intX/30) == intTempx && chrMovement == 'd'){
								intX -= 30;
							}else if((intY/30) == intTempy && (intX/30) == intTempx && chrMovement == 'a'){
								intX += 30;
							}else if((intY/30) == intTempy && (intX/30) == intTempx && chrMovement == 's'){
								intY -= 30;
							}
						}
						//check for enemy and enemy level
						else if(strMap[intRow][intCol].equals("e1") || strMap[intRow][intCol].equals("e5") || strMap[intRow][intCol].equals("e8") || strMap[intRow][intCol].equals("e10")){
							strLocation = strMap[intRow][intCol];
							if((intY/30) == intTempy && (intX/30) == intTempx){
								//get HP after battle
								intHP = rpgtools.Enemy(con, intHP, intAttack, intPotion, intDefense, intMaxHP, strLocation);
								//increase stats based on difficulty of enemies
								if(intHP > 0 && strLocation.equals("e1")){
									intLevel += 1;
									intHP += intLevel;
									intAttack += 2;
									intDefense += 2;
									intMaxHP += 2;
									intDefeat += 1;
								}else if(intHP > 0 && strLocation.equals("e5")){
									intLevel += 2;
									intHP += 4;
									intAttack += 4;
									intDefense += 4;
									intMaxHP += 4;
									intDefeat += 1;
								}else if(intHP > 0 && strLocation.equals("e8")){
									intLevel += 3;
									intHP += intLevel;
									intAttack += 6;
									intDefense += 6;
									intMaxHP += 6;
									intDefeat += 1;
								}else if(intHP > 0 && strLocation.equals("e10")){
									intLevel += 4;
									intHP += intLevel;
									intAttack += 8;
									intDefense += 8;
									intMaxHP += 8;
									intDefeat += 1;
								}
								strMap[intRow][intCol] = "g";
								rpgtools.drawMap(con, strMap);
							}
						}
						//walk into buildings to heal
						else if(strMap[intRow][intCol].equals("b")){
							strLocation = strMap[intRow][intCol];
							if((intY/30) == intTempy && (intX/30) == intTempx){
								intHP = intMaxHP;
								rpgtools.HUD(con, intHP, intAttack, intDefense, strLocation);
								con.sleep(1000);
								if((intY/30) == intTempy && (intX/30) == intTempx && chrMovement == 'w'){
									intY += 30;
								}else if((intY/30) == intTempy && (intX/30) == intTempx && chrMovement == 'd'){
									intX -= 30;
								}else if((intY/30) == intTempy && (intX/30) == intTempx && chrMovement == 'a'){
									intX += 30;
								}else if((intY/30) == intTempy && (intX/30) == intTempx && chrMovement == 's'){
									intY -= 30;
								}
							}
						}
						//attack potion boost
						else if(strMap[intRow][intCol].equals("ap")){
							if((intY/30) == intTempy && (intX/30) == intTempx){
								intAttack += 3;
								strMap[intRow][intCol] = "g";
								rpgtools.drawMap(con, strMap);
							}
						}
						//defense potion boost
						else if(strMap[intRow][intCol].equals("dp")){
							if((intY/30) == intTempy && (intX/30) == intTempx){
								intDefense += 2;
								strMap[intRow][intCol] = "g";
								rpgtools.drawMap(con, strMap);
							}
						}
						//pick up potion, usable in battle
						else if(strMap[intRow][intCol].equals("p")){
							if((intY/30) == intTempy && (intX/30) == intTempx){
								intPotion = 1;
								strMap[intRow][intCol] = "g";
								rpgtools.drawMap(con, strMap);
							}
						}
					}
				}
				//draw postion of character again
				con.drawImage(imgHero, intX, intY);
				con.repaint();
			}
		}
		//if all enemies defeated player wins
		if(intDefeat == 10){
			con.setDrawColor(new Color(0, 0, 0));
			con.fillRect(200, 300, 600, 300);
			con.setDrawColor(new Color(255, 255, 255));
			rpgtools.Font(con, "Oswald-Bold.ttf", 60, "You Win!!!", 350, 370);
			con.repaint();
		}
		//gameover if 0 HP
		if(intHP <= 0){
			con.setDrawColor(new Color(0, 0, 0));
			con.fillRect(200, 300, 600, 300);
			con.setDrawColor(new Color(255, 255, 255));
			rpgtools.Font(con, "Oswald-Bold.ttf", 60, "Game Over", 350, 370);
			con.repaint();
		}
		//quit game option
		if(chrOption == 'q'){
			con.closeConsole();
		}
					
	}
	
}
		
