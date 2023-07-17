EmotionalSongs

![Logo del progetto](,none)

Benvenuti in EmotionalSongs! Questo è un progetto Java che ti consente di esplorare e gestire la tua libreria musicale in base alle tue emozioni.

Caratteristiche

	•	Analisi delle emozioni: il programma utilizza un algoritmo avanzato per analizzare le caratteristiche delle canzoni e assegnare loro emozioni specifiche.
 
	•	Gestione della libreria: puoi aggiungere, rimuovere e organizzare le tue canzoni in base alle emozioni associate.
 
	•	Riproduzione intelligente: il programma crea playlist personalizzate in base al tuo stato emotivo attuale.
 

Requisiti di sistema


installare se non presente

	•	Maven 3.x (aggiungi Maven alla variabile di ambiente PATH)
 
	•	JavaFX 18 (aggiungi JavaFX alla variabile di ambiente PATH ? chiedete a bea come si fa)
 

Installazione

	0.	Clona il repository:   
 
git clone https://github.com/caskasclass/LabB2023.git

Creazione file pom.xml

	0.	Apri la root del progetto dovresti vedere solo la cartella src , il file README.md e .gitignor
 
	0.	Crea un file pom.xml
 
	0.	Inserisci il seguente codice nel file pom.xml
 
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  
  <modelVersion>4.0.0</modelVersion>
  
  <groupId>core</groupId>
  
  <artifactId>emotionalsongs</artifactId>
  
  <packaging>jar</packaging>
  
  <version>1.0-SNAPSHOT</version>
  
  <name>emotionalsongs</name>
  
  <url>http://maven.apache.org</url>
  
  <build>
	  
    <sourceDirectory>src/emotionalsongs/java</sourceDirectory>
    
    <resources>
    
      <resource>
      
        <directory>src/emotionalsongs/resources</directory>
	
      </resource>
      
    </resources>
    
    <plugins>
    
      <plugin>
      
        <groupId>org.codehaus.mojo</groupId>
	
        <artifactId>exec-maven-plugin</artifactId>
	
        <version>3.1.0</version>
	
        <configuration>
	
          <mainClass>emotionalsongs</mainClass> <!-- This is the class with the main method -->
	  
        </configuration>
	
      </plugin>
      
      <plugin>
      
        <groupId>org.apache.maven.plugins</groupId>
	
        <artifactId>maven-surefire-plugin</artifactId>
	
        <version>3.0.0-M5</version>
	
        <configuration>
	
            <forkCount>0</forkCount>
	    
            <reuseForks>false</reuseForks>
	    
            <argLine>-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005</argLine>
	    
        </configuration>
	
      </plugin>
      
      <plugin>
      
        <groupId>org.apache.maven.plugins</groupId>
	
        <artifactId>maven-compiler-plugin</artifactId>
	
        <version>3.8.1</version>
	
        <configuration>
	
          <source>18</source>
	  
          <target>18</target>
	  
          <compilerArgs>
	  
            <arg>--module-path</arg>
	    
            <arg>/Users/caska/Developer/JavaStuff/SDK/javafx-sdk-18/lib</arg>
	    
            <arg>--add-modules</arg>
	    
            <arg>javafx.controls</arg>
	    
          </compilerArgs>
	  
        </configuration>
	
      </plugin>
      
    </plugins>
    
  </build>
  
  <properties>
	  
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    
  </properties>
  
  <dependencies>
	  
    <dependency>
    
      <groupId>junit</groupId>
      
      <artifactId>junit</artifactId>
      
      <version>3.8.1</version>
      
      <scope>test</scope>
      
    </dependency>
    
    <dependency>
    
      <groupId>org.openjfx</groupId>
      
      <artifactId>javafx-controls</artifactId>
      
      <version>18</version>
      
    </dependency>
    
    <dependency>
    
      <groupId>org.openjfx</groupId>
      
      <artifactId>javafx-fxml</artifactId>
      
      <version>18</version>
      
    </dependency>
    
    <dependency>
    
      <groupId>org.openjfx</groupId>
      
      <artifactId>javafx-graphics</artifactId>
      
      <version>18</version>
      
    </dependency>
    
  </dependencies>
  
</project>


	0.	Sostituisci il percorso della cartella javafx-sdk-18 con il percorso della tua cartella javafx-sdk-18
 
	•	modifica :
 
<arg>/Users/caska/Developer/JavaStuff/SDK/javafx-sdk-18/lib</arg>


	•	con il percorso della tua cartella javafx-sdk-18, occhio se sei sul windows devi usare il backslash invece dello slash, per eseempio:
 
<arg>\Users\caska\Developer\JavaStuff\SDK\javafx-sdk-18\lib</arg>


	0.	Salva il file pom.xml, dovrebbe funzionare tutto correttamente



COMPITI DA DIVIDERE: (tutte le finestre create devono conservare la memoria)

	⁃	Creazione delle 4 viste (canzone, tabella con tutte le canzoni, playlist, creazione playlist, home)
 
	⁃	Separatore per le mie playlist e l’esplora
 
	⁃	Creare una classe “util” che mixi i colori dell’immagine della playlist in modo che il box abbia quel colori
 
	⁃	Aggiungere finestra per fare il login/logout
 
	⁃	Aggiunta finestra per aggiungere le immagini alla playlist con blocco della finestra sottostante (opzionale blurr)
 
	⁃	Aggiunta controlli per i campi della registrazione
 
	⁃	Creazione del server RMI per la creazione del server (se ne occupa barbio)
 
	⁃	Creazione cartella view della home
