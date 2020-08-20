package com.example.Dao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Purchase {
    private int member_number;
    private int game_number;
    private LocalDateTime purchase_date;

    public Purchase(int member_number, int game_number, LocalDateTime purchase_date) {
        this.member_number = member_number;
        this.game_number = game_number;
        this.purchase_date = purchase_date;
    }

    public int getMember_number() { return member_number; }
    public int getGame_number() { return game_number; }
    public LocalDateTime getPurchase_date() { return purchase_date; }

    public void setMember_number(int member_number) { this.member_number = member_number; }
    public void setGame_number(int game_number) { this.game_number = game_number; }
    public void setPurchase_date(LocalDateTime purchase_Date) { this.purchase_date = purchase_Date; }
}
