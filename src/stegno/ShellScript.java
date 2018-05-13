
package stegno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellScript extends Thread implements Runnable {
    private static String rootPass;
    
    
    public static void setRootPassword(String pass){
        rootPass =pass;
    }
    //to run and get single string ouput
    public String runCmd(String target){
                try {
                       //String target= new String("date");
                        Runtime rt = Runtime.getRuntime();
                        Process proc = rt.exec(target);
                        proc.waitFor();
                        StringBuffer output = new StringBuffer();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                        String line = "";                       
                        while ((line = reader.readLine())!= null) {
                                output.append(line + "\n");
                                
                                
                        }
                        System.out.println("### " + output);
                        String testOut = (String)output.toString();
                        //return output.toString();
                        System.out.println(testOut.length());
                        return testOut;
                } catch (Throwable t) {
                        t.printStackTrace();
                }return null;
        }

public String[] runCmd(String target,int skipLn){
                try {
                       //String target= new String("date");
                        Runtime rt = Runtime.getRuntime();
                        Process proc = rt.exec(target);
                        proc.waitFor();
                        String[] cmdOut =new String[20];
                        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                        String line = "";                       
                        int currentLn=0;
                        int lnCount=0;
                        while ((line = reader.readLine())!= null) {
                            if(currentLn>=skipLn){
                                    cmdOut[lnCount]=line.replaceAll(" +", " ") ;
                                    lnCount++;
                            }
                            currentLn+=1;
                        }
                        String cmdOutFinal[]= new String[lnCount];
                        int k=0;
                        while(cmdOut[k]!=null){
                            cmdOutFinal[k]=cmdOut[k];
                            k++;
                        }
                        return cmdOutFinal;
                } catch (Throwable t) {
                        t.printStackTrace();
                }return null;
        }

public String[] runCmd(String target,int skipLn,int ln){
                try {
                        Runtime rt = Runtime.getRuntime();
                        Process proc = rt.exec(target);
                        proc.waitFor();
                        StringBuffer output = new StringBuffer();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                        String line = "";
                        String[] cmdOut= new String[ln];
                        int currentLn=0;
                        int lnCount=0;
                        while ((line = reader.readLine())!= null) {
                            if(currentLn>=skipLn){
                                if(lnCount>=ln){break;}//to avoid error ouput line more than LN
                                else{
                                    output.append(line);
                                    cmdOut[lnCount]=line ;
                                    lnCount++;
                                }
                            }
                            currentLn+=1;
                        }
                        String cmdOutFinal[]= new String[lnCount];
                        int k=0;
                        while(cmdOut[k]!=null){
                            cmdOutFinal[k]=cmdOut[k];
                            k++;
                        }
                        return cmdOutFinal;
                } catch (Throwable t) {
                        t.printStackTrace();
                }return null;
        }

public String bashScript(String script){
    try {   
            //String cmd = " echo \""+rootPass+"\"| sudo -S "+script;
            //String cmd ="echo \"hi world\"";
            //String cmd = " /bin/bash -c echo bluemango"+" | sudo -S parted -l | grep ext4";
            //System.out.println(script);
            Process proc = Runtime.getRuntime().exec(script); //Whatever you want to execute
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(read.readLine());
            return read.readLine();
            /*
            while (read.ready()) {
                String str=read.readLine();
                return str;
            }
            */
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }return null;
}

}