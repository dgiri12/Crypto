import java.util.*; //to use scanner

//Program to encrypt
//This program didn't need packages, from now on only use classes and no package
//packages don't work in cs3750a

class MyMethods{
    public int myEncrypt(int _x, int _km, int _kn, int _deltaY)
    {
        return ((_x << 4)+_km) ^ ((_x >> 5)+_kn) ^ (_x + _deltaY);

        //The bitwise operation for XOR (^) works exactly the same way like in
        //comp org 2, even if the variables are integer type, XOR works as if they
        //were still binary numbers. Same with bitwise addition and subtraction.
        //The data type of the numbers don't matter
    }
}
public class Crypto {
    //some constants
    static final int deltaOne = 0x11111111;
    static final int deltaTwo = 0x22222222;

    public static void main (String[] args){
        //Initializations
        Scanner sc=new Scanner(System.in);

        //variable initializations
        int[] K = {0x00000000,0x00000000,0x00000000,0x00000000};
        int[] L={0x00000000,0x00000000,0x00000000};
        int[] R={0x00000000,0x00000000,0x00000000};

        System.out.println("Program start...");
        System.out.println("Maximum value supported is "+Integer.toHexString(Integer.MAX_VALUE));

        //FILL KEY ARRAY BLOCK--------------------------
        for (int i=0;i<4;i++) {
            boolean goOn = false;
            while (goOn == false) {
                try {
                    System.out.print("Please input K[" + i + "] in hex string (without 0x)");
                    String str = "0x" + sc.nextLine();
                    K[i] = Integer.decode(str);
                    goOn = true;
                } catch (Exception ex) {
                    System.out.println("Error! Try again...");
                }
            }
        }
        //END FILL KEY ARRAY BLOCK------------------------------

        //loop to test the array
        for (int i=0;i<4;i++){
            System.out.println("You have entered: "+Integer.toHexString(K[i])+" = "+K[i]);
        }

        //INPUT L[0] AND R[0] BLOCK-------------------------------
        boolean goOn = false;
        while (goOn==false)
        {
            try{
                //L[0]
                System.out.print("Please input L[0] in hex string (without 0x)");
                String str="0x"+sc.nextLine();
                L[0]=Integer.decode(str);

                //R[0]
                System.out.print("Please input R[0] in hex string (without 0x)");
                str="0x"+sc.nextLine();
                R[0]=Integer.decode(str);
                goOn=true;
            }
            catch(Exception ex)
            {
                System.out.println("Error! Try again...");
            }
        }
        //END INPUT L[0] AND R[0] BLOCK-------------------------------

        //Show inputs
        System.out.println("You have entered L[0]: "+Integer.toHexString(L[0])+" = "+L[0]);
        System.out.println("You have entered R[0]: "+Integer.toHexString(R[0])+" = "+R[0]);
        System.out.println("Begin Excryption...");

        //EXCRYPTION BLOCK BEGIN. Use provided L[0] and R[0] values to come up with
        //L[1], L[2], R[1] and R[2].
        MyMethods methods = new MyMethods();
        L[1] = R[0];
        R[1] = L[0] + methods.myEncrypt(R[0],K[0],K[1],deltaOne);
        L[2] = R[1];
        R[2] = L[1] + methods.myEncrypt(R[1],K[2],K[3],deltaTwo);
        //END ENCRYPTION---------------------------------------------

        System.out.println("L[0] = "+L[0]+" L[1] = "+L[1]+" L[2] = "+L[2]);
        System.out.println("R[0] = "+R[0]+" R[1] = "+R[1]+" R[2] = "+R[2]);
        System.out.println("In hex, L[2] = "+Integer.toHexString(L[2])+ " R[2] = "+Integer.toHexString(R[2]));

       }
}
