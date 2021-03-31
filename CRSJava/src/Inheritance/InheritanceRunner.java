package Inheritance;

class Employee{
    float salary=40000;
}
class Programmer extends Employee{
    int bonus=10000;

}


class Mammal {
    void eat(){System.out.println("eating...");}
}
class Dog extends Mammal {
    void bark(){System.out.println("barking...");}
}
class BabyDog extends Dog{
    void weep(){System.out.println("weeping...");}
}

class Lion extends Mammal {
    void roar(){System.out.println("meowing...");}
}

public class InheritanceRunner {

    public static void runner(String[] args) {

        //Single level inheritance
        Programmer p=new Programmer();
        System.out.println("Programmer salary is:"+p.salary);
        System.out.println("Bonus of Programmer is:"+p.bonus);

        //Multilevel inheritance
        BabyDog d=new BabyDog();
        d.weep();
        d.bark();
        d.eat();

        //Hierarchical inheritance
        Lion c=new Lion();
        c.roar();
        c.eat();

    }
}
