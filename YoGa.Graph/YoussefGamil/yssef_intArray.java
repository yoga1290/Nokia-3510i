package YoussefGamil;
/**
 This class was established by Youssef MAI Gamil
 on 9/6/2005 with footage from class [yssef_array].
 last updates:
      10/6/2005:
         -The function [int[] getIndexOf(int)] was updated with (if-else) statement. 
      12/6/2005:
         -The Function [int getLength()] was updated to return {0} if [firstTime]=0.
         -The Function [int getByIndex(int)] was created.
         -The Function [void edit(int,int)] was created.
      14/6/2005:
         -The constructor [yssef_intArray(String)] was created.
      15/6/2005:
         -The Function [void drop(int)] was created.
      25/6/2005:
         -The Function [void replace(int,int)] was created.
      27/6/2005:   
         -The Function [String[] intoString()] was created.
      20/9/2005:
         -The Function [int[] uniqe()] was created.
         -The Function [int[] arrange()]was created.
      2/3/2006:
         -The function [int[] getCommanValues(int num[])] was created.
      23/4/2006:
         -The function [void add(int[])] was updated.
**/
public class yssef_intArray
{
 public int[] mainAr={0},tempar={0};
 public int firstTime=0;
 public yssef_intArray()
 {
 }
 public yssef_intArray(int txt)
 {
  mainAr[0]=txt;
  firstTime=-1;
 }
 public yssef_intArray(int a[]) /** copy the Array [a] to the main Array [mainAr] **/
 {
  mainAr=new int[a.length];
  for(int i=0;i<a.length;i++)
  {
   mainAr[i]=a[i];
  }
  firstTime=-1;
 }
 public void replace(int n,int newn)
 {
  for(int i=0;i<mainAr.length;i++)
  {
   if(mainAr[i]==n)
    mainAr[i]=newn;
  }
 }
 public void copy()
 {
  int i=0;
  tempar=new int[(mainAr.length)]; /** {mainAr.length-1} because [mainAr.length] starts counting from 1 not 0 **/
  while(i!=mainAr.length)
  {
   tempar[i]=mainAr[i];
   i++;
  }
 }
 public void drop(int n)
 {
  copy();
  int count=0;
  mainAr=new int[(tempar.length-1)];
  for(int i=0;i<tempar.length;i++)
  {
   if(i!=n)
   {
    mainAr[count]=tempar[i];
    count++;
   }
  }
 }
 public void edit(int n,int i)
 {
  mainAr[i]=n;
 }
 public int getByIndex(int i)
 {
  return mainAr[i];
 }
 public void add(int txt[])
 {
  if(firstTime!=0)  
  {
   copy();
   mainAr=new int[(tempar.length+txt.length)];
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
   mainAr=new int[txt.length];
   for(int i=0;i<txt.length;i++)
   {
    mainAr[i]=txt[i];
   }
   firstTime=-1;
  }
 }
 public void add(int txt)
 {
  if(firstTime!=0)
  {
   copy();
   mainAr=new int[(tempar.length+1)];
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
 public int howm(int n)
 {
  int tot=0;
  for(int i=0;i<mainAr.length;i++)
  {
   if(mainAr[i]==n) tot++;
  }
  return tot;
 }
 public int[] getIndexOf(int n)  /** return the indexs in which the [n] was repeated in the Array [mainAr] **/  
 {
  int len=howm(n),tot[];
  if(len!=0)
  {
   tot=new int[howm(n)];
   int count=0;
   for(int i=0;i<mainAr.length;i++)
   {
    if(mainAr[i]==n)
    {
     tot[count]=i;
     count++;
    }
   }
  }
  else
  {
   tot=new int[1];
   tot[0]=-1;
  }
  return tot;
 }
 public int[] getArray()
 {
  return mainAr;
 }
 public int getLength()
 {
  int tot=0;
  if(firstTime!=0)
   tot=mainAr.length;
  return tot;
 }

 public int[] arrange()
 {
  int temp;
  for(int i=0;i<mainAr.length;i++)
  {
   temp=mainAr[i];
   for(int i2=i;i2<mainAr.length;i2++)
   {
    if(mainAr[i]>mainAr[i2])
    {
     temp=mainAr[i];
     mainAr[i]=mainAr[i2];
     mainAr[i2]=temp;
    }
   }
  }
  return mainAr;
 }
 public int[] uniqe()  // Return INT[] contains un-repeated items
 {
  yssef_intArray tot=new yssef_intArray();
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
 public String[] intoString()
 {
  String[] tot=new String[mainAr.length];
  for(int i=0;i<tot.length;i++)
  {
   tot[i]=""+mainAr[i];
  }
  return tot;
 }
 public static int[] getCommanValues(int num[])
 {
  int c=0;
  yssef_intArray yoga=new yssef_intArray();
  for(int i=0;i<num.length;i++)
  {
   c=0;
   for(int i2=(i+1);i2<num.length;i2++)
   {
    if(num[i]==num[i2])
    {
     c++;
    }
   }
 //  if(c==0)
   if(c!=0)
   {
    yoga.add(num[i]);
   }
  }
  return yoga.mainAr;
 }
}