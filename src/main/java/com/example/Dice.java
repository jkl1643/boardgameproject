package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Dice {
    private String cmd;
    private int dice1;
    private int dice2;
    private int dice3;
    private int dice4;
    private int dice5;
    private int dice6;
    private int aces;
    private int twos;
    private int threes;
    private int fours;
    private int fives;
    private int sixes;
    private int three_Of_A_Kind;
    private int four_Of_A_Kind;
    private int full_House;
    private int small_Straight;
    private int large_Straight;
    private int chance;
    private int yahtzee;
    private int bonus;
    private int player;

    public String getCmd() { return cmd; }
    public void setCmd(String cmd) { this.cmd = cmd; }
    public int getDice1() { return dice1; }
    public void setDice1(int dice1) { this.dice1 = dice1; }
    public int getDice2() { return dice2; }
    public void setDice2(int dice2) { this.dice2 = dice2; }
    public int getDice3() { return dice3; }
    public void setDice3(int dice3) { this.dice3 = dice3; }
    public int getDice4() { return dice4; }
    public void setDice4(int dice4) { this.dice4 = dice4; }
    public int getDice5() { return dice5; }
    public void setDice5(int dice5) { this.dice5 = dice5; }
    public int getDice6() { return dice6; }
    public void setDice6(int dice6) { this.dice6 = dice6; }
    public int getAces() { return aces; }
    public void setAces(int aces) { this.aces = aces; }
    public int getTwos() { return twos; }
    public void setTwos(int twos) { this.twos = twos; }
    public int getThrees() { return threes; }
    public void setThrees(int threes) { this.threes = threes; }
    public int getFours() { return fours; }
    public void setFours(int fours) { this.fours = fours; }
    public int getFives() { return fives; }
    public void setFives(int fives) { this.fives = fives; }
    public int getSixes() { return sixes; }
    public void setSixes(int sixes) { this.sixes = sixes; }
    public int getThree_Of_A_Kind() { return three_Of_A_Kind; }
    public void setThree_Of_A_Kind(int three_Of_A_Kind) { this.three_Of_A_Kind = three_Of_A_Kind; }
    public int getFour_Of_A_Kind() { return four_Of_A_Kind; }
    public void setFour_Of_A_Kind(int four_Of_A_Kind) { this.four_Of_A_Kind = four_Of_A_Kind; }
    public int getFull_House() { return full_House; }
    public void setFull_House(int full_House) { this.full_House = full_House; }
    public int getSmall_Straight() { return small_Straight; }
    public void setSmall_Straight(int small_Straight) { this.small_Straight = small_Straight; }
    public int getLarge_Straight() { return large_Straight; }
    public void setLarge_Straight(int large_Straight) { this.large_Straight = large_Straight; }
    public int getChance() { return chance; }
    public void setChance(int chance) { this.chance = chance; }
    public int getYahtzee() { return yahtzee; }
    public void setYahtzee(int yahtzee) { this.yahtzee = yahtzee; }
    public int getBonus() { return bonus; }
    public void setBonus(int bonus) { this.bonus = bonus; }
    public int getPlayer() { return player; }
    public void setPlayer(int player) { this.player = player; }
}