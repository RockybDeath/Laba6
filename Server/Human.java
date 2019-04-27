import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Human extends Creature implements Comparable<Human>, Serializable {
    private String nickname;
    private String surName;
//    private Skill skill;
    private Location location;
    private int age;
    private Date date;
    @Override
    public int compareTo(Human o){
        if (this.nickname.toLowerCase().compareTo(o.getNickname().toLowerCase())<0) return 1;
        else if (this.nickname.toLowerCase().compareTo(o.getNickname().toLowerCase())>0) return -1;
        else if (this.surName.toLowerCase().compareTo(o.getSurName().toLowerCase())<0) return 1;
        else if (this.surName.toLowerCase().compareTo(o.getSurName().toLowerCase())>0) return -1;
        return 0;
    }
    public Human(){

    }

    public Date getDate() {
        return date;
    }

    public Human(String _name, String _surName, Location location, int age, Date date){
        nickname=_name;
        surName=_surName;
        this.location=location;
        this.age=age;
        this.date=date;
    }
    public void setNickname(String nickname){
        this.nickname=nickname;
    }
    public void setSurName(String surName){
        this.surName=surName;
    }
    public Human(String _name, String _surName, Location location, int age){
        nickname=_name;
        surName=_surName;
        this.location=location;
        this.age=age;
        date=new Date();
    }
    public String getSurName() {
        return surName;
    }
    public String getNickname(){
        return nickname;
    }
    public Location getLocation(){return location;}

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "nickname='" + nickname + '\'' +
                ", surName='" + surName + '\'' +
                ", location=" + location +
                ", age=" + age +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age &&
                Objects.equals(nickname, human.nickname) &&
                Objects.equals(surName, human.surName) &&
                Objects.equals(location, human.location) &&
                Objects.equals(date, human.date);
    }

    @Override
    public int hashCode() {
        final int prime = 2;
        int result = 1;
        result = prime * result
                + getNickname().length();
        result = prime*result + surName.length();
        result = prime*result + location.getX();
        result = prime*result + location.getY();
        result = prime*result + age;
        return result;
    }
    /*@Override
    public int compareTo(Human o) {
        return getWidth()-o.getWidth();
    }*/
    /*public void setHeight(int height){
        this.height=height;
    }*/
    /*public Human (String _name, String _surName, int _width, int _height){
        name=_name;
        surName=_surName;
        width=_width;
        height=_height;
        System.out.println("Человек - "+name+" "+surName+" c ростом "+height+" и шириной "+width+" успешно создан");
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Human(String _name,String _surName, int _width, int _height,int _mood){
        name=_name;
        surName=_surName;
        width=_width;
        height=_height;
        if (_mood>4) mood=4;
        else if (_mood<0) mood=0;
        else mood=_mood;
        System.out.println("Человек - "+name+" "+surName+" c ростом "+height+" и шириной "+width+" успешно создан");
    }
    public Human (String _name, String _surName, int _width, int _height, ArrayList<Skill> _skills){
        name=_name;
        surName=_surName;
        skills=_skills;
        width=_width;
        height=_height;
        System.out.println("Человек - "+name+" "+surName+" c ростом "+height+" и шириной "+width+" успешно создан");
    }
    public void Smthsaying(String say, Human human){
        System.out.println(say+"to"+human);
    }*/
    /*public Human(String _name,String _surName, int _width, int _height,int _mood, ArrayList<Skill> _skills){
        name=_name;
        surName=_surName;
        skills=_skills;
        width=_width;
        height=_height;
        if (_mood>4) mood=4;
                else if (_mood<0) mood=0;
                else mood=_mood;
        System.out.println("Человек - "+_name+" "+surName+" c ростом "+height+" и шириной "+width+" успешно создан");
    }*/
//    public Skill getSkill(){
//        return skill;
//    }
//    public void removeSkill(){
//        skill=null;
//    }
//    public void setSkill(Skill skill){
//        this.skill=skill;
//    }
    /*public ArrayList<Skill> getSkills() {
        return skills;
    }
    public void delSkill(Skill skill){
        skills.remove(skill);
    }
    public void setSkills(ArrayList<Skill> skills){
        this.skills=skills;
    }
    public boolean addSkill (Skill skill){
        try{
        if (skill.getName()!="Burn"){if (skills.add(skill)){
            System.out.println("Объекту - "+super.GetName()+" успешно присвоено умение "+skill.getName()+" "+skill.getInfo());
            return true;
        }
        else {
            System.out.println("При добавлении произошла ошибка");
            return false;
        }}
        else throw new WrongSkill(skill.getName());}
        catch(WrongSkill yatut){
            yatut.MyMessageForMyException();
            return false;
        }
    }*/
    /*public Moods getMood(){
        switch(mood){
            case 0: return Moods.ПСЖ;
            case 1: return Moods.Ужасное;
            case 2: return Moods.Пойдет;
            case 3: return Moods.Хорошее;
            default: return Moods.Письмяша;
        }
    }*/
    /*@Override
    public void activateSkill(Object object, String _nameskill){
        for (int i=0; i<getSkills().size();i++) {
            if (getSkills().get(i).getName() == _nameskill) {
                object.setStatus(getSkills().get(i).getStatus());
            }
        }
        System.out.println("Текущее состояние "+object.getName()+" - "+object.getStatus());
    }
    @Override
    public void Move(Object object, String _nameskill){
        for (int i=0; i<getSkills().size();i++) {
            if (getSkills().get(i).getName() == _nameskill) {
                if (object.getStatus()==getSkills().get(i).getStatus() /*&& object.getHeight()>getHeight() && object.getWidth()>getWidth()*//*){
                    System.out.println(super.GetName()+" "+getSkills().get(i).getInfo());
                }
                else System.out.println(super.GetName()+" найн "+getSkills().get(i).getInfo());
            }
        }
    }*/
//    @Override
//    public void activateSkill(Object object, String _nameskill){
//        object.setStatus(skill.getStatus());
//        System.out.println("Текущее состояние "+object.getName()+" - "+object.getStatus());
//    }
//    @Override
//    public void Move(Object object, String _nameskill){
//            if (skill.getName() == _nameskill) {
//                if (object.getStatus()==skill.getStatus() /*&& object.getHeight()>getHeight() && object.getWidth()>getWidth()*/){
//                    System.out.println(super.GetName()+" "+skill.getInfo());
//                }
//                else System.out.println(super.GetName()+" найн "+skill.getInfo());
//            }
//    }
    /*public static class Query {
        private boolean p;
        public boolean Checkskill(String nameskill, Human human){
            for (int i=0; i<human.getSkills().size();i++) {
                if (human.getSkills().get(i).getName() == nameskill) {
                    p=true; }
                p=false;
            }
        return p;
        }
    }*/
    /*public static boolean Check(Human human){
        return human.getHeight()>100;
    }*/
    /*public class Powder{
        private String color;
        private String type;
        public Powder(String color, String type){
            this.color=color;
            this.type=type;
        }
        public void pour(Human human){
            System.out.println("Насыпать порох " +human.GetName()+"y"+" который имеет "+color+" цвет и он "+type);
            if (color.equals("Черный")) human.changeMood(2);
            else human.changeMood(-2);
        }
    }*/
    /*@Override
    public void changeMood(int _mood){
        mood=mood+_mood;
        if (_mood<0) System.out.println("Настроение у "+super.GetName()+" упало ");
            else if (_mood>0) System.out.println("Настроение у "+super.GetName()+" поднялось ");
                else System.out.println("Настроение у "+super.GetName()+" не изменилось ");
        if (mood<0) mood=0;
        if (mood>4) mood=4;
    }*/
    /*@Override
    public boolean equals(java.lang.Object obj) {
        if (obj == null || obj.getClass() != getClass()) { return false; }
        Human guest=(Human) obj;
        if (obj.hashCode()==this.hashCode())
            return true;
        return /*guest.height == this.height &&*/ //guest.surName==this.surName /*&& guest.mood==this.mood && guest.width==this.width*/ //&& this.GetName()==guest.GetName()
        //        && this.skill==guest.skill;
    //}
}

