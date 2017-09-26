package br.com.importarchive.util;

import static br.com.importarchive.util.CheckInstanceObject.IsNullOrIsEmpty;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Date;

import br.com.importarchive.facade.Facade;
import br.com.importarchive.facade.FacadeSystem;

public class ArchiveUtils {
	
	public static byte[] read(InputStream is) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int read = 0;
        final byte[] b = new byte[1024];

        while ((read = is.read(b)) != -1) {
            out.write(b, 0, read);
        }
        return out.toByteArray();
    }
	
	public static String readToString(InputStream is) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int read = 0;
		final byte[] b = new byte[1024];
		
		while ((read = is.read(b)) != -1) {
			out.write(b, 0, read);
		}
		return out.toString();
	}
	
	public static String readInputStream(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader( new InputStreamReader(is));
		String linha = "";
		String result ="";
		int i = 0;
		while((linha = reader.readLine()) != null) {
			i++;
			linha = new String(linha.getBytes(), "UTF-8");
			String[] donor = linha.split(",");
			for(int x = 0; x < donor.length; x++)
				
			System.out.println("Linha " + i + linha);
			System.out.println(donor);
			result+= linha;
		}
		return result;
	}
	
	public static String readInputStreamToIssueNew(InputStream is) throws IOException, ClassNotFoundException, SQLException {
		BufferedReader reader = new BufferedReader( new InputStreamReader(is));
		String linha ="";
		String result ="";
		String[] header = null;
		int i = 0;
		Facade fc = new FacadeSystem();
		ConnectionHelper.init(insertIssue());
		while((linha = reader.readLine()) != null) {
			i++;
			linha = new String(linha.getBytes(), "UTF-8");
			if(i == 1) 
				header = linha.split(",");
			String bodyEmail = "";
			if(i != 1) {
				bodyEmail += headerEmail();
				while(linha.contains(",\"")) {
					int indexOf = linha.indexOf(",\"");
					int indexOf2 = linha.indexOf("\",");
					String substring = linha.substring(indexOf + 1,indexOf2 + 1);
					String substring2 = substring.replaceAll(",", ".").replaceAll("\"", "");
					linha = linha.replaceFirst(substring, substring2);
				}
				
				//linha = linha.replaceAll("'", "");
				
				String[] donor = linha.split(",");
				String email = "";
				String registryDate = "";
	 			for(int x = 0; x < donor.length; x++) {
	 				String h = (x <= (header.length -1) ? header[x] : "");
	 				bodyEmail += "<p><b>"+ (IsNullOrIsEmpty(h) ? "Obs" :  h) +":</b> "+donor[x]+"</p>";
	 				if(findAttribute(h, "email"))
	 					email = donor[x];
	 				if(findAttribute(h, "Criado em"))
	 					registryDate = donor[x];
	 			}
	 			bodyEmail += " </div>";
	 			registryDate = IsNullOrIsEmpty(registryDate) ? DateUtils.formataData(new Date(), "yyyy-MM-dd HH:mm:ss") : 
	 				registryDate.length() == 25 ? registryDate.substring(0,19) : DateUtils.formataData(new Date(), "yyyy-MM-dd HH:mm:ss");
	 			bodyEmail.replaceAll("  ", " ");
	 			fc.insertIssueNew(bodyEmail, registryDate, email, ConnectionHelper.getPreparedStatement(null));	 			
			}
			
		}
		ConnectionHelper.close();
		return result;
	}
	
	private static String insertIssue() {
		String insert = "";
		
			insert = "INSERT Attendance.dbo.Issue( emailFrom, subject, registryDate, context, answered, fullfilled, tagType_id, serverEmail_id,	" +
					"serverEmailHeader, emailTo, identifiedTag_id, role_id, company_id, application_id, body ) " +
					"VALUES( " +
				"?,	" +	
				"'Doação Web TRACKMOBBI', " +
				"?, " +
				"1," +
				"0," +
				"0," +
				"1," +
				"NULL," +
				"NULL," +
				"'anjosemacao.trackmobbi@novotempo.com'," +
				"416," +
				"318," +
				"2," +
				"5," +
				"?)";
		return insert; 
	}
	
	private static String headerEmail() {
		String header =                                                                                                                    
				"<style>                                                                                                                                                      " +
			    "                                                                                                                                                             " +
			    "    html{                                                                                                                                                    " +
			    "        font-family: ''Trebuchet MS'', ''Lucida Sans Unicode'', ''Lucida Grande'', ''Lucida Sans'', Arial, sans-serif;                                       " +
			    "    }                                                                                                                                                        " +
			    "    #topDiv{                                                                                                                                                 " +
			    "        background-color: #3399cc;                                                                                                                           " +
			    "        padding: 2px;                                                                                                                                        " +
			    "        margin-bottom: 30px;                                                                                                                                 " +
			    "    }                                                                                                                                                        " +
			    "    .footer{                                                                                                                                                 " +
			    "        font-size:1.3em;                                                                                                                                     " +
			    "        text-align: right;                                                                                                                                   " +
			    "        margin-right: 50px;                                                                                                                                  " +
			    "        text-decoration: underline;                                                                                                                          " +
			    "    }                                                                                                                                                        " +
			    "    body{                                                                                                                                                    " +
			    "        padding: 0px;                                                                                                                                        " +
			    "        margin: 0px;                                                                                                                                         " +
			    "    }                                                                                                                                                        " +
			    "</style>                                                                                                                                                     " +
			    "<div id=''topDiv''>                                                                                                                                            " +
			    "	<h2 style=''text-align: center;''><span style=''color: white;''>  Importação de Doação via Arquivo </span></h2> " +
			    "</div>																																						  ";
		return header;
	}
	
	private static boolean findAttribute(String hearder, String whatfind) {
		if(IsNullOrIsEmpty(hearder))
			return false;
		if(IsNullOrIsEmpty(whatfind))
			return false;
		
		whatfind = whatfind.toLowerCase();
		hearder = hearder.toLowerCase();
		
		if(whatfind.equals(hearder))
			return true;
		else 
			return false;
	}
}
