package mypackage;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.util.StringProvider;

public class SoccerCalcScreen extends MainScreen implements FieldChangeListener {

	ButtonField defenseButton;
	ButtonField offenseButton;
	private Bitmap backgroundBitmap;
	VerticalFieldManager verticalManager;
	RichTextField rtfHeading;
	Font fontHeading = null;
	 
    public SoccerCalcScreen() {
        super(MainScreen.NO_HORIZONTAL_SCROLL | MainScreen.USE_ALL_WIDTH | MainScreen.USE_ALL_HEIGHT | MainScreen.NO_VERTICAL_SCROLL);
        setTitle( "Soccer Calculator" );

        backgroundBitmap = Bitmap.getBitmapResource("background.jpg");
        verticalManager  = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH | VerticalFieldManager.USE_ALL_HEIGHT){
        	 public void paint(Graphics graphics)
             {
                 //Draw the background image and then call paint.
                 graphics.drawBitmap(0, 0, Display.getWidth(),Display.getHeight(), backgroundBitmap, 0, 0);
                 super.paint(graphics);
             }
        };
        
        rtfHeading = new RichTextField("Soccer Calculator", RichTextField.TEXT_ALIGN_HCENTER){
        	protected void paint(Graphics g){ 
                g.setColor(0xffffffff);
                super.paint(g);
            }
        };
        rtfHeading.setMargin(50, 50, 80, 50);
        fontHeading = getFontToDisplay("Times New Roman", 55);
        rtfHeading.setFont(fontHeading);
        verticalManager.add(rtfHeading);
       
        fontHeading = getFontToDisplay("Comic Sans MS", 30);
        defenseButton = new ButtonField( "Calculate Defense", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
        defenseButton.setChangeListener(this);
        defenseButton.setMargin(175, 35, 75, 45);
        defenseButton.setFont(fontHeading);
        verticalManager.add( defenseButton );
        
        offenseButton = new ButtonField( "Calculate Offense", ButtonField.CONSUME_CLICK | FIELD_HCENTER );
        offenseButton.setChangeListener(this);
        offenseButton.setMargin(175, 35, 75, 45);
        offenseButton.setFont(fontHeading);
        verticalManager.add( offenseButton );
       
        add(verticalManager);
        
        
       
    }
    
    public void fieldChanged(Field field, int context) {
        if(field == defenseButton)
        {
        	UiApplication.getUiApplication().pushScreen(new DefenseCalculationScreen());
        }
        else 
        {
        	UiApplication.getUiApplication().pushScreen(new OffenseCalculationScreen());
        }
    }

    protected void makeMenu( Menu menu, int instance ) {
    	super.makeMenu(menu, instance);
        MenuItem mntm = new NewMenuItem();
        menu.add( mntm );
    }

    private class NewMenuItem extends MenuItem {
        public NewMenuItem() {
            super( new StringProvider( "Choose" ), 0, 0 );
        }

        public void run() {
        	chooseOption();
        }
    }

    private void chooseOption() {
    	
    	Dialog.inform("Please Select a Option and Continue!!!!");
       
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
