# CaesarGuiApp
A Java GUI application for decrypting/encrypting text with the Caesar cipher

## Installation and Settings:

**Windows:** Run the application just by double-clicking the **Caesar.exe** file. If you don't have Java installed 
on your computer the application should automatically download and install Java on your system. If that fails then 
you will have the option to manually install Java after the software takes you to the Java download page.


**Linux and Mac:** You need to have Java installed on your system. If you do not have Java installed then you can download and install it from here: <https://java.com/en/download/>
-  **Linux:** Open your terminal, navigate to the folder where the **Caesar.jar** file is, and type: **java -jar Caesar.jar**

-  **Mac:** To execute the **.jar** file on the Mac, go to your terminal and type in: **java -cp Caesar.jar menu.Menu**

## Short User's Manual:

When you run the program, this is the GUI visual interface that will appear on the screen.

![alt CaesarCipherCrackZ](http://www.emil.free.bg/CaesarCipherCrackZ_010.png)

(Actually, this is how it looks like under my version of GNU Linux!)

The Java code is written up so that the application will have the **_"LookAndFeel"_** of the operating system under which it is running; Therefore, it will look different under Windows, Mac, and other operating systems.

#### From the top menu bar you can select three options: *File*, *Settings*, and *Help*.

- Under the **_File_** drop down menu you can select three options:

    -  **Open** -- opens a file and displays its text in the input text area.

    -  **Save** -- saves the text from the output text area into a file.

    -  **Exit** -- exits the application and quits the program.


- Under the **_Settings_** drop down menu you can select one of the two (radio button) options:

    -  **English dictionary** -- the default selected which uses a 10k English words dictionary to crack the encrypted code.

    -  **Brute force attack** -- is the other option that you can select to get the 25 key variations of the encrypted text.


- Under the **_Help_** drop down menu currently there is only one option:

    -  **About** -- displays information about the software version, release date, contact information, and displays the GNU GPL 3.0 LICENSE which is read from a file.

    -  _In future versions we might add a Short User's Manual as another option under the **Help** drop down menu..._

#### **How to Encrypt your message you ask?**

1. Type in a plain text message in the input text area or just open a text file from the **File** > **Open** menu option.

2. Select the **Brute force attack** radio-button option from the **Settings** drop down menu.

3. Click the **Crack it** button.

![alt CaesarCipherCrackZ](http://www.emil.free.bg/CaesarCipherCrackZ_004.png)

- The display text area will display all 25 key encrypted variations of your text.

- Copy and Paste the variation you like to your message and send it to your recipient...

- Or use the **Save** option under the **File** drop down menu to save all of the encrypted variations to a file for later use...

:shipit: Enjoy!
