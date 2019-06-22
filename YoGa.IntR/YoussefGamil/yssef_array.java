package YoussefGamil;
/**
 This class was established by Youssef MAI Gamil
 on 6/6/2005.
  Last updates:
   7/6/2005:
      -The function [void add(String[])] was created.
      -The class uses the first object on the Array [mainAr] with [null] to understand it is the first opening.
   9/6/2005:
      -The functon [int getLength()] was created.
      -The functions [int howm(String)]&[int[] getIndexs(String)] were created.
   12/6/2005:
         -The Function [int getLength()] was updated to return {0} if [firstTime]=0.
   14/6/2005:(from a look in [yssef_array])
         -The Function [int getByIndex(int)] was created.
         -The Function [void edit(String,int)] was created.
   15/6/2005:
         -The Function [void drop(int)] was created.
   25/6/2005:
         -The Function [void replace(int,int)] was created.
   27/6/2005:
         -The Function [int[] intoInt()] was created.
   20/9/2005:
         -The Function [String[] uniqe()]was created.
**/
public class yssef_array
{
 public String[] mainAr={null},tempar={null};
 public int firstTime=0;
 public yssef_array()
 {
 }
 public yssef_array(String txt)
 {
  mainAr[0]=txt;
  firstTime=-1;
 }

 public yssef_array(String a[]) /** copy the Array [a] to the main Array [mainAr] **/
 {
  mainAr=new String[a.length];
  for(int i=0;i<a.length;i++)
  {
   mainAr[i]=a[i];
  }
  firstTime=-1;
 }
 public void replace(String n,String news)
 {
  for(int i=0;i<mainAr.length;i++)
  {
   if(mainAr[i]==n)
    mainAr[i]=news;
  }
 }
 public void copy()
 {
  int i=0;
  tempar=new String[(mainAr.length)]; /** {mainAr.length-1} because [mainAr.length] starts counting from 1 not 0 **/
  while(i!=mainAr.length)
  {
   tempar[i]=mainAr[i];
   i++;
  }  
 }
 public void add(String txt[])
 {
  if(firstTime!=0)  
  {
   copy();
   mainAr=new String[(tempar.length+txt.length)];
   /** [mainAr.length] starts counting from 1 not 0 and I did that because [mainAr] needs to be increased by one element which in [txt] **/
   int i=0;
   int txtCounter=0;
   while(i!=(tempar.length+txt.length))
   {
    if( (i>=(tempar.length)) )
    {
     mainAr[i]=txt[txtCounter];
     txtCounter++;
    }
    else
    {
     mainAr[i]=tempar[i];
    }
    i++;
   }
  }
  else
  {
   mainAr=new String[txt.length];
   for(int i=0;i<txt.length;i++)
   {
    mainAr[i]=txt[i];
   }
   firstTime=-1;
  }
 }
 public void add(String txt)
 {
  if(firstTime!=0)
  {
   copy();
   mainAr=new String[(tempar.length+1)];
   /** [mainAr.length] starts counting from 1 not 0 and I did that because [mainAr] needs to be increased by one element which in [txt] **/
   int i=0;
   while(i!=mainAr.length)
   {
    if(i>=(tempar.length))
      mainAr[i]=txt;
    else
     mainAr[i]=tempar[i];
    i++;
   }
  }
  else
  {
   mainAr[0]=txt;
   firstTime=-1;
  }
 }
 public int howm(String n)
 {
  int tot=0;
  for(int i=0;i<mainAr.length;i++)
  {
   if(mainAr[i]==n) tot++;
  }
  return tot;
 }
 public int[] getIndexOf(String n)  /** return the indexs in which the [n] was repeated in the Array [mainAr] **/  
 {
  int tot[]=new int[howm(n)],count=0;
  for(int i=0;i<mainAr.length;i++)
  {
   if(mainAr[i]==n)
   {
    tot[count]=i;
    count++;
   }
  }
  return tot;
 }
 public void drop(int n)
 {
  copy();
  int count=0;
  mainAr=new String[(tempar.length-1)];
  for(int i=0;i<tempar.length;i++)
  {
   if(i!=n)
   {
    mainAr[count]=tempar[i];
    count++;
   }
  }
 }
 public void edit(String n,int i)
 {
  mainAr[i]=n;
 }
 public String getByIndex(int i)
 {
  return mainAr[i];
 }
 public int getLength()
 {
  int tot=0;
  if(firstTime!=0)
   tot=mainAr.length;
  return tot;
 }
 public String[] getArray()
 {
  return mainAr;
 }
 public String[] uniqe()  // Return String[] contains un-repeated items
 {
  yssef_array tot=new yssef_array();
  for(int i=0;i<mainAr.length;i++)
  {
   int len=mainAr.length;
   int c=-1;
   for(int i2=(i+1);i2<mainAr.length;i2++)
   {
    if(mainAr[i]==mainAr[i2]) c++;
   }
   if(c==-1) tot.add(mainAr[i]);
  }
  return tot.getArray();
 }
 public int[] intoInt()
 {
  int[] tot=new int[mainAr.length];
  for(int i=0;i<tot.length;i++)
  {
   tot[i]=Integer.parseInt(mainAr[i]);
  }
  return tot;
 }
}