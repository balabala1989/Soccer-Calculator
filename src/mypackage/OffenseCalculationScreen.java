package mypackage;

import javax.microedition.global.Formatter;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

public class OffenseCalculationScreen extends MainScreen implements FieldChangeListener{

	/**
	 * 
	 */
	
	private Bitmap backgroundBitmap;
	VerticalFieldManager verticalManager;
	RichTextField rtfHeading;
	Font fontHeading = null;
	private EditField shotsOnGoalEdit;
	private EditField goalsScoredEdit;
	private EditField gamesWonEdit;
	private EditField gamesLostEdit;
	private LabelField shotsOnGoalLabel;
	private LabelField goalsScoredLabel;
	private LabelField gamesWonLabel;
	private LabelField gamesLostLabel;
	ButtonField calculateButton;
	ButtonField backButton;
	Border roundedBorder;
	
	
	public OffenseCalculationScreen() {
		

		

		super(MainScreen.NO_HORIZONTAL_SCROLL | MainScreen.USE_ALL_WIDTH | MainScreen.USE_ALL_HEIGHT | MainScreen.NO_VERTICAL_SCROLL);
        setTitle( "Ice Hockey Calculator" );
        roundedBorder = BorderFactory.createRoundedBorder(new XYEdges(10,10,10,10), Color.WHITE, Border.STYLE_FILLED);
        
        backgroundBitmap = Bitmap.getBitmapResource("background.jpg");
        verticalManager  = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH | VerticalFieldManager.USE_ALL_HEIGHT){
        	 public void paint(Graphics graphics)
             {
                 //Draw the background image and then call paint.
                 graphics.drawBitmap(0, 0, Display.getWidth(),Display.getHeight(), backgroundBitmap, 0, 0);
                 super.paint(graphics);
             }
        };
        
        rtfHeading = new RichTextField("Calculate Offense Statistics", RichTextField.TEXT_ALIGN_HCENTER){
        	protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
        };
        rtfHeading.setMargin(20, 50, 20, 50);
        fontHeading = getFontToDisplay("Times New Roman", 55);
        rtfHeading.setFont(fontHeading);
        verticalManager.add(rtfHeading);
        
        shotsOnGoalEdit = new EditField("","",10, TextField.NO_LEARNING | TextField.NO_NEWLINE | TextField.NON_SPELLCHECKABLE ){
            protected void paint(Graphics graphics) {
                graphics.clear();
                graphics.setBackgroundColor( Color.WHITE );
         
                super.paint(graphics);
              }
            };
            shotsOnGoalEdit.setBorder(roundedBorder);
            shotsOnGoalEdit.setMargin(10,10,20,10);
	    shotsOnGoalLabel = new LabelField( "Scoring Attempts (Shots on Goal)" ){
            public void paint(Graphics g ){
                g.setColor(Color.WHITE);
                super.paint(g);
              }
            };
            shotsOnGoalLabel.setFont( Font.getDefault().derive(Font.EMBOSSED_EFFECT,25,Ui.UNITS_px) );   
		verticalManager.add(shotsOnGoalLabel);
		verticalManager.add(shotsOnGoalEdit);
		
		goalsScoredEdit = new EditField("","",10, TextField.NO_LEARNING | TextField.NO_NEWLINE | TextField.NON_SPELLCHECKABLE ){
            protected void paint(Graphics graphics) {
                graphics.clear();
                graphics.setBackgroundColor( Color.WHITE );
         
                super.paint(graphics);
              }
            };
            goalsScoredEdit.setBorder(roundedBorder);
            goalsScoredEdit.setMargin(10,10,20,10);
	    goalsScoredLabel = new LabelField( "Goals Scored" ){
            public void paint(Graphics g ){
                g.setColor(Color.WHITE);
                super.paint(g);
              }
            };
            goalsScoredLabel.setFont( Font.getDefault().derive(Font.EMBOSSED_EFFECT,25,Ui.UNITS_px) );   
		verticalManager.add(goalsScoredLabel);
		verticalManager.add(goalsScoredEdit);
		
				
		gamesWonEdit = new EditField("","",10, TextField.NO_LEARNING | TextField.NO_NEWLINE | TextField.NON_SPELLCHECKABLE ){
            protected void paint(Graphics graphics) {
                graphics.clear();
                graphics.setBackgroundColor( Color.WHITE );
         
                super.paint(graphics);
              }
            };
            gamesWonEdit.setBorder(roundedBorder);
            gamesWonEdit.setMargin(10,10,20,10);
	    gamesWonLabel = new LabelField( "Team Games Won" ){
            public void paint(Graphics g ){
                g.setColor(Color.WHITE);
                super.paint(g);
              }
            };
            gamesWonLabel.setFont( Font.getDefault().derive(Font.EMBOSSED_EFFECT,25,Ui.UNITS_px) );   
		verticalManager.add(gamesWonLabel);
		verticalManager.add(gamesWonEdit);
		
		
		gamesLostEdit = new EditField("","",10, TextField.NO_LEARNING | TextField.NO_NEWLINE | TextField.NON_SPELLCHECKABLE ){
            protected void paint(Graphics graphics) {
                graphics.clear();
                graphics.setBackgroundColor( Color.WHITE );
         
                super.paint(graphics);
              }
            };
            gamesLostEdit.setBorder(roundedBorder);
            gamesLostEdit.setMargin(10,10,20,10);
	    gamesLostLabel = new LabelField( "Team Games Lost" ){
            public void paint(Graphics g ){
                g.setColor(Color.WHITE);
                super.paint(g);
              }
            };
            gamesLostLabel.setFont( Font.getDefault().derive(Font.EMBOSSED_EFFECT,25,Ui.UNITS_px) );   
		verticalManager.add(gamesLostLabel);
		verticalManager.add(gamesLostEdit);
		
				
	        fontHeading = getFontToDisplay("Comic Sans MS", 30);
	        calculateButton = new ButtonField( "Calculate", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
	        calculateButton.setChangeListener(this);
	        calculateButton.setFont(fontHeading);
	        calculateButton.setMargin(40, 40, 20, 80);
	        
	        backButton = new ButtonField( "Back", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
	        backButton.setChangeListener(this);
	        backButton.setFont(fontHeading);
	        backButton.setMargin(40, 40, 20, 80);
	        
	        
	        
	        HorizontalFieldManager horizontalManager = new HorizontalFieldManager(USE_ALL_WIDTH);
	        horizontalManager.add(calculateButton);
	        horizontalManager.add(backButton);
		verticalManager.add(horizontalManager);
		add(verticalManager);
	
		
		
	
		
	}
	
	
	public void fieldChanged(Field field, int context) {

		double shotsOnGoal = 0;
		double goalsScored = 0;
		double gamesWon = 0;
		double gamesLost = 0;
		
		double gamesTotal = 0;
		double scoringPercentage = 0;
		double scoringRatio = 0;
		double goalieWonPercentage = 0;
		if(field == calculateButton)
		{
			if(calculateButton.getLabel().equals("Calculate"))
			{
				calculateButton.setLabel("Reset");
				shotsOnGoalEdit.setEditable(false);
				goalsScoredEdit.setEditable(false);
				gamesWonEdit.setEditable(false);
				gamesLostEdit.setEditable(false);
				try {
					shotsOnGoal = Long.parseLong(shotsOnGoalEdit.getText());
					goalsScored = Long.parseLong(goalsScoredEdit.getText());
					gamesWon = Long.parseLong(gamesWonEdit.getText());
					gamesLost = Long.parseLong(gamesLostEdit.getText());
					if(shotsOnGoal == 0)
					{
						Dialog.inform("Shots on Goal cannot be zero");
					}
					else if(goalsScored == 0)
					{
						Dialog.inform("Goals Scored cannot be zero");
					}
					else if(gamesWon == 0 && gamesLost == 0)
					{
						Dialog.inform("Games Won in Goal and Games Lost in Goal both cannot be zero");
					}
					else if(shotsOnGoal < 0 || goalsScored < 0 || gamesWon < 0 || gamesLost < 0 )
					{
						Dialog.inform("Please Enter value greater than 0");
					}
					else if(shotsOnGoal < goalsScored)
					{
						Dialog.inform("Shots on Goal should be greater than Goals Scored");
					}
					else
					{
						gamesTotal = gamesWon + gamesLost;
						goalieWonPercentage = gamesWon / gamesTotal;
						scoringPercentage = (shotsOnGoal - goalsScored) / shotsOnGoal;
						scoringRatio = shotsOnGoal / goalsScored;
						Formatter format = new Formatter();
						
						StringBuffer stResult = new StringBuffer();
						stResult.append("Scoring Percentage : " + format.formatNumber(scoringPercentage, 4) + "\n\n" +
								"Scoring Ratio : " + format.formatNumber(scoringRatio, 4) + "\n\n" +
								"Games Won Percentage : " + format.formatNumber(goalieWonPercentage,4));
						IntermediateScreen.setStResultData(stResult.toString());
						IntermediateScreen.setStResultHeading("Offense Calculation Results");
						UiApplication.getUiApplication().pushScreen(new ResultScreen());
					}
				} catch (NumberFormatException e) {
					
					Dialog.inform("Please Enter Number!!!!");
					
				}
				catch (ArithmeticException e) {
					
						Dialog.inform("Error!!!! Please try after some time");
						UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());

				}
				catch (Exception e) {

					Dialog.inform("Error!!!! Please try after some time");
					UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());

				}
				
			}
			else
			{
				calculateButton.setLabel("Calculate");
				shotsOnGoalEdit.setText("");
				goalsScoredEdit.setText("");
				gamesWonEdit.setText("");
				gamesLostEdit.setText("");
				shotsOnGoalEdit.setEditable(true);
				goalsScoredEdit.setEditable(true);
				gamesWonEdit.setEditable(true);
				gamesLostEdit.setEditable(true);
			}
		}
		else if(field == backButton)
		{
			UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
		}
		
	}
	
	private Font getFontToDisplay(String stFontName, int fontSize)
    {
    	try
        {
            FontFamily ff1 = FontFamily.forName(stFontName);
            fontHeading = ff1.getFont(Font.ITALIC | Font.EXTRA_BOLD , fontSize);
            return fontHeading;
        }
        catch (Exception e) {
			e.printStackTrace();
			Dialog.inform("Error Occurred. Please try after some time");
			return null;
		}
    }
    
    protected boolean onSavePrompt() {
        return true;
    }

}
