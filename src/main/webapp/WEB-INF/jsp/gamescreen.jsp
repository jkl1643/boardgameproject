<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게임화면</title>

<SCRIPT LANGUAGE = "JavaScript">
		var rollCounter = 0;
		
		var calScore = new Array();
		for(var i = 0 ; i < 14 ; i++)
			calScore[i] = 0;
		
		var RecScore = new Array();
		for(var i = 0 ; i < 14 ; i++)
			RecScore[i] = -1;
		
		var dice = new Array();
		
		var diceKeepNum = new Array();
		for(var i = 0 ; i<5 ; i++)
			diceKeepNum[i] = 0;
			
		var diceCounter = new Array();
		
		var roundCounter = 13;
		
		function rollDiceButton(){
			if(rollCounter<3){
			rollDice(dice, diceKeepNum);
			rollCounter++;
			buttonDisabled(RecScore);
			countDice(dice, diceCounter);
			checkRoutine(calScore, RecScore, dice, diceCounter);
			}
		}
		
		
		function keepDice1Button(){
			var diceImg = document.getElementById("diceimg1");
			if(rollCounter>0 && rollCounter<3) {
				if(diceKeepNum[0] == 0) {
					diceKeepNum[0] = 1;
					switch(dice[0]){
						case 1:
							diceImg.src = "dice1_red.png";
							break;
						case 2:
							diceImg.src = "dice2_red.png";
							break;
						case 3:
							diceImg.src = "dice3_red.png";
							break;
						case 4:
							diceImg.src = "dice4_red.png";
							break;
						case 5:
							diceImg.src = "dice5_red.png";
							break;
						case 6:
							diceImg.src = "dice6_red.png";
							break;
					}
				}
				else if(diceKeepNum[0] == 1) {
					diceKeepNum[0] = 0;
					switch(dice[0]){
						case 1:
							diceImg.src = "dice1.png";
							break;
						case 2:
							diceImg.src = "dice2.png";
							break;
						case 3:
							diceImg.src = "dice3.png";
							break;
						case 4:
							diceImg.src = "dice4.png";
							break;
						case 5:
							diceImg.src = "dice5.png";
							break;
						case 6:
							diceImg.src = "dice6.png";
							break;
					}
				}
			}
		}
		
		function keepDice2Button(){
		var diceImg = document.getElementById("diceimg2");
			if(rollCounter>0 && rollCounter<3) {
				if(diceKeepNum[1] == 0) {
					diceKeepNum[1] = 1;
					switch(dice[1]){
						case 1:
							diceImg.src = "dice1_red.png";
							break;
						case 2:
							diceImg.src = "dice2_red.png";
							break;
						case 3:
							diceImg.src = "dice3_red.png";
							break;
						case 4:
							diceImg.src = "dice4_red.png";
							break;
						case 5:
							diceImg.src = "dice5_red.png";
							break;
						case 6:
							diceImg.src = "dice6_red.png";
							break;
					}
				}
				else if(diceKeepNum[1] == 1) {
					diceKeepNum[1] = 0;
					switch(dice[1]){
						case 1:
							diceImg.src = "dice1.png";
							break;
						case 2:
							diceImg.src = "dice2.png";
							break;
						case 3:
							diceImg.src = "dice3.png";
							break;
						case 4:
							diceImg.src = "dice4.png";
							break;
						case 5:
							diceImg.src = "dice5.png";
							break;
						case 6:
							diceImg.src = "dice6.png";
							break;
					}
				}
			}
		}
		
		function keepDice3Button(){
		var diceImg = document.getElementById("diceimg3");
			if(rollCounter>0 && rollCounter<3) {
				if(diceKeepNum[2] == 0) {
					diceKeepNum[2] = 1;
					switch(dice[2]){
						case 1:
							diceImg.src = "dice1_red.png";
							break;
						case 2:
							diceImg.src = "dice2_red.png";
							break;
						case 3:
							diceImg.src = "dice3_red.png";
							break;
						case 4:
							diceImg.src = "dice4_red.png";
							break;
						case 5:
							diceImg.src = "dice5_red.png";
							break;
						case 6:
							diceImg.src = "dice6_red.png";
							break;
					}
				}
				else if(diceKeepNum[2] == 1) {
					diceKeepNum[2] = 0;
					switch(dice[2]){
						case 1:
							diceImg.src = "dice1.png";
							break;
						case 2:
							diceImg.src = "dice2.png";
							break;
						case 3:
							diceImg.src = "dice3.png";
							break;
						case 4:
							diceImg.src = "dice4.png";
							break;
						case 5:
							diceImg.src = "dice5.png";
							break;
						case 6:
							diceImg.src = "dice6.png";
							break;
					}
				}
			}
			
		}
		
		function keepDice4Button(){
		var diceImg = document.getElementById("diceimg4");
			if(rollCounter>0 && rollCounter<3) {
				if(diceKeepNum[3] == 0) {
					diceKeepNum[3] = 1;
					switch(dice[3]){
						case 1:
							diceImg.src = "dice1_red.png";
							break;
						case 2:
							diceImg.src = "dice2_red.png";
							break;
						case 3:
							diceImg.src = "dice3_red.png";
							break;
						case 4:
							diceImg.src = "dice4_red.png";
							break;
						case 5:
							diceImg.src = "dice5_red.png";
							break;
						case 6:
							diceImg.src = "dice6_red.png";
							break;
					}
				}
				else if(diceKeepNum[3] == 1) {
					diceKeepNum[3] = 0;
					switch(dice[3]){
						case 1:
							diceImg.src = "dice1.png";
							break;
						case 2:
							diceImg.src = "dice2.png";
							break;
						case 3:
							diceImg.src = "dice3.png";
							break;
						case 4:
							diceImg.src = "dice4.png";
							break;
						case 5:
							diceImg.src = "dice5.png";
							break;
						case 6:
							diceImg.src = "dice6.png";
							break;
					}
				}
			}
			
		}
		
		function keepDice5Button(){
		var diceImg = document.getElementById("diceimg5");
			if(rollCounter>0 && rollCounter<3) {
				if(diceKeepNum[4] == 0) {
					diceKeepNum[4] = 1;
					switch(dice[4]){
						case 1:
							diceImg.src = "dice1_red.png";
							break;
						case 2:
							diceImg.src = "dice2_red.png";
							break;
						case 3:
							diceImg.src = "dice3_red.png";
							break;
						case 4:
							diceImg.src = "dice4_red.png";
							break;
						case 5:
							diceImg.src = "dice5_red.png";
							break;
						case 6:
							diceImg.src = "dice6_red.png";
							break;
					}
				}
				else if(diceKeepNum[4] == 1) {
					diceKeepNum[4] = 0;
					switch(dice[4]){
						case 1:
							diceImg.src = "dice1.png";
							break;
						case 2:
							diceImg.src = "dice2.png";
							break;
						case 3:
							diceImg.src = "dice3.png";
							break;
						case 4:
							diceImg.src = "dice4.png";
							break;
						case 5:
							diceImg.src = "dice5.png";
							break;
						case 6:
							diceImg.src = "dice6.png";
							break;
					}
				}
			}
			
		}
		
		function AcesButton(){
			if(rollCounter>0 && RecScore[0]==-1) {
				RecScore[0] = calScore[0];
				calScore[13] += calScore[0];
				document.getElementById('text1').innerHTML=String(calScore[13])+"/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					document.getElementById('text1').innerHTML=String(RecScore[13]);
					
				}
				
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				
				rollCounter = 0;
				buttonDisabled(RecScore);
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}

			}
			
				
		}
		
		function TwosButton(){
			if(rollCounter>0 && RecScore[1]==-1) {
			RecScore[1] = calScore[1];
			calScore[13] += calScore[1];
			document.getElementById('text1').innerHTML=String(calScore[13])+"/63";
			if(calScore[13]>=63){
				RecScore[13] = 35;
				document.getElementById('text1').innerHTML=String(RecScore[13]);
				}
			document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
			rollCounter = 0;
			buttonDisabled(RecScore);
			for(var i = 0 ; i < 5 ; i++)
				diceKeepNum[i]=0;
			roundCounter--;
			
			if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
			
			}
			
		}
	
	
		function ThreesButton(){ 
				if(rollCounter>0 && RecScore[2]==-1) {
				RecScore[2] = calScore[2];
				calScore[13] += calScore[2];
				document.getElementById('text1').innerHTML=String(calScore[13])+"/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					document.getElementById('text1').innerHTML=String(RecScore[13]);
				}
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0; 
				buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
				
				}
				
			}
	
	
		function FoursButton(){ 
				if(rollCounter>0 && RecScore[3]==-1) {
			
				RecScore[3] = calScore[3];
				calScore[13] += calScore[3];
				document.getElementById('text1').innerHTML=String(calScore[13])+"/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					document.getElementById('text1').innerHTML=String(RecScore[13]);
				}
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0; 
				buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
				
				}
				
			}
	
	
	
		function FivesButton(){ 
				if(rollCounter>0 && RecScore[4]==-1) {
			
				RecScore[4] = calScore[4];
				calScore[13] += calScore[4];
				document.getElementById('text1').innerHTML=String(calScore[13])+"/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					document.getElementById('text1').innerHTML=String(RecScore[13]);
				}
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0; 
				buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
				
				}
			
			}
		
		
		function SixesButton(){ 
				if(rollCounter>0 && RecScore[5]==-1) {
			
				RecScore[5] = calScore[5];
				calScore[13] += calScore[5];
				document.getElementById('text1').innerHTML=String(calScore[13])+"/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					document.getElementById('text1').innerHTML=String(RecScore[13]);
				}
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0; 
				buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
				
				}
				
			}
			
		
		function Three_Of_A_KindButton(){
				if(rollCounter>0 && RecScore[6]==-1) {
				RecScore[6] = calScore[6];
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0; 
				buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
				
				}
			
		}	
			
			
		function Four_Of_A_KindButton(){
				if(rollCounter>0 && RecScore[7]==-1) {
				RecScore[7] = calScore[7];
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0; 
				buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
				
				}
			
			}
			
			
		function Full_HouseButton(){
				if(rollCounter>0 && RecScore[8]==-1) {
				RecScore[8] = calScore[8];
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				 buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
				
				}
				
			}
			
			
		function Small_StraightButton(){
				if(rollCounter>0 && RecScore[9]==-1) {
				RecScore[9] = calScore[9];
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0; 
				buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
			
				}
			
			}
			
		function Large_StraightButton(){
				if(rollCounter>0 && RecScore[10]==-1) {
				RecScore[10] = calScore[10];
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				 buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
			
				}
			
			}
			
			
		function ChanceButton(){
				if(rollCounter>0 && RecScore[11]==-1) {
				RecScore[11] = calScore[11];
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0; 
				buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
				
				}
			
			}
		
		function YahtzeeButton(){
				if(rollCounter>0 && RecScore[12]==-1) {
				RecScore[12] = calScore[12];
				document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				 buttonDisabled(RecScore) ;
				
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;
				roundCounter--;
				
				if(roundCounter==0){
					document.getElementById("roll").disabled = true;
					alert("게임 종료");
				}
				
				}
			
			}
		
		
		
		
		function rollDice(dice, diceKeepNum) {
			var diceImg = new Array();
			
			diceImg[0] = document.getElementById("diceimg1");
			diceImg[1] = document.getElementById("diceimg2");
			diceImg[2] = document.getElementById("diceimg3");
			diceImg[3] = document.getElementById("diceimg4");
			diceImg[4] = document.getElementById("diceimg5");
		
		
			for(var i = 0 ; i<5 ; i++) {
				if(diceKeepNum[i]==0){
					dice[i] = Math.floor(Math.random() * 6) + 1;
					
					switch(dice[i]){
					case 1:
						diceImg[i].src = "dice1.png";
						break;
					case 2:
						diceImg[i].src = "dice2.png";
						break;
					case 3:
						diceImg[i].src = "dice3.png";
						break;
					case 4:
						diceImg[i].src = "dice4.png";
						break;
					case 5:
						diceImg[i].src = "dice5.png";
						break;
					case 6:
						diceImg[i].src = "dice6.png";
						break;
					}
				}
				
				else{
				switch(dice[i]){
						case 1:
							diceImg.src = "dice1_red.png";
							break;
						case 2:
							diceImg.src = "dice2_red.png";
							break;
						case 3:
							diceImg.src = "dice3_red.png";
							break;
						case 4:
							diceImg.src = "dice4_red.png";
							break;
						case 5:
							diceImg.src = "dice5_red.png";
							break;
						case 6:
							diceImg.src = "dice6_red.png";
							break;
					}
				
				
				}
			}
			
			
		
		
		}
		
		
		function buttonDisabled(RecScore) {
		
		
			if(rollCounter==0){
			document.getElementById("roll").disabled = false;
			document.getElementById("button1").disabled = true;
			document.getElementById("button3").disabled = true;
			document.getElementById("button5").disabled = true;
			document.getElementById("button7").disabled = true;
			document.getElementById("button9").disabled = true;
			document.getElementById("button11").disabled = true;
			document.getElementById("button13").disabled = true;
			document.getElementById("button15").disabled = true;
			document.getElementById("button17").disabled = true;
			document.getElementById("button19").disabled = true;
			document.getElementById("button21").disabled = true;
			document.getElementById("button23").disabled = true;
			document.getElementById("button25").disabled = true;
			}
			else if(rollCounter==1){
			document.getElementById("dice1").disabled = false;
			document.getElementById("dice2").disabled = false;
			document.getElementById("dice3").disabled = false;
			document.getElementById("dice4").disabled = false;
			document.getElementById("dice5").disabled = false;
			if(RecScore[0]==-1)
			document.getElementById("button1").disabled = false;
			if(RecScore[1]==-1)
			document.getElementById("button3").disabled = false;
			if(RecScore[2]==-1)
			document.getElementById("button5").disabled = false;
			if(RecScore[3]==-1)
			document.getElementById("button7").disabled = false;
			if(RecScore[4]==-1)
			document.getElementById("button9").disabled = false;
			if(RecScore[5]==-1)
			document.getElementById("button11").disabled = false;
			if(RecScore[6]==-1)
			document.getElementById("button13").disabled = false;
			if(RecScore[7]==-1)
			document.getElementById("button15").disabled = false;
			if(RecScore[8]==-1)
			document.getElementById("button17").disabled = false;
			if(RecScore[9]==-1)
			document.getElementById("button19").disabled = false;
			if(RecScore[10]==-1)
			document.getElementById("button21").disabled = false;
			if(RecScore[11]==-1)
			document.getElementById("button23").disabled = false;
			if(RecScore[12]==-1)
			document.getElementById("button25").disabled = false;
			}
			else if(rollCounter==3){
			document.getElementById("roll").disabled = true;
			document.getElementById("dice1").disabled = true;
			document.getElementById("dice2").disabled = true;
			document.getElementById("dice3").disabled = true;
			document.getElementById("dice4").disabled = true;
			document.getElementById("dice5").disabled = true;
			}
		
		
		}
		
		
		function countDice(dice, diceCounter) {
			for(var i = 0 ; i<6 ; i++) {
				diceCounter[i] = 0;
			}
	
			for(var i = 0 ; i<5 ; i++) {
				diceCounter[(dice[i]-1)]++;
			}
		}
		
		
		function checkRoutine(calScore, RecScore, dice, diceCounter) {
		if(RecScore[0]==-1)
		calScore[0] = checkAces(diceCounter);
		else
			calScore[0] = RecScore[0];
		 document.getElementById("button1").value=String(calScore[0]);
		
		if(RecScore[1]==-1)
		calScore[1] = checkTwos(diceCounter);
		else
			calScore[1] = RecScore[1];
		document.getElementById("button3").value=String(calScore[1]);
		
		if(RecScore[2]==-1)
		calScore[2] = checkThrees(diceCounter);
		else
			calScore[2] = RecScore[2];
		document.getElementById("button5").value=String(calScore[2]);
		
		if(RecScore[3]==-1)
		calScore[3] = checkFours(diceCounter);
		else
			calScore[3] = RecScore[3];
		document.getElementById("button7").value=String(calScore[3]);
		
		if(RecScore[4]==-1)
		calScore[4] = checkFives(diceCounter);
		else
			calScore[4] = RecScore[4];
		document.getElementById("button9").value=String(calScore[4]);
		
		if(RecScore[5]==-1)
		calScore[5] = checkSixes(diceCounter);
		else
			calScore[5] = RecScore[5];
		document.getElementById("button11").value=String(calScore[5]);
		
		if(RecScore[6]==-1)
		calScore[6] = checkThree_Of_A_Kind(dice, diceCounter);
		else
			calScore[6] = RecScore[6];
		document.getElementById("button13").value=String(calScore[6]);
		
		if(RecScore[7]==-1)
		calScore[7] = checkFour_Of_A_Kind(dice, diceCounter);
		else
			calScore[7] = RecScore[7];
		document.getElementById("button15").value=String(calScore[7]);
		
		if(RecScore[8]==-1)
		calScore[8] = checkFull_House(diceCounter);
		else
			calScore[8] = RecScore[8];
		document.getElementById("button17").value=String(calScore[8]);
		
		if(RecScore[9]==-1)
		calScore[9] = checkSmall_Straight(diceCounter);
		else
			calScore[9] = RecScore[9];
		document.getElementById("button19").value=String(calScore[9]);
		
		if(RecScore[10]==-1)
		calScore[10] = checkLarge_Straight(diceCounter);
		else
			calScore[10] = RecScore[10];
		document.getElementById("button21").value=String(calScore[10]);
		
		if(RecScore[11]==-1)
		calScore[11] = checkChance(dice);
		else
			calScore[11] = RecScore[11];
		document.getElementById("button23").value=String(calScore[11]);
		
		if(RecScore[12]==-1)
		calScore[12] = checkYahtzee(diceCounter);
		else
			calScore[12] = RecScore[12];
		document.getElementById("button25").value=String(calScore[12]);
	}
		
		function checkAces(diceCounter) {
			return diceCounter[0]*1;
		}
	
		function checkTwos(diceCounter) {
			return diceCounter[1]*2;
		}
		
		function checkThrees(diceCounter) {
			return diceCounter[2]*3;
		}
		
		function checkFours(diceCounter) {
			return diceCounter[3]*4;
		}
		
		function checkFives(diceCounter) {
			return diceCounter[4]*5;
		}
		
		function checkSixes(diceCounter) {
			return diceCounter[5]*6;
		}
	

		function checkThree_Of_A_Kind(dice, diceCounter) {
			var checkValue = false;
			var sum = 0;
			
			for(var i = 0 ; i<6 ; i++) {
				if(diceCounter[i]>=3) {
					checkValue = true;
					break;
				}	
			}
				
			if(checkValue) {
				for(var i = 0 ; i<5 ; i++) {
					sum += dice[i];
				}
				return sum;
			}
			else
				return 0;
		
		}
		
		function checkFour_Of_A_Kind(dice, diceCounter) {
			var checkValue = false;
			var sum = 0;
			
			for(var i = 0 ; i<6 ; i++) {
				if(diceCounter[i]>=4) {
					checkValue = true;
					break;
				}	
			}
			
			if(checkValue) {
				for(var i = 0 ; i<5 ; i++) {
					sum += dice[i];
				}
				return sum;
			}
			else
				return 0;
		
		}
		
		function checkFull_House(diceCounter) {
			var checkValue1 = false;
			var checkValue2 = false;
			
			for(var i = 0 ; i<6 ; i++) {
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
	    
	    function checkSmall_Straight(diceCounter) {
		var checkValue = new Array();
		
		for(var i = 0 ; i<6 ; i++) {
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
	  
	  function checkLarge_Straight(diceCounter) {
		var checkValue = new Array();
		
		for(var i = 0 ; i<6 ; i++) {
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
		
		
	function checkChance( dice) {
		var sum = 0;
		for(var i = 0 ; i<5 ; i++) {
			sum += dice[i];
		}
		return sum;
	}
	
	function checkYahtzee(diceCounter) {
		var checkValue = false;
		
		for(var i = 0 ; i<6 ; i++) {
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
	
	function RecScoreSum(RecScore){
	var sum = 0;
		for(var i = 0 ; i<14 ; i++){
			if(RecScore[i]!=-1)
				sum += RecScore[i];
		}
	return sum;
	}

	</SCRIPT>

<STYLE TYPE="text/css">
<!--
	div#box {width: 1600px; height: 750px; border: 5px solid black; position: relative; left: 100px; top: 50px}
	button#dice1 {width: 70px; height: 70px; position: relative; left: 100px; top: 70px}
	img#diceimg1 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
	button#dice2 {width: 70px; height: 70px; position: relative; left: 200px; top: -5px}
	img#diceimg2 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
	button#dice3 {width: 70px; height: 70px; position: relative; left: 300px; top: -80px}
	img#diceimg3 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
	button#dice4 {width: 70px; height: 70px; position: relative; left: 400px; top: -155px}
	img#diceimg4 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
	button#dice5 {width: 70px; height: 70px; position: relative; left: 500px; top: -230px}
	img#diceimg5 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
	button#roll {width: 70px; height: 40px; position: relative; left: 300px; top: -200px}
	.divTable{ display: table; width: 700px; height: 750px; position: relative; left: 700px; top: -420px} 
	.divTableRow { display: table-row; }
	.divTableHeading { background-color: #EEE; display: table-header-group; } 
	.divTableCell, .divTableHead { border: 1px solid #999999; display: table-cell; padding: 3px 10px; } 
	.divTableHeading { background-color: #EEE; display: table-header-group; font-weight: bold; } 
	.divTableFoot { background-color: #EEE; display: table-footer-group; font-weight: bold; } 
	.divTableBody {text-align: center; display: table-row-group; }
	button#exitbutton {width: 100px; height: 50px; background-color: black; color: white; position: relative; right: -1610px; top: 60px}
-->
</STYLE>
</head>
<body>
	<div id="box">
		<div><button type="button" id="dice1" onClick="keepDice1Button()" ><img src="dice1.png" id="diceimg1" disabled = true ></button></div>
		<div><button type="button" id="dice2" onClick="keepDice2Button()" ><img src="dice1.png" id="diceimg2" disabled = true></button></div>
		<div><button type="button" id="dice3" onClick="keepDice3Button()" ><img src="dice1.png" id="diceimg3" disabled = true></button></div>
		<div><button type="button" id="dice4" onClick="keepDice4Button()" ><img src="dice1.png" id="diceimg4" disabled = true></button></div>
		<div><button type="button" id="dice5" onClick="keepDice5Button()" ><img src="dice1.png" id="diceimg5" disabled = true></button></div>
		<div><button id="roll" onClick="rollDiceButton()">굴리기</button></div>
		<div class="divTable"> 
			<div class="divTableBody"> 
				<div class="divTableRow"> 
					<div class="divTableCell">에이스</div> 
					<div class="divTableCell"><input type="button" value="0" id="button1" onClick="AcesButton()"  disabled = true></div>
					<div class="divTableCell"><input type="button" value="0" id="button2" ></div> 
				</div> 
				<div class="divTableRow"> 
					<div class="divTableCell">투</div> 
					<div class="divTableCell"><input type="button" value="0" id="button3" onClick="TwosButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button4"></div> 
				</div> 
				<div class="divTableRow"> 
					<div class="divTableCell">쓰리</div> 
					<div class="divTableCell"><input type="button" value="0" id="button5" onClick="ThreesButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button6"></div> 
				</div> 
				<div class="divTableRow"> 
					<div class="divTableCell">포</div> 
					<div class="divTableCell"><input type="button" value="0" id="button7" onClick="FoursButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button8"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">파이브</div> 
					<div class="divTableCell"><input type="button" value="0" id="button9" onClick="FivesButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button10"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">식스</div> 
					<div class="divTableCell"><input type="button" value="0" id="button11" onClick="SixesButton()"disabled = true ></div> 
					<div class="divTableCell"><input type="button" value="0" id="button12"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">보너스</div> 
					<div class="divTableCell"><b id="text1">0/63</b></div> 
					<div class="divTableCell"><b id="text2">0/63</b></div>
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">쓰리-오브-카인드</div> 
					<div class="divTableCell"><input type="button" value="0" id="button13" onClick="Three_Of_A_KindButton()"disabled = true ></div> 
					<div class="divTableCell"><input type="button" value="0" id="button14"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">포-오브-카인드</div> 
					<div class="divTableCell"><input type="button" value="0" id="button15" onClick="Four_Of_A_KindButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button16"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">풀 하우스</div> 
					<div class="divTableCell"><input type="button" value="0" id="button17" onClick="Full_HouseButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button18"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">스몰 스트레이트</div> 
					<div class="divTableCell"><input type="button" value="0" id="button19" onClick="Small_StraightButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button20"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">라지 스트레이트</div> 
					<div class="divTableCell"><input type="button" value="0" id="button21" onClick="Large_StraightButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button22"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">찬스</div> 
					<div class="divTableCell"><input type="button" value="0" id="button23" onClick="ChanceButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button24"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">야찌</div> 
					<div class="divTableCell"><input type="button" value="0" id="button25" onClick="YahtzeeButton()" disabled = true></div> 
					<div class="divTableCell"><input type="button" value="0" id="button26"></div> 
				</div>
				<div class="divTableRow"> 
					<div class="divTableCell">최종점수</div> 
					<div class="divTableCell"><b id="text3">0</b></div>
					<div class="divTableCell"><b id="text4">0</b></div>
				</div>
			</div>
		</div>
	</div>
	<button id="exitbutton">나가기</button>
</body>
</html>