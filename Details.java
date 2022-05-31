import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class ATM_INTERFACE
{
	String user_id;
	int pin_no;
	int Balance;

	 void Getdata()
	 {
		 System.out.println("Enter the information about customers...");
		 Scanner sc = new Scanner(System.in);
		 System.out.print("Enter User ID :");
		 user_id= sc.nextLine();
		 System.out.print("Enter pin nubmer :");
		 pin_no = sc.nextInt();
		 System.out.print("Enter Balance:");
		 Balance = sc.nextInt();
		 System.out.println("=====================================");
 	}
}
class transhist{
	void add(int amt,int choice,List<String> transhist1) {
		if(choice == 2) {
			String transaction = amt+" has been withdrawn";
			transhist1.add(transaction);
		}
		else {
			String transaction = amt+" deposited";
			transhist1.add(transaction);
		}
	}
	void add(int amt,String rec,List<String> transhist1) {
		String transaction = amt+" transferred to "+rec;
		transhist1.add(transaction);
	}
	void display(List<String> transhist1) {
		if(transhist1.size() == 0) {
			System.out.println("No Transactions yet!");
		}
		else {
			for(int i=1;i<=transhist1.size();i++) {
				System.out.println("Transaction "+i+" : "+transhist1.get(i-1));
			}
		}
	}
}

class withdraw {
	int draw(int bal,int amt) {
		bal = bal - amt;
		return bal;
	}
}
class deposit{
	int add(int bal,int amt) {
		bal = bal + amt;
		return bal;
	}
}
class transfer{
	int trans(int bal,int amt) {
		bal = bal - amt;
		return bal;
	}
}
class Details extends ATM_INTERFACE
{
	public static void main(String args[])
	{
			String user_id;
			int pin_no;
			int amount;
			int Balance;
			List<String> transhist1 = new ArrayList<>();
			Details info=new Details();
			info.Getdata();
			//obj1.Putdata();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your User ID:");
			String id = sc.nextLine();
			System.out.println("Enter your pin:");
			int pword = sc.nextInt();

			boolean leave=true;
					if(info.user_id.equals(id) && info.pin_no==pword) {
						System.out.println("Welcome! Your account balance is:= "+info.Balance);
						do {
							System.out.println("\nPress the corresponding numbers:");
							System.out.println("1.Transaction History\n2.Deposit Money\n3.Withdraw Money\n4.Transfer Money\n5.Quit\n");
							int choice = sc.nextInt();
							transhist objx;
							switch(choice) {
							case 1: transhist obj = new transhist();
									obj.display(transhist1);
								    break;
							case 2: System.out.println("Enter amount to be deposited:");
			                    amount = sc.nextInt();
			                    deposit obj2 = new deposit();
			                    Balance = obj2.add(info.Balance, amount);
			                    objx = new transhist();
			                    objx.add(amount,3,transhist1);
			                    System.out.println("Successfully deposited!");
			                    System.out.println("Current Balance: "+Balance);
			                    break;
							case 3:
			                        System.out.println("Enter amount to be withdrawn:");
									amount = sc.nextInt();
									if(amount > info.Balance) {
										System.out.println("Amount greater than balance");
										break;
									}
									withdraw obj1 = new withdraw();
								    Balance = obj1.draw(info.Balance, amount);
								    objx = new transhist();
									objx.add(amount,2,transhist1);
								    System.out.println("Money is Successfully Withdrawn!");
								    System.out.println("Current Balance is: "+Balance);
								    break;
							case 4: System.out.println("Enter amount to be transfered:");
									amount = sc.nextInt();
									if(amount >info.Balance) {
										System.out.println("Amount is greater than balance");
										break;
									}
									System.out.println("Enter receipent Number:");
									String rec = sc.next();
									transfer obj3 = new transfer();
								    Balance = obj3.trans(info.Balance, amount);
								    objx = new transhist();
									objx.add(amount,rec,transhist1);
								    System.out.println("Successfully transferred to "+rec+"!");
								    System.out.println("Current Balance: "+Balance);
								    break;
							case 5: leave = false;
								    break;
							default: System.out.println("Invalid Choice");

							}
						}while(leave);
					}
					else {
						System.out.println("Wrong Credentials");
					}
					sc.close();
	}
}