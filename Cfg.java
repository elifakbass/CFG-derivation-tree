/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package odevv31;

import java.util.Scanner;

/**
 *
 * @author EXCALIBUR
 */
public class Odevv31 {

   public static void main(String[] args) {
        String alfabe,CFG,terminal;
        char non_terminal =0;
        Scanner sc=new Scanner(System.in);
        System.out.println("lütfen bütün ifadeleri , ile ayırınız");
        
        System.out.print("alfabeyi giriniz(örn:a,b,X):");
        alfabe=sc.nextLine();
        char[] x=alfabe.toCharArray();
        for(char harf : x){
            if(harf==','){
                continue;
            }
            if(harf>=65 && harf<=90){
                non_terminal=harf;
            }  
        }
        
        System.out.print("CFG;S--> ");
        CFG=sc.nextLine();
        String[] S=CFG.split(",");
        
        System.out.print(""+non_terminal+"-->");
        terminal=sc.nextLine();
        String[] terminalKelime=terminal.split(",");
        
        String[] non_terminal_tekrar=new String[20];
        int non_index=0;
        
        int non_terminl_sayisi=0; 
        for(String kelime:S){ //for dongusu ile S kümesine kaç tane non terminal harf yazıldığını buluruz
            char[] kelimee=kelime.toCharArray();
            int non_terminal_tekrari=0;
            for(char s:kelimee){
                if(s==non_terminal){
                    non_terminal_tekrari++;
                }
            }
            for(int i=1;i<=non_terminal_tekrari;i++){
                non_terminl_sayisi+=2*i;
            }
        }
        double elemanSayisi=S.length+non_terminl_sayisi;  //toplam dil agacındaki eleman sayısını buluruz
        int elemanSayisii=(int)elemanSayisi;
        String[] olusturulan=new String[elemanSayisii];
        int index=0;
        for(String kelime:S){
            olusturulan[index]=kelime;
            index++;
        }

        
        for(int e=0;e<elemanSayisii;e++){
         int sayac=-1,tane=0; 

            if(olusturulan[e].contains(""+non_terminal)){
            for(int i = 0; i < olusturulan[e].length(); i++) {
                if(non_terminal == olusturulan[e].charAt(i)) { //kelimedeki non terminal harf sayısını tane değişkenine atarız
                    tane++;
                }
            }
            char[] harfler=new char[20];
            harfler=olusturulan[e].toCharArray();
            char[] non_terminal_oncesi=new char[20];

        
                for(char h:harfler){   //non terminal harfin hangi indexte olduğunu sayaca atarız.
                    sayac++;
                    if(h==non_terminal){
                        break;
                    }
                non_terminal_oncesi[sayac]=h;     //kelimenin non terminalden onceki indexteki harflerini aldık
                }
            

            int gecici=sayac;
            

            for(String k:terminalKelime){
                char[] harf=k.toCharArray();
                char[] kelimeninDevamı=new char[10];
                int indeks=0;
                
                for(int i=0;i<harf.length;i++){
                    non_terminal_oncesi[sayac]=harf[i];   //non terminal harf yerine gelebilecek kelimeleri yazarız
                    sayac++;
                    if(olusturulan[e].length()>sayac){
                      kelimeninDevamı[indeks]=harfler[sayac];  //kelime non terminal olan harfin indexinden sonra harf içeriyorsa kelimeninDevamı dizisine aldık.
                      indeks++;
                    }                       
                }
                
                for(char devam:kelimeninDevamı){
                     if(devam!='\0'){
                        non_terminal_oncesi[sayac]=devam;  //kelimenin devamını yeni olusturulan kelimemize ekledik
                        sayac++; 
                    }

                }
                olusturulan[index]=String.valueOf(non_terminal_oncesi);
                index++;
                sayac=gecici;
                for(int j=gecici;j<non_terminal_oncesi.length;j++){  //sonraki olusturulacak kelimeleri kolaylıkla yazmak için non terminal harfin sonrasındaki indexlere null ifade verildi.
                    non_terminal_oncesi[j]='\0';
                }
                
    
            }
           }
        }
        
        
        String[] uretilenKelimeler=new String[elemanSayisii];

        int uretilenIndex=0;
        
        for(int j=0;j<olusturulan.length;j++){

           int var=0;
            for(int k=0;k<uretilenIndex;k++){

             if( olusturulan[j].equals(uretilenKelimeler[k])){
                var=1;
                break;
                }
            }
        
             if(var==0 && !olusturulan[j].contains(""+non_terminal)){ 
                uretilenKelimeler[uretilenIndex]=olusturulan[j];
                uretilenIndex++;
                
            }
            
         
        }
        
        
        System.out.println("için üretilen kelimeler şunlardır:");
        for(int i=0;i<uretilenIndex;i++){
            System.out.println(""+uretilenKelimeler[i]);
        }
     
        
        
        
        
    }
}
