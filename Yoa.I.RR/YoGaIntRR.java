import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import YoussefGamil.*;

public class YoGaIntRR extends MIDlet implements CommandListener
{
 private Display display;
 private TextBox tb;
 private String eg="2,4,8";
 private Command ok=new Command("OK",Command.SCREEN,1)
                ,rest=new Command("Rest",Command.SCREEN,2);
 private void setTB(String txt)
 {
  tb= new TextBox("YoGa.IntRatio:",txt, 100, TextField.ANY);
  tb.addCommand(ok);
  tb.addCommand(rest);
  tb.setCommandListener(this);
  display.setCurrent(tb);
 }
 public void startApp()
 {
  display=Display.getDisplay(this);
  setTB(eg);
 }
 public void pauseApp()
 {
 }
 public void destroyApp(boolean unconditional)
 {
 }
 public void commandAction(Command c, Displayable d)
 {
  if(c==ok)
  {
   eg=tb.getString();
   int temp[]=new yssef_search(tb.getString()).intArrayForm(",",0,tb.getString().length());
   String t=new yssef_search("").getArrayForm(",",new yssef_int().toSimplestForm(temp));
   setTB(t);
  }
  else
  {
   setTB(eg);
  }
 }
}