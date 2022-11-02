import java.lang.annotation.ElementType;

public class GetClass {
    public static void main(String[] args) throws ClassNotFoundException {
        //获得class的4种方式
        Student student=new Student();
        Class aClass = student.getClass();
        System.out.println(aClass.hashCode());

        Class<?> student1 = Class.forName("Student");
        System.out.println(student1.hashCode());
        System.out.println(student1);

        Class<Student> studentClass = Student.class;
        System.out.println(studentClass.hashCode());
        System.out.println(studentClass);

        Class<Integer> type = Integer.TYPE;
        System.out.println(type);

        Class superclass = aClass.getSuperclass();
        System.out.println(superclass);


        //哪些类型的数据有class？
        Class c1=Object.class;
        Class c2=Comparable.class;
        Class c3=String [].class;
        Class c4=int[][].class;
        Class c5=Override.class;
        Class c6=ElementType.class;
        Class c7=Integer.class;
        Class c8=void.class;
        Class c9=Class.class;
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);

        int[] a=new int[10];
        int[] b=new int[1000];
        System.out.println(a.getClass().hashCode()+"  "+ b.getClass().hashCode());

    }
}
