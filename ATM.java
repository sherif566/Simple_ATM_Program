import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ATM {
    public
            
     Scanner sc=new Scanner(System.in);
     String f_name;
     String L_name;
     int pin;
     int age;
     String nationality;
     String status;
     int amnt_av;
     int amnt_dep;
     String gender;

    public String getF_name() {
        return f_name;
    }
    
    public void setF_name(String f_name) {
        
        this.f_name = f_name;
    }

    public String getL_name() {
        return L_name;
    }

    public void setL_name(String L_name) {
        this.L_name = L_name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) throws ClassNotFoundException, SQLException {
               this.pin = pin;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmnt_av() {
        return amnt_av;
    }

    public void setAmnt_av(int amnt_av) {
        this.amnt_av = amnt_av;
    }

    public int getAmnt_dep() {
        return amnt_dep;
    }

    public void setAmnt_dep(int amnt_dep) {
        this.amnt_dep = amnt_dep;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
   
    public void log_inuser() throws SQLException, ClassNotFoundException{
        System.out.println("Please enter your First name:");
        String f_n= sc.nextLine();
        setF_name(f_n);
        System.out.println("Please enter Pin:");
               int st=sc.nextInt();
        sc.nextLine();
        setPin(st);
        Connection con = connection.connectDB();
        Statement stmt = con.createStatement();
        String k="SELECT First_Name,PIN FROM accounts";
        ResultSet rs = stmt.executeQuery(k);
        boolean ch1=false;
        boolean ch2=false;
      while (rs.next()) {
         String F_name = rs.getString("First_Name");
         if(F_name.equals(getF_name()) ){
             ch1=true;
         }
         int ppin=rs.getInt("PIN");
             if(ppin==getPin()){
             ch2=true;
         }
         //&& ppin==getPin()
      }
      if(ch1==true && ch2==true){
          System.out.println("Logged in successfully");
      }
      else{
          System.out.println("Sorry either the password or the pin is wrong");
          log_inuser();
      }
 


    }
    
    public void insert_data(String F_name,String L_name,String nationality,String gender,int age,int pin,int amnt_av) throws SQLException, ClassNotFoundException{
    Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;

		con = connection.connectDB();

		

			String sql = "select * from accounts";
			p = con.prepareStatement(sql);
			rs = p.executeQuery();
                                    Statement stmt = con.createStatement();
                      
 int result = stmt.executeUpdate("insert into accounts(First_Name,Last_Name,Nationality,Gender,Age,Pin,Amount_Available) values('"+F_name+"','"+L_name+"','"+nationality+"','"+gender+"','"+age+"','"+pin+"','"+amnt_av+"')");}
   
    
    
    public void Open_acc() throws SQLException, ClassNotFoundException{
        System.out.println("Please enter your First name:");
        String f_n= sc.nextLine();
        setF_name(f_n);
        System.out.println("Please enter your Last name:");
        String l_n= sc.nextLine();
        setL_name(l_n);
        System.out.println("Please enter your age :");
        int ag=sc.nextInt();
        sc.nextLine();
        setAge(ag);        
        System.out.println("Please enter your Gender:");
        String g=sc.nextLine();
        setGender(g);
        System.out.println("Please enter your Nationality :");
        String n= sc.nextLine();
        setNationality(n);
        System.out.println("Please enter Amount Available :");
        int av_am= sc.nextInt();
        setAmnt_av(av_am);
        System.out.println("Please enter your Pin :");
        int st=sc.nextInt();
        sc.nextLine();
                String k="SELECT PIN FROM accounts";
    Connection con = connection.connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(k);
      while (rs.next()) {
         int pins = rs.getInt("PIN");
         if(pins==pin){
             System.out.println("Sorry this pin is already used before try again :(");
             Open_acc();
         }
         else{
        setPin(st);    

         }
        setPin(st);    
        insert_data(getF_name(),getL_name(),getNationality(),getGender(),getAge(),getPin(),getAmnt_av());        
        
    }
    }
    
    
public void view_acc() throws ClassNotFoundException, SQLException{

    Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		con = connection.connectDB();
			// SQL command data stored in String datatype
			String sql = "select * from accounts";
			p = con.prepareStatement(sql);
			rs = p.executeQuery();
                                    Statement stmt = con.createStatement();
			// Condition check
			while (rs.next()) {
                                String name = rs.getString("First_Name");
				String l_name = rs.getString("Last_Name");
				String nat = rs.getString("Nationality");
                                String gen = rs.getString("Gender");
                                int ag= rs.getInt("Age");
                                int am_av= rs.getInt("Amount_Available");
				System.out.println(name + "\t\t" + l_name+ "\t\t" + nat+"\t\t" + gen+ "\t\t" + ag  +"\t\t"+ am_av );
}
}




public void delete_acc() throws SQLException, ClassNotFoundException{

    System.out.println("Please enter the First name");
    String First=sc.nextLine();
        System.out.println("Please enter the Last name");
    String Last=sc.nextLine();
        System.out.println("Please enter the Password");
    int pass=sc.nextInt();
    sc.nextLine();
        Connection con = connection.connectDB();
        Statement stmt = con.createStatement();

     String q1 = "DELETE from accounts WHERE PIN = '"+ pass +"' AND First_Name = '"+ First +"' AND Last_Name ='"+Last+"'";
                     
            int x = stmt.executeUpdate(q1);
             
            if (x > 0)           
                System.out.println("One User Successfully Deleted"); 

else
                System.out.println("ERROR OCCURED :(");   
}






public void Deposit() throws ClassNotFoundException, SQLException{

    System.out.println("Please enter the First name");
    String First=sc.nextLine();

        System.out.println("Please enter the Password");
    int pass=sc.nextInt();
    sc.nextLine();
    System.out.println("Please enter the amount you'd like to deposit :");
    int dep=sc.nextInt();
    sc.nextLine();
        Connection con = connection.connectDB();
        Statement stmt = con.createStatement();
        String k="SELECT Amount_Available FROM accounts WHERE PIN = '"+pass+"' AND First_Name='"+First+"'";
        ResultSet rs = stmt.executeQuery(k);
        int am_av = 0;
      while (rs.next()) {
         am_av = rs.getInt("Amount_Available");
      }
      int am_deposited=am_av+dep;
        String q1 = "UPDATE accounts set Amount_Deposited = '" + dep +
                    "' WHERE First_Name = '" +First+ "' AND PIN = '" + pass + "'";
        String q2 = "UPDATE accounts set Amount_Available = '" + am_deposited +
                    "' WHERE First_Name = '" +First+ "' AND PIN = '" + pass + "'";
        int x = stmt.executeUpdate(q1);
        int y = stmt.executeUpdate(q2);

        System.out.println("Amount deposited successfully !!");
}





public void Withdraw() throws ClassNotFoundException, SQLException{

    System.out.println("Please enter the First name");
    String First=sc.nextLine();

        System.out.println("Please enter the Password");
    int pass=sc.nextInt();
    sc.nextLine();
    System.out.println("Please enter the amount you'd like to Withdraw :");
    int withd=sc.nextInt();
    sc.nextLine();
        Connection con = connection.connectDB();
        Statement stmt = con.createStatement();
        String k="SELECT Amount_Available FROM accounts WHERE PIN = '"+pass+"' AND First_Name='"+First+"'";
        ResultSet rs = stmt.executeQuery(k);
        int am_av = 0;
      while (rs.next()) {
         am_av = rs.getInt("Amount_Available");
      }
      if(withd>am_av){
          System.out.println("There isnt enough money in your account to withdraw this amount");
          Withdraw();
      }
      else{
      int am_withdrawed=am_av-withd;
        String q1 = "UPDATE accounts set Amount_Available = '" + am_withdrawed +
                    "' WHERE First_Name = '" +First+ "' AND PIN = '" + pass + "'";
        int x = stmt.executeUpdate(q1);
        System.out.println("Amount Withdrawed successfully !!");
}
}





public void check_balance() throws ClassNotFoundException, SQLException{

    System.out.println("Please enter the First name");
    String First=sc.nextLine();

        System.out.println("Please enter the Password");
    int pass=sc.nextInt();
    sc.nextLine();
    String k="SELECT Amount_Available FROM accounts WHERE PIN = '"+pass+"' AND First_Name='"+First+"'";
    Connection con = connection.connectDB();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(k);
        int am_av = 0;
      while (rs.next()) {
         am_av = rs.getInt("Amount_Available");
          System.out.println("Your balance is : "+am_av);
      }
    
}


public void Transfer_money() throws ClassNotFoundException, SQLException{
    System.out.println("Please enter the First name of the account user you are transferring from");
    String First=sc.nextLine();
    System.out.println("Please enter the pin of the account user you are transferring from");
    int pass=sc.nextInt();
    sc.nextLine();
    System.out.println("Please enter the First name of the person you'd like to  transfer the money to: ");
    String oth_persfname=sc.nextLine();
    System.out.println("Please enter the Last name of the person you'd like to  transfer the money to:  ");
    String oth_perslname=sc.nextLine();
     System.out.println("Please enter the amount you'd like to transfer to this account : ");
    int amnt=sc.nextInt();
    sc.nextLine();
    Connection con = connection.connectDB();
        Statement stmt = con.createStatement();
        String k="SELECT Amount_Available FROM accounts WHERE PIN = '"+pass+"' AND First_Name='"+First+"'";
        ResultSet rs = stmt.executeQuery(k);
        int am_av = 0;
      while (rs.next()) {
         am_av = rs.getInt("Amount_Available");
      }
      if(amnt>am_av){
          System.out.println("There isnt enough money in your account to transfer this amount");
          Transfer_money();
      }
 
      int am_trans=am_av-amnt;
        String q1 = "UPDATE accounts set Amount_Available = '" + am_trans +
                    "' WHERE First_Name = '" +First+ "' AND PIN = '" + pass + "'";

      
      
        String s="SELECT Amount_Available FROM accounts WHERE First_name = '"+oth_persfname+"' AND Last_Name='"+oth_perslname+"'";
                Statement st = con.createStatement();

        ResultSet rs2 = st.executeQuery(s);
        int am2_av = 0;
      while (rs2.next()) {
         am2_av = rs2.getInt("Amount_Available");
      }
       int am_trans2=am2_av+amnt;
                
        String q2 = "UPDATE accounts set Amount_Available = '" + am_trans2 +
                    "' WHERE First_Name = '" +oth_persfname+ "' AND Last_Name = '" + oth_perslname + "'";
        int y = stmt.executeUpdate(q2);
        int x = stmt.executeUpdate(q1);


        System.out.println("Amount Transferred successfully !!");
      
    
}

}

