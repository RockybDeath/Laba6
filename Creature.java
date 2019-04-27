public abstract class Creature {
    private String name;
    private int age;

    public String GetName() {
        return name;
    }

//    public int GetAge() {
//        return age;
//    }

    public Creature(String _name, int _age) {
        name = _name;
        age = _age;
    }

    public Creature() {
    }

    public Creature(String _name) {
        name = _name;
    }

    /*@Override
    public boolean equals(java.lang.Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Creature guest = (Creature) obj;
        if (obj.hashCode() == this.hashCode())
            return true;
        return guest.name == this.name && guest.age == this.age;
    }*/

    /*@Override
    public String toString() {
        return "Object [name=" + name
                + ", age=" + age
                + "]";
    }*/

    /*@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + name.length();
        result = prime * result + age;
        return result;
    }*/
}
