# picoCcompiler

Projekat predstavlja minijaturnu verziju C kompajlera.
Funkcionise tako sto izvorni fajl, napisan u C programskom jeziku, prevodi u asemblerski jezik.
Asemblerski jezik koji se ovde koristi je NASM (Netwide Assembler).

Za parsiranje teksta se koristi ANTLR (Another Tool For Language Recognition) alat.
ANTLR predstavlja alat za generisanje parsera i lexera na osnovu zadate gramatike.

ANTLR je top-down parser koji automatski generise parser i lexer.
Takodje, generise sintaksna i apstraktno-sintaksna stabla koja sluze za samo prevodjenje.
Jezik je odredjen kontekstno-slobodnom gramatikom koja se iskazuje 
pomocu Prosirene Backus-Naur--ove forme (Extended Backus–Naur Form (EBNF)).
ANTLR podrzava levu i desnu direktnu rekurziju, ali ne podrzava levu indirektnu rekurziju.

Takodje podrzava mehanizam oporavka za ulazni kod koji nije u skladu sa zadatom gramatikom, sto dalje omogucava
laksu obradu gresaka.

Ukratko objasnjenje postupka za prevodjenje koda:

Na osnovu zadate gramatike, ANTLR pravi klase Parser i Lexer.
Parseru je potrebno proslediti stream tokena od kojih on pravi sintaksno stablo za navedeno pravilo iz gramatike.

Sledece dve klase se koriste za ugradnju akcija u kontekstne cvorove stabla:

ANTLR omogucava automatsko pravljenje Visitor i Listener class-a.

Visitor klasa se koristi za eksplicitno obilazenje nekog dela parsnog stabla, odnosno
obilazenje odredjenog pravila u izgenerisanom parsnom stablu.

Listener klasa omogucava ugradnju akcija u svaki cvor parsnog stabla.
Funkcionise tako sto svaki cvor u stablu osluskuje, odnosno ocekuje odredjeni dogadjaj.
Taj dogadjaj je obilazak tog (kontekstnog) cvora. Obilazak celog stabla se vrsi pomocu klase Walker.

Walker klasa je pomocnik listener klase. Kada se napravi "setac", on po default-u
obilazi stablo In-order obilaskom. Prvo se obilazi levo podstablo, zatim trenutni cvor i na kraju desno podstablo.
Kada walker stigne do nekog kontekstnog cvora, tada se aktivira osluskivac za to 
pravilo (koji je napravljen u klasi listener).
 
U klasi listener se naprave odredjene akcije koje se izvrsavaju kada walker aktivira pravilo.
Ovaj mehanizam omogucava jednostavnu ugradju akcija za prevodjenje.

Nekada je potrebno obici pravilo u stablu na nacin koji se razlikuje od standardnog (bez cekanja da walker aktivira akciju). 

Zato klasa visitor omogucava kretanje po parsnom stablu, odnosno obilazak kontekstnih cvorova na proizvoljan nacin.

