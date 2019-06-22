package YoussefGamil;
/**
This class was made by Youssef MAI Gamil in 22/12/2005
-16/8/2006
**/
public class yssef_int
{
 public String num=null;
 public yssef_int(String txt)
 {
  txt=this.num;
 }
 public yssef_int()
 {
 }
 public int toPos(int num)
 {
  if(num<0) return(num*-1);
  return num;
 }
 public int multi2(String para)
 {
  int re=-1;
  for(int c=0;c<9;c+=2)
  {
   if(para.charAt(para.length()-1)+"".indexOf(c)!=-1)
    re=0;
  }
  return re;
 }

/**
 *return 0 :int ,if the [num] is divisiable by [main]
 *return 1 :int ,if the [num] is not divisiable by [main]
**/
 public int isDivi(int num,int main)
 {
   int temp=num;
  while( (temp-main) >= 0)
  {
   temp=temp-main;
  }
  return temp;
 }
/**
 *return 0 :int ,if the [num] is Prime number
 *return 1 :int ,if the [num] is not Prime number
**/
 public int isPrime(int num)
 {
  if(num>1)
  {
   int i=num-1;
   while( (isDivi(num,i)!=0) && (i!=1) )
   {
    i--;
   }
   return (i-1);
  }
  else
  {
   return 0;
  }
 }
/**
 *return The comman Factors of [num] : int[].
**/
 public int[] getCF(int num)
 {
  if(num>2)
  {
   yssef_intArray re=new yssef_intArray();
   int ind=0;
   int prime[]=getPrime(2,num);
   for(int i=0;i<prime.length;i++)
   {
    if(isDivi(num,prime[i])==0)
    {
     re.add(prime[i]);
    }
   }
   return re.mainAr;
  }
  else
  {
   int tempp[]=new int[1];
   tempp[0]=0;
   return tempp;
  }
 }
/**
 *return Prime numbers from [s] to [f] :int[].
**/
 public int[] getPrime(int s,int f)
 {
  int c=-1;
  yssef_intArray yoga=new yssef_intArray();
  while( (s!=f))
  {
   if(isPrime(s)==0)
   {
    yoga.add(s);c=s;
   }
   s++;
  }

  if(c!=-1)
  {
   return yoga.mainAr;
  }
  else
  {
   int t[]={c};
   return t;
  }
 }
/**
 * Wasn't tested
 *Return 0: if there was no comman Factor.
 *Return int[] of the comman factor(s).
**/
 public int[] getCF(int num[])
 {
  yssef_intArray yoga=new yssef_intArray();
   int cf[];
  for(int i=0;i<(num.length);i++)
  {
   cf=getCF(toPos(num[i]));
   if(i!=0)
   {
    int test=0; 
    for(int i2=0;i2<yoga.mainAr.length;i2++)
    {
     for(int i3=0;i3<cf.length;i3++)
     {
      if( (yoga.mainAr[i2]!=cf[i3]) && (test==0) )
      {
       test++;
      }
     }
    }
    if(test==cf.length)
    {
     int t[]={0};
     return t;
    }
   }
   yoga.add(cf);
  }
  return new yssef_intArray().getCommanValues(yoga.mainAr);
 }
 /**
  * wasn't tested
  *All int should be positive.
  *Return int[] of the simplest form of the array.
 **/


/** public int[] toSimplestForm(int para[]) // Not working
 {
  int num[]=para;
  int divi[];
  divi=getCF(num);
  while(divi[0]!=0)
  {
   for(int i=0;i<num.length;i++)
   {
    num[i]=num[i]/divi[0];
   }
   divi=getCF(num);
  }
  return num;
 } **/
 public int[] toSimplestForm(int para[]) // working
 {
  int factor[]=getCF(para);
  int res[]=para,i;
  while(factor[0]!=0)
  {
   for(i=0;i<res.length;i++)
   {
    res[i]/=factor[0];
   }
   factor=getCF(res);
  }
  return res;
 }


 /**
  *16/8/2006,17/8/2006
  *All int should be possitive.
  *Return int[] of the simplest form of the array.
 **/
 public int[] intCF(int par[])
 {
  int res[],temp[]=getCF(par[0]);
  if(temp[0]==0)   // if it was aprime number, include it
  {
   res=new int[1];res[0]=par[0];
  }
  else
  {
   res=temp;
  }
  int i=1;
  while((i<par.length) && (res[0]!=0) )
  {
   temp=getCF(par[i]);
   if(temp[0]==0)   // if it was aprime number, include it
   {
    temp=new int[1];temp[0]=par[i];
    res=getC(temp,res);
   }
   else
   {
    res=getC(temp,res);
   }
   i++;
  }
  return res;
 }
 /**
 * 16/8/2006
 * Import: yssef_intArray
 * Returns : int[];Comman Ints
 * Parameter: int[],int[]
 **/
 public static int[] getC(int par[],int par2[])
 {
  yssef_intArray res=new yssef_intArray();
  for(int i=0;i<par.length;i++)
  {
   for(int j=0;j<par2.length;j++)
   {
    if(par[i]==par2[j])
    {
     res.add(par[i]);
    }
   }
  }
  return res.mainAr;
 }
}