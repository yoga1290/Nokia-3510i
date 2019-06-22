package YoussefGamil;
 /**
 Intro: This class was established by Youssef MAI Gamil
        between  ýOctober ý2004 and April 2005  
 Last updates:
    6/6/2005: [String[] getAll2Txt(String,String)]
              was deleted and other return function was created having the same name
              working faster than the old one as the old one uses [String[] getAll2TxtStr(String,String,int)]
   -20/6/2005:
            -The function [void setTxt(String)] was created.
            -The constructor [yssef_search()] was created.
   -27/6/2005:
            -The 5 functions [String[] arrayForm(String,String,String)]
                         ,   [String[] arrayForm(String,int,int)]
                         ,   [String getArrayForm(String,String[])]
                         ,   [int[] intArrayForm(String,String,String)]
                       and   [int[] intArrayForm(String,int,int)] were created.
   -4/7/2005:
           -The Functions [String arrayForm(char,int,int)]
                          ,[String arrayForm(String,int,int)]were updated.
   -5/7/2005:
           -The Functions [int[] intArrayForm(String,String,String)]
                         ,[int[] intArrayForm(String,int,int)] were updated.
           -The Function  [int[] intArrayForm(char,int,int)] was created.
   -13/7/2005:
           -The Function [int yssef_howm(String,int,int)] was updated.
   -12/8/2005:
           -The if-condition were add to these functions:
             arrayForm(String sym,int str,int finn),
   -13/8/2005:
           -The Functions [insertTxt(String,int,int)]
                         ,[insertTxt(String,String,String)]
                         ,[removeTxt(String,int,int)]
                      and [removeTxt(String,String,String)] were created.
   -4/10/2005:
           -The function [String getArrayForm(String,int[])] was created..
		 same as [String getArrayForm(String,String[])].
   -9/11/2005:
           -The function [String arrayForm(String,int,int,int)] was created.
           -The function [String arrayForm(String,String,String,int)] was created.
   -1/7/2006:
           -The function [int[] getTxtIndNear(String stxt,String ext[],int st,int fin)]
                         ,[String getTxtNear(String stxt,String ext[],int st,int fin)] was created.
**/
public class yssef_search
{
 public String txt="";
 public yssef_search(String txt)
 {
  this.txt=txt;
 }
 public yssef_search()
 {
 }
 public void setTxt(String txt)
 {
  this.txt=txt;
 }
 public  int[] igsearch(String ext,int st,int fin) // throws Exception
 {
  int i=st,tot=0,c=0,s=-10,f=-10;
  while((i!=fin) && (tot!=ext.length()))
  {
   if(txt.charAt(i)==ext.charAt(c))
   {
    tot++;
    if(c==0)
    { 
     s=i;
    }
    f=i;
    c++;
   }
   else if(txt.charAt(i)!=' ')
   {
    tot=0;
    c=0;
    s=-1;
    f=-1;
   }
   i++;
  }
  int tottt[]={s,f};
  return tottt;
 }
  public  int[] igsearchAll(String ext,int s)
 {
  return igsearch(ext,s,txt.length());
 }
 public  int yssef_howm(String ext,int s,int f)
 {
  int i=0;
  int t[]=igsearch(ext,s,f);
  while((t[1]<f) && (t[0]!=-1))
  {
   if((t[1]!=-1) && (t[1]<f))
   {
    i++;
    t=igsearch(ext,(t[1]+1),f);
   }
  }
  return i;
 }
 public  int yssef_howm(String ext)
 {
  int i=0;
  int t[]=igsearchAll(ext,0);
  while(t[0]!=-1)
  {
   t=igsearchAll(ext,t[1]+1);
   i++;
  }
  return i;
 }
public  int yssef_howm(char ext,int s,int f)
 {
  int i=0;
  for(int t=s;t<(f);t++)
  {
   if(txt.charAt(t)==ext)
    i++;
  }
  return i;
 }
 public void removeTxt(int st,int fin)
 {
  String tot="";
  for(int i=0;i<txt.length();i++)
  {
   if((i>fin) || (i<st))
    tot+=txt.charAt(i);
  }
  txt=tot;
 }
 public void insertTxt(String itxt,String stt,String fint)
 {
  int st=igsearch(stt,0,txt.length())[1]
    ,fin=igsearch(fint,st,txt.length())[0];
  insertTxt(itxt,st,fin);
 }
 public String mainStr()
 {
  return txt;
 }
 public void insertTxt(String itxt,int st,int fin)
 {
  String tot="";
  itxt=getTxt((st),fin)+itxt;
  removeTxt(st,(fin-2));
  for(int i=0;i<txt.length();i++)
  {
   if(i!=st)
   {
    tot+=txt.charAt(i);
   }
   else
   {
    tot+=itxt;
   }
  }
  txt=tot;
 }
 public void addTxt(String atxt)
 {
  this.txt+=atxt;
 }
 public void removeTxt(String stt,String fint)
 {
  int st=igsearch(stt,0,txt.length())[0]
     ,fin=igsearch(fint,st,txt.length())[1];
  removeTxt(st,fin);
 }
 public  int[] get2Txt(String ext,String ext2,int n)
 {
  int ygs[]=new int[2],ygs2[]=new int[2],tot[]=new int[2],i=0,st=0,fn=0;
  while(i!=n)
  {
   ygs=igsearch(ext,fn,txt.length());
   st=ygs[1];
   ygs2=igsearch(ext2,st,txt.length());
   fn=ygs2[0];i++;
  }
  tot[0]=st;tot[1]=fn;
  return tot;
 }
 public  String getTxt(int st,int fn)
 {
  int i=st;String tot="";
  while(i!=fn)
  {
   tot+=txt.charAt(i);i++;
  }
  return tot;
 }

 public  String get2TxtStr(String ext,String ext2,int n)
 {
  int yg[]=get2Txt(ext,ext2,n);
  String ygtot=getTxt((yg[0]+1),yg[1]);
  return ygtot;
 }

 public String[] getAll2TxtStr(String ext,String ext2)
 {
  int n=yssef_howm(ext);
  String tot[]=new String[n];
  int st=0,fin=0,c=0;
  st=igsearchAll(ext,fin)[1]+1;  /** get after the last char [carAt(fin)] to not be repeated **/
  fin=igsearchAll(ext2,st)[0];
  while((c!=n) && (fin!=-1))
  {
   tot[c]=getTxt(st,fin);
   c++;
   st=igsearchAll(ext,fin)[1]+1;  /** get after the last char [carAt(fin)] to not be repeated **/
   fin=igsearchAll(ext2,st)[0];
  }
  return tot;
 }

 public int[] getAll2IntStr(String ext,String ext2) /** get all integers that
                                                    are between [ext][ext2] & put them in an INT[] **/
 {
  int n=yssef_howm(ext,0,txt.length());
  int tot[]=new int[n];
  int st=0,fin=0,c=0;
  while((c!=n) && (fin!=-1))
  {
   st=igsearchAll(ext,fin)[1]+1;  /** get after the last char [carAt(fin)] to not be repeated **/
   fin=igsearchAll(ext2,st)[0];
   tot[c]=Integer.parseInt(getTxt(st,fin));
   c++;
  }
  return tot;
 }
 public static int isInt(char ch) /** make sure that [ch] is an Integer **/
 {
  int check=0;
  switch(ch)
  {
   case '0': ;break;
   case '1': ;break;
   case '2': ;break;
   case '3': ;break;
   case '4': ;break;
   case '5': ;break;
   case '6': ;break;
   case '7': ;break;
   case '8': ;break;
   case '9': ;break;
   default: check=-1;
  }
  return check;
 }
 public int isInt(String ig) /** make sure it is [int] without looking in {ig}  **/
 {
  int i2=0,check=0,i=0;
  char ch,ch2;
  while((i!=txt.length()) && (check!=-1))
  {
   ch=txt.charAt(i);
   for(i2=0;i2<(ig.length());i2++)
    {
     ch2=ig.charAt(i2);
      if((ch==ch2) || (isInt(ch)!=-1))
      {
       check=0;
      }else { check=-1;}
   }
    i++;
  }
  return check;
 }
 public int[] getIndexOf(String n,int str,int fin)
 {
  int tot[],st=str,i=0;
   tot=new int[yssef_howm(n,str,fin)];
   int[] temp=igsearch(n,st,fin);
   while((i!=tot.length) && (tot[0]!=-1))
   {
    tot[i]=temp[0];
    st=temp[1]+1;
    i++;
    temp=igsearch(n,st,fin);
   }
  return tot;
 }
 public String[] arrayForm(char sym,int str,int finn)
 {
  int st=str,i=1,tt=0,t=0,fin=finn;
  /**
   the valur of [i] is =1; as tot starts after the first [sym]
  **/
  String tot[]=new String[(yssef_howm(sym,st,fin)+1)];
  /** I added 1 to [tot] array as this example shows why? ex: {Yo,Mo,Ga}
      The items number is 3 but, the [sym=","] is 2 **/
  tot[0]=getTxt(st,igsearch(sym+"",st,fin)[0]);
  while((i!=(tot.length-1)) && (tt!=-1))
  {
   t=igsearch(sym+"",st,fin)[1]+1;
   st=t+1;
   tt=igsearch(sym+"",st,fin)[0];
   if(tt!=-1)
    tot[i]=getTxt(t,tt);
   else
    tot[i]=getTxt(t,fin);
   i++;
  }
  if(tt!=0)
   tot[(tot.length-1)]=getTxt((igsearch(sym+"",tt,fin)[1]+1),fin);
  else
   tot[(tot.length-1)]=getTxt((igsearch(sym+"",str,fin)[1]+1),fin);
  return tot;
 }
 public String[] arrayForm(String sym,int str,int finn)
 {
  if(igsearch(sym,str,finn)[0]>0)
  {
    int st=str,i=1,tt=0,t=0,fin=finn;
   /**
    the value of [i] is =1; as tot starts after the first [sym]
   **/
   if(sym.length()==1)
   {
    return arrayForm(sym.charAt(0),str,finn);
   }
   String tot[]=new String[(yssef_howm(sym,st,fin)+1)];
   /** I added 1 to [tot] array as this example shows why? ex: {Yo,Mo,Ga}
       The items number is 3 but, the [sym=","] is 2 **/
   tot[0]=getTxt(st,igsearch(sym,st,fin)[0]);
   while((i!=(tot.length-1)) && (tt!=-1))
   {
    t=igsearch(sym,st,fin)[1]+1;
    st=t+1;
    tt=igsearch(sym,st,fin)[0];
    if(tt!=-1)
     tot[i]=getTxt(t,tt);
    else
     tot[i]=getTxt(t,fin);
    i++;
   }
   if(tt!=0)
    tot[(tot.length-1)]=getTxt((igsearch(sym+"",tt,fin)[1]+1),fin);
   else
    tot[(tot.length-1)]=getTxt((igsearch(sym+"",str,fin)[1]+1),fin);
   return tot;
  }
  else
  {
   String[] temp={getTxt(str,finn)};
   return temp;
  }
 }
 public String[] arrayForm(String sym,String st,String f)
 {
  int frist=igsearchAll(st,0)[1]+1;
  int sec=igsearchAll(f,frist)[0];
  return arrayForm(sym,frist,sec);
 }
 public String getArrayForm(String sym,String ar[])
 {
  String tot="";
  for(int i=0;i<(ar.length-1);i++)
  {
   tot+=ar[i]+sym;
  }
  tot+=ar[(ar.length-1)];
  return tot;
 }
 public String getArrayForm(String sym,int ar[])
 {
  String tot="";
  for(int i=0;i<(ar.length-1);i++)
  {
   tot+=ar[i]+sym;
  }
  tot+=ar[(ar.length-1)];
  return tot;
 }
 
 public int[] intArrayForm(char sym,int str,int finn)
 {
  int st=str,i=1,tt=0,t=0,fin=finn;
  /**
   the valur of [i] is =1; as tot starts after the first [sym]
  **/
  int tot[]=new int[(yssef_howm(sym,st,fin)+1)];
  /** I added 1 to [tot] array as this example shows why? ex: {Yo,Mo,Ga}
      The items number is 3 but, the [sym=","] is 2 **/
  tot[0]=Integer.parseInt(getTxt(st,igsearch(sym+"",st,fin)[0]));
  while((i!=(tot.length-1)) && (tt!=-1))
  {
   t=igsearch(sym+"",st,fin)[1]+1;
   st=t+1;
   tt=igsearch(sym+"",st,fin)[0];
   if(tt!=-1)
    tot[i]=Integer.parseInt(getTxt(t,tt));
   else
    tot[i]=Integer.parseInt(getTxt(t,fin));
   i++;
  }
  if(tt!=0)
   tot[(tot.length-1)]=Integer.parseInt(getTxt((igsearch(sym+"",tt,fin)[1]+1),fin));
  else
   tot[(tot.length-1)]=Integer.parseInt(getTxt((igsearch(sym+"",str,fin)[1]+1),fin));
  return tot;
 }
 public int[] intArrayForm(String sym,int str,int finn)
 {
  int st=str,i=1,tt=0,t=0,fin=finn;
  /**
   the valur of [i] is =1; as tot starts after the first [sym]
  **/
  if(sym.length()==1)
  {
   return intArrayForm(sym.charAt(0),str,finn);
  }
  int tot[]=new int[(yssef_howm(sym,st,fin)+1)];
  /** I added 1 to [tot] array as this example shows why? ex: {Yo,Mo,Ga}
      The items number is 3 but, the [sym=","] is 2 **/
  tot[0]=Integer.parseInt(getTxt(st,igsearch(sym,st,fin)[0]));
  while((i!=(tot.length-1)) && (tt!=-1))
  {
   t=igsearch(sym,st,fin)[1]+1;
   st=t+1;
   tt=igsearch(sym,st,fin)[0];
   if(tt!=-1)
    tot[i]=Integer.parseInt(getTxt(t,tt));
   else
    tot[i]=Integer.parseInt(getTxt(t,fin));
   i++;
  }
  if(tt!=0)
   tot[(tot.length-1)]=Integer.parseInt(getTxt((igsearch(sym+"",tt,fin)[1]+1),fin));
  else
   tot[(tot.length-1)]=Integer.parseInt(getTxt((igsearch(sym+"",str,fin)[1]+1),fin));
  return tot;
 }
 public int[] intArrayForm(String sym,String st,String f)
 {
  int frist=igsearchAll(st,0)[1]+1;
  int sec=igsearchAll(f,frist)[0];
  return intArrayForm(sym,frist,sec);
 }

/** This function return only one element which is in index of [Ind] **/
 public String arrayForm(String sym,int st,int fin,int Ind)
 {
  String tot="";
  int s=st,i=0;
  if(Ind>0)
  {
   int[] temp;
   while((i!=Ind) && (s!=-1))
   {
    temp=igsearch(sym,s,fin);
    s=temp[1]+1;
    i++;
   }

   if(Ind==0)
   {
    tot=getTxt(s,igsearch(sym,s,txt.length())[0]);
   }
   else if((Ind+1)==(yssef_howm(sym)-1))
   {
    //(txt.lastIndexOf(sym)+1)
    tot=getTxt(s,fin);
   }
   else
   {
    tot=getTxt(s,igsearch(sym,(s+1),fin)[0]);
   }
  }
  return tot;
 }
 public String arrayForm(String sym,String st,String fin,int Ind)
 {
  int s=igsearch(st,0,txt.length())[1]
     ,f=igsearch(fin,(s+1),txt.length())[0];
  return arrayForm(sym,s,f,Ind);
 }
 public int[] getTxtIndNear(String stxt,String ext[],int st,int fin)
 {
  int startpoints[]=new int[ext.length];
 // int temp;
  int i=0;
  for(i=0;i<ext.length;i++)
  {
  // temp=igsearch(ext[i],st,fin)[0];
 //  if(temp!=-1) startpoints.add(igsearch(ext[i],st,fin)[0]);
   startpoints[i]=igsearch(ext[i],st,fin)[0];
  }
  int arrangedSP[]=new yssef_intArray(startpoints).arrange();
  
  int found=-1;
  i=0;
  while((i<arrangedSP.length) && (found==-1))
  {
   if(arrangedSP[i]!=-1) found=arrangedSP[i];
   i++;
  }
  
  st=igsearch(stxt,st,fin)[1]+1;
  fin=found;
  int tot[]={st,fin};
  return tot;
 }
 public String getTxtNear(String stxt,String ext[],int st,int fin)
 {
  int temp[]=getTxtIndNear(stxt,ext,st,fin);
  return (getTxt(temp[0],temp[1]));
 }
}