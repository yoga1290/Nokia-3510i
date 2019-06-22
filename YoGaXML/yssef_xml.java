import YoussefGamil.*;
public class yssef_xml
{
 public String txt;
 public int lvlInd=-1;

 public yssef_array tags=new yssef_array(),tagdesc=new yssef_array(),tagProp=new yssef_array();
 public int i=0,found=-2;
 public yssef_intArray tagsInd=new yssef_intArray(),tagScope=new yssef_intArray(),tagdescInd=new yssef_intArray();
 public final String ending[]={" ",">"};
 public String tagName="";
 public int tagFin[]={0,0},startTagInd[]={0,0},st=0;

 public yssef_xml(String txt)
 {
  this.txt=txt;
 }
 public yssef_xml(String txt,int lvlInd,yssef_array tags,yssef_intArray tagsInd,yssef_intArray tagScope,yssef_intArray tagdescInd,yssef_array tagdesc,yssef_array tagProp)
 {
  this.txt=txt;
  this.tags=tags;
  this.tagScope=tagScope;
  this.tagsInd=tagsInd;
  this.lvlInd=lvlInd;
  this.tagdesc=tagdesc;
  this.tagProp=tagProp;
  this.tagdescInd=tagdescInd;
  setAll();
 }
 public void setTree()
 {
  if((startTagInd[1]!=-1) && (tagFin[1]>-1))
  {
   tags.add(tagName);
   tagScope.add(lvlInd);

   // if this tag doesn't contain any tag inside,get it's comments.22/7/2006:
   int temp=new yssef_search(this.txt).igsearch(">",startTagInd[1]-1,tagFin[0])[1];
   if( (new yssef_search(this.txt).igsearch("<",startTagInd[1],tagFin[0])[0]) == -1)
   {
   // int temp=new yssef_search(this.txt).igsearch(">",startTagInd[1]-1,tagFin[0])[1];
    tagdesc.add(new yssef_search(this.txt).getTxt(temp+1,tagFin[0]));
    tagdescInd.add(tagdesc.mainAr.length-1);
   }

   // add proprites:(25/7/2006)
   tagProp.add(new yssef_search(this.txt).getTxt(startTagInd[1],temp)+"");


  /** Set the tag Ind which this element belongs to **/
   i=this.tagScope.mainAr.length-1;
   found=-2;
   while((i>-1) && (found==-2))
   {
    if(this.tagScope.mainAr[i]<lvlInd )
    {
     tagsInd.add(i);
     found=i;
    }
    i--;
   }
   if(found==-2)
   {
    tagsInd.add(-1);
   }
   /** End of setting tagsInd **/

//   for(i=-1;i<lvlInd;i++) System.out.print(" ");
//   System.out.print("<"+tagName+"> on Scope Level "+ (lvlInd)+" belongs to "+ this.tagsInd.mainAr[this.tagsInd.mainAr.length-1]);
//   System.out.println();
   
   yssef_xml yy=new yssef_xml(new yssef_search(this.txt).getTxt(startTagInd[1],tagFin[1]),(lvlInd+1) ,this.tags,this.tagsInd,this.tagScope,this.tagdescInd,this.tagdesc,this.tagProp);
  /**
   this.tags=yy.tags;
   this.tagsInd=yy.tagsInd;
   this.tagScope=yy.tagScope;
   this.tagdesc=yy.tagdesc;
   this.tagdescInd=yy.tagdescInd; **/
   startTagInd=new yssef_search(this.txt).getTxtIndNear("<",ending,st,this.txt.length());
   if(startTagInd[1]>-1)
   {
    tagName=new yssef_search(this.txt).getTxt(startTagInd[0],startTagInd[1]);
    tagFin=new yssef_search(this.txt).igsearch("</"+tagName+">",startTagInd[1],this.txt.length());
    st=tagFin[1];
    setTree();
   }
  }
 }
 public void setAll()
 {
  startTagInd=new yssef_search(this.txt).getTxtIndNear("<",ending,st,this.txt.length());
  if(startTagInd[1]>-1)
  {
   tagName=new yssef_search(this.txt).getTxt(startTagInd[0],startTagInd[1]);
   tagFin=new yssef_search(this.txt).igsearch("</"+tagName+">",startTagInd[1],this.txt.length());
   st=tagFin[1];

   setTree();
  }
 }
}