import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
	private Label l1,l2;
	private TextField txt;
	private Button button;
	public static boolean isFirst(char tmp) {
		if(tmp=='M'||tmp=='C'||tmp=='X'||tmp=='I') {
			return true;
		}
		return false;
	}
	public static boolean isSecond(char tmp) {
		if(tmp=='V'||tmp=='L'||tmp=='D') {
			return true;
		}
		return false;
	}
	public static void checkFirstValid(ArrayList<Character> arr) {
		for(int i = 1; i<(arr.size()-2); i++) {
			if(isFirst(arr.get(i))&&arr.get(i-1)==arr.get(i)&&arr.get(i)==arr.get(i+1)&&arr.get(i+1)==arr.get(i+2)) {
				System.out.println("Err1");
				throw new IllegalArgumentException();
			}
		}
	}
	
	public static void checkSecondValid(ArrayList<Character> arr) {
		for(int i = 1; i<arr.size(); i++) {
			if(isSecond(arr.get(i))&&arr.get(i-1)==arr.get(i)) {
				System.out.println("Err2");
				throw new IllegalArgumentException();
			}
		}
	}
	
	public static int toNum(char ch) {
		if(ch=='I') {return 1;}
		else if(ch=='V') {return 5;}
		else if(ch=='X') {return 10;}
		else if(ch=='L') {return 50;}
		else if(ch=='C') {return 100;}
		else if(ch=='D') {return 500;}
		else if(ch=='M') {return 1000;}
		return 0;
	}
	
	public static int solve(ArrayList<Character> arr) {
		int result = 0;
		for(int i = 0; i< arr.size();i++) {
			if(i!=(arr.size()-1)) {
				if(toNum(arr.get(i))<toNum(arr.get(i+1))) {
					result+=toNum(arr.get(i+1));
					result-=toNum(arr.get(i));
					i++;
				}
				else {
					result+=toNum(arr.get(i));
				}
			}
			else {
				result+=toNum(arr.get(i));
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {  
		stage.setTitle("Roman Numeric System");
		VBox panel = new VBox(); 
		txt = new TextField();
		txt.setPrefWidth(200);
		txt.setEditable(true);
		txt.setAlignment(Pos.CENTER);
		l1 = new Label("Insert");
		l2 = new Label("");
		l1.setMinWidth(200);
		l2.setMinWidth(200);
		l1.setAlignment(Pos.CENTER);
		l2.setAlignment(Pos.CENTER);
		button = new Button("Ok");
		button.setMinSize(200, 100);
		button.setOnAction(this);
		panel.getChildren().addAll(l1,txt,button,l2);
        Scene scene = new Scene(panel,200,160);
		stage.setScene(scene);
		stage.show();
	}
	public void handle(ActionEvent event) {
		String tmp = txt.getText();
		tmp = tmp.toUpperCase();
		ArrayList<Character> arr= new ArrayList<Character>();
		for(int i = 0;i<tmp.length();i++) {
			arr.add(tmp.charAt(i));
		}
		checkFirstValid(arr);
		checkSecondValid(arr);
		int result = solve(arr);
		l2.setText(String.valueOf(result));
	}

}
