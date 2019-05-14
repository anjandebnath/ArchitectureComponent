This project covers the syllabus of `Associate Android Developer Certification`



### TIPS 

#### Tip 1
        Android supports RTL layouts from API 17+ i.e., Android 4.2 (Jelly Bean).and when we make our layout
        to support both RTL and LTR then we can not use layout_marginleft and layout_marginRight there
        we use layout_marginstart and layout_maginend.
        
  [img1](https://github.com/anjandebnath/ArchitectureComponent/blob/feature/constraintlayout/img/relative_positioning.png)
        
#### Tip 2
     xmlns:android="http://schemas.android.com/apk/res/android" 
      
 In XML, *xmlns* declares a *Namespace*. In fact, **The namespace has pretty much the same uses as the package name in a Java application.**  

#### Tip 3

    When a dimension is set to MATCH_CONSTRAINT, the default behavior is
    to have the resulting size take all the available space. 

 [img2](https://github.com/anjandebnath/ArchitectureComponent/blob/feature/constraintlayout/img/matchconstraint.png)   
     
#### Tip 4

        app:layout_constraintDimensionRatio="16:9"
    
- Suppose you have an ImageView whose width should match the Screen Width and the Image Aspect ratio should be 16:9    

    
    
        app:layout_constraintDimensionRatio="H,3:1"   
    
       
- To set aspect ratio **if both dimensions are set to MATCH_CONSTRAINT (0dp).** 
- **H,3:1 will always make the ImageView appear 3 times wider than height.**
- The prefix H or W tells ConstraintLayout which dimension **should be adjusted.** 
   If it is H then it means **width will be first computed** from other constraints and **then height will be adjusted** according to the aspect ratio. 
   
       
#### Tip 5
    android:lineSpacingExtra add extra spacing between lines of text of TextView
    
    
#### Tip 6  

    A set of widgets are considered a chain if they are linked together via a bi-directional connection.  
    
[img3](https://github.com/anjandebnath/ArchitectureComponent/blob/feature/constraintlayout/img/chain.png)




















