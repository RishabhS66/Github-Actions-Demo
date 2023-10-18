package com.bankingApp.banking.client;

import com.bankingApp.banking.entity.AccountManager;

import java.io.*;

import static java.lang.Math.min;

public class BankUserInterface {
    public static void main(String[] args) throws FileNotFoundException {

        introMsg();

        BufferedReader inpReader;
        boolean fromFile = false;
        if(args.length > 0) {
            try {
                String folder = new File("").getAbsolutePath();
                String filePath = folder + "/" + args[0];
                FileReader fileReader = new FileReader(filePath);   //reads the file
                inpReader = new BufferedReader(fileReader);
                fromFile = true;
            }
            catch (Exception e) {
                System.out.println("File Path given is wrong!");
                return;
            }
        }
        else {
            inpReader = new BufferedReader(new InputStreamReader(System.in));
        }

        boolean exit = false;
        String inputCommand;
        String[] inp;
        AccountManager aseAccMgr = new AccountManager();

        while(!exit) {
            try {
                takeCmdMsg();
                inputCommand = inpReader.readLine();
                if(fromFile) System.out.println(inputCommand);

                if(inputCommand == null) {
                    exit = true;
                    continue;
                }

                inp = inputCommand.split(" ");
                String cmd = inp[0].toLowerCase();

                if (cmd.equals("create")) {
                    String name = getName(inp);
                    aseAccMgr.createAccount(name);
                }
                else if (cmd.equals("deposit")) {
                    long accID = Long.parseLong(inp[1]);
                    double amount = Double.parseDouble(inp[2].indexOf('.')==-1?inp[2]:inp[2].substring(0, min(inp[2].indexOf('.')+3, inp[2].length())));
                    aseAccMgr.depositInAccount(accID, amount);
                }
                else if (cmd.equals("withdraw")) {
                    long accID = Long.parseLong(inp[1]);
                    double amount = Double.parseDouble(inp[2].indexOf('.')==-1?inp[2]:inp[2].substring(0, min(inp[2].indexOf('.')+3, inp[2].length())));
                    aseAccMgr.withdrawFromAccount(accID, amount);
                }
                else if (cmd.equals("balance")) {
                    long accID = Long.parseLong(inp[1]);
                    aseAccMgr.getBalanceOfUser(accID);
                }
                else if (cmd.equals("transfer")) {
                    long srcAccID = Long.parseLong(inp[1]);
                    long destAccID = Long.parseLong(inp[2]);
                    double amount = Double.parseDouble(inp[3].indexOf('.')==-1?inp[3]:inp[3].substring(0, min(inp[3].indexOf('.')+3, inp[3].length())));
                    aseAccMgr.transferFunds(srcAccID,destAccID, amount);
                }
                else if (cmd.equals("exit")) {
                    exit = true;
                }
                else {
                    System.out.println("Invalid Command!\n");
                }
            }
            catch (Exception e) {
                System.out.println("Error! Input could not be processed! \n");
            }
        }

        return;
    }

    private static void introMsg() {
        System.out.println("WELCOME TO ASE BANKING SYSTEMS");
        System.out.println("You can perform the following actions - ");
        System.out.println(
                "1. CREATE - Takes 1 parameter that is the full name of the holder. Creates a new account and returns the account number\n" +
                        "2. DEPOSIT - Takes 2 parameters as input. First is the account number and the second is the deposit amount. Returns the balance post deposit.\n" +
                        "3. WITHDRAW - Takes 2 parameters as input. First is the account number and the second is the withdrawal amount. Returns the balance post withdrawal.\n" +
                        "4. BALANCE - Takes 1 parameter that is the account number. Returns current balance.\n" +
                        "5. TRANSFER - Takes 3 parameters. First is the source account number, second is the target account number and the last one is the amount to transfer. Returns status as successful or failure.\n" +
                        "6. EXIT - Exits the program.\n"
        );
    }

    private static void takeCmdMsg() {
        System.out.print(">>> Enter command : ");
        return;
    }

    private static String getName(String[] inp) {
        String name = "";
        for(int i=1;i<inp.length;i++) {
            String[] nameVar = inp[i].split("\"");
            for(String s:nameVar) {
                if(s.length() > 0) {
                    if(i > 1) name+=(" "+s);
                    else name+=s;
                }
            }
        }
        return name;
    }
}
