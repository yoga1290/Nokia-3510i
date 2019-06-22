import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.microedition.io.file.*;
import java.io.*;
import YoussefGamil.*;
//MADE as an MIDlet on 24/1/2006
 // made on 13/1/2006
 // developed on 14/1/2006
public class YoGaGraph extends MIDlet implements CommandListener
{
 private int line_XPOS[],line_YPOS[],maxY,maxX,minX,minY,W,H;
 private Display dis;
 private TextBox tb,dem,tb_file;
 private Command cmExit,cmShow,cmMore,cmOK,cmSave,cmLoad,cmFile,cmImage;
 private yssef_intArray intArray_XPOS,intArray_YPOS;
 private void showtb_file()
 {
  tb_file=new TextBox("File Path:","file:///e:/",30,0);
  cmSave=new Command("Save File",Command.SCREEN,1);
  cmLoad=new Command("Load File",Command.SCREEN,1);
  cmImage=new Command("Save as an Image File",Command.SCREEN,1);
  tb_file.addCommand(cmSave);
  tb_file.addCommand(cmImage);
  tb_file.addCommand(cmLoad);
  tb_file.setCommandListener(this);
  dis.setCurrent(tb_file);
 }
 public void startApp()
 {
  dis=Display.getDisplay(this);
  intArray_XPOS=new yssef_intArray();
  intArray_YPOS=new yssef_intArray();
  tb=new TextBox("Insert new Point (X,Y):","0,0",20,0);
//   tb=new TextBox("Insert new Point (X,Y):");
   cmShow=new Command("Show",Command.SCREEN,1);
   cmFile=new Command("Save/Load File",Command.SCREEN,1);
   cmMore=new Command("Add",Command.SCREEN,1);
   tb.addCommand(cmShow);
   tb.addCommand(cmMore);
   tb.addCommand(cmFile);
   tb.setCommandListener(this);

  dem=new TextBox("Enter Width,Hieght:","50,80",20,0);
// dem=new TextBox("Enter Width,Hieght:");
   cmExit=new Command("Exit",Command.EXIT,0);
   cmOK=new Command("OK",Command.SCREEN,1);
   dem.addCommand(cmOK);
   dem.addCommand(cmExit);
   dem.setCommandListener(this);
 
  dis.setCurrent(dem);
 }
 public void pauseApp()
 {
 }
 public void destroyApp(boolean unconditional)
 {
 }
 public void commandAction(Command c,Displayable s)
 {
  if(c==cmMore)
  {
   int t[]=new yssef_search(tb.getString()).intArrayForm(",",0,tb.getString().length());
   intArray_XPOS.add(t[0]);
   intArray_YPOS.add(t[1]);
   dis.setCurrent(tb);
  }
  if(c==cmFile)
  {
   showtb_file();
  }
  if(c==cmShow)
  {
   dis.setCurrent(new graph(intArray_XPOS.getArray(),intArray_YPOS.getArray(),W,H));
  }
  if(c==cmOK)
  {
   int t[]=new yssef_search(dem.getString()).intArrayForm(",",0,dem.getString().length());
   W=t[0];H=t[1];
   dis.setCurrent(tb);
  }
  if(c==cmSave)
  {
   String txttemp="";
   txttemp+="var xpos=new Array("+new yssef_search().getArrayForm(",",intArray_XPOS.mainAr)+"); ";
   txttemp+="var ypos=new Array("+new yssef_search().getArrayForm(",",intArray_YPOS.mainAr)+");";
   fiosave(tb_file.getString(),txttemp.getBytes());
   dis.setCurrent(tb);
  }
  if(c==cmImage)
  {
  // Canvas CanImage=new graph(intArray_XPOS.getArray(),intArray_YPOS.getArray(),W,H);
  // Image img=CanImage.createImage(W,H);
  // new fcop(tb_file.getString()).fiosave(img.getBytes());
  }
  if(c==cmLoad)
  {
   String txttemp="";
   fioload(tb_file.getString());
   yssef_search temp_yoga=new yssef_search(txttemp);
   intArray_XPOS.mainAr=temp_yoga.intArrayForm(",","xpos=(",");");
   intArray_YPOS.mainAr=temp_yoga.intArrayForm(",","ypos=(",");");
   dis.setCurrent(tb);
  }
  if(c==cmExit)
  {
  // destroyApp(false);
 //  notifyDestroyed();
  }
 }

 private String fioload(String file) throws SecurityException
 {
  String txt="";
  try {
     FileConnection fc=(FileConnection) Connector.open(file, Connector.READ);
     InputStream is = fc.openInputStream();
     int ch;
    // int length=0;
    // byte bff[]=new byte[2];
     ch=is.read();
     while( (ch!=-1))
     {
      txt+=(char) ch;
      ch=is.read();
     }
      is.close();
      fc.close();
     return txt;
   }catch(Exception e){e.printStackTrace();Alert alert=new Alert("Error","IOReading:"+e,null,AlertType.ERROR);dis.setCurrent(alert);}
   return txt;
 }
 private void fiosave(String file,byte fb[]) throws SecurityException
 {
   try{
    FileConnection fc=(FileConnection) Connector.open(file);   // , Connector.WRITE);
    OutputStream os;
    if(!fc.exists())
    {
     fc.create();        
    }
    os = fc.openOutputStream();      
    os.write(fb);
    os.close();
    fc.close();
   }catch(Exception e){e.printStackTrace();}
  }
 
}

class graph extends Canvas
{
 public int XPOS[],YPOS[],H,W,maxX,maxY,minY,minX,line_XPOS[],line_YPOS[];
 graph(int XPOS[],int YPOS[],int H,int W)
 {
  this.XPOS=XPOS;
  this.YPOS=YPOS;
  this.H=H;
  this.W=W;
 }
 private int[] movePos(int para[])
 {
  int arr[]=new yssef_intArray(para).arrange();
  if(arr[0]<0)
  {
   int miniPos=arr[0];
   miniPos*=-1;
   for(int i=0;i<para.length;i++)
   {
    if(para[i]==arr[0])
    {
     para[i]=0;
    }
    else
    {
     para[i]+=miniPos;
    }
   }
  }
  return para;
 }
 public void paint(Graphics g)
 {
  XPOS=new yssef_int().gcdR(XPOS);
  YPOS=new yssef_int().gcdR(YPOS);
  XPOS=movePos(XPOS);
  YPOS=movePos(YPOS);
  line_XPOS=new int[XPOS.length];
  line_YPOS=new int[XPOS.length];
  int temp[]=new yssef_intArray(XPOS).arrange();
   maxX=temp[(XPOS.length-1)];
   minX=new yssef_int().toPos(temp[0]);
  if(temp[0]<0)
  {
   W=W-minX;
  }
  temp=new yssef_intArray(YPOS).arrange();
  maxY=temp[(YPOS.length-1)];
  minY=new yssef_int().toPos(temp[0]);
  if(temp[0]<0)
  {
   H=H-minY;
  }

  for(int i=0;i<line_XPOS.length;i++)
  {
   line_XPOS[i]=( XPOS[i] *(W/maxX) )+minX;
   line_YPOS[i]=(H-( YPOS[i] *(H/maxY) ) )+minY;
  }

   g.setColor(000,255,000);// (Color.green);
  g.drawLine(W+minX,0,W+minX,H+minY); // left line
  g.drawLine(0,H+minY,W+minX,H+minY); // down
  g.drawLine(0,0,W+minX,0); // up
  g.drawLine(0,0,0,H+minY); // right

   g.setColor(000,000,255);// (Color.black);
  for(int i=0;i<(line_XPOS.length-1);i++)
  {
   if((line_XPOS[i]<=minX) || (line_YPOS[i]<=minY))
    g.setColor(255,000,000); // (Color.red);
   else
    g.setColor(000,000,255);// (Color.black);
    g.drawLine(line_XPOS[i],line_YPOS[i],line_XPOS[(i+1)],line_YPOS[(i+1)]);
  }
 }
}