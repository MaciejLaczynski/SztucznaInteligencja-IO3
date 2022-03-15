import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //3A
        String file1 = "C:\\Users\\PC\\Desktop\\FolderKurwa\\5SemestrKurwa\\SztucznaInteligencja-IO3\\dane\\australian-type.txt";
        Scanner scanner1 = new Scanner(new File(file1));
        String text1;
        String[] nazwy = new String[14];

        int counter = 1;

        while (scanner1.hasNextLine()){
            text1 = scanner1.nextLine();
            nazwy[counter-1]=text1.split(" ")[0];
            System.out.print(nazwy[counter-1] + " ");
            counter++;
        }

        String file2 = "C:\\Users\\PC\\Desktop\\FolderKurwa\\5SemestrKurwa\\SztucznaInteligencja-IO3\\dane\\_info-data-discrete.txt";
        Scanner scanner2 = new Scanner(new File(file2));
        String text2;
        text2 = scanner2.nextLine();

        while (!text2.split(" ")[0].equals("australian")){
            text2 = scanner2.nextLine();
        }
        int obiect_counter = Integer.valueOf(text2.split(" ")[2]);

        String file = "C:\\Users\\PC\\Desktop\\FolderKurwa\\5SemestrKurwa\\SztucznaInteligencja-IO3\\dane\\australian.txt";
        Scanner scanner = new Scanner(new File(file));

        double[][] australian = new double[counter][obiect_counter];

        String text;
        String[] values;
        for (int i=0;i<obiect_counter;i++) {
            text=scanner.nextLine();
            values = text.split(" ");
            for (int j=0; j<counter; j++){
                australian[j][i] = Double.valueOf(values[j]);
            }
        }

        //3B
        int ones=0;
        int zeros=0;
        for(int i=0; i<obiect_counter; i++){
            if(australian[8][i]==0){
                zeros++;
            }
            else{
                ones++;
            }
        }
        System.out.print("\nZera: "+zeros+"\nJedynki: "+ones+"\n");

        //3C
        double[] min = new double[14];
        double[] max = new double[14];

        for(int i=0; i<counter-1; i++){
            double minimum = australian[i][0];
            double maximum = australian[i][0];
            for (int j=0; j<obiect_counter; j++){
                if (australian[i][j]<minimum){
                    minimum = australian[i][j];
                }
                if (australian[i][j]>maximum){
                    maximum = australian[i][j];
                }
            }
            min[i]=minimum;
            max[i]=maximum;
            System.out.print("\nMaximum " + nazwy[i] + " : " + maximum + "\nMinimum " + nazwy[i] + " : " + minimum);
        }

        //3D, 3E
        for(int i=0; i<counter-1; i++){
            int licznik = 0;
            System.out.print("\nDostepne wartosci dla "+nazwy[i]+": ");
            for (int j=0; j<obiect_counter; j++){
                boolean nowy = true;
                for(int k=0; k<j; k++){
                    if (australian[i][j] == australian[i][k]) {
                        nowy = false;
                    }
                }
                if (nowy){
                    licznik++;
                    System.out.print(australian[i][j] + " ");
                }
            }
            System.out.print("\nIlosc roznych wartosci " + nazwy[i] + " : " + licznik);
        }

        //3F
        double[] average = new double[14];
        double[] average_zeros = new double[14];
        double[] average_ones = new double[14];

        for (int i=0; i<8; i++){
            average[i]=0;
            average_zeros[i]=0;
            average_ones[i]=0;
        }

        for(int i=0; i<obiect_counter; i++){
            if(australian[14][i]==0){
                for(int j=0; j<counter-1; j++){
                    average_zeros[j]+=australian[j][i];
                }
            }
            else{
                for(int j=0; j<counter-1; j++){
                    average_ones[j]+=australian[j][i];
                }
            }
            for(int j=0; j<counter-1; j++){
                average[j]+=australian[j][i];
            }

        }
        for (int i=0; i<8; i++){
            average[i]=average[i]/obiect_counter;
            average_zeros[i]=average_zeros[i]/zeros;
            average_ones[i]=average_ones[i]/ones;
        }

        double[] deviation = new double[14];
        double[] deviation_zeros = new double[14];
        double[] deviation_ones = new double[14];
        for (int i=0; i<8; i++){
            deviation[i]=0;
            deviation_zeros[i]=0;
            deviation_ones[i]=0;
        }

        for(int i=0; i<obiect_counter; i++){
            if(australian[8][i]==0){
                for(int j=0; j<counter-1; j++){
                    deviation_zeros[j]=Math.pow((australian[j][i]-average_zeros[j]), 2);
                }
            }
            else{
                for(int j=0; j<counter-1; j++){
                    deviation_ones[j]=Math.pow((australian[j][i]-average_ones[j]), 2);
                }
            }
            for(int j=0; j<counter-1; j++){
                deviation[j]=Math.pow((australian[j][i]-average[j]), 2);
            }
        }

        for (int i=0; i<8; i++){
            deviation[i]=Math.sqrt(deviation[i]/counter);
            deviation_zeros[i]=Math.sqrt(deviation_zeros[i]/zeros);
            deviation_ones[i]=Math.sqrt(deviation_ones[i]/ones);
        }

        System.out.print("\nOdchylenie standardowe dla Całości:\n");
        for (int i=0; i<8; i++){
            System.out.print(deviation[i]+" ");
        }
        System.out.print("\nOdchylenie standardowe dla Zer:\n");
        for (int i=0; i<8; i++){
            System.out.print(deviation_zeros[i]+" ");
        }
        System.out.print("\nOdchylenie standardowe dla: Jedynek:\n");
        for (int i=0; i<8; i++){
            System.out.print(deviation_ones[i]+" ");
        }

        //4A
        int[] missing_values = new int[10];

        for (int i=0; i< missing_values.length; i++){
            missing_values[i] = (int) (Math.random()*1000%obiect_counter);
            for (int j=0; j<i; j++){
                if (missing_values[j] == missing_values[i]){
                    i--;
                    break;
                }
            }
        }

        for (int i=0; i<missing_values.length; i++){
            for(int j=0; j<counter-1; j++){
                australian[j][missing_values[i]]=average[j];
            }
        }

        //4B
        int a=0;
        int b=1;

        for (int i=0; i<counter-1; i++){
            for(int j=0; j<obiect_counter; j++){
                australian[i][j]=(((australian[i][j]-min[i])*(b-a))/(max[i]-min[i]))+a;
            }
        }

        double[][] stand = new double[counter-1][obiect_counter];

        //4C
        for (int i=0; i<counter-1; i++){
            for (int j=0; j<obiect_counter; j++){
                stand[i][j] = (australian[i][j]-average[i])/deviation[i];
            }
        }

        //4D
        String file3 = "C:\\Users\\PC\\Desktop\\FolderKurwa\\5SemestrKurwa\\SztucznaInteligencja-IO3\\dane\\Churn_Modelling.csv";
        Scanner scan = new Scanner(new File(file3));
        int counter1 = scan.nextLine().split(",").length;

        scan = new Scanner(new File(file3));
        int obiect_counter1 = 0;
        while(scan.hasNextLine()) {
            scan.nextLine();
            obiect_counter1++;
        }

        String[][] csv = new String[counter1][obiect_counter1];

        scan = new Scanner(new File(file3));
        String linia3;
        String[] wartosci3;
        for (int i=0;i<obiect_counter1;i++) {
            linia3=scan.nextLine();
            wartosci3 = linia3.split(",");
            for (int j=0; j<counter1; j++){
                csv[j][i] = wartosci3[j];
            }
        }

        int i=4;
        int licznik = 0;
        System.out.print("\nWartosci dostepne dla geo: ");
        for (int j=1; j<obiect_counter1; j++){
            boolean nowy = true;
            for(int k=0; k<j; k++){
                if (csv[i][j].equals(csv[i][k])) {
                    nowy = false;
                }
            }
            if (nowy){
                licznik++;
                System.out.print(csv[i][j] + " ");
            }
        }
        System.out.print("\nIlosc roznych wartosci geo: " + licznik);

        String[][] geography = new String[licznik-1][obiect_counter1-1];

        for (int j=1; j<obiect_counter1; j++){
            if (csv[i][j].equals("France")){
                geography[0][j-1]="1";
                geography[1][j-1]="0";
            }
            if (csv[i][j].equals("Spain")){
                geography[0][j-1]="0";
                geography[1][j-1]="1";
            }
        }

    }

}
