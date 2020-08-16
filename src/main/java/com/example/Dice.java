package com.example;

public class Dice {
    private String cmd;
    private int dice1;
    private int dice2;
    private int dice3;
    private int dice4;
    private int dice5;
    private int dice6;
    private int Aces;
    private int Twos;
    private int Threes;
    private int Fours;
    private int Fives;
    private int Sixes;
    private int Three_Of_A_Kind;
    private int Four_Of_A_Kind;
    private int Full_House;
    private int Small_Straight;
    private int Large_Straight;
    private int Chance;
    private int Yahtzee;


    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    public int getDice3() {
        return dice3;
    }

    public void setDice3(int dice3) {
        this.dice3 = dice3;
    }

    public int getDice4() {
        return dice4;
    }

    public void setDice4(int dice4) {
        this.dice4 = dice4;
    }

    public int getDice5() {
        return dice5;
    }

    public void setDice5(int dice5) {
        this.dice5 = dice5;
    }

    public int getDice6() {
        return dice6;
    }

    public void setDice6(int dice6) {
        this.dice6 = dice6;
    }

    public int getAces() {
        return Aces;
    }

    public void setAces(int aces) {
        Aces = aces;
    }

    public int getTwos() {
        return Twos;
    }

    public void setTwos(int twos) {
        Twos = twos;
    }

    public int getThrees() {
        return Threes;
    }

    public void setThrees(int threes) {
        Threes = threes;
    }

    public int getFours() {
        return Fours;
    }

    public void setFours(int fours) {
        Fours = fours;
    }

    public int getFives() {
        return Fives;
    }

    public void setFives(int fives) {
        Fives = fives;
    }

    public int getSixes() {
        return Sixes;
    }

    public void setSixes(int sixes) {
        Sixes = sixes;
    }

    public int getThree_Of_A_Kind() {
        return Three_Of_A_Kind;
    }

    public void setThree_Of_A_Kind(int three_Of_A_Kind) {
        Three_Of_A_Kind = three_Of_A_Kind;
    }

    public int getFour_Of_A_Kind() {
        return Four_Of_A_Kind;
    }

    public void setFour_Of_A_Kind(int four_Of_A_Kind) {
        Four_Of_A_Kind = four_Of_A_Kind;
    }

    public int getFull_House() {
        return Full_House;
    }

    public void setFull_House(int full_House) {
        Full_House = full_House;
    }

    public int getSmall_Straight() {
        return Small_Straight;
    }

    public void setSmall_Straight(int small_Straight) {
        Small_Straight = small_Straight;
    }

    public int getLarge_Straight() {
        return Large_Straight;
    }

    public void setLarge_Straight(int large_Straight) {
        Large_Straight = large_Straight;
    }

    public int getChance() {
        return Chance;
    }

    public void setChance(int chance) {
        Chance = chance;
    }

    public int getYahtzee() {
        return Yahtzee;
    }

    public void setYahtzee(int yahtzee) {
        Yahtzee = yahtzee;
    }
}