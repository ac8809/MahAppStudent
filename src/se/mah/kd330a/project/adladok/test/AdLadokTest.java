package se.mah.kd330a.project.adladok.test;

import java.util.Observable;
import java.util.Observer;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.adladok.model.Course;
import se.mah.kd330a.project.adladok.model.Me;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdLadokTest extends Activity implements Observer{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad_ladok_test);
		Me.observable.addObserver(this);
	}
	
	public void update(View v){
		//clear all
		Me.setDispayName("");
		Me.setEmail("");
		Me.setFirstName("");
		Me.setIsStudent(false);
		Me.setIsStaff(false);
		Me.setLastName("");
		Me.setPassword("");
		Me.setUserID("");
		Me.clearCourses();
		//First we have to set username and password those should are probably be saved in Sharedprefs
		EditText userID_et =(EditText)findViewById(R.id.userIDet);
		EditText password_et =(EditText)findViewById(R.id.passWordET);
		Me.setUserID(userID_et.getText().toString());
		Me.setPassword(password_et.getText().toString());
		TextView tv = (TextView)findViewById(R.id.tvadladoktest);
		tv.setText("");
		//Then call to update
	     Me.updateMe();	     
	     
	}

	@Override
	public void update(Observable observable, Object data) {
		TextView tv = (TextView)findViewById(R.id.tvadladoktest);
		String courses = null;
		for (Course c : Me.getCourses()) {
			if (courses == null){
				courses = "course: \n"+c.getDisplayname()+ "," + c.getCourseID()+"\n";
			}else{
				courses = courses + "course: \n"+c.getDisplayname()+ "," + c.getCourseID()+"\n";
			}
			
		}
	     tv.setText("Me\n"+
	    		 	"FirstName: "+ Me.getFirstName()+"\n"+
	    		    "GivenName: "+ Me.getLastName()+"\n"+
	    		    "Displayname: "+ Me.getDispayName()+"\n"+
	    		    "Email: "+ Me.getEmail()+"\n"+
	    		    "UserID: "+ Me.getUserID()+"\n"+
	    		    "***Courses for Me***\n"+
	    		    courses);
	}
	


}