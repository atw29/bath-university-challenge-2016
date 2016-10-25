import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//ONLY DID THE TITLE


public class Main {

	Scanner s = new Scanner(System.in); 
	URL url;
	String title;
	String titleOld; 
	String editReport = "";
	
    public static void main(String[] args) {
    	new Main();
    }
    
    public Main() {
    	initialOptions();
    }
    
    private void initialOptions(){
    	while(true){
    	System.out.println("1. Edit a web page");
    	System.out.println("2. Editing report");
    	System.out.println("3. Exit application");
    	
    	String input = s.nextLine();
    	switch (input){
    	case "1": 
    		edit();
    		optionsToEdit();
    		break;
    	case "2": 
    		editing();
    		break;
    	case "3": 
    		exitThis();
    	}
    	}
    }
    
    private void edit(){
    	System.out.println("Give URL");
    	try {
			url = new URL(s.nextLine());
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			StringBuilder response = new StringBuilder();
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			
			String webText = response.toString();
			///System.out.println(webText);
			
			extractTitle(webText);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException f) {
			
		}
    }
    
    private void optionsToEdit(){
    	//System.out.println("What would you like to edit?");
    	System.out.println("What would you like the title to be?");
    	String read = s.nextLine();
    	titleOld = title;
    	title = read;
    	
    	String editingString = "|" + url.toString() + "|TITLE|" + titleOld.toString() + "|" + title.toString() + "|";
    	System.out.println(editingString);
    	//editReport.concat(editingString);
    	editReport = editReport + editingString + "\n";
    }
    
    private void extractTitle(String input){
    	String regexString = Pattern.quote("<title>") + "(.*?)" + Pattern.quote("</title>");
    	Pattern pattern = Pattern.compile(regexString);
    	Matcher matcher = pattern.matcher(input);
    	
    	while(matcher.find()) {
    		title = matcher.group(1);
    	}
    	System.out.println(title);
    }
    
    private void extractDesc(String input){
    	String regexString = Pattern.quote("<title>") + "(.*?)" + Pattern.quote("</title>");
    	Pattern pattern = Pattern.compile(regexString);
    	Matcher matcher = pattern.matcher(input);
    	
    	while(matcher.find()) {
    		title = matcher.group(1);
    	}
    }
    
    private void extractKeywords(String input){
    	String regexString = Pattern.quote("<title>") + "(.*?)" + Pattern.quote("</title>");
    	Pattern pattern = Pattern.compile(regexString);
    	Matcher matcher = pattern.matcher(input);
    	
    	while(matcher.find()) {
    		title = matcher.group(1);
    	}
    }
    
    private void editing(){
    	System.out.println(editReport);
    }
    
    private void exitThis(){
    	System.out.println("Byw");
    	System.exit(0);
    }
    
}
