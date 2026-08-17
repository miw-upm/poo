package upm.exam;

import java.io.File;

public class Reader {

         public static void read ( String name ) {
         File f = new File ( name ) ;
         Scanner sc = new Scanner ( f ) ;

         }

         public static void main ( String [] args ) {
         read ( " date . txt " ) ;
         }
 }
