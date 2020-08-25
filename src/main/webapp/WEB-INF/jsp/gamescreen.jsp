
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>게임화면</title>
	<%
		session.setAttribute("roomid", request.getAttribute("id"));
	%>
	<SCRIPT LANGUAGE = "JavaScript" type = "text/javascript">



		var endTrigger = false;

		var webSocket;
		var output;

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

		var userId;
		var roomId = "${id}";

		var player = 0;



		function init() {
			output = document.getElementById("output");

			<%@ page import="com.example.Member" %>
			<%


                Member mem = (Member) session.getAttribute("mem");
                Long userId= null ;
                if(mem!=null)
                    userId = mem.getId();

            %>

			userId =<%=userId%>;

			testWebSocket();

		}

		function testWebSocket()
		{
			webSocket = new WebSocket("ws://" + location.host + "/game");
			webSocket.onopen = function(evt) { onOpen(evt) ;};
			webSocket.onclose = function(evt) { onClose(evt) };
			webSocket.onmessage = function(evt) { onMessage(evt) };
			webSocket.onerror = function(evt) { onError(evt) };
		}

		function onOpen(evt)
		{
			webSocket.send(JSON.stringify({cmd : "start", selecto : userId, roomId : roomId}));
			writeToScreen("연결완료");

		}

		function onClose(evt)
		{
			writeToScreen("연결해제");
		}



		function onMessage(evt)
		{

			var cmd = JSON.parse(evt.data);


			switch(cmd.cmd){
				case "start":
					player = cmd.player;

					if(player == 1){
						document.getElementById("roll").disabled = false;
						writeToScreen("니 차례");
					}
					break;

				case "roll":
					var diceImg = new Array();
					var dice = new Array();
					var number=0;


					diceImg[0] = document.getElementById("diceimg1");
					diceImg[1] = document.getElementById("diceimg2");
					diceImg[2] = document.getElementById("diceimg3");
					diceImg[3] = document.getElementById("diceimg4");
					diceImg[4] = document.getElementById("diceimg5");

					dice[0]=cmd.dice1;
					dice[1]=cmd.dice2;
					dice[2]=cmd.dice3;
					dice[3]=cmd.dice4;
					dice[4]=cmd.dice5;



					for(var i = 0 ; i<5 ; i++) {

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



					if(player == 1) {





						document.getElementById("button2").value = String(cmd.aces);
						document.getElementById("button4").value = String(cmd.twos);
						document.getElementById("button6").value = String(cmd.threes);
						document.getElementById("button8").value = String(cmd.fours);
						document.getElementById("button10").value = String(cmd.fives);
						document.getElementById("button12").value = String(cmd.sixes);
						document.getElementById("button14").value = String(cmd.three_Of_A_Kind);
						document.getElementById("button16").value = String(cmd.four_Of_A_Kind);
						document.getElementById("button18").value = String(cmd.full_House);
						document.getElementById("button20").value = String(cmd.small_Straight);
						document.getElementById("button22").value = String(cmd.large_Straight);
						document.getElementById("button24").value = String(cmd.chance);
						document.getElementById("button26").value = String(cmd.yahtzee);
					}
					else if(player == 2) {


						document.getElementById("button1").value = String(cmd.aces);
						document.getElementById("button3").value = String(cmd.twos);
						document.getElementById("button5").value = String(cmd.threes);
						document.getElementById("button7").value = String(cmd.fours);
						document.getElementById("button9").value = String(cmd.fives);
						document.getElementById("button11").value = String(cmd.sixes);
						document.getElementById("button13").value = String(cmd.three_Of_A_Kind);
						document.getElementById("button15").value = String(cmd.four_Of_A_Kind);
						document.getElementById("button17").value = String(cmd.full_House);
						document.getElementById("button19").value = String(cmd.small_Straight);
						document.getElementById("button21").value = String(cmd.large_Straight);
						document.getElementById("button23").value = String(cmd.chance);
						document.getElementById("button25").value = String(cmd.yahtzee);
					}
					break;
				case "record":
					var sumBonus = cmd.aces+
							cmd.twos+
							cmd.threes+
							cmd.fours+
							cmd.fives+
							cmd.sixes;

					var sum =
							sumBonus+
							cmd.three_Of_A_Kind+
							cmd.four_Of_A_Kind+
							cmd.full_House+
							cmd.small_Straight+
							cmd.large_Straight+
							cmd.chance+
							cmd.yahtzee+
							cmd.bonus;

					if(player == 1) {
						document.getElementById("button2").value = String(cmd.aces);
						if(cmd.selecto==0)
							document.getElementById("button2").style.color = '#d23030';

						document.getElementById("button4").value = String(cmd.twos);
						if(cmd.selecto==1)
							document.getElementById("button4").style.color = '#d23030';

						document.getElementById("button6").value = String(cmd.threes);
						if(cmd.selecto==2)
							document.getElementById("button6").style.color = '#d23030';

						document.getElementById("button8").value = String(cmd.fours);
						if(cmd.selecto==3)
							document.getElementById("button8").style.color = '#d23030';

						document.getElementById("button10").value = String(cmd.fives);
						if(cmd.selecto==4)
							document.getElementById("button10").style.color = '#d23030';

						document.getElementById("button12").value = String(cmd.sixes);
						if(cmd.selecto==5)
							document.getElementById("button12").style.color = '#d23030';

						document.getElementById("button14").value = String(cmd.three_Of_A_Kind);
						if(cmd.selecto==6)
							document.getElementById("button14").style.color = '#d23030';

						document.getElementById("button16").value = String(cmd.four_Of_A_Kind);
						if(cmd.selecto==7)
							document.getElementById("button16").style.color = '#d23030';

						document.getElementById("button18").value = String(cmd.full_House);
						if(cmd.selecto==8)
							document.getElementById("button18").style.color = '#d23030';

						document.getElementById("button20").value = String(cmd.small_Straight);
						if(cmd.selecto==9)
							document.getElementById("button20").style.color = '#d23030';

						document.getElementById("button22").value = String(cmd.large_Straight);
						if(cmd.selecto==10)
							document.getElementById("button22").style.color = '#d23030';

						document.getElementById("button24").value = String(cmd.chance);
						if(cmd.selecto==11)
							document.getElementById("button24").style.color = '#d23030';

						document.getElementById("button26").value = String(cmd.yahtzee);
						if(cmd.selecto==12)
							document.getElementById("button26").style.color = '#d23030';

						if(sumBonus>=63)
							document.getElementById("text2").innerHTML = String(cmd.bonus);
						else
							document.getElementById("text2").innerHTML = String(sumBonus)+"/63";

						document.getElementById("text4").innerHTML = String(sum);

					}
					else if(player == 2) {
						document.getElementById("button1").value = String(cmd.aces);
						if(cmd.selecto==0)
							document.getElementById("button1").style.color = '#d23030';

						document.getElementById("button3").value = String(cmd.twos);
						if(cmd.selecto==1)
							document.getElementById("button3").style.color = '#d23030';

						document.getElementById("button5").value = String(cmd.threes);
						if(cmd.selecto==2)
							document.getElementById("button5").style.color = '#d23030';

						document.getElementById("button7").value = String(cmd.fours);
						if(cmd.selecto==3)
							document.getElementById("button7").style.color = '#d23030';

						document.getElementById("button9").value = String(cmd.fives);
						if(cmd.selecto==4)
							document.getElementById("button9").style.color = '#d23030';

						document.getElementById("button11").value = String(cmd.sixes);
						if(cmd.selecto==5)
							document.getElementById("button11").style.color = '#d23030';

						document.getElementById("button13").value = String(cmd.three_Of_A_Kind);
						if(cmd.selecto==6)
							document.getElementById("button13").style.color = '#d23030';

						document.getElementById("button15").value = String(cmd.four_Of_A_Kind);
						if(cmd.selecto==7)
							document.getElementById("button15").style.color = '#d23030';

						document.getElementById("button17").value = String(cmd.full_House);
						if(cmd.selecto==8)
							document.getElementById("button17").style.color = '#d23030';

						document.getElementById("button19").value = String(cmd.small_Straight);
						if(cmd.selecto==9)
							document.getElementById("button19").style.color = '#d23030';

						document.getElementById("button21").value = String(cmd.large_Straight);
						if(cmd.selecto==10)
							document.getElementById("button21").style.color = '#d23030';

						document.getElementById("button23").value = String(cmd.chance);
						if(cmd.selecto==11)
							document.getElementById("button23").style.color = '#d23030';

						document.getElementById("button25").value = String(cmd.yahtzee);
						if(cmd.selecto==12)
							document.getElementById("button25").style.color = '#d23030';

						if(sumBonus>=63)
							document.getElementById("text1").innerHTML = String(cmd.bonus);
						else
							document.getElementById("text1").innerHTML = String(sumBonus)+"/63";
						document.getElementById("text3").innerHTML = String(sum);
					}
					document.getElementById("roll").disabled = false;


					writeToScreen("게임 시작");
					document.getElementById("roll").style.WebkitAnimation = "col 1s infinite";
					document.getElementById("roll").addEventListener('click',function(){
						document.getElementById("roll").style.WebkitAnimation = 'none';
					})
					writeToScreen("니 차례");
					break;




				case "end":

					document.getElementById("roll").disabled = true;
					var sum = 0 ;
					for(var i = 0 ; i < 13 ; i++)
						sum+=RecScore[i];
					if(RecScore[13]!=-1)
						sum+=RecScore[13]
					webSocket.send(JSON.stringify({cmd : "end", player : player, selecto : sum, roomId : roomId }));


					break;


				case "alert":
					document.getElementById("roll").disabled = true;
					writeToScreen("이긴놈은 player"+String(cmd.selecto));
					endTrigger=true;
					break;

				case "close":
					webSocket.close();
					break;

			}


		}

		function onError(evt)
		{
			writeToScreen('<span style="color: red;">에러:</span> ' + evt.data);
		}



		function writeToScreen(message)
		{
			var pre = document.getElementById('output');
			pre.innerHTML = message;
		}


		window.addEventListener("load", init, false);



		function rollDiceButton(){
			if(rollCounter<3){
				rollDice(dice, diceKeepNum);
				rollCounter++;
				buttonDisabled(RecScore);
				countDice(dice, diceCounter);
				checkRoutine(calScore, RecScore, dice, diceCounter);
				webSocket.send(JSON.stringify({cmd : "roll", player : player, dice1:dice[0], dice2:dice[1], dice3:dice[2], dice4:dice[3], dice5:dice[4],
					aces:calScore[0], twos:calScore[1], threes:calScore[2], fours:calScore[3], fives:calScore[4], sixes:calScore[5],
					three_Of_A_Kind:calScore[6], four_Of_A_Kind:calScore[7], full_House:calScore[8], small_Straight:calScore[9], large_Straight:calScore[10],
					chance:calScore[11], yahtzee:calScore[12], roomId : roomId}));

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
			document.getElementById("roll").disabled = true;
			if(player==1)
				var id = document.getElementById("button1");
			else if(player==2)
				var id = document.getElementById("button2");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[0]==-1) {
				RecScore[0] = calScore[0];
				calScore[13] += calScore[0];
				if(player==1) {
					document.getElementById('text1').innerHTML = String(calScore[13]) + "/63";
				}
				else if(player==2)
					document.getElementById('text2').innerHTML = String(calScore[13]) + "/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					if(player==1)
						document.getElementById('text1').innerHTML=String(RecScore[13]);
					else if(player==2)
						document.getElementById('text2').innerHTML=String(RecScore[13]);

				}

				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));

				rollCounter = 0;
				buttonDisabled(RecScore);

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}

			webSocket.send(JSON.stringify({cmd : "record", player : player, selecto : 0 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}

		function TwosButton(){
			document.getElementById("roll").disabled = true;
			if(player==1)
				var id = document.getElementById("button3");
			else if(player==2)
				var id = document.getElementById("button4");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[1]==-1) {
				RecScore[1] = calScore[1];
				calScore[13] += calScore[1];
				if(player==1) {
					document.getElementById('text1').innerHTML = String(calScore[13]) + "/63";
				}
				else if(player==2)
					document.getElementById('text2').innerHTML = String(calScore[13]) + "/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					if(player==1)
						document.getElementById('text1').innerHTML=String(RecScore[13]);
					else if(player==2)
						document.getElementById('text2').innerHTML=String(RecScore[13]);
				}
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore);
				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}

			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 1 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}


		function ThreesButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button5");
			else if(player==2)
				var id = document.getElementById("button6");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[2]==-1) {
				RecScore[2] = calScore[2];
				calScore[13] += calScore[2];
				if(player==1) {
					document.getElementById('text1').innerHTML = String(calScore[13]) + "/63";
				}
				else if(player==2)
					document.getElementById('text2').innerHTML = String(calScore[13]) + "/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					if(player==1)
						document.getElementById('text1').innerHTML=String(RecScore[13]);
					else if(player==2)
						document.getElementById('text2').innerHTML=String(RecScore[13]);;
				}
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 2 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}


		function FoursButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button7");
			else if(player==2)
				var id = document.getElementById("button8");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[3]==-1) {

				RecScore[3] = calScore[3];
				calScore[13] += calScore[3];
				if(player==1) {
					document.getElementById('text1').innerHTML = String(calScore[13]) + "/63";
				}
				else if(player==2)
					document.getElementById('text2').innerHTML = String(calScore[13]) + "/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					if(player==1)
						document.getElementById('text1').innerHTML=String(RecScore[13]);
					else if(player==2)
						document.getElementById('text2').innerHTML=String(RecScore[13]);
				}
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 3 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}



		function FivesButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button9");
			else if(player==2)
				var id = document.getElementById("button10");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[4]==-1) {

				RecScore[4] = calScore[4];
				calScore[13] += calScore[4];
				if(player==1) {
					document.getElementById('text1').innerHTML = String(calScore[13]) + "/63";
				}
				else if(player==2)
					document.getElementById('text2').innerHTML = String(calScore[13]) + "/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					if(player==1)
						document.getElementById('text1').innerHTML=String(RecScore[13]);
					else if(player==2)
						document.getElementById('text2').innerHTML=String(RecScore[13]);
				}
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 4 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}


		function SixesButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button11");
			else if(player==2)
				var id = document.getElementById("button12");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[5]==-1) {

				RecScore[5] = calScore[5];
				calScore[13] += calScore[5];
				if(player==1) {
					document.getElementById('text1').innerHTML = String(calScore[13]) + "/63";
				}
				else if(player==2)
					document.getElementById('text2').innerHTML = String(calScore[13]) + "/63";
				if(calScore[13]>=63){
					RecScore[13] = 35;
					if(player==1)
						document.getElementById('text1').innerHTML=String(RecScore[13]);
					else if(player==2)
						document.getElementById('text2').innerHTML=String(RecScore[13]);
				}
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 5 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}


		function Three_Of_A_KindButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button13");
			else if(player==2)
				var id = document.getElementById("button14");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[6]==-1) {
				RecScore[6] = calScore[6];
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 6 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}


		function Four_Of_A_KindButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button15");
			else if(player==2)
				var id = document.getElementById("button16");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[7]==-1) {
				RecScore[7] = calScore[7];
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 7 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}


		function Full_HouseButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button17");
			else if(player==2)
				var id = document.getElementById("button18");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[8]==-1) {
				RecScore[8] = calScore[8];
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 8 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}


		function Small_StraightButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button19");
			else if(player==2)
				var id = document.getElementById("button20");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[9]==-1) {
				RecScore[9] = calScore[9];
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 9 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}

		function Large_StraightButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button21");
			else if(player==2)
				var id = document.getElementById("button22");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[10]==-1) {
				RecScore[10] = calScore[10];
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 10, aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}


		function ChanceButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button23");
			else if(player==2)
				var id = document.getElementById("button24");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[11]==-1) {

				RecScore[11] = calScore[11];
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 11 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));
		}

		function YahtzeeButton(){
			document.getElementById("roll").disabled = true;

			if(player==1)
				var id = document.getElementById("button25");
			else if(player==2)
				var id = document.getElementById("button26");
			id.style.color = '#d23030';

			if(rollCounter>0 && RecScore[12]==-1) {
				RecScore[12] = calScore[12];
				if(player==1)
					document.getElementById('text3').innerHTML=String(RecScoreSum(RecScore));
				else if(player==2)
					document.getElementById('text4').innerHTML=String(RecScoreSum(RecScore));
				rollCounter = 0;
				buttonDisabled(RecScore) ;

				for(var i = 0 ; i < 5 ; i++)
					diceKeepNum[i]=0;


			}
			webSocket.send(JSON.stringify({cmd : "record", player : player,selecto : 12 , aces:RecScore[0], twos:RecScore[1], threes:RecScore[2], fours:RecScore[3], fives:RecScore[4], sixes:RecScore[5],
				three_Of_A_Kind:RecScore[6], four_Of_A_Kind:RecScore[7], full_House:RecScore[8], small_Straight:RecScore[9], large_Straight:RecScore[10],
				chance:RecScore[11], yahtzee:RecScore[12], bonus:RecScore[13], roomId : roomId}));

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
				document.getElementById("roll").disabled = true;
				if(player==1) {
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
				else if(player==2){
					document.getElementById("button2").disabled = true;
					document.getElementById("button4").disabled = true;
					document.getElementById("button6").disabled = true;
					document.getElementById("button8").disabled = true;
					document.getElementById("button10").disabled = true;
					document.getElementById("button12").disabled = true;
					document.getElementById("button14").disabled = true;
					document.getElementById("button16").disabled = true;
					document.getElementById("button18").disabled = true;
					document.getElementById("button20").disabled = true;
					document.getElementById("button22").disabled = true;
					document.getElementById("button24").disabled = true;
					document.getElementById("button26").disabled = true;
				}
			}
			else if(rollCounter==1){
				document.getElementById("dice1").disabled = false;
				document.getElementById("dice2").disabled = false;
				document.getElementById("dice3").disabled = false;
				document.getElementById("dice4").disabled = false;
				document.getElementById("dice5").disabled = false;
				if(player==1) {
					if (RecScore[0] == -1)
						document.getElementById("button1").disabled = false;
					if (RecScore[1] == -1)
						document.getElementById("button3").disabled = false;
					if (RecScore[2] == -1)
						document.getElementById("button5").disabled = false;
					if (RecScore[3] == -1)
						document.getElementById("button7").disabled = false;
					if (RecScore[4] == -1)
						document.getElementById("button9").disabled = false;
					if (RecScore[5] == -1)
						document.getElementById("button11").disabled = false;
					if (RecScore[6] == -1)
						document.getElementById("button13").disabled = false;
					if (RecScore[7] == -1)
						document.getElementById("button15").disabled = false;
					if (RecScore[8] == -1)
						document.getElementById("button17").disabled = false;
					if (RecScore[9] == -1)
						document.getElementById("button19").disabled = false;
					if (RecScore[10] == -1)
						document.getElementById("button21").disabled = false;
					if (RecScore[11] == -1)
						document.getElementById("button23").disabled = false;
					if (RecScore[12] == -1)
						document.getElementById("button25").disabled = false;
				}
				else if(player==2){
					if (RecScore[0] == -1)
						document.getElementById("button2").disabled = false;
					if (RecScore[1] == -1)
						document.getElementById("button4").disabled = false;
					if (RecScore[2] == -1)
						document.getElementById("button6").disabled = false;
					if (RecScore[3] == -1)
						document.getElementById("button8").disabled = false;
					if (RecScore[4] == -1)
						document.getElementById("button10").disabled = false;
					if (RecScore[5] == -1)
						document.getElementById("button12").disabled = false;
					if (RecScore[6] == -1)
						document.getElementById("button14").disabled = false;
					if (RecScore[7] == -1)
						document.getElementById("button16").disabled = false;
					if (RecScore[8] == -1)
						document.getElementById("button18").disabled = false;
					if (RecScore[9] == -1)
						document.getElementById("button20").disabled = false;
					if (RecScore[10] == -1)
						document.getElementById("button22").disabled = false;
					if (RecScore[11] == -1)
						document.getElementById("button24").disabled = false;
					if (RecScore[12] == -1)
						document.getElementById("button26").disabled = false;
				}
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
			if(player==1) {
				document.getElementById("button1").value = String(calScore[0]);
			}
			else if(player==2){
				document.getElementById("button2").value = String(calScore[0]);
			}

			if(RecScore[1]==-1)
				calScore[1] = checkTwos(diceCounter);
			else
				calScore[1] = RecScore[1];
			if(player==1)
				document.getElementById("button3").value=String(calScore[1]);
			else if(player==2)
				document.getElementById("button4").value=String(calScore[1]);
			if(RecScore[2]==-1)
				calScore[2] = checkThrees(diceCounter);
			else
				calScore[2] = RecScore[2];
			if(player==1)
				document.getElementById("button5").value=String(calScore[2]);
			else if(player==2)
				document.getElementById("button6").value=String(calScore[2]);
			if(RecScore[3]==-1)
				calScore[3] = checkFours(diceCounter);
			else
				calScore[3] = RecScore[3];
			if(player==1)
				document.getElementById("button7").value=String(calScore[3]);
			else if(player==2)
				document.getElementById("button8").value=String(calScore[3]);
			if(RecScore[4]==-1)
				calScore[4] = checkFives(diceCounter);
			else
				calScore[4] = RecScore[4];
			if(player==1)
				document.getElementById("button9").value=String(calScore[4]);
			else if(player==2)
				document.getElementById("button10").value=String(calScore[4]);
			if(RecScore[5]==-1)
				calScore[5] = checkSixes(diceCounter);
			else
				calScore[5] = RecScore[5]
			if(player==1)
				document.getElementById("button11").value=String(calScore[5]);
			else if(player==2)
				document.getElementById("button12").value=String(calScore[5]);
			if(RecScore[6]==-1)
				calScore[6] = checkThree_Of_A_Kind(dice, diceCounter);
			else
				calScore[6] = RecScore[6];
			if(player==1)
				document.getElementById("button13").value=String(calScore[6]);
			else if(player==2)
				document.getElementById("button14").value=String(calScore[6]);
			if(RecScore[7]==-1)
				calScore[7] = checkFour_Of_A_Kind(dice, diceCounter);
			else
				calScore[7] = RecScore[7];
			if(player==1)
				document.getElementById("button15").value=String(calScore[7]);
			else if(player==2)
				document.getElementById("button16").value=String(calScore[7]);
			if(RecScore[8]==-1)
				calScore[8] = checkFull_House(diceCounter);
			else
				calScore[8] = RecScore[8];
			if(player==1)
				document.getElementById("button17").value=String(calScore[8]);
			else if(player==2)
				document.getElementById("button18").value=String(calScore[8]);
			if(RecScore[9]==-1)
				calScore[9] = checkSmall_Straight(diceCounter);
			else
				calScore[9] = RecScore[9];
			if(player==1)
				document.getElementById("button19").value=String(calScore[9]);
			else if(player==2)
				document.getElementById("button20").value=String(calScore[9]);
			if(RecScore[10]==-1)
				calScore[10] = checkLarge_Straight(diceCounter);
			else
				calScore[10] = RecScore[10];
			if(player==1)
				document.getElementById("button21").value=String(calScore[10]);
			else if(player==2)
				document.getElementById("button22").value=String(calScore[10]);
			if(RecScore[11]==-1)
				calScore[11] = checkChance(dice);
			else
				calScore[11] = RecScore[11];
			if(player==1)
				document.getElementById("button23").value=String(calScore[11]);
			else if(player==2)
				document.getElementById("button24").value=String(calScore[11]);
			if(RecScore[12]==-1)
				calScore[12] = checkYahtzee(diceCounter);
			else
				calScore[12] = RecScore[12];
			if(player==1)
				document.getElementById("button25").value=String(calScore[12]);
			else if(player==2)
				document.getElementById("button26").value=String(calScore[12]);
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

		function exitButtons(){
			if(endTrigger==true)
				webSocket.close();
			else
				exitButtonConfrim();
		}

		function exitButtonConfrim(){
			if(confirm("탈주 처리 됩니다")){
				webSocket.send(JSON.stringify({cmd : "run", player: player, roomId : roomId}));
				webSocket.close();
			}
			else
				return;
		}

	</SCRIPT>

	<STYLE TYPE="text/css">
		<!--
		BODY {background-color: #bf8861}
		h2#web {font-size: x-large; text-align: center; color: #253245; position: relative; top: 10px; -webkit-animation: none}
		p#output {position: relative; left: 900px; top: 10px; font-size: large; -webkit-animation:blink 1s ease-in-out infinite alternate;}
		@-webkit-keyframes blink{
			0% {opacity:0;}
			100% {opacity:1;}
		}
		div#box {background-color: white; width: 1600px; height: 750px; border: 5px solid black; position: relative; left: 100px; top: 50px}
		button#dice1 {width: 70px; height: 70px; position: relative; left: 100px; top: 70px}
		img#diceimg1 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
		button#dice2 {width: 70px; height: 70px; position: relative; left: 200px; top: 0px}
		img#diceimg2 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
		button#dice3 {width: 70px; height: 70px; position: relative; left: 300px; top: -70px}
		img#diceimg3 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
		button#dice4 {width: 70px; height: 70px; position: relative; left: 400px; top: -140px}
		img#diceimg4 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
		button#dice5 {width: 70px; height: 70px; position: relative; left: 500px; top: -210px}
		img#diceimg5 {position: absolute; top:0; left: 0; width: 100%; height: 100%}
		button#roll {width: 70px; height: 40px; position: relative; left: 300px; top: -200px; -webkit-animation: none}
		@-webkit-keyframes col {
			0% { color: red; }
			50% { color: blue; }
			100% { color: red; }
		}
		.divTable{ display: table; width: 700px; height: 750px; position: relative; left: 700px; top: -395px}
		.divTableRow { display: table-row; }
		.divTableHeading { background-color: #EEE; display: table-header-group; }
		.divTableCell, .divTableHead { border: 1px solid #999999; display: table-cell; padding: 3px 10px; }
		.divTableHeading { background-color: #EEE; display: table-header-group; font-weight: bold; }
		.divTableFoot { background-color: #EEE; display: table-footer-group; font-weight: bold; }
		.divTableBody {text-align: center; display: table-row-group; }
		input#exitbutton {width: 100px; height: 50px; background-color: black; color: white; position: relative; right: -1610px; top: 60px}
		-->
	</STYLE>
</head>
<body>
<h2 id="web">야추 게임에 오신것을 환영합니다!</h2>
<p id="output"></p>
<div id="box">
	<div><button type="button" id="dice1" onClick="keepDice1Button()" ><img src="dice1.png" id="diceimg1" disabled = true ></button></div>
	<div><button type="button" id="dice2" onClick="keepDice2Button()" ><img src="dice1.png" id="diceimg2" disabled = true></button></div>
	<div><button type="button" id="dice3" onClick="keepDice3Button()" ><img src="dice1.png" id="diceimg3" disabled = true></button></div>
	<div><button type="button" id="dice4" onClick="keepDice4Button()" ><img src="dice1.png" id="diceimg4" disabled = true></button></div>
	<div><button type="button" id="dice5" onClick="keepDice5Button()" ><img src="dice1.png" id="diceimg5" disabled = true></button></div>
	<div><button id="roll" onClick="rollDiceButton()" disabled = true>굴리기</button></div>
	<div class="divTable">
		<div class="divTableBody">
			<div class="divTableRow">
				<div class="divTableCell">에이스</div>
				<div class="divTableCell"><input type="button" value="0" id="button1" onClick="AcesButton()"  disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button2" onClick="AcesButton()"  disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">투</div>
				<div class="divTableCell"><input type="button" value="0" id="button3" onClick="TwosButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button4" onClick="TwosButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">쓰리</div>
				<div class="divTableCell"><input type="button" value="0" id="button5" onClick="ThreesButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button6" onClick="ThreesButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">포</div>
				<div class="divTableCell"><input type="button" value="0" id="button7" onClick="FoursButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button8" onClick="FoursButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">파이브</div>
				<div class="divTableCell"><input type="button" value="0" id="button9" onClick="FivesButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button10" onClick="FivesButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">식스</div>
				<div class="divTableCell"><input type="button" value="0" id="button11" onClick="SixesButton()"disabled = true ></div>
				<div class="divTableCell"><input type="button" value="0" id="button12" onClick="SixesButton()"disabled = true ></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">보너스</div>
				<div class="divTableCell"><b id="text1">0/63</b></div>
				<div class="divTableCell"><b id="text2">0/63</b></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">쓰리-오브-카인드</div>
				<div class="divTableCell"><input type="button" value="0" id="button13" onClick="Three_Of_A_KindButton()"disabled = true ></div>
				<div class="divTableCell"><input type="button" value="0" id="button14" onClick="Three_Of_A_KindButton()"disabled = true ></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">포-오브-카인드</div>
				<div class="divTableCell"><input type="button" value="0" id="button15" onClick="Four_Of_A_KindButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button16" onClick="Four_Of_A_KindButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">풀 하우스</div>
				<div class="divTableCell"><input type="button" value="0" id="button17" onClick="Full_HouseButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button18" onClick="Full_HouseButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">스몰 스트레이트</div>
				<div class="divTableCell"><input type="button" value="0" id="button19" onClick="Small_StraightButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button20" onClick="Small_StraightButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">라지 스트레이트</div>
				<div class="divTableCell"><input type="button" value="0" id="button21" onClick="Large_StraightButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button22" onClick="Large_StraightButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">찬스</div>
				<div class="divTableCell"><input type="button" value="0" id="button23" onClick="ChanceButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button24" onClick="ChanceButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">야찌</div>
				<div class="divTableCell"><input type="button" value="0" id="button25" onClick="YahtzeeButton()" disabled = true></div>
				<div class="divTableCell"><input type="button" value="0" id="button26" onClick="YahtzeeButton()" disabled = true></div>
			</div>
			<div class="divTableRow">
				<div class="divTableCell">최종점수</div>
				<div class="divTableCell"><b id="text3">0</b></div>
				<div class="divTableCell"><b id="text4">0</b></div>
			</div>
		</div>
	</div>
</div>
<div><input type="button" id="exitbutton" value = "나가기" onClick="exitButtons()"></div>
</body>
</html>