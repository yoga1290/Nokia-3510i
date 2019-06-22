import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.microedition.io.file.*;
import java.io.*;
import YoussefGamil.*;

public class YoGaXML extends MIDlet implements CommandListener
{
 private int stage=-1,propTagInd=-1,i=0;
 private Display display=Display.getDisplay(this);
 private List menu,prop,roots,mainTagsMenu;
 private TextBox tb;
 private String currentFilePath="";
 private yssef_intArray tagsInd,tagdescInd,tagScope,mainTagsInd;
 private yssef_array tags,tagdesc,tagProp,AllCurrentProp;
 private int copy=-1,cut=-1;
 private yssef_intArray listedInd;
 private static final Command menu_up = new Command("Up", Command.SCREEN, 1)
         ,menu_delTag= new Command("Delete Tag", Command.SCREEN, 1)
         ,menu_add=new Command("Add Tag", Command.SCREEN, 1)
         ,menu_add_ok=new Command("OK", Command.SCREEN, 1)
         ,menu_editTag= new Command("Edit Tag name", Command.SCREEN, 1)
         ,menu_properties= new Command("Attributes", Command.SCREEN, 1)
         ,menu_open= new Command("Open Tag", Command.SCREEN, 1)
         ,tb_ok=new Command("OK", Command.SCREEN, 1)
         ,tb_can=new Command("Cancel", Command.SCREEN, 1)
         ,prop_back=new Command("Back", Command.SCREEN, 1)
         ,prop_edit=new Command("Edit Attribute", Command.SCREEN, 1)
         ,prop_del=new Command("Delete Attribute", Command.SCREEN, 1)
         ,prop_add=new Command("Add Attribute", Command.SCREEN, 1)
         ,prop_add_ok=new Command("OK", Command.SCREEN, 1)
         ,prop_add_can=new Command("Cancel", Command.SCREEN, 1)
         ,prop_edit_ok=new Command("OK", Command.SCREEN, 1)
         ,prop_edit_can=new Command("Cancel", Command.SCREEN, 1)
         ,fio_load=new Command("Load File", Command.SCREEN, 1)
         ,fio_save=new Command("Save File", Command.SCREEN, 1)
         ,fio_new=new Command("New File", Command.SCREEN, 1)
         ,fio_can=new Command("Cancel", Command.SCREEN, 1)
         ,roots_ok=new Command("OK", Command.SCREEN, 1)
         ,mainTagsMenu_add=new Command("Add main Tag", Command.SCREEN, 1)
         ,mainTagsMenu_add_ok=new Command("OK", Command.SCREEN, 1)
         ,mainTagsMenu_del=new Command("Delete Tag", Command.SCREEN, 1)
         ,mainTagsMenu_edit=new Command("Edit Tag", Command.SCREEN, 1)
         ,mainTagsMenu_edit_ok=new Command("OK", Command.SCREEN, 1)
         ,mainTagsMenu_open=new Command("Open", Command.SCREEN, 1);
 private Image tagIcon,attIcon;

 private String withoutspace(String txt)
 {
  txt.replace(' ','_');
  return txt;
 }
 public void startApp()
 {
  try
  {
   tagIcon=Image.createImage("/tag.png");
   attIcon=Image.createImage("/att.png");
  }catch(Exception e){}
  display=Display.getDisplay(this);
  roots=new List("Choose a root:", Choice.IMPLICIT);
  roots.append("CFCard/", tagIcon);
  roots.append("SDCard/", tagIcon);
  roots.append("MemoryStick/", tagIcon);
  roots.append("C:/", tagIcon);
  roots.append("MMC", tagIcon);
  roots.addCommand(roots_ok);
  roots.addCommand(tb_can);
  roots.setCommandListener(this);
  display.setCurrent(roots);
 }
 public void pauseApp()
 {
 }
 public void destroyApp(boolean unconditional)
 {
 }
 private void mainTags()
 {
  mainTagsInd=new yssef_intArray();
  mainTagsMenu=new List("Main Tags:", Choice.IMPLICIT);
  for(int i=0;i<tags.mainAr.length;i++)
  {
   if(tagsInd.mainAr[i]==-1)
   {
    mainTagsMenu.append(tags.mainAr[i], tagIcon);
    mainTagsInd.add(i);
   }
  }
  mainTagsMenu.addCommand(mainTagsMenu_open);
  mainTagsMenu.addCommand(mainTagsMenu_edit);
  mainTagsMenu.addCommand(mainTagsMenu_del);
  mainTagsMenu.addCommand(mainTagsMenu_add);
  mainTagsMenu.addCommand(fio_save);
  mainTagsMenu.setCommandListener(this);
  display.setCurrent(mainTagsMenu);
 }

 private String getParents(int ind)
 {
  int i=ind;
  String tot="";
  while(i!=-1)
  {
   tot=tags.mainAr[i]+"."+tot;
   i=tagsInd.mainAr[i];
  }
  return tot;
 }
 private void setProp(int ind)
 {
  propTagInd=ind;
  prop=new List(tags.mainAr[ind]+".Attributes:", Choice.IMPLICIT);
  String currentTagProp=tagProp.mainAr[ind];
  if( (currentTagProp) !="")
  {
   AllCurrentProp=new yssef_array(new yssef_search(currentTagProp).arrayForm(" ",0,currentTagProp.length()));
   for(int i=0;i<AllCurrentProp.mainAr.length;i++)
   {
    prop.append(AllCurrentProp.mainAr[i], attIcon);
   }
   prop.addCommand(prop_del);
   prop.addCommand(prop_edit);
  }
/**
  else
  {
   prop.append("NO Properties!",null);
   prop.addCommand(prop_back);
   prop.addCommand(prop_add);
  } **/
  prop.addCommand(prop_back);
  prop.addCommand(prop_add);
  prop.setCommandListener(this);
  display.setCurrent(prop);
 }
 private void setTree(int ind)
 {
  stage=ind;
  menu = new List(getParents(ind), Choice.IMPLICIT);
  listedInd=new yssef_intArray();
  for(int i=0;i<tagsInd.mainAr.length;i++)
  {
   if(tagsInd.mainAr[i]==ind)
   {
    listedInd.add(i);
    menu.append(tags.mainAr[i], tagIcon);
   }
  }
  menu.addCommand(menu_open);
  menu.addCommand(menu_up);
  menu.addCommand(menu_add);
  menu.addCommand(menu_editTag);
  menu.addCommand(menu_delTag);
  menu.addCommand(menu_properties);
  menu.addCommand(fio_save);
  menu.setCommandListener(this);
  display.setCurrent(menu);
 }
 
 private void del(int ind)
 {
  tags.drop(ind);
  tagsInd.drop(ind);
  for(int i=0;i<tagdesc.mainAr.length;i++)
  {
   if(tagdescInd.mainAr[i]==ind)
   {
    tagdescInd.drop(i);
    tagdesc.drop(i);
   }
  }
  for(i=0;i<tags.mainAr.length;i++)
  {
   if(tagsInd.mainAr[i]==ind)
   {
   // tags.drop(i);
   // tagsInd.drop(i);   // mot sure cuz Mihahiss
    del(i);
   }
  }
  setTree(stage); // high-scope
 }

 private String getTag(int ind)
 {
  String tot="",spaces="";
  for(int j=-1;j<tagScope.mainAr[ind];j++)
  {
   spaces+=" ";
  }
  tot+=spaces+"<"+tags.mainAr[ind]+" "+tagProp.mainAr[ind]+">";
  for(int i=0;i<tags.mainAr.length;i++)
  {
   if(tagsInd.mainAr[i]==ind) tot+=getTag(i);
  }
  tot+=spaces+"</"+tags.mainAr[ind]+">";
  return tot;
 }


 public void commandAction(Command c, Displayable d)
 {
  if(c==mainTagsMenu_open)
  {
   setTree(mainTagsInd.mainAr[mainTagsMenu.getSelectedIndex()]);
  }
  if(c==mainTagsMenu_add)
  {
   tb= new TextBox("Main Tag Name:", "YoGaSh", 50, TextField.ANY);
   tb.addCommand(mainTagsMenu_add_ok);
   tb.addCommand(tb_can);
   tb.setCommandListener(this);
   display.setCurrent(tb);
  }
  if(c==mainTagsMenu_add_ok)
  {
   tags.add(tb.getString());
   tagsInd.add(-1);
   tagScope.add(-1);
   tagProp.add("no");
   mainTags();
  }
  if(c==mainTagsMenu_edit)
  {
   tb= new TextBox("Edit Tag Name:", tags.mainAr[listedInd.mainAr[menu.getSelectedIndex()]], 50, TextField.ANY);
   tb.addCommand(mainTagsMenu_edit_ok);
   tb.addCommand(tb_can);
   tb.setCommandListener(this);
   display.setCurrent(tb);
  }
  if(c==mainTagsMenu_edit_ok)
  {
   tags.mainAr[mainTagsInd.mainAr[mainTagsMenu.getSelectedIndex()]]=tb.getString();
   mainTags();
  }
  if(c==mainTagsMenu_del)
  {
   del(mainTagsInd.mainAr[mainTagsMenu.getSelectedIndex()]);
   mainTags();
  }
  if(c==menu_delTag)
  {
   del(listedInd.mainAr[menu.getSelectedIndex()]);
   setTree(stage);
  }
  if(c==menu_open)
  {
   setTree(listedInd.mainAr[menu.getSelectedIndex()]);
  }
  if(c==menu_up)
  {
   if( (stage) !=-1)
   {
   // if( (tagsInd.mainAr[(tagsInd.mainAr[listedInd.mainAr[menu.getSelectedIndex()]])]) !=-1)
     setTree(tagsInd.mainAr[stage]); // high-scope
   }
   else
   {
    mainTags();
   }
  }
  if(c==menu_editTag)
  {
   tb= new TextBox("Edit Tag Name:", tags.mainAr[listedInd.mainAr[menu.getSelectedIndex()]], 50, TextField.ANY);
   tb.addCommand(tb_ok);
   tb.addCommand(tb_can);
   tb.setCommandListener(this);
   display.setCurrent(tb);
  }
  if(c==menu_add)
  {
   tb= new TextBox("Tag Name:", "YoGaSh", 50, TextField.ANY);
   tb.addCommand(menu_add_ok);
   tb.addCommand(tb_can);
   tb.setCommandListener(this);
   display.setCurrent(tb);
  }
  if(c==menu_add_ok)
  {
   tags.add(tb.getString());
   tagsInd.add(stage);
   tagScope.add(tagScope.mainAr[stage]+1);
   tagProp.add("no");
   setTree(stage);
  }
  if(c==tb_ok)
  {
   tags.mainAr[listedInd.mainAr[menu.getSelectedIndex()]]=tb.getString();
   setTree(stage);
  }
  if((c==tb_can) || (c==prop_back) )
  {
   display.setCurrent(menu);
  }
  if((c==prop_edit_can) || (c==prop_add_can) )
  {
   display.setCurrent(prop);
  }
  if(c==prop_del)
  {
   AllCurrentProp.drop(prop.getSelectedIndex());
   tagProp.mainAr[stage]=new yssef_search("").getArrayForm(" ",AllCurrentProp.mainAr);
   setProp(stage);
  }
  if(c==prop_edit)
  {
   tb= new TextBox("Edit Tag Propery:", AllCurrentProp.mainAr[prop.getSelectedIndex()], 50, TextField.ANY);
   tb.addCommand(prop_edit_ok);
   tb.setCommandListener(this);
   display.setCurrent(tb);
  }
  if(c==prop_edit_ok)
  {
   AllCurrentProp.mainAr[prop.getSelectedIndex()]=tb.getString();
   tagProp.mainAr[stage]=new yssef_search("").getArrayForm(" ",AllCurrentProp.mainAr);
   setProp(stage);
  }
  if(c==prop_add)
  {
   tb= new TextBox("Add Tag Propery:", "Property=yes", 50, TextField.ANY);
   tb.addCommand(prop_add_ok);
   tb.setCommandListener(this);
   display.setCurrent(tb);
  }
  if(c==prop_add_ok)
  {
   AllCurrentProp.add(tb.getString());
   tagProp.mainAr[stage]=new yssef_search("").getArrayForm(" ",AllCurrentProp.mainAr);
   setProp(stage);
  }
  if(c==roots_ok)
  {
   String rr[]={"file:///CFCard/","file:///SDCard/","file:///MemoryStick/","file:///c:/","file:///e:/"};
   tb= new TextBox("File Path:", rr[roots.getSelectedIndex()], 50, TextField.ANY);
   tb.addCommand(fio_new);
   tb.addCommand(fio_load);
   tb.setCommandListener(this);
   display.setCurrent(tb);
  }
  if(c==menu_properties)
  {
   setProp(stage);
  }
  if(c==fio_new)
  {
   currentFilePath=tb.getString();
   this.tagsInd=new yssef_intArray(-1);
   this.tagProp=new yssef_array("Property=no");
   this.tagdesc=new yssef_array("YoGaSh-Comments");
   this.tagScope=new yssef_intArray(-1);
   this.tagdescInd=new yssef_intArray(0);
   this.tags=new yssef_array("YoGaSh");
   mainTags();
  }
  if(c==fio_save)
  {
    String ftxt=""; // ="<?xml version=\"1.0\" ?>";
    for(int i=0;i<tags.mainAr.length;i++)
    {
     if(tagsInd.mainAr[i]==-1)
     {
      ftxt+=getTag(i);
     }
    }
   fiosave(currentFilePath,ftxt);
  }
  if(c==fio_load)
  {
   fioload();
   mainTags();
  }
 }
 private void fioload() // throws SecurityException
 {
  currentFilePath=tb.getString();
  String txt="";
  try {
     FileConnection fc=(FileConnection) Connector.open(currentFilePath, Connector.READ);
     InputStream is = fc.openInputStream();
    // DataInputStream in=new DataInputStream(is);
     int ch;
     int length=0;
     byte bff[]=new byte[2];
    // long overallsize=fc.fileSize();
     ch=is.read();
     while( (ch!=-1))
     {
      txt+=(char) ch;
      ch=is.read();
     // length+=is.read(bff);
     // txt+=bff.toString();
     }
      is.close();
      fc.close();
   }catch(Exception e){e.printStackTrace();Alert alert=new Alert("Error","IOReading:"+e,null,AlertType.ERROR);display.setCurrent(alert);}

   // fiosave(currentFilePath+"2",txt);
    if(txt.indexOf("?>")!=-1)
      txt=new yssef_search(txt).getTxt(txt.indexOf("?>"),txt.length());
    yssef_xml yxml=new yssef_xml(txt,-1,new yssef_array(),new yssef_intArray(),new yssef_intArray(),new yssef_intArray(),new yssef_array(),new yssef_array());
     this.tagsInd=yxml.tagsInd;
     this.tagProp=yxml.tagProp;
    // this.tagdesc=yxml.tagdesc;
     this.tagScope=yxml.tagScope;
     this.tagdescInd=yxml.tagdescInd;
     this.tags=yxml.tags;
 // e.printStackTrace();
 }
 private void fiosave(String file,String txt) throws SecurityException
 {
   try{
    FileConnection fc=(FileConnection) Connector.open(file);   // , Connector.WRITE);
    OutputStream os;
    if(!fc.exists())
    {
     fc.create();        
    }
    os = fc.openOutputStream();      
    os.write(txt.getBytes());
    os.close();
    fc.close();
    mainTags();
   }catch(Exception e){e.printStackTrace();Alert alert=new Alert("Error","IOwriting:"+e,null,AlertType.ERROR);display.setCurrent(alert);}
 }

}