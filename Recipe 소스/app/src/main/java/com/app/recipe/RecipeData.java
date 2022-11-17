package com.app.recipe;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class RecipeData implements Serializable {

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getTitle_re() {
        return title_re;
    }

    public void setTitle_re(String title_re) {
        this.title_re = title_re;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getRecomm() {
        return recomm;
    }

    public void setRecomm(String recomm) {
        this.recomm = recomm;
    }

    public String getScrap() {
        return scrap;
    }

    public void setScrap(String scrap) {
        this.scrap = scrap;
    }

    public String getC_method() {
        return c_method;
    }

    public void setC_method(String c_method) {
        this.c_method = c_method;
    }

    public String getC_state() {
        return c_state;
    }

    public void setC_state(String c_state) {
        this.c_state = c_state;
    }

    public String getC_nick() {
        return c_nick;
    }

    public void setC_nick(String c_nick) {
        this.c_nick = c_nick;
    }

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getC_material() {
        return c_material;
    }

    public void setC_material(String c_material) {
        this.c_material = c_material;
    }

    public String getDiffculity() {
        return diffculity;
    }

    public void setDiffculity(String diffculity) {
        this.diffculity = diffculity;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getC_times() {
        return c_times;
    }

    public void setC_times(String c_times) {
        this.c_times = c_times;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }


    String idx = "";


    String title_re = ""; //레시피제목


    String title = ""; //요리명


    String reg_id = "";


    String reg_name = "";


    String hit = "";


    String recomm = "";


    String scrap = "";


    String c_method = ""; //요리방법별명


    String c_state = "";//요리상황별명


    String c_nick = ""; //요리재료별명


    String c_type = ""; //요리종류별명


    String description = ""; //소개


    String c_material = ""; //재료


    String diffculity = ""; //난이도


    String people = ""; //인분


    String c_times = ""; //소요시간


    public RecipeData(String idx, String title_re, String title, String reg_id, String reg_name, String hit, String recomm, String scrap, String c_method, String c_state, String c_nick, String c_type, String description, String c_material, String diffculity, String people, String c_times, String reg_date) {
        this.idx = idx;
        this.title_re = title_re;
        this.title = title;
        this.reg_id = reg_id;
        this.reg_name = reg_name;
        this.hit = hit;
        this.recomm = recomm;
        this.scrap = scrap;
        this.c_method = c_method;
        this.c_state = c_state;
        this.c_nick = c_nick;
        this.c_type = c_type;
        this.description = description;
        this.c_material = c_material;
        this.diffculity = diffculity;
        this.people = people;
        this.c_times = c_times;
        this.reg_date = reg_date;
    }

    String reg_date = ""; //등록일


}
