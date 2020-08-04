
import java.util.Random;
import java.util.Scanner;


public class Yahtzee {
	
	static Random random = new Random();
	
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		int calScore[] = new int[14];
		calScore[13]=0;
		int RecScore[] = new int[14];
		for(int i = 0 ; i < 14 ; i++)
			RecScore[i] = -1;
		
		int dice[] = new int[5];
		int diceKeepNum[] = new int[5];
		for(int i = 0 ; i<5 ; i++) {
			diceKeepNum[i] = 0;
		}
		int diceCounter[] = new int[6];
		int rollCounter = 0;
		int roundCounter = 13; // or 26
		byte input = 0;
	    
		
		
		System.out.println("���� �����Դϴ�.");
	    System.out.println("������: 1, \nkeep1��: 2, keep2��: 3, keep3��: 4, keep4��: 5, keep5��: 6, \ncheck���̽�: 7, check����: 8, check������: 9, check����: 10, check���̺꽺: 11, check�Ľ���: 12, \ncheck��������ī�ε�: 13, check������ī�ε�: 14, checkǮ�Ͽ콺: 15, check������Ʈ����Ʈ: 16, check������Ʈ����Ʈ: 17, check����: 18, check����: 19"); // ���ڿ� + ���� ���     
		
	    
	    while(roundCounter>0) {

		    System.out.println("\n��ǲ: ");
		    input = scn.nextByte();
		    
			
			if(input == 1) { // ������ ��ư Ŭ�� �� 
				if(rollCounter>=3)
					 System.out.println("\n�� �̻� ���� �� ����. \n");
				else {
				rollDice(dice, diceKeepNum);
				rollCounter++;
				countDice(dice, diceCounter);
				checkRoutine(calScore, RecScore, dice, diceCounter);
				System.out.printf("%d, %d, %d, %d, %d\n", dice[0], dice[1], dice[2], dice[3], dice[4]);
				
				System.out.printf("\n���̽�: %d\n ��: %d\n ����: %d\n ��: %d\n ���̺�: %d\n �Ľ�: %d\n \n��������ī�ε�: %d\n ������ī�ε�: %d\n Ǯ�Ͽ콺: %d\n ������Ʈ: %d\n ������Ʈ: %d\n ����: %d\n ����: %d\n ���ʽ�: %d\n", calScore[0], calScore[1], calScore[2], calScore[3], calScore[4], calScore[5], calScore[6], calScore[7], calScore[8], calScore[9], calScore[10], calScore[11], calScore[12], calScore[13]);
				}
			}
			
			
			else if(input >=2 && input <= 6) { // keep ��ư ���� ���� 
				if(rollCounter>0 && rollCounter<3) {
					
		
					if(diceKeepNum[input-2] == 0) {
						diceKeepNum[input-2] = 1;
						System.out.printf("%d�� �ֻ��� keep", input-1);
					}
					else if(diceKeepNum[input-2] == 1) {
						diceKeepNum[input-2] = 0;
						System.out.printf("%d�� �ֻ��� keep����", input-1);
					}
				}
				else
					System.out.println("\nkeep �� �� ����. \n");
			}
			
			else if(input ==7) { // Aces ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[0]==-1) {
					RecScore[0] = calScore[0];
					calScore[13] += calScore[0];
					if(calScore[13]>=63)
						RecScore[13] = 35;
					rollCounter = 0;
					for(int i = 0 ; i < 5 ; i++)
						diceKeepNum[i]=0;
					roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
				
			}
			
			if(input ==8) { // Twos ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[1]==-1) {
				RecScore[1] = calScore[1];
				calScore[13] += calScore[1];
				if(calScore[13]>=63)
					RecScore[13] = 35;
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
	
			if(input ==9) { // Threes ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[2]==-1) {
				RecScore[2] = calScore[2];
				calScore[13] += calScore[2];
				if(calScore[13]>=63)
					RecScore[13] = 35;
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
			
			if(input ==10) { // Fours ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[3]==-1) {
			
				RecScore[3] = calScore[3];
				calScore[13] += calScore[3];
				if(calScore[13]>=63)
					RecScore[13] = 35;
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
			
			if(input ==11) { // Fives ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[4]==-1) {
			
				RecScore[4] = calScore[4];
				calScore[13] += calScore[4];
				if(calScore[13]>=63)
					RecScore[13] = 35;
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
	
			if(input ==12) { // Sixes ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[5]==-1) {
			
				RecScore[5] = calScore[5];
				calScore[13] += calScore[5];
				if(calScore[13]>=63)
					RecScore[13] = 35;
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
			
			if(input ==13) { // Three-Of-A-Kind ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[6]==-1) {
				RecScore[6] = calScore[6];
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
			
			if(input ==14) { // Four-Of-A-Kind ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[7]==-1) {
				RecScore[7] = calScore[7];
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
	
			if(input ==15) { // Full House ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[8]==-1) {
				RecScore[8] = calScore[8];
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
			
			if(input ==16) { // Small Straight ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[9]==-1) {
				RecScore[9] = calScore[9];
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
			
			if(input ==17) { // Large Straight ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[10]==-1) {
				RecScore[10] = calScore[10];
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
	
			if(input ==18) { // Chance ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[11]==-1) {
				RecScore[11] = calScore[11];
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
			
			if(input ==19) { // Yahtzee ��ư ���� ����   if(rollCounter>0)
				if(rollCounter>0 && RecScore[12]==-1) {
				RecScore[12] = calScore[12];
				rollCounter = 0;
				for(int i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				}
				else
					System.out.println("\n�� �� ����. \n");
			}
			
	    }
		
	}
	
	public static void rollDice(int[] dice, int[] diceKeepNum) {
		for(int i = 0 ; i<5 ; i++) {
			if(diceKeepNum[i]==0)
				dice[i] = random.nextInt(6)+1;
		}
	}
	
	public static void countDice(int[] dice, int[] diceCounter) {
		for(int i = 0 ; i<6 ; i++) {
			diceCounter[i] = 0;
		}

		for(int i = 0 ; i<5 ; i++) {
			diceCounter[(dice[i]-1)]++;
		}
	}
	
	public static void checkRoutine(int calScore[], int RecScore[], int[] dice, int[] diceCounter) {
		if(RecScore[0]==-1)
		calScore[0] = checkAces(diceCounter);
		else
			calScore[0] = RecScore[0];
		
		if(RecScore[1]==-1)
		calScore[1] = checkTwos(diceCounter);
		else
			calScore[1] = RecScore[1];
		
		if(RecScore[2]==-1)
		calScore[2] = checkThrees(diceCounter);
		else
			calScore[2] = RecScore[2];
		
		if(RecScore[3]==-1)
		calScore[3] = checkFours(diceCounter);
		else
			calScore[3] = RecScore[3];
		
		if(RecScore[4]==-1)
		calScore[4] = checkFives(diceCounter);
		else
			calScore[4] = RecScore[4];
		
		if(RecScore[5]==-1)
		calScore[5] = checkSixes(diceCounter);
		else
			calScore[5] = RecScore[5];
		
		if(RecScore[6]==-1)
		calScore[6] = checkThree_Of_A_Kind(dice, diceCounter);
		else
			calScore[6] = RecScore[6];
		
		if(RecScore[7]==-1)
		calScore[7] = checkFour_Of_A_Kind(dice, diceCounter);
		else
			calScore[7] = RecScore[7];
		
		if(RecScore[8]==-1)
		calScore[8] = checkFull_House(diceCounter);
		else
			calScore[8] = RecScore[8];
		
		if(RecScore[9]==-1)
		calScore[9] = checkSmall_Straight(diceCounter);
		else
			calScore[9] = RecScore[9];
		
		if(RecScore[10]==-1)
		calScore[10] = checkLarge_Straight(diceCounter);
		else
			calScore[10] = RecScore[10];
		
		if(RecScore[11]==-1)
		calScore[11] = checkChance(dice);
		else
			calScore[11] = RecScore[11];
		
		if(RecScore[12]==-1)
		calScore[12] = checkYahtzee(diceCounter);
		else
			calScore[12] = RecScore[12];
		
	}
	
	public static int checkAces(int[] diceCounter) {
		return diceCounter[0]*1;
	}
	
	public static int checkTwos(int[] diceCounter) {
		return diceCounter[1]*2;
	}
	
	public static int checkThrees(int[] diceCounter) {
		return diceCounter[2]*3;
	}
	
	public static int checkFours(int[] diceCounter) {
		return diceCounter[3]*4;
	}
	
	public static int checkFives(int[] diceCounter) {
		return diceCounter[4]*5;
	}
	
	public static int checkSixes(int[] diceCounter) {
		return diceCounter[5]*6;
	}

	public static int checkThree_Of_A_Kind(int[] dice, int[] diceCounter) {
		boolean checkValue = false;
		int sum = 0;
		
		for(int i = 0 ; i<6 ; i++) {
			if(diceCounter[i]>=3) {
				checkValue = true;
				break;
			}	
		}
		
		if(checkValue) {
			for(int i = 0 ; i<5 ; i++) {
				sum += dice[i];
			}
			return sum;
		}
		else
			return 0;
		
	}
	
	public static int checkFour_Of_A_Kind(int[] dice, int[] diceCounter) {
		boolean checkValue = false;
		int sum = 0;
		
		for(int i = 0 ; i<6 ; i++) {
			if(diceCounter[i]>=4) {
				checkValue = true;
				break;
			}	
		}
		
		if(checkValue) {
			for(int i = 0 ; i<5 ; i++) {
				sum += dice[i];
			}
			return sum;
		}
		else
			return 0;
		
	}
	
	public static int checkFull_House(int[] diceCounter) {
		boolean checkValue1 = false;
		boolean checkValue2 = false;
		
		for(int i = 0 ; i<6 ; i++) {
			if(diceCounter[i]==3) {
				checkValue1 = true;
			}
			if(diceCounter[i]==2) {
				checkValue2 = true;
			}
		}
		
		if(checkValue1 && checkValue2) {
			return 25;
		}
		else
			return 0;
		
	}
	
	public static int checkSmall_Straight(int[] diceCounter) {
		boolean checkValue[] = new boolean[6];
		
		for(int i = 0 ; i<6 ; i++) {
			if(diceCounter[i]>0)
				checkValue[i]=true;
			else
				checkValue[i]=false;
		}
		
		if((checkValue[0]&&checkValue[1]&&checkValue[2]&&checkValue[3]) ||
		   (checkValue[1]&&checkValue[2]&&checkValue[3]&&checkValue[4]) ||
		   (checkValue[2]&&checkValue[3]&&checkValue[4]&&checkValue[5]) )
		return 30;
		else
			return 0;
	}
	
	public static int checkLarge_Straight(int[] diceCounter) {
		boolean checkValue[] = new boolean[6];
		
		for(int i = 0 ; i<6 ; i++) {
			if(diceCounter[i]>0)
				checkValue[i]=true;
			else
				checkValue[i]=false;
		}
		
		if((checkValue[0]&&checkValue[1]&&checkValue[2]&&checkValue[3]&&checkValue[4]) ||
		   (checkValue[1]&&checkValue[2]&&checkValue[3]&&checkValue[4]&&checkValue[5]) )
		return 40;
		else
			return 0;
	}
	
	public static int checkChance(int[] dice) {
		int sum = 0;
		for(int i = 0 ; i<5 ; i++) {
			sum += dice[i];
		}
		return sum;
	}
	
	public static int checkYahtzee(int[] diceCounter) {
		boolean checkValue = false;
		
		for(int i = 0 ; i<6 ; i++) {
			if(diceCounter[i]==5) {
				checkValue = true;
				break;
			}	
		}
		
		if(checkValue) 
			return 50;
		else
			return 0;
		
	}
	
	
		
}
