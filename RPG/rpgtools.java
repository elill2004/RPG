import arc.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.image.*;

public class rpgtools{
	public static String[][] loadMap(Console con, String strFileName){
		//method to load map from csv file and store into array
		TextInputFile txtMap;
		String strMap[][];
		String tempArray[];
		int intCount = 0;
		int intRow;
		int intCol;
		String strLine;
		//open file
		txtMap = new TextInputFile(strFileName);
		while(txtMap.eof() == false){
			strLine = txtMap.readLine();
			intCount += 1;
		}
		tempArray = new String[intCount];
		strMap = new String[28][30];
		txtMap.close();
		txtMap = new TextInputFile(strFileName);
		//store csv file content into array
		for(intRow = 0; intRow < intCount; intRow++){
			strLine = txtMap.readLine();
			tempArray = strLine.split(",");
			for(intCol = 0; intCol < 30; intCol++){
				strMap[intRow][intCol] = tempArray[intCol];
			}
		}
		txtMap.close();
		return strMap;
	}
	
	public static void Start(Console con){
		//main menu
		BufferedImage imgPekka = con.loadImage("HUD.png");
		con.setBackgroundColor(new Color(173, 216, 230));
		con.setDrawColor(new Color(255,255,255));
		rpgtools.Font(con, "Oswald-Bold.ttf", 68, "Pekka's Adventure",400,0);
		rpgtools.Font(con, "Oswald-Bold.ttf", 48, "Please select an option",0,180);
		rpgtools.Font(con, "Oswald-Bold.ttf", 48, "(P)lay",0,300);
		rpgtools.Font(con, "Oswald-Bold.ttf", 48, "(H)elp",0,400);
		rpgtools.Font(con, "Oswald-Bold.ttf", 48, "(Q)uit",0,500);
		con.drawImage(imgPekka, 870, 450);
		con.fillRect(0, 140, 1300, 30);
		con.repaint();
	}
	
	public static void Help(Console con){
		//help
		BufferedImage imgGrass = con.loadImage("grass2.jpg");
		BufferedImage imgWater = con.loadImage("water.jpg");
		BufferedImage imgTree = con.loadImage("tree.jpg");
		BufferedImage imgEnemy = con.loadImage("enemy.jpg");
		BufferedImage imgBuilding = con.loadImage("building.jpg");
		BufferedImage imgHero = con.loadImage("hero2.png");
		BufferedImage imgEnemy5 = con.loadImage("enemy5.png");
		BufferedImage imgEnemy8 = con.loadImage("enemy8.png");
		BufferedImage imgEnemy10 = con.loadImage("enemy10.png");
		BufferedImage imgAtkPotion = con.loadImage("atkpotion.png");
		BufferedImage imgDefPotion = con.loadImage("defpotion.png");
		BufferedImage imgPotion = con.loadImage("potion.png");
		//variable to let user continue
		char chrContinue = ' ';
		con.setBackgroundColor(new Color(173, 216, 230));
		con.setDrawColor(new Color(255,255,255));
		rpgtools.Font(con, "Oswald-Bold.ttf", 48, "These are all tiles in the game",0,10);
		con.drawImage(imgGrass, 0, 100);
		con.drawImage(imgWater, 35, 100);
		con.drawImage(imgTree, 70, 100);
		con.drawImage(imgHero, 105, 100);
		con.drawImage(imgBuilding, 140, 100);
		con.drawImage(imgHero, 175, 100);
		con.drawImage(imgEnemy, 210, 100);
		con.drawImage(imgEnemy5, 245, 100);
		con.drawImage(imgEnemy8, 280, 100);
		con.drawImage(imgEnemy10, 315, 100);
		con.drawImage(imgAtkPotion, 340, 100);
		con.drawImage(imgDefPotion, 375, 100);
		con.drawImage(imgPotion, 410, 100);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "You can walk on grass tiles",0,150);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Walking into water will kill you",0,180);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "You can not walk into trees",0,210);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "The mini pekka is you player character, use 'w' 'a' 's' 'd' to move",0,240);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Buildings will heal you",0,270);
		rpgtools.Font(con, "Oswald-Bold.ttf", 25, "There are different levels of enemies (1,5,8,10), tougher enemies will boost your level higher boosting your HP, atk, and def",0,300);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Red potions will boost your attack",0,330);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Blue potions will boost your defense",0,360);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "The yellow potion can be carried and used once per battle and will restore you to full health",0,390);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Press anything to continue",0,750);
		con.repaint();
		chrContinue = con.getChar();
		con.setDrawColor(new Color(173,216,230));
		con.fillRect(0,0,1380,1080);
		BufferedImage imgText = con.loadImage("textbox.jpg");
		con.drawImage(imgText, 0, 0);
		con.setDrawColor(new Color(255,255,255));
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Q - Ultimate slicer", 40, 40);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "W - Elemental Strike", 40, 120);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "E - Slash", 350, 40);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "R - Poison", 350, 120);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "T - Protect", 650, 40);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Y - Potion", 650, 120);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "You can use ultimate slicer once per battle, deals damage equal to twice your attack - monster defense",0,290);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Elemental strike will deal damage equal to attack - monster defense",0,320);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Slash will do damage equal to half your attack, ignores monter defense and also the default attack",0,350);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Poison will deal 5 damage at the end of each turn",0,380);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Protect will shield you from damage for one turn, can not use twice in a row",0,410);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Potion can be picked up from the map and be used once per battle to restore HP",0,440);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Defeat all the enemies to win the game!!!",0,470);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Press anything to continue",0,750);
		con.repaint();
		chrContinue = con.getChar();
		
	}
	
	public static String[][] drawMap(Console con, String[][] strMap){
		//draw map to the console
		int intRow;
		int intCol;
		//in-game tile files
		BufferedImage imgGrass = con.loadImage("grass2.jpg");
		BufferedImage imgWater = con.loadImage("water.jpg");
		BufferedImage imgTree = con.loadImage("tree.jpg");
		BufferedImage imgEnemy = con.loadImage("enemy.jpg");
		BufferedImage imgBuilding = con.loadImage("building.jpg");
		BufferedImage imgHero = con.loadImage("hero2.png");
		BufferedImage imgEnemy5 = con.loadImage("enemy5.png");
		BufferedImage imgEnemy8 = con.loadImage("enemy8.png");
		BufferedImage imgEnemy10 = con.loadImage("enemy10.png");
		BufferedImage imgAtkPotion = con.loadImage("atkpotion.png");
		BufferedImage imgDefPotion = con.loadImage("defpotion.png");
		BufferedImage imgPotion = con.loadImage("potion.png");
		
		//draw map according to contents of array
		for(intRow = 0; intRow < 28; intRow++){
			for(intCol = 0; intCol < 30; intCol++){
				if(strMap[intRow][intCol].equals("t")){
					con.drawImage(imgTree, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("g")){
					con.drawImage(imgGrass, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("w")){
					con.drawImage(imgWater, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("e1")){
					con.drawImage(imgEnemy, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("e5")){
					con.drawImage(imgGrass, intCol*30, intRow*30);
					con.drawImage(imgEnemy5, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("e8")){
					con.drawImage(imgGrass, intCol*30, intRow*30);
					con.drawImage(imgEnemy8, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("e10")){
					con.drawImage(imgGrass, intCol*30, intRow*30);
					con.drawImage(imgEnemy10, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("b")){
					con.drawImage(imgBuilding, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("h")){
					con.drawImage(imgGrass, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("ap")){
					con.drawImage(imgGrass, intCol*30, intRow*30);
					con.drawImage(imgAtkPotion, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("dp")){
					con.drawImage(imgGrass, intCol*30, intRow*30);
					con.drawImage(imgDefPotion, intCol*30, intRow*30);
				}else if(strMap[intRow][intCol].equals("p")){
					con.drawImage(imgGrass, intCol*30, intRow*30);
					con.drawImage(imgPotion, intCol*30, intRow*30);
				}
			}
		}
		con.repaint();
		return strMap;
	}
	
	public static int Enemy(Console con, int intHP, int intAttack, int intPotion, int intDefense, int intMaxHP, String strEnemy){
		//battle engine
		//draw enemy based on tile
		BufferedImage imgHero = con.loadImage("battlehero.png");
		BufferedImage imgEnemy;
		if(strEnemy.equals("e1")){
			imgEnemy = con.loadImage("enemy1.png");
		}else if(strEnemy.equals("e5")){
			imgEnemy = con.loadImage("boss5.png");
		}else if(strEnemy.equals("e8")){
			imgEnemy = con.loadImage("boss8.png");
		}else{
			imgEnemy = con.loadImage("boss10.png");
		}
		//user move option, character stats, and critical hit system
		char chrAttack = ' ';
		int intEnemyHP;
		int intEnemyAttack;
		int intEnemyDefense;
		int intluck1, intluck2, intluck3;
		int intCrit = 1;
		//randomize enemy stats based on enemy level
		if(strEnemy.equals("e1")){
			intEnemyHP = (int)(Math.random()*18+20);
			intEnemyAttack = (int)(Math.random()*4+5);
			intEnemyDefense = 3;
		}else if(strEnemy.equals("e5")){
			intEnemyHP = (int)(Math.random()*23+30);
			intEnemyAttack = (int)(Math.random()*5+10);
			intEnemyDefense = 5;
		}else if(strEnemy.equals("e8")){
			intEnemyHP = (int)(Math.random()*10+55);
			intEnemyAttack = (int)(Math.random()*5+14);
			intEnemyDefense = 7;
		}else{
			intEnemyHP = 75;
			intEnemyAttack = 25;
			intEnemyDefense = 10;
		}
		//poison, protect, and special move variables
		int intPoison = 0;
		int intProtect = 0;
		int intSpecial = 0;
		while(intEnemyHP > 0 && intHP > 0){
			//draw battle environment and get user battle input
			rpgtools.Battle(con);
			con.drawImage(imgHero, 100, 320);
			con.drawImage(imgEnemy, 700, 300);
			chrAttack = con.getChar();
			//critical hit system
			intluck1 = (int)(Math.random()*5+0);
			intluck2 = (int)(Math.random()*5+0);
			intluck3 = (int)(Math.random()*5+0);
			if(intluck1 == intluck2 && intluck1 == intluck3){
				intCrit = 2;
			}else{
				intCrit = 1;
			}
			//special attack only once per battle
			if(chrAttack == 'q' && intSpecial != 1){
				rpgtools.Animation(con, chrAttack, strEnemy);
				intEnemyHP -= (intAttack*2*intCrit)-intEnemyDefense;
				con.setDrawColor(new Color(255, 255, 255));
				rpgtools.Font(con, "Oswald-Bold.ttf", 30, "You dealt "+((intAttack*2*intCrit)-intEnemyDefense)+" damage", 910, 740);
				con.repaint();
				con.sleep(500);
				intSpecial = 1;
			}
			//semi strong attack based on attack and enemy defense
			else if(chrAttack == 'w'){
				rpgtools.Animation(con, chrAttack, strEnemy);
				intEnemyHP -= (intAttack*intCrit)-intEnemyDefense;
				con.setDrawColor(new Color(255, 255, 255));
				rpgtools.Font(con, "Oswald-Bold.ttf", 30, "You dealt "+((intAttack*intCrit)-intEnemyDefense)+" damage", 910, 740);
				con.repaint();
				con.sleep(500);
			}
			//default attack ignores enemy defense but only doe shalf of attack
			else if(chrAttack == 'e'){
				rpgtools.Animation(con, chrAttack, strEnemy);
				intEnemyHP -= intAttack/2;
				con.setDrawColor(new Color(255, 255, 255));
				rpgtools.Font(con, "Oswald-Bold.ttf", 30, "You dealt "+((intAttack/2))+" damage", 910, 740);
				con.repaint();
				con.sleep(500);
			}
			//poison attack
			else if(chrAttack == 'r' && intPoison == 0){
				intPoison = 1;
				rpgtools.Animation(con, chrAttack, strEnemy);
				con.setDrawColor(new Color(255, 255, 255));
				rpgtools.Font(con, "Oswald-Bold.ttf", 30, "You poisoned the enemy", 910, 740);
				con.repaint();
				con.sleep(500);
			}
			//protect from damage
			else if(chrAttack == 't' && intProtect != -1){
				intProtect = 1;
				rpgtools.Animation(con, chrAttack, strEnemy);
				con.setDrawColor(new Color(255, 255, 255));
				rpgtools.Font(con, "Oswald-Bold.ttf", 30, "You used protect", 910, 740);
				con.repaint();
				con.sleep(500);
			}
			//use potion to heal HP
			else if(chrAttack == 'y' && intPotion > 0){
				intHP = intMaxHP;
				intPotion -= 1;
			}
			//default attack if invalid move
			else{
				chrAttack = 'e';
				rpgtools.Animation(con, chrAttack, strEnemy);
				intEnemyHP -= intAttack/2;
				con.setDrawColor(new Color(255, 255, 255));
				rpgtools.Font(con, "Oswald-Bold.ttf", 24, "Invalid move dealt: "+((intAttack/2))+" damage instead", 910, 740);
				con.repaint();
				con.sleep(500);
			}
			//enemy Attack
			if(intProtect != 1 && intEnemyHP > 0){
				chrAttack = 'd';
				rpgtools.HUD(con, intHP, intAttack, intDefense, "");
				rpgtools.Animation(con, chrAttack, strEnemy);
				intHP -= intEnemyAttack;
				con.setDrawColor(new Color(255, 255, 255));
				rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Enemy dealt: "+intEnemyAttack+" damage", 910, 740);
				con.repaint();
				con.sleep(500);
				intProtect = 0;
			}
			//can't use protect twice in a row
			else{
				intProtect = -1;
			}
			//deal poison damage
			if(intPoison == 1){
				intEnemyHP -= 5;
			}
			//draw HUD	
			rpgtools.HUD(con, intHP, intAttack, intDefense, "");
		}
			
		
		//return HP after battle
		return intHP;
	}
	
	public static void Battle(Console con){
		//battle environment method
		BufferedImage imgBackground = con.loadImage("background.jpg");
		BufferedImage imgText = con.loadImage("textbox.jpg");
		con.drawImage(imgBackground, 0, 0);
		con.drawImage(imgText, 0, 600);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Time to fight...", 5, 590);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Q - Ultimate slicer", 40, 640);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "W - Elemental Strike", 40, 720);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "E - Slash", 350, 640);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "R - Poison", 350, 720);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "T - Protect", 650, 640);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Y - Potion", 650, 720);
		con.repaint();
	}
	
	public static void Animation(Console con, char chrAttack, String strEnemy){
		//battle animations
		BufferedImage imgHero = con.loadImage("battlehero.png");
		BufferedImage imgEnemy;
		BufferedImage imgSwipe = con.loadImage("ultimate_slicer.png");
		BufferedImage imgSwipe2 = con.loadImage("elemental_strike.png");
		BufferedImage imgSwipe3 = con.loadImage("slash.png");
		BufferedImage imgPoison = con.loadImage("poison.png");
		BufferedImage imgProtect = con.loadImage("protect.png");
		if(strEnemy.equals("e1")){
			imgEnemy = con.loadImage("enemy1.png");
		}else if(strEnemy.equals("e5")){
			imgEnemy = con.loadImage("boss5.png");
		}else if(strEnemy.equals("e8")){
			imgEnemy = con.loadImage("boss8.png");
		}else{
			imgEnemy = con.loadImage("boss10.png");
		}
		//animate hero character
		if(chrAttack == 'q'){
			for(int intX = 100; intX < 680; intX += 100){
				con.drawImage(imgHero, intX, 320);
				con.drawImage(imgEnemy, 700, 300);
				con.sleep(100);
				con.repaint();
				rpgtools.Battle(con);
			}
			con.drawImage(imgSwipe, 720, 310);
			con.sleep(100);
		}else if(chrAttack == 'w'){
			for(int intX = 100; intX < 680; intX += 100){
				con.drawImage(imgHero, intX, 320);
				con.drawImage(imgEnemy, 700, 300);
				con.sleep(100);
				con.repaint();
				rpgtools.Battle(con);
			}
			con.drawImage(imgSwipe2, 720, 310);
			con.sleep(100);
		}else if(chrAttack == 'e'){
			for(int intX = 100; intX < 680; intX += 100){
				con.drawImage(imgHero, intX, 320);
				con.drawImage(imgEnemy, 700, 300);
				con.sleep(100);
				con.repaint();
				rpgtools.Battle(con);
			}
			con.drawImage(imgSwipe3, 720, 310);
			con.sleep(1000);
		}else if(chrAttack == 'r'){
			for(int intX = 100; intX < 680; intX += 100){
				con.drawImage(imgHero, intX, 320);
				con.drawImage(imgEnemy, 700, 300);
				con.sleep(100);
				con.repaint();
				rpgtools.Battle(con);
			}
			con.drawImage(imgPoison, 720, 310);
			con.sleep(1000);
		}else if(chrAttack == 't'){
			con.drawImage(imgHero, 100, 320);
			con.drawImage(imgEnemy, 700, 300);
			con.drawImage(imgProtect, 110, 340);
			con.sleep(500);
			con.repaint();
			rpgtools.Battle(con);
		}
		//animate enemy attack
		else if(chrAttack == 'd'){
			for(int intX = 700; intX > 120; intX -= 100){
				con.drawImage(imgHero, 100, 320);
				con.drawImage(imgEnemy, intX, 300);
				con.sleep(100);
				con.repaint();
				rpgtools.Battle(con);
			}
			con.drawImage(imgSwipe, 110, 310);
			con.sleep(100);
		}
	
	}
	
	public static void HUD(Console con, int HP, int Attack, int Defense, String strLocation){
		//draw HUD method
		con.setDrawColor(new Color(0, 0, 0));
		con.fillRect(900, 0, 400, 1080);
		BufferedImage imgPekka = con.loadImage("HUD.png");
		con.drawImage(imgPekka, 870, 20);
		String strHP = "HP: "+HP;
		con.setDrawColor(new Color(255, 255, 255));
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, strHP, 910, 430);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Attack: "+Attack, 910, 550);
		rpgtools.Font(con, "Oswald-Bold.ttf", 30, "Defense: "+Defense, 910, 680);
		if(strLocation.equals("b")){
			rpgtools.Font(con, "Oswald-Bold.ttf", 30, "You are fully healed", 910, 740);
		}
		con.repaint();
	}
		
	
	public static void Font(Console con, String fontType, int fontSize, String message, int intx, int inty){
		//fonts
        Font newFont = con.loadFont(fontType, fontSize);
        con.setDrawFont(newFont);
        con.drawString(message, intx, inty);

    }
}
