This project covers the syllabus of `Associate Android Developer Certification`

[Exam Content](https://developers.google.com/training/certification/associate-android-developer/#exam-content) is here and [x] `topics are covered here`.

Emoji display

# Ways of Initialise Emoji

### Emoji can be applied on 

- EmojiEditText, 
- EmojiTextView, 
- EmojiButton   

### Using EmojiCompat from Downloadable fonts configuration

    <android.support.text.emoji.widget.EmojiTextView
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"/>
       
       

### Using EmojiCompat with AppCompat widgets

    <android.support.text.emoji.widget.EmojiAppCompatTextView
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"/>
       
       
    


### An Emoji can be represented by 

- Unicode                             (**U+1F601**)
- *Unicode java representation*       (**0x1F601**)
- *utf-16 java representation*        (**\ud83d\ude00**)
- utf-8 byte                          (**\xF0\x9F\x98\x81**)



### Important note :  

- We will get unicode formatted string from local db in this format U+1F601
- Then we need to replace `U+` with `0x`
- Then `Integer.decode()` to convert it to `Integer DecimalNumeral` before showing as a emoji on view.

                int unicode = Integer.decode(emoji);
                
                
### Read Emoji from Edit text
We use this library to read emoji from edit text

        implementation 'org.apache.commons:commons-text:1.6'       
        
        StringEscapeUtils.escapeJava(editTextEmoji.getEditableText().toString());         



