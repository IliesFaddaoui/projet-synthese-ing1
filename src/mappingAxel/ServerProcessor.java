package mappingAxel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import com.mysql.jdbc.Statement;

import com.google.gson.Gson;

import connexion.PoolDeConnexion;


public class ServerProcessor {


	public ServerProcessor() {
		// TODO Auto-generated method stub
		Connection con = BDD();
		EmplacementsDAO empldao = new EmplacementsDAO(con);
		FileWriter fw = null;
		
		

		// Mettre en place socket + seria + thread 
		try {
			fw = new FileWriter("Mapping.txt");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		
		Date aujourdhui = new Date();
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yy-MM-dd");
		
		MagasinsDAO magdao = new MagasinsDAO(con);
		
		int i = 0;
		int j = 1;
		
		OccupationDAO occpdao = new OccupationDAO(con);

		for(i = 1; i < 31; i++) {
			
		// Modifier critères attribution emplacement 
		// Add Chiffre d'affaire et zone privilégié 
			
		Magazins mag1 = magdao.find(i);
		Magazins mag2 = magdao.find(j);
		if (mag1 == null)
			continue ;

		Emplacements empl0 = empldao.find(i); 
		Emplacements empl01 = empldao.find(i);
		 j++;
		if (mag1.getMagasinSuperficie() < empl0.getSuperficie())
			try {
				java.sql.Statement stmt = con.createStatement();
				String sql = "INSERT INTO Occupation(idMagasin, idEmplacement, dateEntree) values ("+mag1.getIdMagasin()+","+ empl0.getIdEmplacement()+", '"+formater.format(aujourdhui)+"')";
				stmt.executeUpdate(sql);
				pw.println("Le magasin "+ mag1.getMagasinName()+" a été placé à l'emplacement "+ empl0.getLocalisation());
				
				}catch(SQLException e) {
					pw.println("Le magasin "+mag1.getMagasinName()+" à déjà été placé à l'emplacement "+empl0.getLocalisation());
				}
			
		else if (mag1.getMagasinSuperficie() < empl01.getSuperficie())
		try {
			java.sql.Statement stmt = con.createStatement();
			String sql = "INSERT INTO Occupation(idMagasin, idEmplacement, dateEntree) values ("+mag1.getIdMagasin()+","+ empl01.getIdEmplacement()+", '"+formater.format(aujourdhui)+"')";
			stmt.executeUpdate(sql);
			pw.println("Le magasin "+ mag1.getMagasinName()+" a été placé à l'emplacement "+ empl01.getCategorie());
			
		}catch(SQLException e) {
				pw.println("Le magasin "+mag1.getMagasinName()+" a déjà été placé à l'emplacement "+empl01.getLocalisation());
			}
		else 
		pw.println("Le magasin " + mag1.getMagasinName()+" n'a pas pu être placé");
		}
		pw.close();
	//}
	// Pour nouvelle attribution Magasin Emplacement 
//	public void newStore(String magasin, String emplacement) {
		 Socket sock = null;
	 PrintWriter writer = null;
	 BufferedInputStream reader=null;
	 PoolDeConnexion connection;
	 //writer = new PrintWriter(sock.getOutputStream());
	//	reader = new BufferedInputStream(sock.getInputStream());
		//Gson gson = new Gson();
	/*	Connection con = BDD();
		Date aujourdhui = new Date();
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("dd-MM-yy");*/
		MagasinsDAO magdao1 = new MagasinsDAO(con);
		//EmplacementsDAO empldao = new EmplacementsDAO(con);
		
		try {
				ServerSocket s = new ServerSocket(500);
		       Socket soc = s.accept();
		        BufferedReader plec = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		        PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())),true);
		       Gson gson = new Gson();
		        //writer = new PrintWriter(sock.getOutputStream());
			//reader = new BufferedInputStream(sock.getInputStream());

		        while(true) {
		            String str = plec.readLine();
		          //  Emplacements e1 = gson.fromJson(str, Emplacements.class);
		            String str2 = plec.readLine();
		            if (str.equals("END")) break;
		            if (str2.equals("END")) break;
		      //     Emplacements e1 = gson.fromJson(str, Emplacements.class);
		            System.out.println(str);
		            System.out.println(str2);
		            pred.println(str);
		            pred.println(str2);
		        /*
				
			//Magazins magname = magdao1.findName(str);
			//Emplacements emp = empldao.findName(str2);
		//	java.sql.Statement stmt = con.createStatement();
		//	String sql = "INSERT INTO Occupation(idMagasin, idEmplacement, dateEntree) values ("+magname.getIdMagasin()+","+ emp.getIdEmplacement()+", '"+formater.format(aujourdhui)+"')";
			//stmt.executeUpdate(sql);
		//	System.out.println("Le magasin "+ magname.getMagasinName() +" a �t� plac� � l'emplacement "+ emp.getLocalisation());
		       */   }
		      plec.close();
		        pred.close();
		        soc.close();
			
			
		        } catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();}
		}
	

	public static Connection BDD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver OK");
			String url="jdbc:mysql://localhost/pds";
			String user="root";
			String password="root";
			Connection con=DriverManager.getConnection(url, user, password);
			System.out.println("Connexion établie");
			return con;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
