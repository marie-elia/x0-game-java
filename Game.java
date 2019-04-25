/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Scanner;


public class Game {
public static void main(String args[])
{
     View mygame=new View();
    Board mm=new Board();
   

int counters=1;
  // boolean gamegoing=true;
   mygame.displayBoard();
 while(mm.gameactive() && counters<10)
  {    
       if(counters%2==0)
       {
           System.out.println("Computer is Thinking");
           mm.computer_move();
          // System.out.println("Computer here2");
       }

       else
       {
            
           mygame.askplayer('X');
          
       }
       counters++;
       System.out.println("\n");
       mm.checkforwinner();
    
        
       mygame.displayBoard();
       if(counters==10)
       {
           System.out.println("Stale");
           mm.gamegoing=false;
       }
   }
      
    }
          
}
 class View
{
static Board bo=new Board();
    static public char[][] gameboard;
     private  String one="1";
     private  String two="2";
     private  String tree="3";
    //gameboard constructor
  public View()
    {
       gameboard=new char[3][3];
        for(int row=0;row<gameboard.length;row++)
        {
            for(int col=0;col<gameboard.length;col++)
        
             {
           // Arrays.fill(gameboard[row],'.');//fill rows with dots
            gameboard[row][col] = '.';
             }       
      
        }
    }

    public void displayBoard()
    {
        System.out.print("\t"+one);
       System.out.print("\t"+two);
        System.out.println("\t"+tree);
        for(int row=0;row<gameboard.length;row++)
        { 
           
            System.out.print(row+1);
            System.out.print("  ");
            for(int col=0;col<gameboard[0].length;col++)
            {
               // System.out.print(col+1);
                System.out.print("\t"+gameboard[row][col]);
                if(col==0 || col==1 )
                    System.out.print(" ");
      
            }
        System.out.println("\n     \n");
        }
        System.out.println();
    }
    
     

//method to pick row and column,validate inputs  and call methos makemove
public void askplayer(char player)
    {    
    Scanner rowin=new Scanner(System.in);
     Scanner colin=new Scanner(System.in);

    int row,col;
   do 
    {
        
         
    System.out.printf("Player %s Please enter a row from 1-3 :",player);
   
    
        row = rowin.nextInt();
        while(row>3 ||row<1) 
    {
          System.out.print("Please Ensure that the row you entered is within the boundaties ");
           row = rowin.nextInt();
    }
     // rowin.close();
     
    System.out.printf("Player %s Please enter a column from 1-3 :", player);
       col = colin.nextInt();
        while(col>3 ||col<1) 
    {
          System.out.print("Please Ensure that the col you entered is within the boundaties ");
          col = colin.nextInt();
    }
     
      System.out.println("Row:"+row);
        System.out.println("Column:"+col);
    }
    while(bo .notValid(row,col));
   
    bo.makemove(player,row-1,col-1);
    }

 
}

 class Board {
static View  mygame = new View();
 boolean gamegoing=true;
  int counters=1; 
  public boolean gameactive()
   {
       return gamegoing;
   }
 
   //to check if the  row and column are between 1 and 3 and i the postion is just a dot
   public boolean notValid(int row,int col)
   {
      if (row >3 || row<1 || col > 3|| col <1 || !isdot(row,col))
       
        return true;
       
     else
           
     return false;

   } 
    public boolean makemove(char player, int row,int col)
   {
    if(row>=0 && row<=2 && col>=0 && col<=2)
    
    {
        if (mygame.gameboard[row][col]!='.')
           return false;
         else 
        {  
           mygame.gameboard[row][col] = player; //print the player's move as decided
            return true;   
        }
    }
      else
       return false; 
     
    }
        public  void computer_move() 
   {
       
    boolean changed=true;
    while(changed)
    {
        int move = (int)(Math.random()*9);

        int roww=move/3;
        int coll=move%3;
    
          
        if(mygame.gameboard[roww][coll] == '.' && mygame.gameboard[roww][coll] != 'X'
                ) 
	 

        {
           makemove('O',roww,coll);
           changed= false;
        }
       
    }
   }
  public boolean isdot(int row,int col)
{
           if (this.mygame.gameboard[row-1][col-1]=='.')
               return true;
          else 
           {
               System.out.println("The position is taken.\n");
           
               
                 return false;
           }
}
  //check for winner 3 in arow
 public boolean checkforwinner()
   {

      //loop over rows to be sure there is no space and they are all the same symbol
      for(int row=0;row<mygame.gameboard.length;row++)
          
      { 
     
            
          if(mygame.gameboard[row][0]==mygame.gameboard[row][1]&& mygame.gameboard[row][1]== mygame.gameboard[row][2] && mygame.gameboard[row][0]!='.')
          {
           if(mygame.gameboard[row][0]=='X')
           System.out.println("The winner is X");
           else
         System.out.println("The Computer Won !!");

           gamegoing=false;
           return true;
              
          }
                   
      }
            //loop over columns to be sure there is no space and they are all the same symbol
for(int col=0;col<mygame.gameboard[0].length;col++)
      {
          if(mygame.gameboard[0][col]==mygame.gameboard[1][col]&& mygame.gameboard[1][col]== mygame.gameboard[2][col] && mygame.gameboard[0][col]!='.')
            {
           
             if(mygame.gameboard[0][col]=='X')
           System.out.println("The winner is X");
           else
         System.out.println("The Computer Won !!");
                        gamegoing=false;
                       return true;
          }
                   
      }
//check for diagonals
if(mygame.gameboard[0][0]==mygame.gameboard[1][1]&&mygame.gameboard[1][1] ==mygame.gameboard[2][2]&& mygame.gameboard[1][1]!='.')
     {
           
              if(mygame.gameboard[1][1]=='X')
           System.out.println("The winner is X");
           else
         System.out.println("The Computer Won !!");
                       gamegoing=false;
                        return true;
          }
      if(mygame.gameboard[2][0]==mygame.gameboard[1][1]&&mygame.gameboard[1][1] ==mygame.gameboard[0][2]&& mygame.gameboard[0][2]!='.')
    {
           
                if(mygame.gameboard[0][2]=='X')
           System.out.println("The winner is X");
           else
         System.out.println("The Computer Won !!");
                        gamegoing=false;
                    return true;
          }   
         gamegoing=true;
      return false;
  }
}
