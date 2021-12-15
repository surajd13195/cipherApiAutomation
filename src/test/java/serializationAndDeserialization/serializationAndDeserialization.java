package serializationAndDeserialization;

import java.io.*;

class test implements Serializable {

    int i=10, j=20;
}

public class serializationAndDeserialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        test t1 = new test();

        //serialization
        FileOutputStream fos = new FileOutputStream("testFile.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(t1);

        //Deserialization
        FileInputStream fis = new FileInputStream("testFile.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        test t2 = (test) ois.readObject();
        System.out.println(t2.i +" "+t2.j);

    }
}
