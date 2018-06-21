# Korišćenje:

Objasnimo ukratko kako se koristi kompajler na 64-bitnim Linux operativnim sistemima. 
Prvo, potrebno je skinuti kompajler sa github-a u neki direktorijum. Uzmimo /usr/local/lib za naš folder. 
To ćemo uraditi komandama:
```
$ cd  /usr/local/lib
$ sudo wget https://github.com/aleksandarcolic22414/picoCcompiler/releases/download/v1.0/picoC.zip
$ sudo unzip picoC.zip
$ export CLASSPATH=”.:/usr/local/lib/picoC.jar:$CLASSPATH”
$ alias acc=’java -jar /usr/local/lib/picoC.jar’
```
Sada se komandom:
```
$ acc
```
Pokreće kompajler.

Takođe je potrebno skinuti NASM assembler komandom:
```
$ sudo apt install nasm
```
Što se tiče gcc-a, on je već instaliran na većini linux distribucija, ali, ukoliko nije, potrebno ga je skinuti.

Sada imamo sve što nam je potrebno za prevođenje.

Prevedimo neki C program. Recimo da se zove originalno “program.c”.
Pozovimo kompajler komandom:
```
$ acc program.c
```
Izlaz je novi fajl “program” koji možemo pokrenuti komandom:
```
$ ./program
```
Ta da!

-------------------------------------------------------------------------------------------------------------------------------------
# UVOD

Pre samog objašnjavanja procesa prevođenja, potrebno je objasniti par osnovnih stvari. 
Dakle, svrha samog kompajlera je da kod, napisan u programskom jeziku C, prevede u asemblerski jezik. 
U nastavku teksta, pod programskim jezikom C se podrazumeva ograničena verzija ovog programskog jezika 
(https://github.com/aleksandarcolic22414/picoCcompiler/blob/master/picoC/grammar/picoC.g4). 
Pod assemblerom se u nastavku teksta podrazumeva  NASM (Netwide Assembler). 
Jezik u kome je napisan kompajler je Java.

Kada se pozove kompajler sa opcijom -S, nad ulaznim fajlom koji ima ekstenziju *.c, 
dobija se izlazni fajl koji ima extenziju *.s .
Samo prevodjenje je radjeno za 64-bitne linux operativne sisteme, 
ali se minimalnim modifikacijama može prilagoditi bilo kom operativnom sistemu, 
što će, nadam se, biti jasno posle čitanja ovog dokumenta.
Sva uputstva su data isključivo za linux operativne sisteme.

Kako je izlaz procesa prevodjenja asemblerski fajl, on se ne moze pokrenuti. 
Da bi se program pokrenuo, potrebno je odraditi još par stvari.
Prva stvar je da se pozove NASM assembler nad tim fajlom.
NASM pravi objektni fajl od assemblerskog fajla.
Sledeća stvar koju treba odraditi je linkovanje objektnih fajlova.
Ovde je izabran gcc-ov linker, kako bi se mogle koristiti već napravljene standardne biblioteke (<stdio.h>, <stdlib.h> itd...).
Izlaz linkovanja je izvrsni fajl koji se sada moze pokrenuti (odnosno “program”).
Pokretanje programa se vrsi komandom: ```./imePrograma```

Kada se pozove kompajler bez opcija ($ acc program.c), on automatski izvrsava ceo gore pomenuti proces i izlaz
je samo izvršni “program” koji moze da se pokrene, a ostale fajlove, koji se pojavljuju u koracima izmedju, kompajler briše.
Dakle, da ne bude zabune, kompajler pravi izlazni asemblerski fajl, 
ali da korisnik ne bi morao svaki put ručno da poziva asembler i linker, te operaciju su automatizovane.
Videćemo kasnije kako se to lako u Javi može odraditi.

Prikažimo sada postupak pravljenja izvrsnog fajla od ulaznog c fajla, korak po korak.
Pretpostavimo da imamo C program “prviProgram.c” .
Pokrenimo kompajler i prosledimo mu opciju -S (compile only):
```
$ acc -S prviProgram.c
```
Dobija se asemblerski fajl prviProgram.s
Sada pozovimo nasm assembler nad tim fajlom:
```
$ nasm -f elf64 prviProgram.s
```
Dobija se objektni fajl program.o
Pozovimo linker:
```
$ gcc -m64 program.o
```
Dobija se izvrsni fajl a.out koji se moze pokrenuti komandom:
```
$ ./a.out
```
Dakle, kao što smo videli, ovaj kompajler prevodi direktno C jezik u asemblerski jezik. 
Većina kompajlera ne radi direktno prevođenje, već viši programski jezik prebacuje u neku vrstu među jezika
koji nije zavistan od same arhitekture i koji se kasnije može lako prevesti u asembler bez obzira na tip mašine. 

Ovde se neće detaljno zalaziti u NASM asembler, ali je naravno neophodno znati šta treba da se dobije prevođenjem. 

Alat koji će nam pomoći u parsiranju samog jezika je ANTLR (Another Tool For Language Recognition). 
Ovaj alat na osnovu zadate gramatike automatski pravi parser i lexer. 
Videćemo kasnije kako će nam to pomoći u samom prevođenju.

Da ne bi svaki put ručno pravili klase pomoću antlr-a, pa ih posle posebno prevodili,
poželjno je instalirati  antlr 4 Plugin za NetBeans ili neki drugi IDE u kome će se projekat raditi.
Pogledati na internetu kako se to može odraditi.
Sva naredna uputstva su data za NetBeans IDE.

-------------------------------------------------------------------------------------------------------------------------------------
# Ukratko o ANTLR-u:

ANTLR (Another Tool For Language Recognition) predstavlja alat za generisanje parsera i lexera na osnovu zadate gramatike.
ANTLR je top-down parser koji automatski generiše parser i lexer. 
Takodje, generiše sintaksna i apstraktno-sintaksna stabla koja služe za samo prevođenje. 
Jezik je određen kontekstno-slobodnom gramatikom koja se iskazuje pomocu Proširene Backus-Naur--ove forme 
(Extended Backus–Naur Form (EBNF)).
Takodje, ANTLR podrzava mehanizam oporavka za ulazni kod koji nije u skladu sa zadatom gramatikom, 
sto dalje omogućava lakšu obradu grešaka.
Korišćenje ANTLR-a je vrlo jednostavno, tako da ukoliko neki deo ne bude jasan,
postaće kasnije kada se bude objašnjavalo samo prevođenje.
Ukratko objašnjenje postupka za prevodjenje koda:
Na osnovu zadate gramatike, ANTLR pravi klase Parser i Lexer. 
Parseru je potrebno proslediti stream tokena od kojih on pravi sintaksno stablo za navedeno pravilo iz gramatike. 
Da bi se vršilo samo prevođenje, potrebno je obići to sintaksno stablo i izvršiti neke akcije kao što je štampanje teksta u fajl 
(izlazni asemblerski fajl koji pravimo).
Za izvršavanje akcija u čvorovima stabla se koriste klase Visitor i Listener.
To su klase koje ANTLR takodje automatski generiše.
Visitor klasa se koristi za eksplicitno obilaženje nekog dela parsnog stabla, 
odnosno obilaženje odredjenog pravila u izgenerisanom parsnom stablu.
Listener klasa omogućava ugradnju akcija u svaki čvor parsnog stabla.
Funkcioniše tako što svaki čvor u stablu osluškuje, odnosno očekuje određeni događaj.
Taj događaj je obilazak tog (kontekstnog) čvora. Obilazak celog stabla se vrši pomocu klase Walker.
Walker klasa je pomoćnik listener klase. 
Kada se napravi "šetac", on kreće svoj obilazak od vrha parsnog stabla.
U svakom koraku se obilazi trenutni čvor, a zatim sva deca tog kontekstnog čvora.
Kada walker stigne do nekog kontekstnog čvora, tada se aktivira osluškivac za to pravilo (koji je napravljen u klasi listener).
U klasi listener se prave određene akcije koje se izvršavaju kada walker aktivira pravilo.
Ovaj mehanizam omogućava jednostavnu ugradnju akcija za prevodjenje.
Nekada je potrebno obići pravilo u stablu na način koji se razlikuje od standardnog (bez čekanja da walker aktivira akciju).
To omogućava klasa visitor. 
Pomoću ove klase se može obilaziti bilo koji kontekstni čvor koji je povezan sa čvorom u kome se trenutno nalazimo.
Biće kasnije jasno zašto je to važno.

Pogledajmo sada (uprošćeno) kako izgleda prevodjenje i šta je zamisao...

-------------------------------------------------------------------------------------------------------------------------------------
# Prevodjenje:

Sada će ukratko biti objašnjeno kako se može odraditi prevodjenje jednostavnog programa.
Pretpostavimo da treba prevesti program prvi.c . 
Pretpostavimo takođe da imamo već napravljenu gramatiku jezika kao i parser i lexer. 
Sada je moguće napraviti parsno stablo. Neka naš prvi.c izgleda ovako:
("/prvi.c" je fajl na disku čija je putanja .../prvi.c , a u nastavku je njegov sadržaj; 
U nastavku teksta je korišcena takva notacija za sve fajlove.)

/prvi.c
```
int main()
{
    int a = 5;
    return a;
}
```
Ono što treba da dobijemo je fajl prvi.s koji izgleda ovako:

/prvi.s
```
segment .data

segment .text
	global    main
main:
	push	rbp
	mov	rbp, rsp

	sub	rsp, 4
	mov	dword [rbp-4], 5
	mov	eax, dword [rbp-4]
	jmp	mainExit
mainExit:
	mov	rsp,rbp                                 
	pop	rbp                                 
	ret

segment .bss
```
Da bi to dobili, napravimo parsno stablo od naseg programa prvi.c .
Ono će izgledati ovako:

--------------------------------------------------------------------------------------------------------------------------------------
                                                compilationUnit
                                                       |
                                                       |
                                                -----------------
                                                |               |
                                         translationUnit       <EOF>       
                                                |
                                        externalDecalration
                                                |
                                        functionDefinition
                                                |
                              ---------------------------------------
                              |             |           |   |       |
                         typeSpecifier   declarator     (   )   functionBody
                              |             |                       |
                             int      directDeclarator        compoundStatement
                                            |                       |
                                           main           -------------------------
                                                          |         |             |
                                                          {    blockItemList      }
                                                                    |
                                                                    |
                                         ----------------------------------------------------------------
                                         |                                                              |
                                    blockItemList                                                    statement
                                         |                                                              |
                                      blockItem                                                    jumpStatement
                                         |                                                              |
                                     declaration                                        -------------------------------------        
                                         |                                              |               |                   |
                    -------------------------------------------                       return        expression              ;
                    |                    |                    |                                         |
              typeSpecifier      initDeclarationList          ;                                 assignmentExpression
                    |                    |                                                              |
                   int                   |                                                     conditionalExpression
                                    initDeclarator                                                      |
                                         |                                                      logicalOrExpression
                                         |                                                              |
                       -------------------------------------------                             logicalAndExpression
                       |                 |                       |                                      |
                   declarator            =              assignmentExpression                    inclusiveOrExpression
                       |                                         |                                      |
                directDeclarator                        conditionalExpression                   exclusiveOrExpression
                       |                                         |                                      |
                       a                                logicalOrExpression                       andExpression 
                                                                 |                                      |
                                                        logicalAndExpression                    equalityExpression
                                                                 |                                      |
                                                        inclusiveOrExpression                  relationalExpression 
                                                                 |                                      |
                                                        exclusiveOrExpression                    shiftExpression   
                                                                 |                                      |
                                                            andExpression                        additiveExpression
                                                                 |                                      |
                                                         equalityExpression                    multiplicativeExpression
                                                                 |                                      |
                                                         relationalExpression                     castExpression
                                                                 |                                      |
                                                           shiftExpression                       unaryExpression   
                                                                 |                                      |
                                                          additiveExpression                     postfixExpression
                                                                 |                                      |
                                                       multiplicativeExpression                   primaryExpression
                                                                 |                                      |
                                                           castExpression                               a
                                                                 |
                                                           unaryExpression
                                                                 |
                                                          postfixExpression
                                                                 |
                                                          primaryExpression
                                                                 |
                                                              constants
                                                                 |
                                                                 5
         

--------------------------------------------------------------------------------------------------------------------------------------

Da bi dobili izlaz prvi.s napravićemo novi fajl prvi.s pomoću javine new File() funkcije. 
Sada ćemo obilaziti stablo i štampati tekst u ovaj fajl. Zanemarimo segment .data i segment.bss .
Odštampajmo odmah “segment .txt” u fajl prvi.s i krenimo u obilazak. 
Dakle, krećemo od vrha stabla i compilationUnit pravila. 
Krećemo se po stablu dok ne naiđemo na functionDefinition pravilo (u prva tri pravila se ništa interesantno ne dešava). 
Kada stignemo u function definition pravilo iz njega izvlačimo neke informacije
o nazivu funkcije, parametrima, povratnoj vrednosti itd… 
Stampamo u fajl:

```
global    main
main:
	push	rbp
	mov	rbp, rsp
```
Nastavljamo obilazak. Idemo u functionBody, compoundStatement, blockItemList (takođe se ništa interesantno ne dešava). 
Ulazimo u levo podstablo do pravila declaration i tu izvlačimo tip promenljive koja se deklariše (int). 
Stižemo do pravila initDeclarator iz koga izvlačimo naziv promenljive i vrednost promenljive i štampamo u prvi.s:
```
	sub	rsp, 4
	mov	dword [rbp-4], 5
```
Sada se vraćamo do blockItemLista (ispod functionBody) i obilazimo desno podstablo. 
Stižemo do jumpStatement pravila i izvlačimo podatke da je skok return i vrednost desnog podstabla promenljiva a.
Stampamo u prvi.s:
```
	mov	eax, dword [rbp-4]
	jmp	mainExit
```
Na kraju izlazimo iz svih pravila i ponovo se vraćamo u pravilo functionDefinition. 
Pošto smo obišli sva njegova podstabla, štampamo u prvi.s:
mainExit:
```
	mov	rsp,rbp                                 
	pop	rbp                                 
	ret
```
Vraćamo se u compilationUnit i završavamo prevodjenje.
Kada se naš program (kompajler) izvršio dobili smo izlaz prvi.s u kome se nalazi asemblerski jezik. 
Prilično jednostavno, ha?
Ali da bi se sve ovo izvelo potrebno je objasniti čitav proces korak po korak.
Pa krenimo od pravljenja gramatike...

------------------------------------------------------------------------------------------------------------------------------
# Pravljenje Gramatike:

Da bi mogli da počnemo prevođenje potrebno je da u gramatici definišemo naš jezik. 
Zatim će ANTLR da izgeneriše lexer i parser pomoću kojih ćemo napraviti parsno stablo koje se može 
obilaziti i raditi prevođenje. Gramatika se u ANTLR-u pravi u fajlu koji ima extenziju *.g4.
Ovde se neće detaljno zalaziti u celu gramatiku već samo njene najbitnije segmente.
Cela gramatika se nalazi na: https://github.com/aleksandarcolic22414/picoCcompiler/blob/master/picoC/grammar/picoC.g4 .

Dakle, da bi mogao da se pokrene bilo koji C program on mora da ima neku “ulaznu” tačku, 
odnosno mesto sa kojeg počinje izvršavanje programa. To je funkcija main() . 
Main je funkcija kao i svaka druga, ali ćemo je za početak izdvojiti u gramatici kao posebnu da bi se 
lakše shvatilo samo prevođenje. Takodje, stavićemo kao prvo pravilo u gramatici “compilationUnit” koje će se 
sastojati od “translationUnit” i EOF znaka (znak za kraj fajla). 
“translationUnit” će se sastojati samo od main funckije. Main funkcija će samo imati telo i neće imati argumente. 
Gramatika sada izgleda ovako:

/picoC.g4
```
grammar picoC;

compilationUnit
:	translationUnit? EOF
;

translationUnit
:	main
;

main
:	'int' 'main' '(' ')' functionBody
;

functionBody
:	'{' '}'
;	

WHITE_SPACE
:	[ \t\r\n]+ -> skip
;
```
Lexersko pravilo WHITE_SPACE služi da bi se ignorisali space znaci.
Pređimo na pravljenje klase visitor.

------------------------------------------------------------------------------------------------------------------------------
# Pravljenje Visitora:

Pre nego sto se odradi build projekta, potrebno je u fajlu NetBeansProject/.../nbproject/project.properties 
omogućiti pravljenje klase visitor tako što treba naći liniju koja se nalazi pri kraju fajla
antlr.generator.option.code.visitor=false 
i promeniti vrednost sa false na true.

Kada se sada uradi build projekta, ANTLR (odnosno NetBeans pomoću ANTLR-ovog Plugina) će izgenerisati klase:
```
picoCBaseListener.java
picoCLexer.java
picoCListener.java  
picoC.tokens picoCLexer.tokens
picoCParser.java
picoCBaseVisitor.java
picoCVisitor.java ;
```
Da bi ugradili akcije u čvorove stabla potrebno je da napravimo novu klasu koja će da nasledi klasu picoCBaseVisitor. 
Nazovimo je TranslationVisitor. Klasa BaseVisitor je generička, sa tim da svaka funkcija iz nje vraća
generički tip kog je tipa klasa.
Ako sada nasledimo klasu BaseVisitor kao:

```java
public class TranslationVisitor extends picoCBaseVisitor<String>  
{
	// sve visit funkcije
}
```
svaka metoda koju budemo override-ovali će imati povratnu vrednost String. 

Za svako parsersko pravilo koje smo naveli u gramatici postoji po jedna visit funkcija koja će biti pozvana
kada se bude obilazilo to pravilo (taj cvor stabla).
Da bi izvršili akcije u tim funkcijama potrebno je da ih override-ujemo. 
Override-ujmo funkciju compilationUnit (to je uvek prvo pravilo, odnosno koren stabla). 
Ispišimo samo na standardnom izlazu u kom se pravilu nalazimo.

```java
@Override 
public String visitCompilationUnit
(PicoCParser.compilationUnitContext ctx)
{
    System.out.println(“Pravilo: compilationUnit”);  // ispisi pravilo	
    super.visitCompilationUnit();  // obidji svu decu
    return null;
}
```
Parametar funkcije PicoCParser.compilationUnitContext ctx
je kontekstni čvor u kome se nalazimo. U ovom slučaju to je compilationUnit čvor.
Iz gramatike se vidi da ctx može da ima samo jedno dete i to dete je translationUnit.

Linija super.visitCompilationUnit(); obilazi svu decu od čvora compilationUnit.
To je zgodno u situacijama kada naš čvor ima više dece i ima isti efekat kao kada bi napisali 
```
visit(ctx.prvoDete);
visit(ctx.drugoDete);
visit(ctx.treceDete);
…
visit(ctx.poslednjeDete);
```
Odradimo istu stvar za sve visit funkcije koje mogu da se override-uju.

```java
@Override 
public String visitTranslationUnit
(PicoCParser.translationUnitContext ctx)
{
    System.out.println(“Pravilo: translationUnit”);  // ispisi pravilo	
    super.visitTranslationUnit();  // obidji svu decu
    return null;
}

@Override 
public String visitMain
(PicoCParser.mainContext ctx)
{
    System.out.println(“Pravilo: main”);  // ispisi pravilo	
    super.visitMain();  // obidji svu decu
    return null;
}

@Override 
public String visitFunctionBody
(PicoCParser.functionBodyContext ctx)
{
    System.out.println(“Pravilo: functionBody”);  // ispisi pravilo	
    super.functionBody();  // obidji svu decu
    return null;
}
```
Sada, kada imamo akcije ugradjene u čvorove, potrebno nam je stablo koje ćemo da obiđemo. 
Pogledajmo kako to može da se odradi.

------------------------------------------------------------------------------------------------------------------------------
# Pravljenje I Obilazak Parsnog Stabla:

Napravimo posebno Main klasu u kojoj ćemo napraviti sve što nam je potrebno za prevodjenje.

``` java
public class Main
{	
    public static final String pathToInputFile = “//putanja//do//ulaznog//fajla.c”
	
    public static void main(String[] args) 
    {
		try {
			InputStream is = new FileInputStream(pathToInputFile);
			ANTLRInputStream in = new ANTLRInputStream(is);
			picoCLexer lexer = new picoCLexer(in);
			CommonTokenStream tokens = new  CommonTokenStream(lexer);
			picoCParser parser = new picoCParser(tokens);
			ParseTree tree = parser.compilationUnit();
		
			TranslationVisitor visitor = new TranslationVisitor();
			visitor.visit(tree);
		} catch (Exception ex) {
			System.err.println(ex); 
		}
	}
}
```
Za više informacija o tome kako ANTLR funkcionise pogledati: The Definitive ANTLR 4 Reference - Terence Parr.

Ukratko objašnjenje postupka:

Potrebno je napraviti InputStream koji se prosleđuje ANTLRInputStream-u. 
InputStream u ovom slučaju je FileInputStream ali može biti bilo koji.
Naš FileInputStream predstavlja stream do fajla koji prevodimo.
U tom fajlu će se naravno nalaziti naš program koji ćemo kasnije napisati.
Zatim se pravi lexer koji kao ulazni parametar prihvata ANTLRInputStream.
CommonTokenStream privata lexer i commonTokenStream se sada prosleđuje parseru.
Parsno stablo se pravi pozivanjem nekog parserskog pravila.
To je u nasem slucaju compilationUnit() ili startno pravilo.
Treba uvideti da se može pozvati bilo koje pravilo iz gramatike što
može dosta da olakša debagovanje samog kompajlera!

Sada, kada imamo parsno stablo, možemo da ga obiđemo pomoću klase visitor.
Pravi se visitor i poziva metoda visit kojoj se prosleđuje parsno stablo. 
Sada visitor obilazi parsno stablo u redosledu koji smo mi odredili
(Setimo se da smo za svako pravilo pozivali metodu super.visitImePravila() koje obilazi svu decu). 
Samo nam još preostaje da napravimo ulazni fajl koji će da se prevodi. 
Linija	public static final String pathToInputFile = “//putanja//do//ulaznog//fajla”;
označava putanju do ulaznog fajla koji “prevodimo”. Neka naš fajl izgleda ovako:

//putanja//do//ulaznog//fajla.c
```c
int main()
{
}
```
Kada se napravi parsno stablo (ParseTree tree) od ulaznog fajla (u main metodi od malopre),
ono će izgledati ovako:

--------------------------------------------------------------------------------------------------------------------------------------
                                                compilationUnit
                                                       |
                                                       |
                                              ---------------------
                                              |                    |
                                        translationUnit          <EOF>
                                              |
                                             main
                                              |
                                              |
                                   -------------------------------
                                   |        |       |    |       |
                                  int      main     (    )  functionBody
                                                                 |
                                                             -----------
                                                             |         |
                                                             {         }
                                                            
--------------------------------------------------------------------------------------------------------------------------------------

Kada se pozove metoda visitor.visit(tree) visitor kreće od korena stabla odnosno
compilationUnit pravila i redom aktivitra metode koje smo override-ovali u TranslationVisitor klasi.
Prvo se poziva metoda:

```java
@Override 
public String visitCompilationUnit
(PicoCParser.compilationUnitContext ctx)
{
    System.out.println(“Pravilo: compilationUnit”);  // ispisi pravilo	
    super.visitCompilationUnit();  // obidji svu decu
    return null;
}
```
Ova metoda ispisuje na ekranu:
Pravilo: compilationUnit 
i poziva metodu nadklase koja obilazi svu decu (super.visitCompilationUnit()).
Prvo dete in-order obilaskom je translationUnit, pa se ulazi u taj čvor i poziva metoda 
visitTranslationUnit() na sličan način kao i compilationUnit malopre…
Ispisuje se na ekranu:
Pravilo: translationUnit
I tako dalje...

Na kraju će na ekranu biti ispisano:
```
Pravilo: compilationUnit 
Pravilo: translationUnit
Pravilo: main
Pravilo: functionBody.
```
Dodajmo sada po jednu liniju svakoj metodi da bismo videli kako se može nastaviti rad
posle obilaženja dece (što i jeste osnovna zamisao kod pravljenja kompajlera).
Prepravimo metodu compilationUnit tako da igleda ovako:
```java
@Override 
public String visitCompilationUnit
(PicoCParser.compilationUnitContext ctx)
{
    System.out.println(“Pravilo: compilationUnit”);  // ispisi pravilo	
    super.visitCompilationUnit();  // obidji svu decu
    // Nastavak rada
    System.out.println(“Povratak u pravilo: compilationUnit”); 
    return null;
}
```
Ubacimo dodatno štampanje u svaku metodu i pokrenimo program ponovo.
Sada će na ekranu biti ispisano:
```
Pravilo: compilationUnit 
Pravilo: translationUnit
Pravilo: main
Pravilo: functionBody.
Povratak u pravilo: functionBody
Povratak u pravilo: main
Povratak u pravilo: translationUnit
Povratak u pravilo: compilationUnit
```
Primećujemo da se posle svakog rekurzivnog obilaska dece, vraćamo u pravilo iz kog smo krenuli u obilazak. 
To je mehanizam koji omogućava samo prevodjenje i vrlo je jednostavan za korišćenje.
Sve što sada treba uraditi da bi dobili pravo prevođenje je pravljenje novog fajla
na početku našeg programa u koji ćemo upisivati neki asemblerski kod tokom obilaska stabla.

Pretpostavimo da smo napravili klasu Writers koja će raditi sa našim izlaznim fajlom.
Pretpostavimo i da ta klasa ima statičku metodu za upis u naš fajl koja glasi: 
Writers.emitInstruction(String instruction). 
Kada se pozove, ova metoda stampa String instruction u fajl i znak za prelazak u novi red (‘\n’). 
Naravno, imaćemo i metodu init() koja će da inicijalizuje potrebne stvari za rad sa fajlom. 
Sada je potrebno, da u nekim metodama u visitor klasi, štampamo asemblerski kod u naš fajl. 
Pošto je naša gramatika vrlo jednostavna, jedina motoda iz koje treba stampati neke instrukcije je visitMain(). 
U njoj treba odštampati instrukcije kao sto su:
```
	global main
main:
```
itd...

Prepravimo visitMain:

```java
@Override 
public String visitMain
(PicoCParser.mainContext ctx)
{
    Writers.emitInstruction(“\tglobal main”);  //stampaj globalnu funkciju
    Writers.emitInstruction(“main:”);
    Writers.emitInstruction(“\tpush    rbp”) // snimi rbp registar
    Writers.emitInstruction(“\tmov	rbp, rsp”); // novi stack frame
    visit(ctx.functionBody());  // obidji telo funkcije
    Writers.emitInstruction(“\tmov	rsp, rbp”); // skini stack frame
    Writers.emitInstruction(“\pop    rbp”) // popuj rbp registar
    Writers.emitInstruction(“\ret”); // izlaz iz funkcije

    return null;
}
```
Dakle, prvo će se odstampati standardni ulaz u main funkciju (prve četiri instrukcije), 
pa će metoda visit(ctx.functionBody) štampati instrukcije koje se nalaze u telu main funkcije
programa koji prevodimo, pa će se tek onda nastaviti sa stampanjem standardon
izlaza iz funkcije (poslednje 3 instrukcije).
Naravno, naša funkcija za obilazak tela funkcije visit.(ctx.FunctionBody) neće odstampati nista,
pošto je ostala nepromenjena, ali je bitno da uvidimo kako će teći proces prevođenja...

Takodje je potrebno pre svih štampanja instrukcija odštampati:
```
segment .text
```
što označava segment koda u asembleru. 
To se može odraditi u compilationUnit pravilu na primer…

Kada se sada pokrene program, kao rezultat ćemo imati izlazni fajl koji će u sebi
sadržati asemblerske instrukcije.
Nad tim fajlom se sada treba pozvati asembler:
$ nasm -f elf64 -o out.o izlazniFajl.s
I linker:
$ gcc -m64 -o izlaz out.o
I program se sada može pokrenuti instrukcijom:
$ ./izlaz

------------------------------------------------------------------------------------------------------------------------
Čestitam! Upravo smo napravili naš prvi kompajler! 
Dobro, ja sam napravio, ali opet...


