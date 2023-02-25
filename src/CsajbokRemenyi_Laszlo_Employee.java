//Készítő: Csajbók-Reményi László 2023.02.21
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CsajbokRemenyi_Laszlo_Employee{
    
    public static void main(String[] args) throws Exception{
        ArrayList<Dolgozo> dolgozoLista = new ArrayList<>();
        FileReader file=null;
        System.out.println("Készítő: Csajbók-Reményi László 2023.02.21");
        try {
            file = new FileReader("employee.csv");
        } catch (FileNotFoundException ex) {
            System.out.println("Nem elérhető fájl!");
        }
        Scanner scan = new Scanner(file);
        int sum=0;
        double salarysum=0;
        int calculator=0;
        
        String row = scan.nextLine();
        while (scan.hasNext()) {
            row = scan.nextLine();
            String rowSP[] = row.split(":");
            Dolgozo dolgozo = new Dolgozo();

            dolgozo.setName(rowSP[0]);
            dolgozo.setMother(rowSP[1]);
            dolgozo.setCity(rowSP[2]);
            dolgozo.setAddress(rowSP[3]);
            dolgozo.setSalary(rowSP[4]);
            dolgozo.setBorn(rowSP[5]);
            dolgozo.setBonus(rowSP[6]);
            dolgozoLista.add(dolgozo);
            sum++;
        }
        maxFizu(dolgozoLista);
        atlagFizu(dolgozoLista);
        letszam(sum);
        
        scan.close();
        file.close();
    }

    public static void letszam(int szam){
        System.out.println("Dolgozók létszáma: " + szam);
    }
    public static void maxFizu(ArrayList<Dolgozo> dolgozoLista){
        
        
        int maxxalary=0;
        int indexS=0;
        for (int i = 0; i < dolgozoLista.size(); i++) {
            Dolgozo keres = new Dolgozo();
            keres = dolgozoLista.get(i);
            if(Integer.parseInt(keres.getSalary())>maxxalary){
                maxxalary=Integer.parseInt(keres.getSalary());
                indexS=i;
            } 
        }

        
        System.out.println("Legjobban kereső: " + dolgozoLista.get(indexS).getName());
    }
    public static void atlagFizu(ArrayList<Dolgozo> dolgozoLista){
        
        int calculathor=0;
        int salarysumm=0;
        for (int i = 0; i < dolgozoLista.size(); i++) {
            Dolgozo keres = new Dolgozo();
            keres = dolgozoLista.get(i);
            if(keres.getCity().matches("Taktaharkány")){
                calculathor++;
                salarysumm += Double.parseDouble(keres.getSalary());
            }
            
        }
        double atlag = salarysumm/calculathor;
        


        System.out.printf("Fizetések átlaga: %10.2f\n", atlag);

        try {
            FileWriter writer = new FileWriter("fizetesek.txt", false);
            writer.write(String.valueOf(atlag));

            writer.close();
        } catch (IOException e) {

            System.out.println("Nem található a fájl!");
        }
    }

}
