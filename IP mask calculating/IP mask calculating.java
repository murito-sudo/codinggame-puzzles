/*
Given an IP address and a mask in CIDR format you must output the network address and the broadcast address.

The IP address is the address of a computer on a network. In an IP address there are two parts, the network part and the machine part. We can separate the two parts with the mask.

The mask is, in binary, a continuous part of 1s flowing by a continuous part of 0s. It can't be a mixture of 1 and 0.

To know the network address of an IP we proceed like this:
For the entry 192.168.0.15/24, the machine IP is 192.168.0.15 and the mask part is 24.

The mask part means the first 24 bits in the mask are set at 1 and the last ones are set to 0:
The mask is 11111111.11111111.11111111.00000000
In integers that corresponds to 255.255.255.0

To know the network address we take all bits with a 1 in the mask:
192.168.0.15 is in binary
11000000.10101000.00000000.00001111
11111111.11111111.11111111.00000000 -> is the mask in binary
Now the network part of the IP address is:
11000000.10101000.00000000
After, we place all 0 we need to get a total of 32 digits
The network address is now
10000000.10101000.00000000.00000000
In integer that means
192.168.0.0

The broadcast address is the network address with all bits in machine part set to 1.

If we take the same example we got this
192.168.0.15 is in binary
11000000.10101000.00000000.00001111
11111111.11111111.11111111.00000000 -> is the mask in binary
The broadcast address is:
11000000.10101000.00000000.11111111 -> in integer that means 192.168.0.255.


*/


import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String ip = in.nextLine();
        String ipBinary = "";
        String subnet = "";
        String bitMask = "";
        String nAdress = "";
        String bAdress = "";
        int index = ip.indexOf("/");
        int mask = Integer.parseInt(ip.substring(index+1, ip.length()));
        ip = ip.substring(0, index);
        boolean isSame = true;
       
        if(mask == 0){
            System.out.println("0.0.0.0");
            System.out.println("255.255.255.255");
            return;
        }
        

        String[] array = ip.split("\\.");

         for(int x = 0; x < array.length; x++){
            
             if(!array[0].equals(array[x])){
                 isSame = false;
                 break;
             }
         }

       

  


      

        for(int x = 0; x  < array.length; x++){
           
            if(x == array.length-1){
                ipBinary += Integer.toBinaryString(Integer.parseInt(array[x]));

            }else{
                ipBinary += Integer.toBinaryString(Integer.parseInt(array[x])) + ".";
            }
        }

        String[] array2 = ipBinary.split("\\.");
        ipBinary = "";
        int counter = 0;

        while(counter < array2.length){
            if(array2[counter].length() == 8){
                if(counter+1 == array2.length){
                    ipBinary += array2[counter];
                }else{
                    ipBinary += array2[counter] + ".";
                }
                counter++;
            }else{
                array2[counter] = "0" + array2[counter];
            }

        }

     
        for(int x = 1; x <= array2.length * 8; x++){
            if(x <= mask){
                if(x % 8 == 0 && x < 32){
                    bitMask += "1.";
                }else{
                    bitMask += "1";

                }

            }else{
                if(x % 8 == 0 && x < 32){
                    bitMask += "0.";
                }else{
                    bitMask += "0";
                }

            }

        }

        for(int x = 0; x < array2.length-1; x++){
            if(x == array.length-2){
                nAdress += String.valueOf(Integer.parseInt(array2[x], 2));
            }else{
                nAdress += String.valueOf(Integer.parseInt(array2[x], 2)) + ".";
            }
            
        }

        
        
       
        
      

        bitMask = bitMask.replace("1", "2");
        bitMask = bitMask.replace("0", "1");
         bitMask = bitMask.replace("2", "0");
        
        bitMask = bitMask.replace(".","");
        ipBinary = ipBinary.replace(".","");


        for(int x = 0; x < bitMask.length(); x++){
            int q = Integer.parseInt(String.valueOf(bitMask.charAt(x)));
            int qq = Integer.parseInt(String.valueOf(ipBinary.charAt(x)));

            if(q == 1 || qq == 1){
                if((x+1) % 8 == 0 && (x+1) < 32){
                    bAdress += "1" + ".";
                }else{
                    bAdress += "1";
                }
            }else{
                if((x+1) % 8 == 0 && (x+1) < 32){
                    bAdress += "0" + ".";
                }else{
                    bAdress += "0";
                }

            }
        }
       

       
            String[] array5 = bAdress.split("\\.");
            bAdress = "";

            for(int x = 0; x < array5.length; x++){
                int q = Integer.parseInt(array5[x], 2);


                if(x == 0 && q == 255){
                    bAdress += "0.";
                    continue;
                }

                if(x == array5.length-1){
                    if(array5[x].equals(array5[x-1]) && bAdress.charAt(bAdress.length()-2) == '0'){
                        bAdress += "0";
                    }else{
                        bAdress += q;
                    }

                }else{
                    if(x == 0){
                        bAdress += q + ".";
                    }else{
                        if(array5[x].equals(array5[x-1]) && bAdress.charAt(bAdress.length()-2) == '0'){
                            bAdress += "0.";
                        }else{
                            bAdress += q + ".";
                        }
                    }

                }


            }

                String reference = bitMask.substring(8);
                if(!reference.contains("0")){
                    int eq = Integer.parseInt(array[0])-1;
                    nAdress = eq + ".0.0";
                }

                if(isSame == true){
                    
                    nAdress = array[0] + ".0.0";
                }
            

                System.out.println(nAdress + ".0");
                System.out.println(bAdress);
       
    }
}