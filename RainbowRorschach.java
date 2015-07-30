import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class RainbowRorschach extends PApplet {

//ben kandov
//my function for color; I'll be modifying it in future for loops to make different arrays of color
public void colordisplay(float color1, float color2, float color3){
  fill(color1,color2,color3);
}
public void setup(){
  /*
  In this void setup, I put the designs for my drawing material buttons
  and the for loops that draw my arrays of colors, which I'll use later in the mousePressed function to set the buttons for choosing between colors 
  */
  size(500,400);
  background(255,255,255);
  rectMode(CENTER);
  //designs for buttons for switching between drawing materials
  fill(125,125,125);
  rect(425,25,50,50);
  fill(0,0,0);
  text("Ink",415,25);
  fill(125,125,125);
  rect(475,25,50,50);
  fill(0,0,0);
  text("Paint",465,25);
  fill(125,125,125);
  rect(425,75,50,50);
  fill(0,0,0);
  text("Eraser",410,75);
  fill(125,125,125);
  rect(475,75,50,50);
  fill(0,0,0);
  text("Big",465,70);
  text("Eraser",460,85);
  
  for(float c = 0; c<200; c+=15){
      colordisplay(c,0,0);
      rect(415,200+c,15,15);
     }
  for(float c =0; c<200; c+=15){
      colordisplay(0,c,0);
      rect(430,200+c,15,15);
     }
  for(float c =0; c<200;c+=15){
      colordisplay(0,0,c);
      rect(445,200+c,15,15);
     }
  for(float c =0; c<200;c+=15){
      colordisplay(c,0,c);
      rect(460,200+c,15,15);
     } 
  for(float c =0; c<200;c+=15){
      colordisplay(0,c,c);
      rect(475,200+c,15,15);
     }  
  for(float c =0; c<200;c+=15){
      colordisplay(c,c,0);
      rect(490,200+c,15,15);
     }  
     
 
}
    
//variables for sizes of material
//variable for paint-stroke size
float ps = 5;
//variable for eraser-size
float e = 10;
//variable for big-eraser-size
float be = 25;
//variable for splotch size
float s = 25;
boolean inkSplotch=false;
boolean paintStroke=false;
boolean eraser = false;
boolean bigEraser = false;

float randfactor = 5;

//variables for color
float colorVal1;
float colorVal2;
float colorVal3;


public void draw(){
  //conditionals for activating drawing materials
  fill(colorVal1,colorVal2,colorVal3);

  //conditionals for color
  //fill(color1,color2,color3);
  //conditionals for big eraser material
  if(bigEraser==true){
    noStroke();
    if ((mousePressed==true) && (mouseX<200)){
      bigEraser(mouseX,mouseY,be);
      float xmirrored = 200 + (200-mouseX);
      bigEraser(xmirrored,mouseY,be);
    }
  }
  //conditionals for eraser material
 if(eraser==true){
  noStroke();
  if ((mousePressed==true) && (mouseX<200)){
      eraser(mouseX,mouseY,e);
      float xmirrored = 200 + (200-mouseX);
      eraser(xmirrored,mouseY,e);
  }
 }
  //conditionals for paint-brush material
 if(paintStroke==true){
   noStroke();
  if ((mousePressed==true) && (mouseX < 200)) {
    brushStroke(mouseX,mouseY,ps);
    float xmirrored = 200 + (200-mouseX);
    brushStroke(xmirrored,mouseY,ps);
  }  
 } 
  //conditionals for ink splotch material
  
 if(inkSplotch==true){
   noStroke();
  //if mouse is pressed in a certain area, and the mouse is in the first half of the screeen (width/2)
  if ((mousePressed == true) && (mouseX < 200)) {
    splotch(mouseX,mouseY,s);
    s += random(-randfactor,randfactor);
    
    float xmirrored = 200 + (200-mouseX);
    splotch(xmirrored, mouseY, s);
    s += random(-randfactor,randfactor);
    if(mousePressed == false) {
      s = 5;
    }
    
  }
  if (s>50){
    s-=5;
  }
  if (s<15) {
    s+=5;
  }
 }
 if (keyPressed==true){
      background(255,255,255);
      stroke(5);
        //designs for buttons for switching between drawing materials
      fill(125,125,125);
      rect(425,25,50,50);
      fill(0,0,0);
      text("Ink",415,25);
      fill(125,125,125);
      rect(475,25,50,50);
      fill(0,0,0);
      text("Paint",465,25);
      fill(125,125,125);
      rect(425,75,50,50);
      fill(0,0,0);
      text("Eraser",410,75);
      fill(125,125,125);
      rect(475,75,50,50);
      fill(0,0,0);
      text("Big",465,70);
      text("Eraser",460,85);
       //designs for buttons for switching between colors
      for(float c = 0; c<200; c+=15){
        colordisplay(c,0,0);
        rect(415,200+c,15,15);
        }
      for(float c =0; c<200; c+=15){
        colordisplay(0,c,0);
        rect(430,200+c,15,15);
        }
      for(float c =0; c<200;c+=15){
        colordisplay(0,0,c);
        rect(445,200+c,15,15);
        }
      for(float c =0; c<200;c+=15){
        colordisplay(c,0,c);
        rect(460,200+c,15,15);
        } 
        for(float c =0; c<200;c+=15){
        colordisplay(0,c,c);
        rect(475,200+c,15,15);
        }  
       for(float c =0; c<200;c+=15){
        colordisplay(c,c,0);
        rect(490,200+c,15,15);
        }   
      
    }
} 

//functions for different drawing materials
//ink splotches/default material
public void splotch(float xsplotch, float ysplotch, float splotchsize){


 ellipse(xsplotch,ysplotch,splotchsize,splotchsize);
 float chance = random(6);
 
 
 
 if (chance>5) {
   float rand = random(0.4f);
   ellipse(xsplotch+splotchsize+random(-3,3),ysplotch+splotchsize+random(-3,3),s*rand,s*rand);
 }
}
//paint-strokes material
public void brushStroke(float strokeX, float strokeY, float strokeSize){
  ellipse(strokeX,strokeY,strokeSize,strokeSize);
}
//eraser material
public void eraser(float eraserX, float eraserY, float eraserSize){
  fill(255,255,255);
  ellipse(eraserX,eraserY,eraserSize,eraserSize);
}
//big eraser material
public void bigEraser(float bigEraserX,float bigEraserY,float bigEraserSize){
  fill(255,255,255);
  ellipse(bigEraserX,bigEraserY,bigEraserSize,bigEraserSize);
}
  
//function button pressings
public void mousePressed(){
  if(mouseX>400&&mouseX<450&&mouseY>0&&mouseY<50){
    inkSplotch=true;
    paintStroke=false;
    eraser=false;
    bigEraser=false;
  }
  if(mouseX>450&&mouseX<500&&mouseY>0&&mouseY<50){
    paintStroke=true;
    inkSplotch=false;
    eraser=false;
    bigEraser=false;
  }
  if(mouseX>400&&mouseX<450&&mouseY>50&&mouseY<100){
    eraser=true;
    inkSplotch=false;
    paintStroke=false;
    bigEraser=false;
  }
  if(mouseX>450&&mouseX<500&&mouseY>50&&mouseY<100){
    bigEraser=true;
    eraser=false;
    paintStroke=false;
    eraser=false;
  }


  for(float c = 0; c<200; c+=15){
       colordisplay(c,0,0);
       rect(415,200+c,15,15);
      if(mouseX>408&&mouseX<422&&mouseY>200+c-7&&mouseY<200+c+7){
        colorVal1=c;
        colorVal2=0;
        colorVal3=0;
      }
     }
  for(float c =0; c<200; c+=15){
       colordisplay(0,c,0);
       rect(430,200+c,15,15);
      if(mouseX>423&&mouseX<437&&mouseY>200+c-7&&mouseY<200+c+7){
        colorVal1=0;
        colorVal2=c;
        colorVal3=0;
      }
     }
  for(float c =0; c<200;c+=15){
       colordisplay(0,0,c);
       rect(445,200+c,15,15);
      if(mouseX>438&&mouseX<452&&mouseY>200+c-7&&mouseY<200+c+7){
        colorVal1=0;
        colorVal2=0;
        colorVal3=c;
      }
     }
  for(float c =0; c<200;c+=15){
       colordisplay(c,0,c);
       rect(460,200+c,15,15);
       if(mouseX>453&&mouseX<467&&mouseY>200+c-7&&mouseY<200+c+7){
         colorVal1=c;
         colorVal2=0;
         colorVal3=c;
       }
     } 
  for(float c =0; c<200;c+=15){
       colordisplay(0,c,c);
       rect(475,200+c,15,15);
       if(mouseX>468&&mouseX<482&&mouseY>200+c-7&&mouseY<200+c+7){
         colorVal1=0;
         colorVal2=c;
         colorVal3=c;
       }
     }  
  for(float c =0; c<200;c+=15){
       colordisplay(c,c,0);
       rect(490,200+c,15,15);
       if(mouseX>483&&mouseX<497&&mouseY>200+c-7&&mouseY<200+c+7){
         colorVal1=c;
         colorVal2=c;
         colorVal3=0;
       }
     }   
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "RainbowRorschach" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
